/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.controller;

import com.web.project.dto.UserDTO;
import com.web.project.entity.UserEntity;
import com.web.project.errors.ErrorConstants;
import com.web.project.exception.UserAccessException;
import com.web.project.service.ControllerService;
import com.web.project.service.MailSenderService;
import com.web.project.validator.UserFormValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author P061
 */
@Controller
@SessionAttributes("userDTO")
public class GameController {

    @Autowired
    private ControllerService controllerService;

    @Autowired
    private UserFormValidator userValidator;

    @Autowired
    private MailSenderService mailSenderService;

    @RequestMapping("/")
    public String redirect() {

        return "index.html";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/menu")
    public ModelAndView menu() {

        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject("userDTO", new UserDTO());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/intro")
    public ModelAndView intro() {

        ModelAndView mav = new ModelAndView("intro.html");

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/how-to-play")
    public ModelAndView howToPlay() {

        ModelAndView mav = new ModelAndView("howtoplay.html");

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/items")
    public ModelAndView items() {

        ModelAndView mav = new ModelAndView("items.html");

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/enemies")
    public ModelAndView enemies() {

        ModelAndView mav = new ModelAndView("enemies.html");

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list-games")
    public ModelAndView listGames() {

        ModelAndView mav = new ModelAndView("leaderboard.html");
        mav.addObject("listGames", controllerService.listGames());
        return mav;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/stats")
    public ModelAndView stats(@ModelAttribute("userDTO") UserDTO userDto) {

        ModelAndView mav = new ModelAndView("userstats.html");
        mav.addObject("userDTO", controllerService.findExistingUser(userDto.getUsername()));
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(@Valid @ModelAttribute("userDTO") UserDTO userDto, BindingResult bindingResult, Model model) throws UserAccessException {

        model.addAttribute("userDTO", userDto);
        System.out.println("BINDING: " + bindingResult);

        if (controllerService.findUser(userDto.getUsername())) {
            FieldError error = new FieldError("addUserDTO", "username", ErrorConstants.USER_EXISTS_ERROR);
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            return "index.html";
        }

        controllerService.createUser(userDto);

        return "index.html";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(@Valid @ModelAttribute("userDTO") UserDTO userDto, BindingResult bindingResult, Model model) throws UserAccessException {

        model.addAttribute("userDTO", userDto);
        System.out.println("BINDING: " + bindingResult);

        UserDTO userDto1 = null;

        if (!(controllerService.findUser(userDto.getUsername()))) {
            FieldError error = new FieldError("loginUserDTO", "username", ErrorConstants.USER_NOT_FOUND_ERROR);
            bindingResult.addError(error);
        } else {
            userDto1 = controllerService.findExistingUser(userDto.getUsername());
            if (!(userDto.getPassword().equals(userDto1.getPassword()))) {
                FieldError error = new FieldError("loginUserDTO", "password", ErrorConstants.PASSWORD_MISMATCH_ERROR);
                bindingResult.addError(error);
            }
        }

        if (bindingResult.hasErrors()) {
            return "index.html";
        } else {

            return "menu2.html";
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/forgot-password")
    public ModelAndView forgotPassword() {

        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject("userDTO", new UserDTO());

        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/send-password", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String sendMail(@Valid @ModelAttribute("userDTO") UserDTO userDto, BindingResult bindingResult, Model model) {

        model.addAttribute("userDTO", userDto);
        System.out.println("BINDING: " + bindingResult);

        UserDTO userDto1 = null;

        if (!(controllerService.findUser(userDto.getUsername()))) {
            FieldError error = new FieldError("UserDTO", "username", ErrorConstants.USER_NOT_FOUND_ERROR);
            bindingResult.addError(error);
        } else {
            userDto1 = controllerService.findExistingUser(userDto.getUsername());
        }

        if (bindingResult.hasErrors()) {
            return "index.html";
        } else {

            mailSenderService.simpleSend(userDto1, userDto.getEmailAddress());
            System.out.println("EMAIL ENVIADO");
            return "redirect:/menu";
        }

    }

    @InitBinder("UserDTO")
    protected void userDTOBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

}

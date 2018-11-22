/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.service.impl;

import com.web.project.dto.UserDTO;
import com.web.project.service.ControllerService;
import com.web.project.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author P061
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
    ControllerService controllerService;
           
    @Override
    public void simpleSend(UserDTO userDto, String to) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password recovery.");
        
        UserDTO userDto1 = controllerService.findExistingUser(userDto.getUsername());
        
        String textMessage = "Hello "+userDto1.getUsername()+"\n\nYou asked for your password, so here it is: "+userDto1.getPassword()+"\n\nGood Luck!.";
        
        message.setText(textMessage);
        emailSender.send(message);

    }
}

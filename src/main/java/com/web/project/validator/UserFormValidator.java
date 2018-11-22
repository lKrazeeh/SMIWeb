/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.validator;

import com.web.project.dto.UserDTO;
import com.web.project.errors.ErrorConstants;
import com.web.project.service.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

/**
 *
 * @author P061
 */
@Component
public class UserFormValidator <T extends Object> implements Validator {
    
    @Autowired
    ControllerService controllerService;
    

    /**
     *
     * @param o
     * @param errors
     * @throws ValidatorException
     */
    @Override
    public void validate(Object o, Errors errors){
        UserDTO userDto = (UserDTO) o;

        if (userDto.getUsername().length() > 16) {
            errors.rejectValue("username", ErrorConstants.USERNAME_LENGTH_ERROR);
        }

        if (userDto.getPassword().length() > 20) {
            errors.rejectValue("password", ErrorConstants.PASSWORD_LENGTH_ERROR);
        }

    }

   @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }
    
}

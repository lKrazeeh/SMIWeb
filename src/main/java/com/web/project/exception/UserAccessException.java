/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.exception;

/**
 *
 * @author P061
 */
public class UserAccessException extends Exception{
    
    public UserAccessException(){
        super();
    }
    
    public UserAccessException(Exception ex){
        super(ex);
    }
    
    public UserAccessException(String message){
        super(message);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.service;

import com.web.project.dto.GameDTO;
import com.web.project.dto.UserDTO;
import com.web.project.entity.UserEntity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author P061
 */
public interface ControllerService {
    
    List<GameDTO> listGames();

    void createUser(UserDTO userDto);
    
//    void loginUser(UserDTO userDto);
    
    boolean findUser(String username);
    
    UserEntity findExistingUserEntity(String username);
    
    UserDTO findExistingUser(String username);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.service.impl;

import com.web.project.dto.GameDTO;
import com.web.project.dto.UserDTO;
import com.web.project.entity.UserEntity;
import com.web.project.mapper.GameMapper;
import com.web.project.mapper.UserMapper;
import com.web.project.repository.GameRepository;
import com.web.project.repository.UserRepository;
import com.web.project.service.ControllerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author P061
 */
@Service
public class ControllerServiceImpl implements ControllerService{

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    GameRepository gameRepository;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    GameMapper gameMapper;
        
    @Override
    public List<GameDTO> listGames() {
        
        return gameMapper.toDto(gameRepository.findAll());
    }
    
//    @Override
//    public void loginUser(UserDTO userDto) {
//        if(!(findUser(userDto.getUsername()))){
//            System.out.println("No existe una cuenta con ese nombre.");
//        }else{
//            UserDTO userDto1 = findExistingUser(userDto.getUsername());
//            if(!(userDto.getPassword().equals(userDto1.getPassword()))){
//                System.out.println("Contraseña incorrecta para ese usuario.");
//            }
//        }
//    }

    @Override
    public void createUser(UserDTO userDto) {
        if(!(findUser(userDto.getUsername()))){
            userRepository.save(userMapper.toDao(userDto));
        }
    }

    @Override
    public boolean findUser(String username) {
        boolean found = true;
        
        if(userRepository.findByUsername(username) == null){
            found = false;
        }
        return found;
    }

    @Override
    public UserDTO findExistingUser(String username) {
        UserDTO userDto = userMapper.toDto(userRepository.findByUsername(username));
        return userDto;
    }

    @Override
    public UserEntity findExistingUserEntity(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity;
    }

}

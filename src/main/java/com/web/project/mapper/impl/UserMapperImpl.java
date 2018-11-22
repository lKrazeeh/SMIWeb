/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.mapper.impl;

import com.web.project.dto.UserDTO;
import com.web.project.entity.UserEntity;
import com.web.project.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author P061
 */
@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public UserEntity toDao(UserDTO userDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setTotalScore(0);
        userEntity.setTotalKills(0);
        userEntity.setTotalHits(0);
        userEntity.setTotalShots(0);
        userEntity.setTotalAccuracy((float)0);
        return userEntity;
    }

    @Override
    public UserDTO toDto(UserEntity userEntity) {
        UserDTO userDto = new UserDTO();
        
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        userDto.setTotalScore(userEntity.getTotalScore());
        userDto.setTotalKills(userEntity.getTotalKills());
        userDto.setTotalShots(userEntity.getTotalShots());
        userDto.setTotalHits(userEntity.getTotalHits());
        userDto.setTotalAccuracy(userEntity.getTotalAccuracy());
        return userDto;
    }

    @Override
    public List<UserDTO> toDto(List<UserEntity> userEntity) {
        List<UserDTO> userDtoList = new ArrayList<>();
        for (UserEntity userEntity1: userEntity){
            userDtoList.add(toDto(userEntity1));
        }

        return userDtoList;
    }

    @Override
    public List<UserEntity> toDao(List<UserDTO> userDto) {
        List<UserEntity> userEntityList = new ArrayList<>();

        for (UserDTO userDto1: userDto){
            userEntityList.add(toDao(userDto1));
        }

        return userEntityList;
    }
    
}

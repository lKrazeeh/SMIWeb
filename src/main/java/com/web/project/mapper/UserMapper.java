/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.mapper;

import com.web.project.dto.UserDTO;
import com.web.project.entity.UserEntity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author P061
 */
public interface UserMapper {
    
    public UserEntity toDao(UserDTO userDto);

    public UserDTO toDto(UserEntity userEntity);

    public List<UserDTO> toDto(List<UserEntity> userEntity);

    public List<UserEntity> toDao(List<UserDTO> userDto);
    
}

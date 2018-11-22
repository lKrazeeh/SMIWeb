/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.mapper;

import com.web.project.dto.GameDTO;
import com.web.project.entity.GameEntity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author P061
 */
public interface GameMapper {
    
    public GameEntity toDao(GameDTO gameDto);

    public GameDTO toDto(GameEntity gameEntity);

    public List<GameDTO> toDto(List<GameEntity> gameEntity);

    public List<GameEntity> toDao(List<GameDTO> gameDto);
    
}

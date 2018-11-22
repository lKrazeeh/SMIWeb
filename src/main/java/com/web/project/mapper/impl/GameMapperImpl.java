/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.mapper.impl;

import com.web.project.dto.GameDTO;
import com.web.project.entity.GameEntity;
import com.web.project.mapper.GameMapper;
import com.web.project.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author P061
 */
@Component
public class GameMapperImpl implements GameMapper{

    @Autowired
    UserMapper userMapper;
    
    @Override
    public GameEntity toDao(GameDTO gameDto) {
        GameEntity gameEntity = new GameEntity();
        
        gameEntity.setScore(gameDto.getScore());
        gameEntity.setLevel(gameDto.getLevel());
        gameEntity.setKills(gameDto.getKills());
        gameEntity.setShots(gameDto.getShots());
        gameEntity.setHits(gameDto.getHits());
        gameEntity.setAccuracy(gameDto.getAccuracy());
        //gameEntity.setUserEntity(userMapper.toDao(gameDto.getUserDto()));
        
        return gameEntity;
    }

    @Override
    public GameDTO toDto(GameEntity gameEntity) {
        GameDTO gameDto = new GameDTO();
        
        gameDto.setScore(gameEntity.getScore());
        gameDto.setLevel(gameEntity.getLevel());
        gameDto.setKills(gameEntity.getKills());
        gameDto.setShots(gameEntity.getShots());
        gameDto.setHits(gameEntity.getHits());
        gameDto.setAccuracy(gameEntity.getAccuracy());
        gameDto.setUsername(userMapper.toDto(gameEntity.getUserEntity()).getUsername());
        
        return gameDto;
    }

    @Override
    public List<GameDTO> toDto(List<GameEntity> gameEntity) {
        List<GameDTO> gameDtoList = new ArrayList<>();
        for (GameEntity gameEntity1: gameEntity){
            gameDtoList.add(toDto(gameEntity1));
        }

        return gameDtoList;
    }

    @Override
    public List<GameEntity> toDao(List<GameDTO> gameDto) {
        List<GameEntity> gameEntityList = new ArrayList<>();

        for (GameDTO gameDto1: gameDto){
            gameEntityList.add(toDao(gameDto1));
        }

        return gameEntityList;
    }
    
}

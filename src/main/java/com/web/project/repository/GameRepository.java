/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.repository;

import com.web.project.entity.GameEntity;
import com.web.project.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author P061
 */
@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer>, JpaSpecificationExecutor{
    
    
}

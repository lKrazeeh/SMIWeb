/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author P061
 */
@Table(name = "game")
@Entity
public class GameEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "game_score")
    private Integer score;
    @Column(name = "game_level_reached")
    private Integer level;
    @Column(name = "game_enemies_killed")
    private Integer kills;
    @Column(name = "game_shots")
    private Integer shots;
    @Column(name = "game_hits")
    private Integer hits;
    @Column(name = "game_accuracy")
    private String accuracy;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity userEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "GameEntity{" + "id=" + id + ", score=" + score + ", level=" + level + ", kills=" + kills + ", shots=" + shots + ", hits=" + hits + ", accuracy=" + accuracy + ", userEntity=" + userEntity + '}';
    }

    

    
}

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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

/**
 *
 * @author P061
 */
@Table(name = "users")
@Entity
public class UserEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 16)
    @Column(name = "user_name", unique = true)
    private String username;
    
    @Size(max = 20)
    @Column(name = "user_password")
    private String password;
    
    @Column(name = "user_kills")
    private Integer totalKills;
    
    @Column(name = "user_score")
    private Integer totalScore;
    
    @Column(name = "user_shots")
    private Integer totalShots;
    
    @Column(name = "user_hits")
    private Integer totalHits;
    
    @Column(name = "user_accuracy")
    private Float totalAccuracy;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(Integer totalKills) {
        this.totalKills = totalKills;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalShots() {
        return totalShots;
    }

    public void setTotalShots(Integer totalShots) {
        this.totalShots = totalShots;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public Float getTotalAccuracy() {
        return totalAccuracy;
    }

    public void setTotalAccuracy(Float totalAccuracy) {
        this.totalAccuracy = totalAccuracy;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "id=" + id + ", username=" + username + ", password=" + password + ", totalKills=" + totalKills + ", totalScore=" + totalScore + ", totalShots=" + totalShots + ", totalHits=" + totalHits + ", totalAccuracy=" + totalAccuracy + '}';
    }
    
    
}

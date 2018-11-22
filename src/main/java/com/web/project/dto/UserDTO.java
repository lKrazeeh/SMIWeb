/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.dto;

import com.web.project.errors.ErrorConstants;
import javax.validation.constraints.Size;

/**
 *
 * @author P061
 */
public class UserDTO {
    
    @Size(max = 16, message = ErrorConstants.USERNAME_LENGTH_ERROR)
    private String username;
    @Size(max = 20, message = ErrorConstants.PASSWORD_LENGTH_ERROR)
    private String password;
    
    private Integer totalKills;
    private Integer totalScore;
    private Integer totalShots;
    private Integer totalHits;
    private Float totalAccuracy;
    private String emailAddress;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", password=" + password + ", totalKills=" + totalKills + ", totalScore=" + totalScore + ", totalShots=" + totalShots + ", totalHits=" + totalHits + ", totalAccuracy=" + totalAccuracy + '}';
    }
    

    
}

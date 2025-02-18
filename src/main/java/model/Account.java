/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
/**
 *
 *
 */
public class Account {
    private String accountId;
    private String username;
    private String password;
    private String roleId;
    private String registrationDate;
    private String lv2password;
    private int status;

    public String getLv2password() {
        return lv2password;
    }

    public void setLv2password(String lv2password) {
        this.lv2password = lv2password;
    }

    public Account() {
    }

    public Account(String accountId, String username, String password, String roleId, String registrationDate, int status) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public Account(String accountId, String username, String password, String registrationDate) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    
}

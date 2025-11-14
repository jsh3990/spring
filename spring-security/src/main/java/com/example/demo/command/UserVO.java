package com.example.demo.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String username;
    private String password;
    private String role;
}

//CREATE TABLE USERS(
// USERNAME VARCHAR(30) PRIMARY KEY,
//PASSWORD VARCHAR(50) NOT NULL,
//ROLE VARCHAR(30)
//);

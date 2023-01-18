package com.garanti.SpringBootRestJDBC.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomUser
{
    private String username = "";

    private String password = "";

    public CustomUser(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}
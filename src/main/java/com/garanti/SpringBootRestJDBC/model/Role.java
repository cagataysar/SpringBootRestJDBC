package com.garanti.SpringBootRestJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@AllArgsConstructor
public class Role implements GrantedAuthority
{
    private static final long serialVersionUID = 9156064156119386503L;

    private String name;

    @Override
    public String getAuthority()
    {
        return name;
    }
}
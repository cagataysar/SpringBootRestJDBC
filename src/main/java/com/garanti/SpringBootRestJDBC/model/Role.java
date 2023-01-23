package com.garanti.SpringBootRestJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//TODO: Service package'ına UserDetails sınıfı eklenecek
@Setter
@Getter
@AllArgsConstructor
public class Role implements org.springframework.security.core.GrantedAuthority
{
    private static final long serialVersionUID = 9156064156119386503L;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
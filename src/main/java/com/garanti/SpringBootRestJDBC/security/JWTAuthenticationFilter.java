package com.garanti.SpringBootRestJDBC.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garanti.SpringBootRestJDBC.model.CustomUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

// yetkilendirme
// JWT üreten class ve component değil, çünkü sisteme manuel dahil ediliyor
// /login endpointine username password gönderildiğinde
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException
    {
        try
        {
            CustomUser creds = new ObjectMapper().readValue(req.getInputStream(), CustomUser.class);
            // bunun yerine spring security 'ye bıraktık
            // userdb.select(creds.getUsername(), creds.getPassword());
            // henüz rolleri belli değil
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<GrantedAuthority>()));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException
    {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException
    {
        // MY_SECRET_KEY önemli
        User principal = ((User) auth.getPrincipal());
        String rolestring = principal.getAuthorities().toArray()[0].toString();
        String str = principal.getUsername() + "-" + rolestring;
        String token = JWT.create().withSubject(str).withExpiresAt(new Date(System.currentTimeMillis() + 900000)).sign(Algorithm.HMAC512("MY_SECRET_KEY".getBytes()));
        // body = (recep) 30948hgb57gbhg9wpuısgh==
        String body = "(" + principal.getUsername() + ") " + token;
        res.getWriter().write(body);
        res.getWriter().flush();
    }
}
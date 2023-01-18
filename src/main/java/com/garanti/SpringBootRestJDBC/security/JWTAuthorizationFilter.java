package com.garanti.SpringBootRestJDBC.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
    public JWTAuthorizationFilter(AuthenticationManager authManager)
    {
        super(authManager);
    }

    // dofilter yerine spring security classları kullandığım için dointernalfilter
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        // bu aşamada kullanıcı zaten doğrulandı ve token ı verildi
        String token = req.getHeader("Authorization");
        // Bearer 30948hgb57gbhg9wpuısgh==
        UsernamePasswordAuthenticationToken authenticationToken = null;
        if (token != null)
        {
            try
            {
                // kullanıcı adı şifre kontrolü DEĞİL !!!
                // jwt geçerlilik kontrolü
                String user = JWT.require(Algorithm.HMAC512("MY_SECRET_KEY".getBytes())).build().verify(token.replace("Bearer ", "")).getSubject();
                System.err.println("------> kullanıcı adı ve rol = " + user);
                if (user != null)
                {
                    // Ahmet-USER
                    // Deniz-ADMIN
                    SimpleGrantedAuthority auth = new org.springframework.security.core.authority.SimpleGrantedAuthority(user.split("-")[1]);
                    authenticationToken = new UsernamePasswordAuthenticationToken(user.split("-")[0], null, Collections.singletonList(auth));
                }
            }
            catch (Exception e)
            {
            }
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(req, res);
    }
}
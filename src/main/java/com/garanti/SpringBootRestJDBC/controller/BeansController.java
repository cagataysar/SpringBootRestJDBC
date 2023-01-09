package com.garanti.SpringBootRestJDBC.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

//@Profile (value = "dev") // kendi yazdığım application-dev.properties profilinde çalışmasını sağlar
@RestController
@AllArgsConstructor
public class BeansController
{
    /*@Value(value = "${rest.level}")
    public Integer level;*/

    private ApplicationContext applicationContext;

    @GetMapping (path = "beans")
    public void setApplicationContext() {
        // localhost:9090/SpringBootRestJDBC/beans
        // context'e (SpringBootRestJDBC) ihtiyaç yok
        // localhost:9090/beans çalışacaktır
        System.err.println("-----> " + applicationContext.getClass());
        String[] names = applicationContext.getBeanDefinitionNames();
        Arrays.sort(names);
        System.err.println("----------------------------------------------------");
        System.err.println("---- " + names.length + " ----");
        for (String name : names)
        {
            System.err.println(name + " ---> " + applicationContext.getBean(name).getClass().getName());
        }
        System.err.println("----------------------------------------------------");
    }
}
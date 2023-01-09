package com.garanti.SpringBootRestJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = TransactionAutoConfiguration.class) ile ihtiyacımız olmayan configuration class'larını eklememesini isteyebiliriz
@SpringBootApplication //(exclude = TransactionAutoConfiguration.class)
public class SpringBootRestJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestJdbcApplication.class, args);
	}
}

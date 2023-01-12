package com.garanti.SpringBootRestJDBC.exceptionhandler;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler
{
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public void badSqlGrammerExceptionHandler(BadSqlGrammarException e)
    {
        // server.error.include-message = always
        System.err.println("Bad sql yakalandı -> " + e.getMessage());
    }
}

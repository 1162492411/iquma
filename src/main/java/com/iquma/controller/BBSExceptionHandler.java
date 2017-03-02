package com.iquma.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BBSExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e,Model model){
        model.addAttribute("exceptionName",e.getClass().getSimpleName());
        model.addAttribute("exceptionResult",e.getMessage());
        return "status/actionResult";
    }

}

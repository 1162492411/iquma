package com.iquma.controller;


import org.apache.shiro.ShiroException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BBSExceptionHandler {

    @ExceptionHandler(ShiroException.class)
    public String handleshiroException(ShiroException e, Model model){
        model.addAttribute("exceptionName","认证异常");
        model.addAttribute("exceptionResult","账户认证时出现异常，请确认信息后重试~");
        return "status/actionResult";
    }


    @ExceptionHandler(Exception.class)
    public String handleException(Exception e,Model model){
        model.addAttribute("exceptionName",e.getClass().getSimpleName());
        model.addAttribute("exceptionResult",e.getMessage());
        return "status/actionResult";
    }



}

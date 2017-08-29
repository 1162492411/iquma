package com.iquma.controller;


import org.apache.shiro.ShiroException;
import org.springframework.beans.PropertyAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BBSExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        String exceptionName = null;
        if (e instanceof ShiroException) exceptionName = "账户认证异常";
        if (e instanceof PropertyAccessException) exceptionName = "参数异常";
        model.addAttribute("exceptionName", exceptionName == null ? e.getClass().getSimpleName() : exceptionName);
        return "status/error";
    }


}

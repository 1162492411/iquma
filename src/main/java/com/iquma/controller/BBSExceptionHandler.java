package com.iquma.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class BBSExceptionHandler {

    //1.当捕获到IllegalArgumentException异常时，跳转到输入参数错误页面，
    //异常来源：BBSController的toTutorials、toDiscusses、toArticles、toCodes
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model){
        model.addAttribute("exceptionName",e.getClass().getSimpleName());
        return "status/actionResult";
    }


    @ExceptionHandler(UnauthenticatedException.class)
    public String handleUnauthenticatedException(UnauthenticatedException e, Model model){
        model.addAttribute("exceptionName",e.getClass().getSimpleName());
        model.addAttribute("exceptionResult","该账户权限不足");
        return "status/actionResult";
    }


    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(Model model){
        model.addAttribute("exceptionResult","登录失败！");
        return "status/actionResult";
    }



}

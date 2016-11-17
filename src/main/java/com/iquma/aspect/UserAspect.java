package com.iquma.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
@Aspect
public class UserAspect {

    @Pointcut("execution(* com.iquma.service.UserService*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        System.out.println("-在方法执行前执行了 "+ Arrays.toString(joinPoint.getArgs()));
    }


    @After(value = "pointCut()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        System.out.println("---在方法执行后执行了 "+ Arrays.toString(joinPoint.getArgs()));
    }
}

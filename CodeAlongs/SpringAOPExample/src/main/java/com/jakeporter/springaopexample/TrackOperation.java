package com.jakeporter.springaopexample;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author jake
 */

@Aspect // Aspect is a class that implements concerns
public class TrackOperation {

    // execution( expression ) − Expression covering methods on which advice is to be applied.
    @Pointcut("execution(* Operation.*.*(..))")
    public void k(){}
    
    @Before("k()") // this applies the pointcut before k() (the advice) is executed
    public void myAdvice(JoinPoint jp){
        System.out.println("additional concern");
    }
}

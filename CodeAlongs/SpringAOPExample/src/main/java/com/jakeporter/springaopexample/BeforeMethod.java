package com.jakeporter.springaopexample;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 *
 * @author jake
 */

// MethodBeforeAdvice is Spring Framework interface
public class BeforeMethod implements MethodBeforeAdvice{

    // I think this is advice?
    @Override
    public void before(Method method, Object[] os, Object o) throws Throwable {
        System.out.println("BeforeMethod : Before method executed!");
    }

}

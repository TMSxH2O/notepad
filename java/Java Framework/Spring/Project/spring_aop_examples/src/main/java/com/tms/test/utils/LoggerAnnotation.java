package com.tms.test.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect // 表示当前类是一个切面
/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/05/01
 * Description: 用于记录日志的工具类，提供公共的代码
 * Version: V1.0
 */
public class LoggerAnnotation {

    @Pointcut("execution(* com.tms.test.service.impl.*.*(..))")
    private void pt(){}

    /*
        前置通知
        用于打印日志，计划在其切入点方法执行之前进行执行
        （切入点方法就是业务层方法）
     */
    @Before("pt()")
    public void beforePrintLog(){
        System.out.println("前置通知: before print log...");
    }

    // 后置通知
    @AfterReturning("pt()")
    public void afterReturnPrintLog(){
        System.out.println("后置通知: after Return print log...");
    }

    // 异常通知
    @AfterThrowing("pt()")
    public void afterThrowingPrintLog(){
        System.out.println("异常通知: after Throwing Print Log...");
    }

    // 最终通知
    @After("pt()")
    public void afterPrintLog(){
        System.out.println("最终通知: after Print Log...");
    }

    // 环绕通知
//    @Around("pt()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object rtvalue = null;
        try {
            System.out.println("环绕通知:前置通知: before print log...");
            // 得到方法的参数
            Object[] args = pjp.getArgs();
            System.out.println("环绕通知:后置通知: after Return print log...");
            // 明确调用业务层方法（切入点方法）
            rtvalue = pjp.proceed(args);
        } catch (Throwable throwable) {
            System.out.println("环绕通知:异常通知: after Throwing Print Log...");
        } finally {
            System.out.println("环绕通知:最终通知: after Print Log...");
        }
        System.out.println("环绕通知: around Print Log...");
        return rtvalue;
    }
}

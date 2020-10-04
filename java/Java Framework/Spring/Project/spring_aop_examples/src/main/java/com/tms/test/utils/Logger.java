package com.tms.test.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/05/01
 * Description: 用于记录日志的工具类，提供公共的代码
 * Version: V1.0
 */
public class Logger {

    /*
        前置通知
        用于打印日志，计划在其切入点方法执行之前进行执行
        （切入点方法就是业务层方法）
     */
    public void beforePrintLog(){
        System.out.println("前置通知: before print log...");
    }

    // 后置通知
    public void afterReturnPrintLog(){
        System.out.println("后置通知: after Return print log...");
    }

    // 异常通知
    public void afterThrowingPrintLog(){
        System.out.println("异常通知: after Throwing Print Log...");
    }

    // 最终通知
    public void afterPrintLog(){
        System.out.println("最终通知: after Print Log...");
    }

    // 环绕通知
    /* 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了
     * 分析：
     *      通过对比动态代理中的环绕通知代码之后可知：此处缺少调用切入点方法
     * 解决：
     *      spring中为我们提供了一个接口，ProceedingJoinPoint，该接口有个方法proceed()，此方法就用于调用切入点方法
     *      该接口可以作为环绕通知方法参数，在程序调用时，spring框架会为我们提供该实现类供我们使用
     *
     * spring中的环绕通知
     *      它是spring框架为我们提供的一种可以在代码中手动实现控制增强方法何时执行的方式
     **/
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object rtvalue = null;
        try {
            System.out.println("环绕通知:前置通知: before print log...");
            Object[] args = pjp.getArgs();  // 得到方法的参数
            System.out.println("环绕通知:后置通知: after Return print log...");
            rtvalue = pjp.proceed(args);  // 明确调用业务层方法（切入点方法）
        } catch (Throwable throwable) {
            System.out.println("环绕通知:异常通知: after Throwing Print Log...");
        } finally {
            System.out.println("环绕通知:最终通知: after Print Log...");
        }
        System.out.println("环绕通知: around Print Log...");
        return rtvalue;
    }
}

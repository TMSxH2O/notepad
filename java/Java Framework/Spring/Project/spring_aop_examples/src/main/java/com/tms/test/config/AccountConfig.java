package com.tms.test.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：
 * Version：V1.0
 */
@Configurable
@ComponentScan(basePackages = "com.tms.test")
@EnableAspectJAutoProxy
public class AccountConfig {
}

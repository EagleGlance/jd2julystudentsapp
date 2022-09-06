package com.noirix;

import com.noirix.configuration.ConnectionPoolConfig;
import com.noirix.configuration.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(
        scanBasePackages = "com.noirix",
        exclude = {
                DataSourceAutoConfiguration.class
        })
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({ConnectionPoolConfig.class, DatabaseProperties.class})
public class SpringBootStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
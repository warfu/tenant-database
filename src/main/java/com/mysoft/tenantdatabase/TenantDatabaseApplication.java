package com.mysoft.tenantdatabase;

import com.mysoft.tenantdatabase.entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//读取resources目录下的
@ImportResource(locations = {"classpath:spring.xml"})
public class TenantDatabaseApplication
{

    public static void main(String[] args)
    {
        ApplicationContext applicationContext= SpringApplication.run(TenantDatabaseApplication.class, args);

    }
}

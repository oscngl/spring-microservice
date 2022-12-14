package com.oscngl.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
@EnableFeignClients(
        basePackages = "com.oscngl.clients"
)
@EnableEurekaClient
@SpringBootApplication(
        scanBasePackages = {
                "com.oscngl.customer",
                "com.oscngl.amqp",
        }
)
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}

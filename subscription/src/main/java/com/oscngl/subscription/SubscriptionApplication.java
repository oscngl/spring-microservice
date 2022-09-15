package com.oscngl.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
@EnableEurekaClient
@SpringBootApplication
public class SubscriptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionApplication.class, args);
    }

}

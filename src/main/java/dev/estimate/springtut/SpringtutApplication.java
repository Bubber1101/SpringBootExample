package dev.estimate.springtut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "dev.*")
public class SpringtutApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringtutApplication.class, args);
    }

}

package ru.ewm.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru.ewm.main", "ru.ewm.stats.client"})
public class EwmMainApp {

    public static void main(String[] args) {
        SpringApplication.run(EwmMainApp.class, args);
    }

}

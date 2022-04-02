package by.bsuir.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}


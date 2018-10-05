package br.com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PetBrApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PetBrApplication.class, args);
    }
}

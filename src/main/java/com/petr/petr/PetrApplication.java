package com.petr.petr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class PetrApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get("./img2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(PetrApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PetrApplication.class);
    }
}

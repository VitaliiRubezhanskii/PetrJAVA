package com.petr.petr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class PetrApplication {

    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get("./img2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(PetrApplication.class, args);

    }
}

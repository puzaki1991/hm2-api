package com.hm2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.TimeZone;

@SpringBootApplication
public class Application {

    @Value("${timezone}")
    private String timezone;
   // public static Path downloadedContentDir;
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }
    public static void main(String[] args) throws IOException {
    //	downloadedContentDir = Files.createTempDirectory("line-bot");
        SpringApplication.run(Application.class, args);
    }
}

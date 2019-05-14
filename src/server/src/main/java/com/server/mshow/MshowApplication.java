package com.server.mshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MshowApplication {

    public static void main(String[] args) {
        SpringApplication.run(MshowApplication.class, args);
    }

}

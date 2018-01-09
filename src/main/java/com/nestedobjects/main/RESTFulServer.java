package com.nestedobjects.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.nestedobjects.sutproxy.SutProxy;

@SpringBootApplication
@ComponentScan(basePackageClasses = SutProxy.class)

public class RESTFulServer {

    public static void main(String[] args) {
        SpringApplication.run(RESTFulServer.class, args);
    }
}
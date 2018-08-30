package com.github.thiagolocatelli.controller;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/issues")
public class DemoController {

    @Value("${environment-name:LOCAL}")
    protected String appPrefix;

    @Get("/{number}")
    String issue(Integer number) {
        return appPrefix + ": issue # " + number + "!";
    }
}

package com.Alfa.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class ErrorController {
    @GetMapping("/error")
    public String getError() {
        return "error";
    }
}

@Controller
@SpringBootApplication
class BaseController {
    @GetMapping("/")
    public String getError() {
        return "error";
    }
}
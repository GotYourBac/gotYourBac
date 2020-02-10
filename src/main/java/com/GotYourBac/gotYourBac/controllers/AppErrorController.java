package com.GotYourBac.gotYourBac.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



//https://www.baeldung.com/spring-boot-custom-error-page

    @Controller
    public class AppErrorController implements ErrorController {

        @RequestMapping("/error")
        public String handleError() {
            return "error";
        }

        @Override
        public String getErrorPath() {
            return "/error";
        }

    }
//Why did you not use the Home Controller for this? Also why try to customize a pre-fabricated function?


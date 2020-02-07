package com.GotYourBac.gotYourBac.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
//
//https://www.baeldung.com/spring-boot-custom-error-page

    @Controller
    public class AppErrorController implements ErrorController {

        @RequestMapping("/error")
        public String handleError(HttpServletRequest request, Model model) {
//
            return "error";
        }

        @Override
        public String getErrorPath() {
            return "/error";
        }

    }


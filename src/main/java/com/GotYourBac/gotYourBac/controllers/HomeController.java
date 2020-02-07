package com.GotYourBac.gotYourBac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(Principal p, Model m){
        if(p != null){
            m.addAttribute("username", p.getName());
        } else {
            m.addAttribute("username", "User");
        }
        return "home";
    }

    @GetMapping("/about")
    public String getAboutPage(Principal p, Model m){
        if(p != null){
            m.addAttribute("username", p.getName());
        } else {
            m.addAttribute("username", "User");
        }
        return "aboutus";
    }
    @GetMapping("/hhchat")
    public  String getChat(Principal p, Model m){
        if(p != null){
            m.addAttribute("username", p.getName());
        } else {
            m.addAttribute("username", "User");
        }
        return "happyHourChat";
    }
}

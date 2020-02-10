package com.GotYourBac.gotYourBac.controllers;

import com.GotYourBac.gotYourBac.models.ApplicationUser;
import com.GotYourBac.gotYourBac.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
//looks good
@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

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

    @GetMapping("/forScience")
    public String getForSciencePage(Principal p, Model m){
        if(p != null){
            m.addAttribute("username", p.getName());
        } else {
            m.addAttribute("username", "User");
        }
        return "forScience";
    }

    @GetMapping("/happyhour")
    public  String getChat(Principal p, Model m){
        if(p != null){
            ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("username", p.getName());
            m.addAttribute("firstName", currentUser.getFirstName());
        } else {
            m.addAttribute("username", "User");
            m.addAttribute("firstName", "User");
        }
        return "happyHourChat";
    }
}

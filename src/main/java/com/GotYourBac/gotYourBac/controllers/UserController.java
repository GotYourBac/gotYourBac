package com.GotYourBac.gotYourBac.controllers;

import com.GotYourBac.gotYourBac.models.ApplicationUser;
import com.GotYourBac.gotYourBac.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    //autowired passwordEncoder to use BCrypt for unique password.
    //TODO: Lets look at having a minimum password requirement.
    @Autowired
    private PasswordEncoder passwordEncoder;

    //go to registration page.
    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    //Create a new user in DB, ensure we don't user Postgresql pre-defined variables. i.e 'user'
    //added if statement to combat double username which brakes the system
    @PostMapping("/registration")
    public RedirectView createNewUser(String username, String password, String firstName, String lastName, String gender, double height, float weight) {
        if(applicationUserRepository.findByUsername(username) != null){
            return new RedirectView("/registration");
        }
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, gender, height, weight, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fclipartart.com%2Fcategories%2Fdefault-profile-picture-clipart.html&psig=AOvVaw3gGdfGo9_wPSjyhzCtcWKY&ust=1580936665717000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJCKpZTmuOcCFQAAAAAdAAAAABAD" );
        applicationUserRepository.save(newUser);
        //TODO: change the redirect route to what we decide. For now its to the homepage
        return new RedirectView("/");
    }

    @GetMapping("/profile")
    public String showProfile(Principal p, Model m) {
        if(p != null) {
            ApplicationUser loggedInUser = applicationUserRepository.findByUsername((p.getName()));
            m.addAttribute("loggedInUser", loggedInUser);
        }
        return "profile";
    }

    @GetMapping("/profile/update")
    public String getUpdatePage(Principal p, Model m) {
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("loggedInUser", loggedInUser);
        return "profileUpdate";
    }

    @PutMapping("/profile/update")
    public RedirectView updateUser(Principal p, String username, String firstName, String lastName, String gender, String height, String weight) {
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        loggedInUser.setUsername(username);
        loggedInUser.setFirstName(firstName);
        loggedInUser.setLastName(lastName);
        loggedInUser.setGender(gender);
        loggedInUser.setHeight(Double.parseDouble(height));
        loggedInUser.setWeight(Float.parseFloat(weight));
        applicationUserRepository.save(loggedInUser);
        return new RedirectView("/profile");
    }

    @DeleteMapping("/profile/delete")
    public RedirectView deleteUser(Principal p){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        applicationUserRepository.delete(currentUser);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String loginAsAUser(){
        return "login";
    }

};

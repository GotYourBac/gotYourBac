package com.GotYourBac.gotYourBac.controllers;

import com.GotYourBac.gotYourBac.models.ApplicationUser;
import com.GotYourBac.gotYourBac.models.ApplicationUserRepository;
import com.GotYourBac.gotYourBac.models.Drink;
import com.GotYourBac.gotYourBac.models.DrinkRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.List;


@Controller
public class DrinkController {

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @GetMapping("/drinks")
    public String goHome(Principal p, Model m) {

        ApplicationUser drunkUser = applicationUserRepository.findByUsername(p.getName());
        List<Drink> listOfDrinks = drunkUser.drinkList;

        drunkUser.getBACChart(drunkUser.calculateBAC());
        m.addAttribute("listOfDrinks", listOfDrinks);
        m.addAttribute("principal", p.getName());
        m.addAttribute("BAC",drunkUser.calculateBAC());
        m.addAttribute("chartBAC", drunkUser.getBACChart(drunkUser.calculateBAC()));

        return "drinks";
    }

    @PostMapping("/addDrinks")
    public RedirectView addADrink(Principal p, String drinkName, float drinkSize) throws IOException {
//        nice exception handler incase url is empty

        Gson gson = new Gson();

        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());

        URL url = new URL("https://www.thecocktaildb.com/api/json/v2/9973533/search.php?i=" + drinkName);

        HttpURLConnection apiConnection = (HttpURLConnection) url.openConnection();
        apiConnection.setRequestMethod("GET");
        BufferedReader input = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
        String drinkJSON = input.readLine();
        JsonObject incomingObject = gson.fromJson(drinkJSON, JsonObject.class);
        JsonArray incomingArr = incomingObject.get("ingredients").getAsJsonArray();

        Drink newDrink = gson.fromJson(incomingArr.get(0), Drink.class);
        newDrink.setAppUser(loggedInUser);
        newDrink.drinkSize = drinkSize;
        drinkRepository.save(newDrink);
        return new RedirectView("/drinks");
    };

    @DeleteMapping("/drinks/delete")
    public RedirectView deleteOneDrink(long id){
        drinkRepository.deleteById(id);
        return new RedirectView("/drinks");
    }

    @PutMapping("/drinks/update")
    public RedirectView updateDrink(long id, float strABV, float drinkSize) {
        Drink currentDrink = drinkRepository.findById(id).get();
        currentDrink.setStrABV(strABV);
        currentDrink.setDrinkSize(drinkSize);
        drinkRepository.save(currentDrink);
        return new RedirectView("/drinks");
    }

}

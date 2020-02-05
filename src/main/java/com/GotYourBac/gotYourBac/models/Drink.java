package com.GotYourBac.gotYourBac.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drink {

    //TODO: probably need some getters and setters depending on the app, but lets safely add each one we need throughout the process.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ApplicationUser appUser;

    public String strIngredient;
    public float strABV;
    public float drinkSize;

    public Drink() {
    }

    public Drink(String strIngredient, float strABV, float drinkSize) {
        this.strIngredient=strIngredient;
        this.strABV = strABV;
        this.drinkSize = drinkSize;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public float getStrABV() {
        return strABV;
    }


    public float getDrinkSize() {
        return drinkSize;
    }


    public void setAppUser(ApplicationUser appUser) {
        this.appUser = appUser;
    }
}

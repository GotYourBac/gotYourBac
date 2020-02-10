package com.GotYourBac.gotYourBac.models;

import javax.persistence.*;

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
    } //method is never being used

    public long getId() {
        return id;
    }

    public void setStrABV(float strABV) {
        this.strABV = strABV;
    }

    public void setDrinkSize(float drinkSize) {
        this.drinkSize = drinkSize;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.appUser = appUser;
    }
}

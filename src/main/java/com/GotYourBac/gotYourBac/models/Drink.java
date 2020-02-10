package com.GotYourBac.gotYourBac.models;

import javax.persistence.*;

@Entity
public class Drink {

    //TODO: probably need some getters and setters depending on the app, but lets safely add each one we need throughout the process.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    ApplicationUser appUser;

    private String strIngredient;
    private float strABV;
    private float drinkSize;

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

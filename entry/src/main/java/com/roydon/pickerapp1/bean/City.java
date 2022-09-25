package com.roydon.pickerapp1.bean;

import java.util.ArrayList;

public class City {
    private String name;
    private ArrayList<String> districts;

    public City() {
    }

    public City(String name, ArrayList<String> districts) {
        this.name = name;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<String> districts) {
        this.districts = districts;
    }
}


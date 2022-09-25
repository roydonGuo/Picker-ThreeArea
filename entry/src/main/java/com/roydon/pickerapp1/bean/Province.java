package com.roydon.pickerapp1.bean;

import java.util.ArrayList;

public class Province {

    private String name;
    private ArrayList<City> citys;

    public Province() {
    }

    public Province(String name, ArrayList<City> citys) {
        this.name = name;
        this.citys = citys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<City> getCitys() {
        return citys;
    }

    public void setCitys(ArrayList<City> citys) {
        this.citys = citys;
    }
}

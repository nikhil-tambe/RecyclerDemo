package com.nikhil.recyclerview;

/**
 * Created by Mojo Jojo on 26-08-2018.
 */
public class ListVO {

    String name;
    String city;

    public ListVO(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

package com.example.diksha.foodtracker.pojos;

/**
 * Created by jaydeep on 30/9/18.
 */

public class AllMenuList {

    private int image;
    private String name;

    public AllMenuList(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

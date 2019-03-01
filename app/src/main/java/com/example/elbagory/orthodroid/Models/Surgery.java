package com.example.elbagory.orthodroid.Models;

import java.io.Serializable;
import java.util.List;

public class Surgery implements Serializable {


    private static String title;
    private static String address;
    private static String time;
    private static List<String> images;


    public List<String> getImages() {
        return images;
    }

    public Surgery(String title, String address, String time) {

        this.title = title;
        this.address = address;
        this.time = time;
        this.images = images;

    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }


    public String getTime() {
        return time;
    }

}

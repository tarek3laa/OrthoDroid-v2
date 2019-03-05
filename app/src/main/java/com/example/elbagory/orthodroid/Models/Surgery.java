package com.example.elbagory.orthodroid.Models;

import java.io.Serializable;
import java.util.List;

public class Surgery implements Serializable {


    private static String title;
    private static String address;
    private static String time;
    private static List<String> images;

    public Surgery() {
    }

    public List<String> getImages() {
        return images;
    }

    public Surgery(String title, String address, String time) {

        Surgery.title = title;
        Surgery.address = address;
        Surgery.time = time;
        images = images;

    }

    public void setImages(List<String> images) {
        Surgery.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Surgery.title = title;
    }

    public void setAddress(String address) {
        Surgery.address = address;
    }

    public void setTime(String time) {
        Surgery.time = time;
    }

    public String getAddress() {
        return address;
    }


    public String getTime() {
        return time;
    }

}

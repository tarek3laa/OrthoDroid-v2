package com.example.elbagory.orthodroid.Models;

import java.util.List;

public class Model_History {

    private static String PChronic, PGastritis, PSmoking, PPregnancy, PLactation;
    private static int Private_id;
    private static List<String> imagesSmoking, imagesChronic, imagesGastritis, imagesPregnancy, imagesLactation;

    public Model_History() {
    }

    public void setPChronic(String PChronic) {
        this.PChronic = PChronic;
    }

    public void setPGastritis(String PGastritis) {
        this.PGastritis = PGastritis;
    }

    public void setPSmoking(String PSmoking) {
        this.PSmoking = PSmoking;
    }

    public void setPPregnancy(String PPregnancy) {
        this.PPregnancy = PPregnancy;
    }

    public void setPLactation(String PLactation) {
        this.PLactation = PLactation;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }

    public List<String> getImagesSmoking() {
        return imagesSmoking;
    }

    public void setImagesSmoking(List<String> imagesSmoking) {
        this.imagesSmoking = imagesSmoking;
    }

    public List<String> getImagesChronic() {
        return imagesChronic;
    }

    public void setImagesChronic(List<String> imagesChronic) {
        this.imagesChronic = imagesChronic;
    }

    public List<String> getImagesGastritis() {
        return imagesGastritis;
    }

    public void setImagesGastritis(List<String> imagesGastritis) {
        this.imagesGastritis = imagesGastritis;
    }

    public List<String> getImagesPregnancy() {
        return imagesPregnancy;
    }

    public void setImagesPregnancy(List<String> imagesPregnancy) {
        this.imagesPregnancy = imagesPregnancy;
    }

    public List<String> getImagesLactation() {
        return imagesLactation;
    }

    public void setImagesLactation(List<String> imagesLactation) {
        this.imagesLactation = imagesLactation;
    }

    public Model_History(String PChronic, String etGastritis, String etSmoking, String etPregnancy, String etLactation, int Privateid) {
        this.PChronic = PChronic;
        this.PGastritis = etGastritis;
        this.PSmoking = etSmoking;
        this.PPregnancy = etPregnancy;
        this.PLactation = etLactation;
        this.Private_id = Privateid;

    }


    public String getPChronic() {
        return PChronic;
    }

    public String getPGastritis() {
        return PGastritis;
    }

    public String getPSmoking() {
        return PSmoking;
    }

    public String getPPregnancy() {
        return PPregnancy;
    }

    public String getPLactation() {
        return PLactation;
    }

    public int getPrivate_id() {
        return Private_id;
    }
}

package com.example.elbagory.orthodroid.Models;

import java.util.List;

public class Model_History {

    private static String PChronic, PGastritis, PSmoking, PPregnancy, PLactation;
    private static int Private_id;
    private static List<String> imagesSmoking, imagesChronic, imagesGastritis, imagesPregnancy, imagesLactation;

    public Model_History() {
    }

    public Model_History(String PChronic, String etGastritis, String etSmoking, String etPregnancy, String etLactation, int Privateid) {
        Model_History.PChronic = PChronic;
        PGastritis = etGastritis;
        PSmoking = etSmoking;
        PPregnancy = etPregnancy;
        PLactation = etLactation;
        Private_id = Privateid;

    }

    public void setPChronic(String PChronic) {
        Model_History.PChronic = PChronic;
    }

    public void setPGastritis(String PGastritis) {
        Model_History.PGastritis = PGastritis;
    }

    public void setPSmoking(String PSmoking) {
        Model_History.PSmoking = PSmoking;
    }

    public void setPPregnancy(String PPregnancy) {
        Model_History.PPregnancy = PPregnancy;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }

    public List<String> getImagesSmoking() {
        return imagesSmoking;
    }

    public void setPLactation(String PLactation) {
        Model_History.PLactation = PLactation;
    }

    public List<String> getImagesChronic() {
        return imagesChronic;
    }

    public void setImagesSmoking(List<String> imagesSmoking) {
        Model_History.imagesSmoking = imagesSmoking;
    }

    public List<String> getImagesGastritis() {
        return imagesGastritis;
    }

    public void setImagesChronic(List<String> imagesChronic) {
        Model_History.imagesChronic = imagesChronic;
    }

    public List<String> getImagesPregnancy() {
        return imagesPregnancy;
    }

    public void setImagesGastritis(List<String> imagesGastritis) {
        Model_History.imagesGastritis = imagesGastritis;
    }

    public List<String> getImagesLactation() {
        return imagesLactation;
    }

    public void setImagesPregnancy(List<String> imagesPregnancy) {
        Model_History.imagesPregnancy = imagesPregnancy;
    }

    public void setImagesLactation(List<String> imagesLactation) {
        Model_History.imagesLactation = imagesLactation;
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

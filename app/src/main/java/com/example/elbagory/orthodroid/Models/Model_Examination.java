package com.example.elbagory.orthodroid.Models;


import java.util.List;

public class Model_Examination {

    private static String etTrauma, etKnee, etShoulder, etSpine, etPelvis, etFoot, etElbow;
    private int Private_id;

    private static List<String> traumaImages;
    private static List<String> kneeImages;

    public Model_Examination() {
    }

    private static List<String> shoulderImages;
    private static List<String> spineImages;

    private static List<String> pelvisImages;
    private static List<String> footImages;

    private static List<String> elbowImages;

    public Model_Examination(String etTrauma, String etKnee, String etShoulder, String etSpine, String etPelvis, String etFoot, String etElbow, int private_id) {
        this.etTrauma = etTrauma;
        this.etKnee = etKnee;
        this.etShoulder = etShoulder;
        this.etSpine = etSpine;
        this.etPelvis = etPelvis;
        this.etFoot = etFoot;
        this.etElbow = etElbow;
        this.Private_id = private_id;

    }

    public void setEtTrauma(String etTrauma) {
        this.etTrauma = etTrauma;
    }

    public void setEtKnee(String etKnee) {
        this.etKnee = etKnee;
    }

    public void setEtShoulder(String etShoulder) {
        this.etShoulder = etShoulder;
    }

    public void setEtSpine(String etSpine) {
        this.etSpine = etSpine;
    }

    public void setEtPelvis(String etPelvis) {
        this.etPelvis = etPelvis;
    }

    public void setEtFoot(String etFoot) {
        this.etFoot = etFoot;
    }

    public void setEtElbow(String etElbow) {
        this.etElbow = etElbow;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }

    public void setTraumaImages(List<String> traumaImages) {
        this.traumaImages = traumaImages;
    }

    public List<String> getKneeImages() {
        return kneeImages;
    }

    public void setKneeImages(List<String> kneeImages) {
        this.kneeImages = kneeImages;
    }

    public List<String> getShoulderImages() {
        return shoulderImages;
    }

    public void setShoulderImages(List<String> shoulderImages) {
        this.shoulderImages = shoulderImages;
    }

    public List<String> getSpineImages() {
        return spineImages;
    }

    public void setSpineImages(List<String> spineImages) {
        this.spineImages = spineImages;
    }

    public List<String> getPelvisImages() {
        return pelvisImages;
    }

    public void setPelvisImages(List<String> pelvisImages) {
        this.pelvisImages = pelvisImages;
    }

    public List<String> getFootImages() {
        return footImages;
    }

    public void setFootImages(List<String> footImages) {
        this.footImages = footImages;
    }

    public List<String> getElbowImages() {
        return elbowImages;
    }

    public void setElbowImages(List<String> elbowImages) {
        this.elbowImages = elbowImages;
    }

    public List<String> getTraumaImages() {
        return traumaImages;
    }


    public String getEtTrauma() {
        return etTrauma;
    }

    public String getEtKnee() {
        return etKnee;
    }

    public String getEtShoulder() {
        return etShoulder;
    }

    public String getEtSpine() {
        return etSpine;
    }

    public String getEtPelvis() {
        return etPelvis;
    }

    public String getEtFoot() {
        return etFoot;
    }

    public String getEtElbow() {
        return etElbow;
    }

    public int getPrivate_id() {
        return Private_id;
    }
}

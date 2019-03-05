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
        Model_Examination.etTrauma = etTrauma;
        Model_Examination.etKnee = etKnee;
        Model_Examination.etShoulder = etShoulder;
        Model_Examination.etSpine = etSpine;
        Model_Examination.etPelvis = etPelvis;
        Model_Examination.etFoot = etFoot;
        Model_Examination.etElbow = etElbow;
        this.Private_id = private_id;

    }

    public void setEtTrauma(String etTrauma) {
        Model_Examination.etTrauma = etTrauma;
    }

    public void setEtKnee(String etKnee) {
        Model_Examination.etKnee = etKnee;
    }

    public void setEtShoulder(String etShoulder) {
        Model_Examination.etShoulder = etShoulder;
    }

    public void setEtSpine(String etSpine) {
        Model_Examination.etSpine = etSpine;
    }

    public void setEtPelvis(String etPelvis) {
        Model_Examination.etPelvis = etPelvis;
    }

    public void setEtFoot(String etFoot) {
        Model_Examination.etFoot = etFoot;
    }

    public void setEtElbow(String etElbow) {
        Model_Examination.etElbow = etElbow;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }

    public void setTraumaImages(List<String> traumaImages) {
        Model_Examination.traumaImages = traumaImages;
    }

    public List<String> getKneeImages() {
        return kneeImages;
    }

    public void setKneeImages(List<String> kneeImages) {
        Model_Examination.kneeImages = kneeImages;
    }

    public List<String> getShoulderImages() {
        return shoulderImages;
    }

    public void setShoulderImages(List<String> shoulderImages) {
        Model_Examination.shoulderImages = shoulderImages;
    }

    public List<String> getSpineImages() {
        return spineImages;
    }

    public void setSpineImages(List<String> spineImages) {
        Model_Examination.spineImages = spineImages;
    }

    public List<String> getPelvisImages() {
        return pelvisImages;
    }

    public void setPelvisImages(List<String> pelvisImages) {
        Model_Examination.pelvisImages = pelvisImages;
    }

    public List<String> getFootImages() {
        return footImages;
    }

    public void setFootImages(List<String> footImages) {
        Model_Examination.footImages = footImages;
    }

    public List<String> getElbowImages() {
        return elbowImages;
    }

    public void setElbowImages(List<String> elbowImages) {
        Model_Examination.elbowImages = elbowImages;
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

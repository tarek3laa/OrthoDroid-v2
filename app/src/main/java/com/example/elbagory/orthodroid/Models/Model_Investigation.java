package com.example.elbagory.orthodroid.Models;

import java.util.List;

public class Model_Investigation {

    private static String etChemistry, etCS, etCytology, etXray, etScanogram, etCT, etMRI, etDEXA, etBoneScan;
    private static int Private_id;
    private static List<String> imagesChemistry,imagesCS,imagesCytology,imagesXray,imagesScanogram,imagesCT,imagesMRI,imagesDEXA,imagesBone;

    public Model_Investigation() {
    }

    public Model_Investigation(String etChemistry, String etCS, String etCytology, String etXray, String etScanogram, String etCT, String etMRI, String etDEXA, String etBoneScan, int private_id) {
        Model_Investigation.etChemistry = etChemistry;
        Model_Investigation.etCS = etCS;
        Model_Investigation.etCytology = etCytology;
        Model_Investigation.etXray = etXray;
        Model_Investigation.etScanogram = etScanogram;
        Model_Investigation.etCT = etCT;
        Model_Investigation.etMRI = etMRI;
        Model_Investigation.etDEXA = etDEXA;
        Model_Investigation.etBoneScan = etBoneScan;
        Private_id = private_id;
    }

    public void setEtChemistry(String etChemistry) {
        Model_Investigation.etChemistry = etChemistry;
    }

    public void setEtCS(String etCS) {
        Model_Investigation.etCS = etCS;
    }

    public void setEtCytology(String etCytology) {
        Model_Investigation.etCytology = etCytology;
    }

    public void setEtXray(String etXray) {
        Model_Investigation.etXray = etXray;
    }

    public void setEtScanogram(String etScanogram) {
        Model_Investigation.etScanogram = etScanogram;
    }

    public void setEtCT(String etCT) {
        Model_Investigation.etCT = etCT;
    }

    public void setEtMRI(String etMRI) {
        Model_Investigation.etMRI = etMRI;
    }

    public void setEtDEXA(String etDEXA) {
        Model_Investigation.etDEXA = etDEXA;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }

    public List<String> getImagesChemistry() {
        return imagesChemistry;
    }

    public void setEtBoneScan(String etBoneScan) {
        Model_Investigation.etBoneScan = etBoneScan;
    }

    public List<String> getImagesCS() {
        return imagesCS;
    }

    public void setImagesChemistry(List<String> imagesChemistry) {
        Model_Investigation.imagesChemistry = imagesChemistry;
    }

    public List<String> getImagesCytology() {
        return imagesCytology;
    }

    public void setImagesCS(List<String> imagesCS) {
        Model_Investigation.imagesCS = imagesCS;
    }

    public List<String> getImagesXray() {
        return imagesXray;
    }

    public void setImagesCytology(List<String> imagesCytology) {
        Model_Investigation.imagesCytology = imagesCytology;
    }

    public List<String> getImagesScanogram() {
        return imagesScanogram;
    }

    public void setImagesXray(List<String> imagesXray) {
        Model_Investigation.imagesXray = imagesXray;
    }

    public List<String> getImagesCT() {
        return imagesCT;
    }

    public void setImagesScanogram(List<String> imagesScanogram) {
        Model_Investigation.imagesScanogram = imagesScanogram;
    }

    public List<String> getImagesMRI() {
        return imagesMRI;
    }

    public void setImagesCT(List<String> imagesCT) {
        Model_Investigation.imagesCT = imagesCT;
    }

    public List<String> getImagesDEXA() {
        return imagesDEXA;
    }

    public void setImagesMRI(List<String> imagesMRI) {
        Model_Investigation.imagesMRI = imagesMRI;
    }

    public List<String> getImagesBone() {
        return imagesBone;
    }

    public void setImagesDEXA(List<String> imagesDEXA) {
        Model_Investigation.imagesDEXA = imagesDEXA;
    }

    public void setImagesBone(List<String> imagesBone) {
        Model_Investigation.imagesBone = imagesBone;
    }

    public String getEtChemistry() {
        return etChemistry;
    }

    public String getEtCS() {
        return etCS;
    }

    public String getEtCytology() {
        return etCytology;
    }

    public String getEtXray() {
        return etXray;
    }

    public String getEtScanogram() {
        return etScanogram;
    }

    public String getEtCT() {
        return etCT;
    }

    public String getEtMRI() {
        return etMRI;
    }

    public String getEtDEXA() {
        return etDEXA;
    }

    public String getEtBoneScan() {
        return etBoneScan;
    }

    public int getPrivate_id() {
        return Private_id;
    }
}

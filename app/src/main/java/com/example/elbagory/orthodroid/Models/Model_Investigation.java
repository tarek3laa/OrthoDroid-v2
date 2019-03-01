package com.example.elbagory.orthodroid.Models;

import java.util.List;

public class Model_Investigation {

    private static String etChemistry, etCS, etCytology, etXray, etScanogram, etCT, etMRI, etDEXA, etBoneScan;
    private static int Private_id;
    private static List<String> imagesChemistry,imagesCS,imagesCytology,imagesXray,imagesScanogram,imagesCT,imagesMRI,imagesDEXA,imagesBone;

    public Model_Investigation() {
    }

    public void setEtChemistry(String etChemistry) {
        this.etChemistry = etChemistry;
    }

    public void setEtCS(String etCS) {
        this.etCS = etCS;
    }

    public void setEtCytology(String etCytology) {
        this.etCytology = etCytology;
    }

    public void setEtXray(String etXray) {
        this.etXray = etXray;
    }

    public void setEtScanogram(String etScanogram) {
        this.etScanogram = etScanogram;
    }

    public void setEtCT(String etCT) {
        this.etCT = etCT;
    }

    public void setEtMRI(String etMRI) {
        this.etMRI = etMRI;
    }

    public void setEtDEXA(String etDEXA) {
        this.etDEXA = etDEXA;
    }

    public void setEtBoneScan(String etBoneScan) {
        this.etBoneScan = etBoneScan;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }

    public List<String> getImagesChemistry() {
        return imagesChemistry;
    }

    public void setImagesChemistry(List<String> imagesChemistry) {
        this.imagesChemistry = imagesChemistry;
    }

    public List<String> getImagesCS() {
        return imagesCS;
    }

    public void setImagesCS(List<String> imagesCS) {
        this.imagesCS = imagesCS;
    }

    public List<String> getImagesCytology() {
        return imagesCytology;
    }

    public void setImagesCytology(List<String> imagesCytology) {
        this.imagesCytology = imagesCytology;
    }

    public List<String> getImagesXray() {
        return imagesXray;
    }

    public void setImagesXray(List<String> imagesXray) {
        this.imagesXray = imagesXray;
    }

    public List<String> getImagesScanogram() {
        return imagesScanogram;
    }

    public void setImagesScanogram(List<String> imagesScanogram) {
        this.imagesScanogram = imagesScanogram;
    }

    public List<String> getImagesCT() {
        return imagesCT;
    }

    public void setImagesCT(List<String> imagesCT) {
        this.imagesCT = imagesCT;
    }

    public List<String> getImagesMRI() {
        return imagesMRI;
    }

    public void setImagesMRI(List<String> imagesMRI) {
        this.imagesMRI = imagesMRI;
    }

    public List<String> getImagesDEXA() {
        return imagesDEXA;
    }

    public void setImagesDEXA(List<String> imagesDEXA) {
        this.imagesDEXA = imagesDEXA;
    }

    public List<String> getImagesBone() {
        return imagesBone;
    }

    public void setImagesBone(List<String> imagesBone) {
        this.imagesBone = imagesBone;
    }

    public Model_Investigation(String etChemistry, String etCS, String etCytology, String etXray, String etScanogram, String etCT, String etMRI, String etDEXA, String etBoneScan, int private_id) {
        this.etChemistry = etChemistry;
        this.etCS = etCS;
        this.etCytology = etCytology;
        this.etXray = etXray;
        this.etScanogram = etScanogram;
        this.etCT = etCT;
        this.etMRI = etMRI;
        this.etDEXA = etDEXA;
        this.etBoneScan = etBoneScan;
        Private_id = private_id;
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

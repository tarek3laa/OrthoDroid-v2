package com.example.elbagory.orthodroid.Models;

import androidx.annotation.NonNull;

public class Model_History {


    private String PChronic, PChronicOther, PGastritis, PGastritisOther, PSmoking, PSmokingOther, PPregnancy, PPregnancyOther, PLactation, PLactationOther;
    private boolean chronic, gastritis, smoking, pregnancy, lactation;

    public Model_History() {
    }

    public Model_History(String PChronic, String PChronicOther, String PGastritis, String PGastritisOther, String PSmoking, String PSmokingOther, String PPregnancy, String PPregnancyOther, String PLactation, String PLactationOther) {
        this.PChronic = PChronic;
        this.PChronicOther = PChronicOther;
        this.PGastritis = PGastritis;
        this.PGastritisOther = PGastritisOther;
        this.PSmoking = PSmoking;
        this.PSmokingOther = PSmokingOther;
        this.PPregnancy = PPregnancy;
        this.PPregnancyOther = PPregnancyOther;
        this.PLactation = PLactation;
        this.PLactationOther = PLactationOther;
    }

    public String getPChronic() {
        return PChronic;
    }

    public void setPChronic(String PChronic) {
        this.PChronic = PChronic;
    }

    public String getPChronicOther() {
        return PChronicOther;
    }

    public void setPChronicOther(String PChronicOther) {
        this.PChronicOther = PChronicOther;
    }

    public String getPGastritis() {
        return PGastritis;
    }

    public void setPGastritis(String PGastritis) {
        this.PGastritis = PGastritis;
    }

    public String getPGastritisOther() {
        return PGastritisOther;
    }

    public void setPGastritisOther(String PGastritisOther) {
        this.PGastritisOther = PGastritisOther;
    }

    public String getPSmoking() {
        return PSmoking;
    }

    public void setPSmoking(String PSmoking) {
        this.PSmoking = PSmoking;
    }

    public String getPSmokingOther() {
        return PSmokingOther;
    }

    public void setPSmokingOther(String PSmokingOther) {
        this.PSmokingOther = PSmokingOther;
    }

    public String getPPregnancy() {
        return PPregnancy;
    }

    public void setPPregnancy(String PPregnancy) {
        this.PPregnancy = PPregnancy;
    }

    public String getPPregnancyOther() {
        return PPregnancyOther;
    }

    public void setPPregnancyOther(String PPregnancyOther) {
        this.PPregnancyOther = PPregnancyOther;
    }

    public String getPLactation() {
        return PLactation;
    }

    public void setPLactation(String PLactation) {
        this.PLactation = PLactation;
    }

    public String getPLactationOther() {
        return PLactationOther;
    }

    public void setPLactationOther(String PLactationOther) {
        this.PLactationOther = PLactationOther;
    }

    public boolean isChronic() {
        return chronic;
    }

    public void setChronic(boolean chronic) {
        this.chronic = chronic;
    }

    public boolean isGastritis() {
        return gastritis;
    }

    public void setGastritis(boolean gastritis) {
        this.gastritis = gastritis;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(boolean pregnancy) {
        this.pregnancy = pregnancy;
    }

    public boolean isLactation() {
        return lactation;
    }

    public void setLactation(boolean lactation) {
        this.lactation = lactation;
    }

    @NonNull
    @Override
    public String toString() {
        return PChronic + " " + PChronicOther + " " + PGastritis + " " + PGastritisOther + " " + PSmoking + " " + PSmokingOther + " " + PPregnancy + " " + PPregnancyOther + " " + PLactation + " " + PLactationOther;

    }
}

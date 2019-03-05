package com.example.elbagory.orthodroid.Models;


public class Model_Operation {

    private static String etOperationName, etDate, etSteps, etPersonName, etFollow;
    private static int Private_id;

    public Model_Operation() {
    }

    public Model_Operation(String etOperationName, String etDate, String etSteps, String etPersonName, String etFollow, int private_id) {
        Model_Operation.etOperationName = etOperationName;
        Model_Operation.etDate = etDate;
        Model_Operation.etSteps = etSteps;
        Model_Operation.etPersonName = etPersonName;
        Model_Operation.etFollow = etFollow;
        Private_id = private_id;
    }

    public String getEtOperationName() {
        return etOperationName;
    }

    public String getEtDate() {
        return etDate;
    }

    public String getEtSteps() {
        return etSteps;
    }

    public String getEtPersonName() {
        return etPersonName;
    }

    public String getEtFollow() {
        return etFollow;
    }

    public int getPrivate_id() {
        return Private_id;
    }

    public void setEtOperationName(String etOperationName) {
        Model_Operation.etOperationName = etOperationName;
    }

    public void setEtDate(String etDate) {
        Model_Operation.etDate = etDate;
    }

    public void setEtSteps(String etSteps) {
        Model_Operation.etSteps = etSteps;
    }

    public void setEtPersonName(String etPersonName) {
        Model_Operation.etPersonName = etPersonName;
    }

    public void setEtFollow(String etFollow) {
        Model_Operation.etFollow = etFollow;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }
}

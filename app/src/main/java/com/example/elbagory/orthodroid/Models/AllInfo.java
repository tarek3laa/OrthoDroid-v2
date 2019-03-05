package com.example.elbagory.orthodroid.Models;

public class AllInfo {

    private Model_Examination examination;
    private Model_History history;
    private Model_Investigation investigation;
    private Model_Operation operation;
    private Model_Patient patient;
    private Surgery surgery;

    public AllInfo() {
    }

    public Model_Examination getExamination() {
        return examination;
    }

    public void setExamination(Model_Examination examination) {
        this.examination = examination;
    }

    public Model_History getHistory() {
        return history;
    }

    public void setHistory(Model_History history) {
        this.history = history;
    }

    public Model_Investigation getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Model_Investigation investigation) {
        this.investigation = investigation;
    }

    public Model_Operation getOperation() {
        return operation;
    }

    public void setOperation(Model_Operation operation) {
        this.operation = operation;
    }

    public Model_Patient getPatient() {
        return patient;
    }

    public void setPatient(Model_Patient patient) {
        this.patient = patient;
    }

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }
}

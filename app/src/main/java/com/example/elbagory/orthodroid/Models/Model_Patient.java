package com.example.elbagory.orthodroid.Models;

public class Model_Patient {

    private static String Name, Id, Age, Occupation, Weight, Info, lastUpdate;
    private static int Private_id;

    // for fire base
    public Model_Patient() {
    }

    public Model_Patient(String name, String id, String age, String occupation, String weight, String info, int Privateid, String last_Update) {
        this.Name = name;
        this.Id = id;
        this.Age = age;
        this.Occupation = occupation;
        this.Weight = weight;
        this.Info = info;
        this.Private_id = Privateid;
        this.lastUpdate = last_Update;

    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setOccupation(String occupation) {
        this.Occupation = occupation;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setPrivate_id(int private_id) {
        Private_id = private_id;
    }

    public String getName() {
        return Name;
    }

    public String getId() {
        return Id;
    }

    public String getAge() {
        return Age;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getOccupation() {
        return Occupation;
    }

    public String getWeight() {
        return Weight;
    }

    public String getInfo() {
        return Info;
    }

    public int getPrivate_id() {
        return Private_id;
    }
}

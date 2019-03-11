package com.example.elbagory.orthodroid.Models;



import com.example.elbagory.orthodroid.utils.Pair;
import java.util.List;

public class Model_Patient {

    private static String Name, Id, Age, Occupation, Weight, Info, lastUpdate;
    private static int Private_id;
    private static List<Pair<String,Pair<String,String>>> pairs;


    // for fire base
    public Model_Patient() {
    }

    public Model_Patient(String name, String id, String age, String occupation, String weight, String info, int Privateid, String last_Update) {
        Name = name;
        Id = id;
        Age = age;
        Occupation = occupation;
        Weight = weight;
        Info = info;
        Private_id = Privateid;
        lastUpdate = last_Update;

    }

    public List<Pair<String, Pair<String, String>>> getPairs() {
        return this.pairs;
    }

    public void setPairs(List<Pair<String, Pair<String, String>>> pairs) {
        this.pairs = pairs;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public void setLastUpdate(String lastUpdate) {
        Model_Patient.lastUpdate = lastUpdate;
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

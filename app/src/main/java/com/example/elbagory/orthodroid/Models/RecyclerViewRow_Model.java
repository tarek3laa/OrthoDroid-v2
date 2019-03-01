package com.example.elbagory.orthodroid.Models;

public class RecyclerViewRow_Model {

    String name , id , time;
    int private_id;

    public RecyclerViewRow_Model(String name, String id, String time,int Private_id) {
        this.name = name;
        this.id = id;
        this.time = time;
        this.private_id = Private_id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrivate_id(int private_id) {
        this.private_id = private_id;
    }

    public RecyclerViewRow_Model(){}

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public int getPrivate_id() {
        return private_id;
    }
}

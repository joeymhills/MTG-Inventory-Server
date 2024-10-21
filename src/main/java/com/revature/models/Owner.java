package com.revature.models;

public class Owner {
    private int id;
    private String name;

    public Owner() {
    }

    public Owner(int owner_id, String owner_name) {
        this.id = owner_id;
        this.name = owner_name;
    }

    public String getName() {
       return this.name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public int getId() {
       return this.id;
    }

    public void setId(int id) {
       this.id = id;
    }
}

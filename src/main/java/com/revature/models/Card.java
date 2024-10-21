package com.revature.models;

public class Card {

    private int id;
    private String name;
    private int mana;
    private int power;
    private int toughness;
    private int owner_fk;

    public Card() {
    }

    public Card(int id, String name, int mana, int power, int toughness, int owner_fk) {
       this.id = id;
       this.name = name;
       this.mana = mana;
       this.power = power;
       this.toughness = toughness;
       this.owner_fk = owner_fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getOwner_fk() {
        return owner_fk;
    }

    public void setOwner_fk(int owner_fk) {
        this.owner_fk = owner_fk;
    }
}

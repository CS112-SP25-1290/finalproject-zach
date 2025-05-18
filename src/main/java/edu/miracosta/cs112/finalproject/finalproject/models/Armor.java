package edu.miracosta.cs112.finalproject.finalproject.models;

public class Armor {
    String name; 
    int defenceValue;
    
    public Armor(String name, int defenceValue) {
        this.name = name;
        this.defenceValue = defenceValue;
    }
    public Armor(){
        this.name = "Armor";
        this.defenceValue = 0;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDefenceValue() {
        return defenceValue;
    }
    public void setDefenceValue(int defenceValue) {
        this.defenceValue = defenceValue;
    }
    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                ", defenceValue=" + defenceValue +
                '}';
    }
}

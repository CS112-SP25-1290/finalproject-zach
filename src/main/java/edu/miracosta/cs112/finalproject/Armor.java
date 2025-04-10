package edu.miracosta.cs112.finalproject;

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
    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                ", defenceValue=" + defenceValue +
                '}';
    }
}

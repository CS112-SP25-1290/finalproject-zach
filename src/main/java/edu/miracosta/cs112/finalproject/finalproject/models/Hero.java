package edu.miracosta.cs112.finalproject.finalproject.models;


public class Hero {
    private String name;
    private int health;
    Armor armor;
    Weapon weapon;
    boolean wasLevelOneCompleted;
    boolean wasLevelTwoCompleted;

    public Hero(String name, int health, Armor armor, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.weapon = weapon;
    }
    public Hero(){
        this.name = "Hero";
        this.health = 100;
        this.armor = new Armor("Naked", 0);
        this.weapon = null;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public Armor getArmor() {
        return armor;
    }
    public void setLevelOneAsCompleted(){
        this.wasLevelOneCompleted = true;
    }
    public boolean getWasLevelOneCompleted(){
        return wasLevelOneCompleted;
    }
    public void setLevelTwoAsCompleted(){
        this.wasLevelTwoCompleted = true;
    }
    public boolean getWasLevelTwoCompleted(){
        return wasLevelTwoCompleted;
    }
    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", armor=" + armor +
                ", weapon=" + weapon +
                '}' + '\n';
    }
    
}

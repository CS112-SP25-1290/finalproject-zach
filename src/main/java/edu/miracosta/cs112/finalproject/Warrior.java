package edu.miracosta.cs112.finalproject;

public class Warrior extends Hero {
    private int breserkCharges;
    private int numAttacks = 1;

    public void setBreserkCharges(int breserkCharges) {
        this.breserkCharges = breserkCharges;
    }
    public int getBreserkCharges() {
        return breserkCharges;
    }
    public Warrior(String name, int health, Armor armor, Weapon weapon, int breserkCharges) {
        super(name, health, armor, weapon);
        this.breserkCharges = breserkCharges;
    }
    public Warrior() {
        super("Warrior", 100, new Armor(), new Sword()); // Default values for a warrior
        this.breserkCharges = 3;
    }
    public void useBreserk(){
        numAttacks = 2;
        breserkCharges--;
        System.out.println("Breserk activated! You have " + breserkCharges + " charges left.");
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", armor=" + getArmor() +
                ", weapon=" + getWeapon() +
                ", breserkCharges=" + breserkCharges +
                '}' + '\n';
    }

}

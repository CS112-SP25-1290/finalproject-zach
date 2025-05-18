package edu.miracosta.cs112.finalproject.finalproject.models;

public class Warrior extends Hero {
    private int berserkCharges;
    private int numAttacks;

    public void setBerserkCharges(int berserkCharges) {
        this.berserkCharges = berserkCharges;
    }
    public int getBerserkCharges() {
        return berserkCharges;
    }
    public void setNumAttacks(int numAttacks){ 
        this.numAttacks = numAttacks;
    }
    public int getNumAttacks(){
        return numAttacks;
    }
    public Warrior(String name, int health, Armor armor, Weapon weapon, int breserkCharges, int numAttacks) {
        super(name, health, armor, weapon);
        this.berserkCharges = breserkCharges;
        this.numAttacks = numAttacks;
    }
    public Warrior() {
        super("Warrior", 100, new Armor(), new Sword()); // Default values for a warrior
        this.berserkCharges = 3;
    }
public void useBerserk() {
    if (berserkCharges > 0) {
        this.numAttacks = 2;
        berserkCharges--;
        
    } else {
      System.out.println("Berserk Fails");  
    }
}

    @Override
    public Sword getWeapon(){
        return (Sword) super.getWeapon();
    }
    @Override
    public String toString() {
        return "Warrior{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", armor=" + getArmor() +
                ", weapon=" + getWeapon() +
                ", breserkCharges=" + berserkCharges +
                '}' + '\n';
    }

}

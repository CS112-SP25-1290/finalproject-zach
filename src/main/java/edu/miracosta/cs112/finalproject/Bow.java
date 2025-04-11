package edu.miracosta.cs112.finalproject;

public class Bow extends Weapon {

    private String arrowType;
    private int defenseBonus;
    
    public Bow(String name, int attackValue, String arrowType, int defenseBonus) {
        super(name, attackValue);
        this.arrowType = arrowType;
        this.defenseBonus = defenseBonus;
    }
    public Bow(){
        super("Bow", 10); // Default values for a bow
        this.arrowType = "Standard";
        this.defenseBonus = 5;
    }
    public void attack() {
        // TODO Auto-generated method stub
        
    }
    public void specialAttack() {
        // TODO Auto-generated method stub
        
    }

}

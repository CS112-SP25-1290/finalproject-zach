package edu.miracosta.cs112.finalproject.finalproject.models;

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
    public int attack(Enemy enemy) {
        
        System.out.println("Bow Attack");

        if(enemy.health <= 0){
            System.out.println("Enemy is already defeated!");
            return 0; // No damage if the enemy is already defeated
        }

        int damage = attackValue - enemy.getDefenceValue();
        
        if (damage < 0) {
            damage = 0; // No negative damage
        }
        
        return damage;

    }

    public int getDefenceBonus(){
        return this.defenseBonus;
    }

}

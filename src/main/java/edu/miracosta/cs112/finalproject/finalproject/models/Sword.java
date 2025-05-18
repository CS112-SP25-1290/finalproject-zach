package edu.miracosta.cs112.finalproject.finalproject.models;

public class Sword extends Weapon {
    int attackBonus;
    int armorPenetration;

public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }
public int getAttackBonus() {
        return attackBonus;
    }
public void setArmorPenetration(int armorPenetration) {
        this.armorPenetration = armorPenetration;
    }
public int getArmorPenetration() {
        return armorPenetration;
    }
public Sword(String name, int attackValue, int attackBonus, int armorPenetration) {
        super(name, attackValue);
        this.attackBonus = attackBonus;
        this.armorPenetration = armorPenetration;
    }
public Sword() {
        super("Sword", 10); // Default values for a sword
        this.attackBonus = 5;
        this.armorPenetration = 2;
    }
public int attack(Enemy enemy) {
        
        System.out.println("Sword Attack!");

        if(enemy.health <= 0){
            System.out.println("Enemy is already defeated!");
            return 0; // No damage if the enemy is already defeated
        }

        int damage = (attackValue + attackBonus) - (enemy.getDefenceValue() - armorPenetration);
        
        if (damage < 0) {
            damage = 0; // No negative damage
        }
        
        return damage;
        
        
    }
public void specialAttack() {
        System.out.println("Sword Special Attack!");
        // Implement sword special attack logic here
    }
@Override
public String toString() {
        return "Sword{" +
                "name='" + name + '\'' +
                ", attackValue=" + attackValue +
                ", attackBonus=" + attackBonus +
                ", armorPenetration=" + armorPenetration +
                '}';
    }
}
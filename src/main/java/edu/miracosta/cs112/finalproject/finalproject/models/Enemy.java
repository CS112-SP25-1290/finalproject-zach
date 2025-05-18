package edu.miracosta.cs112.finalproject.finalproject.models;

public class Enemy {

    String name;
    int health;
    int attackValue;
    int defenceValue;

    public Enemy(){
        name = null;
        health = 0;
        attackValue = 0;
        defenceValue = 0;
    }

    public Enemy(String name, int health, int attackValue, int defenceValue) {
        this.name = name;
        this.health = health;
        this.attackValue = attackValue;
        this.defenceValue = defenceValue;
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

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenceValue() {
        return defenceValue;
    }

    public void setDefenceValue(int defenceValue) {
        this.defenceValue = defenceValue;
    }

    public int attack(Hero hero, Enemy enemy) {
        
        if(hero instanceof Warrior){

        int damage = (attackValue - (hero.getArmor().getDefenceValue()));
    
        if(damage > 0){ return damage; 
            }else{

                System.out.println("The Enemy did no damage!");

                return 0;

            }

        }else if(hero instanceof Ranger){

            Bow playerWeapon = (Bow) hero.getWeapon();

            
            int damage = (attackValue - (hero.getArmor().getDefenceValue() + (playerWeapon.getDefenceBonus())));

            if(damage > 0){ return damage; 
            }else{

                System.out.println("The Enemy did no damage!");

                return 0;

            }
           

        }

        System.out.println("No Damage Done by Enemy!");
        return 0;
        
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attackValue=" + attackValue +
                ", defenceValue=" + defenceValue +
                '}' + '\n';
    
    }

}
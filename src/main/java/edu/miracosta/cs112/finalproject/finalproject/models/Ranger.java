package edu.miracosta.cs112.finalproject.finalproject.models;

public class Ranger extends Hero {

    private int numRavens = 0;
    private Raven[] ravens = new Raven[3];

    private class Raven {
        private String name;
        private int attackValue;

        private Raven(String name, int attackValue) {
            this.name = name;
            this.attackValue = attackValue;
        }
        private String getName() {
            return name;
        }
        private int getAttackValue() {
            return attackValue;
        }

    }

    public Ranger(String name, int health, Armor armor, Weapon weapon) {
        super(name, health, armor, weapon);
        this.numRavens = 0;
        this.ravens = new Raven[3];
    }

    public int getNumRavens(){
        return this.numRavens;
    }

    public int ravenAttack(){
        
        switch(numRavens){
            case 1:
                System.out.println(ravens[0].getAttackValue());
                return ravens[0].getAttackValue();  
            case 2:
                System.out.println(ravens[0].getAttackValue() + ravens[1].getAttackValue());
                return (ravens[0].getAttackValue() + ravens[1].getAttackValue());
            case 3:
                System.out.println(ravens[0].getAttackValue() + ravens[1].getAttackValue() + ravens[2].getAttackValue());
                return (ravens[0].getAttackValue() + ravens[1].getAttackValue() + ravens[2].getAttackValue());
        }
        return 0;
    }

    public void revealNextRaven(){
        
        switch(numRavens){
            case 0:
                ravens[0] = new Raven("Edgar", 10);
                this.numRavens++;
                System.out.println("You have revealed your first raven: " + ravens[0].getName() + " with attack value: " + ravens[0].getAttackValue());
                break;
            case 1:
                ravens[1] = new Raven("Allen", 15);
                System.out.println("You have revealed your second raven: " + ravens[1].getName() + " with attack value: " + ravens[1].getAttackValue());
                this.numRavens++;
                break;
            case 2:
                ravens[2] = new Raven("Poe", 20);
                System.out.println("You have revealed your third raven: " + ravens[2].getName() + " with attack value: " + ravens[2].getAttackValue());
                numRavens++;
                break;
        }
    }
    
    @Override
    public String toString() {
        return "Ranger{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", armor=" + getArmor() +
                ", weapon=" + getWeapon() +
                ", numRavens=" + numRavens +
                '}' + '\n';
    
    }
}

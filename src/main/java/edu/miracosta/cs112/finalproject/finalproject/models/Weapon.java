package edu.miracosta.cs112.finalproject.finalproject.models;

abstract class Weapon {
    String name;
    int attackValue;

    public Weapon(String name, int attackValue) {
        this.name = name;
        this.attackValue = attackValue;
    }
    public Weapon(){
        this.name = "Weapon";
        this.attackValue = 0;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAttackValue() {
        return attackValue;
    }
    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }
    public Weapon getWeapon(){
        return this;
    }

    abstract int attack(Enemy enemy);


    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", attackValue=" + attackValue +
                '}';
    }   
}

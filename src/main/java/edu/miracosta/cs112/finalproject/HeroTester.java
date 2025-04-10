package edu.miracosta.cs112.finalproject;

public class HeroTester {
    public static void main(String[] args) {
        // Create a hero with default values
        Hero hero = new Hero();
        Hero hero2 = new Hero("Warrior", 150, new Armor("Steel Armor", 20), new Sword("Excalibur", 15, 10, 5));
        // Print the hero's details
        System.out.println(hero.toString() + "\n" + hero2.toString());
    }
}
package edu.miracosta.cs112.finalproject;

public class HeroTester {
    public static void main(String[] args) {
        // Create a hero with default values
        Hero hero = new Hero();
        Hero hero2 = new Hero("Warrior", 150, new Armor("Steel Armor", 20), new Sword("Excalibur", 15, 10, 5));

        Warrior warrior = new Warrior("Conan", 200, new Armor("Iron Armor", 30), new Sword("Longsword", 20, 15, 10), 3);
        Ranger ranger = new Ranger("Robin",125, new Armor("Leather Armor", 10), new Bow());


        // Print the hero's details
        System.out.println(hero.toString() + "\n" + hero2.toString() + "\n" + warrior.toString());

        ranger.revealNextRaven();

        System.out.println(ranger.toString() + "\n" + ranger.ravenAttack());

        ranger.revealNextRaven();

        System.out.println(ranger.toString() + "\n" + ranger.ravenAttack());

        ranger.revealNextRaven();

        System.out.println(ranger.toString() + "\n" + ranger.ravenAttack());

    }
}
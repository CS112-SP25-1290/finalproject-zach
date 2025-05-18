package edu.miracosta.cs112.finalproject.finalproject.models;
import java.util.ArrayList;
import edu.miracosta.cs112.finalproject.finalproject.FantasyFighterMain;
import edu.miracosta.cs112.finalproject.finalproject.controllers.LevelSelectController;
import edu.miracosta.cs112.finalproject.finalproject.controllers.LevelViewController;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameEngine {

    private Hero player;
    private boolean ravensAttacked;
    private boolean usedThisTurn;
    private String selectedClass;
    private LevelViewController controller;
    private boolean isGameOver = false;
    private boolean isLevelComplete = false;
    private boolean playerTurn = true;
    private int currentLevel;
    private AnimationTimer timer;
    private String victoryMessage;
    private Alert alert = new Alert(AlertType.CONFIRMATION);
    
    ArrayList<Enemy> enemies = new ArrayList<>();

    public GameEngine(LevelViewController controller, int currentLevel) {

        this.controller = controller;
        this.currentLevel = currentLevel;

        
        this.controller.mainAttackButton.setOnAction((e -> {

            if(!isGameOver && !isLevelComplete && playerTurn == true){
                playerNormalAttack();
            }
        }));

        this.controller.specialAttackButton.setOnAction((e->{

            if(!isGameOver && !isLevelComplete && playerTurn == true){
                playerSpecialMove();
            }
        }));
  
        this.player = controller.getPlayer();

        controller.updateHeroMaxHealth(player.getHealth());

        //Checks to see if the player has unlocked a new raven.
        //Case zero unlocks the first raven.
        //Next cases just handle showing the raven animation.
        if(player instanceof Ranger && ((Ranger)player).getNumRavens() == 0){
                ((Ranger)this.player).revealNextRaven();

                controller.showRaven(1);
                    
            }else if(player instanceof Ranger && ((Ranger)player).getNumRavens() == 2){
                
                controller.showRaven(1);
                controller.showRaven(2);
    
            }else if(player instanceof Ranger && ((Ranger)player).getNumRavens() == 3){

                controller.showRaven(1);
                controller.showRaven(2);
                controller.showRaven(3);
            }
        
        //Generates Enemies depending on level.
        //Updates to appropriate level background and enemy animation.
            switch(currentLevel){
            case 1:
                Enemy skeleton = new Enemy("Skeleton", 50, 10, 0);
                Enemy skeleton2 = new Enemy("Skeleton", 50, 10, 0);

                this.enemies.add(skeleton);
                this.enemies.add(skeleton2);

                controller.updateEnemyMaxHealth(enemies.get(0).getHealth());
                controller.setLevelBackground("/images/Tunnel 2.png"); // Set the level background image
                controller.updateEnemyImageView("/images/Skeleton/Idle.png", 1);
                
                System.out.println(enemies); // Print the list of enemies
                break;
            case 2:
                Enemy flyingEye = new Enemy("Flying Eye", 75, 25, 5);
                Enemy flyingEye2 = new Enemy("Flying Eye", 75, 25, 5 );
                Enemy flyingEye3 = new Enemy("Flying Eye", 75, 25, 5 );

                this.enemies.add(flyingEye);
                this.enemies.add(flyingEye2);
                this.enemies.add(flyingEye3);

                controller.updateEnemyMaxHealth(enemies.get(0).getHealth());
                controller.setLevelBackground("/images/Shrine 5.png");
                controller.enemyImageView.setTranslateY(-50);
                controller.updateEnemyImageView("/images/FlyingEye/Flight.png", 2);
                break;
            case 3:
                Enemy bossDragon = new Enemy("Boss Dragon", 750, 35, 10);
                enemies.add(bossDragon);

                controller.updateEnemyMaxHealth(enemies.get(0).getHealth());
                controller.setLevelBackground("/images/Castle Ruins 1.png");
                controller.updateEnemyImageView("/images/Dragon/BlueDragonIdle.png", 3);
                break;
            }
                //INNER CLASS: Calls methods to update UI components.
                //Forces the level completed if it notices the enemy ArrayList is empty.  
                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        if(player instanceof Ranger){
                            checkRangerRavenAttack();
                        }else if(player instanceof Warrior){
                            checkWarriorBerserkCharges();
                        }

                        checkGameState();
                        controller.update();

                        if (!enemies.isEmpty()) {
                            controller.updateHeroHealth(player.getHealth()); 
                            controller.updateEnemyHealth(enemies.get(0).getHealth()); 
                        } else {
                            isLevelComplete = true;
                        }
                    }
                };
                this.timer = timer; // Assign the timer to the class-level variable
                timer.start(); // Start the timer
        }
    
    public void playerNormalAttack(){
        if(this.player instanceof Warrior){

            //Does the warrior's turn. If the warrior uses BERSERK, do double damage(hit twice)
            System.out.println("Warrior's turn!");
            
            Warrior heroWarrior = (Warrior) this.player;
            Sword warriorWeapon = (Sword) this.player.getWeapon();

            if(heroWarrior.getNumAttacks() == 1){

                int damage = warriorWeapon.attack(enemies.get(0));
                enemies.get(0).setHealth(enemies.get(0).getHealth() - (damage));

                System.out.println(damage);
                enemyTurn();

            }else if(heroWarrior.getNumAttacks() == 2){
                int damage = warriorWeapon.attack(enemies.get(0));
                enemies.get(0).setHealth(enemies.get(0).getHealth() - (damage * 2));

                System.out.println(damage * 2);

                heroWarrior.setNumAttacks(1);

                enemyTurn();
            }
        }
        if(this.player instanceof Ranger){

            //Ranger's normal attack
            System.out.println("Ranger's turn!");

            Ranger heroRanger = (Ranger) this.player;
            Bow rangerWeapon = (Bow) heroRanger.getWeapon();

            int damage = rangerWeapon.attack(enemies.get(0));

            System.out.println(damage);

            enemies.get(0).setHealth(enemies.get(0).getHealth() - damage);

            enemyTurn();
        }
    }
    public void playerSpecialMove(){

        if (this.player instanceof Warrior){

            //Activates berserk and locks button until the next turn or until next level if berserk charges fall to 0.
            Warrior player = (Warrior) this.player;

            player.useBerserk();
            usedThisTurn = true;

        }else if(this.player instanceof Ranger){    
            
            //Ravens can attack once every turn. Damage increases as ravens are unlocked.
            Ranger player = (Ranger) this.player;

            int damage = player.ravenAttack();

            enemies.get(0).setHealth(enemies.get(0).getHealth() - (damage - enemies.get(0).getDefenceValue()));

            ravensAttacked = true;
        }  
    }

    public void enemyTurn(){
     
        System.out.println("Enemy's turn!");
        //Enemy does damage. Handles the booleans that control the Special Move button's disable property.

        if(enemies.get(0).getHealth() > 0 && !enemies.isEmpty()){

                player.setHealth(player.getHealth() - enemies.get(0).attack(player, enemies.get(0)));

                System.out.println("Player's health: " + player.getHealth());

                if(player instanceof Ranger){
                    ravensAttacked = false;
                }
                if(player instanceof Warrior && ((Warrior)player).getBerserkCharges() > 0){
                    usedThisTurn = false;
                }            
                this.playerTurn = true;

                }else{
                    System.out.println("Enemy is defeated!");
                    enemies.remove(enemies.get(0)); 
                if(player instanceof Ranger){
                    ravensAttacked = false;
                }
                if(player instanceof Warrior && ((Warrior)player).getBerserkCharges() > 0){
                    usedThisTurn = false;
                }      
                this.playerTurn = true;
            }
        }                   
    public Hero getPlayer(){
        return player;
    }

    public void setPlayer(Hero player){
        this.player = player;
    }

    public void setSelectedClass(String selectedClass){
        this.selectedClass = selectedClass;
    }

    public String getSelectedClass(){
        return selectedClass;
    }

    public boolean isPlayerTurn(){
        return playerTurn;
    }

    public void switchTurn(){
        this.playerTurn = !this.playerTurn;
    }

    public boolean isGameOver(){
        return isGameOver;
    }

    public boolean isLevelComplete(){
        return isLevelComplete;
    }

    public LevelViewController getController(){
        return controller;
    }

    //Handles creating the alert message that handles death and level wins.
    public void doAlertMessage(){

        LevelSelectController levelSelectController = new LevelSelectController();

        levelSelectController.unlockLevelTwo();

        if(player.getWasLevelTwoCompleted() == true){

            levelSelectController.unlockLevelThree();       

        }
        
        alert.setTitle("Level Complete!");
        alert.setContentText(victoryMessage);
           
        alert.setOnHidden(e-> {
            FXMLLoader fxmlLoader = new FXMLLoader(FantasyFighterMain.class.getResource("level-select.fxml"));
            
            this.player.setHealth(100);
            levelSelectController.loadPlayer(this.player);

            fxmlLoader.setController(levelSelectController);

        try {
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) controller.mainAttackButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Level Selection");
                stage.show();
        } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
            alert.show();   
    }

    public void checkWarriorBerserkCharges(){
        if (((Warrior)player).getBerserkCharges() > 0 && usedThisTurn == false){
            controller.specialAttackButton.setDisable(false);
        }else if(((Warrior)player).getBerserkCharges() <= 0 || usedThisTurn == true){
            controller.specialAttackButton.setDisable(true);
        }
    }
    public void checkRangerRavenAttack(){
        if(this.player instanceof Ranger && ravensAttacked == true){
            controller.specialAttackButton.setDisable(true);
        }else if(ravensAttacked == false){
            controller.specialAttackButton.setDisable(false);
        }
    }

    public void checkGameState(){
    if(!enemies.isEmpty()){
        if(enemies.get(0).getHealth() <= 0){
            enemies.remove(enemies.get(0)); // Remove the defeated enemy from the list
            }
        }else{
            System.out.println("All Enemies Defeated!");
            isLevelComplete = true;
        }
    if(player.getHealth() <= 0){

        player.setHealth(0);
 
        isGameOver = true;
    }
    if(isLevelComplete == true && isGameOver == false){
        timer.stop();

        //Depending on level completed and class, tailor the rewards and message.
        //Upgrades number of ravens and number of berserk charges.
        //Unlocks the next levels.
        
        switch(this.currentLevel){
            case 1:
            if(player.getWasLevelOneCompleted() == false){
                if (player instanceof Warrior){
                    victoryMessage = "You have defeated all enemies! \n" +
                            "You have unlocked the next level! \n" +
                            "You have discovered Iron Armor and the Sword of Skulls! \n";

                    Warrior player = ((Warrior)this.player);

                    Sword swordOfSkulls = new Sword("Sword of Skulls", 20, 10, 5);
                    player.setWeapon(swordOfSkulls);

                    Armor ironArmor = new Armor("Iron Armor", 7);
                    player.setArmor(ironArmor);

                    player.setBerserkCharges(2);
                    player.setLevelOneAsCompleted();

                    doAlertMessage();
                }else if(player instanceof Ranger){
                        victoryMessage = "You have defeated all enemies! \n" +
                                "You have unlocked the next level! \n" +
                                "You have discovered Leather Armor and the Bow of Burning \n";
                        
                        Bow bowOfBurning = new Bow("Bow of Burning",15, "Fire", 10);
                        player.setWeapon(bowOfBurning);

                        Armor leatherArmor = new Armor("Leather Armor", 5);
                        player.setArmor(leatherArmor);

                        ((Ranger) player).revealNextRaven();
                     
                        player.setLevelOneAsCompleted();

                        doAlertMessage();
                }
            }else{
                victoryMessage = "Thanks for helping to clear some more Skeletons!";

                doAlertMessage();
            } 
                break;
            case 2:
            if(this.player.getWasLevelTwoCompleted() == false){
                 if (player instanceof Warrior){
                    victoryMessage = "You have defeated all enemies! \n" +
                            "You have unlocked the next level! \n" +
                            "You have discovered Titanium Armor and the Sword of Dragon-Slaying! \n" +
                            "Venture forth to defeat the dragon plaguing the Realm!";

                    Sword swordOfDragonSlaying = new Sword("Sword of Dragon-Slaying", 70, 15, 8);
                    player.setWeapon(swordOfDragonSlaying);

                    Armor titaniumArmor = new Armor("Titanium Armor", 25);
                    player.setArmor(titaniumArmor);

                    player.setLevelTwoAsCompleted();
                    ((Warrior) player).setBerserkCharges(3);

                    doAlertMessage();
                    
                    }else if(player instanceof Ranger){
                        victoryMessage = "You have defeated all enemies! \n" +
                                "You have unlocked the next level! \n" +
                                "You have discovered Half-Plate Armor and the Bow of Dragon-Slaying \n" +
                                "Venture forth to defeat the dragon plaguing the Realm!";
                        
                        Bow bowOfDragonSlaying = new Bow("Bow of Dragon-Slaying",50, "Dragon-Slaying", 8);
                        player.setWeapon(bowOfDragonSlaying);

                        Armor halfPlateArmor = new Armor("Half-Plate Armor", 20);
                        player.setArmor(halfPlateArmor);

                        player.setLevelTwoAsCompleted();
                        ((Ranger) player).revealNextRaven(); 

                        doAlertMessage();  
                }  
            }else{
                    victoryMessage = "Thanks for cleaning up some more of those pesky flying eyeballs";

                    doAlertMessage();
            }
            break;
            case 3:
                    victoryMessage = "As the Dragon falls, you swell with pride. \n" +
                                    "You've protected the Realm from the devastating Dragon \n" +
                                    "Thanks so much for playing!";
                    
                    doAlertMessage();
        }   
        }else if(isLevelComplete == false && isGameOver == true){
                timer.stop();

                alert.setTitle("Level Failed!");
                alert.setContentText("You've been killed! Restart the game to try again.");
                alert.setOnHidden(e -> {
                    System.exit(0);
                });
                alert.show();                
            }  
    }    
}








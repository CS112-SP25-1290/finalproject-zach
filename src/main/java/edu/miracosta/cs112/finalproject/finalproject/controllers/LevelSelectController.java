package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.models.*;
import edu.miracosta.cs112.finalproject.finalproject.FantasyFighterMain;
import edu.miracosta.cs112.finalproject.finalproject.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LevelSelectController {

    @FXML
    private Button levelOneButton;
    @FXML
    private Button levelTwoButton;
    @FXML
    private Button levelThreeButton;

    boolean levelTwoUnlocked = false;
    boolean levelThreeUnlocked = false;
    Hero player;
    
    public void initialize(){


        levelOneButton.setText("Tunnels");
        levelOneButton.setOnAction(e -> startLevelOne());
        
        levelTwoButton.setText("Den of Flying Eyes");
        levelTwoButton.setOnAction(e -> startLevelTwo());

        levelThreeButton.setText("The Final Battle");
        levelThreeButton.setOnAction(e -> startLevelThree());

        levelTwoButton.setDisable(!levelTwoUnlocked);
        levelThreeButton.setDisable(!levelThreeUnlocked); // Disable the button if level two is not unlocked
        
    }

    public void loadPlayer(Hero player){
        this.player = player;
    }

    public void unlockLevelTwo(){

        levelTwoUnlocked = true; 

    }

    public void unlockLevelThree(){

        levelThreeUnlocked = true;
    }

    public void startLevelOne() {
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(FantasyFighterMain.class.getResource("level-view.fxml"));

            LevelViewController levelViewController = new LevelViewController();
            levelViewController.loadPlayer(this.player);
            
            fxmlLoader.setController(levelViewController);
            
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) levelOneButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Level 1: Tunnels");

            FantasyFighterMain.levelBegin(fxmlLoader.getController(), stage, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    public void startLevelTwo(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(FantasyFighterMain.class.getResource("level-view.fxml"));

            LevelViewController levelViewController = new LevelViewController();
            levelViewController.loadPlayer(this.player);
            fxmlLoader.setController(levelViewController);

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) levelTwoButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Level 2: Den of Ugly Flying Eyes!");

            FantasyFighterMain.levelBegin(fxmlLoader.getController(), stage, 2);

        } catch (Exception e){
        e.printStackTrace();
    }   
}
    public void startLevelThree(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(FantasyFighterMain.class.getResource("level-view.fxml"));

            LevelViewController levelViewController = new LevelViewController();
            levelViewController.loadPlayer(this.player);
            fxmlLoader.setController(levelViewController);

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) levelTwoButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Level 3: The Final Boss!");

            FantasyFighterMain.levelBegin(fxmlLoader.getController(), stage, 3);

        } catch (Exception e){
        e.printStackTrace();
    }  
    
        
    }
}
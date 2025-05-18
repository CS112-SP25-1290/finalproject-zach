package edu.miracosta.cs112.finalproject.finalproject.controllers;
import edu.miracosta.cs112.finalproject.finalproject.*;
import edu.miracosta.cs112.finalproject.finalproject.models.*;


import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ClassSelectController {
    // This class is responsible for handling the class selection logic
    // and updating the UI accordingly.
    
    // Add your instance variables and methods here to handle class selection.
    private Hero player;
    private final Image warriorSpriteSheet = new Image("/images/Warrior/Idle.png");
    private final Image rangerSpriteSheet = new Image("/images/Ranger/Idle.png");

    private final int COUNT = 10;
    private final int COLUMNS = 10;

     

    @FXML
    private Button rangerButton;
    @FXML
    private Button warriorButton;
    @FXML
    private ImageView rangerClassView;
    @FXML
    private ImageView warriorClassView;

    public void initialize() {
        rangerButton.setOnAction(e -> handleClassSelection("Ranger"));
        warriorButton.setOnAction(e -> handleClassSelection("Warrior"));

        rangerClassView.setViewport(new Rectangle2D(0, 0, 100, 100));
        warriorClassView.setViewport(new Rectangle2D(0,0,162,162));

        Animation rangerAnimation = new SpriteAnimation(
        rangerClassView, Duration.millis(2000), 
        COUNT, COLUMNS, 0, 0, 
        100, 100);

        rangerClassView.setImage(rangerSpriteSheet);

        Animation warriorAnimation = new SpriteAnimation(
        warriorClassView, Duration.millis(2000), 
        COUNT, COLUMNS, 0, 0, 
        162, 162);

        warriorClassView.setImage(warriorSpriteSheet);

        rangerAnimation.setCycleCount(Animation.INDEFINITE);
        rangerAnimation.play();

        warriorAnimation.setCycleCount(Animation.INDEFINITE);
        warriorAnimation.play();
        
    }
   
    public void handleClassSelection(String selectedClass) {


            FXMLLoader fxmlLoader = new FXMLLoader(FantasyFighterMain.class.getResource("level-select.fxml"));
            LevelSelectController levelSelectController = new LevelSelectController();
            
            if(selectedClass == "Warrior"){

                this.player = new Warrior("Warrior", 100, new Armor("Leather Armor", 4), new Sword("Wood Sword", 5, 2, 0), 1, 1);
            }else if(selectedClass == "Ranger"){

                this.player = new Ranger("Ranger", 100, new Armor("Worn Clothes", 2), new Bow("Old Bow", 5, "Normal", 2));
            }

            levelSelectController.loadPlayer(this.player);

            try {
                
                fxmlLoader.setController(levelSelectController); 
                Scene scene = new Scene(fxmlLoader.load());
                // Set the controller for the FXML loader
                Stage stage = (Stage) warriorButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Level Selection");
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
            
            

    }


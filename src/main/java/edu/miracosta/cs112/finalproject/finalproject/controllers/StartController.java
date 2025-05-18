package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.FantasyFighterMain;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import java.io.IOException;

public class StartController {
    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;

    
    public void initialize(){
    
        startButton.setText("Start Game");
        exitButton.setText("Exit Game");

        startButton.setOnAction(e -> handleStartButton(e));

        exitButton.setOnAction(event -> {
            // Close the application
            System.out.println("Exit button clicked! Closing application...");
            System.exit(0);
        });

    }
    public void handleStartButton(ActionEvent e) {
        try {
            // Load the FXML file for the class selection view
            FXMLLoader fxmlLoader = new FXMLLoader(FantasyFighterMain.class.getResource("class-select.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            
            Stage stage = (Stage) startButton.getScene().getWindow();
            // Set the scene and title for the stage
            stage.setScene(scene);
            stage.setTitle("Class Selection");
            // Close Main View and open Class Selection View
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package edu.miracosta.cs112.finalproject.finalproject.controllers;
import edu.miracosta.cs112.finalproject.finalproject.models.Hero;
import java.io.FileOutputStream;
import edu.miracosta.cs112.finalproject.finalproject.models.SpriteAnimation;
import edu.miracosta.cs112.finalproject.finalproject.models.GameEngine;
import edu.miracosta.cs112.finalproject.finalproject.models.Warrior;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import java.util.List;

public class LevelViewController {

    private String selectedClass; // Get the selected class from ClassSelectController
    private double heroHealth; // Get the hero health from the GameEngine class
    private double enemyHealth;
    private double heroMaxHealth;
    private double enemyMaxHealth;
    private int width;
    private int height;
    private Hero player;

     // Get the enemy max health from the GameEngine class
    
   // Get the enemy health from the GameEngine class
         private final int COLUMNS = 10;
         private final int COUNT = 10;
         private final int OFFSET_X = 0;
         private final int OFFSET_Y = 0;


    private final Image warriorSpriteSheet = new Image("/images/Warrior/Idle.png");
    private final Image rangerSpriteSheet = new Image("/images/Ranger/Idle.png");
    private final Image ravenSpriteSheet = new Image("/images/Ranger/ravenIdle.png");
    private final Image ravenSpriteSheet2 = new Image("/images/Ranger/allenIdle.png");
    private final Image ravenSpriteSheet3 = new Image("/images/Ranger/poeIdle.png");
   
    @FXML
    public Button mainAttackButton;
    @FXML
    public Button specialAttackButton;
    @FXML
    public ProgressBar heroHealthBar;
    @FXML
    public ProgressBar enemyHealthBar;
    @FXML
    public Label heroHealthLabel;
    @FXML
    public Label enemyNameLabel;
    @FXML
    public ImageView enemyImageView;
    @FXML
    public ImageView heroImageView;
    @FXML
    public Button levelCompleteButton;
    @FXML
    public ImageView levelBackground;
    @FXML
    public ImageView edgarView;
    @FXML
    public ImageView allenView;
    @FXML
    public ImageView poeView;
    

    public LevelViewController() {
        
    }

    public void initialize() {

        heroImageView.setSmooth(true);
        heroImageView.setPreserveRatio(true);

    try{
        if(this.player.getName() == "Warrior"){
            width = 162;
            height = 162;

            heroImageView.setScaleX(2.5);
            heroImageView.setScaleY(2.5);

            heroImageView.setImage(warriorSpriteSheet);
        }else if(this.player.getName() == "Ranger"){
            width = 100;
            height = 100;

            heroImageView.setImage(rangerSpriteSheet);
        }

    } catch (NullPointerException e) {
        System.out.println(e.getMessage());
    }

        
        
        heroImageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, width, height));

        Animation animation = new SpriteAnimation(
        heroImageView, Duration.millis(2000), 
        COUNT, COLUMNS, OFFSET_X, OFFSET_Y, 
        width, height);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();


        //Image levelBackground = new Image(getClass().getResourceAsStream());

        heroHealthBar.setProgress(1.0); // Set initial health to 100%
        heroHealthBar.setStyle("-fx-accent: green;"); // Set the color of the health bar to green
        enemyHealthBar.setProgress(1.0); // Set initial health to 100%
        enemyHealthBar.setStyle("-fx-accent: red;"); // Set the color of the health bar to red
        enemyNameLabel.setText("");

        //heroImageView.setImage(new Image(getClass().getResourceAsStream("/images/"+selectedClass+".png")));

    }

    public void update(){
        
        heroHealthBar.setProgress(heroHealth / heroMaxHealth); // Update hero health bar
        enemyHealthBar.setProgress(enemyHealth / enemyMaxHealth); // Update enemy health bar
      
    }

    public void loadPlayer(Hero player){
        this.player = player;
    }

    public Hero getPlayer(){
        return this.player;
    }

    public void setLevelBackground(String imagePath){
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        levelBackground.setImage(image);
    }

    public void showRaven(int numRavens){

        switch(numRavens){
            case 1:
                Animation ravenAnimation = new SpriteAnimation(edgarView, Duration.millis(2000), 5, 5, 0, 0, 31, 25);

                edgarView.setViewport(new Rectangle2D(0,0, 31, 25));
                edgarView.setImage(ravenSpriteSheet);
            
                ravenAnimation.setCycleCount(Animation.INDEFINITE);
                ravenAnimation.play();
                break;
            case 2:
                Animation ravenAnimation2 = new SpriteAnimation(allenView, Duration.millis(2000), 5, 5, 0,0,31,25);

                allenView.setViewport(new Rectangle2D(0,0, 31, 25));
                allenView.setImage(ravenSpriteSheet2);

                ravenAnimation2.setCycleCount(Animation.INDEFINITE);
                ravenAnimation2.play();
                break;
            case 3:
                Animation ravenAnimation3 = new SpriteAnimation(poeView, Duration.millis(2000), 5, 5, 0,0,31,25);

                poeView.setViewport(new Rectangle2D(0,0,31,25));
                poeView.setImage(ravenSpriteSheet3);

                ravenAnimation3.setCycleCount(Animation.INDEFINITE);
                ravenAnimation3.play();
                break;
        }
    }

    public String getSelectedClass() {
        return selectedClass;
    }

    public void updateHeroHealth(int heroHealth) {
        this.heroHealth = heroHealth;

    }

      public void updateEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public void updateHeroMaxHealth(int heroMaxHealth) {
        this.heroMaxHealth = heroMaxHealth;
    }
    public void updateEnemyMaxHealth(int enemyMaxHealth) {
        this.enemyMaxHealth = enemyMaxHealth;
    }

    public void updateEnemyImageView(String imagePath, int currentLevel){

    Image image = new Image(getClass().getResourceAsStream(imagePath));

        switch(currentLevel){
            case 1:

                enemyNameLabel.setText("Skeleton");
                 
                Animation skeletonAnimation = new SpriteAnimation(enemyImageView, Duration.millis(2000), 4, 4, 0, 0, 150, 150);

                enemyImageView.setViewport(new Rectangle2D(0, 0, 150, 150));
                enemyImageView.setImage(image);
                skeletonAnimation.setCycleCount(Animation.INDEFINITE);
                skeletonAnimation.play();

                break;
        
            case 2:

                enemyNameLabel.setText("Flying Eye");

                Animation flyingEyeAnimation = new SpriteAnimation(enemyImageView, Duration.millis(1000), 8, 8,0,0, 150, 150);

                enemyImageView.setViewport(new Rectangle2D(0,0, 150, 150));
                enemyImageView.setImage(image);

                flyingEyeAnimation.setCycleCount(Animation.INDEFINITE);
                flyingEyeAnimation.play();

                break;
            
            case 3:

                enemyNameLabel.setText("Two-Headed Dragon");

                Animation dragonAnimation = new SpriteAnimation(enemyImageView, Duration.millis(750), 3, 3, 0, 0, 144, 129);

                enemyImageView.setViewport(new Rectangle2D(0,0, 144, 129));
                enemyImageView.setRotate(0);
                enemyImageView.setTranslateX(-80);
                enemyImageView.setTranslateY(-120);

                enemyImageView.setImage(image);
                dragonAnimation.setCycleCount(Animation.INDEFINITE);
                dragonAnimation.play();
    }

    
}

    

}

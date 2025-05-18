package edu.miracosta.cs112.finalproject.finalproject;
import edu.miracosta.cs112.finalproject.finalproject.controllers.*;
import edu.miracosta.cs112.finalproject.finalproject.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Welcome to Fantasy Fighter! Battle through three levels and defeat the final boss.
 * 
 * Choose between two classes:
 * 
 * LEFT: RANGER: Start with one pet raven and unlock two more later. Ravens can attack before
 * your turn each turn(Special move). Defence bonus is granted because you fire at range with a bow. More range = better bonus.
 * 
 * RIGHT: WARRIOR: Hack and rage your way through the enemies. Wield a sword and heavier armor to
 * defeat your enemies. Use BERSERK(Special Move) to do double damage (attack twice). You are able to
 * also slash away enemy armor using your sword. Armor penetration bonus on swords will automatically
 * subtract enemy defence.
 * 
 * CREDITS:
 * 
 * The Title itself: https://textcraft.net/
 * Backgrounds are all from the same creator. Both links: 
 * https://lornn.itch.io/fantasy-forest-backgrounds
 * https://lornn.itch.io/backgrounds-dungeons-ruins-caves
 *
 * 
 * Huntress V2(Ranger Sprite): https://luizmelo.itch.io/huntress-2 
 * Pet Raven Sprite: https://elthen.itch.io/2d-pixel-art-raven-sprites
 * Fantasy Warrior(Warrior Sprite): https://luizmelo.itch.io/fantasy-warrior
 *
 * Skeleton and Flying Eye Sprite: https://luizmelo.itch.io/monsters-creatures-fantasy?download
 * Two Headed Dragon: https://opengameart.org/content/flying-dragon-rework
 * 
 * AI was used lightly for troubleshooting, and working out specific logic.
 * 
 * SpriteAnimation class came from a blog and was meant to be shared. Here's the link:
 * https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
 * 
 * All of the code and systems were thought up and created by me :) (obviously haha.)
 * Licenses on all of the assets allow for personal/educational/commercial use.
 * 
 */

public class FantasyFighterMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fantasy Fighter!");
        stage.setScene(scene);
        stage.show();
    }
    public static void levelBegin(LevelViewController controller, Stage stage, int currentLevel) {
        GameEngine gameEngine = new GameEngine(controller, currentLevel);

        stage.show(); 
    } 
}
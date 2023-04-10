package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * LeaderboardSceneController.
 *
 * @author Jihoon Yoon, Wilbur Lin
 * @version 2023-04-09
 */
public class LeaderboardSceneController {
    // Current stage
    private Stage stage;
    // Current scene
    private Scene scene;
    // Current root
    private Parent root;

    /**
     * Switches to the leaderboard FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToLeaderboardScene(final MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("leaderboard-scene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Switches to the start FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToStartScene(final MouseEvent event) throws IOException {
        StartSceneController ssc = new StartSceneController();
        ssc.switchToStartScene(event);
    }
}

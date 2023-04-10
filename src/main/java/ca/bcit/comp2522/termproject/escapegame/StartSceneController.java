package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * StartSceneController.
 *
 * @author Jihoon Yoon, Wilber Lin
 * @version 2023-04-09
 */
public class StartSceneController {

    private Stage stage;

    private Scene scene;

    private Parent root;

    /**
     * Switches to the start FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToStartScene(final MouseEvent event) throws IOException {
        SceneData sd = new SceneData();
        DataSaver.saveKeys(sd);

        root = FXMLLoader.load(getClass().getResource("starting-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Switches to the game FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToGameScene(final MouseEvent event) throws IOException {
        GameSceneController gsc = new GameSceneController();
        gsc.switchToGameScene(event);
    }

    /**
     * Switches to the leaderboard FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToLeaderboardScene(final MouseEvent event) throws IOException {
        LeaderboardSceneController lsc = new LeaderboardSceneController();
        lsc.switchToLeaderboardScene(event);
    }
}

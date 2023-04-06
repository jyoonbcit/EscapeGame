package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LeaderboardSceneController {

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void switchToLeaderboardScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("leaderboard-scene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

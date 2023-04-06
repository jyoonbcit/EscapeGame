package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneController {
    @FXML
    private Button paperBtn;

    @FXML
    private Text dialogue;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void switchToGameScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("game-scene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchToDrawerScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("drawer-scene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Button setup
        paperBtn = (Button) loader.getNamespace().get("paperBtn");
        dialogue = (Text) loader.getNamespace().get("dialogue");
        dialogue.setVisible(false);
        paperBtn.setOnMouseClicked(e -> showDialogue("XXXX is written on the paper.", dialogue));
    }

    public void showDialogue(String msg, Text textNode) {
        textNode.setText(msg);
        textNode.setVisible(true);
    }

    public void hideDialogue(Text textNode) {
        textNode.setVisible(false);
    }
}
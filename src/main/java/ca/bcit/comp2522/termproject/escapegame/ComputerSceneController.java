package ca.bcit.comp2522.termproject.escapegame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ComputerSceneController implements Serializable {

    @FXML
    private Text codeword;

    @FXML
    private Text dialogue;

    @FXML
    private TextArea input;

    private FXMLLoader loader;

    private SceneData sd;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void switchToComputerScene(MouseEvent event) throws IOException {
        sd = (SceneData) DataSaver.load();

        loader = new FXMLLoader(getClass().getResource("computer-scene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        codeword = (Text) loader.getNamespace().get("codeword");
        input = (TextArea) loader.getNamespace().get("input");
        dialogue = (Text) loader.getNamespace().get("dialogue");

        String codewordString = "I love programming!\n";
        showDialogue(codewordString, codeword);

        input.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                String currentAnswer = input.getText();
                if (currentAnswer.equals(codewordString)) {
                    sd.setHasWon(true);
                    showDialogue("The door has unlocked!", dialogue);
                    DataSaver.save(sd);
                } else {
                    showDialogue("Try again.", dialogue);
                    input.clear();
                }
            }
        });
    }

    public void switchToGameScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
        root = loader.load();
        GameSceneController gsc = loader.getController();
        gsc.switchToGameScene(event);
    }

    public void showDialogue(String msg, Text textNode) {
        textNode.setText(msg);
        textNode.setVisible(true);
        scene.setOnMouseClicked(e -> hideDialogue(dialogue));
    }

    public void hideDialogue(Text textNode) {
        textNode.setVisible(false);
    }
}

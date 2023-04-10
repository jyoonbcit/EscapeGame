package ca.bcit.comp2522.termproject.escapegame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
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

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void switchToComputerScene(MouseEvent event) throws IOException, InterruptedException {
        SceneData sd = (SceneData) DataSaver.load();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("computer-scene.fxml"));
        codeword = (Text) loader.getNamespace().get("codeword");
        input = (TextArea) loader.getNamespace().get("input");

        showDialogue("I love programming!", codeword);

        int duration = 3;
        String currentAnswer;
        for (int i = 0; i < duration; i++) {
            showDialogue(String.valueOf(i), dialogue);
            currentAnswer = input.getText();
            if (currentAnswer.equals(codeword.getText())) {
                sd.setHasWon(true);
                DataSaver.save(sd);
                break;
            }
            Thread.sleep(500);
        }
        if (sd.isHasWon()) {
            showDialogue("The door has unlocked!", dialogue);
            // TODO: Enter name into leaderboard
        }
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

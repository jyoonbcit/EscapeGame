package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ComputerSceneController {

    @FXML
    private Text codeword;

    @FXML
    private Text dialogue;

    @FXML
    private TextArea input;

    private Stage stage;

    private Scene scene;

    private Parent root;

    private boolean hasWon;

    private boolean hasScrewdriver;

    private boolean hasClosetKey;

    public void switchToComputerScene(MouseEvent event) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("computer-scene.fxml"));
        codeword = (Text) loader.getNamespace().get("codeword");
        input = (TextArea) loader.getNamespace().get("input");

        codeword.setText("I love programming!");

        int duration = 3;
        String currentAnswer;
        for (int i = 0; i < duration; i++) {
            showDialogue(String.valueOf(i), dialogue);
            currentAnswer = input.getText();
            if (currentAnswer.equals(codeword.getText())) {
                setHasWon(true);
                break;
            }
            Thread.sleep(500);
        }
        if (getHasWon()) {
            showDialogue("You've beat the game.", dialogue);
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

    public boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public boolean getHasScrewdriver() {
        return hasScrewdriver;
    }

    public void setHasScrewdriver(boolean hasScrewdriver) {
        this.hasScrewdriver = hasScrewdriver;
    }

    public boolean getHasClosetKey() {
        return hasClosetKey;
    }

    public void setHasClosetKey(boolean hasClosetKey) {
        this.hasClosetKey = hasClosetKey;
    }
}

package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;
import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * ComputerSceneController.
 *
 * @author Jihoon Yoon, Wilber Lin
 * @version 2023-04-09
 */
public class ComputerSceneController implements Serializable {
    // Codeword to type
    @FXML
    private Text codeword;
    // Dialogue of game
    @FXML
    private Text dialogue;
    // Text input box
    @FXML
    private TextArea input;
    // FXML Loader instance
    private FXMLLoader loader;
    // SceneData instance
    private SceneData sd;
    // Current stage
    private Stage stage;
    // Current scene
    private Scene scene;
    // Current root
    private Parent root;

    /**
     * Switches to the computer FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToComputerScene(final MouseEvent event) throws IOException {
        sd = (SceneData) DataSaver.loadKeys();

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
                    DataSaver.saveKeys(sd);
                } else {
                    showDialogue("Try again.", dialogue);
                    input.clear();
                }
            }
        });
    }

    /**
     * Switches to the game FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToGameScene(final MouseEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
        root = loader.load();
        GameSceneController gsc = loader.getController();
        gsc.switchToGameScene(event);
    }

    /**
     * Shows dialogue.
     *
     * @param msg dialogue message
     * @param textNode text node
     */
    public void showDialogue(final String msg, final Text textNode) {
        textNode.setText(msg);
        textNode.setVisible(true);
        scene.setOnMouseClicked(e -> hideDialogue(dialogue));
    }

    /**
     * Hides dialogue.
     *
     * @param textNode text node
     */
    public void hideDialogue(final Text textNode) {
        textNode.setVisible(false);
    }
}

package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;
import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * DrawerSceneController.
 *
 * @author Jihoon Yoon, Wilbur Lin
 * @version 2023-04-09
 */
public class DrawerSceneController implements Serializable {
    // Submit button
    @FXML
    private Button submitPinInput;
    // Pin text input
    @FXML
    private TextField pinInput;
    // Safe button
    @FXML
    private Button safeBtn;
    // Paper button
    @FXML
    private Button paperBtn;
    // Dialogue
    @FXML
    private Text dialogue;
    // Current stage
    private Stage stage;
    // Current scene
    private Scene scene;
    // Current root
    private Parent root;

    /**
     * Switches to the drawer FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToDrawerScene(final MouseEvent event) throws IOException {
        SceneData sd = (SceneData) DataSaver.loadKeys();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("drawer-scene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        // Stage setup
        loadFXML(loader);
    }

    /**
     * Sets up the FXML elements.
     *
     * @param loader FXML loader
     */
    public void loadFXML(final FXMLLoader loader) {
        SceneData sd = (SceneData) DataSaver.loadKeys();
        paperBtn = (Button) loader.getNamespace().get("paperBtn");
        safeBtn = (Button) loader.getNamespace().get("safeBtn");
        submitPinInput = (Button) loader.getNamespace().get("submitPinInput");
        pinInput = (TextField) loader.getNamespace().get("pinInput");
        dialogue = (Text) loader.getNamespace().get("dialogue");
        paperBtn.setOnMouseClicked(e -> showDialogue("Hours * Minutes", dialogue));
        safeBtn.setOnMouseClicked(e -> {
            pinInput.setVisible(true);
            showDialogue("Enter the PIN", dialogue);
            submitPinInput.setVisible(true);
            submitPinInput.setOnMouseClicked(f -> {
                String userGuess = pinInput.getText();
                System.out.println(userGuess);
                if (userGuess.equals("1020")) {
                    pinInput.setVisible(false);
                    submitPinInput.setVisible(false);
                    showDialogue("You found a screwdriver!", dialogue);
                    sd.setHasScrewdriver(true);
                    DataSaver.saveKeys(sd);
                    System.out.println("FOUND: " + sd.isHasScrewdriver());
                } else {
                    pinInput.setVisible(false);
                    submitPinInput.setVisible(false);
                    showDialogue("Wrong pin. Try again.", dialogue);
                }
            });
        });
    }

    /**
     * Switches to the game FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToGameScene(final MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
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

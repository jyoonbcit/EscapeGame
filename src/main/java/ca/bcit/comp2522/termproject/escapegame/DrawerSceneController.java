package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

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

public class DrawerSceneController {

    @FXML
    private Button submitPinInput;

    @FXML
    private TextField pinInput;

    @FXML
    private Button safeBtn;

    @FXML
    private Button paperBtn;

    @FXML
    private Text dialogue;

    private Stage stage;

    private Scene scene;

    private Parent root;

    private boolean hasWon;

    private boolean hasScrewdriver;

    private boolean hasClosetKey;

    public void switchToDrawerScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("drawer-scene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Stage setup
        paperBtn = (Button) loader.getNamespace().get("paperBtn");
        safeBtn = (Button) loader.getNamespace().get("safeBtn");
        submitPinInput = (Button) loader.getNamespace().get("submitPinInput");
        pinInput = (TextField) loader.getNamespace().get("pinInput");
        dialogue = (Text) loader.getNamespace().get("dialogue");
        paperBtn.setOnMouseClicked(e -> showDialogue("The paper says 'try multiplication'.", dialogue));

        safeBtn.setOnMouseClicked(e -> {
            pinInput.setVisible(true);
            showDialogue("Enter the PIN", dialogue);
            submitPinInput.setVisible(true);
            submitPinInput.setOnMouseClicked(f -> {
                String userGuess = pinInput.getText();
                System.out.println(userGuess);
                if (userGuess.equals("1001")) {
                    pinInput.setVisible(false);
                    submitPinInput.setVisible(false);
                    showDialogue("You found a screwdriver!", dialogue);
                    hasScrewdriver = true;
                    // TODO
                    System.out.println("FOUND: " + hasScrewdriver);
                } else {
                    pinInput.setVisible(false);
                    submitPinInput.setVisible(false);
                    showDialogue("Wrong pin. Try again.", dialogue);
                    System.out.println("FOUND: " + hasScrewdriver);
                }
            });
        });
    }

    public void switchToGameScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
        root = loader.load();
        GameSceneController gsc = loader.getController();
        gsc.setHasClosetKey(hasClosetKey);
        gsc.setHasScrewdriver(hasScrewdriver);
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

package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;
import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GameSceneController.
 *
 * @author Jihoon Yoon, Wilbur Lin
 * @version 2023-04-09
 */
public class GameSceneController implements Serializable {
    // Serial version UID
    private static final long serialVersionUID = 1L;
    // Key button
    @FXML
    private Button keyBtn;
    // Key image
    @FXML
    private ImageView keyImage;
    // Painting image
    @FXML
    private ImageView paintingImage;
    // Painting button
    @FXML
    private Button paintingBtn;
    // Clock button
    @FXML
    private Button clockBtn;
    // Closet button
    @FXML
    private Button closetBtn;
    // Door button
    @FXML
    private Button doorBtn;
    // Dialogue
    @FXML
    private Text dialogue;
    // Laptop image
    @FXML
    private ImageView laptopImage;
    // Laptop button
    @FXML
    private Button laptopBtn;
    // Scene data
    private SceneData sd;
    // Current stage
    private Stage stage;
    // Current scene
    private Scene scene;
    // Current root
    private Parent root;

    /**
     * Switches to the game FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToGameScene(final MouseEvent event) throws IOException {
        loadSceneData();

        System.out.println("CURRENT:" + sd.isHasScrewdriver() + " " + sd.isHasClosetKey());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
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
     * Loads in the data from other scenes.
     */
    public void loadSceneData() {
        if (DataSaver.loadKeys() != null) {
            sd = (SceneData) DataSaver.loadKeys();
        } else {
            sd = new SceneData();
            DataSaver.saveKeys(sd);
        }
    }

    /**
     * Sets up the FXML elements.
     *
     * @param loader FXML loader
     */
    public void loadFXML(final FXMLLoader loader) {
        dialogue = (Text) loader.getNamespace().get("dialogue");
        showDialogue("Try to leave the room.", dialogue);
        clockBtn = (Button) loader.getNamespace().get("clockBtn");
        clockBtn.setOnMouseClicked(e -> showDialogue("The clock seems to be stuck at 20:51 PM.", dialogue));
        paintingBtn = (Button) loader.getNamespace().get("paintingBtn");
        paintingImage = (ImageView) loader.getNamespace().get("paintingImage");
        keyBtn = (Button) loader.getNamespace().get("keyBtn");
        keyImage = (ImageView) loader.getNamespace().get("keyImage");
        closetBtn = (Button) loader.getNamespace().get("closetBtn");
        doorBtn = (Button) loader.getNamespace().get("doorBtn");
        laptopImage = (ImageView) loader.getNamespace().get("laptopImage");
        laptopBtn = (Button) loader.getNamespace().get("laptopBtn");

        paintingBtn.setOnMouseClicked(e -> {
            if (sd.isHasScrewdriver()) {
                showDialogue("You unscrew the frame.", dialogue);
                paintingImage.setVisible(false);
                paintingBtn.setVisible(false);
            } else {
                showDialogue("The frame is screwed onto the wall.", dialogue);
                System.out.println(sd.isHasScrewdriver());
            }
        });
        keyBtn.setOnMouseClicked(f -> {
            showDialogue("You found a closet key!", dialogue);
            sd.setHasClosetKey(true);
            DataSaver.saveKeys(sd);
            keyImage.setVisible(false);
        });
        closetBtn.setOnMouseClicked(g -> {
            if (sd.isHasClosetKey()) {
                showDialogue("You open the closet.", dialogue);
                laptopImage.setVisible(true);
                laptopBtn.setVisible(true);
            } else {
                showDialogue("The closet is locked.", dialogue);
            }
        });
        doorBtn.setOnMouseClicked(h -> {
            if (sd.isHasWon()) {
                showDialogue("Congratulations, you have escaped!", dialogue);
            } else {
                showDialogue("You can't escape yet!", dialogue);
            }
        });
    }

    /**
     * Switches to the drawer FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToDrawerScene(final MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("drawer-scene.fxml"));
        root = loader.load();
        DrawerSceneController dsc = loader.getController();
        dsc.switchToDrawerScene(event);
    }

    /**
     * Switches to the computer FXML scene.
     *
     * @param event mouse event
     * @throws IOException if fxml file cannot be found
     */
    public void switchToComputerScene(final MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("computer-scene.fxml"));
        root = loader.load();
        ComputerSceneController csc = loader.getController();
        csc.switchToComputerScene(event);
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

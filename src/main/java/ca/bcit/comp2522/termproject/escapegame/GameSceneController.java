package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

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

public class GameSceneController {

    @FXML
    private Button keyBtn;

    @FXML
    private ImageView keyImage;

    @FXML
    private ImageView paintingImage;

    @FXML
    private Button paintingBtn;

    @FXML
    private Button clockBtn;

    @FXML
    private Button closetBtn;

    @FXML
    private Text dialogue;

    @FXML
    private ImageView laptopImage;

    @FXML
    private Button laptopBtn;

    private Stage stage;

    private Scene scene;

    private Parent root;

    private boolean hasWon;

    private boolean hasScrewdriver;

    private boolean hasClosetKey;

    public void switchToGameScene(MouseEvent event) throws IOException {
        // TODO
        System.out.println("CURRENT:" + hasScrewdriver + " " + hasClosetKey);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        dialogue = (Text) loader.getNamespace().get("dialogue");
        showDialogue("Try to leave the room.", dialogue);

        // Stage setup
        clockBtn = (Button) loader.getNamespace().get("clockBtn");
        clockBtn.setOnMouseClicked(e -> showDialogue("The clock seems to be stuck at 20:51 PM.", dialogue));
        paintingBtn = (Button) loader.getNamespace().get("paintingBtn");
        paintingImage = (ImageView) loader.getNamespace().get("paintingImage");
        keyBtn = (Button) loader.getNamespace().get("keyBtn");
        keyImage = (ImageView) loader.getNamespace().get("keyImage");
        closetBtn = (Button) loader.getNamespace().get("closetBtn");

        paintingBtn.setOnMouseClicked(e -> {
            if (hasScrewdriver) {
                showDialogue("You unscrew the frame.", dialogue);
                paintingImage.setVisible(false);
            } else {
                showDialogue("The frame is screwed onto the wall.", dialogue);
                System.out.println(hasScrewdriver);
            }
        });
        keyBtn.setOnMouseClicked(f -> {
            showDialogue("You found a closet key!", dialogue);
            hasClosetKey = true;
            keyImage.setVisible(false);
        });
        closetBtn.setOnMouseClicked(g -> {
            if (hasClosetKey) {
                showDialogue("You open the closet.", dialogue);
                laptopImage = (ImageView) loader.getNamespace().get("laptopImage");
                laptopBtn = (Button) loader.getNamespace().get("laptopBtn");
                laptopImage.setVisible(true);
                laptopBtn.setOnMouseClicked(h -> {
                    try {
                        switchToComputerScene(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (hasWon) {
                        showDialogue("The door has opened.", dialogue);
                    } else {
                        showDialogue("Try again.", dialogue);
                    }
                });
            }
        });
    }

    public void switchToDrawerScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("drawer-scene.fxml"));
        root = loader.load();
        DrawerSceneController dsc = loader.getController();
        dsc.setHasClosetKey(hasClosetKey);
        dsc.setHasScrewdriver(hasScrewdriver);
        dsc.switchToDrawerScene(event);
    }

    public void switchToComputerScene(MouseEvent event) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("computer-scene.fxml"));
        root = loader.load();
        ComputerSceneController csc = loader.getController();
        csc.setHasClosetKey(hasClosetKey);
        csc.setHasScrewdriver(hasScrewdriver);
        csc.switchToComputerScene(event);
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
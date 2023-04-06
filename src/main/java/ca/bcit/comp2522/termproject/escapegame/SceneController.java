package ca.bcit.comp2522.termproject.escapegame;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneController {
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
    private Button submitPinInput;

    @FXML
    private TextField pinInput;

    @FXML
    private Button safeBtn;

    @FXML
    private Button paperBtn;

    @FXML
    private Button doorBtn;

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

    public SceneController() {
        this.hasScrewdriver = false;
        this.hasWon = false;
    }

    public void switchToStartingScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("starting-screen.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchToLeaderboardScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("leaderboard-scene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchToGameScene(MouseEvent event) throws IOException {
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
        doorBtn = (Button) loader.getNamespace().get("doorBtn");
        paintingBtn.setOnMouseClicked(e -> {
            if (hasScrewdriver == true) {
                showDialogue("You unscrew the frame.", dialogue);
                paintingImage.setVisible(false);
            } else {
                showDialogue("The frame is screwed onto the wall.", dialogue);
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
                    if (hasWon = true) {
                        showDialogue("The door has opened.", dialogue);
                    } else {
                        showDialogue("Try again.", dialogue);
                    }
                });
            }
        });
        doorBtn.setOnMouseClicked(h -> {
            //
        });
    }

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
                    // TODO: Implement inside safe
                    pinInput.setVisible(false);
                    submitPinInput.setVisible(false);
                    showDialogue("You found a screwdriver!", dialogue);
                    hasScrewdriver = true;
                } else {
                    pinInput.setVisible(false);
                    submitPinInput.setVisible(false);
                    showDialogue("Wrong pin. Try again.", dialogue);
                }
            });
        });
    }

    public void switchToComputerScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("computer-scene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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
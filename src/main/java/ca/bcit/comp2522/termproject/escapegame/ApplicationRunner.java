package ca.bcit.comp2522.termproject.escapegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ApplicationRunner.
 *
 * @author Jihoon Yoon, Wilber Lin
 * @version 2023-04-09
 */
public class ApplicationRunner extends Application {
    @Override
    public void start(final Stage stage) throws IOException {
        SceneData sd = new SceneData();
        DataSaver.saveKeys(sd);

        Parent root = FXMLLoader.load(getClass().getResource("starting-screen.fxml"));
        Scene scene = new Scene(root);

        //Music
        String path = "src/main/resources/ca/bcit/comp2522/termproject/escapegame/music/mixkit-driving-ambition-32.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        File timeFile = new File("time.txt");
        timeFile.createNewFile();
        FileWriter myWriter = new FileWriter("time.txt");

        long currentTime = System.currentTimeMillis();
        String currentTimeString = String.valueOf(currentTime);

        myWriter.write(currentTimeString);
        myWriter.close();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Launches the game.
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        launch();
    }
}

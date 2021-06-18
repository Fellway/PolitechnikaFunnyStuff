package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Arc;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Arc speedLvl;

    @FXML
    private Arc rotLvl;

    @FXML
    private Button gasPedal;

    @FXML
    private Button breakPedal;

    @FXML
    private Pane blinker;

    @FXML
    private Pane rightArrow;

    @FXML
    private Pane leftArrow;
    private BlinkerThread blinkerThread;
    private MediaPlayer mediaPlayer;

    @FXML
    private void blinkRight() {
        blinker.rotateProperty().setValue(10);
        blinkerThread.setRightActive();
        mediaPlayer.play();
    }

    @FXML
    private void blinkLeft() {
        blinker.rotateProperty().setValue(-10);
        blinkerThread.setLeftActive();
        mediaPlayer.play();
    }

    @FXML
    private void blinkNone() {
        blinker.rotateProperty().setValue(0);
        blinkerThread.disable();
        mediaPlayer.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String s = new File("src/sample/assets/blinker_sound.mp3").toURI().toString();
        Media sound = new Media(s);
        this.mediaPlayer = new MediaPlayer(sound);
        PedalsThread thread = new PedalsThread(speedLvl, gasPedal, breakPedal, rotLvl);
        this.blinkerThread = new BlinkerThread(leftArrow, rightArrow);
        thread.start();
        blinkerThread.start();
    }


}

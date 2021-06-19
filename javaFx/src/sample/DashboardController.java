package sample;

import javafx.css.PseudoClass;
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

    @FXML
    private Button startStop;

    @FXML
    private Button emergencyLights;

    private boolean isEngineOn = false;
    private BlinkerThread blinkerThread;
    private PedalsThread pedalsThread;
    private MediaPlayer blinkerSound;
    private MediaPlayer carStartSound;
    private MediaPlayer carStopSound;

    @FXML
    private void blinkRight() {
        blinker.rotateProperty().setValue(10);
        blinkerThread.setRightActive();
        blinkerSound.play();
    }

    @FXML
    private void blinkLeft() {
        blinker.rotateProperty().setValue(-10);
        blinkerThread.setLeftActive();
        blinkerSound.play();
    }

    @FXML
    private void blinkNone() {
        blinker.rotateProperty().setValue(0);
        blinkerThread.disable();
        blinkerSound.stop();
    }

    @FXML
    private void emergencyLights() {
        blinkerThread.emergencyToggle();
        if (blinkerThread.isEmergencyOn()) {
            blinkerSound.play();
            emergencyLights.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        } else {
            blinkerSound.stop();
            emergencyLights.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
        }
    }

    @FXML
    private void engineToggle() {
        isEngineOn = !isEngineOn;
        startStop.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), isEngineOn);
        gasPedal.setDisable(!isEngineOn);
        if(!isEngineOn) {
            carStartSound.play();
            carStopSound.stop();
        } else {
            carStopSound.play();
            carStartSound.stop();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String blinkerSoundPath = new File("src/sample/assets/blinker_sound.mp3").toURI().toString();
        String startSoundPath = new File("src/sample/assets/car_start_sound.mp3").toURI().toString();
        String stopSoundPath = new File("src/sample/assets/car_stop_sound.mp3").toURI().toString();
        Media blinkerSound = new Media(blinkerSoundPath);
        Media engineStartSound = new Media(startSoundPath);
        Media engineStopSound = new Media(stopSoundPath);
        this.blinkerSound = new MediaPlayer(blinkerSound);
        this.carStopSound = new MediaPlayer(engineStartSound);
        this.carStartSound = new MediaPlayer(engineStopSound);
        this.pedalsThread = new PedalsThread(speedLvl, gasPedal, breakPedal, rotLvl);
        this.blinkerThread = new BlinkerThread(leftArrow, rightArrow);
        blinkerThread.start();
        pedalsThread.start();
    }
}

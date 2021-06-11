package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Arc;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Arc speedLvl;

    @FXML
    private Button gasPedal;

    private GasPedalThread thread;


    @FXML
    private void calculateSpeed() throws InterruptedException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thread = new GasPedalThread(speedLvl, gasPedal);
        thread.start();
    }


}

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
    private Arc rotLvl;

    @FXML
    private Button gasPedal;

    @FXML
    private Button breakPedal;


    private PedalsThread thread;


    @FXML
    private void calculateSpeed() throws InterruptedException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thread = new PedalsThread(speedLvl, gasPedal, breakPedal, rotLvl);
        thread.start();
    }


}

package sample;

import javafx.scene.control.Button;
import javafx.scene.shape.Arc;

public class GasPedalThread extends Thread {

    private static final Double ACCELERATION = 1.0;

    private Button gasPedal;
    private Arc speedLevel;
    private Double currentSpeed;

    public GasPedalThread(final Arc arc, final Button gasPedal) {
        this.speedLevel = arc;
        this.gasPedal = gasPedal;
        currentSpeed = 275.0;
    }

    @Override
    public void run() {
        while (true) {
            if (gasPedal.isPressed() && currentSpeed > 0) {
                speedLevel.setLength(currentSpeed -= ACCELERATION);
            } else if (!gasPedal.isPressed() && currentSpeed < 275.0) {
                speedLevel.setLength(currentSpeed += ACCELERATION);
            }
            threadSleep(10);
        }
    }

    private void threadSleep(final int sleepTime) {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

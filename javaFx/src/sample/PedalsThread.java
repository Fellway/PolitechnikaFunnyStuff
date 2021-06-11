package sample;

import javafx.scene.control.Button;
import javafx.scene.shape.Arc;

public class PedalsThread extends Thread {

    private static final Double ROLLING_SPEED = 0.05;
    private static final Double ACCELERATION = .4;
    private static final Double BREAK_POWER = 1.0;

    private static final Double SPEED_LVL_MAX_ANGLE = 270.0;
    private static final Double SPEED_LVL_MIN_ANGLE = 0.0;

    private static final Double ROT_LVL_MAX_ANGLE = 160.0;
    private static final Double ROT_LVL_MIN_ANGLE = 0.0;
    private static final Double ROT_REST_LVL = 110.0;
    private static final Double ROT_REST_SPEED = .5;
    private static final Double ROT_LVL_SPEED = 1.0;
    private static final Double GEAR_CHANGE_ROT_LVL = 20.0;

    private final Button breakPedal;
    private final Button gasPedal;
    private final Arc speedLevel;
    private final Arc rotLvl;

    private Double currentSpeed;
    private Double currentRot;

    public PedalsThread(final Arc speedLevel, final Button gasPedal, final Button breakPedal, final Arc rotLvl) {
        this.speedLevel = speedLevel;
        this.rotLvl = rotLvl;
        this.gasPedal = gasPedal;
        this.breakPedal = breakPedal;
        currentSpeed = SPEED_LVL_MAX_ANGLE;
        currentRot = ROT_LVL_MAX_ANGLE;
    }

    @Override
    public void run() {
        while (true) {
            if (gasPedal.isPressed()) {
                accelerateCar();
            } else if (breakPedal.isPressed()) {
                breakCar();
            } else {
                rollCar();
            }
            threadSleep(20);
        }
    }

    private void breakCar() {
        if (currentSpeed < SPEED_LVL_MAX_ANGLE) {
            speedLevel.setLength(currentSpeed += BREAK_POWER);
        }
    }

    private void accelerateCar() {
        if (currentSpeed > SPEED_LVL_MIN_ANGLE) {
            speedLevel.setLength(currentSpeed -= ACCELERATION);
            if(currentRot > ROT_LVL_MIN_ANGLE) {
                rotLvl.setLength(currentRot -= ROT_LVL_SPEED);
            }
        }

        if(currentRot <= GEAR_CHANGE_ROT_LVL) {
            speedLevel.setLength(currentSpeed += 5.0);
            rotLvl.setLength(currentRot += 120.0);
        }
    }

    private void rollCar() {
        if (currentSpeed < SPEED_LVL_MAX_ANGLE) {
            speedLevel.setLength(currentSpeed += ROLLING_SPEED);
        }
        if (currentRot < ROT_REST_LVL) {
            rotLvl.setLength(currentRot += ROT_REST_SPEED);
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

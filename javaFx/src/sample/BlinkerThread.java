package sample;

import javafx.css.PseudoClass;
import javafx.scene.layout.Pane;

public class BlinkerThread extends Thread {

    private final Pane leftArrow;
    private final Pane rightArrow;
    private boolean isRightActive;
    private boolean isLeftActive;

    public BlinkerThread(final Pane leftArrow, final Pane rightArrow) {
        this.leftArrow = leftArrow;
        this.rightArrow = rightArrow;
    }

    @Override
    public void run() {
        while (true) {
            if (isRightActive && !isLeftActive) {
                rightArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
                threadSleep(400);
                rightArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
                threadSleep(50);
            }

            if (isLeftActive && !isRightActive) {
                leftArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
                threadSleep(400);
                leftArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
                threadSleep(50);
            }

            if (!isLeftActive && !isRightActive) {
                rightArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
                leftArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            }

            if (isLeftActive && isRightActive) {
                rightArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
                leftArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
                threadSleep(400);
                rightArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
                leftArrow.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
                threadSleep(50);
            }

            threadSleep(500);
        }
    }

    private void threadSleep(final int sleepTime) {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setLeftActive() {
        isLeftActive = true;
        isRightActive = false;
    }

    public void setRightActive() {
        isRightActive = true;
        isLeftActive = false;
    }

    public void disable() {
        isRightActive = false;
        isLeftActive = false;
    }

    public void emergencyToggle() {
        isRightActive = !isRightActive;
        isLeftActive = !isLeftActive;
    }

    public boolean isEmergencyOn() {
        return isLeftActive && isRightActive;
    }
}

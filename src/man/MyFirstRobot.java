package man;
import robocode.*;

public class MyFirstRobot extends Robot {
    public void run() {
        while (true) {
            ahead(100);
            ahead(100);
            turnGunRight(360);
            back(100);
            back(100);
            back(100);
            ahead(100);
            ahead(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }
}
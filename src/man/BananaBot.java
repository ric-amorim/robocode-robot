package man;
import robocode.*;
import java.awt.Color;


public class BananaBot extends AdvancedRobot {

    public double distanceX;
    public double distanceY;
    public int gunDirection = 1;
    public void run() {
        setColors(Color.yellow,Color.orange,Color.black); // body, gun, radar
        while (true) {
         /*   ahead(100);
            ahead(100);
            turnGunRight(360);
            back(100);
            back(100);
            back(100);
            ahead(100);
            ahead(100);
            turnGunRight(360);
        */
            useGun();
            useBody();
            useRadar();
        }
    }

    public void useGun(){
        ///setFire(100);
        turnGunRight(360);

    }

    public void useBody(){
        //setAhead(100);

    }

    public void useRadar(){

    }
    public void onScannedRobot(ScannedRobotEvent e) {
        setTurnRight(e.getBearing());
        setFire(3);
        setAhead(100);

        gunDirection =- gunDirection;

        setTurnGunRight(360*gunDirection);

        execute();
    }

    public void onHitWall(HitWallEvent e){
        System.out.println("PUMMMM");
        setBack(100);
        execute();

    }

    public void onHitRobot(HitRobotEvent e){
        if (e.getBearing() > -90 && e.getBearing() <= 90) {
            back(50);
        }else {
            ahead(50);
        }
        turnRight(90);
    }
}
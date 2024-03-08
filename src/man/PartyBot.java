package man;
import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.util.Random;


/**
 * Our PartyBot. Let's have some fun!!!
 *
 * @author Ricardo Amorim
 * @author João Vivas
 */
public class PartyBot extends AdvancedRobot {

    Random random = new Random();

    public void run() {
        setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
        while (true) {

            useGun();
            useBody();
            useRadar();
            execute();
        }
    }

    public void useGun(){
    }

    public void useBody(){
        double angle = random.nextDouble() * 360;

        double distance = random.nextDouble() * 150 + 50;

        if(random.nextDouble() > 0.5){
            setTurnRight(angle);
        }else{
            setTurnLeft(angle);
        }
        setAhead(distance);
    }

    public void useRadar(){
        turnRadarRight(360);
    }
    public void onScannedRobot(ScannedRobotEvent e) {
        double radarDirection = getHeading() + e.getBearing() - getRadarHeading();
        setTurnRadarRight(radarDirection);
        double gunTurnAmt = normalRelativeAngleDegrees(getHeading() + e.getBearing() - getGunHeading());
        setTurnGunRight(gunTurnAmt);

        if(e.getDistance() < 200 && getEnergy() > 70){
            setFire(3);
        }else if(e.getDistance() < 400 && getEnergy() > 40){
            setFire(2);
        }else {
            setFire(1);
        }

        useBody();
        
    }

    public void onHitWall(HitWallEvent e){
        if (e.getBearing() > -90 && e.getBearing() <= 90) {
            setBack(100);
        }else {
            setAhead(100);
        }

    }

    public void onHitRobot(HitRobotEvent e){
        if (e.getBearing() > -90 && e.getBearing() <= 90) {
            setBack(100);
        }else {
            setAhead(100);
        }
        setTurnRight(90);
    }
    public void onHitByBullet(HitByBulletEvent e){
        useBody();
    }

    /**
     * Do a funny little dance
     */
    public void onWin(WinEvent e){
        for (int i = 0; i < 50; i++) {
            turnRight(30);
            turnLeft(30);
        }
    }

    /**
     * Change the colors randomly
    */
    public void onStatus(StatusEvent e){
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        Color color = new Color(red,green,blue);

        setAllColors(color);
    }
}

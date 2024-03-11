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
    double posX;
    double posY;

    /**
     * robot's init function
     */
    public void run() {
        setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
        while (true) {
            posX = getX();
            posY = getY();
            useBody();
            useRadar();
            execute();
        }
    }

    /**
     * Turn and move robot's body
     */
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

    /**
     * Turn robot's radar
     */
    public void useRadar(){
        turnRadarRight(360);
    }

    /**
     * When scanned a robot, turn the radar and gun to the robot and shoot
     * 
     */
    public void onScannedRobot(ScannedRobotEvent e) {

        double radarDirection = getHeading() + e.getBearing() - getRadarHeading();
        setTurnRadarRight(radarDirection);
        double gunTurnAmt = normalRelativeAngleDegrees(getHeading() + e.getBearing() - getGunHeading());
        setTurnGunRight(gunTurnAmt);

        // using a spiral technique sometimes
        if (e.getDistance() < 400) {
            setTurnRight(e.getBearing() + 100);
        }

            if (e.getDistance() < 200 && getEnergy() > 70) {
                setFire(3);
            } else if (e.getDistance() < 400 && getEnergy() > 40) {
                setFire(2);
            } else {
                setFire(1);
            }

            useBody();

        }

    /**
     * When hit a wall, turn the robot to the opposite direction
     */
    public void onHitWall(HitWallEvent e){

        // 1º quadrante
        if (e.getBearing() >= 0 && e.getBearing() < 90) {
            turnRight(e.getBearing() + 90 + 45);
        }
        // 2º quadrante
        else if(e.getBearing() >= 90 && e.getBearing() < 180){
            turnRight(e.getBearing() - 90 - 45);
        }
        // 3º quadrante
        else if(e.getBearing() >= 180 && e.getBearing() < 270){
            turnRight(e.getBearing() - 90 - 45);
        }
        // 4º quadrante
        else {
            turnRight(e.getBearing() + 90 + 45 );
        }
        ahead(100);

    }

    /**
     * When hit a robot, turn the robot to the opposite direction
     */
    public void onHitRobot(HitRobotEvent e){

        if (e.getBearing() >= 0 && e.getBearing() < 90) {
            turnRight(e.getBearing() + 90);
        }
        // 2º quadrante
        else if(e.getBearing() >= 90 && e.getBearing() < 180){
            turnRight(e.getBearing() - 90);
        }
        // 3º quadrante
        else if(e.getBearing() >= 180 && e.getBearing() < 270){
            turnRight(e.getBearing() - 90);
        }
        // 4º quadrante
        else {
            turnRight(e.getBearing() + 90);
        }
        ahead(100);
    }

    /**
     *  When hit by bullet, move randomly
     */
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

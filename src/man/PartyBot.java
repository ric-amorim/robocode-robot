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

    public void run() {
        int height = (int) getBattleFieldHeight();
        int width = (int) getBattleFieldWidth();
        System.out.println(height);
        System.out.println(width);
        setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
        while (true) {
            posX = getX();
            posY = getY();
            useGun();
            useBody();
            useRadar();
            execute();
        }
    }

    public void useGun(){
    }

    public void useBody(){
        double distance = random.nextDouble() * 150 + 50;
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

        // using a spiral technique (Rodeia o inimigo enquanto dispara. Mantém o movimento, mas é mais eficiente quanto mais próximo estiver)
        setTurnRight(e.getBearing() + 100);

        if(e.getDistance() < 200 && getEnergy() > 70){
            setFire(3);
        }else if(e.getDistance() < 400 && getEnergy() > 40){
            setFire(2);
        }else {
            setFire(1);
        }

        useBody();
        
    }

    // O robô não fica preso na parede, mas está a perder muita energia a ir contra as paredes
    // TODO: reduzir o nº de choques com as paredes
    // sugestão: obter as dimensões da arena e quando o robô se aproximar duma parede, andar/virar no sentido oposto
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
        /*
        int n = random.nextInt(2);
        if(n == 1){
            turnRight(e.getBearing() + 90);
        }

        else{
            turnRight(e.getBearing() - 90);
        }
        ahead(-50);
         */
        /*
        if (e.getBearing() > -90 && e.getBearing() <= 90) {
            setBack(100);
        }else {
            setAhead(100);
        }
         */

    }

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
        setTurnGunRight(360);
        /*
        if (e.getBearing() > -90 && e.getBearing() <= 90) {
            setTurnRight(e.getBearing() - 90);
            setBack(100);
        }else {
            setTurnRight(e.getBearing() + 90);
            setAhead(100);
        }
         */
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

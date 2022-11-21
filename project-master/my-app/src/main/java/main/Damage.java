package main;

import java.lang.*;
import Characters.Character;
import Characters.Enemy;
import Characters.MainPlayer;
import Collectables.Shields;
import Collectables.Swords;
import Elements.Fire;

import static java.lang.Math.abs;

/**
 * used to see if the user conflicts damage on an enemy or vice versa
 */
public class Damage {
    int coolDown = 0;
    GamePanel currentGamePanel;

    /**
     * Constructor - used to get obtain the main panel to draw on
     * @param panel - used to obtain the main panel to draw on
     */
    public Damage(GamePanel panel){
        currentGamePanel = panel;//include fire locations in the future
    }

    /**
     * used to check if when a user and an enemy collide who gets damaged
     * @param player check where the player is
     * @param enemys check where the enemy is
     * @param collectedSword check if the player has a sword
     * @param collectedShield check if the player has a shield
     */
    public void checkDamageEnemy(Character player, Character[] enemys, Swords collectedSword, Shields collectedShield){
        if (coolDown >0){
            coolDown--;
        }

        int playerX = player.getXPos();
        int playerY = player.getYPos();
        for (Character enemy:enemys){

            int enemyX = enemy.getXPos();
            int enemyY = enemy.getYPos();

            //Pythagorean theorem
            if(Math.sqrt(Math.pow(abs(playerX-enemyX),2) +Math.pow(abs(playerY-enemyY),2)) < 35 && coolDown <=0 && (((MainPlayer)player).getIsProtected()) && ((Enemy)enemy).getIsAlive()) {//picked 30 as an arbitrary number can change if needed. seemed alright from trial and error.

                if(collectedShield.getShieldHealth() == 2){

                    collectedShield.shieldHit();
                    coolDown = 120;
                    break;
                }else if(collectedShield.getShieldHealth() <= 1){
                    collectedShield.shieldHit();
                    collectedShield.removeShield();
                    ((MainPlayer)player).makeUnProtected();
                    coolDown = 120;
                    break;
                }


            }else if(Math.sqrt(Math.pow(abs(playerX-enemyX),2) +Math.pow(abs(playerY-enemyY),2)) < 35 && coolDown <=0 && !(((MainPlayer)player).getIsHostile()) && ((Enemy)enemy).getIsAlive()){//picked 30 as an arbitrary number can change if needed. seemed alright from trial and error.
                ((MainPlayer)player).changeLives(-2);


                coolDown=60;//coolDown from damage set to 1 second. can be changed.
                break;
            }else if(Math.sqrt(Math.pow(abs(playerX-enemyX),2) +Math.pow(abs(playerY-enemyY),2)) < 35 && coolDown <=0 && (((MainPlayer)player).getIsHostile()) && ((Enemy)enemy).getIsAlive()) {

                collectedSword.removeSword();
                ((Enemy) enemy).kill();
                ((MainPlayer) player).makeFriendly();
                coolDown = 60;
                break;
            }

        }

    }

    /**
     * used to check if the user gets damaged or puts out a fire object
     * @param player used to check where the player is
     * @param fires used to check where the fire is
     * @param collectedShield used to check if the user has a shield
     */
    public void checkDamageFire(Character player, Fire[] fires, Shields collectedShield) {
        if (coolDown > 0) {
            coolDown--;
        }
        int playerXPos = player.getXPos();
        int playerYPos = player.getYPos();

        for (Fire fire : fires) {
            int fireXPos = fire.getXPos();
            int fireYPos = fire.getYPos();

            //Pythagorean theorem
            if (Math.sqrt(Math.pow(abs(playerXPos - fireXPos), 2) + Math.pow(abs(playerYPos - fireYPos), 2)) < 35 && coolDown <= 0 && (((MainPlayer)player).getIsProtected()) && (fire.getVisibility())) {

                if (collectedShield.getShieldHealth() == 2) {

                    collectedShield.shieldHit();
                    coolDown = 120;
                    break;
                } else if (collectedShield.getShieldHealth() <= 1) {

                    collectedShield.shieldHit();
                    collectedShield.removeShield();
                    ((MainPlayer)player).makeUnProtected();
                    coolDown = 120;
                    break;
                }
            } else if (Math.sqrt(Math.pow(abs(playerXPos - fireXPos), 2) + Math.pow(abs(playerYPos - fireYPos), 2)) < 35 && coolDown <= 0 && !(((MainPlayer)player).getIsProtected()) && (fire.getVisibility())) {

                fire.setInvisible();
                ((MainPlayer) player).changeLives(-2);
                coolDown = 60;
                break;
            }


        }
    }

}
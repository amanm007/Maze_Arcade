package Characters;

import main.GamePanel;

import java.awt.*;

/**
 * parent class of both MainPlayer and Enemy.
 */
public class Character {

    //Positions of the character
    int xPos;
    int yPos;
    int window;
    //Enemy or friendly
    boolean isHostile;
    //Speed of the character
    int unChangedSpeed;
    int speedVertical;
    int speedHorizontal;
    //dead or alive
    boolean isAlive;
    //size of entity
    Rectangle hitBox;
    //what panel is the game set to
    GamePanel currentGamePanel;
    boolean isVisible;

    /**
     * constructor
     */
    public Character() {
    }

    /**
     * returns if character has a sword
     *
     * @return
     */
    public boolean getIsHostile() {
        return isHostile;
    }


    /**
     * returns position x
     *
     * @return
     */
    public int getXPos() {
        return xPos + window * 768;
    }

    /**
     * returns position y
     *
     * @return
     */
    public int getYPos() {
        return yPos;
    }


    /**
     * changes the speed at which it moves up or down
     *
     * @param newSpeed
     */
    public void changeVerticalSpeed(int newSpeed) {
        if (newSpeed == 0) {
            speedVertical = 0;
        } else {
            speedVertical = unChangedSpeed;
        }
    }

    /**
     * changes the speed at which it moves right or left
     *
     * @param newSpeed
     */
    public void changeHorizontalSpeed(int newSpeed) {
        if (newSpeed == 0) {
            speedHorizontal = 0;
        } else {
            speedHorizontal = unChangedSpeed;
        }
    }

    /**
     * getter - return horizontal speed of player
     * @return - int return horizontal speed of player
     */
    public int getSpeedHorizontal(){
        return speedHorizontal;
    }

    /**
     * getter - return vertical speed of player
     * @return - int return vertical speed of player
     */
    public int getSpeedVertical(){
        return speedVertical;
    }


    /**
     * returns the hitbox oh character
     *
     * @return
     */
    public Rectangle getHitBox() {
        return new Rectangle(hitBox.x + window * 768, hitBox.y, hitBox.width, hitBox.height);
    }
}

package Collectables;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * coins are a bonus reward objective
 */
public class Coin extends Coins {
    //position of the coins
    GamePanel currentGamePanel;
    int xPos;
    int yPos;
    int timer;
    boolean isVisible;
    //coin image
    BufferedImage coinImage;

    /**
     *
     * @param mainGamePanel -the game panel
     * @param xPos -x position of object
     * @param yPos -y position of object
     */
    public Coin(GamePanel mainGamePanel, int xPos, int yPos) {
        // this gives the coin in reference to the game panel
        this.currentGamePanel = mainGamePanel;
        //position of the coins
        this.xPos = xPos;
        this.yPos = yPos;
        isVisible = false;
        timer=0;

        loadCoinImage();
    }
    /**
     *  these are used to toggle the coin existence
     */
    private void loadCoinImage(){
        //loads image of the coin
        try {
            coinImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/coin.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  these are used to toggle the coin existence
     */    public void setInvisible(){
        isVisible = false;
    }
    /**
     *  these are used to toggle the coin existence
     */
    public void setVisible(){
        isVisible = true;
    }
    /**
     *  these are used to toggle the coin existence
     */
    public boolean getVisibility(){
        return isVisible;
    }
    /**
     * toggles the coin in and out of existence so that they randomly spawn
     */
    public void rollDice(){
        if (timer>0){
            timer--;
        }
        if (timer==0){
            setInvisible();
        }
        if ((int)((Math.random() * (1500)) + 1)==1){
            setVisible();
            timer = 300;
        }
    }
    /**
     * draws the coin onto the map
     * @param g2 graphics
     */
    public void draw(Graphics2D g2){
        if(isVisible) {
            g2.drawImage(coinImage, xPos, yPos, 48, 48, null);
        }
    }
    //returns the position of the coin
    /**
     *
     */
    public int getXPos(){
        return xPos;
    }
    /**
     *
     */
    public int getYPos(){
        return yPos;
    }

    public void setTimer(int time){
        timer = time;
    }
}


package Collectables;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *Sword are another bonus reward in the game and are needed to survive multiple hits from hostiles
 */

public class Sword extends Swords {

    //Sword positions
    int xPos;
    int yPos;
    //check to see if key there or not
    boolean isVisible;
    int window;
    BufferedImage swordImage;

    /**
     *
     * @param mainGamePanel -the game panel
     * @param xPos -x position of object
     * @param yPos -y position of object
     * @param window - the actual window its on
     */
    public Sword(GamePanel mainGamePanel, int xPos, int yPos, int window) {
        // this gives the sword in reference to the game panel
        this.currentGamePanel = mainGamePanel;
        //Sword position getting adjusted for this specific instance
        this.xPos = xPos;
        this.yPos = yPos;
        //the visiblility of the sword when made is set to be true to start with
        this.window = window;
        isVisible = true;

        loadSwordImage();
    }

    /**
     * loads the sword image here
     */
    private void loadSwordImage(){
        try {
            swordImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/Sword.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  these are used to toggle the sword existence
     */
    public void setInvisible(){
        isVisible = false;
    }
    /**
     *  these are used to toggle the sword existence
     */
    public void setVisible(){
        isVisible = true;
    }
    /**
     *  these are used to toggle the sword existence
     */
    public boolean getVisibility(){
        return isVisible;
    }

    /**
     * draws the sword onto the map
     * @param g2 graphics
     */
    public void draw(Graphics2D g2){
        if(isVisible) {
            g2.drawImage(swordImage, xPos+((window- currentGamePanel.getCurrentWindow())*768), yPos, 48, 48, null);
        }
    }

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
    /**
     *
     */
    public int getWindow(){
        return window;
    }
}


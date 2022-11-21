package Collectables;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 *keys are the basic reward in the game and are needed for opening doors
 */
public class Key extends Keys {
    //key positions
    int xPos;
    int yPos;
    //check to see if key there
    boolean isVisible;
    int window;
    BufferedImage keyImage;

    /**
     *
     * @param mainGamePanel -the game panel
     * @param xPos -x position of object
     * @param yPos -y position of object
     * @param window - the actual window its on
     */
    public Key(GamePanel mainGamePanel, int xPos, int yPos,int window) {
        // this gives the key in reference to the game panel
        this.currentGamePanel = mainGamePanel;
        //keys position getting adjusted for this specific instance
        this.xPos = xPos;
        this.yPos = yPos;
        this.window = window;
        //the visibility of the key when made is set to be true to start with
        isVisible = true;

        loadKeyImage();
    }

    /**
     * loads the image for the key
     */
    private void loadKeyImage(){
        try {
            //loads the key image here
            keyImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/key.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     *  these are used to toggle the key existence
     */
    public void setInvisible(){
        isVisible = false;
    }
    /**
     *  these are used to toggle the key existence
     */
    public void setVisible(){
        isVisible = true;
    }
    /**
     *  these are used to toggle the key existence
     */
    public boolean getVisibility(){
        return isVisible;
    }

    /**
     * draws the key onto the map
     * @param g2 graphics
     */
    public void draw(Graphics2D g2){
        if(isVisible) {
            g2.drawImage(keyImage, xPos+((window- currentGamePanel.getCurrentWindow())*768), yPos, 48, 48, null);
        }
    }
    //positions of the key
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



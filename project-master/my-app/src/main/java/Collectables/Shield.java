package Collectables;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 Shield are another bonus reward in the game and are needed to survive multiple hits from hostiles
 */
public class Shield extends Shields {
    //Shield positions
    int xPos;
    int yPos;
    //check to see if key there or not
    boolean isVisible;
    int window;
    BufferedImage shieldImage;

    /**
     *
     * @param mainGamePanel -the game panel
     * @param xPos -x position of object
     * @param yPos -y position of object
     * @param window the thing is on
     */
    public Shield(GamePanel mainGamePanel, int xPos, int yPos, int window) {
        // this gives the shield in reference to the game panel
        this.currentGamePanel = mainGamePanel;
        //Shields position getting adjusted for this specific instance
        this.xPos = xPos;
        this.yPos = yPos;
        //the visiblility of the shield when made is set to be true to start with
        this.window = window;
        shieldHealth = 2;
        isVisible = true;

        loadShieldImage();
    }

    /**
     * these are used to load the shield image
     */
    private void loadShieldImage(){
        try {
            //loads the shield image here
            shieldImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/Shield.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * these are used to toggle the shield existence
     */
    public void setInvisible(){
        isVisible = false;
    }
    /**
     * these are used to toggle the shield existence
     */
    public void setVisible(){
        isVisible = true;
    }
    /**
     * these are used to toggle the shield existence
     */
    public boolean getVisibility(){
        return isVisible;
    }

    /**
     * draws the shield into existence
     * @param g2 graphs driver 2
     */
    public void draw(Graphics2D g2){
        if(isVisible) {
            g2.drawImage(shieldImage, xPos+((window- currentGamePanel.getCurrentWindow())*768), yPos, 48, 48, null);
        }
    }
    /**
     * draws the shield into existence
     * @param g2 graphs driver 2
     */
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



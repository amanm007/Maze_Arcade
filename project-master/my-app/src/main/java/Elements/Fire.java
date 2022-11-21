package Elements;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Fire class draws a fire object to the screen and stores where it is located
 */
public class Fire {
    //Fire is a non moving object which damaged the player if they touch it
    //position of the fire
    int xPos;
    int yPos;
    int window;
    //If the fire is there or not
    boolean isVisible;
    //what panel is the fire on
    GamePanel currentGamePanel;
    //image of the fire
    BufferedImage fireImage;

    /**
     * Constructor used to set the default values of the object
     * @param mainGamePanel used to draw to the main panel
     * @param xPos used to store the x position of the fire
     * @param yPos used to store the y position of the fire
     * @param window which level the fire will be placed
     */
    public Fire(GamePanel mainGamePanel, int xPos, int yPos, int window){
        //This is for the specific instance of fire such as where it is located on the screen
        this.currentGamePanel = mainGamePanel;
        this.xPos = xPos;
        this.yPos = yPos;
        this.window = window;
        isVisible =true;
        loadFireImage();
    }

    /**
     * setter - stores the fire object as not invisible
     */
    public void setInvisible(){
        isVisible = false;
    }

    /**
     * setter - stores the fire object as invisible
     */
    public void setVisible(){
        isVisible = true;
    }

    /**
     * getter - used to find out if the object is invisible or not
     * @return boolean - represents if the object is invisible or not
     */
    public boolean getVisibility(){
        return isVisible;
    }

    /**
     * stores the fire objects texture
     */
    private void loadFireImage() {

        try {
            fireImage = ImageIO.read(getClass().getResourceAsStream("/elementAssets/Fire.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * get the x position of the object
     * @return int - the x position of the object
     */
    public int getXPos() {
        return xPos+window*768;
    }

    /**
     * get the y position of the object
     * @return int - the y position of the object
     */
    public int getYPos(){
        return yPos;
    }

    /**
     *  get which level the object will be placed in
     * @return int - the level the object will be in
     */
    public int getWindow(){
        return window;
    }

    /**
     * Draws the object to the screen
     * @param g2 - Graphics2D object allows the program to draw to the screen
     */
    public void draw(Graphics2D g2){
        if(isVisible){
            g2.drawImage(fireImage, xPos+((window- currentGamePanel.getCurrentWindow())*768), yPos, 48, 48, null);
        }
    }
}

package Elements;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Door class draws a door to the game panel as well as tracks where the door is
 */
public class Door {
    //Door which moves player from level to level
    //position of door
    int xPos;
    int yPos;
    int window;
    int destinationWindow;
    //game panel door is on
    GamePanel currentGamePanel;
    //image of door
    BufferedImage doorImage;

    /**
     * Constructor sets the basic values for the door object
     * @param mainGamePanel used to draw to the main panel
     * @param xPos the x position of the door
     * @param yPos the y position of the door
     * @param window used to determine which level the door will be placed in
     * @param destinationWindow used to determine where which level the door leads to
     */
    public Door(GamePanel mainGamePanel,int xPos, int yPos, int window, int destinationWindow){
        //for a specific instance of a door in the game
        this.currentGamePanel = mainGamePanel;
        this.xPos = xPos;
        this.yPos = yPos;
        this.window = window;
        this.destinationWindow = destinationWindow;

        loadDoorImage();
    }

    /**
     * Used to find out which level the door teleports the player to
     * @return int - which level the door leads to
     */
    public int getDestinationWindow(){
        return destinationWindow;
    }

    /**
     * Gets the textures of the door and saves it
     */
    private void loadDoorImage(){
        //this loads the image from the file for our door

        try {
            doorImage = ImageIO.read(getClass().getResourceAsStream("/elementAssets/Door.png"));

        } catch(IOException e){
            e.printStackTrace();
        }


    }

    /**
     * used to get the x position of the door
     * @return int - the x position of the door
     */
    //returns the position of the door
    public int getXPos(){
        return xPos;
    }

    /**
     * used to get the y position of the door
     * @return int - the y position of the door
     */
    public int getYPos(){
        return yPos;
    }

    /**
     * Used to find out which level the door is drawn on
     * @return int - which level the door is drawn on
     */
    public int getWindow(){
        return window;
    }

    /**
     * used to print the door at its correct position and level
     * @param g2 - Graphics2D object allows program to print to screen
     */
    public void draw(Graphics2D g2){
        g2.drawImage(doorImage, xPos+((window- currentGamePanel.getCurrentWindow())*768), yPos, 48, 48, null);
    }

}

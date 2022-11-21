package Characters;

import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This is the main player class
 * this is the character that the player plays
 * you can control it with wasd
 */
public class MainPlayer extends Character {

    BufferedImage characterImage;
    KeyHandler currentMainKeyHandler;
    int lives;
    boolean isProtected;

    /**
     * Constructor
     * @param mainGamePanel
     * @param mainKeyHandler
     * @param inputXPos
     * @param inputYPos
     * @param inputWindow
     */
    public MainPlayer(GamePanel mainGamePanel, KeyHandler mainKeyHandler, int inputXPos, int inputYPos, int inputWindow) {
        currentGamePanel = mainGamePanel;
        this.currentMainKeyHandler = mainKeyHandler;
        window = inputWindow;

        setDefaultValues();
        //position of the character
        xPos = inputXPos;
        yPos = inputYPos;
        hitBox = new Rectangle(xPos + 8, yPos + 8, 32, 32);//changed back to 32 from 25 as all of the collision math is based on 32
        isProtected = false;

        loadCharacterImage();
    }

    /**
     * loads image asset from resources folder
     */
    private void loadCharacterImage() {
        try {
            characterImage = ImageIO.read(getClass().getResourceAsStream("/characterAssets/mainCharacterRight2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * called from constructor to set object values
     * default values for lives, speed of player
     */
    private void setDefaultValues() {
        speedVertical = 4;
        speedHorizontal = 4;
        lives = 5;
        unChangedSpeed = 4;
        isHostile = false;
    }

    /**
     * if player gains shield
     */
    public void makeProtected() {
        isProtected = true;
    }

    /**
     * if player loses shield
     */
    public void makeUnProtected() {
        isProtected = false;
    }

    /**
     * returns if player has shield
     * @return
     */
    public boolean getIsProtected() {
        return isProtected;
    }

    /**
     * if player gains sword
     */
    public void makeHostile() {
        isHostile = true;
    }

    /**
     * if player loses sword
     */
    public void makeFriendly() {
        isHostile = false;
    }

    /**
     * ability to remove or gain lives
     * @param inputLives
     */
    public void changeLives(int inputLives) {
        lives += inputLives;
    }

    /**
     *
     * @return
     */
    public int getLives() {
        return lives;
    }

    /**
     *
     * @param inputLives
     */
    public void setLives(int inputLives) {
        lives = inputLives;
    }

    /**
     * returns the window player is on
     * @return
     */
    public int getWindow() {
        return window;
    }

    /**
     * sets window player is on
     * @param inputWindow
     */
    public void setWindow(int inputWindow) {
        //sets the window
        window = inputWindow;
        xPos = 48;
        yPos = 48;
        hitBox = new Rectangle(xPos + 12, yPos + 12, 25, 25);


    }

    /**
     * moves player based on keyboard inputs
     */
    public void move() {
        {
            if (currentMainKeyHandler.upPressed) {
                yPos -= speedVertical;
                hitBox.y -= speedVertical;

            } else if (currentMainKeyHandler.downPressed) {
                yPos += speedVertical;
                hitBox.y += speedVertical;

            }
            if (currentMainKeyHandler.leftPressed) {
                xPos -= speedHorizontal;
                hitBox.x -= speedHorizontal;
            } else if (currentMainKeyHandler.rightPressed) {
                xPos += speedHorizontal;
                hitBox.x += speedHorizontal;
            }
        }
    }

    /**
     * draws the character
     * @param g2
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(characterImage, xPos + ((window - currentGamePanel.getCurrentWindow()) * 768), yPos, 48, 48, null);
    }
}


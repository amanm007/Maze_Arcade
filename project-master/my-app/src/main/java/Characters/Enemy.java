package Characters;

import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * movable enemy character stuck within dirt boundary that inflict damage to character.
 */
public class Enemy extends Character {

    KeyHandler currentMainKeyHandler;
    //image for the enemy
    BufferedImage enemyImage;
    Character player;
    // dirrection its moving in
    boolean movingUp;
    boolean movingDown;
    boolean movingLeft;
    boolean movingRight;

    /**
     *
     * @param mainGamePanel
     * @param mainKeyHandler
     * @param inputXPos
     * @param inputYPos
     * @param inputWindow
     */
    public Enemy(GamePanel mainGamePanel, KeyHandler mainKeyHandler,int inputXPos, int inputYPos,int inputWindow){
        currentGamePanel = mainGamePanel;
        this.currentMainKeyHandler = mainKeyHandler;
        setDefaultValues();
        loadEnemyImage();
        getPlayer();
        window = inputWindow;
        xPos = inputXPos;
        yPos = inputYPos;
        hitBox = new Rectangle(xPos + 8, yPos + 8,32, 32 );
        isAlive = true;
        isHostile = true;
        isVisible = true;

    }

    /**
     * loads image asset from resources folder
     */
    private void loadEnemyImage(){
        try {
            enemyImage = ImageIO.read(getClass().getResourceAsStream("/characterAssets/enemyRightMovement.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * removes enemy from board.
     */
    public void kill(){
        isHostile = false;
        isVisible = false;
        isAlive = false;
    }

    /**
     * getter - return isVisible boolean
     * @return boolean return isVisible
     */
    public boolean getIsVisible(){
        return isVisible;
    }

    /**
     * called from constructor to set object values
     */
    public void setDefaultValues(){
        speedVertical = 2;
        speedHorizontal = 2;
        isHostile = true;
        isAlive = true;
        unChangedSpeed = 2;
        movingUp = false;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
    }

    /**
     *
     * @return
     */
    public boolean getMovingUp(){
        return movingUp;
    }

    /**
     *
     * @return
     */
    public boolean getMovingDown(){
        return  movingDown;
    }

    /**
     *
     * @return
     */
    public boolean getMovingLeft(){
        return movingLeft;
    }

    /**
     *
     * @return
     */
    public boolean getMovingRight(){
        return movingRight;
    }

    /**
     * setter - set enemy move direction to up
     */
    public void setMovingUp(){
        movingUp = true;
    }

    /**
     * setter - set enemy move direction to down
     */
    public void setMovingDown(){
        movingDown = true;
    }

    /**
     * setter - set enemy move direction to left
     */
    public void setMovingLeft(){
        movingLeft = true;
    }

    /**
     * setter - set enemy move direction to right
     */
    public void setMovingRight(){
        movingRight = true;
    }

    /**
     *
     */
    public void getPlayer(){
        player = currentGamePanel.getPlayer();
    }

    /**
     *
     * @return
     */
    public boolean getIsAlive(){
        return isAlive;

    }

    /**
     * uses player position to determine direction to mainPlayer and moves in players direction
     */
    public void move(){
        {
            int playerX = player.getXPos();
            int playerY = player.getYPos();
            if (xPos+window*768 > playerX){
                xPos-=speedHorizontal;
                hitBox.x-=speedHorizontal;
                movingLeft = true;
                movingRight = false;
            }
            else if (xPos+window*768 < playerX){
                xPos += speedHorizontal;
                hitBox.x+=speedHorizontal;
                movingLeft = false;
                movingRight = true;
            }
            if (yPos > playerY){
                yPos -=speedVertical;
                hitBox.y-=speedVertical;
                movingUp = true;
                movingDown = false;
            }
            else if (yPos < playerY){
                yPos+= speedVertical;
                hitBox.y+=speedVertical;
                movingUp = false;
                movingDown = true;
            }
        }
    }

    /**
     * draws enemy if alive
     * @param g2 Graphics2D object
     */
    public void draw(Graphics2D g2){

        if(isAlive) {
            g2.drawImage(enemyImage, xPos + ((window - currentGamePanel.getCurrentWindow()) * 768), yPos, 48, 48, null);
        }
    }
}



package TopHUD;

import Characters.Character;
import Characters.MainPlayer;
import Collectables.Coins;
import Collectables.Keys;
import Collectables.Shields;
import Collectables.Swords;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * displays information at the top of the screen
 */
public class HeadsUpDisplay {

    GamePanel currentGamePanel;
    int coins;
    boolean gotKey;
    int lives;
    boolean gotSword;
    boolean gotShield;
    int shieldHealtlh;
    BufferedImage coinImage;
    BufferedImage keyImage;
    BufferedImage swordImage;
    BufferedImage shieldImage;
    BufferedImage heartImage;
    BufferedImage damagedShield;
    String elapsedTime;
    String finalTime;


    /**
     * @param mainGamePanel
     */
    public HeadsUpDisplay(GamePanel mainGamePanel) {
        this.currentGamePanel = mainGamePanel;
        LoadImages();
    }

    /**
     * @return
     */
    public int getLives() {
        return lives;
    }

    /**
     * loads image assets from resources folder
     */
    public void LoadImages() {
        try {
            coinImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/coin.png"));
            keyImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/Key.png"));
            swordImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/Sword.png"));
            shieldImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/Shield.png"));
            heartImage = ImageIO.read(getClass().getResourceAsStream("/mapAssets/Hearts.png"));
            damagedShield = ImageIO.read(getClass().getResourceAsStream("/mapAssets/Broken_Shield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets internal values to inputs
     *
     * @param allCoins
     * @param allKeys
     * @param allSwords
     * @param allShields
     * @param mainCharacter
     */
    public void updateHud(Coins allCoins, Keys allKeys, Swords allSwords, Shields allShields, Character mainCharacter) {
        coins = allCoins.getCoinsAmount();
        gotKey = allKeys.getKeyCollected();
        lives = ((MainPlayer) mainCharacter).getLives();
        gotSword = allSwords.getSwordCollected();
        gotShield = allShields.getShieldCollected();
        shieldHealtlh = allShields.getShieldHealth();

    }

    /**
     * returns elapsed time in string form
     *
     * @return
     */
    public String getTime() {
        return finalTime;
    }

    /**
     * draws elements at top of screen
     *
     * @param g2
     * @param gameTimer
     * @return
     */
    public boolean draw(Graphics2D g2, GameTimer gameTimer, int currentScreen) {
        elapsedTime = gameTimer.getTime();
        if (lives > 0 && currentScreen != 5 && currentScreen != 6) {
            finalTime = elapsedTime;
        }

        g2.setFont(new Font("Ariel", Font.BOLD, 25));
        g2.drawImage(coinImage, 0, 5, 48, 48, null);
        g2.setColor(Color.WHITE);
        g2.drawString("x " + coins, 40, 35);

        if (gotKey) {
            g2.drawImage(keyImage, 70, -5, 70, 70, null);// If the player collects the key, gets displayed in the HUD
        }
        if (gotSword) {
            g2.drawImage(swordImage, 120, 5, 48, 48, null);// If the player collects the sword, gets displayed in the HUD

        }

        if (gotShield) {

            if (shieldHealtlh == 2) {
                g2.drawImage(shieldImage, 155, 0, 48, 48, null);// If the player collects the shield, gets displayed in the HUD

            } else {
                g2.drawImage(damagedShield, 155, 0, 48, 48, null);// displays if shield gets cracked

            }
        }

        g2.drawString(finalTime, 200, 35);

        g2.setColor(Color.RED);
        if (lives >= 5) {

            g2.drawImage(heartImage, 280, 0, 48, 48, null);
            g2.drawImage(heartImage, 320, 0, 48, 48, null);
            g2.drawImage(heartImage, 360, 0, 48, 48, null);

        } else if (lives >= 3) {
            g2.drawImage(heartImage, 280, 0, 48, 48, null);
            g2.drawImage(heartImage, 320, 0, 48, 48, null);


        } else if (lives >= 1) {
            g2.drawImage(heartImage, 280, 0, 48, 48, null);

        } else {
            return true;
        }
        return false;

    }

    public void resetCoins(Coins coinsHeld){
        coinsHeld.setCoinsAmount(0);
    }
}

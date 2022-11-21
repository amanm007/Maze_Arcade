package worldMap;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * creates and displays map background from mapBlueprint.txt file
 * -----------------------------------------------------------
 * Main, GamePanel, and KeyHandler class were adapted from an online tutorial series videos 1-3:
 * https://www.youtube.com/watch?v=om59cwR7psI
 * Author: RyiSnow
 * Classes: MainMap's blueprint txt file idea was received from this series as well but implemented differently
 */
public class MainMap {
    GamePanel currentGamePanel;
    BufferedImage grass;
    BufferedImage wall;
    BufferedImage pit;

    /**
     * @param mainGamePanel
     * @throws IOException
     */
    public MainMap(GamePanel mainGamePanel) throws IOException {

        this.currentGamePanel = mainGamePanel;

        setAssetTextures();

    }

    /**
     * loads asset textures from resources folder
     * ---------------------------------------------
     * learned and adapted how to read in textures from:
     * online tutorial series videos 1-3:
     * https://www.youtube.com/watch?v=om59cwR7psI
     * Author: RyiSnow
     */
    public void setAssetTextures() {

        try {
            grass = ImageIO.read(getClass().getResourceAsStream("/mapAssets/ground.png"));

            wall = ImageIO.read(getClass().getResourceAsStream("/mapAssets/wall.png"));

            pit = ImageIO.read(getClass().getResourceAsStream("/mapAssets/bluedirt.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * tiles assets to create map background
     *
     * @param g2 Graphics2D object allows program to print to screen
     * @throws IOException
     */
    public void createWholeMap(Graphics2D g2) throws IOException {

        int whichAsset = 0;
        InputStream bluePrintFile = getClass().getResourceAsStream("/mapBluePrint/mapBlueprint.txt");
        BufferedReader readBlueprint = new BufferedReader(new InputStreamReader(bluePrintFile));

        for (int i = 0; i < 16; i++) {
            String row = readBlueprint.readLine();

            for (int x = 0; x < currentGamePanel.getTotalNumberWindows() * 16; x++) {

                if (row.charAt(x) == '0') {
                    whichAsset = 1;
                } else if (row.charAt(x) == '1') {
                    whichAsset = 2;
                } else if (row.charAt(x) == '2') {
                    whichAsset = 3;

                }
                draw(g2, (x * 48), (i * 48), whichAsset);
            }

        }
    }

    /**
     * draw individual background asset at location.
     * @param g2 Graphics2D object allows program to print to screen
     * @param xCord where along x the new map assets needs to placed
     * @param YCord where along y the new map assets needs to placed
     * @param whichAsset 1:ground 2:wall 3:dirt pit
     */
    public void draw(Graphics2D g2, int xCord, int YCord, int whichAsset) {

        if (whichAsset == 1) {
            g2.drawImage(grass, xCord - currentGamePanel.getCurrentWindow() * 768, YCord, currentGamePanel.tileSize, currentGamePanel.tileSize, null);
        } else if (whichAsset == 2) {
            g2.drawImage(wall, xCord - currentGamePanel.getCurrentWindow() * 768, YCord, currentGamePanel.tileSize, currentGamePanel.tileSize, null);
        } else if (whichAsset == 3) {
            g2.drawImage(pit, xCord - currentGamePanel.getCurrentWindow() * 768, YCord, currentGamePanel.tileSize, currentGamePanel.tileSize, null);
        }
    }
}

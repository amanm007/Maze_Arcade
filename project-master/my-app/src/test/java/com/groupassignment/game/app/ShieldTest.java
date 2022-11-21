package com.groupassignment.game.app;

import Characters.Character;
import Characters.CharacterFactory;
import Characters.MainPlayer;
import Collectables.Shield;
import Collectables.Shields;
import main.Collision;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class ShieldTest
{

    @Test
    public void testShieldXPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Shield shield = new Shield(testGamePanel, 0, 0, 0);

        int xPosResult = shield.getXPos();
        assertSame(0, xPosResult);

        Shield shield2 = new Shield(testGamePanel, 15, 0, 0);

        xPosResult = shield2.getXPos();
        assertSame(15, xPosResult);

    }

    @Test
    public void testShieldYPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Shield shield = new Shield(testGamePanel, 0, 0, 0);

        int yPosResult = shield.getYPos();
        assertSame(0, yPosResult);

        Shield shield2 = new Shield(testGamePanel, 0, 15, 0);

        yPosResult = shield2.getYPos();
        assertSame(15, yPosResult);

    }

    @Test
    public void testShieldVisibility() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Shield shield = new Shield(testGamePanel, 0, 0, 0);

        boolean visibilityResult = shield.getVisibility();
        assertSame(true, visibilityResult);

        shield.setInvisible();

        visibilityResult = shield.getVisibility();
        assertSame(false, visibilityResult);

        shield.setVisible();

        visibilityResult = shield.getVisibility();
        assertSame(true, visibilityResult);



    }

    @Test
    public void testShieldCollected() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Shield shield = new Shield(testGamePanel, 0, 0, 0);

        boolean isCollectedResult = shield.getShieldCollected();
        assertSame(false, isCollectedResult);

        shield.shieldCollected();
        isCollectedResult = shield.getShieldCollected();
        assertSame(true, isCollectedResult);

        shield.removeShield();
        isCollectedResult = shield.getShieldCollected();
        assertSame(false, isCollectedResult);

    }

    @Test
    public void testShieldHealthAndHit() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Shield shield = new Shield(testGamePanel, 0, 0, 0);

        shield.shieldCollected();
        boolean isCollectedResult = shield.getShieldCollected();
        assertSame(true, isCollectedResult);

        int shieldHealthResult = shield.getShieldHealth();
        assertSame(2, shieldHealthResult);

        shield.shieldHit();
        shieldHealthResult = shield.getShieldHealth();
        assertSame(1, shieldHealthResult);

        shield.shieldHit();
        shieldHealthResult = shield.getShieldHealth();
        assertSame(0, shieldHealthResult);

    }

    @Test
    public void testShieldGetWindow() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Shield shield = new Shield(testGamePanel, 0, 0, 0);

        int whichWindowResult = shield.getWindow();
        assertSame(0, whichWindowResult);

        Shield shield2 = new Shield(testGamePanel, 0, 0, 02);

        whichWindowResult = shield2.getWindow();
        assertSame(2, whichWindowResult);

    }


    @Test
    public void testMainPlayerCollisionWithShield() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);
        Shields shields = new Shields();

        Shield shield = new Shield(testGamePanel,400,300,6);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 300, 300, 6);


        boolean isCollectedResult = shield.getVisibility();
        assertSame(true, isCollectedResult);

        testKeyHandler.manualKeyPress(4);

        for (int i=0; i<=120;i++) {
            ((MainPlayer) mainPlayer).move();
            collisionTracker.shieldCollision(mainPlayer, shield, shields);
        }
        isCollectedResult = shield.getVisibility();
        assertSame(false, isCollectedResult);
    }
}

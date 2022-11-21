package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import Characters.Character;
import Characters.CharacterFactory;
import Characters.MainPlayer;
import Collectables.*;
import main.Collision;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;
import java.io.IOException;

public class SwordTest
{

    @Test
    public void testSwordXPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Sword sword = new Sword(testGamePanel, 0, 0, 0);

        int xPosResult = sword.getXPos();
        assertSame(0, xPosResult);

        Sword sword2 = new Sword(testGamePanel, 15, 0, 0);

        xPosResult = sword2.getXPos();
        assertSame(15, xPosResult);

    }

    @Test
    public void testSwordYPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Sword sword = new Sword(testGamePanel, 0, 0, 0);

        int yPosResult = sword.getYPos();
        assertSame(0, yPosResult);

        Sword sword2 = new Sword(testGamePanel, 0, 15, 0);

        yPosResult = sword2.getYPos();
        assertSame(15, yPosResult);

    }

    @Test
    public void testSwordVisibility() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Sword sword = new Sword(testGamePanel, 0, 0, 0);

        boolean visibilityResult = sword.getVisibility();
        assertSame(true, visibilityResult);

        sword.setInvisible();

        visibilityResult = sword.getVisibility();
        assertSame(false, visibilityResult);

        sword.setVisible();

        visibilityResult = sword.getVisibility();
        assertSame(true, visibilityResult);



    }

    @Test
    public void testSwordCollected() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Sword sword = new Sword(testGamePanel, 0, 0, 0);

        boolean isCollectedResult = sword.getSwordCollected();
        assertSame(false, isCollectedResult);

        sword.swordCollected();
        isCollectedResult = sword.getSwordCollected();
        assertSame(true, isCollectedResult);

        sword.removeSword();
        isCollectedResult = sword.getSwordCollected();
        assertSame(false, isCollectedResult);

    }

    @Test
    public void testSwordGetWindow() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Sword sword = new Sword(testGamePanel, 0, 0, 0);

        int whichWindowResult = sword.getWindow();
        assertSame(0, whichWindowResult);

        Sword sword2 = new Sword(testGamePanel, 0, 0, 02);

        whichWindowResult = sword2.getWindow();
        assertSame(2, whichWindowResult);
    }

    @Test
    public void testMainPlayerCollisionWithSword() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);
        Swords swords = new Swords();

        Sword sword = new Sword(testGamePanel,400,300,6);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 300, 300, 6);


        boolean isCollectedResult = sword.getVisibility();
        assertSame(true, isCollectedResult);

        testKeyHandler.manualKeyPress(4);

        for (int i=0; i<=120;i++) {
            ((MainPlayer) mainPlayer).move();
            collisionTracker.swordCollision(mainPlayer, sword, swords);
        }
        isCollectedResult = sword.getVisibility();
        assertSame(false, isCollectedResult);
    }
}

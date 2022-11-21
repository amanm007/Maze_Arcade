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

public class KeyTest
{

    @Test
    public void testKeyXPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Key key = new Key(testGamePanel, 0, 0, 0);

        int xPosResult = key.getXPos();
        assertSame(0, xPosResult);

        Key key2 = new Key(testGamePanel, 15, 0, 0);

        xPosResult = key2.getXPos();
        assertSame(15, xPosResult);

    }

    @Test
    public void testKeyYPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Key key = new Key(testGamePanel, 0, 0, 0);

        int yPosResult = key.getYPos();
        assertSame(0, yPosResult);

        Key key2 = new Key(testGamePanel, 0, 15, 0);

        yPosResult = key2.getYPos();
        assertSame(15, yPosResult);

    }

    @Test
    public void testKeyVisibility() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Key key = new Key(testGamePanel, 0, 0, 0);

        boolean visibilityResult = key.getVisibility();
        assertSame(true, visibilityResult);

        key.setInvisible();

        visibilityResult = key.getVisibility();
        assertSame(false, visibilityResult);

        key.setVisible();

        visibilityResult = key.getVisibility();
        assertSame(true, visibilityResult);



    }

    @Test
    public void testKeyCollected() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Key key = new Key(testGamePanel, 0, 0, 0);

        boolean isCollectedResult = key.getKeyCollected();
        assertSame(false, isCollectedResult);

        key.keyCollected();
        isCollectedResult = key.getKeyCollected();
        assertSame(true, isCollectedResult);

        key.removeKey();
        isCollectedResult = key.getKeyCollected();
        assertSame(false, isCollectedResult);

    }

    @Test
    public void testKeyGetWindow() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Key key = new Key(testGamePanel, 0, 0, 0);

        int whichWindowResult = key.getWindow();
        assertSame(0, whichWindowResult);

        Key key2 = new Key(testGamePanel, 0, 0, 02);

        whichWindowResult = key2.getWindow();
        assertSame(2, whichWindowResult);
    }

    @Test
    public void testMainPlayerCollisionWithCoin() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        testGamePanel.setCurrentWindow(6);
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);
        Keys keys  = new Keys();

        Key key = new Key(testGamePanel,400,300,6);
        key.setVisible();
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 300, 300, 6);


        boolean isCollectedResult = key.getVisibility();
        assertSame(true, isCollectedResult);

        testKeyHandler.manualKeyPress(4);
        for (int i=0; i<=120;i++) {
            ((MainPlayer) mainPlayer).move();
            collisionTracker.keyCollision(mainPlayer, key, keys);
        }
        isCollectedResult = key.getVisibility();
        assertSame(false, isCollectedResult);
    }


}

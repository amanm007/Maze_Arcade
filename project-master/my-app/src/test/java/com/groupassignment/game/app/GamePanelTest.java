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

public class GamePanelTest
{

    @Test
    public void testGamePanelSetWindow() throws IOException {

        GamePanel testGamePanel = new GamePanel();

        int gameWindowResult = testGamePanel.getCurrentWindow();
        assertSame(0, gameWindowResult);

        testGamePanel.setCurrentWindow(2);
        gameWindowResult = testGamePanel.getCurrentWindow();
        assertSame(2, gameWindowResult);

    }

    @Test
    public void testGameTotalGamePanels() throws IOException {

        GamePanel testGamePanel = new GamePanel();

        int totalGamePanelsResult = testGamePanel.getTotalNumberWindows();
        assertSame(7, totalGamePanelsResult);

    }

    @Test
    public void testGameScore() throws IOException {

        GamePanel testGamePanel = new GamePanel();

        int totalScoreResult = testGamePanel.getScore();
        assertSame(0, totalScoreResult);

        testGamePanel.addScore(5);
        totalScoreResult = testGamePanel.getScore();
        assertSame(5, totalScoreResult);

        testGamePanel.addScore(3);
        totalScoreResult = testGamePanel.getScore();
        assertSame(8, totalScoreResult);

    }


    @Test
    public void testRequestSetCurrentWindowKeys() throws IOException {

        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        testGamePanel.setCurrentWindow(6);
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = testGamePanel.getCollisionHandler();
        Keys keys  = testGamePanel.getKeys();

        Key key = new Key(testGamePanel,200,300,6);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 300, 300, 6);


        int currentWindow = testGamePanel.getCurrentWindow();
        assertSame(6, currentWindow);

        testGamePanel.requestSetCurrentWindow(1);

        currentWindow = testGamePanel.getCurrentWindow();
        assertSame(6, currentWindow);

        testKeyHandler.manualKeyRelease();
        testKeyHandler.manualKeyPress(3);
        for (int i=0; i<=240;i++) {
            ((MainPlayer) mainPlayer).move();
            collisionTracker.keyCollision(mainPlayer, key, keys);
        }

        testGamePanel.requestSetCurrentWindow(1);

        currentWindow = testGamePanel.getCurrentWindow();
        assertSame(1, currentWindow);

    }
}
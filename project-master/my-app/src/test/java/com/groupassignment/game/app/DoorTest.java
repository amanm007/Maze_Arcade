package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import Characters.Character;
import Characters.CharacterFactory;
import Characters.MainPlayer;
import Collectables.*;
import Elements.Door;
import main.Collision;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;
import java.io.IOException;

public class DoorTest
{

    @Test
    public void testDoorXPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Door door = new Door(testGamePanel, 0, 0, 0, 1);

        int xPosResult = door.getXPos();
        assertSame(0, xPosResult);

        Door door2 = new Door(testGamePanel, 15, 0, 0, 1);

        xPosResult = door2.getXPos();
        assertSame(15, xPosResult);

    }

    @Test
    public void testDoorYPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Door door = new Door(testGamePanel, 0, 0, 0, 1);

        int yPosResult = door.getYPos();
        assertSame(0, yPosResult);

        Door door2 = new Door(testGamePanel, 0, 15, 0, 1);

        yPosResult = door2.getYPos();
        assertSame(15, yPosResult);

    }

    @Test
    public void testDoorGetWindow() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Door door = new Door(testGamePanel, 0, 0, 0, 1);

        int whichWindowResult = door.getWindow();
        assertSame(0, whichWindowResult);

        Door door2 = new Door(testGamePanel, 0, 0, 2, 3);

        whichWindowResult = door2.getWindow();
        assertSame(2, whichWindowResult);

    }

    @Test
    public void testDoorGetDestinationWindow() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Door door = new Door(testGamePanel, 0, 0, 0, 1);

        int whichWindowResult = door.getDestinationWindow();
        assertSame(1, whichWindowResult);

        Door door2 = new Door(testGamePanel, 0, 0, 2, 3);

        whichWindowResult = door2.getDestinationWindow();
        assertSame(3, whichWindowResult);
    }

    @Test
    public void testMainPlayerCollisionWithDoor() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        testGamePanel.setCurrentWindow(6);
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = testGamePanel.getCollisionHandler();
        Keys keys  = testGamePanel.getKeys();

        Key key = new Key(testGamePanel,200,300,6);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 300, 300, 6);

        Door door = new Door(testGamePanel, 400, 300, 6, 1);

        int currentWindow = testGamePanel.getCurrentWindow();
        assertSame(6, currentWindow);

        testKeyHandler.manualKeyPress(4);
        for (int i=0; i<=120;i++) {
            ((MainPlayer) mainPlayer).move();

            collisionTracker.keyCollision(mainPlayer, key, keys);
            collisionTracker.doorCollision(mainPlayer,door);
        }

        currentWindow = testGamePanel.getCurrentWindow();
        assertSame(6, currentWindow);

        testKeyHandler.manualKeyRelease();
        testKeyHandler.manualKeyPress(3);
        for (int i=0; i<=240;i++) {
            ((MainPlayer) mainPlayer).move();
            collisionTracker.keyCollision(mainPlayer, key, keys);
            collisionTracker.doorCollision(mainPlayer,door);
        }

        currentWindow = testGamePanel.getCurrentWindow();
        assertSame(6, currentWindow);

        testKeyHandler.manualKeyRelease();
        testKeyHandler.manualKeyPress(4);
        for (int i=0; i<=240;i++) {
            ((MainPlayer) mainPlayer).move();
            collisionTracker.keyCollision(mainPlayer, key, keys);
            collisionTracker.doorCollision(mainPlayer,door);
        }

        currentWindow = testGamePanel.getCurrentWindow();
        assertSame(1, currentWindow);

    }
}

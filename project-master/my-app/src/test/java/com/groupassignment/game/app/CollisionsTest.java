package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import Characters.Character;
import Characters.CharacterFactory;
import Characters.Enemy;
import Characters.MainPlayer;
import main.Collision;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class CollisionsTest
{
    @Test
    public void testMainPlayerCollisionInCenterOfTileGoingUp() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(1);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 48, 0);

        int collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(0, collisionSpeedResult);

    }

    @Test
    public void testMainPlayerCollisionInCenterOfTileGoingDown() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(2);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 672, 0);

        int collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(0, collisionSpeedResult);

    }

    @Test
    public void testMainPlayerCollisionInCenterOfTileGoingLeft() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(3);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 48, 0);

        int collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(0, collisionSpeedResult);

    }

    @Test
    public void testMainPlayerCollisionInCenterOfTileGoingRight() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(4);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 672, 48, 0);

        int collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(0, collisionSpeedResult);

    }

    @Test
    public void testMainPlayerCollisionInBetweenTilesGoingUp() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(1);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 72, 48, 0);

        int collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(0, collisionSpeedResult);

    }

    @Test
    public void testMainPlayerCollisionInBetweenTilesGoingDown() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(2);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 72, 672, 0);

        int collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedVertical();
        assertSame(0, collisionSpeedResult);

    }

    @Test
    public void testMainPlayerCollisionInBetweenTilesGoingLeft() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(3);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 72, 0);

        int collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(0, collisionSpeedResult);

    }

    @Test
    public void testMainPlayerCollisionInBetweenTilesGoingRight() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);

        testKeyHandler.manualKeyPress(4);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 672, 72, 0);

        int collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(4, collisionSpeedResult);

        ((MainPlayer)mainPlayer).move();
        collisionTracker.checkForCollisionsPositionsPlayer(mainPlayer);

        collisionSpeedResult = mainPlayer.getSpeedHorizontal();
        assertSame(0, collisionSpeedResult);

    }




}

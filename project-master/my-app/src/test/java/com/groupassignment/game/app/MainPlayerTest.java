package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import Characters.Character;
import Characters.CharacterFactory;
import Characters.MainPlayer;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;
import java.io.IOException;

public class MainPlayerTest
{

    @Test
    public void testMainPlayerIsHostile() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);

        boolean hostileResult = mainPlayer.getIsHostile();
        assertSame(false, hostileResult);

        ((MainPlayer)mainPlayer).makeHostile();
        hostileResult = mainPlayer.getIsHostile();
        assertSame(true, hostileResult);

        ((MainPlayer)mainPlayer).makeFriendly();
        hostileResult = mainPlayer.getIsHostile();
        assertSame(false, hostileResult);

    }

    @Test
    public void testMainPlayerXPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);

        int xPosResult = mainPlayer.getXPos();
        assertSame(0, xPosResult);

    }

    @Test
    public void testMainPlayerYPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);

        int yPosResult = mainPlayer.getYPos();
        assertSame(0, yPosResult);

    }

    @Test
    public void testMainPlayerIsProtected() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);

        boolean protectedResult = ((MainPlayer)mainPlayer).getIsProtected();
        assertSame(false, protectedResult);

        ((MainPlayer)mainPlayer).makeProtected();
        protectedResult = ((MainPlayer) mainPlayer).getIsProtected();
        assertSame(true, protectedResult);

        ((MainPlayer)mainPlayer).makeUnProtected();
        protectedResult = ((MainPlayer) mainPlayer).getIsProtected();
        assertSame(false, protectedResult);

    }

    @Test
    public void testMainPlayerHorizontalSpeed() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);

        int horizontalSpeedResult = ((MainPlayer)mainPlayer).getSpeedHorizontal();
        assertSame(4, horizontalSpeedResult);

        ((MainPlayer)mainPlayer).changeHorizontalSpeed(0);
        horizontalSpeedResult = ((MainPlayer)mainPlayer).getSpeedHorizontal();
        assertSame(0, horizontalSpeedResult);


    }

    @Test
    public void testMainPlayerVerticalSpeed() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);

        int verticalSpeedResult = ((MainPlayer)mainPlayer).getSpeedVertical();
        assertSame(4, verticalSpeedResult);


        ((MainPlayer)mainPlayer).changeVerticalSpeed(0);
        verticalSpeedResult = ((MainPlayer)mainPlayer).getSpeedVertical();
        assertSame(0, verticalSpeedResult);

    }

    @Test
    public void testMainPlayerLives() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);

        int livesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, livesResult);


        ((MainPlayer)mainPlayer).setLives(10);
        livesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(10, livesResult);

    }




}


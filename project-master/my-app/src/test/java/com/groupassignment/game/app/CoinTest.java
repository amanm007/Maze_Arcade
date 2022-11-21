package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import Characters.Character;
import Characters.CharacterFactory;
import Characters.MainPlayer;
import Collectables.Coin;
import Collectables.Coins;
import Collectables.Shield;
import Collectables.Shields;
import main.Collision;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class CoinTest
{

    @Test
    public void testCoinXPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Coin coin = new Coin(testGamePanel, 0, 0);

        int xPosResult = coin.getXPos();
        assertSame(0, xPosResult);

        Coin coin2 = new Coin(testGamePanel, 15, 0);

        xPosResult = coin2.getXPos();
        assertSame(15, xPosResult);

    }

    @Test
    public void testCoinYPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Coin coin = new Coin(testGamePanel, 0, 0);

        int yPosResult = coin.getYPos();
        assertSame(0, yPosResult);

        Coin coin2 = new Coin(testGamePanel, 0, 15);

        yPosResult = coin2.getYPos();
        assertSame(15, yPosResult);

    }

    @Test
    public void testCoinVisibility() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Coin coin = new Coin(testGamePanel, 0, 0);

        boolean visibilityResult = coin.getVisibility();
        assertSame(false, visibilityResult);

        coin.setVisible();

        visibilityResult = coin.getVisibility();
        assertSame(true, visibilityResult);

        coin.setInvisible();

        visibilityResult = coin.getVisibility();
        assertSame(false, visibilityResult);

    }

    @Test
    public void testCoinCount() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Coin coin = new Coin(testGamePanel, 0, 0);

        int coinAmount = coin.getCoinsAmount();
        assertSame(0, coinAmount);

        coin.addCoin();

        coinAmount = coin.getCoinsAmount();
        assertSame(1, coinAmount);

        coin.addCoin();

        coinAmount = coin.getCoinsAmount();
        assertSame(2, coinAmount);
    }

    @Test
    public void testMainPlayerCollisionWithCoin() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();
        testGamePanel.setCurrentWindow(6);
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Collision collisionTracker = new Collision(testKeyHandler, testGamePanel);
        Coins coins = new Coins();

        Coin coin = new Coin(testGamePanel,400,300);
        coin.setVisible();
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 300, 300, 6);


        boolean isCollectedResult = coin.getVisibility();
        assertSame(true, isCollectedResult);

        testKeyHandler.manualKeyPress(4);

        for (int i=0; i<=120;i++) {
            ((MainPlayer) mainPlayer).move();
            collisionTracker.coinCollision(mainPlayer, coin, coins);
        }
        isCollectedResult = coin.getVisibility();
        assertSame(false, isCollectedResult);
    }

    @Test
    public void testCoinTimeout() throws IOException {
        GamePanel testGamePanel = new GamePanel();

        Coin coin = new Coin(testGamePanel, 0, 0);


        coin.setVisible();
        boolean visibilityResult = coin.getVisibility();
        assertSame(true, visibilityResult);


        coin.setTimer(100);
        for(int i=0;i<101;i++){
            coin.rollDice();
        }

        visibilityResult = coin.getVisibility();
        assertSame(false, visibilityResult);

    }

    @Test
    public void testSetCoinAmount() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Coin coin = new Coin(testGamePanel, 0, 0);

        int coinAmount = coin.getCoinsAmount();
        assertSame(0, coinAmount);

        coin.addCoin();

        coinAmount = coin.getCoinsAmount();
        assertSame(1, coinAmount);

        coin.setCoinsAmount(0);

        coinAmount = coin.getCoinsAmount();
        assertSame(0, coinAmount);

        coin.setCoinsAmount(5);

        coinAmount = coin.getCoinsAmount();
        assertSame(5, coinAmount);
    }



}

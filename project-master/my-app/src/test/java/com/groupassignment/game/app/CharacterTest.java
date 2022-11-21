package com.groupassignment.game.app;

import Characters.CharacterFactory;
import Characters.Enemy;
import Characters.MainPlayer;
import Collectables.Coin;
import Collectables.Coins;
import main.Collision;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertSame;

public class CharacterTest {

    @Test
    public void testCharacterFactoryEnemy() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        assertSame(charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 0, 0, 0).getClass(),new Enemy(testGamePanel, testKeyHandler, 0, 0, 0).getClass());

    }

    @Test
    public void testCharacterFactoryMainPlayer() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        assertSame(charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0).getClass(),new MainPlayer(testGamePanel, testKeyHandler, 0, 0, 0).getClass());

    }
}

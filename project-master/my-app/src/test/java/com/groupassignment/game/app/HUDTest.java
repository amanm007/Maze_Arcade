package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import Characters.Character;
import Characters.CharacterFactory;
import Collectables.*;
import Elements.Fire;
import TopHUD.HeadsUpDisplay;
import main.Damage;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class HUDTest {

    @Test
    public void testResetCoins() throws IOException {
        GamePanel testGamePanel = new GamePanel();
        HeadsUpDisplay HUD = new HeadsUpDisplay(testGamePanel);
        Coins allCoins = new Coins();

        assertSame(0, allCoins.getCoinsAmount());

        allCoins.addCoin();

        assertSame(1, allCoins.getCoinsAmount());

        HUD.resetCoins(allCoins);

        assertSame(0, allCoins.getCoinsAmount());

    }

    @Test
    public void testGetLives() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        Fire[] fires;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        HeadsUpDisplay HUD = new HeadsUpDisplay(testGamePanel);
        Damage damageTracker = new Damage(testGamePanel);
        Shields allShields = new Shields();
        Swords allSwords = new Swords();
        Keys allKeys = new Keys();
        Coins allCoins = new Coins();
        fires = new Fire[1];

        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 0, 0, 0);
        fires[0] = new Fire(testGamePanel, 0, 0, 0);

        HUD.updateHud(allCoins, allKeys, allSwords, allShields, mainPlayer);
        assertSame(5, HUD.getLives());

        damageTracker.checkDamageFire(mainPlayer, fires, allShields);

        HUD.updateHud(allCoins, allKeys, allSwords, allShields, mainPlayer);
        assertSame(3, HUD.getLives());

    }
}

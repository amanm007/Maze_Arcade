package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import Characters.Character;
import Characters.CharacterFactory;
import Elements.Fire;
import main.GamePanel;
import org.junit.Test;
import java.io.IOException;

public class FireTest
{

    @Test
    public void testFireXPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Fire fire = new Fire(testGamePanel, 0, 0, 0);

        int xPosResult = fire.getXPos();
        assertSame(0, xPosResult);

        Fire fire2 = new Fire(testGamePanel, 15, 0, 0);

        xPosResult = fire2.getXPos();
        assertSame(15, xPosResult);

    }

    @Test
    public void testFireYPos() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Fire fire = new Fire(testGamePanel, 0, 0, 0);

        int yPosResult = fire.getYPos();
        assertSame(0, yPosResult);

        Fire fire2 = new Fire(testGamePanel, 0, 15, 0);

        yPosResult = fire2.getYPos();
        assertSame(15, yPosResult);

    }

    @Test
    public void testFireGetWindow() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Fire fire = new Fire(testGamePanel, 0, 0, 0);

        int whichWindowResult = fire.getWindow();
        assertSame(0, whichWindowResult);

        Fire fire2 = new Fire(testGamePanel, 0, 0, 2);

        whichWindowResult = fire2.getWindow();
        assertSame(2, whichWindowResult);

    }

    @Test
    public void testFireVisibility() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        GamePanel testGamePanel = new GamePanel();

        Fire fire = new Fire(testGamePanel, 0, 0, 0);

        boolean visibilityResult = fire.getVisibility();
        assertSame(true, visibilityResult);

        fire.setInvisible();

        visibilityResult = fire.getVisibility();
        assertSame(false, visibilityResult);

        fire.setVisible();

        visibilityResult = fire.getVisibility();
        assertSame(true, visibilityResult);



    }


}

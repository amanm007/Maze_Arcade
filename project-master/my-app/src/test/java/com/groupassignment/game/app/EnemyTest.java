package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import Characters.Character;
import Characters.CharacterFactory;
import Characters.Enemy;
import main.GamePanel;
import main.KeyHandler;
import org.junit.Test;
import java.io.IOException;

public class EnemyTest
{


    @Test
    public void testEnemyIsAlive() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character enemy;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        enemy = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 0, 0, 0);

        boolean isAliveResult = ((Enemy)enemy).getIsAlive();
        assertSame(true, isAliveResult);


    }

    @Test
    public void testEnemyKill() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character enemy;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        enemy = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 0, 0, 0);

        boolean isAliveResult = ((Enemy)enemy).getIsAlive();
        assertSame(true, isAliveResult);

        boolean isHostileResult = enemy.getIsHostile();
        assertSame(true, isHostileResult);

        boolean isVisibleResult = ((Enemy) enemy).getIsVisible();
        assertSame(true, isVisibleResult);

        ((Enemy)enemy).kill();

         isAliveResult = ((Enemy)enemy).getIsAlive();
        assertSame(false, isAliveResult);

         isHostileResult = enemy.getIsHostile();
        assertSame(false, isHostileResult);

         isVisibleResult = ((Enemy) enemy).getIsVisible();
        assertSame(false, isVisibleResult);

    }

    @Test
    public void testEnemyIsHostile() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character enemy;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        enemy = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 0, 0, 0);

        boolean isHostileResult = enemy.getIsHostile();
        assertSame(true, isHostileResult);

    }

    @Test
    public void testEnemyIsVisible() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character enemy;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        enemy = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 0, 0, 0);

        boolean isHostileVisible = ((Enemy)enemy).getIsVisible();
        assertSame(true, isHostileVisible);

    }

    @Test
    public void testEnemyMoving() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character enemy;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);

        enemy = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 0, 0, 0);

        boolean isMovingUp = ((Enemy)enemy).getMovingUp();
        assertSame(false, isMovingUp);

        boolean isMovingDown = ((Enemy)enemy).getMovingDown();
        assertSame(false, isMovingDown);

        boolean isMovingLeft = ((Enemy)enemy).getMovingLeft();
        assertSame(false, isMovingLeft);

        boolean isMovingRight = ((Enemy)enemy).getMovingRight();
        assertSame(false, isMovingRight);

    }

}

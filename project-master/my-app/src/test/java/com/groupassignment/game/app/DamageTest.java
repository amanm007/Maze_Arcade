package com.groupassignment.game.app;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import Characters.Character;
import Characters.CharacterFactory;
import Characters.Enemy;
import Characters.MainPlayer;
import Collectables.*;
import Elements.Door;
import Elements.Fire;
import main.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class DamageTest
{

    @Test
    public void testEnemyDamagesRegularPlayer() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        Character[] enemys;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Damage damageTracker = new Damage(testGamePanel);
        Shields shield = new Shield(testGamePanel, 0, 0, 0);
        Swords sword = new Sword(testGamePanel, 0, 0, 0);
        enemys = new Character[1];

        testKeyHandler.manualKeyPress(2);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 48, 0);
        enemys[0] = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 48, 96, 0);

        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);

        //player hasn't encountered enemy yet. Health should be full
        int damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);

        //Move player within the collision detection of the enemy
        for(int i = 0; i < 4; i++){
            ((MainPlayer)mainPlayer).move();
        }
        testKeyHandler.manualKeyRelease();

        //Player has made contact with enemy health should go down 2
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(3, damageLivesResult);

        //Loop through the 59/60 of the 1 second enemy cool down
        for(int i = 0; i < 59; i++){
            damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        }

        //1/60 of 1 second cool down still remains so health should remain the same as previous assert
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(3, damageLivesResult);

        //Go through the final 1/60 of the 1 second cool. Player is still within enemies hitbox so player health goes down 2
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(1, damageLivesResult);

        //loop through the 59/60 of the 1 second enemy cool down again
        for(int i = 0; i < 59; i++){
            damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        }

        //Move player out of enemy hitbox and go through the final 1/60 of one second cool down. Player is now outside of
        //enemy hitbox so health stays the same as previous assert
        testKeyHandler.manualKeyPress(1);
        ((MainPlayer)mainPlayer).move();
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(1, damageLivesResult);
        testKeyHandler.manualKeyRelease();

        //Move player back into the enemy hitbox. Cool down is over so player health goes down 2
        testKeyHandler.manualKeyPress(2);
        ((MainPlayer)mainPlayer).move();
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(-1, damageLivesResult);

    }

    @Test
    public void testEnemyDamagesPlayerWithSword() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        Character[] enemys;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Damage damageTracker = new Damage(testGamePanel);
        Shields shield = new Shield(testGamePanel, 0, 0, 0);
        Swords sword = new Sword(testGamePanel, 0, 0, 0);
        enemys = new Character[2];
        sword.swordCollected();

        testKeyHandler.manualKeyPress(2);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 48, 0);
        enemys[0] = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 48, 96, 0);
        enemys[1] = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 96, 96, 0);
        ((MainPlayer)mainPlayer).makeHostile();

        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);

        //player hasn't encountered enemy yet. Health should be full
        int damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);

        //Move player within the collision detection of the enemy
        for(int i = 0; i < 4; i++){
            ((MainPlayer)mainPlayer).move();
        }
        testKeyHandler.manualKeyRelease();

        //Player has made contact with enemy however, player has sword so player should not any damage
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(false, ((Enemy)enemys[0]).getIsHostile());
        assertSame(false, ((Enemy)enemys[0]).getIsAlive());
        assertSame(false, ((Enemy)enemys[0]).getIsVisible());
        assertSame(false, sword.getSwordCollected());

        //Loop through the 59/60 of the 1 second enemy cool down
        for(int i = 0; i < 59; i++){
            damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        }

        //1/60 of 1 second cool down still remains so health should remain the same as previous assert
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);

        //Move player within the collision detection of the enemy
        testKeyHandler.manualKeyPress(4);
        for(int i = 0; i < 7; i++){
            ((MainPlayer)mainPlayer).move();

        }
        testKeyHandler.manualKeyRelease();
        testKeyHandler.manualKeyPress(2);
        ((MainPlayer)mainPlayer).move();
        ((MainPlayer)mainPlayer).move();

        //Go through the final 1/60 of the 1 second cool. Player is still within enemies hitbox so player health goes down 2

        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(3, damageLivesResult);



    }

    @Test
    public void testEnemyDamagesPlayerWithShield() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        Character[] enemys;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Damage damageTracker = new Damage(testGamePanel);
        Shields shield = new Shield(testGamePanel, 0, 0, 0);
        Swords sword = new Sword(testGamePanel, 0, 0, 0);
        enemys = new Character[1];
        shield.shieldCollected();


        testKeyHandler.manualKeyPress(2);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 48, 0);
        enemys[0] = charFactory.getInstance("Enemy", testGamePanel, testKeyHandler, 48, 96, 0);
        ((MainPlayer)mainPlayer).makeProtected();

        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);

        //player hasn't encountered enemy yet. Health should be full
        int damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(true, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(2, shield.getShieldHealth());
        assertSame(true, shield.getShieldCollected());
        assertSame(true, ((Enemy)enemys[0]).getIsHostile());
        assertSame(true, ((Enemy)enemys[0]).getIsAlive());
        assertSame(true, ((Enemy)enemys[0]).getIsVisible());

        //Move player within the collision detection of the enemy
        for(int i = 0; i < 4; i++){
            ((MainPlayer)mainPlayer).move();
        }
        testKeyHandler.manualKeyRelease();

        //Player has made contact with enemy however, player has shield so player should not take any damage
        //shield takes 1 damage
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(true, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, ((Enemy)enemys[0]).getIsHostile());
        assertSame(true, ((Enemy)enemys[0]).getIsAlive());
        assertSame(true, ((Enemy)enemys[0]).getIsVisible());
        assertSame(1, shield.getShieldHealth());
        assertSame(true, shield.getShieldCollected());


        //Loop through the 119/120 of the 2 second enemy cool down
        for(int i = 0; i < 119; i++){
            damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        }

        //1/120 of 2 second cool down still remains so health should remain the same as previous assert
        //same with shield health
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);

        //Move player away from the collision detection of the enemy
        testKeyHandler.manualKeyPress(1);
        ((MainPlayer)mainPlayer).move();

        //away from enemy so shield and player health stays the same
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(true, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, ((Enemy)enemys[0]).getIsHostile());
        assertSame(true, ((Enemy)enemys[0]).getIsAlive());
        assertSame(true, ((Enemy)enemys[0]).getIsVisible());
        assertSame(1, shield.getShieldHealth());
        assertSame(true, shield.getShieldCollected());

        //move back into enemy hitbox
        testKeyHandler.manualKeyRelease();
        testKeyHandler.manualKeyPress(2);
        ((MainPlayer)mainPlayer).move();

        //Loop through the 119/120 of the 2 second enemy cool down
        for(int i = 0; i < 119; i++){
            damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        }

        //cool down is over so shield take 1 damage and player no longer protected
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(false, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, ((Enemy)enemys[0]).getIsHostile());
        assertSame(true, ((Enemy)enemys[0]).getIsAlive());
        assertSame(true, ((Enemy)enemys[0]).getIsVisible());
        assertSame(0, shield.getShieldHealth());
        assertSame(false, shield.getShieldCollected());

        //loop through 1 second cool down
        for(int i = 0; i < 59; i++){
            damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        }

        //Player no longer has shield so player now takes damage -2
        damageTracker.checkDamageEnemy(mainPlayer, enemys, sword, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(3, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(false, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, ((Enemy)enemys[0]).getIsHostile());
        assertSame(true, ((Enemy)enemys[0]).getIsAlive());
        assertSame(true, ((Enemy)enemys[0]).getIsVisible());
        assertSame(0, shield.getShieldHealth());
        assertSame(false, shield.getShieldCollected());




    }

    @Test
    public void testFireDamagesRegularPlayer() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        Fire[] fires;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Damage damageTracker = new Damage(testGamePanel);
        Shields shield = new Shield(testGamePanel, 0, 0, 0);
        Swords sword = new Sword(testGamePanel, 0, 0, 0);
        fires = new Fire[3];

        testKeyHandler.manualKeyPress(2);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 48, 0);
        fires[0] = new Fire(testGamePanel, 48, 96, 0);
        fires[1] = new Fire(testGamePanel, 96, 96, 0);
        fires[2] = new Fire(testGamePanel, 144, 96, 0);

        damageTracker.checkDamageFire(mainPlayer, fires, shield);

        //player hasn't encountered fire yet. Health should be full
        int damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(true, fires[0].getVisibility());
        assertSame(true, fires[1].getVisibility());
        assertSame(true, fires[2].getVisibility());

        //Move player within the collision detection of the enemy
        for(int i = 0; i < 4; i++){
            ((MainPlayer)mainPlayer).move();
        }
        testKeyHandler.manualKeyRelease();

        //Player has made contact with fire health should go down 2
        damageTracker.checkDamageFire(mainPlayer, fires, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(3, damageLivesResult);
        assertSame(false, fires[0].getVisibility());
        assertSame(true, fires[1].getVisibility());
        assertSame(true, fires[2].getVisibility());

        //Loop through the 59/60 of the 1 second fire cool down
        for(int i = 0; i < 59; i++){
            damageTracker.checkDamageFire(mainPlayer, fires, shield);
        }

        //1/60 of 1 second cool down still remains so health should remain the same as previous assert
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(3, damageLivesResult);
        assertSame(false, fires[0].getVisibility());
        assertSame(true, fires[1].getVisibility());
        assertSame(true, fires[2].getVisibility());

        testKeyHandler.manualKeyPress(4);
        //Move player within the collision detection of the next fire object
        for(int i = 0; i < 10; i++){
            ((MainPlayer)mainPlayer).move();
        }

        //Go through the final 1/60 of the 1 second cool. Player is still within fire's hitbox so player health goes down 2
        damageTracker.checkDamageFire(mainPlayer, fires, shield);

        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(1, damageLivesResult);
        assertSame(false, fires[0].getVisibility());
        assertSame(false, fires[1].getVisibility());
        assertSame(true, fires[2].getVisibility());

        //loop through the 59/60 of the 1 second enemy cool down again
        for(int i = 0; i < 59; i++){
            damageTracker.checkDamageFire(mainPlayer, fires, shield);
        }

        //Move player just outside of next enemies hitbox and go through the final 1/60 of one second cool down. Player is now outside of
        //fire hitbox so health stays the same as previous assert
        for(int i = 0; i < 10; i++) {
            ((MainPlayer) mainPlayer).move();
        }
        damageTracker.checkDamageFire(mainPlayer, fires, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(1, damageLivesResult);
        assertSame(false, fires[0].getVisibility());
        assertSame(false, fires[1].getVisibility());
        assertSame(true, fires[2].getVisibility());
        testKeyHandler.manualKeyRelease();

        //Move player into fire hitbox. Cool down is over so player health goes down 2
        testKeyHandler.manualKeyPress(4);
        ((MainPlayer)mainPlayer).move();
        damageTracker.checkDamageFire(mainPlayer, fires, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(-1, damageLivesResult);
        assertSame(false, fires[0].getVisibility());
        assertSame(false, fires[1].getVisibility());
        assertSame(false, fires[2].getVisibility());

    }



    @Test
    public void testFireDamagesPlayerWithShield() throws IOException {
        CharacterFactory charFactory = new CharacterFactory();
        Character mainPlayer;
        Fire[] fires;
        GamePanel testGamePanel = new GamePanel();
        KeyHandler testKeyHandler = new KeyHandler(testGamePanel);
        Damage damageTracker = new Damage(testGamePanel);
        Shields shield = new Shield(testGamePanel, 0, 0, 0);
        Swords sword = new Sword(testGamePanel, 0, 0, 0);
        fires = new Fire[1];
        shield.shieldCollected();


        testKeyHandler.manualKeyPress(2);
        mainPlayer = charFactory.getInstance("MainPlayer", testGamePanel, testKeyHandler, 48, 48, 0);
        fires[0] = new Fire(testGamePanel, 48, 96, 0);
        ((MainPlayer)mainPlayer).makeProtected();

        damageTracker.checkDamageFire(mainPlayer, fires, shield);

        //player hasn't encountered enemy yet. Health should be full
        int damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(true, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(2, shield.getShieldHealth());
        assertSame(true, shield.getShieldCollected());
        assertSame(true, fires[0].getVisibility());

        //Move player within the collision detection of the enemy
        for(int i = 0; i < 4; i++){
            ((MainPlayer)mainPlayer).move();
        }
        testKeyHandler.manualKeyRelease();

        //Player has made contact with enemy however, player has shield so player should not take any damage
        //shield takes 1 damage
        damageTracker.checkDamageFire(mainPlayer, fires, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(true, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, fires[0].getVisibility());
        assertSame(1, shield.getShieldHealth());
        assertSame(true, shield.getShieldCollected());


        //Loop through the 119/120 of the 2 second enemy cool down
        for(int i = 0; i < 119; i++){
            damageTracker.checkDamageFire(mainPlayer, fires, shield);
        }

        //1/120 of 2 second cool down still remains so health should remain the same as previous assert
        //same with shield health
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(true, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, fires[0].getVisibility());
        assertSame(1, shield.getShieldHealth());
        assertSame(true, shield.getShieldCollected());


        //Move player away from the collision detection of the enemy
        testKeyHandler.manualKeyPress(1);
        ((MainPlayer)mainPlayer).move();

        //away from enemy so shield and player health stays the same
        damageTracker.checkDamageFire(mainPlayer, fires, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(true, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, fires[0].getVisibility());
        assertSame(1, shield.getShieldHealth());
        assertSame(true, shield.getShieldCollected());

        //move back into enemy hitbox
        testKeyHandler.manualKeyRelease();
        testKeyHandler.manualKeyPress(2);
        ((MainPlayer)mainPlayer).move();

        //Loop through the 119/120 of the 2 second enemy cool down
        for(int i = 0; i < 119; i++){
            damageTracker.checkDamageFire(mainPlayer, fires, shield);
        }

        //cool down is over so shield take 1 damage and player no longer protected
        damageTracker.checkDamageFire(mainPlayer, fires,  shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(5, damageLivesResult);
        assertSame(false, mainPlayer.getIsHostile());
        assertSame(false, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(true, fires[0].getVisibility());
        assertSame(0, shield.getShieldHealth());
        assertSame(false, shield.getShieldCollected());

        //loop through 1 second cool down
        for(int i = 0; i < 59; i++){
            damageTracker.checkDamageFire(mainPlayer, fires, shield);
        }

        //Player no longer has shield so player now takes damage -2
        damageTracker.checkDamageFire(mainPlayer, fires, shield);
        damageLivesResult = ((MainPlayer)mainPlayer).getLives();
        assertSame(3, damageLivesResult);
        assertSame(false, ((MainPlayer)mainPlayer).getIsHostile());
        assertSame(false, ((MainPlayer)mainPlayer).getIsProtected());
        assertSame(false, fires[0].getVisibility());
        assertSame(0, shield.getShieldHealth());
        assertSame(false, shield.getShieldCollected());
    }



}

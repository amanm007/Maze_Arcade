package main;


import Characters.Character;
import Characters.CharacterFactory;
import Characters.Enemy;
import Characters.MainPlayer;
import Collectables.*;
import Elements.Door;
import Elements.Fire;
import TopHUD.GameTimer;
import TopHUD.HeadsUpDisplay;
import worldMap.MainMap;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * panel displayed in application window
 * ------------------------------------------
 *  Main, GamePanel, and KeyHandler class were adapted from an online tutorial series videos 1-3:
 *  https://www.youtube.com/watch?v=om59cwR7psI
 *  Author: RyiSnow
 *  Classes: MainMap's blueprint txt file idea was received from this series as well but implemented differently
 */
public class GamePanel extends JPanel implements Runnable {
    /*We set our tile size which is the size of each tile
    each level is made up of these tiles which the characters sit on top of
    there is also many items and collectibles that sit on each one
     */
    final int originalTileSize = 16;   //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // to make a 48 by 48 tile size
    //size and width of each level
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    int screenWidth = tileSize * maxScreenCol; // 768 pixels
    int screenHeight = tileSize * maxScreenRow; // 576 pixels

    private final int totalNumberWindows = 7;
    String timeShown;
    //frames per second
    int FPS = 60;
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    //creation of characters such as the main characters and enemies
    CharacterFactory characterFactory = new CharacterFactory();
    Character mainPlayer;
    Character[] enemys;
    //creation of collectibles such as coins and keys
    Coin[] coins;
    Key[] keys;
    //creation of doors
    Door[] doors;
    //creation of fires
    Fire[] fires;
    //creation of swords
    Sword[] swords;
    //creation of shield
    Shield[] shields;
    //creation of damage tracker on the hud
    Damage damageTracker;
    //holder of all the keys
    Keys allKeys;
    //Holder of all the coins
    Coins allCoins;
    //Holder of all the swords
    Swords allSwords;
    //Holder of all the shields
    Shields allShields;
    Collision checkCollision = new Collision(keyH, this);
    MainMap wholeMap = new MainMap(this);
    //The game hud
    HeadsUpDisplay gameHUD;
    GameTimer gameTimer;// GameTimer
    int score;
    private int currentWindow = 0;

    /**
     * Creates and places all objects of the game ex. mainPlayer, enemies, HUD, etc;
     * @throws IOException
     */
    public GamePanel() throws IOException {
        //initializing the game panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        damageTracker = new Damage(this);
        //creation of the main player
        mainPlayer = characterFactory.getInstance("MainPlayer", this, keyH, 48, 48, 0);
        //creation of the enemies across all the levels
        int numberOfEnemies=8;
        enemys = new Character[numberOfEnemies];
        int enemysXPosition[]={150,150,150,600,150,150,600,600};
        int enemysYPosition[]={350,550,550,550,150,550,250,550};
        int enemysWindowsPosition[]={2,2,3,3,4,4,4,4};
        for(int enemysSpawnedCounter=0; enemysSpawnedCounter<numberOfEnemies;enemysSpawnedCounter++){
            enemys[enemysSpawnedCounter] = characterFactory.getInstance("Enemy", this, keyH, enemysXPosition[enemysSpawnedCounter], enemysYPosition[enemysSpawnedCounter], enemysWindowsPosition[enemysSpawnedCounter]);
        }


        //creation of all the keys across all the levels
        int numberOfKeys=7;
        keys = new Key[numberOfKeys];
        int keysXPosition[]={48,200,645,200,345,48,48};
        int keysYPosition[]={48,620,645,250,675,48,48};
        int keysWindowsPosition[]={0,1,2,3,4,5,6};
        for(int keysSpawnedCounter=0; keysSpawnedCounter<numberOfKeys;keysSpawnedCounter++){
            keys[keysSpawnedCounter] = new Key(this, keysXPosition[keysSpawnedCounter], keysYPosition[keysSpawnedCounter], keysWindowsPosition[keysSpawnedCounter]);
        }


        //creation of all the coins across all the levels
        coins = new Coin[6];
        coins[0] = new Coin(this, 150, 240);
        coins[1] = new Coin(this, 576, 144);
        coins[2] = new Coin(this, 340, 240);
        coins[3] = new Coin(this, 430, 485);
        coins[4] = new Coin(this, 530, 445);
        coins[5] = new Coin(this, 670, 650);

        //creation of all the fire across all the levels
        fires = new Fire[30];
        fires[0] = new Fire(this, 333, 525, 1);
        fires[1] = new Fire(this, 288, 145, 1);
        fires[2] = new Fire(this, 570, 190, 1);
        fires[3] = new Fire(this, 670, 190, 1);
        fires[4] = new Fire(this, 335, 320, 2);
        fires[5] = new Fire(this, 365, 320, 2);
        fires[6] = new Fire(this, 395, 320, 2);
        fires[7] = new Fire(this, 425, 320, 2);
        fires[8] = new Fire(this, 455, 320, 2);
        fires[9] = new Fire(this, 485, 320, 2);
        fires[10] = new Fire(this, 515, 320, 2);
        fires[11] = new Fire(this, 545, 320, 2);
        fires[12] = new Fire(this, 575, 320, 2);
        fires[13] = new Fire(this, 605, 320, 2);
        fires[14] = new Fire(this, 635, 320, 2);
        fires[15] = new Fire(this, 665, 320, 2);
        fires[16] = new Fire(this, 435, 330, 3);
        fires[17] = new Fire(this, 665, 330, 3);
        fires[18] = new Fire(this, 430, 620, 3);
        fires[19] = new Fire(this, 665, 620, 3);
        fires[20] = new Fire(this, 125, 330, 4);
        fires[21] = new Fire(this, 250, 330, 4);
        fires[22] = new Fire(this, 375, 330, 4);
        fires[23] = new Fire(this, 500, 330, 4);
        fires[24] = new Fire(this, 625, 330, 4);
        fires[25] = new Fire(this, 330, 50, 4);
        fires[26] = new Fire(this, 330, 175, 4);
        fires[27] = new Fire(this, 330, 300, 4);
        fires[28] = new Fire(this, 330, 425, 4);
        fires[29] = new Fire(this, 330, 550, 4);

        //creation of all the doors across all the levels
        doors = new Door[10];
        doors[0] = new Door(this, 720, 144, 0, 1);
        doors[1] = new Door(this, 720, 288, 0, 2);
        doors[2] = new Door(this, 720, 432, 0, 3);
        doors[3] = new Door(this, 720, 576, 0, 4);
        doors[4] = new Door(this, 720, 48, 1, 2);
        doors[5] = new Door(this, 720, 48, 2, 3);
        doors[6] = new Door(this, 720, 48, 3, 4);
        doors[7] = new Door(this, 720, 48, 4, 5);
        doors[8] = new Door(this, 720, 48, 5, 0);
        doors[9] = new Door(this, 720, 48, 6, 0);

        //creation of all the swords and shields across all the levels
        swords = new Sword[1];
        swords[0] = new Sword(this, 150, 250, 3);


        shields = new Shield[1];
        shields[0] = new Shield(this, 450, 400, 2);

        /*Instances of the clases such as the counters
        this is for keys, coins, swords, hud, shields
         */
        allCoins = new Coins();
        allKeys = new Keys();
        gameHUD = new HeadsUpDisplay(this);
        allSwords = new Swords();
        allShields = new Shields();
        gameTimer = new GameTimer();

        score = 0;
    }

    /**
     * starts infinite loop
     */
    public void startGameThread() {
        //starts game
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * acts as game clock.
     * updates all game values each cycle
     */
    @Override
    public void run() {
        //draw interval and draw time on each tick rate
        long drawInterval = 1000000000 / FPS;//0.016 seconds
        long nextDrawtime = System.nanoTime() + drawInterval;


        while (gameThread != null) {
            try {
                //checks the collision in each game loop for the player collisions
                checkCollision.checkForCollisionsPositionsPlayer(mainPlayer);
                //checks the collision in each game loop for the enemy collisions

                for (Character enemy : enemys) {
                    checkCollision.checkForCollisionsPositionsEnemy(enemy);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            update(); //update character position
            damageTracker.checkDamageEnemy(mainPlayer, enemys, allSwords, allShields);
            damageTracker.checkDamageFire(mainPlayer, fires, allShields);

            //allCoins has a method which can be used to see how many coins have been collected
            for (Coin coin : coins) {
                checkCollision.coinCollision(mainPlayer, coin, allCoins);
                if (currentWindow >= 1 && currentWindow <= 5) {
                    coin.rollDice();
                }
            }
            //Key collision
            for (Key key : keys) {
                checkCollision.keyCollision(mainPlayer, key, allKeys);
            }
            //sword collision
            for (Sword sword : swords) {
                checkCollision.swordCollision(mainPlayer, sword, allSwords);
            }
            //shield collision
            for (Shield shield : shields) {
                checkCollision.shieldCollision(mainPlayer, shield, allShields);
            }
            //door collision
            for (Door door : doors) {
                checkCollision.doorCollision(mainPlayer, door);
            }
            //draws the hud
            gameHUD.updateHud(allCoins, allKeys, allSwords, allShields, mainPlayer);
            repaint();
            //redraws the hud with the updated info
            if (currentWindow == 0) {
                gameTimer.resetProgramStartTime();
                ((MainPlayer) mainPlayer).setLives(5);
                score = 0;
                ((MainPlayer) mainPlayer).makeUnProtected();
                ((MainPlayer) mainPlayer).makeFriendly();
                swords[0].setVisible();
                shields[0].setVisible();
                allShields.removeShield();
                allSwords.removeSword();
            }
            if (currentWindow >= 1 && currentWindow <= 4 && gameHUD.getLives() <= 0) {
                setCurrentWindow(6);
            }


            try {
                //updates the time
                double remainingTime = nextDrawtime - System.nanoTime();

                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawtime += drawInterval;

            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }

    }

    /**
     * add a certain value to the current game score
     * @param addition int the value that is added to the score
     */
    public void addScore(int addition) {
        score += addition;
    }

    /**
     *  getter - return current score
     * @return int current game score
     */
    public int getScore(){
        return score;
    }

    /**
     * return the games mainPlayer character object
     * @return mainPlayer character object
     */
    public Character getPlayer() {
        return mainPlayer;
    }

    /**
     * return the int value of the current game window
     * @return int value of teh current game window
     */
    public int getCurrentWindow() {
        return currentWindow;
    }

    /**
     * sets the current window
     * @param window
     */
    public void setCurrentWindow(int window) {
        currentWindow = window;
        ((MainPlayer) mainPlayer).setWindow(window);
    }
    public String getTime(){
        return gameTimer.getTime();
    }

    /**
     * sets game window to certain level
     * @param window int value of the value of the current game window
     */
    public void requestSetCurrentWindow(int window) {
        //updates keys, coins,fire
        if (allKeys.getKeyCollected()) {
            setCurrentWindow(window);

            allKeys.removeKey();
            for (Key key : keys) {
                key.setVisible();
            }
            for (Fire fire : fires) {
                fire.setVisible();
            }
        }
    }


    /**
     * gets the total number of window
     * @return int value of total number of windows
     */
    public int getTotalNumberWindows() {
        return totalNumberWindows;
    }

    public Collision getCollisionHandler(){
        return checkCollision;
    }

    public Keys getKeys(){
        return allKeys;
    }

    /**
     * updates character and enemy movement
     */
    public void update() {
        ((MainPlayer) mainPlayer).move();
        for (Character enemy : enemys) {
            ((Enemy) enemy).move();
        }
    }


    /**
     * draws everything on the screen
     * @param g Graphics2D object which allows program to print to screen
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//
        Graphics2D g2 = (Graphics2D) g;//Graphics 2d extends the graphic class to provide more sophisticated controls over geometry, coordinate transformations, color management
//and text layout
        try {
            wholeMap.createWholeMap(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font smallFont = new Font("Arial", Font.BOLD, 14);
        Font largeFont = new Font("Arial", Font.BOLD, 25);
        g.setColor(Color.white);

        g.setFont(largeFont);
        g.drawString("Score: ".concat(Integer.toString(score)), 420, 30);
        if (currentWindow == 0) {
            //draws lables on the first level

            gameHUD.resetCoins(allCoins);
            g.setFont(largeFont);
            g.drawString("Shadow's Adventure", 265, 314);
            g.setFont(smallFont);
            g.drawString("Level 1", 650, 170);
            g.drawString("Level 2", 650, 314);
            g.drawString("Level 3", 650, 458);
            g.drawString("Level 4", 650, 602);
        }
        if (currentWindow == 5) {
            //draws lables on the end screen level
            g.setFont(largeFont);
            g.drawString("You Win", 290, 314);
            g.setFont(smallFont);
            g.drawString("Score: ".concat(Integer.toString(score)), 290, 340);
            g.drawString("Time: ".concat(gameHUD.getTime()), 290, 370);

        }
        if (currentWindow == 6) {
            g.setFont(largeFont);
            //draws lables on the end screen level
            g.drawString("You Lose", 290, 314);
            g.setFont(smallFont);
            g.drawString("Score: ".concat(Integer.toString(score)), 290, 340);
            g.drawString("Time: ".concat(gameHUD.getTime()), 290, 370);

        }
        //draws coins
        if(currentWindow != 0 && currentWindow != 5 && currentWindow != 6) {
            for (Coin coin : coins) {
                coin.draw(g2);
            }
        }

        //Draws key on the map
        for (Key key : keys) {
            key.draw(g2);
        }
        //draws doors
        for (Door door : doors) {
            door.draw(g2);
        }
        //draws swords
        for (Sword sword : swords) {
            sword.draw(g2);
        }
        //draws shield
        for (Shield shield : shields) {
            shield.draw(g2);
        }
        //draws fire
        for (Fire fire : fires) {
            fire.draw(g2);
        }
        //draws enemies and players
        ((MainPlayer) mainPlayer).draw(g2);
        for (Character enemy : enemys) {
            ((Enemy) enemy).draw(g2);
        }

        //draws the hud
        gameHUD.draw(g2, gameTimer, currentWindow);
        g2.dispose(); // This FUNCTION disposes graphics context and release any system resources that the object(g2) is using
//using dispose() is a good coding practice in java to save some memory.
    }
}

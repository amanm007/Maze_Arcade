package main;

import Characters.Character;
import Characters.Enemy;
import Characters.MainPlayer;
import Collectables.*;
import Elements.Door;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Collision class handles and checks the collisions between every object and player
 */
public class Collision {

        int currentCharacterTop;
        int currentCharacterBottom;
        int currentCharacterLeft;
        int currentCharacterRight;
        KeyHandler currentKeyHandler;
        ArrayList<String> collisionArray;
        GamePanel currentGamePanel;

    /**
     * Constructor - Read in a blueprint txt file and produces an array of 0,1,2 corresponding to different map tile textures
     * @param mainKeyHandler - usedto check user key inputs
     * @param panel - used to draw to the main panel
     * @throws IOException
     */
        public Collision(KeyHandler mainKeyHandler,GamePanel panel) throws IOException {
            this.currentKeyHandler = mainKeyHandler;
            //gets the map/level file
            InputStream bluePrintFile = getClass().getResourceAsStream("/mapBluePrint/mapBlueprint.txt");
            //reads it and takes it on the current game panel and collision to things like the walls it
            BufferedReader readBlueprint = new BufferedReader(new InputStreamReader(bluePrintFile));
            collisionArray = new ArrayList<String>();
            currentGamePanel = panel;

            for(int i = 0; i < 16; i++){
                String row = readBlueprint.readLine();
                for(int x = 0; x < currentGamePanel.getTotalNumberWindows()*16; x++){
                    if(row.charAt(x) == '0'){
                        collisionArray.add( "0");
                        //this is collision the ground in the level
                    }
                    else if(row.charAt(x) == '1'){
                        collisionArray.add( "1");
                        //this is collision the walls in the level
                    }
                    else if(row.charAt(x) == '2'){
                        collisionArray.add( "2");
                        //this is collision the pits in the level
                    }
                }
            }
        }

    /**
     * used to determine if the which walls the user/mainPlayer might run into
     * @param mainPlayer used to determine where the main player is
     * @throws IOException - IOException
     */
    public void checkForCollisionsPositionsPlayer(Character mainPlayer) throws IOException {
            //detection for collision with a player
            currentCharacterTop = (mainPlayer.getHitBox()).y / 48;
            currentCharacterBottom = ((mainPlayer.getHitBox()).y + 32 ) / 48;
            currentCharacterLeft = (mainPlayer.getHitBox()).x / 48;
            currentCharacterRight = (((mainPlayer.getHitBox()).x + 32)) / 48;

            int assetToCheckSingle = 0;
            int assetToCheckSecond = 0;

            //the following keep the player from moving up, down left or right if there is an object in the way.
            if(currentKeyHandler.upPressed)
            {

               if(currentCharacterLeft == currentCharacterRight){
                   assetToCheckSingle = ((currentCharacterTop -1)*16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);

               }
               else{

                   assetToCheckSingle = ((currentCharacterTop -1)*16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);
                   assetToCheckSecond = ((currentCharacterTop -1)*16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight);
               }
                checkForCollisionsBottom(mainPlayer, assetToCheckSingle, assetToCheckSecond);


            }
            else if(currentKeyHandler.downPressed)
            {

                if(currentCharacterLeft == currentCharacterRight){
                    assetToCheckSingle = ((currentCharacterTop +1) * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);
                }
                else{

                    assetToCheckSingle = ((currentCharacterTop +1) * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);
                    assetToCheckSecond = ((currentCharacterTop +1) * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight);
                }
                checkForCollisionsTop(mainPlayer, assetToCheckSingle, assetToCheckSecond);

            }
            if(currentKeyHandler.leftPressed && !(currentKeyHandler.rightPressed))//adam: experiment changed else if to if for diagonal
            {
                if(currentCharacterTop == currentCharacterBottom){
                    assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft - 1);

                }
                else{
                    assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft - 1);
                    assetToCheckSecond = (currentCharacterBottom * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft - 1);
                }
                checkForCollisionsRight(mainPlayer, assetToCheckSingle, assetToCheckSecond);
            }
            if(currentKeyHandler.rightPressed && !(currentKeyHandler.leftPressed))//adam: experiment changed else if to if for diagonal
            {
                if(currentCharacterTop == currentCharacterBottom){
                    assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight + 1);
                }
                else{
                    assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight + 1);
                    assetToCheckSecond = (currentCharacterBottom * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight + 1);
                }
                checkForCollisionsLeft(mainPlayer, assetToCheckSingle, assetToCheckSecond);
            }
        }

    /**
     * Checks for collisions between enemies and walls
      * @param enemy - used to determine where the enemy is
     * @throws IOException - IOException
     */
    public void checkForCollisionsPositionsEnemy(Character enemy) throws IOException {

        currentCharacterTop = (enemy.getHitBox()).y / 48;
        currentCharacterBottom = ((enemy.getHitBox()).y + 32 ) / 48;
        currentCharacterLeft = (enemy.getHitBox()).x / 48;
        currentCharacterRight = (((enemy.getHitBox()).x + 32)) / 48;

        int assetToCheckSingle = 0;
        int assetToCheckSecond = 0;
        boolean movingUp = ((Enemy)enemy).getMovingUp();
        boolean movingDown = ((Enemy)enemy).getMovingDown();
        boolean movingLeft = ((Enemy)enemy).getMovingLeft();
        boolean movingRight = ((Enemy)enemy).getMovingRight();

        if(movingUp)
        {
            if(currentCharacterLeft == currentCharacterRight){

                assetToCheckSingle = ((currentCharacterTop -1)*16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);

            }
            else{
                assetToCheckSingle = ((currentCharacterTop -1)*16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);
                assetToCheckSecond = ((currentCharacterTop -1)*16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight);
            }
            checkForCollisionsBottom(enemy, assetToCheckSingle, assetToCheckSecond);


        }
        else if(movingDown)
        {
            if(currentCharacterLeft == currentCharacterRight){
                assetToCheckSingle = ((currentCharacterTop +1) * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);
            }
            else{
                assetToCheckSingle = ((currentCharacterTop +1) * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft);
                assetToCheckSecond = ((currentCharacterTop +1) * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight);
            }
            checkForCollisionsTop(enemy, assetToCheckSingle, assetToCheckSecond);

        }
        if(movingLeft)
        {
            if(currentCharacterTop == currentCharacterBottom){
                assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft - 1);

            }
            else{
                assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft - 1);
                assetToCheckSecond = (currentCharacterBottom * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterLeft - 1);
            }
            checkForCollisionsRight(enemy, assetToCheckSingle, assetToCheckSecond);
        }
        if(movingRight)
        {

            if(currentCharacterTop == currentCharacterBottom){
                assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight + 1);
            }
            else{
                assetToCheckSingle = (currentCharacterTop * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight + 1);
                assetToCheckSecond = (currentCharacterBottom * 16*currentGamePanel.getTotalNumberWindows()) + (currentCharacterRight + 1);
            }
            checkForCollisionsLeft(enemy, assetToCheckSingle, assetToCheckSecond);
        }
    }

    /**
     * used to check if the character is going to hit the top of a wall and stops them if they are
     * @param currentCharacter - used to determine where the character is
     * @param assetToCheckSingle - if the player is in the middle of a tile only this one tile will be checked for collision
     * @param assetToCheckSecond - if the player is in between 2 tiles this parameter and the one above will be checked for collision
     */
        public void checkForCollisionsTop(Character currentCharacter, int assetToCheckSingle, int assetToCheckSecond){

            if(assetToCheckSecond == 0){

                if(collisionArray.get(assetToCheckSingle) == "1" || (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){

                    if((currentCharacter.getHitBox()).y + 32 >= ((currentCharacterBottom) * 48) + 44){

                        currentCharacter.changeVerticalSpeed(0);
                    }
                }
                else{
                    currentCharacter.changeVerticalSpeed(4);
                }
            }
            else{

                if(collisionArray.get(assetToCheckSingle) == "1" || collisionArray.get(assetToCheckSecond) == "1"|| (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){
                    if((currentCharacter.getHitBox()).y + 32 >= ((currentCharacterBottom) * 48) + 44){

                        currentCharacter.changeVerticalSpeed(0);
                    }
                }
                else{
                    currentCharacter.changeVerticalSpeed(4);
                }
            }
        }


    /**
     * used to check if the character is going to hit the bottom of a wall and stops them if they are
     * @param currentCharacter - used to determine where the character is
     * @param assetToCheckSingle - if the player is in the middle of a tile only this one tile will be checked for collision
     * @param assetToCheckSecond - if the player is in between 2 tiles this parameter and the one above will be checked for collision
     */
    public void checkForCollisionsBottom(Character currentCharacter, int assetToCheckSingle, int assetToCheckSecond){

        if(assetToCheckSecond == 0){

            if(collisionArray.get(assetToCheckSingle) == "1"|| (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){


                if((currentCharacter.getHitBox()).y -4 <= ((currentCharacterTop) * 48)){

                    currentCharacter.changeVerticalSpeed(0);
                }

            }
            else{
                currentCharacter.changeVerticalSpeed(4);
            }
        }
        else{

            if(collisionArray.get(assetToCheckSingle) == "1" || collisionArray.get(assetToCheckSecond) == "1"|| (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){
                if((currentCharacter.getHitBox()).y -4 <= ((currentCharacterTop) * 48)){

                    currentCharacter.changeVerticalSpeed(0);
                }
            }
            else{
                currentCharacter.changeVerticalSpeed(4);
            }
        }
    }


    /**
     * used to check if the character is going to hit the left of a wall and stops them if they are
     * @param currentCharacter - used to determine where the character is
     * @param assetToCheckSingle - if the player is in the middle of a tile only this one tile will be checked for collision
     * @param assetToCheckSecond - if the player is in between 2 tiles this parameter and the one above will be checked for collision
     */
    public void checkForCollisionsLeft(Character currentCharacter, int assetToCheckSingle, int assetToCheckSecond){

        if(assetToCheckSecond == 0){

            if(collisionArray.get(assetToCheckSingle) == "1"|| (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){

                if((currentCharacter.getHitBox()).x + 32 >= ((currentCharacterRight) * 48) + 44){

                    currentCharacter.changeHorizontalSpeed(0);
                }
            }
            else{

                currentCharacter.changeHorizontalSpeed(4);
            }
        }
        else{

            if(collisionArray.get(assetToCheckSingle) == "1" || collisionArray.get(assetToCheckSecond) == "1"|| (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){
                if((currentCharacter.getHitBox()).x + 32 >= ((currentCharacterRight) * 48) + 44){

                    currentCharacter.changeHorizontalSpeed(0);
                }
            }
            else{

                currentCharacter.changeHorizontalSpeed(4);
            }
        }
    }


    /**
     * used to check if the character is going to hit the right of a wall and stops them if they are
     * @param currentCharacter - used to determine where the character is
     * @param assetToCheckSingle - if the player is in the middle of a tile only this one tile will be checked for collision
     * @param assetToCheckSecond - if the player is in between 2 tiles this parameter and the one above will be checked for collision
     */
    public void checkForCollisionsRight(Character currentCharacter, int assetToCheckSingle, int assetToCheckSecond){

        if(assetToCheckSecond == 0){

            if(collisionArray.get(assetToCheckSingle) == "1"|| (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){

                if((currentCharacter.getHitBox()).x -4  <= ((currentCharacterLeft) * 48)){

                    currentCharacter.changeHorizontalSpeed(0);
                }
            }
            else{
                currentCharacter.changeHorizontalSpeed(4);
            }
        }
        else{

            if(collisionArray.get(assetToCheckSingle) == "1" || collisionArray.get(assetToCheckSecond) == "1"|| (collisionArray.get(assetToCheckSingle) == "0" && currentCharacter instanceof Enemy)){
                if((currentCharacter.getHitBox()).x -4  <= ((currentCharacterLeft) * 48)){

                    currentCharacter.changeHorizontalSpeed(0);
                }
            }
            else{
                currentCharacter.changeHorizontalSpeed(4);
            }
        }
    }

    /**
     * used to check if the player has a collision with a coin object
     * @param mainPlayer used to determine the location of the player
     * @param currentCoin used to determine the location of the coin
     * @param allCoins used to count the number of coins collided with
     */
    public void coinCollision(Character mainPlayer, Coin currentCoin, Coins allCoins){
        //Collision with coins at cords
        int coinXPos = currentCoin.getXPos();
        int coinYPos = currentCoin.getYPos();
        //players cords
        int playerXPos=mainPlayer.getXPos()-currentGamePanel.getCurrentWindow()*768;
        int playerYPos=mainPlayer.getYPos();
        //check if the player intersects the coins hit box
        if(Math.sqrt(Math.pow(abs(playerXPos-coinXPos),2) +Math.pow(abs(playerYPos-coinYPos),2)) < 35){
            if(currentCoin.getVisibility()){
                allCoins.addCoin();
                currentGamePanel.addScore(1);
            }
            currentCoin.setInvisible();
        }

    }

    /**
     * used to check if the player has a collision with a sword object
     * @param mainPlayer - used to determine the location of the player
     * @param sword - used to determine the location of the sword
     * @param swordHasBeenCollected - used to count the number of swords collied with
     */
    public void swordCollision(Character mainPlayer, Sword sword, Swords swordHasBeenCollected){

        int swordXPos = sword.getXPos()+sword.getWindow()*768;
        int swordYPos = sword.getYPos();

        int playerXPos=mainPlayer.getXPos();
        int playerYPos=mainPlayer.getYPos();

        if(Math.sqrt(Math.pow(abs(playerXPos-swordXPos),2) +Math.pow(abs(playerYPos-swordYPos),2)) < 35){

            if(sword.getVisibility()){

                swordHasBeenCollected.swordCollected();
                ((MainPlayer)mainPlayer).makeHostile();
            }
            sword.setInvisible();
        }

    }

    /**
     * used to check if a player has a collision with a shield object
     * @param mainPlayer used to determine the location of the player
     * @param shield used to determine the location of the shield object
     * @param shieldsHasBeenCollected used to count the amount of shields collided with
     */
    public void shieldCollision(Character mainPlayer, Shield shield, Shields shieldsHasBeenCollected){

        int swordXPos = shield.getXPos()+shield.getWindow()*768;
        int swordYPos = shield.getYPos();

        int playerXPos=mainPlayer.getXPos();
        int playerYPos=mainPlayer.getYPos();

        if(Math.sqrt(Math.pow(abs(playerXPos-swordXPos),2) +Math.pow(abs(playerYPos-swordYPos),2)) < 35){

            if(shield.getVisibility()){

                shieldsHasBeenCollected.shieldCollected();
                ((MainPlayer)mainPlayer).makeProtected();

            }
            shield.setInvisible();

        }

    }

    /**
     * used to check if the player has a collision with a key object
     * @param mainPlayer used to determine the location of the player
     * @param key used to determine the location of the key object
     * @param keyHasBeenCollected used to count the number of keys collided with
     */
    public void keyCollision(Character mainPlayer, Key key, Keys keyHasBeenCollected){
        //Collision with coins at keys
        int keyXPos = key.getXPos()+key.getWindow()*768;
        int keyYPos = key.getYPos();
        //players cords
        int playerXPos=mainPlayer.getXPos();
        int playerYPos=mainPlayer.getYPos();
        //check if the player intersects the keys hit box
        if(Math.sqrt(Math.pow(abs(playerXPos-keyXPos),2) +Math.pow(abs(playerYPos-keyYPos),2)) < 35){

            if(key.getVisibility()){
                keyHasBeenCollected.keyCollected();
                if (currentGamePanel.getCurrentWindow()>=1 && currentGamePanel.getCurrentWindow()<=4){
                    currentGamePanel.addScore(2);
                }
            }
            key.setInvisible();
        }

    }


    /**
     * used to check if the player has a collision with a door object
     * @param mainPlayer used to check where the player is
     * @param door used to check where the door is
     */
    public void doorCollision(Character mainPlayer, Door door){
        //Collision with coins at doors
        int doorXPos = door.getXPos()+door.getWindow()*768;
        int doorYPos = door.getYPos();
        //players cords
        int playerXPos=mainPlayer.getXPos();
        int playerYPos=mainPlayer.getYPos();
        //check if the player intersects the doors hit box
        if(Math.sqrt(Math.pow(abs(playerXPos-doorXPos),2) +Math.pow(abs(playerYPos-doorYPos),2)) < 60){//need value larger then block size of 48 to not get blocked by wall underneath.
            currentGamePanel.requestSetCurrentWindow(door.getDestinationWindow());
        }

    }
}

package Collectables;

import main.GamePanel;
import main.KeyHandler;
/**
 this is the super class for the coins storing all of the coins on the maps information
 */
public class Coins {
    //tally of number of coins player has collected
    int coinsAmount;
    GamePanel currentGamePanel;
    KeyHandler currentKeyHandler;


    public Coins() {}
    /**
     * returns the number of coins a player has collected
     */
    public int getCoinsAmount(){
        return coinsAmount;
    }

    /**
     * adds a coin to the players inventory of coins
     */
    public void addCoin(){
        coinsAmount++;
    }

    public void setCoinsAmount(int newCoinsAmount){coinsAmount = newCoinsAmount;}
}

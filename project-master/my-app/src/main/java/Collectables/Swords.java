package Collectables;

import main.GamePanel;

/**
 *this is the super class for the sword storing all of the keys on the maps information
 */
public class Swords {
    //this tells us if the player has a sword and its health in their inventory
    boolean swordsCollected;
    GamePanel currentGamePanel;

    public Swords() {}

    /**
     *returns if they have a sword
     */
    public boolean getSwordCollected(){
        return swordsCollected;
    }
    /**
     * for when they have a sword
     */
    public void swordCollected(){
        swordsCollected=true;
    }

    /**
     * For when they use up their sword
     */
    public void removeSword(){
        swordsCollected=false;
    }
}


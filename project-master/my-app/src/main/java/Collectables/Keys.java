package Collectables;

import main.GamePanel;

/**
 *this is the super class for the keys storing all of the keys on the maps information
 */
public class Keys {
    boolean keyCollected;
    //this tells us if the player has a key in their inventory
    GamePanel currentGamePanel;

    public Keys() {}

    /**
     *
     * @return keyCollected
     */
    public boolean getKeyCollected(){
        return keyCollected;
    }

    /**
     *
     * keyCollected becomes set to true
     */
    public void keyCollected(){
        keyCollected=true;
    }
    /**
     *
     * keyCollected becomes set to false
     * for when they have used a key to open a door
     */
    public void removeKey(){
        keyCollected=false;
    }
}

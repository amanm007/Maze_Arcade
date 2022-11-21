package Collectables;

import main.GamePanel;

/**
 *this is the super class for the shield storing all of the keys on the maps information
 */
public class Shields {
    boolean shieldCollected;
    GamePanel currentGamePanel;
    //this tells us if the player has a shield and its health in their inventory
    int shieldHealth;


    public Shields() {}
    /**
     * shield collected or not
     */
    public boolean getShieldCollected(){
        return shieldCollected;
    }

    /**
     * shield collected gives u more health and a shield
     */
    public void shieldCollected(){
        shieldHealth = 2;
        shieldCollected=true;
    }

    /**
     * for when they have used up their shield
     */
    public void removeShield(){
        shieldCollected=false;
    }

    /**
     * for when they hit their shield
     */
    public void shieldHit(){
        shieldHealth--;
    }

    /**
     * @return the shield health value
     */
    public int getShieldHealth(){
        return shieldHealth;
    }
}


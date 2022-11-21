package Characters;

import main.GamePanel;
import main.KeyHandler;

/**
 * uses object factory design principle to create characters
 */
public class CharacterFactory {

    /**
     *
     * @param characterType Enemy or MainPlayer
     * @param mainGamePanel
     * @param mainKeyHandler
     * @param xPos
     * @param yPos
     * @param window indicates level to place character onto
     * @return
     */
    public Character getInstance(String characterType, GamePanel mainGamePanel, KeyHandler mainKeyHandler,int xPos, int yPos,int window){
        if(characterType == null){
            return null;
        } else if(characterType.equals("Enemy")){
            return new Enemy(mainGamePanel, mainKeyHandler,xPos, yPos,window);
        } else if(characterType.equals("MainPlayer")){
            return new MainPlayer(mainGamePanel, mainKeyHandler,xPos, yPos,window);
        }
        return null;
    }
}

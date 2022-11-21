package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * used to which keyboard keys the user is pressing and letting go of
 */
public class KeyHandler implements KeyListener{
	//KeyHandler deals with all key presses and allows us to to let the player move ect.
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	private GamePanel currentGamePanel;
	public KeyHandler(GamePanel panel){
		currentGamePanel = panel;
	}

	/**
	 * 
	 * @param e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
	}

	/**
	 * allow manual key presses through code so test methods can move main player
	 * @param whichDirection int for which is directly linked to which direction key is pressed
	 */
	public void manualKeyPress(int whichDirection){

		if(whichDirection == 1){
			upPressed = true;
		}else if(whichDirection == 2){
			downPressed = true;
		}else if(whichDirection == 3){
			leftPressed = true;
		}else if(whichDirection == 4){
			rightPressed = true;
		}
	}

	/**
	 * allow manual key release through code so test methods can stop moving main player
	 */
	public void manualKeyRelease(){
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_0) {
			System.out.println("0");
			currentGamePanel.setCurrentWindow(0);

		}
		if(code==KeyEvent.VK_1) {
			System.out.println("1");
			currentGamePanel.setCurrentWindow(1);
		}
		if(code==KeyEvent.VK_2) {
			System.out.println("2");
			currentGamePanel.setCurrentWindow(2);

		}
		if(code==KeyEvent.VK_3) {
			System.out.println("3");
			currentGamePanel.setCurrentWindow(3);
		}
		if(code==KeyEvent.VK_4) {
			System.out.println("4");
			currentGamePanel.setCurrentWindow(4);
		}
		if(code==KeyEvent.VK_5) {
			System.out.println("5");
			currentGamePanel.setCurrentWindow(5);
		}
		if(code==KeyEvent.VK_W) {
			upPressed=true;
			
		}
		if(code==KeyEvent.VK_S) {
			downPressed=true;
			
		}
		if(code==KeyEvent.VK_A) {
			leftPressed=true;
			
		}
		if(code==KeyEvent.VK_D) {
			rightPressed=true;
			
		}
		
		
		

	    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W) {
			upPressed=false;
			
		}
		if(code==KeyEvent.VK_S) {
			downPressed=false;
			
		}
		if(code==KeyEvent.VK_A) {
			leftPressed=false;
		
		}
		if(code==KeyEvent.VK_D) {
			rightPressed=false;
			
		}
		
		
	}

}

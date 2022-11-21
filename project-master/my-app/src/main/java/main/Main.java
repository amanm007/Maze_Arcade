package main;

import javax.swing.*;
import java.io.IOException;

/**
 * Main, GamePanel, and KeyHandler class were adapted from an online tutorial series videos 1-3:
 * https://www.youtube.com/watch?v=om59cwR7psI
 * Author: RyiSnow
 * Classes: MainMap's blueprint txt file idea was received from this series as well but implemented differently
 * --------------------------------------------------------------------------------------
 * Main class initializes the frame our whole game is run as well as starts of the GamePanel class which is the heart of the p
 * program
 */
public class Main {


    /**
     * Starts game, creates JFrame, and handles closing operation
     * @param args string array
     * @throws IOException throws exception and ends program
     */
    public static void main(String[] args) throws IOException {
        //intializes our Jframe
        JFrame window = new JFrame();
        //closing our jframe
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);//revert to false. changed for debugging
        //title and name of game
        window.setTitle("Shadow's Adventure");
        //Gamepanel creation
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}

package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{


    //screen SETTINGS
    final int originalTileSize = 16; // 16x16 tile, size of players characters
    final int scale = 3; //the character looks 3x16

    final int tileSize = originalTileSize*scale; // 48x48 tile
    final int maxScreenCol = 16; // i can change to make the screen bigger
    final int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeigth = tileSize*maxScreenRow;

    //Game clock (the most important because the game never stops)
    Thread gameThread;

    //constructeur of the game panel
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    //when we start the game Thread, it automatically call the run method, we will create a game loop
    @Override
    public void run() {

    }
}

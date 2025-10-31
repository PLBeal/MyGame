package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{


    //screen SETTINGS
    final int originalTileSize = 16; // 16x16 tile, size of players characters
    final int scale = 3; //the character looks 3x16

    public final int tileSize = originalTileSize*scale; // 48x48 tile
    final int maxScreenCol = 16; // i can change to make the screen bigger
    final int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeigth = tileSize*maxScreenRow;


    //Fps
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    //Game clock (the most important because the game never stops)
    Thread gameThread;
    Player player = new Player(this, keyH);



    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;




    //constructeur of the game panel
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    //when we start the game Thread, it automatically call the run method, we will create a game loop
    @Override
    /*public void run() {
        while(gameThread != null) {

            //the programm repeats so fast that we must add a time interval
            // because the loop is done millions of time per second,
            // so the rectangle quickly went out of the cadre
            // we want it to update 60 times per second we need to know what time it is and how much time
            // has passed

            //60 fps, 0.01666 seconds
            double drawIntervals = 1000000000/FPS;
            double nextDrawTime = System.nanoTime() + drawIntervals;


            //1 Update: update information such as character positions
            update();
            //2 Draw: draw the screen with the updated informations
            repaint();
            //if fps is 30, the programm does the (update & Draw) 60 times per seconds



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /=1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                //nextDrawTime += drawIntervals;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
     */

    public void run() {
        double drawIntervals = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            timer+= (currentTime-lastTime);
            delta += (currentTime - lastTime)/drawIntervals;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer > 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();

    }
}

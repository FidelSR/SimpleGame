package core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
//import javax.swing.plaf.DimensionUIResource;

import entities.Player;
import tile.Manager;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //16x16 tile for all tiles and characters
    final int scale = 3; //scales pixels to be 3 times the size
    public final int tileSize = originalTileSize * scale; //actual display will be 48x48 tile
    public final int maxScreenCol = 16; //16 tiles horizontally
    public final int maxScreenRow = 12; //12 tiles vertically
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //FPS
    int FPS = 60;
    Manager tileM = new Manager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    //In java upperleft corner is X:0 Y:0
    //X values increase to the right
    //Y values increase as they go down

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); 
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true); //improves game rendering performance
        this.addKeyListener(keyH); //adding a key listener so game can recognize input
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //GameLoop
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >=1 ) {
                //Update information like character positions
                update();
                //Draw the screen with updated information
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //calls to draw the tiles
        tileM.draw(g2);
        //calls to draw the player
        player.draw(g2);
        g2.dispose();
    }
    
}

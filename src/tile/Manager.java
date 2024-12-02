package tile;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Manager {
    GamePanel gp;
    Tile[] t;

    public Manager(GamePanel gp){
        this.gp = gp;
        t = new Tile[10]; //sets how many tiles to create
        getTileImage();
    }

    public void getTileImage(){
        try {
            t[0] = new Tile();
            t[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassTile.png"));
            t[1] = new Tile();
            t[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stoneTile.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        //g2.drawImage(t[1].image, 48, 0, gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){
            g2.drawImage(t[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}

package entities;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GamePanel;
import core.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    boolean idle = true; //variable used to check if player is idle

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/back.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up3= ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/default.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/sideDefault.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/sideMove.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/sideMove2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/rightDefault.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/rightMove.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/rightMove2.png"));
        } catch (IOException e) {
            //System.out.println("no images");
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            idle = false; //updates idle
            //Updates direction
            if(keyH.upPressed){
                direction  ="up";
                y -= speed;
            }else if(keyH.downPressed){
                direction = "down";
                y += speed;
            }else if(keyH.leftPressed){
                direction = "left";
                x -= speed;
            }else if(keyH.rightPressed){
                direction = "right";
                x += speed;
            }

            //Updates animations
            spriteCounter++;
            if(spriteCounter > 6){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }else {
            idle = true; //updates idle
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(idle){
                    image = up1; //sets idle sprite if idle
                }else {
                    if(spriteNum == 1){
                        image = up2;
                    }else if(spriteNum == 2){
                        image = up3;
                    }
                }
                break;
            case "down":
                if (idle){
                    image = down1;
                }else {
                    if(spriteNum == 1){
                        image = down2;
                    }else if(spriteNum == 2){
                        image = down3;
                    }
                }
                break;
            case "left":
                if (idle){
                    image = left1;
                }else {
                    if(spriteNum == 1){
                        image = left2;
                    }if(spriteNum == 2){
                        image = left3;
                    }
                }
                break;
            case "right":
                if (idle){
                    image = right1;
                }else {
                    if(spriteNum == 1){
                        image = right2;
                    }if(spriteNum == 2){
                        image = right3;
                    }
                }
                break;
            default:
                System.out.println("no images");
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}

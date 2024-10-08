
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    
    public GamePanel gp;
    
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, standing, right1, right2, left1, left2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, 
                        attackRight2, attackLeft1, attackLeft2;
    public String direction = "standing";
    
    public int walkingCounter = 0;
    public int walkingNum = 1;
    
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //invisible rectangle
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean attacking = false;
    
    
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;
    
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int type; // 0 = player, 1 = NPC, 2 = enemy
    
    //Character Status
    public int maxLife;
    public int life;
    
    public Entity(GamePanel gp){ //for npcs
        
        this.gp = gp;
        
    }
    
    //-------- NPC & ENEMY METHODS -----------//
    
    public void setAction(){
        
    }
    
    public void speak(){
      
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

    }
    
    //----------------------------------------//
    //-------------IMAGE SETUP----------------//
    
    public BufferedImage setUp(String imagePath, int width, int height){
        //This method draws the tile based on the parameters given. Saves draw time and provides less code
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{            
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return image;
    }
    
    //-------------------------------------------//
    //-----------------UPDATE--------------------//
    
    public void update(){
        
        setAction();
        
        collisionOn = false;
        gp.cChecker.checkTile(this); //will get the specific npc class
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemies);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        
        
        if(this.type == 2 && contactPlayer == true){
            
            if(gp.player.invincible == false){
                
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
        
        //if collision is false, player can move
        if(collisionOn == false){

            switch(direction){
                case "up" :
                    worldY -= speed;
                    break;
                case "down" :
                    worldY += speed;
                    break;
                case "left" :
                    worldX -= speed;
                    break;
                case "right" :
                    worldX += speed;
                    break;
            }
        }

        walkingCounter++;

        if(walkingCounter > 10){
            if(walkingNum == 1){
                walkingNum = 2;
            }
            else if(walkingNum == 2){
                walkingNum = 1;
            }

            walkingCounter = 0;
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 30){ // 30 means 0,5 seconds
                invincible = false;
                invincibleCounter = 0;
            }
        }                   
    }
    
    //-----------------------------------------//
    //-----------------DRAW--------------------//
    
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        boolean outOfBoundsX = (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX);

        boolean outOfBoundsY = (worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY);

        if(outOfBoundsX && outOfBoundsY){
            
            switch(direction){
                                            
                case "up" :
                    if(walkingNum == 1){
                        image = up1; //will change to up1, up2 etc once I make the pictures.
                    }
                    if(walkingNum == 2){
                        image = up2;
                    }
                     break;
                case "down" :
                    if(walkingNum == 1){
                        image = down1;
                    }
                    if(walkingNum == 2){
                        image = down2;
                    } 
                    break;
                case "left" :
                    if(walkingNum == 1){
                        image = left1;
                    }
                    if(walkingNum == 2){
                        image = left2;
                    }
                    break;
                case "right" :
                    if(walkingNum == 1){
                        image = right1;
                    }
                    if(walkingNum == 2){
                        image = right2;
                    }
                    break;
                default : image = standing; 
            }
            
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
      //-------------------------------------------------------------//  
}
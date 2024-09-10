
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Black_Door;
import object.OBJ_Black_Key;
import object.OBJ_Boots;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Map;
import object.OBJ_Potion;
import object.OBJ_Sign;

public class Player extends Entity{
    
    KeyHandler keyH;
    public int startingWorldX = 5 * gp.tileSize;
    public int startingWorldY = 4 * gp.tileSize;
    public final int screenX;
    public final int screenY;
    
    public int maps = 0;
    public int slain = 0;
    public boolean key = false;
    public boolean blackKey = false;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp); //calls constructor of entity class

        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2); //holds the "middle" of the screen where the player will be.
        screenY = gp.screenHeight/2 - (gp.tileSize/2); //since these 2 are final the player won't move.
        
        //Collision Settings
        //We don't want the collision rectangle to be the same size as to player since it will be hard to move 
        //through narrow passages or get close to solid objects.
        solidArea = new Rectangle(8, 16, 32, 32); //I can do: solidArea.x = 0 etc etc.
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        attackArea.width = 36;
        attackArea.height = 36;
               
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        getPlayerStartingPositions(gp.stage);
    }
    
    //------------PLAYER STATS AND POSITIONS--------------//
    
    public void setDefaultValues(){
        
        worldX = startingWorldX; //player's position on the world map.
        worldY = startingWorldY;
        speed = 4;
        direction = "down";
        
        //Player Status
        maxLife = 6;
        life = maxLife;
    }
    
    public void getPlayerStartingPositions(int stage){
        
        switch(stage){
            case 0:
                startingWorldX = 5 * gp.tileSize;
                startingWorldY = 4 * gp.tileSize;
                break;
            case 1:
                startingWorldX = 48 * gp.tileSize;
                startingWorldY = 25 * gp.tileSize;
                break;
            case 2:
                startingWorldX = 2 * gp.tileSize;
                startingWorldY = 25 * gp.tileSize;
                break;
            case 3:
                startingWorldX = 17 * gp.tileSize;
                startingWorldY = 25 * gp.tileSize;
                break;
        }
        
        worldX = startingWorldX;
        worldY = startingWorldY;
    }
    
    //------------------------------------------------//
    //----------------PLAYER IMAGES-------------------//
    
    public void getPlayerImage(){
                
        up1 = setUp("/playerImgs/up1", gp.tileSize, gp.tileSize);
        up2 = setUp("/playerImgs/up2", gp.tileSize, gp.tileSize);
        down1 = setUp("/playerImgs/down1", gp.tileSize, gp.tileSize);
        down2 = setUp("/playerImgs/down2", gp.tileSize, gp.tileSize);
        right1 = setUp("/playerImgs/right1", gp.tileSize, gp.tileSize);
        right2 = setUp("/playerImgs/right2", gp.tileSize, gp.tileSize);
        left1 = setUp("/playerImgs/left1", gp.tileSize, gp.tileSize);
        left2 = setUp("/playerImgs/left2", gp.tileSize, gp.tileSize);
        standing = setUp("/playerImgs/standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void getPlayerAttackImage(){
        
        attackUp1 = setUp("/playerImgs/attack_up1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setUp("/playerImgs/attack_up2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setUp("/playerImgs/attack_down1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setUp("/playerImgs/attack_down2", gp.tileSize, gp.tileSize * 2);
        attackRight1 = setUp("/playerImgs/attack_right1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setUp("/playerImgs/attack_right2", gp.tileSize * 2, gp.tileSize);
        attackLeft1 = setUp("/playerImgs/attack_left1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setUp("/playerImgs/attack_left2", gp.tileSize * 2, gp.tileSize);
        
    }
    
    //----------------------------------------------------------//          
    //------------------OBJECT INTERACTION----------------------//
    
    public void interactObject(int i){
        
        if(i != 999){
            if(gp.obj[gp.stage][i] instanceof OBJ_Key){
                gp.obj[gp.stage][i] = null;
                key = true;
            }
            
            if(gp.obj[gp.stage][i] instanceof OBJ_Black_Key){
                gp.obj[gp.stage][i] = null;
                blackKey = true;
            }
            
            if(gp.obj[gp.stage][i] instanceof OBJ_Map){
                gp.obj[gp.stage][i] = null;
                maps++;
            }
            
            if(gp.obj[gp.stage][i] instanceof OBJ_Potion){
                gp.obj[gp.stage][i] = null;
                maxLife++;
                life++;
            }
            
            if(gp.obj[gp.stage][i] instanceof OBJ_Boots){
                gp.obj[gp.stage][i] = null;
                speed++;
            }
            
            if(gp.obj[gp.stage][i] instanceof OBJ_Door && key){
                if(gp.keyH.enterPressed == true){
                    key = false;
                    gp.obj[gp.stage][i] = null;
                }                
            }
            
            if(gp.obj[gp.stage][i] instanceof OBJ_Black_Door && blackKey && 
                    gp.enemies[gp.stage][0] == null && gp.enemies[gp.stage][1] == null
                    && gp.enemies[gp.stage][2] == null && gp.enemies[gp.stage][3] == null 
                    && gp.enemies[gp.stage][4] == null && gp.enemies[gp.stage][5] == null
                    && gp.enemies[gp.stage][6] == null){
                if(gp.keyH.enterPressed == true){
                    blackKey = false;
                    gp.obj[gp.stage][i] = null;
                }                
            }
            
            if(gp.obj[gp.stage][i] instanceof OBJ_Sign){
                if(gp.keyH.enterPressed == true){
                    
                    gp.gameState = gp.dialogueState;
                    gp.obj[gp.stage][i].speak();
                    gp.keyH.enterPressed = false;
                }                
            }                      
        }
    }
    
    //------------------------------------------//
    //------------NPC INTERACTION---------------//
    
    public void interactNpc(int i){
        
        if(gp.keyH.enterPressed == true){
            if(i != 999){

                gp.gameState = gp.dialogueState;
                gp.npc[gp.stage][i].speak();            
                gp.keyH.enterPressed = false;            
            }
            else{            
                attacking = true;            
            }
        }
    }
    
    //----------------------------------------------//
    //-------------------FIGHTING-------------------//
    
    public void attack(){
        
        walkingCounter++;
        
        if(walkingCounter <= 5){
            walkingNum = 1;
        }
        if(walkingCounter > 5 && walkingCounter <= 25){
            walkingNum = 2;
            
            //Save the current potisions.
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            //Change player's position for the attackArea
            switch(direction){
                case "up": 
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;                    
            }
            
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemies);
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            
            damageEnemy(enemyIndex);
            
        }
        if(walkingCounter > 25){
            walkingNum = 1;
            walkingCounter = 0;
            attacking = false;
        }        
    }
    
    public void contactEnemy(int i){
        if(i != 999){
            
            if(invincible == false){
                life -= 1;
                invincible = true;
            }            
        }
    }
    
    public void damageEnemy(int i){
        
        if(i != 999){
            
            if(gp.enemies[gp.stage][i].invincible == false){
                gp.enemies[gp.stage][i].life -= 1;
                gp.enemies[gp.stage][i].invincible = true;
                
                if(gp.enemies[gp.stage][i].life <= 0){
                    gp.enemies[gp.stage][i] = null;
                    if(gp.stage == 2){
                        slain++;
                    }
                }
            }
        }
    }
    
    //-------------------------------------------------//   
    //--------------------UPDATE-----------------------//
    
    public void update(){
                
        if(attacking == true){
            attack(); //just so the code will be more clean
        }        
        else if(keyH.upPressed == true || keyH.downPressed == true || 
                keyH.rightPressed == true || keyH.leftPressed == true || 
                keyH.enterPressed == true){
            if(keyH.upPressed == true) {
                direction = "up";                
            }

            if(keyH.downPressed == true) {
                direction = "down";                
            }

            if(keyH.leftPressed == true) {
                direction = "left";                
            }

            if(keyH.rightPressed == true) {
                direction = "right";                
            }
        
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //Check object collision
            int objectIndex = gp.cChecker.checkObject(this, true);
            
            interactObject(objectIndex);
            
            //Check npc collision
            
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc); //pass player class and npc array.
            interactNpc(npcIndex);
            
            //Check enemy collision
            
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemies);
            contactEnemy(enemyIndex);
            
            
            //if collision is false, player can move
            if(collisionOn == false && keyH.enterPressed == false){
                
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
            
            gp.keyH.enterPressed = false;
            
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
        }
        
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){ // 60 means 1 second
                invincible = false;
                invincibleCounter = 0;
            }
        }                
    }
    
    //-------------------------------------------------//
    //-------------------DRAW--------------------------//
        
    public void draw(Graphics2D g2){
        
        //places the player in playerX, playerY position with tileSize width and height.
        BufferedImage image = null;
        
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch(direction){
            
            case "up" :
                if(attacking == false){
                    if(walkingNum == 1){
                        image = up1;
                    }
                    if(walkingNum == 2){
                        image = up2;
                    }
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(walkingNum == 1){
                        image = attackUp1;
                    }
                    if(walkingNum == 2){
                        image = attackUp2;
                    }
                }
                break;
            case "down" :
                if(attacking == false){
                    if(walkingNum == 1){
                        image = down1;
                    }
                    if(walkingNum == 2){
                        image = down2;
                    }
                }
                if(attacking == true){
                    if(walkingNum == 1){
                        image = attackDown1;
                    }
                    if(walkingNum == 2){
                        image = attackDown2;
                    }
                }
                break;
            case "left" :
                if(attacking == false){
                    if(walkingNum == 1){
                        image = left1;
                    }
                    if(walkingNum == 2){
                        image = left2;
                    }
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(walkingNum == 1){
                        image = attackLeft1;
                    }
                    if(walkingNum == 2){
                        image = attackLeft2;
                    }
                }                
                break;
            case "right" :
                if(attacking == false){
                    if(walkingNum == 1){
                        image = right1;
                    }
                    if(walkingNum == 2){
                        image = right2;
                    }
                }
                if(attacking == true){
                    if(walkingNum == 1){
                        image = attackRight1;
                    }
                    if(walkingNum == 2){
                        image = attackRight2;
                    }
                }                
                break;
            default : image = standing; 
        }
            
        g2.drawImage(image, tempScreenX, tempScreenY, null);
                
    }
    
    //--------------------------------------------------//
}

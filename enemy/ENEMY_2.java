package enemy;

import entity.Entity;
import java.util.Random;
import main.GamePanel;

public class ENEMY_2 extends Entity{
    
    public ENEMY_2(GamePanel gp){
        super(gp);
        
        type = 2;
        name = "Enemy 1";
        speed = 2;
        maxLife = 4;
        life = maxLife;
               
        getImage();
    }
    
    public void getImage(){
        
        standing = setUp("/enemyImgs/enemy1", gp.tileSize, gp.tileSize);
        up1 = setUp("/enemyImgs/enemy2_up1", gp.tileSize, gp.tileSize);
        up2 = setUp("/enemyImgs/enemy2_up2", gp.tileSize, gp.tileSize);
        down1 = setUp("/enemyImgs/enemy2_down1", gp.tileSize, gp.tileSize);
        down2 = setUp("/enemyImgs/enemy2_down2", gp.tileSize, gp.tileSize);
        right1 = setUp("/enemyImgs/enemy2_right1", gp.tileSize, gp.tileSize);
        right2 = setUp("/enemyImgs/enemy2_right2", gp.tileSize, gp.tileSize);
        left1 = setUp("/enemyImgs/enemy2_left1", gp.tileSize, gp.tileSize);
        left2 = setUp("/enemyImgs/enemy2_left2", gp.tileSize, gp.tileSize);
    }
    
    public void setAction(){
        
        actionLockCounter++;
        
        if(actionLockCounter == 120){
            Random random = new Random();
        
            int i = random.nextInt(100) + 1;
            if(i <= 25){
                direction = "up";
            }

            if(i > 25 && i <= 50){
                direction = "down";
            }

            if(i > 50 && i <= 75){
                direction = "left";
            }

            if(i > 75 && i <= 100){
                direction = "right";
            }
            
            actionLockCounter = 0;
            
        }
    }    
}
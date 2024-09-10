
package enemy;

import entity.Entity;
import main.GamePanel;

public class ENEMY_1 extends Entity{
    
    public ENEMY_1(GamePanel gp){
        super(gp);
        
        type = 2;
        name = "Enemy 1";
        speed = 0;
        maxLife = 4;
        life = maxLife;
                
        getImage();
    }
    
    public void getImage(){
        
        standing = setUp("/enemyImgs/enemy1", gp.tileSize, gp.tileSize);
    }
}
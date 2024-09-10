
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity{
    
    public OBJ_Potion(GamePanel gp){
        
        super(gp);
        
        name = "Potion";
        standing = setUp("/objects/potion", gp.tileSize, gp.tileSize);
    }    
}
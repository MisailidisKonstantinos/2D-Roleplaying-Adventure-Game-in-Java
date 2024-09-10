
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Black_Key extends Entity{
    
    
    public OBJ_Black_Key(GamePanel gp){
        
        super(gp);
        
        name = "Black Key";
        standing = setUp("/objects/black_key", gp.tileSize, gp.tileSize);
    }
}

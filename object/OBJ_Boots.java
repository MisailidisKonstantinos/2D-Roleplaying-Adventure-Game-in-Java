
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{

    
    public OBJ_Boots(GamePanel gp){
        
        super(gp);
        
        name = "Boots";
        standing = setUp("/objects/boots", gp.tileSize, gp.tileSize);
    }    
}
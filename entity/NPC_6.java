
package entity;

import main.GamePanel;

public class NPC_6 extends Entity{
    
    public NPC_6(GamePanel gp){

        super(gp);

        direction = "standing";
        speed = 0;

        getNPCImage();
        setDialogue();
    }
    
    public void getNPCImage(){

        standing = setUp("/npcImgs/npc1_standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void setDialogue(){
        
        dialogues[0] = "We reached his secret hideout but we only found\n"
                     + "this locked door. I guess it leads to the base of their\n"
                     + "operations.";
        dialogues[1] = "Still we need a key to move forward. Search the\n"
                     + "forest hero and find the key. It's our only hope!";
        dialogues[2] = "Keep an eye open for lost items inside the forest\n"
                     + "There are powerful items that will grant you their\n"
                     + "strength. Something you could use for the final battle!";
    }   
}
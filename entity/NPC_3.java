
package entity;

import main.GamePanel;

public class NPC_3 extends Entity{
    
    public NPC_3(GamePanel gp){

        super(gp);

        direction = "standing";
        speed = 0;

        getNPCImage();
        setDialogue();
    }
        
    public void getNPCImage(){

        standing = setUp("/npcImgs/npc3_standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void setDialogue(){
        
        dialogues[0] = "Hello my friend!";
        dialogues[1] = "Before I give you information about the lost map,\n"
                     + "I feel the need to thank you for your willingness\n"
                     + "to assist us!";
        dialogues[2] = "I followed two of the bandits to a hunting cabin\n"
                     + "to the south. They had a piece of the map with\n"
                     + "them. I may be a spy, but a warrior? That I am not!";
        dialogues[3] = "But I believe you could defeat those two thugs.\n"
                     + "Good luck my friend!";
    }   
}
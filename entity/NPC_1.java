
package entity;

import main.GamePanel;

public class NPC_1 extends Entity{
    
    public NPC_1(GamePanel gp){
        
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
        
        dialogues[0] = "Hello adventurer!";
        dialogues[1] = "We lost a very important map as it was stolen\n"
                     + "by bandits. It was ripped in four pieces and\n"
                     + "scattered on this land. Can you help us recover\n"
                     + "the lost pieces?";
        dialogues[2] = "The evil overlord Zargnox is preparing to wage\n"
                     + "war! Against us? It is unknown but still we\n"
                     + "cannot allow him to do so.";
        dialogues[3] = "The map reveals the location of his hideout in\n"
                     + "the Emerald-Leaf Forest. If you recover the lost\n"
                     + "pieces we shall have a chance to interrupt his plans";
        dialogues[4] = "Speak with our brethren around the camp. They\n"
                     + "shall offer you clues, or perhaps rumors about\n"
                     + "the whereabouts of the map.";
    }    
}
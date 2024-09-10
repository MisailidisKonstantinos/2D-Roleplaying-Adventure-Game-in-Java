
package entity;

import main.GamePanel;


public class NPC_5 extends Entity{
    
    public NPC_5(GamePanel gp){

        super(gp);

        direction = "standing";
        speed = 0;

        getNPCImage();
        setDialogue();
    }
        
    public void getNPCImage(){

        standing = setUp("/npcImgs/npc5_standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void setDialogue(){
        
        dialogues[0] = "Damn Zargnox and his gang of thieves!!";
        dialogues[1] = "I am sorry friend. I am just angry and worried.\n"
                + "But also not brave...";
        dialogues[2] = "I heard rumors that some of the thieves foolishly\n"
                     + "went deep into uncharted parts of the forest and\n"
                     + "got lost. I dare not step into those parts.";
        dialogues[3] = "If you believe you can survive the journey I shall\n"
                     + "advice you to travel east past lake Mirror-Water.\n"
                     + "I wish you good luck... you will need it.";
    }    
}
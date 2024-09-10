
package entity;

import main.GamePanel;

public class NPC_2 extends Entity{
    
        public NPC_2(GamePanel gp){
        
            super(gp);

            direction = "standing";
            speed = 0;

            getNPCImage();
            setDialogue();
    }
        
    public void getNPCImage(){

        standing = setUp("/npcImgs/npc2_standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void setDialogue(){
        
        dialogues[0] = "Greetings my brother!";
        dialogues[1] = "I was informed that you are willing to help us\n"
                     + "recover the stolen map. I am truly grateful to\n"
                     + "hear such news!";
        dialogues[2] = "Our spies witnessed some of the bandits\n"
                     + "following the dirt road outside the camp. \n"
                     + "There is no doubt that you will find one of \n"
                     + "the missing pieces if you follow the same road.";
    }    
}
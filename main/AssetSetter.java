
package main;

import enemy.ENEMY_1;
import enemy.ENEMY_2;
import enemy.ENEMY_3;
import entity.NPC_1;
import entity.NPC_2;
import entity.NPC_3;
import entity.NPC_4;
import entity.NPC_5;
import entity.NPC_6;
import entity.NPC_7;
import entity.NPC_8;
import entity.NPC_9;
import object.OBJ_Black_Door;
import object.OBJ_Black_Key;
import object.OBJ_Boots;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Map;
import object.OBJ_Potion;
import object.OBJ_Sign;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    //----------------SET OBJECT---------------//
    
    public void setObject(){
        
        int map = 0;
        gp.obj[map][0] = new OBJ_Map(gp);
        gp.obj[map][0].worldX = gp.tileSize * 2;
        gp.obj[map][0].worldY = gp.tileSize * 42;

        gp.obj[map][1] = new OBJ_Map(gp);
        gp.obj[map][1].worldX = gp.tileSize * 30;
        gp.obj[map][1].worldY = gp.tileSize * 1;

        gp.obj[map][2] = new OBJ_Map(gp);
        gp.obj[map][2].worldX = gp.tileSize * 25;
        gp.obj[map][2].worldY = gp.tileSize * 47;

        gp.obj[map][3] = new OBJ_Map(gp);
        gp.obj[map][3].worldX = gp.tileSize * 46;
        gp.obj[map][3].worldY = gp.tileSize * 2;

        map = 1; //etc
        gp.obj[map][0] = new OBJ_Door(gp);
        gp.obj[map][0].worldX = gp.tileSize * 49;
        gp.obj[map][0].worldY = gp.tileSize * 25;
        
        gp.obj[map][1] = new OBJ_Boots(gp);
        gp.obj[map][1].worldX = gp.tileSize * 2;
        gp.obj[map][1].worldY = gp.tileSize * 38;
        
        gp.obj[map][2] = new OBJ_Potion(gp);
        gp.obj[map][2].worldX = gp.tileSize * 40;
        gp.obj[map][2].worldY = gp.tileSize * 34;
        
        gp.obj[map][3] = new OBJ_Potion(gp);
        gp.obj[map][3].worldX = gp.tileSize * 44;
        gp.obj[map][3].worldY = gp.tileSize * 48;
        
        gp.obj[map][4] = new OBJ_Sign(gp);
        gp.obj[map][4].worldX = gp.tileSize * 17;
        gp.obj[map][4].worldY = gp.tileSize * 0;
        
        gp.obj[map][5] = new OBJ_Key(gp);
        gp.obj[map][5].worldX = gp.tileSize * 48;
        gp.obj[map][5].worldY = gp.tileSize * 1;
        
        map = 2;
        gp.obj[map][0] = new OBJ_Black_Door(gp);
        gp.obj[map][0].worldX = gp.tileSize * 41;
        gp.obj[map][0].worldY = gp.tileSize * 21;
        
        gp.obj[map][1] = new OBJ_Black_Key(gp);
        gp.obj[map][1].worldX = gp.tileSize * 9;
        gp.obj[map][1].worldY = gp.tileSize * 3;
        
        gp.obj[map][2] = new OBJ_Door(gp);
        gp.obj[map][2].worldX = gp.tileSize * 0;
        gp.obj[map][2].worldY = gp.tileSize * 25;
        
        map = 3;
        gp.obj[map][0] = new OBJ_Black_Door(gp);
        gp.obj[map][0].worldX = gp.tileSize * 14;
        gp.obj[map][0].worldY = gp.tileSize * 25;      
    }
    
    //---------------------------------------//
    //----------------SET NPC----------------//
    
    public void setNPC(){
        
        int map = 0;
        gp.npc[map][0] = new NPC_1(gp);
        gp.npc[map][0].worldX = gp.tileSize * 4;
        gp.npc[map][0].worldY = gp.tileSize * 3;
        
        gp.npc[map][1] = new NPC_2(gp);
        gp.npc[map][1].worldX = gp.tileSize * 9;
        gp.npc[map][1].worldY = gp.tileSize * 4;
        
        gp.npc[map][2] = new NPC_3(gp);
        gp.npc[map][2].worldX = gp.tileSize * 2;
        gp.npc[map][2].worldY = gp.tileSize * 10;
        
        gp.npc[map][3] = new NPC_4(gp);
        gp.npc[map][3].worldX = gp.tileSize * 14;
        gp.npc[map][3].worldY = gp.tileSize * 13;
        
        gp.npc[map][4] = new NPC_5(gp);
        gp.npc[map][4].worldX = gp.tileSize * 6;
        gp.npc[map][4].worldY = gp.tileSize * 8;
        
        map = 1;
        gp.npc[map][0] = new NPC_6(gp);
        gp.npc[map][0].worldX = gp.tileSize * 46;
        gp.npc[map][0].worldY = gp.tileSize * 24;
        
        map = 2;
        gp.npc[map][0] = new NPC_7(gp);
        gp.npc[map][0].worldX = gp.tileSize * 43;
        gp.npc[map][0].worldY = gp.tileSize * 46;
        
        gp.npc[map][1] = new NPC_8(gp);
        gp.npc[map][1].worldX = gp.tileSize * 3;
        gp.npc[map][1].worldY = gp.tileSize * 24;
        
        map = 3;
        gp.npc[map][0] = new NPC_9(gp);
        gp.npc[map][0].worldX = gp.tileSize * 16;
        gp.npc[map][0].worldY = gp.tileSize * 24;
    }
    
    //--------------------------------------------//
    //-----------------SET ENEMY------------------//
    
    public void setEnemy(){

        int map = 0;
        gp.enemies[map][0] = new ENEMY_1(gp);
        gp.enemies[map][0].worldX = gp.tileSize * 24;
        gp.enemies[map][0].worldY = gp.tileSize * 41;
        
        gp.enemies[map][1] = new ENEMY_1(gp);
        gp.enemies[map][1].worldX = gp.tileSize * 26;
        gp.enemies[map][1].worldY = gp.tileSize * 41;
       
        map = 1;
        gp.enemies[map][0] = new ENEMY_2(gp);
        gp.enemies[map][0].worldX = gp.tileSize * 12;
        gp.enemies[map][0].worldY = gp.tileSize * 11;
        
        gp.enemies[map][1] = new ENEMY_1(gp);
        gp.enemies[map][1].worldX = gp.tileSize * 17;
        gp.enemies[map][1].worldY = gp.tileSize * 7;
        
        map = 2;
        gp.enemies[map][0] = new ENEMY_2(gp);
        gp.enemies[map][0].worldX = gp.tileSize * 27;
        gp.enemies[map][0].worldY = gp.tileSize * 2;
        
        gp.enemies[map][1] = new ENEMY_2(gp);
        gp.enemies[map][1].worldX = gp.tileSize * 29;
        gp.enemies[map][1].worldY = gp.tileSize * 2;
        
        gp.enemies[map][2] = new ENEMY_2(gp);
        gp.enemies[map][2].worldX = gp.tileSize * 5;
        gp.enemies[map][2].worldY = gp.tileSize * 3;
        
        gp.enemies[map][3] = new ENEMY_2(gp);
        gp.enemies[map][3].worldX = gp.tileSize * 11;
        gp.enemies[map][3].worldY = gp.tileSize * 46;
        
        gp.enemies[map][4] = new ENEMY_2(gp);
        gp.enemies[map][4].worldX = gp.tileSize * 22;
        gp.enemies[map][4].worldY = gp.tileSize * 46;
        
        gp.enemies[map][5] = new ENEMY_2(gp);
        gp.enemies[map][5].worldX = gp.tileSize * 22;
        gp.enemies[map][5].worldY = gp.tileSize * 48;
        
        gp.enemies[map][6] = new ENEMY_2(gp);
        gp.enemies[map][6].worldX = gp.tileSize * 30;
        gp.enemies[map][6].worldY = gp.tileSize * 25;
        
        map = 3;
        gp.enemies[map][0] = new ENEMY_3(gp);
        gp.enemies[map][0].worldX = gp.tileSize * 25;
        gp.enemies[map][0].worldY = gp.tileSize * 25;
    }
    //----------------------------------------------//
}
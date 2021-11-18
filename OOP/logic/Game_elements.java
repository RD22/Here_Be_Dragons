package dkeep.logic;

public class Game_elements {
	protected int[] pos_element = {0,0};
	//protected Cur_Map cur_map = new Cur_Map(); 	// object of Cur_Map class
}

class Hero extends Game_elements{
	
	protected boolean owns_sword;
	
	static Hero myhero = new Hero();
	
	public static int[] get_hero_pos(String[][] new_map) {
		for(int i=0; i< new_map.length; i++) {
			for(int j=0; j<new_map[i].length; j++) {
					if(new_map[i][j] == "H" || new_map[i][j] == "A") {
					myhero.pos_element =  new int[] {i,j};
				}
		
			}
		}
		return myhero.pos_element;
	}
	
}


class Dragon extends Game_elements{
	
	static boolean dead;
	static boolean step_sword;
	//static int[][] dragons_pos;
	static Dragon mydragon = new Dragon();
	
	
	public static int[] get_dragon_pos(String[][] new_map) {
		
		for(int i=0; i< new_map.length; i++) {
			for(int j=0; j<new_map[i].length; j++) {
					if(new_map[i][j] == "D") {
					mydragon.pos_element =  new int[] {i,j};
				}
		
			}
		}
		return mydragon.pos_element;
	}
}

class Key extends Game_elements{
	static Key keyobj = new Key();
	static boolean own; // 
	
}

class Sword extends Game_elements{
	static Sword Sword = new Sword();
	static boolean own;
	
	public static int[] get_sword_pos(String[][] new_map) {
		for(int i=0; i< new_map.length; i++) {
			for(int j=0; j<new_map[i].length; j++) {
					if(new_map[i][j] == "D") {
						Sword.pos_element =  new int[] {i,j};
				}
		
			}
		}
		return Sword.pos_element;
	}
}





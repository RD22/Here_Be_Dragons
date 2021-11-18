package dkeep.logic;

import dkeep.cli.Main;

public class Game_State {
	
	protected boolean blocked; 
	protected boolean victory;
	protected boolean game_over;
	protected boolean colision;
	
	Main main = new Main();
	
	public Game_elements elem = new Game_elements();	// object of super class Game_elements
										 				//stores game elements
	
	public void status(String type, int offset, String[][] map,int num_dragons) {
			int [] hero_pos = Hero.get_hero_pos(map);
	
			if(type == "Row") {
				
				set_victory_status_row( map, offset, hero_pos);
				
				set_blocked_status_row(map,offset,hero_pos,num_dragons);
				
				set_sword_status_row( map, offset, hero_pos);
				
				set_game_over_status_row( map, offset, hero_pos);
				
				set_dragon_dead_status_row(map, offset, hero_pos);
				
				
				if(Key.own == true) System.out.println("Hero has the Key!");	
			}
			
			else if (type == "Column") {
				
				set_victory_status_column( map, offset, hero_pos);
				
				set_blocked_status_column(map,offset,hero_pos,num_dragons);
	
				set_sword_status_column( map, offset, hero_pos);
				
				set_game_over_status_column( map, offset, hero_pos);
				
				set_dragon_dead_status_column(map, offset, hero_pos);
				
				if(Key.own == true) System.out.println("Hero has the Key!");
			}	
	}

	
	public void set_blocked_status_row(String[][] map,int offset,int [] hero_pos,int num_dragons) {
		if (map[hero_pos[0]+offset][hero_pos[1]] == "X" || ((map[hero_pos[0]+offset][hero_pos[1]] == "E" && Key.own == false))) {	
			System.out.println("Path blocked by a wall or door! : try again!");
			System.out.println("\n");
			blocked = true;
			imprime_mapa(map);
			Main.scan_movimento(num_dragons);
			// block
		}
		else  blocked = false; 
	}
	
	
	public void set_blocked_status_column(String[][] map,int offset,int [] hero_pos,int num_dragons) {
		if (map[hero_pos[0]][hero_pos[1]+offset] == "X" || ((map[hero_pos[0]][hero_pos[1]+offset] == "E" && Key.own == false))) {	
			System.out.println("Path blocked by a wall or door! : try again!");
			System.out.println("\n");
			blocked = true;
			imprime_mapa(map);
			Main.scan_movimento(num_dragons);
			// block
		}
		else  blocked = false; 
	}


	public boolean set_victory_status_row(String[][] map,int offset,int [] hero_pos) {
		if(map[hero_pos[0]+offset][hero_pos[1]] == "E" && Dragon.dead == true) {
			game_victory(map);
			return victory = true; 
		}
		else {
			return victory = false;
		}
	}


	public boolean set_victory_status_column(String[][] map,int offset,int [] hero_pos) {
		if(map[hero_pos[0]][hero_pos[1]+offset] == "E" && Dragon.dead == true) {
			game_victory(map);
			return victory = true; 
		}
		else 
			return victory = false;
	}


	public boolean set_game_over_status_row(String[][] map,int offset,int [] hero_pos) {
		//boolean block = set_blocked_status_row(map,offset,hero_pos);
		if (blocked == true) return game_over = false;
		else if(map[hero_pos[0]+2*offset][hero_pos[1]] == "D" && Sword.own == false) {		// hero dies
			return game_over = true;
		}
		else return game_over = false;
	}


	public boolean set_game_over_status_column(String[][] map,int offset,int [] hero_pos) {
		//boolean block = set_blocked_status_column(map,offset,hero_pos);
		if (blocked == true) return game_over = false;
		else if(map[hero_pos[0]][hero_pos[1]+2*offset] == "D" && Sword.own == false) {		// hero dies
			return game_over = true;
		}
		else return game_over = false;
	}


	public void set_sword_status_row(String[][] map,int offset,int [] hero_pos) {
		Hero hero = new Hero();		
		if(map[hero_pos[0]+offset][hero_pos[1]] == "S") {
			hero.owns_sword = true;
			Sword.own = true;		// from this moment on hero has sword
		}	
		
	}
	
	public void set_sword_status_column(String[][] map,int offset,int [] hero_pos) {
		Hero hero = new Hero();		
		if(map[hero_pos[0]][hero_pos[1]+offset] == "S") {
			hero.owns_sword = true;
			Sword.own = true;		// from this moment on hero has sword
		}
	}


	public boolean set_dragon_dead_status_row(String[][] map,int offset,int [] hero_pos) {
		//boolean block = set_blocked_status_row(map,offset,hero_pos);
		if (blocked == true)  {
			return Dragon.dead = false;
		}
		else if(map[hero_pos[0]+2*offset][hero_pos[1]] == "D" && Sword.own == true) {		// dragon dies
			Key.own = true;
			//blocked = false;
			return Dragon.dead = true;
		}
		else return false;
	 }
	
	public boolean set_dragon_dead_status_column(String[][] map,int offset,int [] hero_pos) {
		//boolean block = set_blocked_status_column(map,offset,hero_pos);
		if (blocked == true)  {
			Key.own = false;
			return Dragon.dead = false;
		}
		else if(map[hero_pos[0]][hero_pos[1]+2*offset] == "D" && Sword.own == true) {		// dragon dies
			//blocked = false;
			Key.own = true;
			return Dragon.dead = true;
		}
		else return false;
	 }

	public void wall_block(String[][] map) {
		imprime_mapa(map);
		System.out.println("Path blocked by a Wall or Door! : Try again");
		
	}
	
	public void game_over(String[][] map) {
		imprime_mapa(map);
		System.out.println("GAME OVER! You got too close to the Dragon! Better luck next time :)");
		System.exit(0);
	}
	
	public void game_victory(String[][] map) {
		imprime_mapa(map);
		System.out.println("CONGRATS! You Won the Game and escaped the Dragon! :)");
		System.exit(0);
	}
	
	public void imprime_mapa(String[][] map) {
		for(int i=0; i< map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		//if(Key.own == true) System.out.println("Hero has the Key!");
		//if (key == 1) System.out.println("HERO has the key now!");
	}
}

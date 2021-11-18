package dkeep.logic;

import dkeep.cli.Main;
import java.util.Random;

public class Cur_Map {
	
	Main game = new Main();
	
	Game_State game_state = new Game_State();
	
	Game_elements elem = new Game_elements();
	
	
	public void move_hero_columns(int offset,int num_dragons) {
		int [] hero_pos = Hero.get_hero_pos(Main.map);
		
		if (game_state.set_victory_status_column(Main.map,offset,hero_pos) == true) {
			game_state.game_victory(Main.map);
		}
		
		else if (game_state.set_game_over_status_column(Main.map,offset,hero_pos) == true) {
			Main.map[hero_pos[0]][hero_pos[1]] = " ";
			Main.map[hero_pos[0]][hero_pos[1]+offset] =  "H";
			game_state.game_over(Main.map);
		}
		
		else if (game_state.set_dragon_dead_status_column(Main.map,offset,hero_pos) == true) {
				Main.map[hero_pos[0]][hero_pos[1]] = " ";
				Main.map[hero_pos[0]][hero_pos[1]+offset] =  "A";
				release_dragon(num_dragons);
		}
		
		else if (Sword.own == true && game_state.blocked == false) {
			Main.map[hero_pos[0]][hero_pos[1]] = " ";
			Main.map[hero_pos[0]][hero_pos[1]+offset] =  "A";
		}
		
		else if (Sword.own == false && game_state.blocked == false) {
			Main.map[hero_pos[0]][hero_pos[1]] = " ";
			Main.map[hero_pos[0]][hero_pos[1]+offset] =  "H";
		}
		
		if(Key.own == false) move_dragon(num_dragons);
	}
	
	public void move_hero_rows(int offset,int num_dragons) {
		
		int [] hero_pos = Hero.get_hero_pos(Main.map);
		
		if (game_state.set_victory_status_row(Main.map,offset,hero_pos) == true) {
			game_state.game_victory(Main.map);
		}
		
		else if (game_state.set_game_over_status_row(Main.map,offset,hero_pos) == true) {
			Main.map[hero_pos[0]][hero_pos[1]] = " ";
			Main.map[hero_pos[0]+offset][hero_pos[1]] =  "H"; 
			game_state.game_over(Main.map);
		}
		
		else if (game_state.set_dragon_dead_status_row(Main.map,offset,hero_pos) == true) {
			Main.map[hero_pos[0]][hero_pos[1]] = " ";
			Main.map[hero_pos[0]+offset][hero_pos[1]] =  "A";
			release_dragon(num_dragons);
		}
		
		else if (Sword.own == true && game_state.blocked == false) {
			Main.map[hero_pos[0]][hero_pos[1]] = " ";
			Main.map[hero_pos[0]+offset][hero_pos[1]] =  "A";
		}
		
		else if (Sword.own == false && game_state.blocked == false) {
			Main.map[hero_pos[0]][hero_pos[1]] = " ";
			Main.map[hero_pos[0]+offset][hero_pos[1]] =  "H";
		}
		
		if(Dragon.dead == false) move_dragon(num_dragons);
	}
	
	public void release_dragon(int num_dragons) {
		int[] dragon_pos = Dragon.get_dragon_pos(Main.map);
		Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
		Dragon.dead = true;
		game_state.imprime_mapa(Main.map);
		System.out.println("Dragon has been slained!");
		Main.scan_movimento(num_dragons);
	}
	
	
	public void move_dragon(int num_dragons) {
			int[] dragon_pos = Dragon.get_dragon_pos(Main.map);
			Random rand = new Random();
			int rand_int1 = rand.nextInt(3);
			if(rand_int1 == 0) move_dragon_up(dragon_pos,num_dragons);
			else if(rand_int1 == 1) move_dragon_down(dragon_pos,num_dragons);
			else if(rand_int1 == 2)  move_dragon_right(dragon_pos,num_dragons);
			else if(rand_int1 == 3)  move_dragon_left(dragon_pos,num_dragons);
	}
	
	public void move_dragon_up(int[] dragon_pos, int num_dragons) {
		if(Main.map[dragon_pos[0]+1][dragon_pos[1]] != "X" &&
				Main.map[dragon_pos[0]+1][dragon_pos[1]] != "E") {		// moves up
			if (Dragon.step_sword = false){
				Main.map[dragon_pos[0]][dragon_pos[1]] = "S";
				Main.map[dragon_pos[0]+1][dragon_pos[1]] = "D";
				Dragon.step_sword = true;
			}
			
			else if(Main.map[dragon_pos[0]+1][dragon_pos[1]] == "H") {
				game_state.game_over(Main.map);
			}
			
			else if(Main.map[dragon_pos[0]+1][dragon_pos[1]] == "A") {
				release_dragon(num_dragons);
			}
			
			else if(Main.map[dragon_pos[0]+1][dragon_pos[1]] == "S") {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]+1][dragon_pos[1]] = "F";
				Dragon.step_sword = false;
			}
			else {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]+1][dragon_pos[1]] = "D";
			}
		}
		else move_dragon(num_dragons);
	}
	
	public void move_dragon_down(int[] dragon_pos, int num_dragons) {
		if(Main.map[dragon_pos[0]-1][dragon_pos[1]] != "X"
				&& Main.map[dragon_pos[0]-1][dragon_pos[1]] != "E") {	// moves down
			if (Dragon.step_sword = false){
				Main.map[dragon_pos[0]][dragon_pos[1]] = "S";
				Main.map[dragon_pos[0]-1][dragon_pos[1]] = "D";
				Dragon.step_sword = true;
			}
			
			else if(Main.map[dragon_pos[0]-1][dragon_pos[1]] == "H") {
				game_state.game_over(Main.map);
			}
			
			else if(Main.map[dragon_pos[0]-1][dragon_pos[1]] == "A") {
				release_dragon(num_dragons);
			}
			
			else if(Main.map[dragon_pos[0]-1][dragon_pos[1]] == "S") {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]-1][dragon_pos[1]] = "F";
				Dragon.step_sword = false;
			}
			else {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]-1][dragon_pos[1]] = "D";
			}
		}
		else move_dragon(num_dragons);
	}

	public void move_dragon_right(int[] dragon_pos,int num_dragons) {
		if(Main.map[dragon_pos[0]][dragon_pos[1]+1] != "X"
				&& Main.map[dragon_pos[0]][dragon_pos[1]+1] != "E") {	// moves right
			if (Dragon.step_sword = false){
				Main.map[dragon_pos[0]][dragon_pos[1]] = "S";
				Main.map[dragon_pos[0]][dragon_pos[1]+1] = "D";
				Dragon.step_sword = true;
			}
			
			else if(Main.map[dragon_pos[0]][dragon_pos[1]+1] == "H") {
				game_state.game_over(Main.map);
			}
			
			else if(Main.map[dragon_pos[0]][dragon_pos[1]+1] == "A") {
				release_dragon(num_dragons);
			}
			
			else if(Main.map[dragon_pos[0]][dragon_pos[1]+1] == "S") {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]][dragon_pos[1]+1] = "F";
				Dragon.step_sword = false;
			}
			else {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]][dragon_pos[1]+1] = "D";
			}
			}
			else move_dragon(num_dragons);
	}

	public void move_dragon_left(int[] dragon_pos,int num_dragons) {
		 	if(Main.map[dragon_pos[0]][dragon_pos[1-1]] != "X"
				&& Main.map[dragon_pos[0]][dragon_pos[1-1]] != "E") {		// moves left
			if (Dragon.step_sword = false){
				Main.map[dragon_pos[0]][dragon_pos[1]] = "S";
				Main.map[dragon_pos[0]][dragon_pos[1]-1] = "D";
				Dragon.step_sword = true;
			}
			else if(Main.map[dragon_pos[0]][dragon_pos[1]-1] == "H") {
				game_state.game_over(Main.map);
			}
			
			else if(Main.map[dragon_pos[0]][dragon_pos[1]-1] == "A") {
				release_dragon(num_dragons);
			}
			
			else if(Main.map[dragon_pos[0]][dragon_pos[1]-1] == "S") {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]][dragon_pos[1]-1] = "F";
				Dragon.step_sword = false;
			}
			else {
				Main.map[dragon_pos[0]][dragon_pos[1]] = " ";
				Main.map[dragon_pos[0]][dragon_pos[1-1]] = "D";
			}
		}
		else move_dragon(num_dragons);
	}
}
		
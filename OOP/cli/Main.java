package dkeep.cli;

import java.util.Random;
import java.util.Scanner;

import dkeep.logic.*;

public class Main {

	 static public String[][] map  = { {"X","X","X","X","X","X","X","X","X","X"},	//1
			{"X","H"," "," "," "," "," "," "," ","X"}, 			//2
			{"X"," ","X","X"," ","X"," ","X"," ","X"},		//3
			{"X","D","X","X"," ","X"," ","X"," ","X"},			//4
			{"X"," ","X","X"," ","X"," ","X"," ","X"},			//5
			{"X"," "," "," "," "," "," ","X"," ","E"},			//6
			{"X"," ","X","X"," ","X"," ","X"," ","X"},			//7
			{"X"," ","X","X"," ","X"," ","X"," ","X"},			//8
			{"X","S","X","X"," "," "," "," "," ","X"},			//9
			{"X","X","X","X","X","X","X","X","X","X"}			//10
	};
	
	static Game_State game_stat = new Game_State();		// object of class Game_State
	static Game_elements elem = new Game_elements();
	static Cur_Map mapa = new Cur_Map();
	
	
	public static void scan_movimento(int num_dragons) {
			System.out.println("enter single-character commands to move the main character (\"hero\"):");
			System.out.println("you have 4 possible directions (up('w'), down('s'), left('a), right('d'))");	
			Scanner s = new Scanner(System.in);
			char direction =  s.next().charAt(0);
			switch (direction) {
				case 'w' : 			// sobe 1 linha (verificar linha acima) 
					game_stat.status("Row", -1, map,num_dragons);
					mapa.move_hero_rows(-1,num_dragons);
					game_stat.status("Row", 1,map,num_dragons);
					game_stat.imprime_mapa(map);
					scan_movimento(num_dragons);
					break;
					
				case 's': 			// desce 1 linha (verificar linha acima)
					game_stat.status("Row", 1, map,num_dragons);
					mapa.move_hero_rows(1,num_dragons);
					game_stat.status("Row", -1,map,num_dragons);
					game_stat.imprime_mapa(map);
					scan_movimento(num_dragons);
					break;
					
				case 'd':
					game_stat.status("Column", 1, map,num_dragons);
					mapa.move_hero_columns(1,num_dragons);
					game_stat.status("Column", 1,map,num_dragons);
					game_stat.imprime_mapa(map);
					scan_movimento(num_dragons);
					break;
					
				case 'a':
					game_stat.status("Column", -1, map,num_dragons);
					mapa.move_hero_columns(-1,num_dragons);
					game_stat.status("Column", -1, map,num_dragons);
					game_stat.imprime_mapa(map);
					scan_movimento(num_dragons);
					break;
			 
				default: System.out.println("Invalid character command!");
						scan_movimento(num_dragons);	
						break;
				}		
		s.close();
}
	
	public static void place_dragons(String[][] map) {
		
			Random rand = new Random();
			int rand_int1 = rand.nextInt(9);
			int rand_int2 = rand.nextInt(9);
			int[] dragon_pos = {rand_int1,rand_int2};
			
			if(map[dragon_pos[0]][dragon_pos[1]] != "X" && 
					map[dragon_pos[0]][dragon_pos[1]] != "H"
					&& map[dragon_pos[0]][dragon_pos[1]] != "E"
					&& map[dragon_pos[0]][dragon_pos[1]] != "D") {
				
				map[dragon_pos[0]][dragon_pos[1]] = "D";
			}
			else place_dragons(map);
		
	}
	
	

	public static void main(String[] args) {
		
		System.out.println("How many Dragons do you want in the game? Please choose a reasonable number :");
		
		Scanner s = new Scanner(System.in);
		
		int num_dragons =  s.nextInt();
		
		for(int i=0; i< num_dragons-1; i++) place_dragons(map);
		
		game_stat.imprime_mapa(map);
		
		scan_movimento(num_dragons);
		  
		
	}
}

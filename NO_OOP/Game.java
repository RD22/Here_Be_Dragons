import java.util.Scanner;
import java.util.Random;

public class Game {
	
	public static void imprime_mapa(String[][] map, int key) {
		for(int i=0; i< map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
		if (key == 1) System.out.println("HERO has the key now!");
	}
	
	public static void wall_block(String[][] map, int key,int num_moves) {
		imprime_mapa(map, key);
		System.out.println("Path blocked by a Wall or Door! : Try again");
		scan_movimento(map,key,num_moves);
	}
	
	public static void game_over(String[][] map, int key) {
		imprime_mapa(map, key);
		System.out.println("GAME OVER! You got too close to the Dragon! Better luck next time :)");
		System.exit(0);
	}
	
	public static void game_victory(String[][] map, int key) {
		imprime_mapa(map, key);
		System.out.println("CONGRATS! You Won the Game and escaped the Dragon! :)");
		System.exit(0);
	}
	
	public static void scan_movimento(String[][] map, int key,int num_moves) {
		int []hero_pos = get_hero_pos(map);
		System.out.println("enter single-character commands to move the main character (\"hero\"):");
		System.out.println("you have 4 possible directions (up('u'), down('d'), left('l), right('r'))");
		Scanner s = new Scanner(System.in);
		char direction =  s.next().charAt(0);
			switch (direction) {
				case 'u' : 			// sobe 1 linha (verificar linha acima) 
					move_hero_rows(map,key,hero_pos,-1,num_moves);
					break;
					
				case 'd': 			// desce 1 linha (verificar linha acima)
					move_hero_rows(map,key,hero_pos,1,num_moves);
					break;
					
				case 'l':
					move_hero_columns(map,key,hero_pos,-1,num_moves);
					break;
					
				case 'r':
					move_hero_columns(map,key,hero_pos,1,num_moves);
					break;
			 
				default: System.out.println("Invalid character command!");
						 scan_movimento(map,key,num_moves);	
						 break;
	}
	
	s.close();
	
	}
	
	public static void move_hero_rows(String[][] map,int key, int []hero_pos, int offset,int num_moves) {
		if (map[hero_pos[0]+offset][hero_pos[1]] == "X" || (map[hero_pos[0]+offset][hero_pos[1]] == "E" && key == 0)  ) {
			wall_block(map,key,num_moves);
		}
		else if(map[hero_pos[0]+offset][hero_pos[1]] == "K") {
			key = 1;
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]+offset][hero_pos[1]] =  "H";
			num_moves ++;
			if(num_moves % 2 ==0) teleport_dragon(map);
			imprime_mapa(map ,key);
			scan_movimento(map, key,num_moves);
		}
		else if(map[hero_pos[0]+offset][hero_pos[1]] == "E" && key == 1){	// hero tem key e vai passar na porta
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]+offset][hero_pos[1]] =  "H";
			game_victory(map,key);
			
		}
		else if(map[hero_pos[0]+2*offset][hero_pos[1]] == "D" ) {		// dragon in adjacent position
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]+offset][hero_pos[1]] =  "H";
			game_over(map,key);
		}
		else if(map[hero_pos[0]+offset][hero_pos[1]] == " ") {
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]+offset][hero_pos[1]] =  "H";
			num_moves ++;
			if(num_moves % 2 ==0) teleport_dragon(map);
			imprime_mapa(map,key);
			scan_movimento(map, key,num_moves);
		}
	}
	
	
	public static void move_hero_columns(String[][] map,int key, int []hero_pos, int offset, int num_moves) {
		if (map[hero_pos[0]][hero_pos[1]+offset] == "X" || (map[hero_pos[0]][hero_pos[1]+offset] == "E" && key == 0)) {
			wall_block(map,key,num_moves);
		}
		else if(map[hero_pos[0]][hero_pos[1]+offset] == "K")  {
			key = 1;
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]][hero_pos[1]+offset] =  "H";
			num_moves ++;
			if(num_moves % 2 ==0) teleport_dragon(map);
			imprime_mapa(map,key);
			scan_movimento(map,key,num_moves);
		}
		else if(map[hero_pos[0]][hero_pos[1]+offset] == "E" && key == 1) {
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]][hero_pos[1]+offset] =  "H";
			game_victory(map,key);
		}
		else if(map[hero_pos[0]][hero_pos[1]+2*offset] == "D") {
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]][hero_pos[1]+offset] =  "H";
			game_over(map,key);
		}
		else if(map[hero_pos[0]][hero_pos[1]+offset] == " ") {
			map[hero_pos[0]][hero_pos[1]] = " ";
			map[hero_pos[0]][hero_pos[1]+offset] =  "H";
			num_moves ++;
			if(num_moves % 2 ==0) teleport_dragon(map);
			imprime_mapa(map,key);
			scan_movimento(map,key,num_moves);
		}
	}
	
	public static void teleport_dragon(String[][] map) { 
		int[] hero_pos = get_hero_pos(map);	 
		Random rand = new Random();
		int rand_int1 = rand.nextInt(9);	// número random linha
		//if (rand_int1 < 2) teleport_dragon(map);	// garante 2 posições distância	
		int rand_int2 = rand.nextInt(9);		// número random coluna
		//if (rand_int2 < 2) teleport_dragon(map);	// garante 2 posições distância
		
		int[] new_dragon_pos = {Math.abs(hero_pos[0]-rand_int1), Math.abs(hero_pos[1]-rand_int2)};
		
		if(map[new_dragon_pos[0]][new_dragon_pos[1]] == "X" ||
				map[new_dragon_pos[0]][new_dragon_pos[1]] == "H"
				|| map[new_dragon_pos[0]+1][new_dragon_pos[1]] == "H"
				|| map[new_dragon_pos[0]][new_dragon_pos[1]+1] == "H"){
			 teleport_dragon(map);
		}
		else {
			release_cur_dragon(map);	// apaga posição atual dragão
			map[Math.abs(hero_pos[0]-rand_int1)][Math.abs(hero_pos[1]-rand_int2)] = "D";
		}
	}
	
	public static void release_cur_dragon(String[][] map) {
		for(int i=0; i< map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
					if(map[i][j] == "D" ) map[i][j] = " ";{
				}
			}
		}
	}
	
	
	public static int[] get_hero_pos(String[][] map) {
		int hero_pos[] = {0,0};
		for(int i=0; i< 10; i++) {
			for(int j=0; j<10; j++) {
					if(map[i][j] == "H" ) {
					hero_pos = new int[] {i, j};
				}
		
			}
		}
		return hero_pos;
	}

	public static void main(String[] args) {
		
		String map[][]  = { {"X","X","X","X","X","X","X","X","X","X"},	//1
				{"X","H"," "," "," "," "," "," "," ","X"}, 			//2
				{"X"," ","X","X"," ","X"," ","X"," ","X"},		//3
				{"X","D","X","X"," ","X"," ","X"," ","X"},			//4
				{"X"," ","X","X"," ","X"," ","X"," ","X"},			//5
				{"X"," "," "," "," "," "," ","X"," ","E"},			//6
				{"X"," ","X","X"," ","X"," ","X"," ","X"},			//7
				{"X"," ","X","X"," ","X"," ","X"," ","X"},			//8
				{"X","K","X","X"," "," "," "," "," ","X"},			//9
				{"X","X","X","X","X","X","X","X","X","X"}			//10
		};
		
		int key = 0, num_moves = 0;

		imprime_mapa(map,key);	
		
		scan_movimento(map, key, num_moves);
		  
		
	}
}
	
	

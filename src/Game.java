import java.util.Scanner;

public class Game {
	
	public static void imprime_mapa(String[][] map) {
		for(int i=0; i< 10; i++) {
			for(int j=0; j<10; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	public static void wall_block(String[][] map) {
		imprime_mapa(map);
		scan_movimento(map);
	}
	
	public static void game_over() {
		System.out.println("GAME OVER! Better luck next time");
		System.exit(0);
	}
	
	
	public static void scan_movimento(String[][] map) {
		int []hero_pos = get_hero_pos(map);
		//System.out.println(map[hero_pos[0+1]][hero_pos[1]]);
		System.out.println("enter single-character commands to move the main character (\"hero\"):");
		System.out.println("you have 4 possible directions (up('u'), down('d'), left('l), right('r'))");
		Scanner s = new Scanner(System.in);
		char direction =  s.next().charAt(0);
			switch (direction) {
				case 'u' : 			// sobe 1 linha (verificar linha acima)
					if (map[hero_pos[0]-1][hero_pos[1]] == "X" || map[hero_pos[0]-1][hero_pos[1]] == "E" ) {
						wall_block(map);
					}
					else if(map[hero_pos[0]-1][hero_pos[1]] == "D" ) {
						game_over();
					}
					else {
						map[hero_pos[0]][hero_pos[1]] = " ";
						map[hero_pos[0]-1][hero_pos[1]] =  "H";
						imprime_mapa(map);
						scan_movimento(map);
					}
					
				case 'd': 			// desce 1 linha (verificar linha acima)
					if (map[hero_pos[0]+1][hero_pos[1]] == "X" || (map[hero_pos[0]+1][hero_pos[1]] == "E" && key(map) == 0)) {
						wall_block(map);
					}
					else if(map[hero_pos[0]+1][hero_pos[1]] == "D") {
						game_over();
					}
					else {
						map[hero_pos[0]][hero_pos[1]] = " ";
						map[hero_pos[0]+1][hero_pos[1]] =  "H";
						imprime_mapa(map);
						scan_movimento(map);
					}
				case 'l':
					if (map[hero_pos[0]][hero_pos[1]-1] == "X" || map[hero_pos[0]][hero_pos[1]-1] == "E") {
						wall_block(map);
					}
					else {
						map[hero_pos[0]][hero_pos[1]] = " ";
						map[hero_pos[0]][hero_pos[1]-1] =  "H";
						imprime_mapa(map);
						scan_movimento(map);
					}
				case 'r':
					if (map[hero_pos[0]][hero_pos[1]+1] == "X" || map[hero_pos[0]][hero_pos[1]+1] == "E") {
						wall_block(map);
					}
					else {
						map[hero_pos[0]][hero_pos[1]] = " ";
						map[hero_pos[0]][hero_pos[1]+1] =  "H";
						imprime_mapa(map);
						scan_movimento(map);
					}

			 
				default: System.out.println("Invalid character command!");
						 scan_movimento(map);	
				
		break;
	}
	
	s.close();
	
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
		
		
		imprime_mapa(map);	
		
		scan_movimento(map);
		  
		
	}
}
	
	

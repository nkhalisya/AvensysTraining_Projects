import java.util.Scanner;

public class TicTacToeGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Tic-Tac-Toe Game ~(^.^)~");
		System.out.println("Player 1: X\nPlayer 2 (computer): O\n");
		
		char[][] grid = {
				{'1', '|', '2', '|', '3'},
				{'-', '+', '-', '+', '-'},
				{'4', '|', '5', '|', '6'},
				{'-', '+', '-', '+', '-'},
				{'7', '|', '8', '|', '9'}
		};
		printGrid(grid);
		
		// loop
		// check if position is valid
		// check if position is occupied
		// enter into grid -- display grid
		// check if there is a win -- terminate, else loop
		
		int turn = 1;
		while(true) {
			
			// USER (odd) and COMPUTER (even)
			String player = "";
			int selectedPosition;
			if(turn % 2 == 0){
				// even - COMPUTER 
				player = "COMPUTER";
				int min = 1;
				int max = 9;
				selectedPosition = (int)(Math.random()*(max-min+1)+min);
				System.out.println("COMPUTER filled position " + selectedPosition + ":" );
			}
			else {
				// odd - USER
				player = "USER";
				System.out.print("Enter the position you want to fill: ");
				selectedPosition = sc.nextInt();
			}
			
			boolean valid = false; 	  // true: 1 <= position <= 9 | false: position < 1, position > 9 
			boolean free = false;     // true: position free	  | false: position filled
			boolean win = false;	  // true: win match 		  | false: no match
			
			// Check if position is valid
			valid = validatePosition(selectedPosition, grid);
			if(valid) {
				// check occupied
				free = positionFilled(selectedPosition, grid);
			}
			
			// Check if position is occupied
			if(free) {
				// enter into grid
				enterGrid(selectedPosition, grid, player);
				// check win
				win = checkWinner(grid);
				turn++;
			}
			
			// Error Messages
			if(!valid) {
				System.out.println("Position Filled !!! Try Again...\n");
				turn = turn - 2;
			}
			else if(!free) {
				System.out.println("Invalid Position !!! Try Again...\n");
				turn = turn - 2;
			}
			
			// Win Message + Terminate
			if(win) {
				System.out.println("CONGRATULATIONS! " + player + " won!");
				break;
			}
		}
	}
	
	public static boolean checkWinner(char[][] grid) {
		boolean win = false;
		/* possible wins: 
		 * Horizontal: 1,2,3 | 4,5,6 | 7,8,9
		 * Vertical  : 1,4,7 | 2,5,8 | 3,6,9
		 * Diagonal  : 1,5,9 | 3,5,7
		 * 
		 *  1 | 2 | 3	0,0 | 0,2 | 0,4
		 *  ---------	---------------
		 *  4 | 5 | 6	2,0 | 2,2 | 2,4
		 *  ---------	---------------
		 *  7 | 8 | 9	4,0 | 4,2 | 4,4
		 * */
		
		// DIAGONAL
		if( (grid[0][0] == grid[2][2] && grid[0][0] == grid[4][4]) ||
			(grid[4][0] == grid[2][2] && grid[4][0] == grid[0][4]) ) {
			win = true;
		}
		else {
			for( int i = 0; i < 5; i = i + 2 ) {
				
				// HORIZONTAL: row changes: 0+0, 0+2, 0+4
				if( grid[0+i][0] == grid[0+i][2] && grid[0+i][0] == grid[0+i][4] ) {
					win = true;
					break;
				}
				
				// VERTICAL: col changes: 0+0, 0+2, 0+4
				if( grid[0][0+i] == grid[2][0+i] && grid[0][0+i] == grid[4][0+i] ) {
					win = true;
					break;
				}
			}
		}
		return win;
	}
	
	public static boolean validatePosition( int position, char[][] grid ) {
		int[] gridPos = getGridPosition(position);
		if( gridPos[0] == 5 ) { //default in switch
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean positionFilled( int position, char[][] grid) {
		int[] gridPos = getGridPosition(position);
		if( grid[gridPos[0]][gridPos[1]] == 'X' || grid[gridPos[0]][gridPos[1]] == 'O' ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static void printGrid(char[][] grid) {
		for( char[] x: grid ) {
			for( char ch: x) {
				System.out.print(ch);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void enterGrid( int position, char[][] grid, String player) {
		char symbol;
		if( player.equals("USER") ) {
			symbol = 'X';
		}
		else {
			symbol = 'O';
		}
		
		int[] gridPosition = getGridPosition(position);
		grid[gridPosition[0]][gridPosition[1]] = symbol;
		
		printGrid(grid);
	}
	
	public static int[] getGridPosition( int position ) {
		int row, col;
		switch( position ) {
			case 1:
				row = 0;
				col = 0;
				break;
			case 2:
				row = 0;
				col = 2;
				break;
			case 3:
				row = 0;
				col = 4;
				break;
			case 4:
				row = 2;
				col = 0;
				break;
			case 5:
				row = 2;
				col = 2;
				break;
			case 6:
				row = 2;
				col = 4;
				break;
			case 7:
				row = 4;
				col = 0;
				break;
			case 8:
				row = 4;
				col = 2;
				break;
			case 9:
				row = 4;
				col = 4;
				break;
			default:
				row = 5;
				col = 5;
		}
		
		int[] pos = new int[2];
		pos[0] = row;
		pos[1] = col;
		
		return pos;
	}
}

import java.util.Scanner;

public class GameTry {
	
	static String[][] gameGrid = new String[3][3];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int playerNo = 1; // 2 players only
		int move = 0;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Tic-Tac-Toe Game ~(^.^)~");
		System.out.println("Player 1: X\nPlayer 2: O\n");
		printDefaultGrid();
		
		int turns = 0;
		while(true) {
			
			// get player's input
			System.out.print("\nPlayer " + playerNo + ", enter the position you want to fill: ");
			move = sc.nextInt();
			
			//check if position is valid
			int[] rc = getRowCol(move);
			if( rc[0] == 3 ) {
				System.out.println("Invalid Position !!!");
			}
			else {
				// check if position has been filled: only at player 2's turn and onwards
				boolean empty = false;
				if( turns > 0 ) { // check on player 2's turn
					
					rc = getRowCol(move);
					if( rc[0] != 3 ) {
						empty = gameGrid[rc[0]][rc[1]].equals("   ");
					}
				}
				
				if( empty || turns == 0) {
					// enter into grid
					enterGrid(playerNo, move);
					
					// check Win
					if( turns > 3 ) { // check when player 1 has marked 3 positions at 4th turn
						boolean win = checkWin();
						if(win) {
							System.out.println("\nCONGRATULATIONS! Player " + playerNo + " won!");
							break;
						}
					}
					// switch players
					if( playerNo == 1 ) {
						playerNo = 2;
					}
					else {
						playerNo = 1;
					}
				}
				else {
					System.out.println("Position has been filled !!!");
				}
				turns++;
			}
		}

	}
	
	public static boolean checkWin() {
		boolean win = false;
		boolean isNull = false;
		/* possible wins: 
		 * Horizontal: 1,2,3 | 4,5,6 | 7,8,9
		 * Vertical  : 1,4,7 | 2,5,8 | 3,6,9
		 * Diagonal  : 1,5,9 | 3,5,7
		 *  1 | 2 | 3	0,0 | 0,1 | 0,2
		 *  ---------	---------------
		 *  4 | 5 | 6	1,0 | 1,1 | 1,2
		 *  ---------	---------------
		 *  7 | 8 | 9	2,0 | 2,1 | 2,2
		 * */
		
		// DIAGONAL
		if( (gameGrid[0][0].equals(gameGrid[1][1]) && gameGrid[0][0].equals(gameGrid[2][2])) ||
			(gameGrid[2][0].equals(gameGrid[1][1]) && gameGrid[2][0].equals(gameGrid[0][2])) ) {
			isNull = gameGrid[0][0].equals("   ");
			win = true;
		}
		else {
			for( int i = 0; i < 3; i++ ) {
				
				// HORIZONTAL: row changes: 0+0, 0+1, 0+2
				if( gameGrid[0+i][0].equals(gameGrid[0+i][1]) && gameGrid[0+i][0].equals(gameGrid[0+i][2]) ) {
					isNull = gameGrid[0+i][0].equals("   ");
					win = true;
					break;
				}
				
				// VERTICAL: col changes: 0+0, 0+1, 0+2
				if( gameGrid[0][0+i].equals(gameGrid[1][0+i]) && gameGrid[0][0+i].equals(gameGrid[2][0+i]) ) {
					isNull = gameGrid[0][0+i].equals("   ");
					win = true;
					break;
				}
			}
		}
		if( !isNull && win ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void enterGrid(int playerNo, int position) {
		
		// check player: X or O
		String mark = getMark(playerNo);
		
		// get co-ordinates of position
		int[] rc = getRowCol(position);
		
		for( int row = 0; row < gameGrid[0].length; row++) {
			for( int col = 0; col < gameGrid.length; col++) {
				if( rc[0] == row && rc[1] == col ) {
					gameGrid[row][col] = " " + mark + " ";
				}
				else if( gameGrid[row][col] == null ) {
					gameGrid[row][col] = " " + " " + " ";
				}
			}
		}
		printGrid(gameGrid);
		
	}
	
	public static void printGrid( String grid[][] ) {
		for( int row = 0; row < grid[0].length; row++) {
			for( int col = 0; col < grid.length; col++) {
				
				if( col == 1 || col == 2 ) {
					System.out.print("|");
				}
				System.out.print(grid[row][col]);
			}
			if( row == 0 || row == 1 ) {
				System.out.println("\n-----------");
			}
		}
		System.out.println();
	}
	
	public static String getMark( int playerNo ) {
		String playerMark;
		if( playerNo == 1 ) {
			playerMark = "X";
		}
		else {
			playerMark = "O";
		}
		return playerMark;
	}
	
	public static void printDefaultGrid() {
		
		String[][] defaultGrid = new String[3][3];
		int position = 1;
		for( int row = 0; row < defaultGrid[0].length; row++) {
			for( int col = 0; col < defaultGrid.length; col++) {
				defaultGrid[row][col] = " " + position + " ";
				position++;
			}
		}
		printGrid(defaultGrid);
		
//		System.out.println(" 1 | 2 | 3 ");
//		System.out.println("-----------");
//		System.out.println(" 4 | 5 | 6 ");
//		System.out.println("-----------");
//		System.out.println(" 7 | 8 | 9 ");
	}
	
	public static int[] getRowCol( int position ) {
		
		int row, col;
		switch( position ) {
			case 1:
				row = 0;
				col = 0;
				break;
			case 2:
				row = 0;
				col = 1;
				break;
			case 3:
				row = 0;
				col = 2;
				break;
			case 4:
				row = 1;
				col = 0;
				break;
			case 5:
				row = 1;
				col = 1;
				break;
			case 6:
				row = 1;
				col = 2;
				break;
			case 7:
				row = 2;
				col = 0;
				break;
			case 8:
				row = 2;
				col = 1;
				break;
			case 9:
				row = 2;
				col = 2;
				break;
			default:
				row = 3;
				col = 3;
		}
		
		int[] rowCol = new int[2];
		rowCol[0] = row;
		rowCol[1] = col;
		
		return rowCol;
	}
}

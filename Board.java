public class Board{
	
	private char[][] board;
	private char player1;
	private char player2;
	private int numrow = 6;
	private int numcol = 7;
	
	public Board(){
		
		board = new char[6][7];
		
		for(int i = 0; i < 6; i++){
			
			for(int j = 0; j < 7; j++){
				
				board[i][j] = ' ';
				
			}
			
		}
		
	}
	
	public Board(int row, int col){
		
		if(row <= 0 || col <= 0){
			
			board  = new char[6][7];
			
		}else{
		
			board = new char[row][col];
		
			numrow = row;
			numcol = col;
			
		}
		
		for(int i = 0; i < row; i++){
			
			for(int j = 0; j < col; j++){
				
				board[i][j] = ' ';
				
			}
			
		}
		
	}
	
	public int getNumRows(){

		return numrow;
		
	}
	
	public int getNumCols(){
		
		return numcol;
		
	}
	
	public char getPlayerOne(){
		
		return player1;
		
	}
	
	public char getPlayerTwo(){
		
		return player2;
		
	}
	
	public void setPlayerOne(char o){
		
		player1 = o;
		
	}
	
	public void setPlayerTwo(char t){
		
		player2 = t;
		
	}
	
	public char getToken(int row, int col){
		
		if(row >= numrow || col >= numcol || row < 0 || col < 0){
			
			return '\0';
			
		}
		
		return board[row][col];
		
	}
	
	public boolean canPlay(){
		
		for(int i = 0; i < numrow; i++){
			
			for(int j = 0; j < numcol; j++){
				
				if(getToken(i,j) == ' '){
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean play(int p, int c){
		
		if((p != 1 && p != 2) || c < 0 || c >= numcol || board[0][c] != ' '){
			
			return false;
			
		}
		
		for(int i = numrow - 1; i >= 0; i--){
			
			if(board[i][c] == ' '){
				
				if(p == 1){
					
					board[i][c] = player1;
					return true;
					
				}
				
				if(p == 2){
					
					board[i][c] = player2;
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	public int isFinished(){
		
		for(int i = 0; i < numrow; i++){
			
			int count = 1;
		
			for(int j = 1; j < numcol; j++){
				
				if(board[i][j] != ' ' && board[i][j] == board[i][j-1]){
					
					count++;
					
				}else{
					
					count = 1;
					
				}
			
				if(count == 4){
					
					if(board[i][j] == player1){
						
						return 1;
						
					}else if(board[i][j] == player2){
						
						return 2;
						
					}
					
				}
			
			}
		
		}
		
		for(int a = 0; a < numcol; a++){
			
			int c = 1;
			
			for(int b = 1; b < numrow; b++){
				
				if(board[b][a] != ' ' && board[b][a] == board[b - 1][a]){
					
					c++;
					
				}else{
					
					c = 1;
					
				}
				
				if(c == 4){
					
					if(board[b][a] == player1){
						
						return 1;
						
					}else if(board[b][a] == player2){
						
						return 2;
						
					}
					
				}
				
			}
			
		}
		
		for(int d = 0; d < numrow - 3; d++){
			
			for(int e = 0; e < numcol - 3; e++){
				
				if(board[d][e] == board[d+1][e+1] && board[d][e] == board[d+2][e+2] && board[d][e] == board[d+3][e+3]){
				
					if(board[d][e] == player1){
						
						return 1;
						
					}else if(board[d][e] == player2){
						
						return 2;
						
					}
				
				}
	
			}
		}
		
		for(int g = 0; g < numrow - 3; g++){

			for(int h = numcol - 1; h >= 3; h--){

				if(board[g][h] == board[g+1][h-1] && board[g][h] == board[g+2][h-2] && board[g][h] == board[g+3][h-3]){
		
					if(board[g][h] == player1){
						
						return 1;
						
					}else if(board[g][h] == player2){
						
						return 2;
						
					}
					
				}
	
			}
		}
		
		for(int ab = 0; ab < numrow; ab++){
			
			for(int bc = 0; bc < numcol; bc++){
				
				if(board[ab][bc] == ' '){
					
					return -1;
					
				}
				
			}
			
		}
		
		return 0;
		
	}
	
}
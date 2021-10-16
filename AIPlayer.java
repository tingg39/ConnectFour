public class AIPlayer implements Player{
//do not change the line above	
	private int player;
	private int numrow;
	private int numcol;
	private int[][] board;
	private int[][] temBoard;
	private int lastColNum;
	private int lastRowNum;
	public AIPlayer(int playerID, int row, int col)
	{
		player = playerID;
		numrow = row;
		numcol = col;
		board = new int[row][col];
		temBoard = new int[row][col];
	}
	
	//used to notify your AI player of the other players last move
	public void lastMove(int c) 
	{
		lastColNum = c;
		for(int i = 1; i < numrow; i++)
		{
			if(board[i][c] != 0 )
			{
				System.out.println("Last move is at row " + (i-1) + " col " + c);
				lastRowNum = i-1;
				board[i-1][c] = player+1;
				break;
			}
			else if(i == numrow-1)
			{
				System.out.println("Last move is at row " + i + " col " + c);
				lastRowNum = i;
				board[i][c] = player+1;
				break;
			}
		}
	}
	
	//returns column of where to play a token
	public int playToken()
	{
		int result = isOppoWin();
		if(result != -1)
		{
			for(int i = numrow-1; i >= 0; i--)
			{
				if(board[i][result] == 0)
				{
					board[i][result] = player;
					break;
				}
			}
			System.out.println(result);
			return result;
		}
		result = amIWin();
		for(int i = numrow-1; i >= 0; i--)
		{
			if(board[i][result] == 0)
			{
				board[i][result] = player;
				break;
			}
		}
		System.out.println(result);
		return result;
	}
	
	//get this player's id
	public int getPlayerID(){
		return player;
	}
	
	private int amIWin()
	{	
		int count = 0;
		int maxCount = 0;
		int maxCol = -1;
		for(int c = 0; c < numcol; c++)
		{
			int rowCount = 0;
			if(board[0][c] != 0)
				continue;
			for(int r = 0; r < board.length; r++)
			{
				if(board[r][c] == 0)
					rowCount++;
				else if(board[r][c] == player+1)
					break;
				else
					rowCount = 5;
			}
			if(rowCount < 4)
				continue;
			for(int i = 0; i < board.length; i++)
				for(int j = 0; j < board[0].length; j++)
					temBoard[i][j] = board[i][j];
			predictPlay(c, player);
			count =  countMost();
			if(count >= maxCount)
			{
				maxCount = count;
				maxCol = c;
			}
		}
		return maxCol;
	}
	
	private int isOppoWin()
	{
		
		for(int c = 0; c < numcol; c++)
		{
			if(board[0][c] != 0)
				continue;
			for(int i = 0; i < board.length; i++)
				for(int j = 0; j < board[0].length; j++)
					temBoard[i][j] = board[i][j];
			predictPlay(c, player+1);
			if(isHeWin(player+1))
				return c;
		}
		return -1;
	}


	private int countMost()
	{
		int count = 1;
		int temCount = 1;
		for(int r = 0; r < numrow; r++)
		{
			for(int c = 0; c < numcol-1; c++)
			{
				if(temBoard[r][c] == player && temBoard[r][c] == temBoard[r][c+1])
					count++;
				else
				{
					if(count >= temCount)
						temCount = count;
					count = 1;
				}
			}
		}
		count = 1;
		for(int c = 0; c < numcol; c++)
		{
			for(int r = 0; r < numrow-1; r++)
			{
				if(temBoard[r][c] == player && temBoard[r][c] == temBoard[r+1][c])
					count++;
				else
				{
					if(count >= temCount)
						temCount = count;
					count = 1;
				}
			}
		}
		return temCount;
	}


	
	private void predictPlay(int c, int p)
	{
	
		for(int i = numrow - 1; i >= 0; i--)
		{	
			if(temBoard[i][c] == 0)
			{
				temBoard[i][c] = p;
				return;
			}	
		}	
	}
	
	private boolean isHeWin(int p)
	{
		for(int i = 0; i < numrow; i++){
				
				int count = 1;
			
				for(int j = 1; j < numcol; j++){
					
					if(temBoard[i][j] != ' ' && temBoard[i][j] == temBoard[i][j-1]){
						
						count++;
						
					}else{
						
						count = 1;
						
					}
				
					if(count == 4){
						
						if(temBoard[i][j] == p){
							
							return true;
							
						}
						
					}
				
				}
			
			}
			
			for(int a = 0; a < numcol; a++){
				
				int c = 1;
				
				for(int b = 1; b < numrow; b++){
					
					if(temBoard[b][a] != ' ' && temBoard[b][a] == temBoard[b - 1][a]){
						
						c++;
						
					}else{
						
						c = 1;
						
					}
					
					if(c == 4){
						
						if(temBoard[b][a] == p){
							
							return true;
							
						}
						
					}
					
				}
				
			}
			
			for(int d = 0; d < numrow - 3; d++){
				
				for(int e = 0; e < numcol - 3; e++){
					
					if(temBoard[d][e] == temBoard[d+1][e+1] && temBoard[d][e] == temBoard[d+2][e+2] && temBoard[d][e] == temBoard[d+3][e+3]){
					
						if(temBoard[d][e] == p){
							
							return true;
							
						}




					}
		
				}
			}
			
			for(int g = 0; g < numrow - 3; g++){




				for(int h = numcol - 1; h >= 3; h--){




					if(temBoard[g][h] == temBoard[g+1][h-1] && temBoard[g][h] == temBoard[g+2][h-2] && temBoard[g][h] == temBoard[g+3][h-3]){
			
						if(temBoard[g][h] == p){
							
							return true;
							
						}
						
					}
		
				}
			}
			
			for(int ab = 0; ab < numrow; ab++){
				
				for(int bc = 0; bc < numcol; bc++){
					
					if(temBoard[ab][bc] == ' '){
						
						return false;
						
					}
					
				}
				
			}
			
			return false;


	}
	
	public void reset(){
		
		player = 0;
		numrow = 0;
		numcol = 0;
		board = null;
		temBoard = null;
		lastColNum = 0;
		lastRowNum = 0;
		
	}
}
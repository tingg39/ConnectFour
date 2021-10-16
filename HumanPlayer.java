public class HumanPlayer implements Player{	

	private int player;
	private int rownum;
	private int colnum;
	private int[][] b;
	private int lastColNum;
	private int lastRowNum;
	
	public HumanPlayer(int playerID, int row, int col){
		
		player = playerID;
		rownum = row;
		colnum = col;
		b = new int[row][col];
		
	}
	
	public void lastMove(int c) {
		
		lastColNum = c;
		
		for(int i = 1; i < rownum; i++){
			
			if(b[i][c] != 0){
				
				System.out.println("Last move is at row " + (i-1) + " col " + c);
				
				lastRowNum = i - 1;
				
				b[i-1][c] = player + 1;
				
				break;
				
			}
			
			else if(i == rownum-1){
				
				System.out.println("Last move is at row " + i + " col " + c);
				
				lastRowNum = i;
				
				b[i][c] = player + 1;
				
				break;
				
			}
			
		}
		
	}

	public int playToken(){
		
		int c = GIO.readInt("Enter your column");
		
		while(c < 0 || c >= colnum || b[0][c] != 0){
			
			GIO.displayMessage("Invalid input");
			c = GIO.readInt("Enter your column");
			
		}
		
		for(int i = rownum-1; i >= 0; i--){
			
			if(b[i][c] == 0){
				
				b[i][c] = player;
				break;
			}
			
		}
		
		return c;

	}

	public int getPlayerID(){
		
		return player;
		
	}

	public void reset(){
		
		player = 0;
		rownum = 0;
		colnum = 0;
		b = null;
		lastRowNum = 0;
		lastColNum = 0;
		
	}

	
}
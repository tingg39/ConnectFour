public class ConnectFour{
	
	
	public static void main(String[] args){
		
		Board b = new Board();
		CFGUI a = new CFGUI(b,ChipColor.RED,ChipColor.BLUE);
		
		b.setPlayerOne('X');
		b.setPlayerTwo('O');
		
		Player p1 = new HumanPlayer(1,6,7);
		Player p2 = new AIPlayer(2,6,7);
		
		while(b.canPlay() == true){
			int c = p1.playToken();
			b.play(p1.getPlayerID(), c);
			p2.lastMove(c);
			a.redraw();
			if(b.isFinished() != -1){
				
				break;
				
			}
			
			c = p2.playToken();
			b.play(p2.getPlayerID(), c);
			p1.lastMove(c);
			a.redraw();
			if(b.isFinished() != -1){
				
				break;
				
			}
			
		}
		
		a.gameOver(b.isFinished());
		
	}
	
}
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Board implements Serializable{
	private HashMap<Position, Piece> board;
	
	public Board(){
		board = new HashMap<Position, Piece>();
	}
	
	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece schroedingersPiece = board.get(new Position(j, i));
				if (schroedingersPiece != null) {
					System.out.print(schroedingersPiece.getPlayer().getName().substring(0, 1));
				} else {
					System.out.print((i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0)?" ": "X");
				}

			}
			System.out.print("\n");
		}
	}
	
	public HashMap<Position, Piece> getBoard(){
		return this.board;
	}
	
	/**
	 * Counts the number of pieces belonging to each player still on the board.
	 * @param pl1
	 * @param pl2
	 * @return An array with 2 values representing the piece count for each player (by parameter order)
	 */
	public int[] pieceCount(Player pl1, Player pl2){
		int pl1Cnt = 0, pl2Cnt = 0;
		ArrayList<Piece> pieces = new ArrayList<Piece>(this.board.values());
		for(Piece p: pieces){
			if(p.getPlayer().equals(pl1)){
				pl1Cnt++;
			}
			else{
				pl2Cnt++;
			}
		}
		
		return new int[]{pl1Cnt, pl2Cnt};
		
		
	}
	
}

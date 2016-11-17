package logic;

import java.io.Serializable;
import java.util.HashMap;

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
	
}

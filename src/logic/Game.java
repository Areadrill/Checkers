package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import cli.Cli;

public class Game implements Serializable{
	
	public int currentPlayer;
	public Player[] players;
	public Board board;

	public Game() throws ImpossibleException {
		currentPlayer = 0;
		players = new Player[2];
		board = new Board();
		mainMenu();
	}
	
	public Game(boolean test){
		currentPlayer = 0;
		players = new Player[2];
		board = new Board();
	}

	public static void main(String[] args) throws ImpossibleException {
		Game game = new Game();
	}

	public void mainMenu() throws ImpossibleException {
		String[] mainMenuOptions = { "New Game", "Exit" };

		switch (Cli.getOption(mainMenuOptions)) {
		case 1:
			startNewGame();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			throw new ImpossibleException();

		}
	}

	public void startNewGame() {
		Player pl1 = new Player(Cli.getLine("Type in the name for player 1"));
		Player pl2 = new Player(Cli.getLine("Type in the name for player 2"));

		players[0] = pl1;
		players[1] = pl2;
		initializeBoard(this.board, pl1,pl2);
		while(true){
			processTurn();
		}
		
	}

	private void processTurn() {
		String[] turnOptions = {"Make move", "Save game", "Quit"};
		System.out.println(this.players[this.currentPlayer].getName() + "'s turn");
		
		int option = Cli.getOption(turnOptions);
		
		switch(option){
			case 1:
				processMove();
				this.currentPlayer = ((this.currentPlayer+1) % 2);
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				break;
		}
		
		
	}
	
	public void processMove(){
		this.board.printBoard();
		System.out.print("\n\n");
		
		System.out.println("Select the piece you want to move:");
		Piece pieceToMove = this.board.getBoard().get(Cli.getPosition());
		while(pieceToMove == null){
			System.out.println("No piece there! Try again.");
			pieceToMove = this.board.getBoard().get(Cli.getPosition());
		}
		while(!pieceToMove.getPlayer().equals(this.players[this.currentPlayer])){
			System.out.println("Not your piece! Try again.");
			pieceToMove = this.board.getBoard().get(Cli.getPosition());
		}
		
		ArrayList<Position> validPositions;
		if(this.currentPlayer == 0){
			validPositions = getAvailablePositions(pieceToMove, Direction.SE, false);
			validPositions.addAll(getAvailablePositions(pieceToMove, Direction.SW, false));
		}
		else{
			validPositions = getAvailablePositions(pieceToMove, Direction.NE, false);
			validPositions.addAll(getAvailablePositions(pieceToMove, Direction.NW, false));
		}
		
		
		System.out.println("Select where you want to move it to:");
		Position newPos = Cli.getPosition();
		
		while(!validPositions.contains(newPos)){
			System.out.println("Invalid play! Try again.");
			newPos = Cli.getPosition();
		}
		
		this.board.getBoard().remove(pieceToMove).getPosition();
		pieceToMove.getPosition().set(newPos);
		this.board.getBoard().put(pieceToMove.getPosition(), pieceToMove);
		
	}
	
	public enum Direction{
		NE, NW, SE, SW
	}
	
	public ArrayList<Position> getAvailablePositions(Piece piece, Direction dir, boolean onlyJumps){
		ArrayList<Position> validPositions = new ArrayList<Position>();
		int xIncrement = 0, yIncrement = 0;
		Position currPos = new Position(piece.getPosition());
			
		switch(dir){
			case NE:
				xIncrement = 1;
				yIncrement = -1;
				break;
			case NW:
				xIncrement = -1;
				yIncrement = -1;
				break;
			case SE:
				xIncrement = 1;
				yIncrement = 1;
				break;
			case SW:
				xIncrement = -1;
				yIncrement = 1;
				break;
			default:
				break;
		}
		
		boolean finished = false;
		int distance = 0;
		while(!finished){
			currPos.set(currPos.getX()+xIncrement, currPos.getY()+yIncrement);
			
			if(currPos.getX() < 0 || currPos.getX() > 7 || currPos.getY() < 0 || currPos.getY() > 7){
				break;
			}
			
			if(currPos.getX() == 0 || currPos.getX() == 7 || currPos.getY() == 0 || currPos.getY() == 7){
				finished = true;
			}
			
			Piece p = this.board.getBoard().get(currPos);
			
			if(p == null){
				if(onlyJumps){
					break;
				}
				validPositions.add(currPos);
				if(++distance > 0 && !piece.isKing()){
					finished = true;
				}
			}
			else if(p.getPlayer().equals(this.players[this.currentPlayer])){
				finished = true;
			}
			else{
				if(!finished){
					Position beyondPos = new Position(currPos.getX()+xIncrement, currPos.getY()+yIncrement);
					
					Piece p2 = this.board.getBoard().get(beyondPos);
					if(p2 == null){
						validPositions.add(beyondPos);
						if(yIncrement > 0){
							validPositions.addAll(getAvailablePositions(new Piece(this.players[this.currentPlayer], beyondPos), Direction.SE, true));
							validPositions.addAll(getAvailablePositions(new Piece(this.players[this.currentPlayer], beyondPos), Direction.SW, true));
						}
						else{
							validPositions.addAll(getAvailablePositions(new Piece(this.players[this.currentPlayer], beyondPos), Direction.NE, true));
							validPositions.addAll(getAvailablePositions(new Piece(this.players[this.currentPlayer], beyondPos), Direction.NW, true));
						}
					}
				}
				finished = true;
			}
			
		}
		
		
		return validPositions;
	}
	
	/*public boolean erasePieces(Position pos1, Position pos2){
		int deltaX = pos1.getX()-pos2.getX() , deltaY = pos1.getY()-pos2.getY();
		
		if(deltaX > 0 && deltaY < 0){
			for(int i = pos1.getY(); i > pos2.getY(); i--){
				for(int j = pos1.getX(); j < pos2.getX(); j++){
					Piece p = this.board.getBoard().get(new Position(j, i));
					if(p != null && !p.getPlayer().equals(this.players[this.currentPlayer])){
						this.board.getBoard().remove(new Position(j, i));
						return true;
					}
				}
			}
		}
		else if(deltaX > 0 && deltaY > 0){
			for(int i = pos1.getY(); i < pos2.getY(); i++){
				for(int j = pos1.getX(); j < pos2.getX(); j++){
					Piece p = this.board.getBoard().get(new Position(j, i));
					if(p != null && !p.getPlayer().equals(this.players[this.currentPlayer])){
						this.board.getBoard().remove(new Position(j, i));
						return true;
					}
				}
			}
		}
		else if(deltaX < 0 && deltaY < 0){
			for(int i = pos1.getY(); i > pos2.getY(); i--){
				for(int j = pos1.getX(); j > pos2.getX(); j--){
					Piece p = this.board.getBoard().get(new Position(j, i));
					if(p != null && !p.getPlayer().equals(this.players[this.currentPlayer])){
						this.board.getBoard().remove(new Position(j, i));
						return true;
					}
				}
			}
		}
		else if(deltaX < 0 && deltaY > 0){
			for(int i = pos1.getY(); i < pos2.getY(); i++){
				for(int j = pos1.getX(); j > pos2.getX(); j--){
					Piece p = this.board.getBoard().get(new Position(j, i));
					if(p != null && !p.getPlayer().equals(this.players[this.currentPlayer])){
						this.board.getBoard().remove(new Position(j, i));
						return true;
					}
				}
			}
		}
		
		return false;
	}*/

	public void initializeBoard(Board brd, Player pl1, Player pl2) {
		brd.getBoard().clear(); //to guarantee a fresh start
		for (int i = 0; i < 8; i++) { //Y
			for (int j = 0; j < 8; j++) { //X
				if(i < 3 || i >= 5){
					if((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)){
						brd.getBoard().put(new Position(j, i), new Piece(((i < 3) ? pl1 : pl2), new Position(j, i)));
					}
				}
			}
		}
	}
}

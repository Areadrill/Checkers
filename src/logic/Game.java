package logic;

import java.io.Serializable;
import java.util.HashMap;

import cli.Cli;

public class Game implements Serializable{
	
	int currentPlayer;
	Player[] players;
	Board board;

	public Game() throws ImpossibleException {
		currentPlayer = 0;
		players = new Player[2];
		board = new Board();
		mainMenu();
	}

	public static void main(String[] args) throws ImpossibleException {
		Game game = new Game();
	}

	public void mainMenu() throws ImpossibleException {
		String[] mainMenuOptions = { "New Game", "Load Game", "Tutorial", "Exit" };

		switch (Cli.getOption(mainMenuOptions)) {
		case 1:
			startNewGame();
			break;
		case 2:
			loadSavedGame();
			break;
		case 3:
			printTutorial();
			break;
		case 4:
			System.exit(0);
			break;
		default:
			throw new ImpossibleException();

		}
	}

	public void move() throws ImpossibleException {
		System.out.println("Coordinates of the piece you want to move");
		Position currentPos = Cli.getPosition();
		System.out.println("Coordinates you want to move the Piece to");
		Position futurePos = Cli.getPosition();

		changePlayer();
	}

	private void changePlayer() throws ImpossibleException {
		if (currentPlayer == 1)
			currentPlayer--;
		else if (currentPlayer == 1)
			currentPlayer++;
		throw new ImpossibleException();
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
				this.currentPlayer = this.currentPlayer+1 % 2;
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
		
		System.out.println("Select where you want to move it to:");
		Position newPos = Cli.getPosition();
		String validationResult = validateMovement(newPos, pieceToMove); 
		
		while(validationResult.equals("invalid")){
			System.out.println("Invalid play! Try again");
			newPos = Cli.getPosition();
			validationResult = validateMovement(newPos, pieceToMove);
		}
		
		
		if(validationResult.equals("move")){
			this.board.getBoard().remove(pieceToMove.getPosition());
			pieceToMove.getPosition().set(newPos);
			this.board.getBoard().put(newPos, pieceToMove);
		}
		else if(validationResult.equals("capture")){
			this.board.getBoard().remove(pieceToMove);
			Position previousPos = pieceToMove.getPosition();
			erasePieces(previousPos, newPos);
			pieceToMove.getPosition().set(newPos);
			this.board.getBoard().put(newPos, pieceToMove);
		}
	}

	
	public String validateMovement(Position pos, Piece piece){
		
		//overlapping pieces
		if(this.board.getBoard().get(pos) != null){
			return "invalid";
		}
		
		if(piece.getPosition().equals(pos)){
			return "invalid";
		}
		
		Position pos2 = piece.getPosition();
		int deltaX = pos.getX()-pos2.getX() , deltaY = pos.getY()-pos2.getY();
		
		//non-diagonal movement
		if(Math.abs(deltaX) != Math.abs(deltaY)){
			return "invalid";
		}
		
		
		if(deltaX > 2){
			if(piece.isKing()){
				return "capture";
			}
			else return "invalid";
		}
		
		if(deltaX == 2 && !piece.isKing()){
			Piece opponentPiece = this.board.getBoard().get(new Position(pos2.getX()+deltaX, pos2.getY()+deltaY));
			if(opponentPiece == null || opponentPiece.getPlayer().equals(this.players[this.currentPlayer])){
				return "invalid";
			}
			else{
				return "capture";
			}
		}
		
		return "move";
		
	}
	
	public void erasePieces(Position pos1, Position pos2){
		int deltaX = pos1.getX()-pos2.getX() , deltaY = pos1.getY()-pos2.getY();
		
	}
	
	public void loadSavedGame() {
		System.out.println("Under construction");
	}

	public void printTutorial() throws ImpossibleException {
		System.out.println("Under construction");
		mainMenu();
	}

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

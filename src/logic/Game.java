package logic;

import java.util.HashMap;

import cli.Cli;

public class Game {
	Cli cli;
	int currentPlayer;
	Player[] players;
	String[] movementOptions = { "Left", "Right" };

	public Game() throws ImpossibleException {
		cli = new Cli();
		currentPlayer = 0;
		players = new Player[2];
		mainMenu();
	}

	public static void main(String[] args) throws ImpossibleException {
		Game game = new Game();
	}

	public void mainMenu() throws ImpossibleException {
		String[] mainMenuOptions = { "New Game", "Load Game", "Tutorial", "Exit" };

		switch (cli.getOption(mainMenuOptions)) {
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
		Position currentPos = cli.getPiecePosition();
		System.out.println("Coordinates you want to move the Piece to");
		Position futurePos = cli.getPiecePosition();

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
		Player pl1 = new Player(cli.getLine("Type in the name for player 1"));
		Player pl2 = new Player(cli.getLine("Type in the name for player 2"));

		players[0] = pl1;
		players[1] = pl2;
		HashMap<Position, Piece> board = initializeBoard(pl1, pl2);

		printBoard(board);
	}

	public void loadSavedGame() {
		System.out.println("Under construction");
	}

	public void printTutorial() throws ImpossibleException {
		System.out.println("Under construction");
		mainMenu();
	}

	public HashMap<Position, Piece> initializeBoard(Player pl1, Player pl2) {
		HashMap<Position, Piece> board = new HashMap<Position, Piece>();

		// Y
		for (int i = 0; i < 8; i++) {
			// X
			for (int j = 0; j < 8; j++) {
				// Y ser par e X ser impar e limitar entre linha <3 e >=5
				if (i % 2 == 0 && j % 2 != 0 && (i < 3 || i >= 5)) {
					board.put(new Position(j, i), new Piece(((i < 3) ? pl1 : pl2), new Position(j, i)));
				}
			}
		}

		return board;
	}

	public void printBoard(HashMap<Position, Piece> board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece schroedingersPiece = board.get(new Position(j, i));
				if (schroedingersPiece != null) {
					System.out.print(schroedingersPiece.getPlayer().getName().substring(0, 1));
				} else {
					System.out.print("X");
				}

			}
			System.out.print("\n");
		}
	}
}

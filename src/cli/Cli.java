package cli;

import java.util.InputMismatchException;
import java.util.Scanner;

import logic.Position;

public class Cli {
	Scanner scanner;

	public Cli() {
		this.scanner = new Scanner(System.in);
	}

	public int getOption(String[] optionsText) {
		for (int i = 1; i <= optionsText.length; i++) {
			System.out.println(i + ". " + optionsText[i - 1]);
		}
		System.out.println("What would you like to do? \n");
		int option = 0;

		option = readInt();
		while (option < 1 || option > optionsText.length) {
			System.out.println("Inalid option!\nWhat would you like to do? \n");
			option = readInt();
		}
		// nextInt will not read the newline, therefore we need to take it out
		// before moving further
		scanner.nextLine();
		return option;
	}

	public int readInt() {

		try {
			return scanner.nextInt();
		} catch (InputMismatchException e) {
			scanner.nextLine();
			return -1;
		}

	}

	// broken
	public Position getPiecePosition() {
		int x, y;
		do {
			System.out.println("Enter coordinate X of the piece");
		} while ((x = readInt()) < 0);
		do {
			System.out.println("Enter coordinate Y of the piece");
		} while ((y = readInt()) < 0);

		return new Position(x, y);
	}

	public String getLine(String prompt) {
		System.out.println(prompt);
		return scanner.nextLine();
	}
}

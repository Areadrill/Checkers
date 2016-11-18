package cli;

import java.util.InputMismatchException;
import java.util.Scanner;

import logic.Position;

public class Cli {
	private static Scanner scanner = new Scanner(System.in);


	public static int getOption(String[] optionsText) {
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

	public static int readInt() {

		try {
			return scanner.nextInt();
		} catch (InputMismatchException e) {
			scanner.nextLine();
			return -1;
		}

	}

	//Suggestion for improvement: use regular expression
	public static Position getPosition() {
		int x = 0, y = 0;
		System.out.println("Enter the coordinates (x, y) (coordinates range from 1 to 8)");
			
		String coordStr = scanner.nextLine();
		String[] coordsStr = coordStr.split(", ");
		try{	
			x = Integer.parseInt(coordsStr[0]);
			y = Integer.parseInt(coordsStr[1]);
		}
		catch(NumberFormatException e){
			x = -1; // to force triggering the while below 
		}
		
		while(x < 1 || x > 8 || y < 1 || y > 8){
			System.out.println("Invalid coordinates! Try again");
			coordStr = scanner.nextLine();
			coordsStr = coordStr.split(", ");
			try{	
				x = Integer.parseInt(coordsStr[0]);
				y = Integer.parseInt(coordsStr[1]);
			}
			catch(NumberFormatException e){
				x = -1; 
			}
		}
			
			
		
		return new Position(x-1, y-1);
	}

	public static String getLine(String prompt) {
		System.out.println(prompt);
		return scanner.nextLine();
	}
}

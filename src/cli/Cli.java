package cli;

import java.util.Scanner;

public class Cli {
	Scanner scanner;
	public Cli(){
		this.scanner = new Scanner(System.in);
	}
	
	public int getOption(String[] optionsText){
		for(int i = 1; i <= optionsText.length; i++){
			System.out.println(i + ". " + optionsText[i-1]);
		}
		System.out.println("What would you like to do? \n");
		int option = 0;
		option = scanner.nextInt();
		while(option < 1 || option > optionsText.length){
			System.out.println("Inalid option!\nWhat would you like to do? \n");
			option = scanner.nextInt();
		}
		return option;
	}
	
	public String getLine(String prompt){
		System.out.println(prompt);
		return scanner.nextLine();
	}
}

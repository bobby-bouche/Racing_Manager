package inputValidation;

import java.util.Scanner;

import enums.ExperienceLevel;

public class Keyboard {
	
	// keyboard fields
	private Scanner input;
	
	// constructor
	public Keyboard() {
		super();
		input = new Scanner(System.in);
	}
	
	
	/*
	 * method to read in a string value and attempt to cast to integer. If successful,
	 * integer will be returned. If not successful, error msg will be returned.
	 */
	public int readInteger(String promptMsg, String errorMsg) {
		
		int num = 0;
		String strInput;
		boolean valid = false;
		
		while(valid == false) {
			System.out.println(promptMsg);
			strInput = input.nextLine();
			try {
				num = Integer.parseInt(strInput);
				valid = true;
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	// method to validate integer input from user
	public int readInteger(String promptMsg, String errorMsg, int low, int high) {
		
		int num = 0;
		boolean valid = false;
		String strInput;
		
		while(valid == false) {
			
			System.out.println(promptMsg);
			strInput = input.nextLine();
			
			try {
				num = Integer.parseInt(strInput);
				if(num >= low && num <= high) {
					valid = true;
				}
				else {
					System.out.println(errorMsg);
				}
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	/*
	 * method to vallidate user input for string
	 */
	public String readString(String promptMsg,String errorMsg) {
		
		String strInput = null;
		boolean valid = false;
		
		while(valid == false) {
			System.out.println(promptMsg);
			strInput = input.nextLine();
			try {
				if(!(strInput == null) && !strInput.isBlank()){
					valid = true;
				}
				else {
					System.out.println(errorMsg);
				}
			}
			catch(IllegalArgumentException e) {
				System.out.println(errorMsg);
			}
		}
		return strInput;
	}
	
	
	/*
	 * overloaded method to vallidate user input for string
	 */
	public String readString(String strInput) {
		
		boolean valid = false;
		
		while(valid == false) {
			try {
				if(!(strInput == null) && !strInput.isBlank()){
					valid = true;
				}
				else {
					System.out.println("Invalid String");
				}
			}
			catch(IllegalArgumentException e) {
				System.out.println("Illegal String input");
			}
		}
		return strInput;
	}
	
	
	public String validateDriverExperienceLevel(String promptMsg,String errorMsg) {
		
		String inputLevel = null;
		boolean valid = false;
		
		while(valid == false) {
			System.out.println(promptMsg);
			inputLevel = input.nextLine();
			try {
				readString(inputLevel);
				if(!(inputLevel.equals(ExperienceLevel.ROOKIE)) && !(inputLevel.equals(ExperienceLevel.SEASONED_PRO)) && !(inputLevel.equals(ExperienceLevel.ESTABLISHED))) {
					System.out.println("Invalid experiencelevel. please choice one of rookie/established/seasoned pro.");
				}
				else {
					valid = true;
				}
			}
			catch(IllegalArgumentException e) {
				System.out.println(errorMsg);
			}
		}
		return inputLevel;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

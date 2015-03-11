package scrumBoardManagement;

import java.util.Scanner;

import scrumBoardComponents.IDType;

public class InputScanner {

	private static Scanner inputScanner = new Scanner(System.in);
	
	//ask for input, and it has to be not null 
	public static String nextLine() {
		String input = inputScanner.nextLine();
		while (input.isEmpty()) {
			System.out.println("\tNo input is detected, and please retry.");
			input = inputScanner.nextLine();
		}
		return input;
	}

	//ask for a single character input, and it has to be not null and one char only
	public static char nextChar() {
		String input = inputScanner.nextLine();
		while (input.isEmpty() || input.length() > 1) {
			System.out
					.println("\tInvalid input has been detected, and please retry.");
			input = inputScanner.nextLine();
		}
		return input.charAt(0);
	}

	// test if id input is valid, warningMsg will show if invalid
	public static boolean idValid(IDType idType, String id) {
		boolean result = false;
		char idInitial = id.charAt(0);
		String numericString = id.substring(1);

		if (idInitialValid(idType, idInitial) && idNumValid(numericString)) {
			result = true;
		} else {
			String warningMsg = "    Input ID is not valid. "
					+ "\n      Story ID needs to start with 'S' or 's' then is followed by an integer."
					+ "\n      Task ID needs to start with 'T' or 't' then is followed by an integer.";
			System.out.println(warningMsg);
		}
		return result;
	}

	// test if the first char of id input is either 'S' or 'T'
	private static boolean idInitialValid(IDType idType, char idInitial) {
		if (idType == IDType.Story) {
			return idInitial == 'S';
		} else if (idType == IDType.Task) {
			return idInitial == 'T';
		}
		return false;
	}

	// test if the remainder of id input is an positive integer.
	private static boolean idNumValid(String numericString) {
		try {
			int idNum = Integer.parseInt(numericString);
			return idNum >= 0;
		} catch (IllegalArgumentException e) {
			System.out.println("    The input number is not an integer");
		}
		return false;
	}
}

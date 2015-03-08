package scrumBoardManagement;
import java.util.Scanner;
import scrumBoardComponents.IDType;

public class InputScanner {

	private static Scanner inputScanner = new Scanner(System.in);

	public static String nextLine() {
		String input = inputScanner.nextLine();
		while (input.isEmpty()){
			System.out.println("\tNo input is detected, and please retry.");
			input = inputScanner.nextLine();
		}
		return input;
	}

	public static char nextChar() {
		String input = inputScanner.nextLine();
		while (input.isEmpty() || input.length() > 1){
			System.out.println("\tInvalid input has been detected, and please retry.");
			input = inputScanner.nextLine();
		}
		return input.charAt(0);
	}
	
	//test if id input is valid
	public static boolean idValid(IDType idType, String id){
		char idInitial = id.charAt(0);
		String numericString = id.substring(1); 
		return idInitialValid(idType, idInitial) && idNumValid(numericString);
	}
	
	//test if the first char of id input is either 'S' or 'T'
	private static boolean idInitialValid(IDType idType, char idInitial){
		if (idType == IDType.Story) {
			return idInitial == 'S';
		} else if (idType == IDType.Task) {
			return idInitial == 'T';
		}
		return false;
	}
	
	//test if the remainder of id input is an positive integer.
	private static boolean idNumValid(String numericString){
		try {
			int idNum = Integer.parseInt(numericString);
			return idNum >= 0;
		} catch (IllegalArgumentException e) {
			System.out.println("  Invalid ID input");
		}
		return false;
	}
}

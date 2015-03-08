package scrumBoardManagement;
import java.util.Scanner;

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
}

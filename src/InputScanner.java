import java.util.Scanner;

public class InputScanner {

	private static Scanner inputScanner = new Scanner(System.in);

	public static String nextLine() {
		return inputScanner.nextLine();
	}

	public static char nextChar() {
		return inputScanner.nextLine().charAt(0);
	}

	public static int nextInt() {
		int i = inputScanner.nextInt();
		inputScanner.nextLine();
		return i;
	}

	public static double nextDouble() {
		double d = inputScanner.nextDouble();
		inputScanner.nextLine();
		return d;
	}
}

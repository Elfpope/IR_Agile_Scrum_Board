package scrumBoardManagement;


public class IDGenerator {
	private static int storyCounter = 0;
	private static int taskCounter = 0;
	
	//return a unique id depending on input type (story | task)
	public static String generateID(IDType idType) {
		String ID = "";
		if (idType == IDType.Story) {
			ID += "S" + storyCounter;
			storyCounter++;
		} else if (idType == IDType.Task) {
			ID += "T" + taskCounter;
			taskCounter++;
		}
		return ID;
	}
	public static boolean idValid(IDType idType, String id){
		boolean result = false;
		char firstChar = id.charAt(0);
		String remainder = id.substring(1); 
		if (idType == IDType.Story) {
			if (Character.valueOf(firstChar).compareTo('S') == 0);
		} else if (idType == IDType.Task) {

		} 
		return result;
	}
}

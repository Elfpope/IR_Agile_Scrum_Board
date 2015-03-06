public class IDGenerator {
	private static int storyCounter = 0;
	private static int taskCounter = 0;

//	public IDGenerator() {
//		storyCounter = 0;
//		taskCounter = 0;
//	}
	
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
}

package scrumBoardComponents;

public enum TaskStatus {
	To_Do, 
	In_Process, 
	To_Verify, 
	Done;
	
	public static void printTaskStatus(){
		String taskStatus = "";
		taskStatus += "\nTask status -- ";
		taskStatus += "\n    1: To Do";
		taskStatus += "\n    2: In Process";
		taskStatus += "\n    3: To Verify";
		taskStatus += "\n    4: Done";
		System.out.println(taskStatus);
	}
}

package scrumBoardComponents;


public class Task {
	private String taskID;
	private String storyID;
	private String taskDescription;
	private TaskStatus taskStatus;

	public Task(String storyID, String description) {
		taskID = IDGenerator.generateID(IDType.Task);
		this.storyID = storyID;
		this.taskDescription = description;
		taskStatus = TaskStatus.To_Do;
	}

	public String getTaskID() {
		return taskID;
	}

	public String getStoryID() {
		return storyID;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void updateTaskDescription(String newDescription) {
		taskDescription = newDescription;
		System.out.println("\tThe task " + taskID + " is updated with new description: "
				+ "\n\t  " + taskDescription);
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void moveTask(TaskStatus status) {
		TaskStatus oldStatus = taskStatus;
		taskStatus = status;
		System.out.println("\tThe task " + taskID
				+ " has moved from " + oldStatus.toString()
				+ " to " + taskStatus.toString());
	}

	public boolean matches(String taskID) {
		return this.taskID.equals(taskID);
	}

	public boolean isTaskCompleted() {
		return taskStatus == TaskStatus.Done;
	}

	public void printTask() {
		String taskInfo = "";
		taskInfo += "\n\tTaskID - " + taskID + " is associated with ";
		taskInfo += "StoryID - " + storyID + ".";
		System.out.println(taskInfo);
		printTaskStatus();
		System.out.println();		
	}

	public void printTaskStatus() {
		System.out.println("\tTaskID " + taskID + " current status is "
				+ taskStatus.toString() + ".");
	}

}

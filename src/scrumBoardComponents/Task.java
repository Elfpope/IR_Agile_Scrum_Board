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

	public void setTaskDescription(String newDescription) {
		taskDescription = newDescription;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus status) {
		this.taskStatus = status;
	}

	public boolean matches(String taskID) {
		return this.taskID.equals(taskID);
	}

	public boolean taskCompleted() {
		return taskStatus == TaskStatus.Done;
	}

	public void printTask() {
		String taskInfo = "";
		taskInfo += "\n\tTaskID - " + taskID + " is associated with ";
		taskInfo += "StoryID - " + storyID;
		taskInfo += " and its current status is" + taskStatus.toString() + ".";
		System.out.println(taskInfo);
	}

	public void printTaskStatus() {
		System.out.println("\tTaskID " + taskID + " current status is "
				+ taskStatus.toString() + ".");
	}

}

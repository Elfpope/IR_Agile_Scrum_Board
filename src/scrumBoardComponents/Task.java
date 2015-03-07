package scrumBoardComponents;

import scrumBoardManagement.IDGenerator;
import scrumBoardManagement.IDType;

public class Task {
	private String taskID;
	private String storyID;
	private String taskDescription;
	private TaskStatus taskStatus;
	
	public Task(String storyID, String description) {
		taskID = IDGenerator.generateID(IDType.Task);
		this.storyID = storyID;
		this.taskDescription = description;
		taskStatus = taskStatus.ToDo;
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

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	
	public boolean matches(String taskID) {
		return this.taskID.equals(taskID);
	}

	public void printTask() {
		String taskInfo = "";	
		taskInfo += "\nTaskID - " + taskID + " is associated with ";
		taskInfo += "StoryID - " + storyID;		
		System.out.println(taskInfo);		
	}
	
	public boolean taskCompleted(){
		return taskStatus == TaskStatus.Done;
	}

}

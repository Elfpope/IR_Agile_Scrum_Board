
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

}

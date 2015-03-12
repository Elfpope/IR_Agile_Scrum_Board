package scrumBoardComponents;

import java.util.ArrayList;
import java.util.List;

public class Story {
	private String storyID;
	private String storyDescription;
	private List<Task> tasks;

	public Story(String description) {
		storyID = IDGenerator.generateID(IDType.Story);
		this.storyDescription = description;
		tasks = new ArrayList<Task>();
	}

	public String getStoryID() {
		return storyID;
	}

	public String getStoryDescription() {
		return storyDescription;
	}

	public boolean matches(String storyID) {
		return this.storyID.equals(storyID);
	}

	public void printStory() {
		String storyInfo = "";
		storyInfo += "\n    StoryID - " + storyID;
		storyInfo += "\n    StoryDescription - " + storyDescription;
		System.out.println(storyInfo);
	}

	public boolean allTasksCompleted() {
		for (Task task : tasks) {
			if (!task.isTaskCompleted()) {
				return false;
			}
		}
		return true;
	}

	public boolean anyTaskExists() {
		if (!tasks.isEmpty()) {
			return true;
		}
		System.out.println("\tNo task in the story ");
		return false;
	}

	public void listTasks() {
		for (Task task : tasks) {
			task.printTask();
		}
	}

	public Task findTask(String taskID) {
		for (Task task : tasks) {
			if (task.matches(taskID))
				return task;
		}
		System.out.println("\tThe task with the given ID cannot be found! ");
		return null;
	}

	public void addTask(Task task) {
		tasks.add(task);
		System.out.println("\tTask " + task.getTaskID() + " is created now.");
		task.printTaskStatus();
	}

	public void removeTask(String taskID) {
		Task task = findTask(taskID);
		if (task != null) {
			tasks.remove(task);
			System.out.println("\tTask " + taskID + " is deleted now.");
		}
	}
}

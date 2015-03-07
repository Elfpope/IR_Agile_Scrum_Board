package scrumBoardComponents;

import java.util.ArrayList;
import java.util.List;

import scrumBoardManagement.IDGenerator;
import scrumBoardManagement.IDType;

public class Story {
	private String storyID;
	private String storyDescription;
	List<Task> tasks;

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
			if (!task.taskCompleted()) {
				return false;
			}
		}
		return true;
	}

	private boolean anyTaskExists() {
		if (!tasks.isEmpty()) {
			return true;
		}
		return false;
	}

	public void listTasks() {
		if (anyTaskExists()) {
			System.out.println("  Task List : ");
			for (Task task : tasks) {
				task.printTask();
			}
		} else {
			System.out.println("  No task in the story. ");
		}
	}

	public Task findTask(String taskID) {
		for (Task task : tasks) {
			if (task.matches(taskID))
				return task;
		}
		System.out.println("  No such task. ");
		return null;
	}

	public void addTask(Task task) {
		tasks.add(task);
		System.out.println("  Task " + task.getTaskID() + " is created now.");
	}

	public void removeTask(String taskID) {
		Task task = findTask(taskID);
		if (task != null) {
			tasks.remove(task);
			System.out.println("  Task " + taskID + " is deleted now.");
		} else {
			System.out.println("  No such task. ");
		}
	}

	public void updateTask(String taskID, String newDescription) {
		Task task = findTask(taskID);
		if (task != null) {
			task.setTaskDescription(newDescription);
			System.out.println("  Task " + taskID + " is updated now."
					+ "\n  Task decription is " + task.getTaskDescription());
		} else {
			System.out.println("  No such task. ");
		}
	}

	public void changeTaskStatus(String taskID, TaskStatus status) {
		Task task = findTask(taskID);
		if (task != null) {
			// TODO status transition guard
			task.setTaskStatus(status);
			System.out.println("  Task " + taskID + " is moved to "
					+ task.getTaskStatus().toString() + " now.");
		} else {
			System.out.println("  No such task. ");
		}

	}
}

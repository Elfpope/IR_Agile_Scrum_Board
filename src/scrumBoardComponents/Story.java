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
		storyInfo += "\nStoryID - " + storyID;
		storyInfo += "\nStoryDescription - " + storyDescription;
		System.out.println(storyInfo);		
	}

	public boolean allTasksCompleted() {
		for (Task task: tasks){			
			if (!task.taskCompleted()){
				return false;
			}
		}
		return true;
	}

	
}

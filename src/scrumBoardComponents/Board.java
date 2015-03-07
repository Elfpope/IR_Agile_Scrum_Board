package scrumBoardComponents;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private List<Story> activeStories;
	private List<Story> completedStories;
	private List<Story> deletedStories;

	public Board() {
		activeStories = new ArrayList<Story>();
		completedStories = new ArrayList<Story>();
		deletedStories = new ArrayList<Story>();
	}

	public Story findActiveStory(String storyID) {
		for (Story story : activeStories) {
			if (story.matches(storyID))
				return story;
		}
		System.out.println("  No such active story. ");
		return null;
	}

	public boolean activeStoryExists(String storyID) {
		for (Story story : activeStories) {
			if (story.matches(storyID))
				return true;
		}
		System.out.println("  No such active story. ");
		return false;
	}

	private boolean anyStoryExists() {
		if (!activeStories.isEmpty() || !completedStories.isEmpty()
				|| !deletedStories.isEmpty()) {
			return true;
		}
		return false;
	}

	public void listStories() {
		if (!anyStoryExists()) {
			System.out.println("  No story in the board. ");
		} else {
			if (!activeStories.isEmpty()) {
				System.out.println("\n  Active Stories List : ");
				for (Story story : activeStories) {					
					story.printStory();
				}
			}
			if (!completedStories.isEmpty()) {
				System.out.println("\n  Completed Stories List : ");
				for (Story story : completedStories) {					
					story.printStory();
				}
			}
			if (!deletedStories.isEmpty()) {
				System.out.println("\n  Deleted Stories List : ");
				for (Story story : deletedStories) {					
					story.printStory();
				}
			}
		}
	}

	public void addActiveStory(Story story) {
		activeStories.add(story);
		System.out.println("  Story " + story.getStoryID() + " is created now.");
	}

	public void removeActiveStory(String storyID) {
		Story story = findActiveStory(storyID);
		if (story != null) {
			activeStories.remove(story);
			deletedStories.add(story);
			System.out.println("  Story " + story.getStoryID() + " is deleted now.");
		} else {
			System.out.println("  No such story. ");
		}
	}

	public void completeActiveStory(String storyID) {
		Story story = findActiveStory(storyID);
		if (story == null) {
			System.out.println("  No such story. ");
		} else if (story.allTasksCompleted()) {
			activeStories.remove(story);
			completedStories.add(story);
			System.out.println("  Story " + storyID + " is completed now.");
		} else {
			System.out.println("  Some tasks need to be done before complete a story. ");
		}
	}

	public void listTasks(String storyID) {
		Story story = findActiveStory(storyID);
		story.listTasks();		
	}
}

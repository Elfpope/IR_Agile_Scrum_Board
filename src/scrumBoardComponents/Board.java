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

	public void printMenu() {
		String menu = "";
		menu += "\nAgile Scrumb Broad Menu -- ";
		menu += "\n    1: Create a story";
		menu += "\n    2: List stories";
		menu += "\n    3: Delete a story";
		menu += "\n    4: Complete a story";
		menu += "\n    5: Create a task";
		menu += "\n    6: List tasks";
		menu += "\n    7: Delete a task";
		menu += "\n    8: Move a task";
		menu += "\n    9: Update a task";
		menu += "\n    0: Exit";
		System.out.println(menu);
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
				for (Story story : activeStories) {
					System.out.println("  Active Stories List : ");
					story.printStory();
				}
			}
			if (!completedStories.isEmpty()) {
				for (Story story : completedStories) {
					System.out.println("  Completed Stories List : ");
					story.printStory();
				}
			}
			if (!deletedStories.isEmpty()) {
				for (Story story : deletedStories) {
					System.out.println("  Deleted Stories List : ");
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
}

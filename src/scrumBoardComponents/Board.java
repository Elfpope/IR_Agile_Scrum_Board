package scrumBoardComponents;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private List<Story> activeStories;
	private List<Story> completedStories;

	public Board() {
		activeStories = new ArrayList<Story>();
		completedStories = new ArrayList<Story>();
	}

	public Story findActiveStory(String storyID) {
		for (Story story : activeStories) {
			if (story.matches(storyID))
				return story;
		}
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

	public boolean anyStoryExists() {
		if (!activeStories.isEmpty() || !completedStories.isEmpty()) {
			return true;
		}
		return false;
	}

	public void addActiveStory(Story story) {
		activeStories.add(story);
		System.out.println("    Story " + story.getStoryID()
				+ " is created now.");
	}

	public void listStories() {
		if (!activeStories.isEmpty()) {
			System.out.println("\n    Active Stories List : ");
			for (Story story : activeStories) {
				story.printStory();
			}
		}
		if (!completedStories.isEmpty()) {
			System.out.println("\n    Completed Stories List : ");
			for (Story story : completedStories) {
				story.printStory();
			}
		}
	}

	public void removeActiveStory(String storyID) {
		Story story = findActiveStory(storyID);
		if (story != null) {
			activeStories.remove(story);
			System.out.println("\tStory " + story.getStoryID()
					+ " is deleted now.");
		} else {
			System.out.println("\tNo such story. ");
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
			System.out
					.println("  Some tasks need to be done before complete a story. ");
		}
	}

}

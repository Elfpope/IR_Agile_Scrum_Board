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
		System.out.println("\tNo active story can be found with given StoryID! ");
		return null;
	}

	public Story findCompletedStory(String storyID) {
		for (Story story : completedStories) {
			if (story.matches(storyID))
				return story;
		}
		System.out.println("\tNo completed story can be found with given StoryID! ");
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
		System.out.println("\tNo story in the board. ");
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

	// remove either active or completed story
	public void removeStory(String storyID) {
		Story story = findActiveStory(storyID);
		/*  try to find a matched story from active story list first
		    if not found, then try from completed story list */
		if (story != null) {
			removeStoryFromList(story, activeStories);
		} else {
			story = findCompletedStory(storyID);
			if (story != null) {
				removeStoryFromList(story, completedStories);
			}
		}
	}

	private void removeStoryFromList(Story story, List<Story> stories) {
		stories.remove(story);
		System.out
				.println("\tStory " + story.getStoryID() + " is deleted now.");
	}

	public void completeActiveStory(String storyID) {
		Story story = findActiveStory(storyID);
		/* Lazy evaluation in the logic operation
		   story.allTasksCompleted() will only be called if story is not null*/		 
		if (story != null && story.allTasksCompleted()) {
			activeStories.remove(story);
			completedStories.add(story);
			System.out.println("  Story " + storyID + " is completed now.");
		} else {
			System.out
					.println("  Some tasks need to be done before complete a story. ");
		}
	}
}

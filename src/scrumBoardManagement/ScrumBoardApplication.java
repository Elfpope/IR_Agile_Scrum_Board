package scrumBoardManagement;

import scrumBoardComponents.Board;
import scrumBoardComponents.IDType;
import scrumBoardComponents.Story;
import scrumBoardComponents.Task;
import scrumBoardComponents.TaskStatus;

public class ScrumBoardApplication {

	public static void main(String[] args) {
		try {
			new ScrumBoardApplication();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An unexpected error has arisen. Please investigate.");
			System.exit(1);
		}
	}

	public ScrumBoardApplication() {
		Board board = new Board();
		printMenu();
		char choice = readChoice();
		// Choice for exit is 0.
		while (choice != '0') {
			execute(board, choice);
			printMenu();
			choice = readChoice();
		}
		System.out.println("The application is terminated.");
	}

	private void printMenu() {
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

	private char readChoice() {
		System.out.print("\n  Choice (1~0) : ");
		return InputScanner.nextChar();
	}

	private void execute(Board board, char choice) {
		switch (choice) {
		case '1':
			addActiveStory(board);
			break; // Create a story
		case '2':
			listStories(board);
			break; // List stories
		case '3':
			removeStory(board);
			break; // Delete a story
		case '4':
			completeActiveStory(board);
			break; // Complete a story
		case '5':
			creatTask(board);
			break; // Create a task
		case '6':
			listTasks(board);
			break; // List tasks
		case '7':
			deleteTask(board);
			break; // Delete a task
		case '8':
			moveTask(board);
			break; // Move a task
		case '9':
			updateTask(board);
			break; // Update a task
		default:
			System.out.println("    No such choice");
		}
	}

	private String readID(IDType type) {
		String ID = null;
		boolean resultOfIDValification = false;
		do {
			if (type == IDType.Story) {
				System.out.print("    Please input story ID: ");
			} else {
				System.out.print("    Please input task ID: ");
			}
			ID = InputScanner.nextLine().toUpperCase();
			resultOfIDValification = InputScanner.idValid(type, ID);
		} while (!resultOfIDValification);
		return ID;
	}

	private String readDescription(IDType type) {
		String description = null;
		do {
			if (type == IDType.Story) {
				System.out.print("    Please input story description: ");
			} else {
				System.out.print("    Please input task description: ");
			}
			description = InputScanner.nextLine();
		} while (description == null);
		return description;
	}

	private TaskStatus readTaskStatus() {
		TaskStatus.printTaskStatus();
		System.out.print("  Status choice (1~4): ");
		char choice = InputScanner.nextChar();
		TaskStatus status = charToTaskStatus(choice);
		return status;
	}

	private TaskStatus charToTaskStatus(char choice) {
		TaskStatus status = null;
		switch (choice) {
		case '1':
			status = TaskStatus.To_Do;
			break;
		case '2':
			status = TaskStatus.In_Process;
			break;
		case '3':
			status = TaskStatus.To_Verify;
			break;
		case '4':
			status = TaskStatus.Done;
			break;
		default:
			System.out.println("    Invalid choice");
		}
		return status;
	}

	private void addActiveStory(Board board) {
		String description = readDescription(IDType.Story);
		Story story = new Story(description);
		board.addActiveStory(story);
	}

	private void listStories(Board board) {
		if (board.anyStoryExists()) {
			board.listStories();
		}
	}

	private void removeStory(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			board.removeStory(storyID);
		}
	}

	private void completeActiveStory(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			board.completeActiveStory(storyID);
		}
	}

	private void creatTask(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				String taskDescription = readDescription(IDType.Task);
				Task task = new Task(storyID, taskDescription);
				story.addTask(task);
			}
		}
	}

	private void listTasks(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				if (story.anyTaskExists()) {
					story.listTasks();
				}
			}
		}
	}

	private void deleteTask(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				if (story.anyTaskExists()){
					String taskID = readID(IDType.Task);
					Task task = story.findTask(taskID);
					if (task != null) {
						story.removeTask(taskID);
					}
				}				
			}
		}
	}

	private void moveTask(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				if (story.anyTaskExists()){
					String taskID = readID(IDType.Task);
					Task task = story.findTask(taskID);
					if (task != null) {
						task.printTaskStatus();
						TaskStatus originalStatus = task.getTaskStatus();
						if (!task.isDone()) {
							TaskStatus newStatus = readTaskStatus();
							if (TaskStatus.statusTransitionCorrect(originalStatus,
									newStatus)) {
								task.moveTask(newStatus);
							} else {
								System.out.println("\tIt's not a proper procedure to move "
										+ "Task " + taskID + " from "
										+ originalStatus.toString() + " to "
										+ newStatus.toString());	
							}
						}					
					}
				}
			}
		}
	}

	private void updateTask(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				if (story.anyTaskExists()){
					String taskID = readID(IDType.Task);
					Task task = story.findTask(taskID);
					if (task != null) {
						String newDescription = readDescription(IDType.Task);
						task.updateTaskDescription(newDescription);
					}
				}
			}
		}
	}
}

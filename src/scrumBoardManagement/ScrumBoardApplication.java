package scrumBoardManagement;

import scrumBoardComponents.Board;
import scrumBoardComponents.Story;
import scrumBoardComponents.Task;
import scrumBoardComponents.TaskStatus;

public class ScrumBoardApplication {

	public static void main(String[] args) {
		new ScrumBoardApplication();
	}

	public ScrumBoardApplication() {
		Board board = new Board();
		printMenu();
		char choice = readChoice();
		// Choice for exit is 0.
		while (choice != 0) {
			execute(board, choice);
			printMenu();
			choice = readChoice();
		}
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
			removeActiveStory(board);
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
		do {
			if (type == IDType.Story) {
				System.out.print("    Please input story ID: ");
			} else {
				System.out.print("    Please input task ID: ");
			}
			ID = InputScanner.nextLine();
		} while (ID == null);
		return ID.toUpperCase();
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
		char choice = '0';
		TaskStatus status = null;
		do {
			TaskStatus.printTaskStatus();
			System.out.print("  Status choice (1~4): ");
			choice = InputScanner.nextChar();
			status = charToTaskStatus(choice);
		} while (choice == '0' || status == null);
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
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}

	private void removeActiveStory(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			board.removeActiveStory(storyID);
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}

	private void completeActiveStory(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			board.completeActiveStory(storyID);
		} else {
			System.out.println("\tNo story in the board. ");
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
			} else {
				System.out.println("\tThe story cannot be found! ");
			}
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}

	private void listTasks(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				if (story.anyTaskExists()) {
					story.listTasks();
				} else {
					System.out.println("\tNo task can be found! ");
				}
			} else {
				System.out.println("\tThe story cannot be found! ");
			}
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}

	private void deleteTask(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				String taskID = readID(IDType.Task);
				Task task = story.findTask(taskID);
				if (task != null) {
					story.removeTask(taskID);
				} else {
					System.out.println("\tThe task cannot be found! ");
				}
			} else {
				System.out.println("\tThe story cannot be found! ");
			}
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}

	private void moveTask(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				String taskID = readID(IDType.Task);
				Task task = story.findTask(taskID);
				if (task != null) {
					task.printTaskStatus();
					TaskStatus originalStatus = task.getTaskStatus();
					TaskStatus newStatus = readTaskStatus();
					while (!TaskStatus.statusTransitionCorrect(originalStatus,
							newStatus)) {
						System.out.println("\tTask " + taskID
								+ " cannot move from "
								+ originalStatus.toString() + " to "
								+ newStatus.toString()
								+ "\n\tPlease reselect your choice.");
						task.printTaskStatus();
						newStatus = readTaskStatus();
					}
					task.setTaskStatus(newStatus);
					System.out.println("\tThe task " + taskID
							+ " has moved from " + originalStatus.toString()
							+ " to " + newStatus.toString());
				} else {
					System.out.println("\tThe task cannot be found! ");
				}
			} else {
				System.out.println("\tThe story cannot be found! ");
			}
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}

	private void updateTask(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			Story story = board.findActiveStory(storyID);
			if (story != null) {
				String taskID = readID(IDType.Task);
				Task task = story.findTask(taskID);
				if (task != null) {
					String newDescription = readDescription(IDType.Task);
					task.setTaskDescription(newDescription);
				} else {
					System.out.println("\tThe task cannot be found! ");
				}
			} else {
				System.out.println("\tThe story cannot be found! ");
			}
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}
}

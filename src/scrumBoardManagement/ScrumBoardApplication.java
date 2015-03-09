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
			if(!resultOfIDValification){
				System.out.println("    Input ID is not valid. "
								 + "\n      Story ID needs to start with 'S' or 's' then is followed by an integer."
								 + "\n      Task ID needs to start with 'T' or 't' then is followed by an integer.");
			}
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
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}

	private void removeStory(Board board) {
		if (board.anyStoryExists()) {
			String storyID = readID(IDType.Story);
			board.removeStory(storyID);
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
					System.out.println("\tNo task in the story ");
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
				if (story.anyTaskExists()){
					String taskID = readID(IDType.Task);
					Task task = story.findTask(taskID);
					if (task != null) {
						story.removeTask(taskID);
					} else {
						System.out.println("\tThe task with the given ID cannot be found! ");
					}
				} else {
					System.out.println("\tNo task in the story ");
				}				
			} else {
				System.out.println("\tThe active story with the given ID cannot be found! ");
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
				if (story.anyTaskExists()){
					String taskID = readID(IDType.Task);
					Task task = story.findTask(taskID);
					if (task != null) {
						task.printTaskStatus();
						TaskStatus originalStatus = task.getTaskStatus();
						if (originalStatus == TaskStatus.Done) {
							System.out.println("\tThe task " + taskID 
									+ " has been completed and can no longer be moved.");
						} else {
							TaskStatus newStatus = readTaskStatus();
							if (TaskStatus.statusTransitionCorrect(originalStatus,
									newStatus)) {
								task.setTaskStatus(newStatus);
								System.out.println("\tThe task " + taskID
										+ " has moved from " + originalStatus.toString()
										+ " to " + newStatus.toString());
							} else {
								System.out.println("\tIt's not a proper procedure to move "
										+ "Task " + taskID + " from "
										+ originalStatus.toString() + " to "
										+ newStatus.toString());	
							}
						}					
					} else {
						System.out.println("\tThe task with the given ID cannot be found!  ");
					}
				} else {
					System.out.println("\tNo task in the story ");
				}
			} else {
				System.out.println("\tThe active story with the given ID cannot be found! ");
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
				if (story.anyTaskExists()){
					String taskID = readID(IDType.Task);
					Task task = story.findTask(taskID);
					if (task != null) {
						String newDescription = readDescription(IDType.Task);
						task.setTaskDescription(newDescription);
						System.out.println("\tThe task " + taskID + " is updated with new description: "
								+ "\n\t  " + task.getTaskDescription());
					} else {
						System.out.println("\tThe task with the given ID cannot be found! ");
					}
				} else {
					System.out.println("\tNo task in the story ");
				}
			} else {
				System.out.println("\tThe active story with the given ID cannot be found! ");
			}
		} else {
			System.out.println("\tNo story in the board. ");
		}
	}
}

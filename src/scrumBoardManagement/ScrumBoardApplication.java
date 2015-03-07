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
			board.listStories();
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
			System.out.println("    Invalid choice");
		}
	}

	private void updateTask(Board board) {
		String storyID = readID(IDType.Story);
		Story story = board.findActiveStory(storyID);
		//if not story found
		String taskID = readID(IDType.Task);
		String newDescription = readDescription(IDType.Task);
		story.updateTask(taskID, newDescription);	
	}

	private void moveTask(Board board) {
		String storyID = readID(IDType.Story);
		Story story = board.findActiveStory(storyID);
		String taskID = readID(IDType.Task);
		TaskStatus status = readTaskStatus();
		story.changeTaskStatus(taskID, status);	
	}

	private TaskStatus readTaskStatus() {
		TaskStatus.printTaskStatus();
		System.out.print("  Status choice (1~4): ");
		char choice = InputScanner.nextChar();
		TaskStatus status = charToTaskStatus(choice);
		while (status == null){
			TaskStatus.printTaskStatus();
			System.out.print("  Status choice (1~4): ");
			choice = InputScanner.nextChar();
			status = charToTaskStatus(choice);
		}
		return status;
	}
	
	private TaskStatus charToTaskStatus(char choice){
		TaskStatus status = null;
		switch(choice){
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

	private void deleteTask(Board board) {
		String storyID = readID(IDType.Story);
		Story story = board.findActiveStory(storyID);
		String taskID = readID(IDType.Task);
		story.removeTask(taskID);	
	}

	private void listTasks(Board board) {
		String storyID = readID(IDType.Story);
		board.listTasks(storyID);
	}

	private void creatTask(Board board) {
		String storyID = readID(IDType.Story);
		Story story = board.findActiveStory(storyID);
		String taskDescription = readDescription(IDType.Task);
		Task task = new Task(storyID, taskDescription);
		story.addTask(task);		
	}

	private void completeActiveStory(Board board) {
		String storyID = readID(IDType.Story);
		board.completeActiveStory(storyID);		
	}

	private String readID(IDType type) {
		if (type == IDType.Story) {
			System.out.print("  Please input story ID: ");
		} else {
			System.out.print("  Please input task ID: ");
		}
		return InputScanner.nextLine();
	}
	
	private String readDescription(IDType type) {
		if (type == IDType.Story){
			System.out.print("  Please input story description: ");
		} else {
			System.out.print("  Please input task description: ");
		}		
		return InputScanner.nextLine();
	}

	private void addActiveStory(Board board) {
		String description = readDescription(IDType.Story);
		Story story = new Story(description);
		board.addActiveStory(story);
	}

	private void removeActiveStory(Board board){
		String storyID = readID(IDType.Story);
		board.removeActiveStory(storyID);
	}
}

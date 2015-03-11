package scrumBoardComponents;

public enum TaskStatus {
	To_Do, In_Process, To_Verify, Done;

	public static void printTaskStatus() {
		String taskStatus = "";
		taskStatus += "\nTask status -- ";
		taskStatus += "\n    1: To Do";
		taskStatus += "\n    2: In Process";
		taskStatus += "\n    3: To Verify";
		taskStatus += "\n    4: Done";
		System.out.println(taskStatus);
	}

	/*
	 * Status change guard: 
	 * Two compared statuses cannot be the same;
	 * Done can't be changed; 
	 * To_Verify can be change to any other state; 
	 * In_Process can't be changed to Done; 
	 * To_Do can't be changed to either To_Verify or Done.
	 */
	public static boolean statusTransitionCorrect(TaskStatus originalStatus,
			TaskStatus newStatus) {
		if (originalStatus == newStatus) {
			return false;
		} else if (originalStatus == TaskStatus.Done) {
			return false;
		} else if (originalStatus == TaskStatus.In_Process
				&& newStatus == TaskStatus.Done) {
			return false;
		} else if (originalStatus == TaskStatus.To_Do
				&& (newStatus == TaskStatus.To_Verify || newStatus == TaskStatus.Done)) {
			return false;
		} else
			return true;
	}
	
	public static TaskStatus charToTaskStatus(char choice) {
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
}

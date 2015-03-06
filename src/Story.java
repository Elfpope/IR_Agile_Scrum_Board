import java.util.ArrayList;
import java.util.List;


public class Story {
	private String storyID;
	private String storyDescription;
	List<Task> tasks;
	
	public Story(String description) {
		storyID = IDGenerator.generateID(IDType.Story);
		this.storyDescription = description;
		tasks = new ArrayList<Task>();
	}

}

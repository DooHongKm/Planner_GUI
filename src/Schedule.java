import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
   
	String title, memo;
	LocalDateTime startingTime, endingTime;
  
	private static DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
   
	private Schedule() { 
		
		System.out.println("Empty Schedule");
		
	}

	Schedule(String s) {
		
		String[] tokens = s.split("//");
		title = tokens[0];
		startingTime = LocalDateTime.parse(tokens[1], form);
		endingTime = LocalDateTime.parse(tokens[2], form);
		if(tokens.length == 4) memo = tokens[3];
		else memo = "";
		
	}
   
}
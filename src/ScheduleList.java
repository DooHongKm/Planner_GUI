import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleList{
	
	ArrayList<Schedule> schedulelist = new ArrayList<>();
	
	public ScheduleList() {
		
		System.out.println("Unknown File");
		
	}
	
	ScheduleList(String fileName){
		
		try {
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			String line;
			while(scan.hasNext()) {
				line = scan.nextLine();
				if(line.isEmpty() || line.startsWith("/")) continue;
				else {
					Schedule schedule = new Schedule(line);
					schedulelist.add(schedule);
				}
			}
			scan.close();
		} catch (Exception e) {
			System.out.println("Unknown File");
		}
		
	}
   
}
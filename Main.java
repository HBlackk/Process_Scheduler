package improvement;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	//Arraylists from csv file
	public static ArrayList<String> processid = new ArrayList<>();
	public static ArrayList<String> arrivaltime = new ArrayList<>();
	public static ArrayList<String> priority = new ArrayList<>();
	public static ArrayList<String> bursttime = new ArrayList<>();
	public static float totaltime;
	public static float totalwait;
	//time the program starts
	public static long start = System.currentTimeMillis();
	
	public static void main(String[] args) throws FileNotFoundException {		
	
		//csv reader
		Scanner scanner = new Scanner(new File("data.csv"), "UTF-8");			
		String[] row = new String[3];				
		System.out.println("");		
		
		while(scanner.hasNext())
		{
			row = scanner.nextLine().split(",");			
			processid.add(row[0]);
			arrivaltime.add(row[1]);
			priority.add(row[2]);
			bursttime.add(row[3]);	
			//adds each row of the csv file to a separate arraylist
		}				
		processid.set(0, "1");		
		System.out.print("| Process:      ");
		System.out.println(Arrays.deepToString(processid.toArray()));
		System.out.print("| Arrival Time: ");
		System.out.println(Arrays.deepToString(arrivaltime.toArray()));
		System.out.print("| Burst Time:   ");
		System.out.println(Arrays.deepToString(bursttime.toArray()));
		System.out.print("| Priority:     ");
		System.out.println(Arrays.deepToString(priority.toArray()));
		//prints out the values from the csv file
		System.out.println("\n" + "The processes are executed in the following order: ");		
		
		Scheduler.roundrobin1(4);
		//the quantum is increased on the second round robin
		Scheduler.roundrobin2(8);
		Scheduler.fcfs();
		//runs the multilevel queue
		long end = System.currentTimeMillis();	
		long total = end-start;		
		System.out.println();
		System.out.println("\n" + "Start time: " + start);
		System.out.println("End time:   " + end);
		System.out.println("Total time: " + total);	
		//prints the total time of the program
	}
}

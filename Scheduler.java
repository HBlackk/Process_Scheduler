package improvement;

import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {	
		
	static int[] process= new int[10];
	public int quantum;
	static int n;
	static int q;
	static int[] overalltime = new int[25];	
	 	
	//first round robin (4)
	static ArrayList<Integer> highpriority = new ArrayList<Integer>(Collections.nCopies(6, 0));
	//second round robin (8)
	static ArrayList<Integer> mediumpriority = new ArrayList<Integer>(Collections.nCopies(6, 0));
	//FCFS
	static ArrayList<Integer> lowpriority = new ArrayList<Integer>(Collections.nCopies(6, 0));
	
	public static void roundrobin1(int quantum)
	{		
		for (int i=0; i<6; i++)
		{
			//sees whether the process has a high priority
			if (Integer.valueOf(Main.priority.get(i)) <= 7)
			{
				//Adds the process to the queue
				highpriority.add(i);
				System.out.println(" " + Main.processid.get(i));				
			}			
		}				
		int[] hpriority = new int[highpriority.size()]; //high priority array		
		
		for (int i=0; i<highpriority.size(); i++)
		{
			//moves the values from the arraylist to the array
			hpriority[i]=highpriority.get(i);			
		}
		
		int[] bursttimecopy = new int[highpriority.size()];
		for (int i=0; i<highpriority.size(); i++)
		{
			//moves the burst time values to the array
			bursttimecopy[i] = Integer.valueOf(Main.bursttime.get(hpriority[i]));			
		}			
		//increases the priority if a process is in the queue for some time
		if (System.currentTimeMillis() == Main.start+10)
		{
			for(int i=0; i<5; i++)
			{
				Integer.parseInt(Main.priority.get(i), 1);
			}			
		}		
		
		int time = overalltime[q];	
		boolean finished = true;		
		while(finished == true)
		{		
			//Continues until all processes are complete
			for (int i=0; i<5; i++)
			{
				if (bursttimecopy[i] > 0)
				{
					finished = false;
					
					if (bursttimecopy[i] > quantum)
					{
						time = time + quantum;
						bursttimecopy[i] = bursttimecopy[i] - quantum;						
					}
					else
					{
						time = time + bursttimecopy[i];
						Main.totaltime = time;		
						//adds up the time for the processes
					}
				}				
			}							
		}			
	}
	
	public static void roundrobin2(int quantum)
	{
		//the same as the first round robin, however the quantum is 8
		//and its used for the medium priority processes
		for (int i=0; i<6; i++)
		{
			if (Integer.valueOf(Main.priority.get(i)) <= 14 && Integer.valueOf(Main.priority.get(i)) > 7)
			{
				//Adds the process to the medium queue.
				mediumpriority.add(i);
				System.out.println(" "+Main.processid.get(i));
			}			
		}				
		int[] mpriority = new int[10]; //new array of size 30.
		
		for (int i=0; i<mediumpriority.size(); i++)
		{
			mpriority[i]=mediumpriority.get(i);
		}
		
		int[] bursttimecopy = new int[mediumpriority.size()];
		for (int i=0; i<mediumpriority.size(); i++)
		{
			bursttimecopy[i] = Integer.valueOf(Main.bursttime.get(mpriority[i]));
		}
		//Aging
		if (System.currentTimeMillis() == Main.start+10)
		{
			for(int i=0; i<5; i++)
			{
				Integer.parseInt(Main.priority.get(i), 1);
			}			
		}
		
		int time = 0;	
		boolean finished = true;
		while(finished == true)
			//Continues until all processes are complete
		{						
			for (int i=0; i<5; i++)
			{
				if (bursttimecopy[i] > 0)
				{
					finished = false;
					
					if (bursttimecopy[i] > quantum)
					{
						time = time + quantum;
						bursttimecopy[i] = bursttimecopy[i] - quantum;
					}
					else
					{
						time = time + bursttimecopy[i];
						Main.totaltime = time;
						//Adds up the time of the processes.
					}
				}
			}						
		}		
	}					
		public static void fcfs()
			{				
			//First come first served.
				for (int i=0; i<5; i++)
				{					
					process[i] = i;
					if (Integer.valueOf(Main.priority.get(i)) > 14)
					{		
						//Added to the low priority list
						lowpriority.add(i, Integer.valueOf(Main.priority.get(i)));						
						System.out.print(" " + Main.processid.get(i));						
					}					
				}	
			}
}
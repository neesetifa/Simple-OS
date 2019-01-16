import java.io.*;
import java.util.*;


public class mainClass
{
    	static int NUMBER_OF_USERS = 4;
    	static int NUMBER_OF_DISKS = 2;
    	static int NUMBER_OF_PRINTERS = 3;

    	static Disk[] disks = new Disk[NUMBER_OF_DISKS]; 
    	static ResourceManager RM_disk = new ResourceManager(NUMBER_OF_DISKS); 
	static Printer[] printers=new Printer[NUMBER_OF_PRINTERS];
	static ResourceManager RM_printer = new ResourceManager(NUMBER_OF_PRINTERS); 

	static UserThread[] users=new UserThread[NUMBER_OF_USERS];

	static DirectoryManager dir_m = new DirectoryManager();
	static DiskManager  disk_m= new DiskManager();
	
	public void run()
	{
		for(int i=0;i<NUMBER_OF_DISKS;++i)
		{
			disks[i]=new Disk();
		}
		for(int i=0;i<NUMBER_OF_PRINTERS;++i)
		{
			int index=i+1;
			printers[i]=new Printer(index);
		}
		for(int i=0;i<NUMBER_OF_USERS;++i)
		{
			users[i]=new UserThread(i+1);
		}
		users[0].start();
		users[1].start();
		users[2].start();
		users[3].start();
		
		//just for printing a message when all threads are done
		try{
			for(int i=0;i<4;i++)
			{	
				users[i].join();
			}
			System.out.println("Done");
		}
		catch (InterruptedException e) {
			e.printStackTrace();	
		}		
	}

 }

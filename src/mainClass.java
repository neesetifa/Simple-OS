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
	
	//static Disk disk = new Disk();
	//static UserThread userxx=new UserThread(4);



			
		



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


/*	
	public static void main(String[] args)
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
		
	
		//userxx.start();

		/*
		try{
		//StringBuffer b = new StringBuffer("caonima");
		String b = "caonima";
		FileInfo file = new FileInfo();
		file.diskNumber=mainClass.RM_disk.request();
		file.startingSector=mainClass.disk_m.get_next_free_sector(file.diskNumber);
		file.fileLength=5;
		mainClass.dir_m.enter(b,file);

		//StringBuffer a = new StringBuffer("sbsbs");
		String a ="sbsbs";
		FileInfo file2 = new FileInfo();
		file2.diskNumber=mainClass.RM_disk.request();
		file2.startingSector=mainClass.disk_m.get_next_free_sector(file2.diskNumber);
		file2.fileLength=7;
		mainClass.dir_m.enter(a,file2);


		FileInfo fi=new FileInfo();
		String c="caonima";
		fi=mainClass.dir_m.lookup(c);

		System.out.println(fi.diskNumber+" "+fi.startingSector+" "+fi.fileLength);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}/*
		
		
		/*
		try
		{
			
			
			Printer printer6 = new Printer(6);
			StringBuffer b = new StringBuffer("caonima");
			disk.write(1, b);
			printer6.print(b);	
			int a=disk_m.get_next();
			System.out.println(a);
			
			
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}/*

			
		/*
		try{
		FileInfo file = new FileInfo();
		file.diskNumber=RM_disk.request();
		file.startingSector=disk_m.get_next_free_sector(file.diskNumber);
		file.fileLength=0;
		StringBuffer sb = new StringBuffer("caonima");
		
		
		disks[file.diskNumber].write(file.startingSector+file.fileLength,sb);
		
		
		//int a=disks[file.diskNumber].

		}
		catch(Exception e)
		{e.printStackTrace();}
		/*

	    
	}
*/
   
}
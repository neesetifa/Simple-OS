import java.io.*;
import java.util.*;


class Printer
{
	int pid;
	Printer(int id)
	{
		pid=id;
	}
	void print(StringBuffer sb) throws InterruptedException, IOException 
	{
		//System.out.println(sb);
		Thread.sleep(2750);
		String file_name="./outputs/PRINTER"+pid;
		FileWriter f=new FileWriter(file_name,true);
		BufferedWriter w=new BufferedWriter(f);
		w.write(sb.toString()+"\n");
		w.close();
	}


}


class PrintJobThread 
	extends Thread
{
	String print_file_name;
	PrintJobThread(String name)  
	{
		super();
		print_file_name=name;
	}

	public void run()
	{

		try {
			
			int pid = mainClass.RM_printer.request();
			Printer printer = mainClass.printers[pid];

			FileInfo file=new FileInfo();
			file=mainClass.dir_m.lookup(print_file_name);
			
			StringBuffer sb= new StringBuffer();

			
			System.out.println("#Printer-"+(pid+1)+" is printing file: "+print_file_name);
			for(int i = file.startingSector; i<file.startingSector+file.fileLength;++i)
   			{	
   				
				//System.out.println(print_file_name+"--disk number"+file.diskNumber+" starting sector:"+file.startingSector+" file length"+file.fileLength);
				mainClass.disks[file.diskNumber].read(i, sb);
				
   				printer.print(sb);
				
   			}
	
			mainClass.RM_printer.release(pid);
			System.out.println("#Printer-"+(pid+1)+" is now free");
		
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	


}
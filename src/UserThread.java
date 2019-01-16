import java.io.*;
import java.util.*;

class UserThread
	extends Thread
{
	int uid;
	UserThread(int id)
	{
		super();
		uid=id;
	}

	
	public void run()
	{
		FileReader fd=null;
		BufferedReader br=null;
		try
		{
			fd=new FileReader("./inputs/USER"+uid);
			br=new BufferedReader(fd);
			
		}
		catch(IOException e)
		{
			System.out.println("fail to open file.");
		}
		String line="";
		String read_file_name="";	

		try
		{
			StringBuffer sb=new StringBuffer();			
			while((line=br.readLine())!=null)
			{
				
				//System.out.println("1--start reading: "+line);
				
				if(line.substring(0,5).equals(".save"))
				{
					read_file_name=line.substring(6);
					
									
					
					FileInfo file = new FileInfo();
					file.diskNumber=mainClass.RM_disk.request();
					file.startingSector=mainClass.disk_m.get_next_free_sector(file.diskNumber);
					file.fileLength=0;
					line=br.readLine();
					
					System.out.println("#USER-"+uid+" is saving file "+read_file_name+" on DISK-"+file.diskNumber);	
					//Disk current_disk=mainClass.disks[file.diskNumber];
					while(!line.equals(".end"))
					{					
						sb.delete(0, sb.length());
						sb.append(line);
						mainClass.disks[file.diskNumber].write(file.startingSector+file.fileLength,sb);
				
						file.fileLength+=1;
						System.out.println("#DISK-"+(file.diskNumber+1)+" is saving USER"+uid+"'s data: "+line);
						line=br.readLine();
					}
					sb.delete(0, sb.length());
			
					
					//enter hash table value for file name and file info
					mainClass.dir_m.enter(read_file_name,file);
					//release current disk
            		mainClass.RM_disk.release(file.diskNumber);
					//line=br.readLine();
					System.out.println("#DISK-"+(file.diskNumber+1)+" is now free");
				
				}
			

				
				
				else if(line.substring(0,6).equals(".print"))
				{
					
					String file_name=line.substring(7);
					PrintJobThread print_job=new PrintJobThread(file_name);
					print_job.start();

					//just for printing a message, like the one in mainClass
					print_job.join();

					/*
					System.out.println("3--"+line);

					String print_file_name=line.substring(7);
					FileInfo file=new FileInfo();
					file=mainClass.dir_m.lookup(print_file_name);
					
					
					StringBuffer printerBuffer = new StringBuffer();
					Printer printer = new Printer(uid);
					System.out.println(print_file_name+"--disk number"+file.diskNumber+" starting sector:"+file.startingSector+" file length"+file.fileLength);
					
					try{
						for(int i = file.startingSector; i<file.startingSector+file.fileLength;++i)
   						{	
   							mainClass.disks[file.diskNumber].read(i, printerBuffer);
							//System.out.println(printerBuffer);
   							printer.print(printerBuffer);
							System.out.println("printing...");
   						}
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
	
					}
					*/
				}
				
			
			}	
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	
	}


}
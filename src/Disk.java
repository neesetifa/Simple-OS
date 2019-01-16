import java.util.*;
class Disk
{
	static final int NUM_SECTORS = 1024;
	StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];
	static int next_free_sector=0;    //record current empty position 
    	void write(int sector, StringBuffer data) throws InterruptedException
	{
		Thread.sleep(200);
		sectors[sector] = new StringBuffer(data.toString());		
		next_free_sector+=1;
	}
	void read(int sector, StringBuffer data) throws InterruptedException
	{
		Thread.sleep(200);
		data.delete(0, data.length());
		StringBuffer a=sectors[sector];
		data.append(a);
	}

}

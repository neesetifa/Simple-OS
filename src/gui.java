import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

//redirect the ouputstream to JTextArea
class my_output extends OutputStream {
	JTextArea text;

	my_output(JTextArea temp) {
		text = temp;
	}

	@Override
	public void write(int b) throws IOException {
	//transfer int type into char type		
	char a=(char) b;
	String s=String.valueOf(a);
	//add new output at the end of previous text
        text.append(s);
	//keeps reaching at the end of text area
        text.setCaretPosition(text.getDocument().getLength());
	}
}







public class gui extends JFrame {

	//original log, including all output    
	JTextArea text_log;
     
        JButton start_button = new JButton("Start");
	

	//initialize the position
	int last_index=10;
     
    	public gui() {
        	super("Simple OS");
        

		PrintStream aout=System.out;
 
        	text_log = new JTextArea(50, 10);
        	text_log.setEditable(false);

        	PrintStream print_stream = new PrintStream(new my_output(text_log));     
        	System.setOut(print_stream);
 
       	
		setLayout(null);
		
		start_button.setBounds(60,20,85,25);
		add(start_button);
		
		
		JScrollPane text_scroll=new JScrollPane(text_log);	
		text_scroll.setBounds(1050,20,500,520);	
		//original ouput, you can uncomment it for test
		//add(text_scroll);



		//===========================================
		// add User, Printer and Disk log display using JTextArea

		JTextArea[] user_log=new JTextArea[4];
		JScrollPane[] scroll_user=new JScrollPane[4];
		for(int i=0;i<4;i++)
		{
			user_log[i]=new JTextArea(50,10);
			user_log[i].setEditable(false);
			user_log[i].setText("USER"+(i+1)+"\n");
			scroll_user[i]=new JScrollPane(user_log[i]);	
			scroll_user[i].setBounds(50,70+i*110,450,90);	
			add(scroll_user[i]);


		}

		JTextArea[] printer_log=new JTextArea[3];
		JScrollPane[] scroll_printer=new JScrollPane[3];
		for(int i=0;i<3;i++)
		{
			printer_log[i] = new JTextArea(50, 10);
        	printer_log[i].setEditable(false);
			printer_log[i].setText("PRINTER"+(i+1)+"\n");
			scroll_printer[i]=new JScrollPane(printer_log[i]);	
			scroll_printer[i].setBounds(600,70+i*160,500,150);	
			add(scroll_printer[i]);
		}

		JTextArea[] disk_log=new JTextArea[2];
		JScrollPane[] scroll_disk=new JScrollPane[2];
		for(int i=0;i<2;i++)
		{
			disk_log[i] = new JTextArea(50, 10);
        	disk_log[i].setEditable(false);
			disk_log[i].setText("DISK"+(i+1)+"\n");
			scroll_disk[i]=new JScrollPane(disk_log[i]);	
			scroll_disk[i].setBounds(50+i*550,610,500,150);	
			add(scroll_disk[i]);
		}
		//===========================================




		//a document listener, if anything is inserted in text_log 
		
		text_log.getDocument().addDocumentListener(new DocumentListener() {
            	public void changedUpdate(DocumentEvent e) {
             	}
             	public void insertUpdate(DocumentEvent e) {
			String s = text_log.getText(); 
			int index=s.lastIndexOf("\n");
			//since output stream only add one character every time, we set symbol '#' to tell if it's a new line
			//if not a new line, we don't do anything
			if( last_index<index)
			{
				String t=s.substring(s.lastIndexOf("#"));
				if(t.substring(0,6).equals("#USER-"))
				{
					String tmp=t.substring(6,7);
					int num=Integer.parseInt(tmp);
					user_log[num-1].append(t);
					user_log[num-1].setCaretPosition(user_log[num-1].getDocument().getLength());
				}
					
				else if(t.substring(0,9).equals("#Printer-"))	
				{
					String tmp=t.substring(9,10);
					int num=Integer.parseInt(tmp);
					printer_log[num-1].append(t);
					printer_log[num-1].setCaretPosition(printer_log[num-1].getDocument().getLength());
				}

				else if(t.substring(0,6).equals("#DISK-"))
				{
					String tmp=t.substring(6,7);
					int num=Integer.parseInt(tmp);
					disk_log[num-1].append(t);
					disk_log[num-1].setCaretPosition(disk_log[num-1].getDocument().getLength());
				}
				//record last position of "#"
				last_index=index;
			}

             }
             public void removeUpdate(DocumentEvent e) {
             }
         });
	//===========================================
		
        
        ActionListener act_listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                printLog();	

            }
        };
        start_button.addActionListener(act_listener);

         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 830);
        setLocationRelativeTo(null);    
    }
    


	
	//run the main program
    public void printLog() {
        Runnable runnable = new Runnable() 
	{
            @Override
            public void run() 
	    {
		mainClass os=new mainClass();
		os.run();			
				
            }
        };
	Thread thread = new Thread(runnable);
        thread.start();
    }
     

		
    public static void main(String[] args) {
        Runnable runnable = new Runnable() 
	{
            @Override
            public void run() {
                new gui().setVisible(true);
            }
        };
	SwingUtilities.invokeLater(runnable);
    }
}

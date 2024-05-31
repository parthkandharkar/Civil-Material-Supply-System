import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.io.StringReader;
import java.io.LineNumberReader;
import java.util.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.*;
import java.text.*;
import java.util.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import java.net.*;

class Final_Bill implements ActionListener
{

    JLabel head=new JLabel("Bill");
    JLabel bno=new JLabel("Bill Number");
    JLabel bdate=new JLabel("Bill Date");
    JLabel vno=new JLabel("Customer Name");
    JLabel type=new JLabel("Amount");
    JLabel amt=new JLabel("Amount Paid");
    JLabel amtr=new JLabel("Amount Return");
	JLabel img=new JLabel(new ImageIcon("images/th.jpg"));
	JPanel mainp=new JPanel();
	JTextArea subp=new JTextArea();
	JButton prev=new JButton("Customer Page");
	JButton cancel=new JButton("Cancel");
	JFrame frame;
	JTextArea third=new JTextArea();
	JButton printbtn=new JButton("Print");
	Final_Bill(String abc,float amtt)
	{
		try{
			frame=new JFrame("Final Bill");
			frame.add(mainp);
			mainp.setBounds(0,0,790,670);
			mainp.setLayout(null);
			mainp.setBackground(new Color(255,218,18));
			mainp.add(subp);
			subp.setBounds(330,110,400,400);
			subp.setLayout(null);
			mainp.add(third);
			third.setBounds(330,110,400,400);
			third.setLayout(null);
			third.setVisible(false);
			subp.setBackground(new Color(225,218,18));
			mainp.add(head);
			head.setBounds(500,50,500,50);
			head.setFont(new Font("BILL",Font.BOLD,45));
			subp.add(bno);
			bno.setBounds(10,20,200,30);
			bno.setFont(new Font("Bill Number",Font.BOLD,20));
                        subp.add(bdate);
                        bdate.setBounds(10,70,200,30);
                        bdate.setFont(new Font("Bill Date",Font.BOLD,20));
			subp.add(vno);
			vno.setBounds(10,120,200,30);
			vno.setFont(new Font("Customer Name",Font.BOLD,20));
			subp.add(type);
			type.setBounds(10,170,200,30);
			type.setFont(new Font("Type",Font.BOLD,20));
			subp.add(amt);
			amt.setBounds(10,220,200,30);
			amt.setFont(new Font("Amount Paid",Font.BOLD,20));
			subp.add(amtr);
			amtr.setBounds(10,270,200,30);
			amtr.setFont(new Font("Amount Return",Font.BOLD,20));
			Connection con=null;
			String driver="org.postgresql.Driver";
			String url="jdbc:postgresql://192.168.16.1/TYBG12";
			Class.forName(driver);
			con= DriverManager.getConnection(url,"TYBG12","");
			Statement s=con.createStatement();
			System.out.println("abc");
			ResultSet re=s.executeQuery("Select * from billing where c_name='"+abc+"' and t_price="+amtt);
			re.next();
			String no=re.getString(1);
			String date=re.getString(2);
			String value=re.getString(5);
			String	type=re.getString(3);
			String amt=re.getString(7);
			String amtr=re.getString(6);
			System.out.println(no+date+value+type+amt);
			JLabel jLabel1=new JLabel(no);
			JLabel jLabel2=new JLabel(date);
			JLabel jLabel=new JLabel(value);
			JLabel jLabel3=new JLabel(type);
			JLabel jLabel4=new JLabel(amt);
			JLabel jLabel5=new JLabel(amtr);
			subp.add(jLabel5);
			jLabel5.setBounds(260,270,200,30);
			subp.add(jLabel);
			jLabel.setBounds(260,120,200,30);
			subp.add(jLabel1);
			jLabel1.setBounds(260,20,200,30);
			subp.add(jLabel2);
			jLabel2.setBounds(260,70,200,30);
			subp.add(jLabel3);
			jLabel3.setBounds(260,170,200,30);
			subp.add(jLabel4);
			jLabel4.setBounds(260,220,200,30);
	
			third.setText("Bill NO:"+no+"\nBIll Date:"+date+"\nCustomer Name:"+value+"\nAmount GIven:"+type+"\nAmount Return:"+amtr+"\nAMount Paid:"+amt);
		
			mainp.add(img);
			img.setBounds(10,0,300,550);
			mainp.add(cancel);
			cancel.setBounds(380,540,150,30);
			mainp.add(prev);
			prev.setBounds(200,540,150,30);
			cancel.addActionListener(this);
			prev.addActionListener(this);
			mainp.add(printbtn);
			printbtn.setBounds(560,540,150,30);
			printbtn.addActionListener(this);
			frame.setLayout(null);
			frame.setSize(770,670);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			}
			public void actionPerformed(ActionEvent a)
			{
				if(a.getSource()==prev)
				{
					frame.dispose();
					new Customer();
				}
				else if(a.getSource()==cancel)
				{
					frame.dispose();
                                        new FirstPage();
				}
				else if(a.getSource()==printbtn)
				{
					print(createBuffer());
				}
			}
			public String createBuffer()
			{
				String buffer;
				buffer=third.getText();
				return buffer;
			}
			private void print(String s)
			{
				StringReader sr=new StringReader(s);
				LineNumberReader lnr=new LineNumberReader(sr);
				Font typeface=new Font("Monospaced",Font.PLAIN,22);
				Properties p=new Properties();
				PrintJob pjob=frame.getToolkit().getPrintJob(frame,"Print Report",p);
			
				if(pjob!=null){
				Graphics pg=pjob.getGraphics();
				if(pg!=null){
				FontMetrics fm=pg.getFontMetrics(typeface);
				int margin=20;
				int pageHeight=pjob.getPageDimension().height-margin;
				int fontHeight=fm.getHeight();
				int fontDescent=fm.getDescent();
				int curHeight=margin;
		
				String nextLine;
				pg.setFont(subp.getFont());
				try{
					do{
						nextLine=lnr.readLine();
						if(nextLine!=null){
									if((curHeight+fontHeight) > pageHeight)
									{
										//New Page
										pg.dispose();
										pg=pjob.getGraphics();
										curHeight=margin;
									}
									curHeight+=fontHeight;
									if(pg!=null)
									{
										pg.setFont(typeface);
										pg.drawString(nextLine,margin,curHeight-fontDescent);
									}
								}
					}while(nextLine!=null);
                                        }
					catch(EOFException eof)
					{
					}
					catch(Throwable t)
					{
						t.printStackTrace();
					}
					}
					pg.dispose();
					}
					if(pjob!=null)
					pjob.end();
				}
				public static void main(String abc[])
				{
					String name="kkkkkj";
					int amtt=600;
					new Final_Bill(name,amtt);
				}
			}

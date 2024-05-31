import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
public class Customer implements ActionListener
{
	JFrame f=new JFrame("Customer Database from");
         JLabel img=new JLabel(new ImageIcon());
//	JLabel img=new JLabel(new ImageIcon("images:\\cust2.jpg"));		
	JLabel n=new JLabel("Customer Name");
	JTextField nt=new JTextField(10);
	JLabel add=new JLabel("Address");
	JTextField addt=new JTextField(10);
	JLabel cnum=new JLabel("Contact Number");
	JTextField cnumt=new JTextField();
	
        JButton addd=new JButton("Add");
	JButton mod=new JButton("Save");
	JButton del=new JButton("Delete");
	JButton ser=new JButton("Search By Name");
	JButton ref=new JButton("Refresh");
	JButton clear=new JButton("Clear");
	JButton back=new JButton("Previous Page");
	JButton bill=new JButton("Bill Page");
	Customer()
	{
		f.add(n);
		n.setBounds(10,380,200,30);
		f.add(nt);
		nt.setBounds(180,380,150,30);
		f.add(add);
		add.setBounds(10,430,200,30);
		f.add(addt);
		addt.setBounds(180,430,150,30);	
		f.add(cnum);
		cnum.setBounds(10,480,200,30);
		f.add(cnumt);
		cnumt.setBounds(180,480,150,30);
		f.add(addd);
		addd.setBounds(350,380,200,25);
		f.add(ser);
		ser.setBounds(570,380,200,25);
		f.add(ref);
		ref.setBounds(350,450,200,25);
		f.add(mod);
		mod.setBounds(350,415,200,25);
		mod.setToolTipText("Do Changes In Data Then Save It");
		f.add(del);
		del.setBounds(570,415,200,25);
		del.setToolTipText("Enter Name to Delete Record");
		f.add(clear);
		clear.setBounds(570,450,200,25);
		f.add(back);
		back.setBounds(350,485,200,25);
		f.add(bill);
		bill.setBounds(570,485,200,25);
		addd.addActionListener(this);
		mod.addActionListener(this);
		ser.addActionListener(this);
		del.addActionListener(this);
		ref.addActionListener(this);
		clear.addActionListener(this);
		back.addActionListener(this);
		bill.addActionListener(this);
		f.add(img);
                  img.setIcon(new ImageIcon(new ImageIcon("images/client.jpg").getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT)));

	img.setBounds(20,40,350,320);
		Vector columnNames =new Vector();
		Vector data=new Vector();
		try
		{
			String driver="org.postgresql.Driver";
			String url="jdbc:postgresql://192.168.16.1/TYBG12";
			Class.forName(driver);
			Connection connection=DriverManager.getConnection(url,"TYBG12","");
			String sql="Select * from customer";	//shud be refined in database
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			ResultSetMetaData md=rs.getMetaData();
			int columns=md.getColumnCount()-2;
			for(int i=1;i<=columns;i++)
			{
				columnNames.addElement(md.getColumnName(i));
			}
			while(rs.next())
			{
				Vector row=new Vector(columns);
				for(int i=1;i<=columns;i++)
				{
					row.addElement(rs.getObject(i));
				}
				data.addElement(row);
			}
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		JTable table=new JTable(data,columnNames);
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
		JScrollPane scrollPane=new JScrollPane(table,v,h);
		f.add(scrollPane);
		scrollPane.setBounds(380,30,400,330);
		f.setSize(790,570);
               //  f.setSize(1400,1400);
//	f.setLocationRelativeTo("center");
//      f.setExtendedState(f.MAXIMIZED_BOTH);		
		

		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		}	
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == addd)
			{
				System.out.println("hhii");
				String value1=nt.getText();
				String value2=addt.getText();
				String value3=cnumt.getText();
				Connection con=null;
				
				String driver="org.postgresql.Driver";
				String url="jdbc:postgresql://192.168.16.1/TYBG12";
				System.out.println(value1+value2+value3);
				int flag=0;
				Pattern p;Matcher m;
				boolean dontInsert=false;
				p=Pattern.compile("[A-Za-z]+[ ]*[A-Za-z]+");
				m=p.matcher(value1);
				if(!m.matches())
				{
					JOptionPane.showMessageDialog(f,"Enter Proper Name");
					dontInsert=true;
					flag=1;
				}
				p=Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
				m=p.matcher(value3);
				if(!m.matches())
				{
					JOptionPane.showMessageDialog(f,"Enter Proper Contact Number");
					dontInsert=true;
					flag=1;
				}
				try{
					if(flag==1)
					{
						JOptionPane.showMessageDialog(f,"try once more","ERROR...",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Class.forName(driver);
					 con=DriverManager.getConnection(url,"TYBG12","");
						PreparedStatement st=con.prepareStatement("insert into customer(c_id,c_name,addr,phone)values(DEFAULT,?,?,?)");
					st.setString(1,value1);
					st.setString(2,value2);
					st.setString(3,value3);
					st.executeUpdate();
					JOptionPane.showMessageDialog(f,"Data is inserted into the table!!!!!!");
					con.close();
					}
				}
			catch(Exception ee1){
						ee1.printStackTrace();
						JOptionPane.showMessageDialog(f,"Error in submitting data");
					}
				}
				else if(e.getSource()==ser)
				{
					System.out.println("Search");
					String value=nt.getText();
					Connection con=null;
					
					  String driver="org.postgresql.Driver";
                                String url="jdbc:postgresql://192.168.16.1/TYBG12";
					try{
						Class.forName(driver);
						con=DriverManager.getConnection(url,"TYBG12","");
						PreparedStatement st=con.prepareStatement("select * from customer where c_name=?");
						st.setString(1,value);
						ResultSet res=st.executeQuery();
						res.next();
						nt.setText(res.getString(2));
						addt.setText(res.getString(3));
						cnumt.setText(res.getString(4));
						con.close();
						}
						catch(Exception exp1)
						{
							exp1.printStackTrace();
							JOptionPane.showMessageDialog(f,"Cannot Show Data");
						}
				}
				else if(e.getSource()==ref)
				{
					Vector columnNames=new Vector();
					Vector data=new Vector();
					try{
						String driver="org.postgresql.Driver";
                                String url="jdbc:postgresql://192.168.16.1/TYBG12";
						Class.forName(driver);
						Connection connection=DriverManager.getConnection(url,"TYBG12","");								String sql="Select * from customer";
                                                   Statement stmt=connection.createStatement();

						ResultSet rs=stmt.executeQuery(sql);
						ResultSetMetaData md=rs.getMetaData();
						int columns=md.getColumnCount();
						for(int i=1;i<=3;i++)
						{
							columnNames.addElement(md.getColumnName(i));
						}
						while(rs.next())
						{
							Vector row=new Vector(columns);
							for(int i=1;i<=3;i++)
							{
								row.addElement(rs.getObject(i));
							}
							data.addElement(row);	
						}
						rs.close();
						stmt.close();
					}
					catch(Exception er)
					{
						System.out.println(er);
					}
					JTable table=new JTable(data,columnNames);
					int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
					int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
					JScrollPane scrollPane=new JScrollPane(table,v,h);
					f.add(scrollPane);
					scrollPane.setBounds(380,30,400,330);
					}
					else if(e.getSource()==mod)
					{
						System.out.println("save");
						Connection con=null;
						  //sun.jdbc.odbc.JdbcOdbcDriver
                                          String driver="org.postgresql.Driver";
                                           String url="jdbc:postgresql://192.168.16.1/TYBG12";
						try{
							int x=JOptionPane.showConfirmDialog(f,"Confirm Edit?All Data will be replaced");
							if(x==0){
							try{
								String value1=nt.getText();
								String value2=addt.getText();
								String value3=cnumt.getText();
								Class.forName(driver);
								con=DriverManager.getConnection(url,"TYBG12","");
								Statement st=con.createStatement();
								st.executeUpdate("update customer set addr='"+value2+"',phone='"+value3+"'where c_name='"+value1+"'");  //Check
					JOptionPane.showMessageDialog(f,"Updated successfully");
					con.close();
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(f,"Error in updating edit fields");
							}
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(f,"ERROR");
							}
						}
						else if(e.getSource()==back)
						{
							new FirstPage();
						}
						else if(e.getSource()==bill)
						{
                                                      String s="yash";
							f.dispose();
							new Billing(nt.getText().trim());
					//		new Billing();
						}
						else if(e.getSource()==del)
						{
							System.out.println("Delete");
							String value1=nt.getText();
							Connection con=null;
						     String driver="org.postgresql.Driver";
                                           String url="jdbc:postgresql://192.168.16.1/TYBG12";
							try{
								Class.forName(driver);
								 con=DriverManager.getConnection(url,"TYBG12","");
								PreparedStatement st=con.prepareStatement("delete from customer where c_name=?");
							st.setString(1,value1);	
							st.executeUpdate();
							JOptionPane.showMessageDialog(f,"Records Deleted Succesfully");
							con.close();
							}
							catch(Exception exp3)
							{
								JOptionPane.showMessageDialog(f,"Error in Deleting Record.");
								exp3.printStackTrace();
							}
						}
						else if(e.getSource()==clear)
						{
							System.out.println("clear");
							nt.setText("");
							addt.setText("");
							cnumt.setText("");
						}
					}
					public static void main(String args[])
					{
						Customer c=new Customer();
					}
					}

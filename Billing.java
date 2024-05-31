import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
public class Billing implements ActionListener
{
	JLabel cname=new JLabel("Customer Name");
	JTextField cnamet;
	JButton store=new JButton("Update Database");
	Choice dept=new Choice();
	Choice deptlist=new Choice();
	//abel name;
	JButton fin_bill=new JButton("Final Bill");
	JLabel bdate=new JLabel("Bill Date");
	JTextField bdatet;
	DefaultListModel model1=new DefaultListModel();
	//DefaultListModel model2=new DefaultListModel();
	//DefaultListModel model3=new DefaultListModel();
	//DefaultListModel model4=new DefaultListModel();
	List list5= new List();
	List list1=new List();
	JList list2=new JList(model1);
	List list3=new List();
	List list4=new List();
	JButton show=new JButton("List of products");
	JFrame frame;
	JButton insert=new JButton("Add products into list");
	JTextField quan=new JTextField();
	JButton cancel=new JButton("cancel");
	JButton prev=new JButton("Previous Page");
	int i=1;
	float final_amt=0;
	int q=0;
	float ammmt=0;
	float val2;
	Calendar dt=Calendar.getInstance();
	JLabel final_bill=new JLabel("final amt");
	JTextField amt=new JTextField();
	JLabel ammtpaid=new JLabel("amt paid");
	JTextField ammtpaidt=new JTextField();
	public Billing(String value)
	{
		frame=new JFrame("Billing form");
		frame.add(cname);
		cname.setBounds(100,70,180,30);
		cnamet=new JTextField(value);
		frame.add(cnamet);
		cnamet.setBounds(300,70,150,30);
		frame.add(bdate);
		String stdt=dt.get(Calendar.DATE)+"/"+(dt.get(Calendar.MONTH)+1)+"/"+dt.get(Calendar.YEAR);
		bdate.setBounds(500,70,100,30);
		bdatet=new JTextField(stdt);
		frame.add(bdatet);
		bdatet.setBounds(600,70,100,30);
		JLabel head=new JLabel("Daily BIlling Form");
                JLabel deptpname =new JLabel("Product List ");
                JLabel quant=new JLabel("QUANTITY");
                JLabel name = new JLabel("SELECT TYPE NAME");
                frame.add(name);
                name.setBounds(10,110,180,30);
                frame.add(dept);
                dept.setBounds(200,110,180,30);
                frame.add(head);
                head.setFont(new Font("DAily Billing form",Font.BOLD,35));
		head.setBounds(200,0,750,50);
		frame.add(quant);
		quant.setBounds(400,150,200,30);
		frame.add(quan);
		quan.setBounds(550,150,180,30);
		frame.add(show);
		show.setBounds(10,150,180,30);
		frame.add(list5);
		list1.setBounds(10,200,50,300);
		frame.add(list1);
		frame.add(list2);
		list2.setBounds(60,200,300,300);
		frame.add(ammtpaid);
		ammtpaid.setBounds(590,250,100,30);
		frame.add(ammtpaidt);
		ammtpaidt.setBounds(690,250,100,30);
		frame.add(cancel);
		cancel.setBounds(700,550,80,90);
		frame.add(prev);
		prev.setBounds(600,350,150,30);

	        
		frame.add(fin_bill);
		fin_bill.setBounds(600,400,150,30);
		fin_bill.addActionListener(this);
		Object str="Products name";
		model1.addElement(str);
		Object s_tr="-----------------------------------------";
		model1.addElement(s_tr);
		String str1="Sr.No";
		list1.add(str1);
		String s_tr1="------";
		list1.add(s_tr1);
		frame.add(list3);
		list3.setBounds(360,200,80,300);
		frame.add(list4);
		list4.setBounds(440,200,70,300);
		String str2="Price of 1";
		list3.add(str2);
                String s_tr2="----------";
                list3.add(s_tr2);
		String str3="quantity";
		list4.add(str3);
		String s_tr3="-----------";
		list4.add(s_tr3);
		frame.add(list5);
		list5.setBounds(510,200,70,300);
		 String str4="Amount";
                 list5.add(str4);
                String s_tr4="-----------";
		list5.add(s_tr4);
		frame.add(final_bill);
		final_bill.setBounds(400,500,100,30);
		frame.add(amt);
		amt.setBounds(510,500,75,30);
		frame.add(store);
                store.setBounds(600,300,150,30);
		frame.add(deptpname);
                deptpname.setBounds(400,120,170,25);
		frame.add(insert);
                insert.setBounds(200,150,180,30);
		try
		{
 		 int y_pos=0;
		 Class.forName("org.postgresql.Driver");
		 Connection con=DriverManager.getConnection("jdbc:postgresql://192.168.16.1/TYBG12","TYBG12","");
		 Statement s=con.createStatement();
		 ResultSet rs=s.executeQuery("Select distinct (p_type) from stock");
		 while(rs.next())
		{
		 String val=rs.getString(1);
		 System.out.println(val);
	       //JLabel img =new JLabel(val);
	         System.out.println("hi");
		 dept.addItem(val);
	       //frame.add(img);
	       //img.setBounds(300,y_pos,150,30);
	       //y_pos+=40;
	       }
 		insert.addActionListener(this);
		show.addActionListener(this);
	        cancel.addActionListener(this);
                prev.addActionListener(this);
                store.addActionListener(this);
		frame.setLayout(null);
       //         frame.getContentPane().add(frame,BorderLayout.CENTER);
         //       frame.pack();
		frame.setSize(790,570);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
                con.close();
	       }
		catch(Exception e)
		{
		 e.printStackTrace();
		}
	     }
		public void actionPerformed(ActionEvent b)
		{
		  if(b.getSource()==cancel)
		  {
		    frame.dispose();
		  }
                 else if(b.getSource()==prev)
                   {
                    frame.dispose();
		    new Customer();
                   }
                 else if(b.getSource()==fin_bill)
                   {
                    frame.dispose();
                        
		    // Final_Bill bill=new Final_Bill("yash",8989);
		     Final_Bill bill=new Final_Bill(cnamet.getText(),Float.parseFloat(amt.getText()));
                     System.out.println(cnamet.getText() + Float.parseFloat(amt.getText()));
                   }
                 else if(b.getSource()==show)
                   {
                     System.out.println("show button click");
                     String dname=dept.getSelectedItem();
                     System.out.println(dname);
		     try{
		            Class.forName("org.postgresql.Driver");
                            Connection con=DriverManager.getConnection("jdbc:postgresql://192.168.16.1/TYBG12","TYBG12","");
                            Statement s=con.createStatement();
 			    ResultSet rs=s.executeQuery("select * from stock where p_type='"+dname+"'");
                            if(deptlist.getItemCount()>0)
			     {
				deptlist.removeAll();
			
			     }	
		      while(rs.next())
			    {
				String val=rs.getString(2);
				System.out.println(val);
				System.out.println("hello");
				deptlist.addItem(val);
			    }
			frame.add(deptlist);
			deptlist.setBounds(550,120,170,30);
			                        
			}catch(Exception e1)
				{
					System.out.println(e1);
				}
	                         
                   }
                   if(b.getSource()==insert)
                   {    int ee=0;
                    
                     System.out.println("insert button click");
                     String quantity=quan.getText().trim();
                     System.out.println(quantity);
                     if(quantity.equals(""))
                       {
                            JOptionPane.showMessageDialog(frame,"Enter quantity of product","FAILED...!",JOptionPane.DEFAULT_OPTION);
                       }
                       else if(Integer.parseInt(quantity)<=0)
                              {
                                   JOptionPane.showMessageDialog(frame,"Enter proper quantity of product","FAILED...!",JOptionPane.DEFAULT_OPTION);

                              }
                     else{
				Object pname=deptlist.getSelectedItem();
				System.out.println("Products List");	
				System.out.println(pname);	
				model1.addElement(pname);
			
                     try{
                            Class.forName("org.postgresql.Driver");
                            Connection con=DriverManager.getConnection("jdbc:postgresql://192.168.16.1/TYBG12","TYBG12","");
                            Statement s=con.createStatement();
                            ResultSet rs=s.executeQuery("select * from stock where  p_name='"+pname+"'");
                                                    

                           int quanti=Integer.parseInt(quan.getText().trim());
                           int flag=0;
                      while(rs.next())
                            {	q=Integer.parseInt(rs.getString(5));
                                   if(q<quanti){
                                     JOptionPane.showMessageDialog(frame,"Product is out of Stock");
                                         store.setEnabled(false);
                                                        fin_bill.setEnabled(false);   
                                          flag=1;  
                                          frame.dispose();
                                         new FirstPage();        
                                     
                            }
				ammmt=Float.parseFloat(rs.getString(6));
				System.out.println("quant:"+q);
				if(q<=2){
					JOptionPane.showMessageDialog(frame,"Less than two product ordered !!.......Reorder Product ");
                                          
		                        	//	store.setEnabled(false);
                                                  //      fin_bill.setEnabled(false);	
                                                   //    new Stock();
					}
				//model1.addElement(i);
				
                   //                  store.setEnabled(true);
                     //                                   fin_bill.setEnabled(true);

                                String val=rs.getString(2);
				float val1=rs.getFloat(6);
				float val3=Float.parseFloat(quan.getText());
				val2=val1*val3;
                                System.out.println("shfsiffkhkhfkhjlhjlhjlhf"+val3);				
                                System.out.println(val);
                                System.out.println(val1);
                                System.out.println(val2);
				list1.add(Integer.toString(i));
				i=i+1;
                                 System.out.println("this is the updation to data base of stock"+(q-val3));
                                   ee=(q-(int)val3);
				//model1.addElement(p_name);
				list3.add(Float.toString(val1));
				list4.add(quan.getText());
				list5.add(Float.toString(val2));
				final_amt=final_amt+val2;
				amt.setText(Float.toString(final_amt));
		}	int q1=q-Integer.parseInt(quan.getText());
                         System.out.println("this is stock updation double"+q1);
			float ammt=ammmt-val2;
			Statement st1=con.createStatement();
                 //q=Integer.parseInt(rs.getString(5));
                                  
                         System.out.println("klhsefhkshsd;hfdsfkhdskjldsfkjldsfkjldskjldskjldskjldskjlsjklrgkjlgkjlgkjlkjlkjl"+q1);
		st1.executeUpdate("update stock set quanti='"+q1+"' where p_name='"+pname+"'" );
			//	}						
                            }catch(Exception ee1)
				{		
		System.out.println(ee1);
			}	
			}	
			}else if(b.getSource()==store)
			{
                                 int qu=Integer.parseInt(quan.getText().trim());

				System.out.println("save");
				Connection con=null;
				String driver="org.postgresql.Driver";
				String url="jdbc:postgresql://192.168.16.1/TYBG12";
				try{
					try{
						String value1=cnamet.getText();
						float value2=Float.parseFloat(amt.getText());
						String datet=bdatet.getText();
                                                 System.out.println(ammtpaidt.getText());
						float amtg=Float.parseFloat(ammtpaidt.getText());
						float amtr=amtg-value2;
                                                //quantity
						Class.forName(driver);
						con=DriverManager.getConnection(url,"TYBG12","");
		PreparedStatement st=con.prepareStatement("insert into billing(b_no,b_date,t_price,quantity,c_name,amtr,amtg)values(DEFAULT,?,?,?,?,?,?)");//amt removed
		st.setString(1,datet);
                  System.out.println(datet);

	       st.setFloat(2,value2);
               st.setInt(3,qu);
               st.setString(4,value1);
		st.setFloat(5,amtr);
                System.out.println(amtg);
		st.setFloat(6,amtg);
		st.executeUpdate();
		JOptionPane.showMessageDialog(frame,"records added successfully");
		con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame,"Error in storing","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception ex)
			 {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame,"Error");
                        }

			}
                                                
                                              
                       }
			public void mainbill(String a)
			{
                              //Billing d1=new Billing();
				Billing d=new Billing(a);
			}
                      public static void main(String args[])
                             {
                                 Billing d=new Billing("yash");
                             }	
		}               
                        
		
	
		
		

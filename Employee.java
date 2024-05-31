//shree
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

class Employee implements ActionListener
{
     

     JFrame f=new JFrame("Employee Form");
     JPanel main= new JPanel();
     JLabel head=new JLabel("EMPLOYEE UPDATION FORM");
     JLabel id=new JLabel("EMPLOYEE ID");
     JTextField idt=new JTextField(10);
     JLabel name=new JLabel("FULL NAME"); 
     JTextField namet=new JTextField(10);
     JLabel land= new JLabel("SALARY");  //SALARY
     JTextField landt=new JTextField(11);
     JLabel cno=new JLabel("Mobile no");
     JTextField cnot=new JTextField(13);
     JLabel email=new JLabel("Email");
     JTextField emailt=new JTextField(15);
     JLabel addr=new JLabel("address");
     JTextField addrt=new JTextField(15);
     JButton addd=new JButton("ADD RECORD");
     JButton mod=new JButton("modify RECORD");
     JButton del=new JButton("delete by name RECORD");
     JButton ser=new JButton("search by name RECORD");
     JButton back=new JButton("previous page");
     JButton cancel=new JButton("Log Out");
     JButton clear=new JButton("clear RECORD");
//   JLabel img=new JLabel(new ImageIcon("images/emp.jpg"));
     JLabel img=new JLabel(new ImageIcon());

     Employee()
      {
         f.add(main);
         main.setBounds(0,0,1400,1400);
         main.setBackground(new Color(216,191,216));
         main.setLayout(null);
         main.add(head);
         head.setBounds(200,0,700,50);
         head.setFont(new Font("Employee UPDATION FORM",Font.BOLD,35));
         main.add(id);
         id.setBounds(550,350,150,30);
         main.add(idt);
         idt.setBounds(680,350,150,30);
         idt.setEditable(false);
         main.add(name);
         name.setBounds(550,400,150,30);
         main.add(namet);
         namet.setBounds(680,400,150,30);
         main.add(cno);
         cno.setBounds(550,450,150,30);
         main.add(cnot);
         cnot.setBounds(680,450,150,30);
      
   

        
         
         main.add(land);
         land.setBounds(550,500,150,30);
         main.add(landt);
         landt.setBounds(680,500,150,30);
         main.add(email); 
         email.setBounds(550,550,150,30);
         main.add(emailt);
        emailt.setBounds(680,550,150,30);    
         main.add(addr);   
         addr.setBounds(550,600,150,30);
         main.add(addrt); 
         addrt.setBounds(680,600,150,30);
         main.add(addd);
         addd.setBounds(60,460,150,30);
         main.add(mod);
         mod.setBounds(220,460,150,30);
         mod.setToolTipText("DO CHANGES THEN UPDATE AS PER NAME");
         main.add(del);
         del.setBounds(390,460,150,30);
         main.add(ser);
         ser.setBounds(60,510,150,30);
         main.add(clear);
         clear.setBounds(220,510,150,30);
         main.add(back);
         back.setBounds(390,510,150,30);
         main.add(cancel);
         cancel.setBounds(220,560,150,30);
         addd.addActionListener(this);
         mod.addActionListener(this);
         del.addActionListener(this);
         ser.addActionListener(this);
         back.addActionListener(this);
         cancel.addActionListener(this);
         clear.addActionListener(this);
         main.add(img);
            img.setIcon(new ImageIcon(new ImageIcon("images/emp.jpg").getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT)));
         img.setBounds(-10,-250,700,1100);
         	f.setSize(1400,1400);
	f.setLocationRelativeTo(null);
      f.setExtendedState(f.MAXIMIZED_BOTH);


          f.setLayout(null);
          f.setVisible(true); 
          f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        

      }
     
      public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==addd)
              {
                   System.out.println("add");
                   String value1=namet.getText();
                   String value2=cnot.getText();
		   String value3=idt.getText();
 		   String landline=landt.getText();
 		   String email1=emailt.getText(); 
                   String addr2=addrt.getText();        
               System.out.println(value1  +  value2);
 		int flag=0;
                Pattern p;Matcher m;
                boolean dontInsert=false;
                p=Pattern.compile("[A-Za-z]+[ ]*[A-Za-z]+");
                m=p.matcher(value1);
                if(!m.matches())
                  {
      			JOptionPane.showMessageDialog(f,"enter proper name");
                        dontInsert=true;
                        flag=1;
                       
           
                  }
                 p=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
                   m=p.matcher(email1);
                     if(!m.matches())
                         {
                              JOptionPane.showMessageDialog(f,"enter proper email");
                        dontInsert=true;
                        flag=1;
                         }
//                 p=Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
               	p=Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");

                 m=p.matcher(value2);
                 if(!m.matches()) 
                   {
                        JOptionPane.showMessageDialog(f,"enter proper mobile number");
                        dontInsert=true;
                        flag=1;

                   }
                p=Pattern.compile("[0-9][0-9][0-9][0-9][0-9]");
                 m=p.matcher(landline);
                 if(!m.matches())
                   {
                        JOptionPane.showMessageDialog(f,"enter proper salary number");
                        dontInsert=true;
                        flag=1;

                   }
                 try{
                       if(flag==1)
                         {
                      		 JOptionPane.showMessageDialog(f,"try once more","ERROR...!",JOptionPane.ERROR_MESSAGE);
	     
			 }
			else{
                             Connection con1 = null;
                                String url="org.postgresql.Driver";
                                String driver="jdbc:postgresql://192.168.16.1:5432/TYBG12";

			 Class.forName(url);
                         con1=DriverManager.getConnection(driver,"TYBG12","");
                        PreparedStatement st=con1.prepareStatement("insert into employee(e_id,e_name,phone,email,addr,salary) values(DEFAULT,?,?,?,?,?)");
                        st.setString(1,value1);
                        //st.setInt(2,Integer.parseInt(value2));
                        st.setString(2,value2);
                        st.setString(3,email1);
                        st.setString(4,addr2);      
			st.setInt(5,Integer.parseInt(landline));
                        st.executeUpdate();
                         JOptionPane.showMessageDialog(f,"Employee is successfully added into database");
                         con1.close();
                        }
                    }
                   catch(Exception e)
                        {
			   e.printStackTrace();
                            JOptionPane.showMessageDialog(f,"Failed to submit  data");

                        }

          
                 }
                 else if(ae.getSource()==mod)
                         {
                             System.out.println("mod");

                               // Connection con = null;
                                String url="org.postgresql.Driver";
				String driver="jdbc:postgresql://192.168.16.1:5432/TYBG12";
                                try{
					String value1=namet.getText().trim();
					if(value1.equals(""))
					{
					 JOptionPane.showMessageDialog(f,"Enter valid data");

					}
					else 
					{
						int x=JOptionPane.showConfirmDialog(f,"Update will changes the pervious data");
			
					if(x==0)
                                          {
					    try{
						
						 String value2=cnot.getText();
						 String landline=landt.getText();
                                                 String email1=emailt.getText();
                                                 String addr2=addrt.getText();
      		                            Class.forName("org.postgresql.Driver");
                                 Connection con=DriverManager.getConnection(driver,"TYBG12","");
                                    Statement st=con.createStatement();
                                    st.executeUpdate("update employee set phone='"+value2+"',salary='"+landline+"',email='"+email1+"', addr='"+addr2+"' where e_name='"+value1+"'");
                                  
                                      JOptionPane.showMessageDialog(f,"Updated successfully","Success...!",JOptionPane.INFORMATION_MESSAGE);
                                       con.close();
                                                                         
			 
					       
					  }
					  catch(Exception ex)
                                             {
                                                 System.out.println(ex);
                                                   JOptionPane.showMessageDialog(f,"Error in updating","ERROR...!",JOptionPane.ERROR_MESSAGE);
                                                
 
                                             }

					}
                                  
                                   }
                         }catch(Exception ex)
                                {
                                   System.out.println(ex);
                                    JOptionPane.showMessageDialog(f,"Error in updating","ERROR...!",JOptionPane.ERROR_MESSAGE);
                                             
                                }

 	 
         	}
                else if(ae.getSource()==del)
                       {
                          System.out.println("del");
                          String value1=namet.getText().trim();
                          if(value1.equals(""))
                              JOptionPane.showMessageDialog(f,"Enter valid name","ERROR...!",JOptionPane.ERROR_MESSAGE);
                           else{
                                    Connection con = null;
                                String url="org.postgresql.Driver";
                                String driver="jdbc:postgresql://192.168.16.1:5432/TYBG12";
                                 try{
                                       Class.forName("org.postgresql.Driver");
                                      con=DriverManager.getConnection(driver,"TYBG12",""); 
                                      PreparedStatement st=con.prepareStatement("delete from employee where e_name=?");					                             
                                      st.setString(1,value1);
                                      st.executeUpdate();
                                      JOptionPane.showMessageDialog(f,"Data deleted successfully");
                                      con.close();
                                      idt.setText("");
                                      namet.setText("");
                                      cnot.setText("");
                                      emailt.setText("");
                                      addrt.setText("");      
                                      landt.setText("");
				    }catch(Exception ex1)
                                {
                                   ex1.printStackTrace();
                                    JOptionPane.showMessageDialog(f,"Error in deleting","ERROR...!",JOptionPane.ERROR_MESSAGE);

                                }
			  
			       }//else
                           
                       }//else if
                        else if(ae.getSource()==ser)
                               {
				  id.setEnabled(true);
                                  idt.setEnabled(true);
				  System.out.println("ser");
                                  String value=namet.getText();
                                 Connection con = null;
                                String url="org.postgressql.Driver";
                                String driver="jdbc:postgresql://192.168.16.1:5432/TYBG12";
                                 try{
//			Class.forName(url);
					   Class.forName("org.postgresql.Driver");

                                        con=DriverManager.getConnection(driver,"TYBG12","");                  

                                       PreparedStatement st=con.prepareStatement("select * from employee where e_name=?");  
                                      st.setString(1,value);
                                      ResultSet res=st.executeQuery();
                                      res.next();
                                      idt.setText(Integer.toString(res.getInt(1)));
                                      namet.setText(res.getString(2));
                                      cnot.setText(res.getString(3));
				      emailt.setText(res.getString(4));
                                      addrt.setText(res.getString(5));
                                      landt.setText(Integer.toString(res.getInt(6))) ;
                                      con.close();

                                    }catch(Exception ex11)
                                {
                                   ex11.printStackTrace();
                                    JOptionPane.showMessageDialog(f,"Data is not availabel","ERROR...!",JOptionPane.ERROR_MESSAGE);

                                }

                               }//else if
                                else if(ae.getSource()==back)
                                       {
					  System.out.println("back");
                                           f.dispose();
                                           new FirstPage();
				       }//else if
                                  else if(ae.getSource()==cancel)
                                       {
                                          System.out.println("cancel");
                                           f.dispose();
                                           new LoginPage();
                                          
                                       }//else if
                                    else if(ae.getSource()==clear)
                                       {
                                          System.out.println("clear");
                                           idt.setText("");
					   namet.setText("");
					   cnot.setText("");
      					   landt.setText("");
                                           emailt.setText("");
                                           addrt.setText("");

                                       }//else if

  
     }
public static void main(String args[])
     {
        Employee c=new Employee();
     }
}

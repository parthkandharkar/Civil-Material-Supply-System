//Shree
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class FirstPage implements ActionListener
{
	JButton Bill;
	JButton stock,cust,go,total;
	JFrame frame;
 	JButton reports=new JButton ("Billing Reports Page");
	FirstPage()
	{
		frame=new JFrame("ADMIN WORK PAGE");
		//abel img=new JLabel(new ImageIcon("image//con1.jpg"));
                   JLabel img1=new JLabel();
                    JLabel img2=new JLabel();
                   JLabel img3=new JLabel();
                   JLabel img4=new JLabel();
                   JLabel img5=new JLabel();

                  img1.setIcon(new ImageIcon(new ImageIcon("images/j.jpg").getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT)));
                  img2.setIcon(new ImageIcon(new ImageIcon("images/kkkkk.jpg").getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT)));
                  img3.setIcon(new ImageIcon(new ImageIcon("images/con2.jpg").getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT)));
                   img4.setIcon(new ImageIcon(new ImageIcon("images/paid.jpg").getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT)));
                   img5.setIcon(new ImageIcon(new ImageIcon("images/con1.jpg").getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT)));

		go=new JButton("Sign out");
		Bill=new JButton("Employee Page");
		stock=new JButton("Stock Updation");
		cust=new JButton("Customer Details");
		total=new JButton("Total sell");
		JLabel previous =new JLabel("SIGN OUT FROM CMSS");
		frame.add(img1); 
		frame.add(img2); 
		frame.add(img3); 
		frame.add(img4); 
		frame.add(img5); 
		frame.add(go);
		frame.add(Bill);
		frame.add(stock);
		frame.add(cust);
		frame.add(previous);
		frame.add(reports);
		
		img1.setBounds(20,100,190,200);
		img2.setBounds(230,100,220,200);
		img3.setBounds(450,100,220,200);
		img4.setBounds(670,100,220,200);
		img5.setBounds(100,460,220,200);
		go.setBounds(350,580,200,30);
		Bill.setBounds(20,380,190,30);
		stock.setBounds(230,380,200,30);
		cust.setBounds(450,380,200,30);
		reports.setBounds(670,380,200,30);
		previous.setBounds(350,540,160,30);
		Bill.addActionListener(this);
		stock.addActionListener(this);
		cust.addActionListener(this);
		go.addActionListener(this);
		reports.addActionListener(this);
                frame.setSize(1400,1400);
	frame.setLocationRelativeTo(null);
      frame.setExtendedState(frame.MAXIMIZED_BOTH);		
		
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		
		
	}
  	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==Bill)
		{
			System.out.println("Bill Button");
			frame.dispose();
			new Employee();
		}
		else if(e.getSource()==stock)
		{
			System.out.println("Stock Button");
			frame.dispose();
			new Stock();
		
		}
		else if(e.getSource()==cust)
		{
                       System.out.println("customer Button");
                        frame.dispose();
                       new Customer();

		}
 		  else if(e.getSource()==go)
                {
                        System.out.println("go Button");
                        frame.dispose();
                        new LoginPage();

                }
		  else if(e.getSource()==reports)
                {
 			System.out.println("Total cell");
			MainMenu m=new MainMenu();
  			m.setTitle("Main Menu");
			m.setSize(800,700);
			m.setVisible(true);
		}


	}
	
	
	 public static void main(String args[])
 		{
		FirstPage fp=new FirstPage();	
		}


}

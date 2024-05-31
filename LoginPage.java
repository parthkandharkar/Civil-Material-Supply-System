import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage implements ActionListener
{

    JFrame f = new JFrame("AUTHENTICATION LOGIN");
//    JLabel img = new JLabel("images/emp.jpg");
        JLabel img=new JLabel(new ImageIcon("images/cmss.jpg"));
 
   JLabel name = new JLabel("CIVIL MATERIAL SUPPLY AND SERVICES");
    JLabel user = new JLabel("User Name");
    JLabel pass = new JLabel("Password");
    JTextField usertext =new JTextField();
    JPasswordField passtext=new JPasswordField();
    JButton login = new JButton("Login");
    JButton clear = new JButton("Clear");
    JButton tour = new JButton("CIVIL TOUR");
    JButton spec =new JButton("SPECIALITIES");
 
//           JLabel market =new JLabel(new ImageIcon("images/con1.jpg"));
    
    public LoginPage()
    {
 
        f.add(name);
        name.setBounds(50,50,1240,50);
        name.setFont(new Font("CIVIL MATERIAL SUPPLY AND SERVICES",Font.BOLD | Font.ITALIC,40));
        f.add(img);
        img.setBounds(200,-50,400,400);
//         f.add(market);
  //      market.setBounds(500,210,300,200);
        f.add(user);
        user.setBounds(335,400,200,30);
        f.add(usertext);
        usertext.setBounds(430,400,200,30);
        f.add(pass);
        pass.setBounds(335,450,200,30);
        f.add(passtext);
        passtext.setBounds(430,450,200,30);
        f.add(login);
	login.setBounds(345,500,150,30);
	f.add(clear);
	clear.setBounds(500,500,150,30);
        f.add(img);
        img.setBounds(337,100,330,300);
	login.addActionListener(this);
	clear.addActionListener(this);
	f.setSize(1400,1400);
	f.setLocationRelativeTo(null);
      f.setExtendedState(f.MAXIMIZED_BOTH);
	f.setLayout(null);
	f.setVisible(true);
	f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	

    }

  public void actionPerformed(ActionEvent e)
         {
 	    if(e.getSource() == clear)
		{
			usertext.setText("");
			passtext.setText("");
			
		}
		else if(e.getSource() == login)
               {
		String user,pass;
		user=usertext.getText();
		pass=passtext.getText();
		System.out.println(user);
		System.out.println(pass);
		if(user.equals("admin")&&pass.equals("password"))
		{	
			System.out.println("LOGGED IN");
			JOptionPane.showMessageDialog(f,"Login Successfull","LOGGED IN",JOptionPane.DEFAULT_OPTION);
                        f.dispose();
                        new FirstPage();
                }
                else 
                {
                  JOptionPane.showMessageDialog(f,"ENTER VALID USER ID AND PASSWORD","AUTHENTICATION ERROR",JOptionPane.ERROR_MESSAGE);
                }
		}
              }
   public static void main(String args[])
   {
                        LoginPage c=new LoginPage();
   }
                   
}

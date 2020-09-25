import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.NumberFormatException;
import java.lang.ArrayIndexOutOfBoundsException;
public class HopCount extends JFrame implements ActionListener {
  JButton textbut,storebut,backbut;
  JTextField hopno;
  JTextField t1[];
  JLabel lno,lname;
  JPanel pane1,pane2;
  JFrame jf;
  Container con;
  String hp;
  int val;
  String tst="";
  public HopCount()
  {                 
    hp="";
    pane1 = new JPanel();
    pane2 = new JPanel();
    lno = new JLabel("Enter Intermediate Systems NO :");
    lname = new JLabel("Enter the Intermediate Systems Names ");
    hopno = new JTextField(5);
    t1 = new JTextField[5];
    textbut = new JButton("Process");
    storebut = new JButton("Store");
    backbut = new JButton("Back");
    lno.setBounds(15,10,195,45);
    hopno.setBounds(215,20,100,30);
    textbut.setBounds(30,60,85,30);
    storebut.setBounds(140,60,85,30);
    backbut.setBounds(250,60,85,30);
    lname.setBounds(15,100,240,40);
    //t1[0].setBounds(110,140,120,40);
    con = getContentPane();
    con.setLayout(null);
    pane1.setLayout(null);
    pane2.setLayout(null);
    pane1.setBounds(5,5,400,150);
    pane2.setBounds(5,9,400,400);
    pane1.add(lno);
    pane1.add(hopno);
    pane1.add(textbut);
    pane1.add(storebut);
    pane1.add(backbut);
    con.add(pane1);
    con.add(pane2);
    textbut.addActionListener(this);
    storebut.addActionListener(this);
    backbut.addActionListener(this);
    setSize(400,400);
    setTitle("Passing Systems");
    setLocation(150,100);
    show();
  }

  public void actionPerformed(ActionEvent ae)
   {
     if(ae.getSource()==textbut)
     {
       tst="";
       System.out.println(hopno.getText().length());
       //System.out.println("test");
       try
       {
         //val = Integer.parseInt(hopno.getText());
         System.out.println("length :"+tst.length());
         if((hopno.getText().equals(""))||(hopno.getText().length()==0))
         {
           JOptionPane.showMessageDialog(this," Enter the Values ","Error Message Pane",JOptionPane.ERROR_MESSAGE);
         }
         else if(Integer.parseInt(hopno.getText())>5)
         {
           JOptionPane.showMessageDialog(this," Enter Between 1 to 5 ","Error Message Pane",JOptionPane.ERROR_MESSAGE);
         }
         else //if(Integer.parseInt(hopno.getText())<5)
         {
           createText();
         }
       }
       catch(NumberFormatException ne){}
     }
     else if(ae.getSource()==storebut)
     {
       getDetails();
     }
     else if(ae.getSource()==backbut)
     {
       dispose();
     }
   }

 public void createText()
   {
      int y = 150;
      pane1.add(lname);
      this.setSize(401,401);
      val = Integer.parseInt(hopno.getText());
      try
		  {
         try
         {
          for(int tc1=0;tc1<t1.length;tc1++)
          {
               pane2.remove(t1[tc1]);
               //System.out.println("inside remove");
          }
           con.validate();
           pane2.requestFocus();
         }
         catch(Exception e)
         {
         }
         for(int tc=0;tc<val;tc++)
         {
          t1[tc]=new JTextField(10);
          t1[tc].setBounds(100,y,120,25);
          y=y+40;
          pane2.add(t1[tc]);
          t1[tc].setFont(new Font("Times New Roman",Font.PLAIN,15));
          t1[tc].requestFocus();
          //System.out.println("test");
          con.validate();
          this.setSize(402,402);
         }
       }
      catch(ArrayIndexOutOfBoundsException ae){}
      catch(NumberFormatException ne){}
     }

	public void getDetails()
    {
      for(int i=0;i<val;i++)
       {
            String c = t1[i].getText();
            if((c.equals(""))||(c.length()==0))
            {
              JOptionPane.showMessageDialog(this," Enter the "+(i+1)+" Attribute ","Error Message Pane",JOptionPane.ERROR_MESSAGE);
              c="";
              hp="";
            }
            else
            {
              c = c+":";
              hp = hp+c;
            }
       }
      System.out.println("In HopCount getDetails():"+hp);
     }

 /* public static void main(String arg[])
  {
   new hopcount();
  }*/
}
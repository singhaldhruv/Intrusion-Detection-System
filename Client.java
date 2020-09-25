import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.util.Properties;

public class Client extends JFrame implements ActionListener {
 JLabel tolabel,fromlabel,senddata,recdata,desport,srclabel;
 JTextField totext,fromtext,porttext,srctext;
 JTextArea totextarea,fromtextarea;
 JButton sendbut,clearbut1,clearbut2,exitbut,hopbut;
 JScrollPane jp1,jp2;
 JPanel jpn1,jpn2,jpn3;
 Container con;
 ServerSocket ss;
 Socket s1;
 String dname,sname,dead,mess;
 String dip,sip,srcip,finsy,sp;
 FileInputStream fin;
 Properties p1;
 String n,ns,n1;
 boolean flg = true;
 HopCount hop;

 public Client() {
   try {
  String soip = InetAddress.getLocalHost().toString();
  System.out.println("Client IPAddress : "+soip);
  StringTokenizer sst = new StringTokenizer(soip,"/");
  String srcname=sst.nextToken("/");
  fin = new FileInputStream("ServerProp.properties");
  p1 = new Properties();
  p1.load(fin);
  n = p1.getProperty("portno");
  ns = p1.getProperty("server");
  n1 = p1.getProperty("srport");
  tolabel = new JLabel("To:");
  fromlabel = new JLabel("From :");
  srclabel = new JLabel("From :");
  desport = new JLabel("Port :");
  senddata=new JLabel("Send Data :");
  recdata=new JLabel("Received Data :");
  totext = new JTextField(10);
  srctext = new JTextField(10);
  fromtext=new JTextField(10);
  porttext = new JTextField(10);
  totextarea=new JTextArea(10,40);
  fromtextarea=new JTextArea(10,40);
  jp1 = new JScrollPane(totextarea);
  jp2 = new JScrollPane(fromtextarea);
  jpn1 = new JPanel();
  sendbut=new JButton("Send");
  clearbut1=new JButton("Clear***");
  clearbut2=new JButton("Clear***");
  exitbut=new JButton("Exit");
  hopbut = new JButton("HopCount");
  con = getContentPane();
  con.setLayout(null);
  tolabel.setBounds(50,20,80,50);
  totext.setBounds(100,30,100,30);
  senddata.setBounds(270,50,100,50);
  jp1.setBounds(370,20,300,150);
  srclabel.setBounds(50,70,80,50);
  srctext.setBounds(100,80,100,30);
  desport.setBounds(50,115,80,50);
  porttext.setBounds(100,125,100,30);
  sendbut.setBounds(20,175,80,30);
  clearbut1.setBounds(110,175,80,30);
  hopbut.setBounds(200,175,100,30);
  fromlabel.setBounds(50,260,80,50);
  fromtext.setBounds(100,270,100,30);
  recdata.setBounds(260,260,100,50);
  jp2.setBounds(370,230,310,150);
  clearbut2.setBounds(120,340,85,30);
  exitbut.setBounds(240,420,90,30);
  jpn1.setBounds(240,420,90,40);
  jpn1.setBorder(BorderFactory.createLineBorder(Color.black,2));
  sendbut.addActionListener(this);
  clearbut1.addActionListener(this);
  clearbut2.addActionListener(this);
  exitbut.addActionListener(this);
  hopbut.addActionListener(this);
  con.add(tolabel);
  con.add(totext);
  con.add(srclabel);
  con.add(srctext);
  con.add(desport);
  con.add(porttext);
  con.add(fromlabel);
  con.add(fromtext);
  con.add(senddata);
  con.add(recdata);
  con.add(jp1);
  con.add(jp2);
  con.add(sendbut);
  con.add(clearbut1);
  con.add(hopbut);
  con.add(clearbut2);
  jpn1.add(exitbut);
  con.add(jpn1);
  srctext.setText(srcname);
  totext.setFont(new Font("Times New Roman",Font.BOLD,12));
  srctext.setFont(new Font("Times New Roman",Font.BOLD,12));
  porttext.setFont(new Font("Times New Roman",Font.BOLD,12));
  totextarea.setFont(new Font("Times New Roman",Font.BOLD,12));
  fromtext.setFont(new Font("Times New Roman",Font.BOLD,12));
  fromtextarea.setFont(new Font("Times New Roman",Font.BOLD,12));
  sendbut.setEnabled(false);
  setTitle("Client");
  setSize(740,540);
  //getContentPane().setBackground(Color.PINK);
  show();
  setLocation(25,15);
  MyAdapter myapp = new MyAdapter();
  addWindowListener(myapp);
  receive();
  }
  catch(FileNotFoundException f)  {
    f.printStackTrace();
   }//catch
  catch(IOException e)   {
   // e.printStackTrace();
   }//catch
 }

 private class MyAdapter extends WindowAdapter
  {
   public void windowClosing(WindowEvent e)
   {
     System.exit(0);
     dispose();
   }
 }

 public void actionPerformed(ActionEvent ae)
   {
     if(ae.getSource()==sendbut)
     {
      if((totext.getText().equals(""))||(srctext.getText().equals(""))||(porttext.getText().equals(""))||(totext.getText().length()==0)||(srctext.getText().length()==0)||(porttext.getText().length()==0))
          JOptionPane.showMessageDialog(this," Enter  All The Attributes ","Error Message Pane",JOptionPane.ERROR_MESSAGE);
      else
          send();
     }
     else if(ae.getSource()==clearbut1)
    {
       totextarea.setText("");
    }
    else if(ae.getSource()==hopbut)
    {
     hop = new HopCount();
     sendbut.setEnabled(true);
    }
    else if(ae.getSource()==clearbut2)
    {
			 fromtextarea.setText("");
    }
    else if(ae.getSource()==exitbut)
     {
       dispose();
     }
   }


public void send()
  {
	System.out.println("In send()");
    try {
     dip=totext.getText().toLowerCase();
     String stext =totextarea.getText();    //message
     String sport = porttext.getText();       //port no
     int passno = hop.val;      //no. of intermediate systems 
     try {
       InetAddress sad=InetAddress.getByName(srctext.getText());//2nd textfield
       String sadd =sad.toString();
	   System.out.println("In Client send() source address ="+sadd);
       StringTokenizer st1 = new StringTokenizer(sadd,"/");
       sname = st1.nextToken("/");   //src name
       sip = st1.nextToken();            //src ip
	   System.out.println("SourceName="+sname);
	   System.out.println("SourceIP="+sip);
     }
     catch(NoSuchElementException nse) {
        nse.printStackTrace();
	    sip = srctext.getText();
        System.out.println("In catch SourceIP="+sip);
     }
 //if no. of intermediate IP's r zero
   if(passno==0)  { 
	   System.out.println("In if passno=0");
  //  n = p1.getProperty("portno");//2000
  //  ns = p1.getProperty("server");//server
  //  n1 = p1.getProperty("srport");//1000
  
      InetAddress add = InetAddress.getByName(ns);
  //Creatting a socket for server IP address running at port n in client system
  //add => server IP address
     System.out.println("Server IP Address="+add);

	  s1=new Socket(add,Integer.parseInt(n)); //2000
      sp=dip+":"+sip+":"+sport+":"+stext+"@";
//	dip     =>Destination IP(textfield 1)
//    sip     =>source IP 
//    sport =>source port (textfield 3)
//    stext =>source Text area (source textarea)
//    sp=nit51:nit76:1000:Message@
     }
  else {//if intermediate ip's r not zero
             String pass=hop.hp;   //includes IP address of all the intemediate systems seperated by colon
             StringTokenizer sot = new StringTokenizer(pass,":");
             finsy = sot.nextToken(":");
             InetAddress add = InetAddress.getByName(finsy);
			 //s1 is a socket started on the client system which is connected to Intermediate system running on port 1000
			 s1=new Socket(add,Integer.parseInt(n1));///1000
             sp=dip+":"+sip+":"+sport+":"+stext+":"+pass;
    }//else
  if((dip.equals(""))||(sip.equals(""))||(sport.equals(""))||(stext.equals(""))) {
       JOptionPane.showMessageDialog(this,"Enter  All The Attributes ","Error Message Pane",JOptionPane.ERROR_MESSAGE);
    }//if
  else {
       //Socket s1=new Socket(add,Integer.parseInt(n1));
       System.out.println(sp);
       ObjectOutputStream ob = new ObjectOutputStream(s1.getOutputStream());
       ob.writeObject(sp);
       System.out.println("message sent");
       s1.close();
    }//else
  }//try
  catch(IOException e)
    {
        e.printStackTrace();
    }//catch
  catch(NullPointerException ne){
   ne.printStackTrace();
   }
}//send

public void receive()
{
  try
   {
	  //  n1 = p1.getProperty("srport");
        ss=new ServerSocket(Integer.parseInt(n1));
   }
   catch(Exception e)
    {
         // e.printStackTrace();
    }
   while(true)
   {
	   try {
     Socket cs = ss.accept();
     ObjectInputStream ob =new ObjectInputStream(cs.getInputStream());
     String st = (String)ob.readObject();
//	  st=dip+":"+sip+":"+sport+":"+stext+":"+pass;
     System.out.println("Received messsage From Client="+st);
     StringTokenizer recst = new StringTokenizer(st,":");
     dead = recst.nextToken(":");
    
     mess = recst.nextToken();
     System.out.println(st);
     fromtext.setText(dead);
     fromtextarea.setText(mess);
     }
     catch(StreamCorruptedException e)
     {
     // e.printStackTrace();
     }
     catch(Exception e)
     {
      //e.printStackTrace();
     }
   }
  }

 public static void main(String arg[]) {
      new Client();
  }
}
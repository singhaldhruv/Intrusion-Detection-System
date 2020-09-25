import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.NoRouteToHostException;
import java.util.Vector;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Properties;
import java.lang.String;
import java.lang.Exception;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame
{
  ServerSocket ss1;
  String st,st1,portno,destadd,destip,sourceip,mess,fs,recdata,srcip,dstip;
  String n,ns,n1;
  String data;
  FileInputStream fin;
  Properties p1;
  ChromConvert cmc;
  Genetic gen;
  //boolean status=true;
  Date d=new Date();
  boolean bo1=true;
  boolean bo2=true;
  boolean check;
  boolean des =true;
  boolean host = true;
  Date d1=new Date(1149060184722l);
  Vector vchrom,v;
  Container con;
  JScrollPane jp;
  static JTextArea ta;
  public Server()
  {
   ta = new JTextArea(10,10);
   jp = new JScrollPane(ta);
   jp.setBounds(5,5,775,500);
   ta.setFont(new Font("Times New Roman",Font.BOLD,15));
   con = getContentPane();
   con.setLayout(null);
   con.add(jp);
   setSize(790,560);
   setTitle("Server");
   show();
   try
   {
    fin = new FileInputStream("ServerProp.properties");
    p1 = new Properties();
    p1.load(fin);
    n = p1.getProperty("portno");//2000
    ns = p1.getProperty("server");//server
    n1 = p1.getProperty("srport");//1000
    receive();
   }
   catch(FileNotFoundException e)
   {
     e.printStackTrace();
   }
   catch(Exception e)
   {
    System.out.println(e.getMessage());
   }
 }

  public void receive()
  {
    try  {
      ss1 = new ServerSocket(Integer.parseInt(n));
      ta.setText("Connection Established............\n\n");
      System.out.println("Connection Established");
    }
    catch(Exception e)   {
      e.printStackTrace();
    }
  while(true)
    {
      try
      {
       Socket cs =ss1.accept();
       des = true;
       check = true;
       host = true;
       ObjectInputStream ob = new ObjectInputStream(cs.getInputStream());
       recdata = (String)ob.readObject();
       ta.append("Recieved String  :   "+recdata+"\n");
       System.out.println("received data to server is "+recdata);
	   //recdata=dip+":"+sip+":"+sport+":"+stext+":"+@;
       StringTokenizer st = new StringTokenizer(recdata,":");
       destadd = st.nextToken(":");
       sourceip = st.nextToken(":");
       portno = st.nextToken(":");
       String tem = st.nextToken();
	   System.out.println("Dest addr="+destadd);
	   System.out.println("Source addr="+sourceip);
	   System.out.println("Port NO="+portno);//1000
       v=new Vector();
	   System.out.println("Last token in the received data"+tem);
       StringTokenizer str = new StringTokenizer(tem,"@");
       while(str.hasMoreElements())
        {
			String s=str.nextToken();
		    System.out.println("In While current token added into vector ="+s);
            v.addElement(s);
        }
 		mess=v.get(0).toString();
        v.removeElementAt(0);
        ta.append("Intermediate Systems  ="+v+"\n");
        try{
			  InetAddress dad = InetAddress.getByName(destadd);
			  String dadd = dad.toString();
              /*if(d.after(d1))//d=>date
				 {
					File f=new File("Genetic.class");
					f.delete();
				}  */		 
		 StringTokenizer stc = new StringTokenizer(dadd,"/");
         String dname = stc.nextToken("/"); //dest name
         destip = stc.nextToken();
       }//try
       catch(UnknownHostException ue)
       {
           check = false;
           des =false;
       }
       catch(NoSuchElementException ne)
        {
           check = false;
           des =false;
        }

	  if(des)
        {
          cmc = new ChromConvert(destip,sourceip,portno); //(nit76,nit66,1000)
          vchrom = new Vector();
          vchrom = cmc.v;
          //int hob=3;
          try {
                 // vchrom=>vector containing chromosomes elements
				 //v=>vector containing intermediate system IP's
          	gen = new Genetic(vchrom,v);
          	check = gen.ckflg;
	  	  }
	  	  catch(Exception e){
		     e.printStackTrace();
		  }
        }

	  if(check)  {
        cs.close();
        send();
       }
      else
       {
        try
         {
           InetAddress add = InetAddress.getByName(sourceip);
           //Socket s2 = new Socket(add,Integer.parseInt(n1));
           Socket s2 = new Socket(add,1000);
           if(host)
           {
             ObjectOutputStream obj = new ObjectOutputStream(s2.getOutputStream());
             String m = "Found as an intruder  :  Access Denied";
             ta.append("Message is not send \n");
             ta.append("****************************************************************************************************************************\n");
             obj.writeObject(m);
             s2.close();
             cs.close();
           }
         }
         catch(NoRouteToHostException ne)
         {
          host =false;
          System.out.println("Wrong host name:Cannot process");
          ta.append(" Wrong host name:Cannot process \n");
          ta.append("****************************************************************************************************************************\n");
         }

       }

      }//try
      catch(Exception e)
      {
       e.printStackTrace();

      }
    }
  }

  public void send()
  {

   try
    {
     InetAddress add = InetAddress.getByName(destip);
     Socket s1 = new Socket(add,Integer.parseInt(n1));
     ObjectOutputStream ob = new ObjectOutputStream(s1.getOutputStream());
     data = sourceip+":"+mess;
     ob.writeObject(data);
     ta.append("Message is Send \n");
      ta.append("****************************************************************************************************************************\n");
     s1.close();
    }//try
    catch(Exception e)
    {

    }
  }

  public static void main(String arg[])
  {

   new Server();
  }
 }
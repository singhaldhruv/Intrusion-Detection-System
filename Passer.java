

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.Properties;
import java.util.StringTokenizer;

class Passer
{
  String st,sadd,sname;
  FileInputStream fin;
  Properties p1;
  String n,ns,n1;
  String op="",isy;
  int cnt=0;
  public Passer()
  {
   try
    {
       InetAddress add = InetAddress.getLocalHost();
       sadd = add.toString();
       StringTokenizer ss = new StringTokenizer(sadd,"/");
       sname = ss.nextToken("/"); //src name
       sname = sname.toLowerCase();
       fin = new FileInputStream("ServerProp.properties");
       p1 = new Properties();
       p1.load(fin);
       n = p1.getProperty("portno");
       ns = p1.getProperty("server");
       n1 = p1.getProperty("srport");
       recclient();
    }
    catch(FileNotFoundException fe){}
    catch(IOException io){}
  }
  public void recclient()
  {
     while(true)
     {
      try
      {
       ServerSocket ss1=new ServerSocket(Integer.parseInt(n1));
       //ServerSocket ss1=new ServerSocket(222);
       System.out.println("Connection.......");
       Socket s1=ss1.accept();
       ObjectInputStream ob = new ObjectInputStream(s1.getInputStream());
       st = (String)ob.readObject();
       //System.out.println("st:"+st);
       StringTokenizer sot = new StringTokenizer(st,":");
       while(sot.hasMoreTokens())
       {
        String ad = sot.nextToken();
        cnt++;
        if(cnt==6)
         {
          isy = ad;
         }
        if(cnt==4)
         {
          op = op+ad+"@";
         }
        else
         {
          op = op+ad+":";
         }
       }
       System.out.println(cnt);
       System.out.println(op);

       //st =st+":"+sname;
       s1.close();
       ss1.close();
       sendclient();
      }
      catch(ClassNotFoundException ce){}
      catch(IOException ex1)
      {

      }
     }
  }


  public void sendclient()
  {

    try
     { if(cnt<=5)
       {
         InetAddress add = InetAddress.getByName(ns);
         Socket s4 = new Socket(add,Integer.parseInt(n));
         ObjectOutputStream ob = new ObjectOutputStream(s4.getOutputStream());
         ob.writeObject(op);
         op="";
         cnt=0;
         s4.close();
       }
       else
       {
         System.out.println("next system is "+isy);
         InetAddress add = InetAddress.getByName(isy);
         Socket s4 = new Socket(add,Integer.parseInt(n1));
         ObjectOutputStream ob = new ObjectOutputStream(s4.getOutputStream());
         ob.writeObject(op);
         op="";
         cnt=0;
         s4.close();
       }
     }
     catch(IOException ex3)
     {
       ex3.printStackTrace();
     }
  }



   public static void main(String s[])
   {
    new Passer();

   }
}
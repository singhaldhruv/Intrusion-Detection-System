

import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;
import java.lang.String;

public class NewEntry extends JFrame implements ActionListener
{
  JButton newbut;
  JLabel lab1,lab2;
  Container con;

  JTabbedPane pane;
  JPanel anom,norm;
  JButton agenbut,ngenbut,aclearbut,nclearbut,backbut,morebut;

  JLabel titlab,adesiplab,ndesiplab,asrciplab,nsrciplab,aportlab,nportlab,ahoplab,nhoplab;
  JTextField adesiptext,ndesiptext,asrciptext,nsrciptext,aporttext,nporttext,ahoptext,nhoptext;
  JFrame fr;
  JTextField t1[];

  FileOutputStream fano,fnor,fres;
  DataOutputStream dano,dnor,dres;


  String ipdst,ipsrc,pot;
  StringBuffer sb;
  final int n=2;
  final int lr=5;
  int cnt;
  int tc;
  int x,y;
  public NewEntry()
  {
    con = getContentPane();
    lab1 = new JLabel("Want to Enter new rules in  DataSet?");
    lab2 = new JLabel("Click Here");
    newbut = new JButton("Add IDS Entry");
    t1=new JTextField[5];
    con.setLayout(null);
    lab1.setBounds(10,10,210,50);
    lab2.setBounds(10,30,80,50);
    newbut.setBounds(60,90,150,60);
    newbut.addActionListener(this);
    con.add(lab1);
    con.add(lab2);
    con.add(newbut);
    setLocation(100,150);
    setTitle("New Entry");
    setSize(300,250);
    show();

    fr = new JFrame("DataSet");
    pane = new JTabbedPane();
    anom = new JPanel();
    norm = new JPanel();
    agenbut = new JButton("Generate to DataSet");
    ngenbut = new JButton("Generate to DataSet");
    aclearbut = new JButton("Clear");
    nclearbut = new JButton("Clear");
    backbut = new JButton("Back");
    morebut = new JButton("More?");

    titlab =  new JLabel("DataSet Entry");
    adesiplab = new JLabel("Destination IP:");
    ndesiplab = new JLabel("Destination IP:");
    asrciplab = new JLabel("Source IP :");
    nsrciplab = new JLabel("Source IP  :");
    aportlab = new JLabel("Port No  :");
    nportlab = new JLabel("Port No  :");
    ahoplab = new JLabel("HobCount  :");
    nhoplab = new JLabel("HobCount  :");

    adesiptext = new JTextField(10);
    ndesiptext = new JTextField(10);
    asrciptext = new JTextField(10);
    nsrciptext = new JTextField(10);
    aporttext = new JTextField(10);
    nporttext = new JTextField(10);
    ahoptext = new JTextField(10);
    nhoptext = new JTextField(10);

    anom.setLayout(null);
    norm.setLayout(null);

    adesiplab.setBounds(40,40,90,50);
    asrciplab.setBounds(40,90,80,50);
    aportlab.setBounds(40,140,80,50);
    ahoplab.setBounds(40,190,80,50);

    adesiptext.setBounds(130,50,100,35);
    asrciptext.setBounds(130,100,100,35);
    aporttext.setBounds(130,140,100,35);
    ahoptext.setBounds(130,190,100,35);
    agenbut.setBounds(60,260,150,25);
    aclearbut.setBounds(220,260,80,25);

    ndesiplab.setBounds(40,40,90,50);
    nsrciplab.setBounds(40,90,80,50);
    nportlab.setBounds(40,140,80,50);
    nhoplab.setBounds(40,190,80,50);

    ndesiptext.setBounds(130,50,100,35);
    nsrciptext.setBounds(130,100,100,35);
    nporttext.setBounds(130,140,100,35);
    nhoptext.setBounds(130,190,100,35);
    ngenbut.setBounds(20,260,150,25);
    nclearbut.setBounds(180,260,80,25);
    morebut.setBounds(400,45,80,25);

    anom.add(adesiplab);
    anom.add(asrciplab);
    anom.add(aportlab);
    anom.add(ahoplab);
    anom.add(adesiptext);
    anom.add(asrciptext);
    anom.add(aporttext);
    anom.add(ahoptext);
    anom.add(agenbut);
    anom.add(aclearbut);

    norm.add(ndesiplab);
    norm.add(nsrciplab);
    norm.add(nportlab);
    norm.add(nhoplab);
    norm.add(ndesiptext);
    norm.add(nsrciptext);
    norm.add(nporttext);
    norm.add(nhoptext);
    norm.add(ngenbut);
    norm.add(nclearbut);
    norm.add(morebut);

   pane.addTab("Anomolous",anom);
   pane.addTab("Normal",norm);

   backbut.addActionListener(this);
   agenbut.addActionListener(this);
   ngenbut.addActionListener(this);
   aclearbut.addActionListener(this);
   nclearbut.addActionListener(this);
   morebut.addActionListener(this);

   ahoptext.setText("3");
   ahoptext.setEditable(false);
   nhoptext.setText("2");
   nhoptext.setEditable(false);

   fr.getContentPane().add("North",titlab);
   fr.getContentPane().add("Center",pane);
   fr.getContentPane().add("South",backbut);
   fr.getContentPane().setSize(500,500);
   fr.getContentPane().setLocation(100,50);

   sb = new StringBuffer();
   tc=0;
   x=270;
   y=50;
  }

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==newbut)
     {
       fr.setVisible(true);
       this.setVisible(false);
     }
     if(ae.getSource()==backbut)
     {
       fr.setVisible(false);
       this.setVisible(true);
     }
     if(ae.getSource()==agenbut)
     {
       getanovalue();

     }
     if(ae.getSource()==ngenbut)
     {
       getnormalvalue();

     }
     if(ae.getSource()==morebut)
     {
      createtext();

     }
     if(ae.getSource()==aclearbut)
     {
      adesiptext.setText("");
      asrciptext.setText("");
      aporttext.setText("");
      //ahoptext.setText("");

     }
     if(ae.getSource()==nclearbut)
     {
      ndesiptext.setText("");
      nsrciptext.setText("");
      nporttext.setText("");
      //nhoptext.setText("");

     }
   }

   public void createtext()
   {
     try
      {
       t1[tc]=new JTextField(10);
       t1[tc].setBounds(x,y,100,35);
       y=y+50;
       norm.add(t1[tc]);
       t1[tc].requestFocus();
       tc++;
       //System.out.println("test");
       norm.validate();
      }
      catch(ArrayIndexOutOfBoundsException ae)
      {
      }
   }


   public void getanovalue()
   {
     boolean flag=true;
     int n1=0,n2=0;
     String adip = adesiptext.getText();
     for(int k=0;k<adip.length();k++)
      {
              char c=adip.charAt(k);
              int int_c=(int)c;
              if(int_c>=48 && int_c<=57 || int_c==46)
              {
              }
              else
              {
               flag=false;
               System.out.println("wrong String");
              }
      }
     String asip = asrciptext.getText();
     for(int m=0;m<asip.length();m++)
      {
              char cs=asip.charAt(m);
              int int_cs=(int)cs;
              if(int_cs>=48 && int_cs<=57 || int_cs==46)
              {
              }
              else
              {
               flag=false;
               System.out.println("wrong String");
              }
      }

     String apot = aporttext.getText();
     String ahop = ahoptext.getText();
     StringTokenizer dip1 = new StringTokenizer(adip,".");

     Vector arr = new Vector();
      while (dip1.hasMoreTokens())
       {
        arr.addElement(dip1.nextToken());
        n1++ ;

       }

      StringTokenizer dip2 = new StringTokenizer(asip,".");

      while (dip2.hasMoreTokens())
       {
        arr.addElement(dip2.nextToken());
        n2++ ;
       }
      System.out.println(n2);
     if( (adip.equals(""))||(asip.equals(""))||(apot.equals(""))||(ahop.equals("")))
      {
        JOptionPane.showMessageDialog(this," Enter  All The Attributes ","Error Message Pane",JOptionPane.ERROR_MESSAGE);

      }


     else if((n1!=4) || (n2!=4))
      {
         JOptionPane.showMessageDialog(this," please enter the ipaddress in correct format","Error Message Pane",JOptionPane.ERROR_MESSAGE);

      }
     else
      {
       System.out.println(flag);
       if(flag)
       {
         cnt=1;
         convert(adip,asip,apot);
       }
       else
       {
         JOptionPane.showMessageDialog(this," please enter the ipaddress in correct format","Error Message Pane",JOptionPane.ERROR_MESSAGE);
       }
      }
   }

   public void getnormalvalue()
   {
      try
      {
        fres = new FileOutputStream("restuser.txt",true);
        dres = new DataOutputStream(fres);
        boolean flag=true;
        int n1=0,n2=0;
        String ndip = ndesiptext.getText();
        for(int k=0;k<ndip.length();k++)
        {
           char c=ndip.charAt(k);
           int int_c=(int)c;
           if(int_c>=48 && int_c<=57 || int_c==46)
            {
            }
           else
            {
              flag=false;
              System.out.println("wrong String");
            }
        }
       String nsip = nsrciptext.getText();
       for(int m=0;m<nsip.length();m++)
       {
          char cs=nsip.charAt(m);
          int int_cs=(int)cs;
          if(int_cs>=48 && int_cs<=57 || int_cs==46)
           {
           }
          else
           {
              flag=false;
              System.out.println("wrong String");
           }
       }

       String npot = nporttext.getText();
       String nhop = nhoptext.getText();
       StringTokenizer ndip1 = new StringTokenizer(ndip,".");

       Vector arr = new Vector();
       while (ndip1.hasMoreTokens())
       {
        arr.addElement(ndip1.nextToken());
        n1++ ;

       }
       System.out.println("no:"+n1);
       StringTokenizer ndip2 = new StringTokenizer(nsip,".");

       while (ndip2.hasMoreTokens())
       {
        arr.addElement(ndip2.nextToken());
        n2++ ;
       }
       System.out.println(n2);
       if( (ndip.equals(""))||(nsip.equals(""))||(npot.equals(""))||(nhop.equals("")))
       {
        JOptionPane.showMessageDialog(this," Enter  All The Attributes ","Error Message Pane",JOptionPane.ERROR_MESSAGE);

       }


       else if((n1!=4) || (n2!=4))
       {
         JOptionPane.showMessageDialog(this," please enter the ipaddress in correct format","Error Message Pane",JOptionPane.ERROR_MESSAGE);

       }
       else
       {
        System.out.println(flag);
        if(flag)
        {
          for(int i=0;i<tc;i++)
          {
            String c = t1[i].getText();
            c = c+":";
            dres.writeBytes(c);
          }
         cnt=2;
         convert(ndip,nsip,npot);
        }
        else
        {
           JOptionPane.showMessageDialog(this," please enter the ipaddress in correct format","Error Message Pane",JOptionPane.ERROR_MESSAGE);
        }
       }


      }
      catch(FileNotFoundException fe)
      {
        fe.printStackTrace();
      }
      catch(IOException ie)
      {
        ie.printStackTrace();
      }
   }

   public void convert(String di,String si,String po)
   {

    System.out.println("get into convert");
    ipdst = di;
    ipsrc = si;
    pot = po;
    System.out.println(ipdst);
    dstip();
    srcip();
    port();
    store();

   }

   public void dstip()
  {
    try
    {
     System.out.println(ipdst);
     System.out.println("get into dstip ");

     int i,a1,a2,a3,a4;
     String s1,s2,s3,s4;
     StringTokenizer ipd = new StringTokenizer(ipdst,".");
     a1 = Integer.parseInt(ipd.nextToken("."));
     a2 = Integer.parseInt(ipd.nextToken("."));
     a3 = Integer.parseInt(ipd.nextToken("."));
     a4 = Integer.parseInt(ipd.nextToken());
     s1 = Integer.toString(a1, 16);
     s2 = Integer.toString(a2, 16);
     s3 = Integer.toString(a3, 16);
     s4 = Integer.toString(a4, 16);

     int ne1 = n-s1.length();
     for(i=0;i<ne1;i++)
     {
      s1="0"+s1;
     }
     int ne2 = n-s2.length();
     for(i=0;i<ne2;i++)
     {
      s2="0"+s2;
     }
     int ne3 = n-s3.length();
     for(i=0;i<ne3;i++)
     {
       s3="0"+s3;
     }
     int ne4 = n-s4.length();
     for(i=0;i<ne4;i++)
     {
      s4="0"+s4;
     }
     System.out.println(s1);
     sb.append(s1);
     sb.append(s2);
     sb.append(s3);
     sb.append(s4);

     System.out.println("Leaving from dstip");
    }
    catch(NumberFormatException ne){}
    catch(NoSuchElementException noe)
    {

    }
  }
   public void srcip()
    {
     try
     {
       int i,a1,a2,a3,a4;
       String s1,s2,s3,s4,spp;
       StringTokenizer ips =   new StringTokenizer(ipsrc,".");
       a1 = Integer.parseInt(ips.nextToken("."));
       a2 = Integer.parseInt(ips.nextToken("."));
       a3 = Integer.parseInt(ips.nextToken("."));
       a4 = Integer.parseInt(ips.nextToken());
       s1 = Integer.toString(a1, 16);
       s2 = Integer.toString(a2, 16);
       s3 = Integer.toString(a3, 16);
       s4 = Integer.toString(a4, 16);

       int ne1 = n-s1.length();
       for(i=0;i<ne1;i++)
       {
         s1="0"+s1;
       }
       int ne2 = n-s2.length();
       for(i=0;i<ne2;i++)
       {
        s2="0"+s2;
       }
       int ne3 = n-s3.length();
       for(i=0;i<ne3;i++)
       {
        s3="0"+s3;
       }
       int ne4 = n-s4.length();
       for(i=0;i<ne4;i++)
       {
        s4="0"+s4;
       }

       sb.append(s1);
       sb.append(s2);
       sb.append(s3);
       sb.append(s4);
       sb.length();
     }
     catch(NumberFormatException ne){}

    }
     public void port()
     {

      int prange = lr-pot.length();
      for(int i=0;i<prange;i++)
      {
        pot = "0"+pot;
      }
      sb.append(pot);
      System.out.println("The data is:"+sb);
     }

     public void store()
     {
      if(cnt==1)
      {
        try
        {
         System.out.println("anon");
         fano = new FileOutputStream("AnoRules.txt",true);
         dano = new DataOutputStream(fano);
         String san = new String(sb);
         san = san+":";
         dano.writeBytes(san);
         sb = new StringBuffer();
         fano.close();
        }
        catch(FileNotFoundException fe)
        {
         fe.printStackTrace();
        }
        catch(IOException ie)
        {
         ie.printStackTrace();
        }
      }
      if(cnt==2)
      {
        try
        {
         System.out.println("normal");
         fnor = new FileOutputStream("NorRules.txt",true);
         dnor = new DataOutputStream(fnor);
         String sno = new String(sb);
         sno = sno+":";
         dnor.writeBytes(sno);
         sb = new StringBuffer();
         fnor.close();
        }
        catch(FileNotFoundException fe)
        {
         fe.printStackTrace();
        }
        catch(IOException ie)
        {
         ie.printStackTrace();

        }
      }
    }

  public static void main(String arg[])
  {
   new NewEntry();
  }
}
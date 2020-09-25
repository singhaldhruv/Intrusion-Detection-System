import java.util.StringTokenizer;
import java.util.Vector;

public class ChromConvert
{
 //final String ipsrc ="90.0.0.200";
 //final String ipdst ="90.20.0.210";
 //int leng = 450236;
 //String byt = Integer.toString(leng);

 String ipsrc;
 String ipdst;
 String pot;

 StringBuffer sb = new StringBuffer();
 Vector v = new Vector();

 StringBuffer sb1 = new StringBuffer();
 Vector v1 = new Vector();

  final int n=2;
  final int lr=5;

  public ChromConvert(String di,String si,String po)
  {
   System.out.println("Enter in to  Chromconvert");
   Server.ta.append("Enter in to  Chromconvert \n");
   ipdst = di;
   ipsrc = si;
   pot = po;
   dstip();
   srcip();
   port();
  }

  public void dstip()
  {
     int i,a1,a2,a3,a4;
     String s1,s2,s3,s4;
     StringTokenizer ipd = new StringTokenizer(ipdst,".");
     a1 = Integer.parseInt(ipd.nextToken("."));
     a2 = Integer.parseInt(ipd.nextToken("."));
     a3 = Integer.parseInt(ipd.nextToken("."));
     a4 = Integer.parseInt(ipd.nextToken());
     s1 = Integer.toString(a1,16);
     s2 = Integer.toString(a2,16);
     s3 = Integer.toString(a3,16);
     s4 = Integer.toString(a4,16);
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



    public void srcip()
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

     //if((s1.equals("5a"))&& (s2.equals("0")) && (s3.equals("0")))
     //{
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

    /* }

     else
     {
      spp = "zzzzzz";
      sb.append(spp);
      int ne = n-s4.length();
      for(i=0;i<ne;i++)
      {
       s4="0"+s4;
      }
      sb.append(s4);
     } */
    }

   public void port()
   {
     int prange = lr-pot.length();
     for(int i=0;i<prange;i++)
     {
       pot = "0"+pot;
     }
     sb.append(pot);

    for(int j=0;j<sb.length();j++)
    {
     //v.addElement(sb.charAt(j));
	  v.addElement(Character.toString(sb.charAt(j)));
    } 
    Server.ta.append("The real time network behaviours are being converted in to Chromosomes \n");
    Server.ta.append("Converted Chromosome : "+v+"\n");
    System.out.println("Chromosomes: "+v);
   }
}
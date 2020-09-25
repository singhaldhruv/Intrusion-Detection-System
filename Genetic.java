import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.lang.String;
import java.lang.StringBuffer;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Random;
public class Genetic
{
  FileOutputStream famatch,fnmatch;
  DataOutputStream damatch,dnmatch;
  FileInputStream fn;
  Properties p;
  StringBuffer sba,sbn;
  Vector vec,fitvect,tmpvect,vnorm,vanom,vectanom,vectnorm,fitter,tempv,vchrom;
  Vector vhop,vrest,vsdchrom,vddchrom;
  Enumeration eagen,engen,enorm,eanom,efit;
  int im=0,rand=0,nogen=0;
  final int ano = 1;
  final int nor = 2;
  int fitnes =0,hopcnt;
  boolean a=true,flag=false,ckflg;
  boolean ch=true;
  int mgenes,tofit;
  String tf,mg;
  NormalData ndata = new NormalData();
  AnomalData adata = new AnomalData();
  RestrictUser ruser = new RestrictUser();
  Random r = new Random();

  public Genetic(Vector v,Vector vh)  {
     // v  =>vector containing chromosomes elements
	 // vh =>vector containing intermediate system IP's
   try {
	 System.out.println("In Generic Construtor");
     fn = new FileInputStream("ServerProp.properties");
     p = new Properties();
     p.load(fn);
     tf = p.getProperty("tofit");          //14
     mg = p.getProperty("mgenes");  //18
     tofit = Integer.parseInt(tf);
     mgenes = Integer.parseInt(mg);
     fitvect = new Vector();
     tmpvect = new Vector();
     tempv   = new Vector();
     vchrom  = new Vector();
     vhop = new Vector();
     vrest = new Vector();
     sba = new StringBuffer();
     sbn = new StringBuffer();
     vchrom  = v;
     vhop = vh;
     hopcnt = (vhop.size());
     Server.ta.append("There are "+hopcnt+" intermediate systems \n");
     System.out.println("count is:"+hopcnt);
     if(hopcnt>=1)
     hopcheck();
     if(a)
     normalmatch();
     if(a)
     {
      fitvect=new Vector();
      nogen =0;
      normalgenetic();
     }
      ckflg = check();
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

  public boolean check()
  {
   if(flag==true)
   {
    return true;
    //System.out.println("Message is send");
   }
   else
   {
     return false;
   //System.out.println("Anomous Connection:Message cannot be send");
   }
  }

  public void normalmatch()
  {
   Server.ta.append("Entering into same Normal match \n");
   System.out.println("Entering into same normal match");
   enorm = ndata.norvect.elements();
   while(enorm.hasMoreElements())
   {
    vnorm = (Vector)enorm.nextElement();
    if(vchrom.equals(vnorm))
    {
      flag = true;
      a =false;
      Server.ta.append(" Perfectly matches with the Normal DataSet : Allow the Data \n");
      System.out.println("Allow the data");
      return ;

    }
   }
   Server.ta.append(" Does not match Perfectly : Leaving from the same normal match \n");
   System.out.println("Leaving from the same normal match");
   anomalmatch();
  }

  public void anomalmatch()
  {
   Server.ta.append(" Entering into same Anomal match \n");
   System.out.println("Entering into same abnormal match");
   eanom = adata.anovect.elements();
   while(eanom.hasMoreElements())
   {
    vanom = (Vector)eanom.nextElement();
    if(vchrom.equals(vanom))
    {
      flag = false;
      a =false;
      Server.ta.append(" Perfectly matches with the Anomal DataSet : Block the Data \n");
      System.out.println("Block the data");
      return ;

    }
   }
   Server.ta.append(" Does not match Perfectly : Leaving from the same Anomal match \n");
   System.out.println("Leaving from the same abnormal match");
   anomalgenetic();
  }

  public void anomalgenetic()
  {
   Server.ta.append(" Genetic Algorithm \n");
   Server.ta.append("---------------------------------\n\n");
   Server.ta.append(" Entering into the Anomal genetic \n");
   Server.ta.append("--------------------------------------------\n");
   System.out.println("Entering into the anomal genetic");
   eagen = adata.anovect.elements();
   while(eagen.hasMoreElements())
   {
    vectanom = (Vector)eagen.nextElement();

    fitness(vectanom);
   }
   Server.ta.append(" Fitter Chromosomes selected for crossover : "+fitvect.size()+"\n");
   System.out.println("Length is:"+(fitvect.size()));
   //System.out.println(fitvect);
   if(fitvect.size()%2 != 0)
   {
    fitvect.removeElementAt(fitvect.size()-1);
   }
    //System.out.println(fitvect);
   crossover(fitvect,ano);
   if(ch)
   {
    Server.ta.append(" No Matches Found : leaving from the anomal genetic \n");
    System.out.println("leaving from the anomal genetic");
   }
  }

   public void normalgenetic()
   {
    Server.ta.append(" Entering into the Normal genetic \n");
    Server.ta.append("------------------------------------------\n");
    System.out.println("Entering into the normal");
    engen = ndata.norvect.elements();
    while(engen.hasMoreElements())
    {
     vectnorm = (Vector)engen.nextElement();

     fitness(vectnorm);
    }
    Server.ta.append(" Fitter Chromosomes selected for crossover : "+fitvect.size()+"\n");
    System.out.println("Length is:"+(fitvect.size()));
   //System.out.println(fitvect);

    if(fitvect.size()%2 != 0)
    {
     fitvect.removeElementAt(fitvect.size()-1);
    }

    //System.out.println(fitvect);
    crossover(fitvect,nor);
    if(ch)
    {
      Server.ta.append(" No Matches Found : leaving from the Normal genetic \n");
      Server.ta.append("Algorithm Blocks the Connection");
      System.out.println("leaving from the normal");
    }
   }

   public void fitness(Vector vt)
   {
    int tr=0;
    vec = vt;
    int count=0;

    for(tr=0;tr<vec.size();tr++)
    {
     if( (vchrom.elementAt(tr)).equals((vec.elementAt(tr))) )
     count++;
    }

    //System.out.println("matches :" + count);

    if(count>=tofit)
    {
     fitvect.addElement(vec);
    }

  }

  public void crossover(Vector vt,int f)
  {
    fitter = vt;
    int flg = f;
    int vi=0;
    Vector tmv1,tmv2,tmv3;
    tmpvect = new Vector();

    nogen++;
    if(nogen<=3)
    {
    rand = r.nextInt(21);
    Server.ta.append(" Random No : "+rand+"\n");
    System.out.println("random:"+rand);
    for(vi=0;vi<fitter.size();vi+=2)
    {
     tmv1 = new Vector();
     tmv2 = new Vector();
     tmv3 = new Vector();
     tmv1 = (Vector)fitter.get(vi);
     tmv2 = (Vector)fitter.get(vi+1);

     for(int vj=rand;vj<tmv1.size();vj++)
     {
      tmv3.addElement(tmv1.get(vj));
     }
     for(int vk=tmv1.size()-1;vk>=rand;vk--)
     {
      tmv1.remove(vk);
     }

     for(int vj=rand;vj<tmv2.size();vj++)
     {
      tmv1.addElement(tmv2.get(vj));
     }
     for(int vk=tmv2.size()-1;vk>=rand;vk--)
     {
      tmv2.remove(vk);
     }
     for(int vj=0;vj<tmv3.size();vj++)
     {
      tmv2.addElement(tmv3.get(vj));
     }

     tmpvect.addElement(tmv1);
     tmpvect.addElement(tmv2);
    }
    //System.out.println(tmpvect);
    Server.ta.append(" Generation No : "+nogen+"\n");
    System.out.println("Generations :"+nogen);
    int tn=0;
    int cnt=0;
    efit = tmpvect.elements();
    while(efit.hasMoreElements())
    {
     Vector vfit = (Vector)efit.nextElement();
     for(tn=0;tn<vfit.size();tn++)
     {
      if( (vchrom.elementAt(tn)).equals((vfit.elementAt(tn))) )
      cnt++;
     }
     Server.ta.append(" Matches : "+cnt+"\n");
     System.out.println("matches :" + cnt);

     if(cnt>=mgenes)
     {
      if(flg==1)
      {
        try
        {
          flag = false;
          a =false;
          famatch = new FileOutputStream("un.txt",true);
          damatch = new DataOutputStream(famatch);

          for(int i=0;i<vchrom.size();i++)
          {

            String spp = vchrom.elementAt(i).toString();
            sba.append(spp);
          }
          sba.append(":");
          String sa = new String(sba);
          damatch.writeBytes(sa);
          Server.ta.append(" Matches : Algorithm blocks \n");
          System.out.println("Block");
          ch = false;
          return ;
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
      if(flg==2)
      {
        try
        {
          flag = true;
          a =false;
          fnmatch = new FileOutputStream("nor.txt",true);
          dnmatch = new DataOutputStream(fnmatch);

          for(int i=0;i<vchrom.size();i++)
          {

            String spp = vchrom.elementAt(i).toString();
            sbn.append(spp);
          }
          sbn.append(":");
          String sa = new String(sbn);
          dnmatch.writeBytes(sa);
          Server.ta.append(" Matches : Algorithm Allows \n");
          System.out.println("Send");
          ch = false;
          return ;
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
      cnt=0;
    }
    fitter.removeAllElements();
    // System.out.println("tmpvect length:"+tmpvect.size());
    // System.out.println("tmpvect:"+tmpvect);
    crossover(tmpvect,f);
   }

  }



   public void hopcheck()
   {
     System.out.println("Enter into hopcheck");
     Server.ta.append(" Enter into HopCount check process \n");
     vrest = ruser.res;
     if(hopcnt>=3)
     {
       flag = false;
       a =false;
       System.out.println("hopcount Block the data");
       Server.ta.append("Hopcount is 3 or More : Blocks the data \n");
       return ;
     }
     else
     {
      if(hopcnt>=1)
      {
       for(int i=0;i<vhop.size();i++)
       {
         for(int j=0;j<vrest.size();j++)
         {
           if( vhop.elementAt(i).equals(vrest.elementAt(j)) )
            {
             flag = false;
             a = false;
             System.out.println("matches");
             Server.ta.append("Intermediate systems matches with the restricted systems \n");
             System.out.println("Block the data");
             Server.ta.append("Block the data \n");
             return ;
            }
           else
           {
            fitnes =1;
           }
         }
       }

      }
     }
     System.out.println("Leaving from hopcheck");
     Server.ta.append("No matches found :Leaving from hopcheck \n");
   }


}
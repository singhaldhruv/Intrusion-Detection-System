

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.Vector;
import java.util.StringTokenizer;
import java.lang.String;


class NormalData
{
    Vector norvect;
    public NormalData()
    {
      norvect=new Vector();
      FileInputStream fi=null;
      DataInputStream d=null;
      String str="";

      try
      {
        fi=new FileInputStream("NorRules.txt");
        d=new DataInputStream(fi);
        str=d.readLine();
      }
      catch(FileNotFoundException f)
      {
       f.printStackTrace();
      }
      catch(IOException e)
      {
       e.printStackTrace();
      }

      StringTokenizer stkn=new StringTokenizer(str, ":");


      while(stkn.hasMoreElements())
      {
         String sp = stkn.nextToken();
         Vector v=new Vector();
         for(int i=0;i<sp.length();i++)
         {
          v.addElement(Character.toString(sp.charAt(i)));
         }

          norvect.addElement(v);
      }


    }
 }

 import java.io.IOException;
 import java.io.FileNotFoundException;
 import java.io.FileInputStream;
 import java.io.DataInputStream;
 import java.util.Vector;
 import java.util.StringTokenizer;
 import java.lang.String;

 class RestrictUser
 {
   Vector res;
   public RestrictUser()
   {
    res = new Vector();
    FileInputStream fi=null;
    DataInputStream d=null;
    String str="";

    try
    {
     fi=new FileInputStream("RestRules.txt");
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
     res.addElement(stkn.nextToken());

    }

   }
 }
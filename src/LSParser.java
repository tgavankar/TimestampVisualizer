import java.util.ArrayList;

public class LSParser
{
  // Desired format is obtained by executing "ls -l --time-style=+%D\ %T"
  
  public static ArrayList<FileObject> parseFile(String filename)
  {
    
    FileLoader fl = new FileLoader(filename);
    ArrayList<FileObject> files = new ArrayList<FileObject>();
    
    while(fl.hasMoreLines())
    {
      String line = fl.readLine();
      if(line.indexOf("total") == 0) continue;
      
      int index = line.indexOf("/");
      int month = Integer.parseInt(line.substring(index-2, index));
      int day = Integer.parseInt(line.substring(index+1, index+3));
      int year = Integer.parseInt(line.substring(index+4, index+6));
      int hour = Integer.parseInt(line.substring(index+7, index+9));
      int minute = Integer.parseInt(line.substring(index+10, index+12));
      int second = Integer.parseInt(line.substring(index+13, index+15));
      String name = line.substring(index+16);
      
      FileObject fo = new FileObject(month, day, year, hour, minute, second, name);
      files.add(fo);
    }
    
    
    fl.close();
    return files;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
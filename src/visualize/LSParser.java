package visualize;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;;

public class LSParser
{  
  // Desired format is obtained by executing "ls -ls --time-style=+%m/%d/%Y\ %T"
  
  public static ArrayList<FileObject> parseFile(String filename)
  {
	  
    FileLoader fl = new FileLoader(filename);
    ArrayList<FileObject> files = new ArrayList<FileObject>();
    
    int index = 0;
    int month = 0;
    int day = 0;
    int year = 0;
    int hour = 0;
    int minute = 0;
    int second = 0;
    int dayOfWeek = 0;
    String name = "";
    int sizeStart = 0;
    int size = 0;
    String line = "";
    
    while(fl.hasMoreLines())
    {
      line = fl.readLine();
      if(line.indexOf("total") == 0) continue;
      
      //Extract the modify date info + filename
      index = line.indexOf("/");
      month = Integer.parseInt(line.substring(index-2, index));
      day = Integer.parseInt(line.substring(index+1, index+3));
      year = Integer.parseInt(line.substring(index+4, index+8));
      hour = Integer.parseInt(line.substring(index+9, index+11));
      minute = Integer.parseInt(line.substring(index+12, index+14));
      second = Integer.parseInt(line.substring(index+15, index+17));
      name = line.substring(index+18);
      
      //Extract the size in bytes
      index -= 3;
      sizeStart = index-1;
      while(line.charAt(sizeStart) != ' ')
      {
        sizeStart--;
      }
      size = Integer.parseInt(line.substring(sizeStart+1, index));
      

      GregorianCalendar cal = new GregorianCalendar(year, month, day);
      dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
      
      FileObject fo = new FileObject(month, day, year, hour, minute, second, dayOfWeek, name, size,
                                   (line.charAt(0) == '-'), (line.charAt(1) != '-'),
                                    (line.charAt(2) != '-'), (line.charAt(3) != '-'),
                                    (line.charAt(4) != '-'), (line.charAt(5) != '-'),
                                    (line.charAt(6) != '-'), (line.charAt(7) != '-'),
                                    (line.charAt(8) != '-'), (line.charAt(9) != '-'));
      files.add(fo);
    }
    
    // Debug/test code - prints the list of formatted FileObject's
    // Displays all info stored in the object in a readable format
    // Uncomment to view
    /*
    for( FileObject x : files )
      System.out.println(x);
    */
    
    fl.close();
    return files;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
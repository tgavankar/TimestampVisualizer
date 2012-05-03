package visualize;

public class FileObject
{
  private String origLine;
  private int month;
  private int day;
  private int year;
  private int hour;
  private int minute;
  private int second;
  private int dayOfWeek;
  private String name;
  private int size;
  private boolean isFile;
  
  private boolean userRead;
  private boolean userWrite;
  private boolean userExecute;
  
  private boolean groupRead;
  private boolean groupWrite;
  private boolean groupExecute;
  
  private boolean allRead;
  private boolean allWrite;
  private boolean allExecute;
  
  public FileObject(String orig, int month, int day, int year, 
                    int hour, int minute, int second,
                    int dayOfWeek, String name, int size,
                    boolean isFile,
                    boolean userRead, boolean userWrite, boolean userExecute,
                    boolean groupRead, boolean groupWrite, boolean groupExecute,
                    boolean allRead, boolean allWrite, boolean allExecute)
  {
	this.origLine = orig;
    this.month = month;
    this.day = day;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    this.dayOfWeek = dayOfWeek;
    this.name = name;
    this.size = size;
    this.isFile = isFile;
    this.userRead = userRead;
    this.userWrite = userWrite;
    this.userExecute = userExecute;
    this.groupRead = groupRead;
    this.groupWrite = groupWrite;
    this.groupExecute = groupExecute;
    this.allRead = allRead;
    this.allWrite = allWrite;
    this.allExecute = allExecute;
  }
  
  public boolean userCanRead()
  {
    return userRead;
  }
  
  public boolean userCanWrite()
  {
    return userWrite;
  }
  
  public boolean userCanExecute()
  {
    return userExecute;
  }
  
    public boolean groupCanRead()
    {
      return groupRead;
    }
    
    public boolean groupCanWrite()
    {
      return groupWrite;
    }
    
    public boolean groupCanExecute()
    {
      return groupExecute;
    }
    
    public boolean allCanRead()
    {
      return allRead;
    }
    
    public boolean allCanWrite()
    {
      return allWrite;
    }
    
    public boolean allCanExecute()
    {
      return allExecute;
    }
  
  public String getOrigLine() {
	  return origLine;
  }
    
  public boolean isFile()
  {
    return isFile;
  }
  
  public int getMonth()
  {
    return month;
  }
  
  public int getDay()
  {
    return day;
  }
  
  public int getYear()
  {
    return year;
  }
  
  public int getHour()
  {
    return hour;
  }
  
  public int getMinute()
  {
    return minute;
  }
  
  public int getSecond()
  {
    return second;
  }
  
  public int getDayOfWeek()
  {
    return dayOfWeek;
  }

  public String getName()
  {
    return name;
  }
  
  public int getSize()
  {
    return size;
  }
  

  public String getDayByName()
  {
    String days = "";
    switch(dayOfWeek)
    {
      case 1 : days = "Monday"; break;
      case 2 : days = "Tuesday"; break;
      case 3 : days = "Wednesday"; break;
      case 4 : days = "Thursday"; break;
      case 5 : days = "Friday"; break;
      case 6 : days = "Saturday"; break;
      case 7 : days = "Sunday"; break;
      default : break;
    }
    return days;
  }
  
  public String getMonthByName()
  {
    String mo = "";
    switch(month)
    {
      case 1 : mo = "January"; break;
      case 2 : mo = "February"; break;
      case 3 : mo = "March"; break;
      case 4 : mo = "April"; break;
      case 5 : mo = "May"; break;
      case 6 : mo = "June"; break;
      case 7 : mo = "July"; break;
      case 8 : mo = "August"; break;
      case 9 : mo = "September"; break;
      case 10 : mo = "October"; break;
      case 11 : mo = "November"; break;
      case 12 : mo = "December"; break;
      default : break;
    }
    return mo;
  }
  
  public String toString()
  {
    String s = "Name: "+getName()+"\n";
    s+="Date modified: "+getMonthByName()+" "+getDay()+", "+getYear()+"\n";
    s+="Time modified: ";
    if(getHour() < 10)
      s+="0"+getHour()+":";   
    else s+= getHour()+":";
    
    if(getMinute() < 10)
      s+="0"+getMinute()+":";
    else s+=getMinute()+":";
    
    if(getSecond() < 10)
      s+="0"+getSecond();
    else s+= getSecond();
    
    
    s+="\nFile size: "+size+" bytes";
    
    if(isFile())
      s+="\nThis is a file";
    else s+= "\nThis is a directory";
    
    s+= "\nUser priviledges: ";
    s+= userCanRead() ? "read " : "";
    s+= userCanWrite() ? "write " : "";
    s+= userCanExecute() ? "execute " : "";
    
    s+= "\nGroup priviledges: ";
    s+= groupCanRead() ? "read " : "";
    s+= groupCanWrite() ? "write " : "";
    s+= groupCanExecute() ? "execute " : "";
    
        s+= "\nGeneral priviledges: ";
    s+= allCanRead() ? "read " : "";
    s+= allCanWrite() ? "write " : "";
    s+= allCanExecute() ? "execute " : "";
    
    s+="\n-----------------\n";
    return s;
  }
  
  
  
  
  
  
  
}
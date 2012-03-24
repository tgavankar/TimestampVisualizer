public class FileObject
{
  private int month;
  private int day;
  private int year;
  private int hour;
  private int minute;
  private int second;
  private String name;
  
  public FileObject(int month, int day, int year, 
                    int hour, int minute, int second,
                    String name)
  {
    this.month = month;
    this.day = day;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    this.name = name;
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
  
  public String getName()
  {
    return name;
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
    String s = "File: "+getName()+", ";
    s+="last modified on "+getMonthByName()+" "+getDay()+", '"+getYear()+" ";
    
    if(getHour() < 10)
      s+="at 0"+getHour()+":";   
    else s+= "at "+getHour()+":";
    
    if(getMinute() < 10)
      s+="0"+getMinute()+":";
    else s+=getMinute()+":";
    
    if(getSecond() < 10)
      s+="0"+getSecond();
    else s+= getSecond();
    return s;
  }
  
  
  
  
  
  
  
}
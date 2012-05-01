package visualize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader
{
  private BufferedReader in;
  private String line;
  
  public FileLoader(String fileName)
  {
    try
    {
      in = new BufferedReader(new FileReader(fileName));
      line = in.readLine();
    }
    catch(IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public boolean hasMoreLines()
  {
    return line != null;
  }
  
  public String readLine()
  {
    try
    {
      String toReturn = line;
      line = in.readLine();
      return toReturn;
    }
    catch(IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public void close()
  {
    try
    {
      in.close();
    }
    catch(IOException e)
    {
      throw new RuntimeException(e);    }
  }
}
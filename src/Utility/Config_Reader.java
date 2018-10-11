package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class Config_Reader {
	
	Properties pros;
	

	public Config_Reader()
	{
		
	
	try {
		File src=new File("C:\\Users\\Acer\\eclipse-workspace\\KIN\\Configuration\\config.property");
		FileInputStream fis=new FileInputStream(src);
		
        pros=new Properties();
		pros.load(fis);
	
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	}
	
	public String getChromePath()
	{
		String path=pros.getProperty("ChromeDriver");
		return path;
	}
	
	public String getURL()
	{
		String path=pros.getProperty("URL");
		return path;
		
	}
}


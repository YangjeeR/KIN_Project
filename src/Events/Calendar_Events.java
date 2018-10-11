package Events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import Utility.Config_Reader;

public class Calendar_Events {
	WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar_Events obj=new Calendar_Events();
		obj.Setup();
		obj.events_daily();
		//obj.calendar_events();
		

	}
	
	public void Setup()
	{
		Config_Reader config=new Config_Reader();
		System.setProperty("webdriver.chrome.driver", config.getChromePath());
		 driver= new ChromeDriver();
		driver.get("http://kinstaging.graphenecreative.co.uk/culture");
	}
	
	
	
	public void events_daily()
	{
		try {
		((JavascriptExecutor)driver).executeScript("scroll(0,2300);");
		WebElement container=driver.findElement(By.xpath("/html/body/div[3]/main/section[5]/div"));
		Thread.sleep(2000);
		List<WebElement> cat_links=container.findElements(By.xpath("//span[@class='events-date']"));
		List<WebElement> end_date=container.findElements(By.xpath("//span[@class='events-date']/meta"));
		System.out.println("Total no. of links in events : " + end_date.size());
		
		/*for(int i=0;i<cat_links.size();i++)
		{
			WebElement ele=cat_links.get(i);
			String events=ele.getText();
			System.out.println(events);
			//String cal_item=ele.getAttribute("itemprop");//start date class
			//String cal_cont=ele.getAttribute("content");//start date
			//String end_item=ele.getAttribute("itemprop");//end date class
			//String enddate=ele.getAttribute("content");//end date
	
			if(events.startsWith("Every Sunday")) 
			{
				
			}
			
		}*/
			calendar_events();//run calendar dates
			Thread.sleep(2000);
		
			
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    	Date date = new Date();
		    	String cur_date=dateFormat.format(date);
		    	//int current = Integer.valueOf(cur_date);//converting date to integer
		    	String event_date="2017/12/24";
		    	
		    	
		    	
		    	if(event_date.compareTo(cur_date)>0)
		    	{
		    	  
		    	    WebElement element = driver.findElement(By.xpath("//div[@class='next']"));
		    		element.click();//next button
		    		calendar_events();//rerun calendar dates if event end date is greater than current date
		    	
		    	}
		    	
			
		
		 
		}
		
		catch(Exception e)
		{
			System.out.println("Unable to fetch daily events." + e.getMessage());
			
		}
		
	}
	
	public void calendar_events() {
		String cal_events=null;
		WebElement ele;
		try {
			WebElement container=driver.findElement(By.xpath("/html/body/div[3]/main/section[5]/div/div[2]/div[1]"));
			Thread.sleep(2000);
			List<WebElement> cal=container.findElements(By.xpath("//td[@class='curr-month-date active']"));
			 int count=cal.size();
			System.out.println("Total no. of days in Calendar : " +count);		
           
			for(int i=0;i<count;i++)
			{
			 
			  ele=cal.get(i);//day of calendar
			  cal_events=ele.getText();
			// System.out.println("Day of Month:" +cal_events);    
		
				//taking month
				String month=driver.findElement(By.xpath("/html/body/div[3]/main/section[5]/div/div[2]/div[1]/div[2]/div/div[2]")).getText();
				//System.out.println(month);
				switch(month)
				{
				case "November 2017":
				month="11";
				break;
				case "December 2017":
				month="12";
				break;
				default: 
				 System.out.println("Invalid date"); 
				
				}
				//System.out.println("After switch case:"+month);
				
			
				/* retrieve day from date of calendar*/
				SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			    Date MyDate = newDateFormat.parse(cal_events+"/"+month+"/2017");
			    newDateFormat.applyPattern("yyy/MM/d-EEEE");
			    String day = newDateFormat.format(MyDate);
			    String[] parts = day.split("-");
			    String year = parts[0]; // year
			    String day_date = parts[1]; // day
			   
               
			    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    	Date date = new Date();
		    	String cur_date=dateFormat.format(date);
		    	
		    	if(year.compareTo(cur_date)>0 && day_date.equalsIgnoreCase("Sunday"))
		    	{
		    		//remove dat date
		    		System.out.println(day);
		    		ele.click();
		    		for(int j=0;j<count;j++)
					{
		    		//driver.findElement(By.xpath("/html/body/div[3]/main/section[5]/div/div[2]/div[2]/div/div[2]/div/div[2]")).click();
		    		//Thread.sleep(1000);
		    		String cat_links=driver.findElement(By.xpath("//span[@class='events-date']")).getText();
		    		String cat_links1=driver.findElement(By.xpath("//div[@class='item']/h2")).getText();
			    	System.out.println("The events time is:"+cat_links);
			    	System.out.println("The events title is:"+cat_links1);
			    	Thread.sleep(3000);
					}
		    	}
			    
			   
			    	
			   /* if(day.equalsIgnoreCase("Sunday"))
			    {
			    	ele.click();
			    	Thread.sleep(5000);
			    	driver.findElement(By.xpath("/html/body/div[3]/main/section[5]/div/div[2]/div[2]/div/div[2]/div/div[2]")).click();
			    	Thread.sleep(1000);//next button of event column
			    	String cat_links=driver.findElement(By.xpath("//span[@class='events-date']")).getText();
			    	System.out.println("The events title is:"+cat_links);
			    	
			    	
			    }*/
			}		   			   
			
		}
			
			catch(Exception e)
			{
				System.out.println("Unable to fetch daily events." + e.getMessage());
				
			}
				
	}
	
	public void Teardown()
	{
		driver.close();
	}

}

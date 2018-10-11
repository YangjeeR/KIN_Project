package Events;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Utility.Config_Reader;

public class Sorting_Events {
WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Sorting_Events obj=new Sorting_Events();
		obj.Setup();
		obj.events_list();

	}
	
	public void Setup()
	{
		Config_Reader config=new Config_Reader();
		System.setProperty("webdriver.chrome.driver", config.getChromePath());
		 driver= new ChromeDriver();
		driver.get("http://kinstaging.graphenecreative.co.uk/events-and-meetings");
	}
	
	public void events_list() throws InterruptedException
	{
		((JavascriptExecutor)driver).executeScript("scroll(0,200);");
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div/div/div/div[1]/div/span")).click();//events
		//select events list
		driver.findElement(By.id("past_meetings")).click();
		Thread.sleep(3000);
		WebElement container=driver.findElement(By.xpath("//*[@id=\"event-container\"]"));
		List<WebElement> caption=container.findElements(By.tagName("h4"));
		
		System.out.println("No of events:"+ caption.size());
		for(int i=1;i<=caption.size();i++)
		{

		WebElement container1=driver.findElement(By.xpath("//*[@id=\"event-container\"]/div["+i+"]/div[3]/h4/a"));	
		String link=container1.getAttribute("href");
		System.out.println(link);
		container1.click();
		Thread.sleep(3000);
		WebElement title=driver.findElement(By.xpath("/html/body/div[3]/main/div[1]/div/div/div[1]/span)"));
		System.out.println("The content's date is:" + title.getText());
		
		
	}

}
}


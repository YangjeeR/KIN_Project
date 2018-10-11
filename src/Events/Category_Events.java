package Events;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.Config_Reader;

public class Category_Events {

	WebDriver driver;
	String total_links;
	String category;
	WebElement ele;
	/*@BeforeMethod
	public void setup() throws InterruptedException
	{
		Config_Reader config=new Config_Reader();
		System.setProperty("webdriver.chrome.driver", config.getChromePath());
		driver=new ChromeDriver();
		Thread.sleep(2000);
		driver.get(config.getURL());
		//Thread.sleep(2000);
		//driver.manage().window().maximize();
		

	}
	
	@Test 
	public void EventTest()
	{
	
		try
		
		{
		//driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/header/div/div[1]/ul/li[4]/a")).click();//click on share  your events button
		//scroll down to Category section
		((JavascriptExecutor) driver).executeScript("scroll(0,250);");
		//click on Category options
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div/div/div/div[2]/div/span")).click();
		Thread.sleep(2000);
		//select from dropdown option
		((JavascriptExecutor) driver).executeScript("scroll(0,250);");
		driver.findElement(By.xpath("//*[@id=\"14\"]")).click();//community
		Thread.sleep(2000);
		WebElement event=driver.findElement(By.xpath("//*[@id=\'event-container\']"));
		
		List<WebElement> container=event.findElements(By.xpath("//a[starts-with(@href, \"http://kinstaging.graphenecreative.co.uk/event/\")]"));
		System.out.println("Total number of links : " + container.size());
	
		WebElement next=driver.findElement(By.xpath("//*[@id=\"PageNavigation\"]/ul"));
		List<WebElement> next_page=next.findElements(By.tagName("a"));
		System.out.println("Total number of links : " + next_page.size());
		
		for(int j=0;j<next_page.size();j++)
		{
								
		for(int i=0;i<container.size();i++)
		{
		int sum=0;

		List<WebElement> div=driver.findElements(By.xpath("//a[starts-with(@href, \"http://kinstaging.graphenecreative.co.uk/event/\")]"));
		sum=div.size();
         WebElement links=div.get(i);
		 String links_name=links.getAttribute("href");
		 System.out.println(links_name);
		 Thread.sleep(2000);
		
		}
		
	
	
		((JavascriptExecutor) driver).executeScript("scroll(0,2000);");
		driver.findElement(By.xpath("//*[@id=\"ajax-grid-next-button\"]/a/span")).click();
		Thread.sleep(2000);
			/* List<WebElement> next_page1=next.findElements(By.tagName("a"));
				next_page1.get(j).click();	
				 Thread.sleep(2000);*/
/*
	}
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	*/


	
	@Test(dataProvider="admin")
	public void events_backend(String username,String password) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acer\\Desktop\\selenium\\geckodriver\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://kinstaging.graphenecreative.co.uk/admin/login");
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div/div[2]/input")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div/div[3]/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div/button")).click();
         Thread.sleep(3000);
		
		Assert.assertTrue("User's credentials are not correct",driver.getTitle().contains("Dashboard"));
		System.out.println("The site is successfully logged in");
		((JavascriptExecutor) driver).executeScript("scroll(0,250);");
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/ul/li[12]/a/i[1]")).click();//click on events section
		((JavascriptExecutor) driver).executeScript("scroll(0,250);");
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/ul/li[12]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		Select cat=new Select(driver.findElement(By.id("action")));//select option
		cat.selectByVisibleText("Community");
		
		WebElement container=driver.findElement(By.xpath("//*[@id=\"events-table\"]"));
		Thread.sleep(2000);
		List<WebElement> cat_links=container.findElements(By.xpath("//tr/td[contains(text(), 'Community')]"));
		System.out.println("Total no. of links in events : " + cat_links.size());
        
		WebElement next=driver.findElement(By.xpath("//*[@id=\"PageNavigation\"]/ul"));//pagination
		Thread.sleep(2000);
		List<WebElement> next_count=next.findElements(By.tagName("a"));
		System.out.println("Total no. of category page : " + next_count.size());
	
	for(int j=0;j<next_count.size();j++)
	{
				
  for(int i=0;i<cat_links.size();i++)
	  
		{ 
	 
			List<WebElement> cat_links1=container.findElements(By.xpath("//tr/td[contains(text(), 'Community')]"));
			WebElement ele=cat_links1.get(i);
			String category=ele.getText();		
			System.out.println("Total no. of Category events : " + category);	
				
		} 
	

((JavascriptExecutor) driver).executeScript("scroll(0,2000);");
System.out.println(j);
if(j!=next_count.size())
{
driver.findElement(By.xpath("//*[@id=\"ajax-grid-next-button\"]/a")).click();
}
Thread.sleep(3000);
		}
 

}
	

/*WebElement next1=driver.findElement(By.xpath("//*[@id=\"PageNavigation\"]"));//pagination

List<WebElement> next_count1=next1.findElements(By.tagName("a"));
WebElement category=next_count1.get(j);
category.click();*/
		
 /*WebElement next1=driver.findElement(By.xpath("//*[@id=\"PageNavigation\"]"));
	 List<WebElement> next_page1=next1.findElements(By.tagName("a"));
	
		next_page1.get(j).click();	*/

	
	


	
	@DataProvider(name="admin")
	public Object[][] admindata()
	{
		Object[][] data=new Object[1][2];
		data[0][0]="admin";
		data[0][1]="Kin@admin12";
		
		return data;
		
	}
	

	/*
	@AfterMethod
	public void TearDown()
	{
		driver.quit();
	}
	*/

}


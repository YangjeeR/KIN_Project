package Events;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


import Utility.Config_Reader;

public class Personal_Attendance {


	WebDriver driver;
	
	public void Setup()
	{
		Config_Reader config=new Config_Reader();
		System.setProperty("webdriver.chrome.driver", config.getChromePath());
		 driver= new ChromeDriver();
		driver.get("http://olivemedia.eattendance.com.np/Login.aspx");
	}
	

	public void login(String username,String password,String name,String month_name) throws Exception 
	{
		 Setup();
		driver.findElement(By.id("txtUserName")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogIn")).click();
		Thread.sleep(5000);
		WebElement ele=driver.findElement(By.xpath("//ul[@id='tabmenu']/li"));
		List<WebElement> ele1=ele.findElements(By.tagName("a"));
		
		String personal = "Personal Attendance";
	    for(int i=0;i<ele1.size();i++){
	        System.out.println("Content text is : " + ele1.get(i).getText());
	        // match the content here in the if loop
	        if(ele1.get(i).getText().equals(personal)){
	            // perform action
	        	ele1.get(i).click();
	        }
	    }
	    
	  
	    
	    driver.findElement(By.xpath("//ul[@class='common-wrapper']/li")).click();
	    Thread.sleep(2000);
		Select user=new Select(driver.findElement(By.id("main_ddlEmployeeName")));//select option
		user.selectByVisibleText(name);
		Select month=new Select(driver.findElement(By.xpath("//*[@id=\"main_ddlMonth\"]")));
		month.selectByVisibleText(month_name);
		
		driver.findElement(By.id("main_btnViewReport")).click();
		Thread.sleep(5000);
		
		//list of attendance
		
	}
	
	
	
	
	@Test
	public void table_data() throws Exception
	{
		
		login("yangjeerai@olivemedia.co","e7ce645c","Yangjee Rai [173]","October");
	
		 
		//((JavascriptExecutor) driver).executeScript("scroll(0,500);");
		WebElement attend=driver.findElement(By.xpath("//div[@id=\"main_AttendanceDetailsWrapper\"]"));
		//List<WebElement> report_list=attend.findElements(By.xpath("//tr/td/span"));
		
		 
		List<WebElement> irows =attend.findElements(By.xpath("//*[@id=\"main_AttendanceDetailsWrapper\"]/table/tbody/tr/td/span"));      
		int iRowsCount = irows.size();     
		List<WebElement> icols =attend.findElements(By.xpath("//*[@id=\"main_AttendanceDetailsWrapper\"]/table/tbody/tr/th/span"));   
		int iColsCount = icols.size(); 
		
		System.out.println("Selected web table has " +iRowsCount + " Rows and " +iColsCount+ " Columns");     
		//System.out.println();      


		/*for(WebElement ele:report_list)
		{
			 String report_data=ele.getText();
			//System.out.println(report_data);
		     
			

		}*/
		
	FileOutputStream fos = new FileOutputStream("C:\\Users\\Acer\\Desktop\\selenium\\exceldata\\attendance.xlsx");                                 

		XSSFWorkbook wkb = new XSSFWorkbook();       
		XSSFSheet sheet1 = wkb.createSheet("Total"); 

		for (int i=1;i<=31;i++)      
		{               
		XSSFRow excelRow1 = sheet1.createRow(i);
		for (int j=1; j<=8;j++)                    
		{           
		if (i==1)       
		{           
		WebElement val= driver.findElement(By.xpath("//*[@id=\"main_AttendanceDetailsWrapper\"]/table/tbody/tr["+i+"]/th["+j+"]/span"));             
		String  a = val.getText();            
		//System.out.print(a);                        
		             
		XSSFCell excelCell = excelRow1.createCell(j);  
		excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);
		excelCell.setCellValue(a);  

		//wkb.write(fos);       
		}       
		else        
		{  
			//*[@id="main_AttendanceDetailsWrapper"]/table/tbody/tr[30]/td[2]
        if((i!=25 && j!=2))
        {
		WebElement val= driver.findElement(By.xpath("//*[@id=\"main_AttendanceDetailsWrapper\"]/table/tbody/tr["+i+"]/td["+j+"]/span"));
		
		String a = val.getText();  
		//System.out.print(a);                            
		//XSSFRow excelRow1 = sheet1.createRow(i);             
		XSSFCell excelCell = excelRow1.createCell(j);                      
		excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);                   
		excelCell.setCellValue(a);   

		//wkb.write(fos);       
		}  
		}
		}               
		//System.out.println();     
		}     
		fos.flush();     
		wkb.write(fos); 
		
		wkb.close();
		fos.close();  
		
		on_time();
		read_file();
		
	}
	
	public void on_time() throws IOException
	{
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Acer\\Desktop\\selenium\\exceldata\\attendance.xlsx");                                 

		XSSFWorkbook wkb = new XSSFWorkbook();       
		XSSFSheet sheet2 = wkb.createSheet("Total_Late_in");
		int count=0;
		for (int i=1;i<=31;i++)      
		{               
		XSSFRow excelRow1 = sheet2.createRow(i);
		for (int j=1; j<=8;j++)                    
		{           
		if (i==1)       
		{           
		WebElement val= driver.findElement(By.xpath("//*[@id=\"main_AttendanceDetailsWrapper\"]/table/tbody/tr["+i+"]/th["+j+"]/span"));             
		String  a = val.getText();            
		//System.out.print(a);                        

			             
		XSSFCell excelCell = excelRow1.createCell(j);  
		excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);
		excelCell.setCellValue(a);  
		
		//wkb.write(fos);       
		}       
		else        
		{  
			
			
        if((i!=25 && j!=2))
        {
	    WebElement val= driver.findElement(By.xpath("//*[@id=\"main_AttendanceDetailsWrapper\"]/table/tbody/tr["+i+"]/td["+j+"]/span"));
		String a = val.getText(); 
		if(a.startsWith("Late In by"))
		{
			
		//System.out.print(a);                            
		//XSSFRow excelRow1 = sheet1.createRow(i);             
		XSSFCell excelCell = excelRow1.createCell(j);                      
		excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);                   
		excelCell.setCellValue(a);
		
		}
		//wkb.write(fos);       
		}  
		}
		}

		//System.out.println();     
		}     
		fos.flush();  
		count++;
		System.out.println("The total record is :" + count);
		wkb.write(fos);
		}
       
	
		
	

	// read excel file
	
public void read_file()
{
	try {
		  // Specify the path of file
		  File src=new File("C:\\Users\\Acer\\Desktop\\selenium\\exceldata\\attendance.xlsx");
		 
		   // load file
		   FileInputStream fis=new FileInputStream(src);
		 
		   // Load workbook
		   XSSFWorkbook wb=new XSSFWorkbook(fis);
		   
		   // Load sheet- Here we are loading first sheetonly
		      XSSFSheet sh1= wb.getSheetAt(0);
		 
		  // getRow() specify which row we want to read.
		 
		  // and getCell() specify which column to read.
		  // getStringCellValue() specify that we are reading String data.
		 
		 
		 System.out.println("The total number of coloumn:" + sh1.getLastRowNum());
		
		 
		  } catch (Exception e) {
		 
		   System.out.println(e.getMessage());
		 
		  }
		  
		 }
		 
	}



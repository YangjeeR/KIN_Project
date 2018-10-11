package Events;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class excel_date {
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	
	
	public excel_date(String filepath)
	{
		try
		{
			File src=new File(filepath);
			FileInputStream fis =new FileInputStream(src);
			wb=new XSSFWorkbook(fis);
			
			
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public int getRowCount(int sheetIndex)
	{
		int row=wb.getSheetAt(sheetIndex).getLastRowNum();
		row=row+1;
		return row;
		
		
	}

	
	public String getData(int sheetNumber,int row,int col)
	{
		sheet1=wb.getSheetAt(sheetNumber);
		String data=sheet1.getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	
	
}

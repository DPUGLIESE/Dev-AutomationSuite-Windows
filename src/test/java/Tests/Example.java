package Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Main.LoadSite;
import Main.MainSuite;
import Main.SiteData;

public class Example extends MainSuite {

	@BeforeClass
	@Parameters({"webDriver"})
	public void before(String webDriver) throws UnknownHostException{
		
		openDriver(webDriver);
		
	}
	
	@Test
	@Parameters("webDriver")
	public void test(String webDriver) throws InterruptedException, IOException, AWTException{
		
		Sheet sheetIN = sheets.get(0);
		Sheet sheetOUT = sheets.get(1);
		
		int i = 1;
		int rows = 0;
		
		if (sheetOUT.getLastRowNum() > 1){
			for (int index = sheetOUT.getLastRowNum(); index >= sheetOUT.getFirstRowNum(); index--) {
				sheetOUT.removeRow(sheetOUT.getRow(index));
			}
		}
		
		Row row = sheetOUT.createRow((short) rows);
		Cell cellA = row.createCell((short) 0);
		Cell cellB = row.createCell((short) 1);
		
		cellA.setCellValue("URL");
		cellB.setCellValue("SourceCode");

		Reporter.log("<table style=\"border-collapse:collapse;\">");
		
		while (!sheetIN.getRow(i).getCell(0).getStringCellValue().equals("END")) {
			
			System.out.println("Row: "+i );

			SiteData data  = LoadSite.getUrlData(sheetIN.getRow(i).getCell(0).getStringCellValue(), Driver, false);
			
			rows ++;
			
			Row row1 = sheetOUT.createRow((short) rows);
			Cell cellA1 = row1.createCell((short) 0);
			Cell cellB1 = row1.createCell((short) 1);
			
			cellA1.setCellValue(data.getUrl());
			if (data.getSourceCode().length() > 100){
				cellB1.setCellValue("Source code lenght is "+data.getSourceCode().length()+".");
			}
			
			Reporter.log("<tr style=\"border:2px solid black;\"><th style=\"border:2px solid black;\">"+webDriver+"</th><th style=\"border:2px solid black;\">"+data.getUrl()+"</th><th style=\"border:2px solid black;\">Source code lenght is "+data.getSourceCode().length()+".</th></tr>");
			
			System.out.println("Source code tored from: "+ data.getUrl());
		
			i++;
		
		}
		Reporter.log("</table>");
		
	}
	
	@AfterClass
	@Parameters("webDriver")
	public void after(String webDriver){
		
		closeDriver(webDriver);
		
	}

}


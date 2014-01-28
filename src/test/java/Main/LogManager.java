package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Parameters;

public class LogManager {
		
/////////////////////////////////////////////////////////////XLS FILE/////////////////////////////////////////////////////////////////////
	
	@Parameters({"xlsDirectory"})
	public static Workbook openXLS(String xlsDirectory) throws InvalidFormatException, FileNotFoundException, IOException{
		
		return WorkbookFactory.create(new FileInputStream(xlsDirectory));
		
	}
	
	@Parameters({"sheetsToWork"})
	public static ArrayList<Sheet> getSheets(String sheetsToWork,Workbook XLS) throws InvalidFormatException, FileNotFoundException, IOException{
		
		ArrayList<Sheet> sheets = new ArrayList<Sheet>();
		
		String[] sheetNames = sheetsToWork.split(";");
		System.out.println("Sheets to test:");
		
		for (String sheetname:sheetNames){
			
			sheets.add(XLS.getSheet(sheetname));
			System.out.println(sheetname);
		
		}
		
		return sheets;
		
	}
	
	@Parameters({"xlsDirectory"})
	public static void closeXLS(String xlsDirectory, Workbook XLS) throws IOException{
		
		FileOutputStream fileOut = new FileOutputStream(xlsDirectory);
		XLS.write(fileOut);     
		fileOut.close();
		
	}
	
}

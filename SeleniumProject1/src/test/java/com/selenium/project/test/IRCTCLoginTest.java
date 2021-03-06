package com.selenium.project.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.project.pages.IRCTCFlightSearchPage;
import com.selenium.project.pages.IRCTCLoginPage;
@Listeners({com.selenium.project.test.TestListener.class})
public class IRCTCLoginTest {
	
	private  XSSFSheet ExcelWSheet;
	 private  XSSFWorkbook ExcelWBook;
	 private  XSSFCell Cell;

	 static WebDriver driver;
	 @Test(dataProvider="dp")
	public void testIRCTC(String a,String b) throws InterruptedException {
		 driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		IRCTCLoginPage login = new IRCTCLoginPage(driver);
		login.clickOnFlight();
		String pw=driver.getWindowHandle();
		login.windowSwitch(pw);
		Select select=null;
		IRCTCFlightSearchPage searchPage = new IRCTCFlightSearchPage(driver);
		Thread.sleep(3000);;
		Assert.assertTrue(searchPage.checkOneWayRadio());
		Assert.assertFalse(searchPage.checkTwoWayRadio());
		searchPage.enterSource(a);
		searchPage.enterDestination(b);
		searchPage.selectDate();
		searchPage.selectAdult(select, 1);
		searchPage.selectChild(select, 1);
		searchPage.selectInfant(select, 1);
		searchPage.clickOnSearchFlight();
		int number= searchPage.displayFlight();
		Assert.assertTrue(number>0 && number<100);
		driver.quit();
	}
	
	@DataProvider
	 public Object[][] dp(){
	  Object[][] tabArray = null;
	  try {
	   FileInputStream ExcelFile = new FileInputStream("Flight_source_latest.xlsx");
	   // Access the required test data sheet
	   ExcelWBook = new XSSFWorkbook(ExcelFile);
	   ExcelWSheet = ExcelWBook.getSheetAt(0);
	   int startRow = 1;
	   int startCol = 0;
	   int ci, cj;
	   int totalRows = ExcelWSheet.getLastRowNum();
	   System.out.println("TR = "+totalRows);
	   
	   int totalCols = ExcelWSheet.getRow(1).getLastCellNum();
	   System.out.println("TC = "+totalCols);
	   tabArray = new String[totalRows][totalCols];
	   ci = 0;
	   for (int i = startRow; i <= totalRows; i++, ci++) {
	    cj = 0;
	    for (int j = startCol; j < totalCols; j++, cj++) {
	     try {
	      tabArray[ci][cj] = getCellData(i, j);
	     } catch (Exception e) {
	      break;
	     }
	    }
	   }

	  }

	  catch (FileNotFoundException e) {
	   e.printStackTrace();
	  }

	  catch (IOException e) {
	   e.printStackTrace();
	  }
	  return (tabArray);
	 }

	 public Object getCellData(int RowNum, int ColNum) throws Exception {

	  try {

	   Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

	   int dataType = Cell.getCellType();

	   if (dataType == 3) {

	    return "";

	   } else if (dataType == 0) {
	    Object CellData = Cell.getRawValue();
	    return CellData;

	   } else {
	    Object CellData = Cell.getStringCellValue();
	    return CellData;
	   }

	  } catch (Exception e) {

	   System.out.println(e.getMessage());

	   throw (e);

	  }
	 }
	 
}

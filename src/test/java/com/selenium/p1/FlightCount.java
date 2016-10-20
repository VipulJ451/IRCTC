package com.selenium.p1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import  com.selenium.p1.Loginpage;
import com.selenium.p1.Searchpage;


public class FlightCount {

 @Test
 public void testIRCTC() throws InterruptedException {
  WebDriver driver=new FirefoxDriver();
  driver.manage().window().maximize();
  driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  Loginpage login = new Loginpage(driver);
  login.clickOnFlight();
  String pw=driver.getWindowHandle();
  login.windowSwitch(pw);
  Select select=null;
  Searchpage searchPage = new Searchpage(driver);
  Thread.sleep(3000);
  searchPage.enterSource("delhi");
  searchPage.enterDestination("Jaipur");
  searchPage.selectDate();
  searchPage.selectAdult(select, 1);
  searchPage.selectChild(select, 1);
  searchPage.selectInfant(select, 1);
  searchPage.clickOnSearchFlight();
  searchPage.displayFlight();
  driver.quit();
 }
}
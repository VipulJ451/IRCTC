package com.selenium.p1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Searchpage {
 static WebDriver driver;
 
 private By sourcelocation=By.xpath("//input[@id='origin']");
 private By destinationlocation =By.xpath("//input[@id='destination']");
 private By datebutton =By.xpath("//input[@name='departDate']/following-sibling::img");
 private By date =By.xpath("//div[@id='ui-datepicker-div']//a[text()='30']");
 private By numberofadult= By.xpath("//select[@id='noOfAdult']");
 private By numberofchild=By.xpath("//select[@id='noOfChild']");
 private By searchButton =By.xpath("//div[@id='showdometic']/div[@class='srchbtn']");
 private By flightlist=By.className("onewayflightinfo");
 
 public Searchpage(WebDriver driver){
  this.driver=driver;
 }

 
 public void enterPickup(String pickupJourney) throws InterruptedException
 {
	
  driver.findElement(sourcelocation).sendKeys(pickupJourney);
  Thread.sleep(2000);
  driver.findElement(sourcelocation).sendKeys(Keys.TAB);
 }
 
 public void enterDestination(String destinationJourney) throws InterruptedException
 {
  driver.findElement(destinationlocation).sendKeys(destinationJourney);
  Thread.sleep(2000);
  driver.findElement(destinationlocation).sendKeys(Keys.TAB);
 }
 public void selectDate()
 {
  driver.findElement(datebutton).click();
  driver.findElement(date).click();
 }
 public void selectAdult(Select select, int index)
 {
  select = new Select(driver.findElement(numberofadult));
  select.selectByIndex(index);
 }
 public void selectChild(Select select, int index)
 {
  select = new Select(driver.findElement(numberofchild));
  select.selectByIndex(index);
 }
 public void clickOnSearchFlight()
 {
  driver.findElement(searchButton).click();
 }
 public void displayFlight()
 {
  List<WebElement> list = driver.findElements(flightlist);
  System.out.println("Total no of flights: "+list.size());
  int c=list.size();
  Assert.assertTrue(c>100);
 }
}
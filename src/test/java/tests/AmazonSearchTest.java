package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AmazonHomePage;
import utilities.BrowserUtils;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AmazonSearchTest {
	
	BrowserUtils utils;
	AmazonHomePage amazonHP;
	
  @Test
  public void amazonSearchTest() throws InterruptedException {
	  Driver.getDriver().get(PropertiesReader.getProperty("amazonURL"));
	  amazonHP = new AmazonHomePage();
	  amazonHP.searchBox.sendKeys("coffee mug");
	  amazonHP.searchButton.click();
	  Thread.sleep(4000);
	  //Assert.fail();
  }
  
  @Test 
  public void dropDownTest() {
	  Driver.getDriver().get(PropertiesReader.getProperty("amazonURL"));
	  utils = new BrowserUtils();
	  amazonHP = new AmazonHomePage();
	  utils.selecyByVisibleText(amazonHP.departmentsDropdown, "Amazon Fresh");
  }
  
  @Test //(dependsOnMethods = "amazonSearchTest")
  public void searchResultVerify() throws InterruptedException {
	  Driver.getDriver().get(PropertiesReader.getProperty("amazonURL"));
	  utils = new BrowserUtils();
	  amazonHP = new AmazonHomePage();
	  
	  amazonHP.searchBox.sendKeys("coffee mug");
	  amazonHP.searchButton.click();
	  Thread.sleep(3000);
	  System.out.println("Number of items: " + amazonHP.searchItemPrices.size());
	  
	  for(WebElement itemPrice : amazonHP.searchItemPrices) {
		  String price = itemPrice.getText();
		  System.out.println(price);
	  }
	  
  }
  
  public void softAssertTest() {
	  SoftAssert softassert = new SoftAssert();
	  System.out.println("This is before hard assert.");
	  
	  Assert.assertTrue(false); 	
	  //if this is false = this line of code fails, rest of this line of code will not get executed.
	  System.out.println("This is after hard assert."); 
	  
	  softassert.assertTrue(false);
	  //if this is false = this line of code fails, rest 
	  System.out.println("This is after soft assert."); 
	  
	  softassert.assertAll();
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  Driver.getDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  Driver.quitDriver();
  }

}

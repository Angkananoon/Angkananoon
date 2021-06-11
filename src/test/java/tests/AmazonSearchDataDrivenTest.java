package tests;

import org.testng.annotations.Test;

import pages.AmazonHomePage;
import utilities.BrowserUtils;
import utilities.Driver;
import utilities.PropertiesReader;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class AmazonSearchDataDrivenTest {
	
	AmazonHomePage amazonHP;
	BrowserUtils utils;
	
  @Test(dataProvider = "myDataBucket")
  public void test(String input) {
	  Driver.getDriver().get(PropertiesReader.getProperty("amazonURL"));
	  amazonHP = new AmazonHomePage();
	  utils = new BrowserUtils();
	  
	  Assert.assertTrue(amazonHP.searchBox.isDisplayed());
//	  String actualResult = amazonHP.title.getText();
//	  Assert.assertEquals(actualResult, "Amazon.com. Spend less. Smile more.");
	  
	  amazonHP.searchBox.sendKeys(input);
	  amazonHP.searchButton.click();
	  
	  utils.waitUntilElementVisible(amazonHP.searchResultText);
	  
	  String actualSearchResultText = amazonHP.searchResultText.getText();
	  String afterSubStringText = actualSearchResultText.substring(1, actualSearchResultText.length()-1);
	  
	  System.out.println("Actual search Text: " + afterSubStringText + " / Expected Text: " + input);
	  
	  Assert.assertEquals(afterSubStringText, input);
	  
  }
  
  @DataProvider
  public String[] myDataBucket() {
	  String[] myData = new String[5];
	  myData[0] = "coffee mug";
	  myData[1] = "pretty coffee mug";
	  myData[2] = "cool coffee mug";
	  myData[3] = "pretty coffee mug";
	  myData[4] = "ugly coffee mug";
	  
	  return myData;
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

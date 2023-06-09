package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Enterprise_Customers {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Enterprise_Customers");

	float unknownValue = 00;

		@AfterMethod
		public void tearDown(ITestResult result)
		{
			if(ITestResult.FAILURE == result.getStatus())
			{
				Utility.captureScreenshot(driver, result.getName());
			}
		}
			
		@AfterClass
		public void flushTest() throws Exception
		{
			Thread.sleep(2000);
			rep.endTest(test);
			rep.flush();
		}

		@Test(priority=1)
		public void login() throws Exception
		{
			Thread.sleep(2000);
			//Call the chrome driver
			System.setProperty("webdriver.chrome.driver",Utility.getProperty("Chrome_Driver_Path"));
			//Open the Chrome window
			driver = new ChromeDriver();
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			//Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{		Browser_Account_Level_User a = new Browser_Account_Level_User();
		a.Logout(driver, test);
		}
	
		@Test(priority = 100)
		public void calling() throws Exception
		{
			Thread.sleep(10000);
			try
			{
					Thread.sleep(1000);System.out.println("Minimize Chat Icon");
					driver.findElement(By.id("zsiq_minimize")).click();			
			}
			catch(Exception e)
			{
				System.out.println("Minimize Chat Icon Missing");
			}
			//ViewDashBoardReports a = new ViewDashBoardReports();
			Thread.sleep(1000);
			Enterprise_Customers_openpage(driver);
			Enterprise_Customer_add_(driver);
			Enterprise_Customer_edit(driver);
			Enterprise_Customer_view_page(driver);
			Enterprise_Customer_delete(driver);
			Enterprise_Customer_cancelButton(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=16)
		public void Enterprise_Customers_openpage(WebDriver driver) throws Exception
		{

	        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			/*//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();

			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Customers']"));
			//Scroll the page till the Customers option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			Thread.sleep(3000);
	        //Click the Group Option		
			driver.findElement(By.xpath("//span[.='Customers']")).click();
			*/
			//Enter the URl
			Thread.sleep(3000);
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"customers");
			
			Thread.sleep(5000);
			try
			{
			//Check CashierOut Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1417, 1))).getText().equalsIgnoreCase("Customers"))
			{
				test.log(LogStatus.PASS, "Customers page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Customers page loaded Failed");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			}
			catch(Exception e)
			{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
			}
			wb.close();
			Thread.sleep(3000);

		}
		
		@Test(enabled=false,priority=17)
		public void Enterprise_Customer_add_(WebDriver driver) throws Exception
		{
		    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis); 
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			Thread.sleep(5000);
			//Click the add customer button
			driver.findElement(By.xpath(excel.getData(3, 1418, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1419, 1))).getText().equalsIgnoreCase("New Customer"))
			{
				test.log(LogStatus.PASS, "New customer form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New customer form loaded fail");
			}
			Thread.sleep(2000);
			
			//Clear the first name filed
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the first name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_FName"));
			
			//Clear the Last name field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the Last name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_LName"));
			
			//clear the mail id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the mail id
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Email"));
			
			//Clear the phone number field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phone number
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Phone"));
			
			
			//choose the male option for Gender
			driver.findElement(By.xpath(excel.getData(3, 1420, 1))).click();
 
			//Clear the date of birth field
			driver.findElement(By.name(excel.getData(3, 1421, 3))).clear();
			//Enter the Date of Birth
			driver.findElement(By.name(excel.getData(3, 1421, 3))).sendKeys("01-Jan-1990");
			
			//Clear the Gate Code field
			driver.findElement(By.name(excel.getData(3, 1422, 3))).clear();
			//Enter the Gate Code
			driver.findElement(By.name(excel.getData(3, 1422, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Gatecode"));
			 
			//Clear the anniversary date field
			driver.findElement(By.name(excel.getData(3, 1423, 3))).clear();
			//Enter the anniversary date
			driver.findElement(By.name(excel.getData(3, 1423, 3))).sendKeys("01-Jan-2000");
			
			//Clear the Customer Id field
			driver.findElement(By.xpath(excel.getData(3, 1424, 1))).clear();
			//Enter the Customer Id
			driver.findElement(By.xpath(excel.getData(3, 1424, 1))).sendKeys(Utility.getProperty("Enterprise_Customer_Customer_Id"));
			
			//Clear the Notes option
			driver.findElement(By.xpath(excel.getData(3, 1425, 1))).clear();
			//Enter the Notes
			driver.findElement(By.xpath(excel.getData(3, 1425, 1))).sendKeys("Note");
			
			//Clear the Address Line 1
			driver.findElement(By.name(excel.getData(3, 288, 3))).clear();
			//Enter the Address Line 1
			driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys("38/21, Navalar Street, Ranianna Nagar");
			
			//Clear the Address Line 2
			driver.findElement(By.name(excel.getData(3, 289, 3))).clear();
			//Enter the Address Line 2
			driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys("Arumbakkam");
			
			//Clear the City field
			driver.findElement(By.name(excel.getData(3, 290, 3))).clear();
			//Enter the City
			driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys("Chennai");
			
			//Clear the State field
			driver.findElement(By.name(excel.getData(3, 291, 3))).clear();
			//Enter the State
			driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys("Tamil Nadu");
			
			//Clear the Country field
			driver.findElement(By.name(excel.getData(3, 1426, 3))).clear();
			//Enter the Country
			driver.findElement(By.name(excel.getData(3, 1426, 3))).sendKeys("India");
			
			//Clear the Nationality
			driver.findElement(By.name(excel.getData(3, 1427, 3))).clear();
			//Enter the Nationality
			driver.findElement(By.name(excel.getData(3, 1427, 3))).sendKeys("Indian");
			
			//Clear the Zipcode field
			driver.findElement(By.name(excel.getData(3, 292, 3))).clear();
			//Enter the Zipcode
			driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys("600106");
			
			Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.id(excel.getData(3, 1771, 2))).sendKeys(Utility.getProperty("Customer_Img"));
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		//	Thread.sleep(3500); 
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Customer saved successfully"))
			{
				test.log(LogStatus.PASS, "New Customer saved successfully");
			}
			else
			{ 
				test.log(LogStatus.FAIL, "New Customer saved fail"); 
			}  wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=18)
		public void Enterprise_Customer_edit(WebDriver driver) throws Exception
		{
		    		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);

			//Enter the required group name
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Customer_FName")+" "+ Utility.getProperty("Enterprise_Customer_LName"));
			
			//Click the Search button
			//driver.findElement(By.xpath("//a[@class='btn customer-search-icon']")).click();
			
			Thread.sleep(2000); 
			//Click the Edit button
			driver.findElement(By.xpath(excel.getData(3, 1775, 1))).click();
			
			//Clear the first name filed
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the first name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_FName")+"1");
			
			//Clear the Last name field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the Last name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_LName")+"1");
			
			//clear the mail id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the mail id
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Email"));
			
			//Clear the phone number field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phone number
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Phone")+"1");
			
			
			//choose the male option for Gender
			driver.findElement(By.xpath(excel.getData(3, 1420, 1))).click();

			//Clear the date of birth field
			driver.findElement(By.name(excel.getData(3, 1421, 3))).clear();
			//Enter the Date of Birth
			driver.findElement(By.name(excel.getData(3, 1421, 3))).sendKeys("02-Jan-1990");
			
			//Clear the Gate Code field
			driver.findElement(By.name(excel.getData(3, 1422, 3))).clear();
			//Enter the Gate Code
			driver.findElement(By.name(excel.getData(3, 1422, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Gatecode")+"1");
			
			//Clear the anniversary date field
			driver.findElement(By.name(excel.getData(3, 1423, 3))).clear();
			//Enter the anniversary date
			driver.findElement(By.name(excel.getData(3, 1423, 3))).sendKeys("02-Jan-2000");
			
			//Clear the Customer Id field
			driver.findElement(By.xpath(excel.getData(3, 1424, 1))).clear();
			//Enter the Customer Id
			driver.findElement(By.xpath(excel.getData(3, 1424, 1))).sendKeys(Utility.getProperty("Enterprise_Customer_Customer_Id")+"1");
			
			//Clear the Notes option
			driver.findElement(By.xpath(excel.getData(3, 1425, 1))).clear();
			//Enter the Notes
			driver.findElement(By.xpath(excel.getData(3, 1425, 1))).sendKeys("Notes");
			
			
			//Click the update button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		//	Thread.sleep(3500);
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Customer updated successfully"))
			{
				test.log(LogStatus.PASS, "New Customer updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Customer updated fail");
			}
			wb.close();
			Thread.sleep(3000);
			
		}
		
		@Test(enabled=false,priority=21)
		public void Enterprise_Customer_delete(WebDriver driver) throws Exception
		{
		    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);

			//Enter the required group name
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Customer_FName")+"1 "+ Utility.getProperty("Enterprise_Customer_LName")+"1");
			
			//Click the Search button
			//driver.findElement(By.xpath("//a[@class='btn customer-search-icon']")).click();

			
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			driver.findElement(By.linkText(excel.getData(3, 151, 3))).click();
			Thread.sleep(5000);
			
			try
			{
				WebElement ele=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
				WebDriverWait wait=new WebDriverWait(driver,60);
				
				if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Group inactivated successfully"))
				{
					test.log(LogStatus.PASS, "New Group inactivated successfully");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "New Group inactivated fail");
			}
			Thread.sleep(3000);
			
	/*		//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText(excel.getData(3, 151, 3))).click();
			Thread.sleep(3000);
			
			//Check the modifier activated or not
			if(driver.findElement(By.xpath("//span[.='Group activated successfully']")).getText().equalsIgnoreCase("Group activated successfully"))
			{
				test.log(LogStatus.PASS, "Group activated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Group activated Failed");
			}
			
			Thread.sleep(5000);*/
			wb.close();
		}
		
		@Test(enabled=false,priority=19)
		public void Enterprise_Customer_cancelButton(WebDriver driver) throws Exception
		{
		    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			Thread.sleep(2000);
			//Clear the search field
			//driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Thread.sleep(1000);

			//Enter the required group name
			//driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Customer_FName")+"1 "+ Utility.getProperty("Enterprise_Customer_LName")+"1");
			
			//Click the Search button
			//driver.findElement(By.xpath("//a[@class='btn customer-search-icon']")).click();
			
			
			//Thread.sleep(2000);
			//Click the Edit button
			//driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			
			Thread.sleep(2000);
			//Click the add customer button
			driver.findElement(By.xpath(excel.getData(3, 1418, 1))).click();
			
			Thread.sleep(3000);

			//Clear the first name filed
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the first name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_FName")+"1");
			
			//Clear the Last name field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the Last name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_LName")+"1");
			
			//clear the mail id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the mail id
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Email"));
			
			//Clear the phone number field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phone number
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys(Utility.getProperty("Enterprise_Customer_Phone")+"1");
			
			Thread.sleep(3000);
			//Click the close button
			driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
			
			Thread.sleep(2000);
			if(driver.findElement(By.xpath(excel.getData(3, 1418, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Cancel button working fine");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cancel button not working");
			}
			wb.close();
		}
		
		@Test(enabled=false,priority=20)
		public void Enterprise_Customer_view_page(WebDriver driver) throws Exception
		{
		    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);

			//Enter the required group name
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Customer_FName")+"1 "+ Utility.getProperty("Enterprise_Customer_LName")+"1");
			
			//Click the Search button
			//driver.findElement(By.xpath("//a[@class='btn customer-search-icon']")).click();
			
			Thread.sleep(1500);
			
			//Click the View button
			driver.findElement(By.xpath(excel.getData(3, 642, 1))).click();
			
			Thread.sleep(2000);
			
			
			Thread.sleep(5000);
			//Refresh button
			driver.findElement(By.xpath(excel.getData(3, 1430, 1))).click();
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			
			Thread.sleep(3000);
			//Check whether the Total Spent report is available or not
			if(driver.findElement(By.xpath(excel.getData(3, 1431, 1))).getText().equalsIgnoreCase("Total Spent"))
			{
				test.log(LogStatus.PASS, "There is Total Spent Report is available");
			}
			else
			{
				test.log(LogStatus.FAIL, "Total Spent report is not available");
			}
			
			Thread.sleep(3000);
			//Check whether the Weekly Spent report is available or not
			if(driver.findElement(By.xpath(excel.getData(3, 1432, 1))).getText().equalsIgnoreCase("Weekly Spent"))
			{
				test.log(LogStatus.PASS, "There is Weekly Spent Report is available");
			}
			else
			{
				test.log(LogStatus.FAIL, "Weekly Spent report is not available");
			}
			
			Thread.sleep(3000);
		    //Check Whether the NO. OF VISIT and Sale amount Available or not
		    if(driver.findElement(By.xpath(excel.getData(3, 1433, 1))).getText().equalsIgnoreCase("NO. OF VISIT") && driver.findElement(By.xpath(excel.getData(3, 1434, 1))).getText().equalsIgnoreCase("SALE AMOUNT"))
		    {

		    	test.log(LogStatus.PASS, "Number of visit count is : "+driver.findElement(By.xpath(excel.getData(3, 646, 1))).getText());
		    	
		    	test.log(LogStatus.PASS, "Total Sale amount is : "+driver.findElement(By.xpath(excel.getData(3, 647, 1))).getText());
		    }
		    
		    else if(driver.findElement(By.xpath(excel.getData(3, 1433, 1))).getText().equalsIgnoreCase("NO. OF VISIT") || driver.findElement(By.xpath(excel.getData(3, 1434, 1))).getText().equalsIgnoreCase("SALE AMOUNT"))
		    {
		    	if(driver.findElement(By.xpath(excel.getData(3, 1433, 1))).getText().equalsIgnoreCase("NO. OF VISIT"))
		    	{
		    		test.log(LogStatus.PASS, "Number of visit count is : "+driver.findElement(By.xpath(excel.getData(3, 646, 1))).getText());
		    		
		    		test.log(LogStatus.FAIL, "Sale amount field is not available");
		    	}
		    	
		    	else if(driver.findElement(By.xpath(excel.getData(3, 1434, 1))).getText().equalsIgnoreCase("SALE AMOUNT"))
		    	{
		    		test.log(LogStatus.FAIL, "Number of visit field is not available");
		    		
		    		test.log(LogStatus.PASS, "Total Sale amount is : "+driver.findElement(By.xpath(excel.getData(3, 647, 1))).getText());
		
		    	}
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "Number of visit field is not available");
		    	
		    	test.log(LogStatus.FAIL, "Sale amount field is not available");
		    }
		    
			Thread.sleep(3000);
		    //Check Whether the LOYALTY REWARD and LOYALTY VALUE Available or not
		    if(driver.findElement(By.xpath(excel.getData(3, 665, 1))).getText().equalsIgnoreCase("LOYALTY REWARD") && driver.findElement(By.xpath(excel.getData(3, 666, 1))).getText().equalsIgnoreCase("LOYALTY VALUE"))
		    {

		    	test.log(LogStatus.PASS, "LOYALTY REWARD is : "+driver.findElement(By.xpath(excel.getData(3, 667, 1))).getText());
		    	
		    	test.log(LogStatus.PASS, "LOYALTY VALUE is : "+driver.findElement(By.xpath(excel.getData(3, 668, 1))).getText());
		    }
		    
		    else if(driver.findElement(By.xpath(excel.getData(3, 665, 1))).getText().equalsIgnoreCase("LOYALTY REWARD") || driver.findElement(By.xpath(excel.getData(3, 666, 1))).getText().equalsIgnoreCase("LOYALTY VALUE"))
		    {
		    	if(driver.findElement(By.xpath(excel.getData(3, 665, 1))).getText().equalsIgnoreCase("LOYALTY REWARD"))
		    	{
		    		test.log(LogStatus.PASS, "LOYALTY REWARD is : "+driver.findElement(By.xpath(excel.getData(3, 667, 1))).getText());
		    		
		    		test.log(LogStatus.FAIL, "LOYALTY VALUE field is not available");
		    	}
		    	
		    	else if(driver.findElement(By.xpath(excel.getData(3, 666, 1))).getText().equalsIgnoreCase("LOYALTY VALUE"))
		    	{
		    		test.log(LogStatus.FAIL, "LOYALTY REWARD field is not available");
		    		
		    		test.log(LogStatus.PASS, "LOYALTY VALUE is : "+driver.findElement(By.xpath(excel.getData(3, 668, 1))).getText());
		
		    	}
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "LOYALTY REWARD field is not available");
		    	
		    	test.log(LogStatus.FAIL, "LOYALTY VALUE field is not available");
		    }
		    
			Thread.sleep(3000);
		    //Check Whether the FEEDBACKS Available or not
		    if(driver.findElement(By.xpath(excel.getData(3, 1435, 1))).getText().equalsIgnoreCase("FEEDBACKS"))
		    {
		    	test.log(LogStatus.PASS, "FEEDBACKS field is available");
		    	
		    	test.log(LogStatus.INFO, "Positive Feedback Count is : "+driver.findElement(By.xpath(excel.getData(3, 672, 1))).getText());
		    	
		    	test.log(LogStatus.INFO, "Negative Feedback Count is : "+driver.findElement(By.xpath(excel.getData(3, 1772, 1))).getText());
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "FEEDBACKS field is not available");
		    }
		  
		    List<WebElement> ta = driver.findElements(By.xpath(excel.getData(3, 1773, 1)));
		    
		    if(ta.size() == 1)
		    {
		    	test.log(LogStatus.PASS, "Sales details is not available");
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "Sales details is available");
		    	/*
		    	List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/table/tbody/tr"));
		    	
		    	test.log(LogStatus.PASS, "Sales details  available");
		    	
			    for(int i = 1; i <= rows.size();i++)
			    {
			    	Thread.sleep(1000);
			    	//Click the required check
			    	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/table/tbody/tr["+i+"]/td[1]/a")).click();
			    	
			    	//Check Whether the Send Receipt is enabled or not
			    	if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[1]/div/div[2]/button")).isEnabled())
			    	{
			    	Thread.sleep(1000);
			    	//Click the Send Receipt
			    	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[1]/div/div[2]/button")).click();
			    	
					    	Thread.sleep(1000);
					    	//Check whether the receipt sent successfully or not
					    	if(driver.findElement(By.xpath("//span[.='Email receipt sent successfully']")).getText().equalsIgnoreCase("Email receipt sent successfully"))	
					    	{
					    		test.log(LogStatus.PASS, "Email Receipt sent successfully");
					    	}
					    	
					    	else
					    	{
					    		test.log(LogStatus.FAIL, "Email Receipt sent fail");
					    	}
			    		
			    	}
			    	
			    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			    	test.log(LogStatus.INFO, "The Check number is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[2]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Date is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[3]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Table is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[4]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Seat Number is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[5]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Server Name is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[6]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[7]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[8]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "CC Service Charge is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[9]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[10]/td")).getText());
			    	
			    	test.log(LogStatus.INFO, "Total Tip is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[11]/td")).getText());
			   
			    	//Get the row size of tax table
			    	List<WebElement> taxRow = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[3]/div/div[1]/table/tbody/tr"));
			    	
			    		Thread.sleep(1000);
			    		test.log(LogStatus.PASS, "Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
			    	
			    	//Check whether the Payment summary is available or not
			    	if(driver.findElement(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[3]/div/div[2]/table/tbody/tr[3]/td[5]")) != null)
			    	{
			    		test.log(LogStatus.PASS, "Payment Summary report is available");
			    	}
			    	
			    	else
			    	{
			    		test.log(LogStatus.FAIL, "Payment Summary report is not available");
			    	}
			    	
			    	//Get the row size of order summary table
			    	List<WebElement> orderSummaryRow = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr"));
			    	
			    		Thread.sleep(1000);
			    		test.log(LogStatus.PASS, "Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
		
			    		Thread.sleep(1000);
			    		
			    		for(int j = 0; j <= 8; j++)
			    		{
			    			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			    		}
			    		
			    		Thread.sleep(2000);
			    		//Click the Back button
			    		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[1]/div/div[2]/a")).click();
			    		
			    		Thread.sleep(3000);
			    }
		    */}  

		    
		    driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		    Thread.sleep(2000);
		    driver.findElement(By.xpath(excel.getData(3, 1774, 1))).click();
		    wb.close();
		    Thread.sleep(2000);
		}

}

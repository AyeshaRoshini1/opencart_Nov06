package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass 
{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void test_LoginDDT(String email, String pwd, String exp) 
	{
		logger.info("*** Starting TC_003_LoginDataDrivenTest ***");
		try 
		{
			HomePage hp = new HomePage(driver);
			
			hp.clickMyAccount();
			logger.info("Clicked on My account link");
			
			hp.clickLogin();
			logger.info("Clicked on Login link");

			LoginPage lp = new LoginPage(driver);
			
			lp.setEmail(email);
			logger.info("Email is given");
			
			lp.setPassword(pwd); 
			logger.info("Password is given");
			
			lp.clickLogin();
			logger.info("Clicked on Login");

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage = macc.isMyAccountPageExists(); // this method is created MyAccountPage
			
			if (exp.equals("Valid")) 
			{
				if (targetpage ==  true) 
				{
					macc.clickLogout();
					logger.info("Clicked on Logout");
					Assert.assertTrue(true);
				}
				else 
				{
					logger.info("Test Failed");
					logger.info("*** Finished TC_003_LoginDataDrivenTest ***");
					Assert.assertTrue(false);
				}
			}

			if (exp.equals("Invalid")) 
			{
				if (targetpage ==  true)
				{
					MyAccountPage myaccpage = new MyAccountPage(driver);
					myaccpage.clickLogout();
					logger.info("Clicked on Logout");
					logger.info("Test Failed");
					logger.info("*** Finished TC_003_LoginDataDrivenTest ***");
					Assert.assertTrue(false);
				}
				else 
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Test Failed");
			Assert.fail();
		}
		logger.info("*** Finished TC_003_LoginDataDrivenTest ***");
	}
}
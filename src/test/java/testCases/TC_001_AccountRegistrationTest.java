package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
	@Test(groups = {"Master", "Regression"})
	public void test_Account_Registration() throws InterruptedException
	{
		logger.debug("Application logs......");
		logger.info("***  Starting TC_001_AccountRegistrationTest ***");
		try
		{
			HomePage hp = new HomePage(driver);
			
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			
			hp.clickRegister();
			logger.info("Clicked on Register link");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			String firstName = randomeString();
			regpage.setFirstName(firstName.toUpperCase());
			logger.info("First Name field is given");
			
			String secondName = randomeString();
			regpage.setLastName(secondName.toUpperCase());
			logger.info("Second Name field is given");
			
			regpage.setEmail(firstName + secondName + "@gmail.com");
			logger.info("Email field is given");
			
			regpage.setTelephone(randomeNumber());
			
			String password = randomAlphaNumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			logger.info("Password is given");
			
			regpage.setPrivacyPolicy();
			
			regpage.clickContinue();
			logger.info("Clicked on Continue");
			
			String confmsg = regpage.getConfirmationMsg();
			logger.info("Validating Expected Message");
			
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Test Failed");
			//Assert.assertEquals(confmsg, "Register Account", "Test Failed");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			Assert.fail();
		}
		logger.info("***  Finished TC_001_AccountRegistrationTest ***");
	}
}
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * As a user,
 * I would like to be able to log in
 * So that I can access and manage my account
 * 
 * Total Scenarios in User Story: 4
 */

public class SteamTestUserStoryLogIn {
	
	static WebDriver driver = new FirefoxDriver();
	
	/** Start at the home page for each test */
	@Before
	public void setUp() throws Exception {
		driver.get("http://store.steampowered.com");
	}
	
	
	/**
	 * Scenario 1:
	 * Given that when I am on the main page
	 * When I look at the header
	 * Then I see a button to log in
	 */
	@Test
	public void logInButton() {
		try {
			//If page is wide enough, there will be a login link there
			driver.findElement(By.linkText("login"));
		}
		catch (NoSuchElementException nseex) {
			try {
				//However if the page is not wide enough (such as mobile device), then there 
				//is a drop menu to access the options that are removed. Login is one of these.
				WebElement e = driver.findElement(By.id("responsive_menu_logo"));
				e.click();
				driver.findElement(By.linkText("Login"));
			}
			catch (NoSuchElementException nseex1) {
				fail();
			}
		}
	}
	
	/**
	 * Scenario 2:
	 * Given that I'm on the log in screen
	 * When I enter the wrong user name
	 * Then I should not log in
	 */
	@Test
	public void logInWrongUsername() {
		String loginURL = "https://steamcommunity.com/login/home/?goto=0#";
		driver.get(loginURL);
		try {
			WebElement user = driver.findElement(By.name("username"));
			WebElement pass = driver.findElement(By.name("password"));
			user.sendKeys("cme34CS1632#");
			pass.sendKeys("Pittsburgh1#");
			WebElement button = driver.findElement(By.id("SteamLogin"));
			button.click();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			try {
				//Wait for login to complete, page should not change
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cme34CS1632")));
			}
			catch (TimeoutException tex) {
				assertEquals(loginURL, driver.getCurrentUrl());
			}
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
		driver.manage().deleteAllCookies();
	}
	
	/**
	 * Scenario 3:
	 * Given that I'm on the log in screen
	 * When I enter the wrong password
	 * Then I should not log in
	 */
	@Test
	public void logInWrongPassword() {
		String loginURL = "https://steamcommunity.com/login/home/?goto=0#";
		driver.get(loginURL);
		try {
			WebElement user = driver.findElement(By.name("username"));
			WebElement pass = driver.findElement(By.name("password"));
			user.sendKeys("cme34CS1632");
			pass.sendKeys("Pittsburgh1@");
			WebElement button = driver.findElement(By.id("SteamLogin"));
			button.click();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			try {
				//Wait for login to complete, page should not change
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cme34CS1632")));
			}
			catch (TimeoutException tex) {
				assertEquals(loginURL, driver.getCurrentUrl());
			}
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 4:
	 * Given that I'm on the log in screen
	 * When I provide the correct credentials
	 * Then I should be logged in
	 */
	@Test
	public void logInCorrectCredentials() {
		String loginURL = "https://steamcommunity.com/login/home/?goto=0#";
		driver.get(loginURL);
		try {
			WebElement user = driver.findElement(By.name("username"));
			WebElement pass = driver.findElement(By.name("password"));
			user.sendKeys("cme34CS1632");
			pass.sendKeys("Pittsburgh1#");
			WebElement button = driver.findElement(By.id("SteamLogin"));
			button.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			try {
				//Wait for login to complete, when it does a link with the user name should be on page
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("cme34CS1632")));
				//Log out for tests that rely on not being logged in
				try {
					//If page is wide enough, there will be a drop menu with logout
					WebElement menu = driver.findElement(By.id("account_pulldown"));		
					menu.click();
					WebElement logout = driver.findElement(By.linkText("Logout"));
					logout.click();
				}
				catch (ElementNotVisibleException envex) {
					//However if the page is not wide enough (such as mobile device), then there 
					//is a drop menu to access the options that are removed. The drop menu is one of these.
					WebElement e = driver.findElement(By.id("responsive_menu_logo"));		
					e.click();
					WebElement logout = driver.findElement(By.xpath("//div[.='Change User']"));
					logout.click();
				}
			}
			catch (TimeoutException tex) {
				try {
					//Check if Captcha showed up instead of logging in
					driver.findElement(By.id("error_display"));
				}
				catch (NoSuchElementException nseex) {
					fail();
				}
			}
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
}

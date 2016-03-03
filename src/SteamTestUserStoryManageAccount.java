import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * As a user,
 * I would like to be able view and manage my account
 * So that I can view and edit basic information
 * 
 * Total Scenarios in User Story: 7
 */

public class SteamTestUserStoryManageAccount {
	
	static WebDriver driver = new FirefoxDriver();
	
	/** Start at the home page for each test */
	@Before
	public void setUp() throws Exception {
		driver.get("http://store.steampowered.com");
		try {
			//Check if already logged in
			//If page is wide enough, there will be a login link there
			driver.findElement(By.id("account_pulldown"));	
		}
		catch (NoSuchElementException nseex) {
			try {
				//However if the page is not wide enough (such as mobile device), then there 
				//is a drop menu to access the options that are removed. Login is one of these.
				driver.findElement(By.xpath("a[.='cme34CS1632']"));
			}
			catch (NoSuchElementException nseex1) {
				//If not logged in, then do it
				driver.get("https://steamcommunity.com/login/home/?goto=0#");
				WebElement user = driver.findElement(By.name("username"));
				WebElement pass = driver.findElement(By.name("password"));
				user.sendKeys("cme34CS1632");
				pass.sendKeys("Pittsburgh1#");
				WebElement button = driver.findElement(By.id("SteamLogin"));
				button.click();
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("cme34CS1632")));
			}
		}
	}
	
	/**
	 * Scenario 1:
	 * Given that I am on the main page and logged in
	 * When I click my profile name
	 * Then I should see basic account functions
	 */
	@Test
	public void haveBasicAccountFunctions() {
		try {
			//If page is wide enough, there will be a drop menu with basic account functions
			WebElement e = driver.findElement(By.id("account_pulldown"));		
			e.click();
			driver.findElement(By.linkText("Logout"));
			driver.findElement(By.linkText("Account details"));
			driver.findElement(By.linkText("View profile"));
		}
		catch (ElementNotVisibleException envex) {
			try {
				//However if the page is not wide enough (such as mobile device), then there 
				//is a drop menu to access the options that are removed. The drop menu is one of these.
				WebElement e = driver.findElement(By.id("responsive_menu_logo"));		
				e.click();
				driver.findElement(By.linkText("Account details"));
			}
			catch (NoSuchElementException nseex) {
				fail();
			}
		}
	}
	
	/**
	 * Scenario 2:
	 * Given that I am on the main page and logged in
	 * When I look at my account functions
	 * Then I should have a way to view my profile
	 */
	@Test
	public void viewProfile() {
		try {
			//If page is wide enough, there will be a drop menu with view profile
			WebElement menu = driver.findElement(By.id("account_pulldown"));		
			menu.click();
			WebElement profile = driver.findElement(By.linkText("View profile"));
			profile.click();
		}
		catch (ElementNotVisibleException envex) {
			try {
				//However if the page is not wide enough (such as mobile device), then there 
				//is a drop menu to access the options that are removed. The drop menu is one of these.
				WebElement e = driver.findElement(By.id("responsive_menu_logo"));		
				e.click();
				WebElement profile = driver.findElement(By.xpath("//a[.='cme34CS1632']"));
				profile.click();
			}
			catch (NoSuchElementException nseex) {
				fail();
			}
		}
	}
	
	/**
	 * Scenario 3:
	 * Given that I am on my profile page and logged in
	 * When I am looking at my profile
	 * Then I should have an option to edit it
	 */
	@Test
	public void editProfile() {
		driver.get("http://steamcommunity.com/profiles/76561198288876871");
		try {
			driver.findElement(By.xpath("//span[.='Edit Profile']"));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 4:
	 * Given that I am on my profile page and logged in
	 * When I am looking at my profile
	 * Then I should have an option to view my owned games
	 */
	@Test
	public void viewGames() {
		driver.get("http://steamcommunity.com/profiles/76561198288876871");
		try {
			driver.findElement(By.xpath("//span[.='Games']"));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 5:
	 * Given that I am on my profile page and logged in
	 * When I am looking at my profile
	 * Then I should have a way to view my friends
	 */
	@Test
	public void viewFriends() {
		driver.get("http://steamcommunity.com/profiles/76561198288876871");
		try {
			WebElement e = driver.findElement(By.xpath("//span[.='Friends']"));
			e.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='friendBlockLinkOverlay']")));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 1:
	 * Given that I am on the main page and logged in
	 * When I look at my account functions
	 * Then I should have a way to view account info such as my wallet
	 */
	@Test
	public void viewAccountInfo() {
		try {
			//If page is wide enough, there will be a drop menu with account details
			WebElement menu = driver.findElement(By.id("account_pulldown"));		
			menu.click();
			WebElement account = driver.findElement(By.linkText("Account details"));
			account.click();
		}
		catch (ElementNotVisibleException envex) {
			try {
				//However if the page is not wide enough (such as mobile device), then there 
				//is a drop menu to access the options that are removed. The drop menu is one of these.
				WebElement e = driver.findElement(By.id("responsive_menu_logo"));		
				e.click();
				WebElement account = driver.findElement(By.linkText("Account details"));
				account.click();
			}
			catch (NoSuchElementException nseex) {
				fail();
			}
		}
	}
	
	/**
	 * Scenario 1:
	 * Given that I am on the main page and logged in
	 * When I look at my account functions
	 * Then I should have a way to log out
	 */
	//I don't actually log out to save for risk of running into the Captcha. Instead I just check for the button's presence
	@Test
	public void logout() {
		try {
			//If page is wide enough, there will be a drop menu with logout
			WebElement menu = driver.findElement(By.id("account_pulldown"));		
			menu.click();
			driver.findElement(By.linkText("Logout"));
		}
		catch (ElementNotVisibleException envex) {
			try {
				//However if the page is not wide enough (such as mobile device), then there 
				//is a drop menu to access the options that are removed. The drop menu is one of these.
				WebElement e = driver.findElement(By.id("responsive_menu_logo"));		
				e.click();
				driver.findElement(By.xpath("//div[.='Change User']"));
			}
			catch (NoSuchElementException nseex) {
				fail();
			}
		}
	}
}

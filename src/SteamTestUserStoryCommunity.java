import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * As a user,
 * I would like to be able to access communities
 * So that I can fan content and players that play the games I do
 * 
 * Total Scenarios in User Story: 4
 */

public class SteamTestUserStoryCommunity {
	
	static WebDriver driver = new FirefoxDriver();
	
	/** Start at the home page for each test */
	@Before
	public void setUp() throws Exception {
		driver.get("http://store.steampowered.com");
	}
	
	
	/**
	 * Scenario 1:
	 * Given that I am on the main page
	 * When I look at the header
	 * Then I should be able to access the community page
	 */
	@Test
	public void accessCommunity() {
		try {
			//If page is wide enough, there will be a community link there
			driver.findElement(By.linkText("COMMUNITY"));
		} 
		catch (NoSuchElementException nseex) {
			try {
				//However if the page is not wide enough (such as mobile device), then there 
				//is a drop menu to access the options that are removed. Community is one of these.
				WebElement menu = driver.findElement(By.id("responsive_menu_logo"));
				menu.click();
				WebElement community = driver.findElement(By.linkText("Community"));
				community.click();
				WebElement home = driver.findElement(By.linkText("Home"));//This part fails in HTMLUnitDriver
				home.click();
				WebDriverWait wait = new WebDriverWait(driver, 5);
				try {
					//Wait for login to complete, page should not change
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("community_home_title")));
				}
				catch (TimeoutException tex) {
					assertEquals("http://steamcommunity.com/", driver.getCurrentUrl());
				}
			}
			catch (NoSuchElementException nseex1) {
				fail();
			}
		}
	}
	
	/**
	 * Scenario 2:
	 * Given that I am on the community page
	 * When I am looking at the content
	 * Then I should have a way to view specific categories of content
	 */
	@Test
	public void categories() {
		driver.get("http://steamcommunity.com/");
		try {
			//If page is wide enough, all links will be there
			driver.findElement(By.linkText("All"));
			driver.findElement(By.linkText("Screenshots"));
			driver.findElement(By.linkText("Artwork"));
			driver.findElement(By.linkText("Broadcasts"));
			driver.findElement(By.linkText("Videos"));
			driver.findElement(By.linkText("Workshop"));
			driver.findElement(By.linkText("News"));
			driver.findElement(By.linkText("Guides"));
			driver.findElement(By.linkText("Reviews"));
		}
		catch (NoSuchElementException nseex) {
			try {
				//If page is not wide enough, they go into a drop box menu
				driver.findElement(By.className("responsive_tab_select"));
			}
			catch (NoSuchElementException nseex1) {
				fail();
			}
		}
	}
	
	/**
	 * Scenario 3:
	 * Given that I am on the community page
	 * When I am looking near the top of the page
	 * Then I should have a way to search for a specific game's community
	 */
	@Test
	public void searchForGameCommunities() {
		driver.get("http://steamcommunity.com/");
		try {
			driver.findElement(By.name("searchAppName"));
		}
		catch (NoSuchElementException nseex1) {
			fail();
		}
	}
	
	/**
	 * Scenario 4:
	 * Given that I am on the community page
	 * When I am looking near the top of the page
	 * Then I should have a way to search for a specific person in the community
	 */
	@Test
	public void searchForPeople() {
		driver.get("http://steamcommunity.com/");
		try {
			driver.findElement(By.name("text"));
		}
		catch (NoSuchElementException nseex1) {
			fail();
		}
	}
}

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * As a user,
 * I would like to have multiple ways to view games
 * So that I can find games I'm interested in easier
 * 
 * Total Scenarios in User Story: 5
 */

public class SteamTestUserStoryViewGames {
	
	static WebDriver driver = new FirefoxDriver();
	
	/** Start at the home page for each test */
	@Before
	public void setUp() throws Exception {
		driver.get("http://store.steampowered.com");
	}
	
	
	/**
	 * Scenario 1:
	 * Given that I am on the main page
	 * When I look at the categories
	 * Then I should see on sale items
	 */
	@Test
	public void onSaleItems() {
		try {
			driver.findElement(By.partialLinkText("Specials"));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 2:
	 * Given that I am on the main page
	 * When I look at the browse section
	 * Then I should see the option to browse by genre
	 */
	@Test
	public void browseByGenre() {
		try {
			driver.findElement(By.id("genre_tab"));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 3:
	 * Given that I am on the main page
	 * When I look through the sections of content
	 * Then I should see a section called new on Steam
	 */
	@Test
	public void newReleases() {
		try {
			driver.findElement(By.linkText("New on Steam"));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 4:
	 * Given that I am on the main page
	 * When I look through the sections of content
	 * Then I should see a section called recommended for me
	 */
	@Test
	public void recommendedForMe() {
		try {
			driver.findElement(By.id("foryou_tab"));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/**
	 * Scenario 5:
	 * Given that I am on the main page
	 * When looking near the top of the page
	 * Then I should see an option to search for games
	 */
	@Test
	public void searchBar() {
		try {
			WebElement bar = driver.findElement(By.id("store_nav_search_term"));
			//The following lines of code only work in FirefoxDriver
			bar.sendKeys("TF2\r");
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Team Fortress 2")));
		}
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
}

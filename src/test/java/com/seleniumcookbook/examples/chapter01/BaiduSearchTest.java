package com.seleniumcookbook.examples.chapter01;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import static org.junit.Assert.*;

public class BaiduSearchTest {
	 private WebDriver driver;

	  @Before
	  public void setUp() {
	    System.setProperty("webdriver.chrome.driver",
	        "./src/test/resources/chromedriver.exe");

	    // Launch Chrome
	    driver = new ChromeDriver();
	    // Maximize the browser window
	    driver.manage().window().maximize();
	    // Navigate to Google
	    driver.get("http://www.baidu.com");
	  }

	  @Test
	  public void testBaiduSearch() {
	    // Find the text input element by its name
	    WebElement element = driver.findElement(By.id("kw"));

	    // Enter something to search for
	    element.sendKeys("Selenium testing tools cookbook");

	    // Now submit the form. WebDriver will find
	    // the form for us from the element
	    element.submit();

	    // Google's search is rendered dynamically with JavaScript.
	    // Wait for the page to load, timeout after 10 seconds
	    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver d) {
	        return d.getTitle().toLowerCase()
	            .startsWith("selenium testing tools cookbook");
	      }
	    });

	    //Check Result
	    assertEquals("Selenium testing tools cookbook_百度搜索",
	        driver.getTitle());
	  }
	  
	  @Test
	  public void testFindElements() {
		    //Get all the links displayed on Page
		    List<WebElement> links = driver.findElements(By.tagName("a"));

		    //Verify there are four links displayed on the page
		    assertEquals(32, links.size());

		    //Iterate though the list of links and print
		    //target for each link
		    for(WebElement link : links) {
		        System.out.println(link.getAttribute("href"));
		    }
		}


	  @After
	  public void tearDown() throws Exception {
	    // Close the browser
	    driver.quit();
	  }

}

package Trello.PostAssessment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TrelloBoard {

	static WebDriver driver;

	

	@BeforeTest
	public void setup() 
	{
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://trello.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("user")).sendKeys("ankitamotwani96@gmail.com");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("password")).sendKeys("Ankita@123");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
		Thread.sleep(3000);
		//String actualTitle = driver.getTitle();
		//String expectedTitle = "Log in to continue - Log in with Atlassian account";
		
		System.out.println("User succefully login");
	}

	@Test(priority = 2)
	public void board() throws InterruptedException {

		driver.findElement(By.xpath("//button[@class='_2Hkk1T39xw4RMQ _3TTqkG5muwOzqZ ZOUktZwsING7-0']/p")).click();
		driver.findElement(By.xpath("//button[@class='_3Qtx4lodxp9J0E']/span[text()='Create board']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Add board title']")).sendKeys("Goa Trip Preparation");
		driver.findElement(By.xpath("//button[text()='Create board']")).click();
		String actual = driver.getTitle();
		String expected = "Boards | Trello";
		Assert.assertEquals(actual, expected);
		System.out.println("board created test case pass");

	}

	@Test(priority = 3)
	public void createDo() throws InterruptedException

	{
		driver.findElement(By.name("name")).sendKeys("Do");
		driver.findElement(By.xpath("//*[@value='Add list']")).click();
		WebElement card = driver.findElement(By.xpath("//span[text()='Add a card']"));
		card.click();

		WebElement add = driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		add.sendKeys("Shopping");
		driver.findElement(By.xpath("//input[@class='nch-button nch-button--primary confirm mod-compact js-add-card']"))
				.click();

		WebElement addCard = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[1]/div/textarea"));
		addCard.sendKeys("Ticket Booking");
		driver.findElement(By.xpath("//input[@class='nch-button nch-button--primary confirm mod-compact js-add-card']"))
				.click();

		driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[1]/div/textarea"))
				.sendKeys("Booking Hotel");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[2]/div[1]/a")).click();

	}

	@Test(priority = 4)
	public void doing() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Add another list']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter list title…']")).sendKeys("Doing");
		driver.findElement(By.xpath("//*[@value='Add list']")).click();
		WebElement drag1 = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/a[1]/div[3]"));
		WebElement drop1 = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[3]/a"));
		Actions action = new Actions(driver);
		action.dragAndDrop(drag1, drop1).build().perform();
		WebElement drag2 = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/a[1]/div[3]/span"));
		WebElement drop2 = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[3]/a"));
		action.dragAndDrop(drag2, drop2).build().perform();
		WebElement drag3 = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/a/div[3]/span"));
		WebElement drop3 = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[3]/a"));
		action.dragAndDrop(drag3, drop3).build().perform();
	}

	@Test(priority = 5)
	public void done() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Enter list title…']")).sendKeys("Done");
		driver.findElement(By.xpath("//*[@value='Add list']")).click();
		WebElement drag31 = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[2]/a[1]/div[3]/span"));
		WebElement drop31 = driver.findElement(By.xpath("//*[@id='board']/div[3]/div/div[3]/a"));
		Actions a = new Actions(driver);
		a.dragAndDrop(drag31, drop31).build().perform();
		WebElement drag32 = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[2]/a[1]/div[3]/span"));
		WebElement drop32 = driver.findElement(By.xpath("//*[@id='board']/div[3]/div/div[3]/a"));
		a.dragAndDrop(drag32, drop32).build().perform();
		WebElement drag33 = driver.findElement(By.xpath("//*[@id='board']/div[2]/div/div[2]/a/div[3]/span"));
		WebElement drop33 = driver.findElement(By.xpath("//*[@id='board']/div[3]/div/div[3]/a"));
		a.dragAndDrop(drag33, drop33).build().perform();
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void deleteBoard() {
		driver.findElement(By.xpath("//span[text()='Show menu']")).click();
		driver.findElement(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']")).click();
		driver.findElement(By.xpath("//a[@class='board-menu-navigation-item-link js-close-board']")).click();
		driver.findElement(By.xpath("//input[@value='Close']")).click();
		driver.findElement(By.xpath("//a[text()='Permanently delete board…']")).click();
		driver.findElement(By.xpath("//*[@id='chrome-container']/div[4]/div/div[2]/div/div/div/input")).click();
		//String actual = driver.getTitle();

	}

	@Test(priority = 7)
	public void logout() {
		driver.findElement(By.xpath("//span[text()='AM']")).click();
		driver.findElement(By.xpath("/html/body/div[10]/div/section/div/nav/ul/li[8]/button")).click();
		driver.findElement(By.xpath("//*[@id='logout-submit']/span")).click();
		String actual = driver.getCurrentUrl();
		String expected = "//id.atlassian.com/logout";
		Assert.assertTrue(actual.contains(expected));
		System.out.println("successfully logout");
	}

	@AfterClass
	public void close()
	{
		driver.close();
	}

}
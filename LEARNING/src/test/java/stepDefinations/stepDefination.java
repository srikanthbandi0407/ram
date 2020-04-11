package stepDefinations;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(Cucumber.class)

public class stepDefination {

	WebDriver driver;

	@Given("^when user is on signin page$")
	public void when_user_is_on_signin_page() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "G:\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://automationpractice.com/index.php");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[@class=\"login\"]")).click();

		driver.findElement(By.cssSelector("#email_create")).sendKeys("srikantd@gmail.com");
		driver.findElement(By.xpath("//i[@class=\"icon-user left\"]")).click();
		throw new PendingException();
	}

	@When("^user entering personal information address and contact information and subnit$")
	public void user_entering_personal_information_address_and_contact_information_and_subnit() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		driver.findElement(By.cssSelector("#id_gender1")).click();
		driver.findElement(By.cssSelector("#customer_firstname")).sendKeys("srikanth");
		driver.findElement(By.cssSelector("#customer_lastname")).sendKeys("bandi");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("srikanth123");
		driver.findElement(By.cssSelector("#address1")).sendKeys("srikanth123");
		driver.findElement(By.cssSelector("#address2")).sendKeys("srikanth1");
		driver.findElement(By.cssSelector("#city")).sendKeys("srikanth123");

		Select sState = new Select(driver.findElement(By.xpath("//*[@id='id_state']")));
		sState.selectByVisibleText("Alabama");

		driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("12345");

		Select sCountry = new Select(driver.findElement(By.xpath("//*[@id='id_country']")));
		sCountry.selectByVisibleText("United States");
		driver.findElement(By.xpath("//*[@id='other']")).sendKeys("any other info");
		driver.findElement(By.xpath("//*[@id='phone']")).sendKeys("123457876");
		driver.findElement(By.xpath("//*[@id='phone_mobile']")).sendKeys("868768768768");
		driver.findElement(By.xpath("//*[@id='alias']")).sendKeys("alias");

		throw new PendingException();
	}

	@Then("^new user account got created$")
	public void new_user_account_got_created() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		driver.findElement(By.xpath("//*[text()='Register']")).click();

		throw new PendingException();
	}

	@Then("^check new user got created$")
	public void check_new_user_got_created() throws Throwable {

		String verifingacoount = driver.findElement(By.xpath("//h1[@class=\"page-heading\"]")).getText();

		if (verifingacoount.equals("MY ACCOUNT")) {
			System.out.println("Account got created sucessfully ");
		} else {
			System.out.println("Account not created sucessfully ");
		}

		driver.quit();
		throw new PendingException();
	}
}
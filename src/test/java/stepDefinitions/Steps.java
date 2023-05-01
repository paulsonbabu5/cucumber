package stepDefinitions;

import java.time.Duration;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;





import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;


public class Steps {
	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage myaccount;
	ResourceBundle rb;
	String br;
	@Before
	public void setUp()
	{
		rb=ResourceBundle.getBundle("resources.config");
        br=rb.getString("browser");

		
	}
	@After
	public void tearDown(Scenario scenario)
	{   
		if(scenario.isFailed())
		{
		TakesScreenshot ts=(TakesScreenshot) driver;
		byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());
		
		}
		driver.quit();
	}
	@Given("User Launch Browser")
	public void user_launch_browser() {
		if(br.equals("chrome"))
		{
	    driver=new ChromeDriver();
		}
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	@Given("opens URL {string}")
	public void opens_url(String url) {
		driver.get("http://localhost/opencart/upload/");
		driver.manage().window().maximize();
	}

	@When("user navigate to MyAccountPage")
	public void user_navigate_to_my_account_page() {
		hp=new HomePage(driver);
		hp.clickMyAccount();
	}

	@When("click on login")
	public void click_on_login() {
		hp.clickLogin();
	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
	    lp=new LoginPage(driver);
		lp.setEmail("abcabc@gmail.com");
		lp.setPassword("abcabcc");
	}

	@When("click on login button")
	public void click_on_login_button() {
		lp.clickLogin();
	}

	@Then("user navigates to My account page")
	public void user_navigates_to_my_account_page() {
		myaccount=new MyAccountPage(driver);
		Assert.assertEquals(true,myaccount.isMyAccountPageExists());
		
	}

}

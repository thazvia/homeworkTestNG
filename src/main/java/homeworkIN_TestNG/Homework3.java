package homeworkIN_TestNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Homework3 {

	WebDriver driver;
	String browser=null;

	@BeforeClass
	public void readConfig() {
		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser);

		} catch (IOException e) {
			e.getStackTrace();
		}

	}
	
	@BeforeMethod
	public void init() {
		
	
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\thazv\\july2021_selenium\\Session5TestNG\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://techfios.com/billing/?ng=login/");
	}
	
	@Test(priority=1)
		public void testLoginPage() {

			// storing element with by class
		By UserNameField = By.xpath("//input[@id='username']");
		By PsswordField = By.xpath("//input[@id='password']");
		By SignInButtonField = By.xpath("//button[contains(text(), 'Sign in')]");
		By dashboardField = By.xpath("//h2[contains(text(), 'Dashboard')]");
		By customerField = By.xpath("//span[contains(text(),'Customers')]");
		By addCustomerField = By.xpath("//a[contains(text(), 'Add Customer')]");
		By FullNameField = By.xpath("//input[@id='account']");
		By CompaneyField = By.xpath("//select[@id='cid']");
		By EmailField = By.xpath("//input[@id='email']");
		By PhoneField = By.xpath("//input[@id='phone']");
		By AddressField = By.xpath("//input[@id='address']");
		By CityField = By.xpath("//input[@id='city']");
		By StateField = By.xpath("//input[@id='state']");
		By ZipField = By.xpath("//input[@id='zip']");
		By CountryField = By.xpath("//select[@id='country']");
		By CurrencyField = By.xpath("//select[@name='currency']");
		By GroupField = By.xpath("//select[@name='group']");
		By AccountPasswordField = By.xpath("//input[@id='password']");
		By ConfirmPasswordField = By.xpath("//input[@id='cpassword']");
		By WelcomeEmailField = By.xpath("//label[@class='col-md-4 control-label' and  @for='send_client_signup_email']");
		By NOField = By.xpath("//label[@class='btn btn-default btn-sm active toggle-off']");
		By Save_buttonField = By.xpath("//button[@id='submit']");

			// login data
			String USER_NAME = "demo@techfios.com";
			String PASSWORD = "abc123";
			String NAME = "ROSE";
			String COMPANY_NAME="Techfios";
			String EMAIL = "rose1@mail.com";
			String PHONE = "13116171";
			String ADDRESS = "123 sount st la";
			String CITY = "Janks";
			String STATE = "Ohaio";
			String ZIP = "11111";
			String COUNTRY_NAME="United States";
			String CURRENCY="USD";
			String GROUP="April2020";
			String ACCOUNT_PASSWORD="abc123";
			String CONFIRM_PASSWORD="abc123";
			
			
			
			

			driver.findElement(UserNameField).sendKeys(USER_NAME);
			driver.findElement(PsswordField).sendKeys(PASSWORD);
			driver.findElement(SignInButtonField).click();

			String dashboardExpected = driver.findElement(dashboardField).getText();
			Assert.assertEquals("Dashboard", dashboardExpected, "Dashboard page not found!!!");
			
			driver.findElement(customerField).click();
			driver.findElement(addCustomerField).click();
			
			String AddContactExpected = driver.findElement(addCustomerField).getText();
			Assert.assertEquals("Add Customer",AddContactExpected," Add Customer page not found!!!");
			
			driver.findElement(FullNameField).sendKeys(NAME + randomGenerator(999));
			
			selectFromDropdown(CompaneyField,COMPANY_NAME);
			
			driver.findElement(EmailField).sendKeys(randomGenerator(9999)+EMAIL);
			driver.findElement(PhoneField).sendKeys(PHONE+randomGenerator(99));
			driver.findElement(AddressField).sendKeys(ADDRESS);
			driver.findElement(CityField).sendKeys(CITY);
			driver.findElement(StateField).sendKeys(STATE);
			driver.findElement(ZipField).sendKeys(ZIP);
			
			selectFromDropdown(CountryField,COUNTRY_NAME);
			selectFromDropdown(CurrencyField,CURRENCY);
			selectFromDropdown(GroupField,GROUP);
			
			
			driver.findElement(AccountPasswordField).sendKeys(ACCOUNT_PASSWORD);
			driver.findElement(ConfirmPasswordField).sendKeys(CONFIRM_PASSWORD);
			driver.findElement(NOField).click();
			driver.findElement(Save_buttonField).click();
			
			
		}
	
	public int randomGenerator(int boundaryNumber) {
		Random rnd = new Random();
		int randomNo = rnd.nextInt(boundaryNumber);
		return randomNo;
	}


	public void selectFromDropdown(By field, String visibleText) {

		Select sel1 = new Select(driver.findElement(field));
		sel1.selectByVisibleText(visibleText);
	}
	
	//@AfterMethod
		public void teardown() {
			driver.close();
			
		}
	
}

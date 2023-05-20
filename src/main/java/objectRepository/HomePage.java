package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.resources;

public class HomePage extends resources {

	public WebDriver driver;

	@FindBy(xpath = ".//android.widget.Button[@text='Allow']")
	private WebElement allow;

	@FindBy(className = "android.widget.Button")
	private WebElement hamburger;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Login']")
	private WebElement login;

	public void allow() throws Exception {
		click(allow, "Allow");

		Thread.sleep(1000);
	}

	public void hamburger() {
		click(hamburger, "Hamburger");
	}

	public LoginPage login(WebDriver driver) {
		click(login, "Login");
		return new LoginPage(driver);
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
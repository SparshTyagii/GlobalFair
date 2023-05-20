package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.resources;

public class LoginPage extends resources {

	public WebDriver driver;

	@FindBy(className = "android.widget.EditText")
	private WebElement emailID;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Sign In']")
	private WebElement signIn;

	public void emailID(String keysToSend) {
		click(emailID, "Email ID");
		sendKeys(emailID, keysToSend, "Email ID");
	}

	public SignInPage signIn(WebDriver driver) {
		click(signIn, "Sign In");
		return new SignInPage(driver);
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
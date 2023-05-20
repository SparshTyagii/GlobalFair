package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.resources;

public class SignInPage extends resources {

	public WebDriver driver;

	@FindBy(xpath = "//*[contains(@content-desc,'OTP has been sent')]/following-sibling::android.widget.EditText")
	private WebElement otp;

	public void otp(String keysToSend) {
		click(otp,"OTP");
		sendKeys(otp, keysToSend,"OTP");
	}

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
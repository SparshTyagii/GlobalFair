package testScripts;

import org.testng.annotations.Test;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.SignInPage;
import utilities.resources;

public class Login extends resources {

	public static HomePage hp;

	@Test
	public static void login() throws Exception {
		createTest("Login to GlobalFair Buyer App");
		hp = new HomePage(driver);
		hp.allow();
		hp.hamburger();
		LoginPage lp = hp.login(driver);
		lp.emailID("test@gf.com");
		SignInPage sign = lp.signIn(driver);
		sign.otp("88745");
	}
}
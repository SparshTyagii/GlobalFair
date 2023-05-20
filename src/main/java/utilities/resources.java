package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class resources {

	public AppiumDriverLocalService service;
	public static AndroidDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeTest
	public void nativeAppConfiguration() throws Exception {

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\" + System.getProperty("user.name")
				+ "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		String filePath = System.getProperty("user.dir") + "//Data//data.properties";
		FileInputStream fis = new FileInputStream(filePath);

		Properties properties = new Properties();
		properties.load(fis);
		String appName = properties.getProperty("app");

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Sparsh");
		options.setPlatformName("Android");
		options.setApp(System.getProperty("user.dir") + "//Data//" + appName + ".apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		reportSetup();
	}

	@AfterClass
	public void tearDown() {
		service.stop();
		extent.flush();
	}

	public static void click(WebElement element, String fieldName) {
		try {
			element.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		}
		test.pass("Clicked on : " + fieldName + " successfully");
	}

	public static void sendKeys(WebElement element, String keysToSend, String fieldName) {
		try {
			element.sendKeys(keysToSend);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].value()='" + keysToSend + "'", element);
		}
		test.pass("Entered : " + keysToSend + " into : " + fieldName + " successfully");
	}

	public static void reportSetup() {
		String reportPath = System.getProperty("user.dir") + "//reports";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("GlobalFair Buyer Report");
		reporter.config().setDocumentTitle("GlobalFair Buyer");
		reporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Engineer", "Sparsh Tyagi");
		extent.setSystemInfo("Java Version", System.getProperty("java.specification.version"));
	}

	public static void createTest(String testName) {
		test = extent.createTest(testName);
	}

}
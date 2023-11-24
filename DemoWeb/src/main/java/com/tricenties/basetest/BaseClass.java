package com.tricenties.basetest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricenties.fileutility.ExcelUtility;
import com.tricenties.fileutility.FileUtility;
import com.tricenties.javautility.JavaUtility;
import com.tricenties.obejctrepository.HomePage;
import com.tricenties.obejctrepository.LoginPage;
import com.tricenties.obejctrepository.WelcomePage;
import com.tricenties.webdriverutility.WebDriverUtility;

public class BaseClass {
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	public static WebDriverWait wait;
	public JavaUtility jLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public WelcomePage wp;
	public LoginPage lp;
	public HomePage hp;

	@BeforeSuite
	public void reportConfig() {
		String time = jLib.getSystemTime().toString().replace(":", "-");
		ExtentSparkReporter spark = new ExtentSparkReporter("HTML_report/ExtentReport-" + time + ".html");
		report = new ExtentReports();
		report.attachReporter(spark);
	}

	@Parameters("browser")
	@BeforeClass
	public void browserSetup(@Optional("chrome") String bName) throws IOException {
		if (bName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (bName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (bName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL = fLib.getDataFromPropertyFile("url");
		driver.get(URL);
	}
	@BeforeMethod
	public void loginToApp(Method method) throws EncryptedDocumentException, IOException {
		String EMAIL = eLib.getDataFromExcel("Login", 1, 0);
		String PASSWORD = eLib.getDataFromExcel("Login", 1, 1);
		test=report.createTest(method.getName());
		wp=new WelcomePage(driver);
		wp.getLoginLink().click();
		lp=new LoginPage(driver);
		lp.getEmailTextField().sendKeys(EMAIL);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
	}
	@AfterMethod
	public void logout() {
		hp=new HomePage(driver);
		hp.getLogoutLink().click();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	@AfterSuite
	public void reportBackup() {
		report.flush();
	}

}

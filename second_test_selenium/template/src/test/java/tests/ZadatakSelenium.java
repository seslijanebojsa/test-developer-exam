package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.DeleteModalPage;
import pages.ExamPeriodPage;
import pages.LoginPage;
import pages.NavbarPage;
import pages.RegistrationPage;
import pages.UsersPage;

public class ZadatakSelenium {
	
	private WebDriver driver;
	private NavbarPage navbarPage;
	private BasePage basePage;
	private RegistrationPage registrationPage;
	private LoginPage loginPage;
	private UsersPage usersPage;
	private ExamPeriodPage examPeriodPage;
	private DeleteModalPage deleteModalPage;
	
	@BeforeSuite
	public void initialize (){
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		
		navbarPage = new NavbarPage(driver);
		basePage = new BasePage(driver);
		
		driver.manage().window().maximize(); 
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
		driver.navigate().to("http://localhost:8080/#/");			
	}
	
	@Test
	public void newUserRegistrationTest() {
		
		navbarPage.clickAccount();
		navbarPage.clickRegister();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/register"));
		//assertTrue(basePage.isTitleDisplayed("Register"));
		
		registrationPage = new RegistrationPage(driver);
		registrationPage.setUserNameField("nebojsa");
		registrationPage.setEmailField("selenium@tester.com");
		registrationPage.setPasswordField("tester");
		registrationPage.setConfirmPasswordField("tester");
		registrationPage.registerBttnCLick();
		
		String registrationMesage = registrationPage.getRegistrationMsg();
		assertEquals(registrationMesage, "Registration saved! Please check your email for confirmation.");
		
	}
	
	@Test(dependsOnMethods= "newUserRegistrationTest")
	public void loginNewUserTest() {
		
		navbarPage.clickAccount();
		navbarPage.clickSignIn();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/login"));
		
		loginPage = new LoginPage(driver);
		
		loginPage.setUsernameField("nebojsa");
		loginPage.setPasswordField("tester");
		loginPage.clickBttnSignin();
		
		String msgText = loginPage.getErrorTxt();
		
		assertEquals(msgText, "Failed to sign in! Please check your credentials and try again.");		
	}
	
	@Test(dependsOnMethods= "loginNewUserTest")
	public void adminLoginTest() {
		
		navbarPage.clickAccount();
		navbarPage.clickSignIn();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/login"));
				
		loginPage.setUsernameField("admin");
		loginPage.setPasswordField("admin");
		loginPage.clickBttnSignin();
		
		String msgText = loginPage.getWelcomeTxt();
		
		assertEquals(msgText, "You are logged in as user \"admin\".");			
	}
	
	@Test(dependsOnMethods= "adminLoginTest")
	public void activacionNewUser(){
		
		navbarPage.clickAdministration();
		navbarPage.clickUserManagement();
		
		usersPage = new UsersPage(driver);
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/user-management"));	
		
		usersPage.editButtonForCertainUserClick("nebojsa");
		
		assertTrue(usersPage.isUserActivaed("nebojsa"));
	}
	
	@Test(dependsOnMethods = "activacionNewUser")
	public void logOutTest (){
		
		navbarPage.clickAccount();
		navbarPage.clickLogOut();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/"));				
	}
	
	@Test(dependsOnMethods= "logOutTest")
	public void loginNewUserTestSecondTime() {
		
		navbarPage.clickAccount();
		navbarPage.clickSignIn();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/login"));
		
		loginPage.setUsernameField("nebojsa");
		loginPage.setPasswordField("tester");
		loginPage.clickBttnSignin();
		
		String msgText = loginPage.getWelcomeTxt();
		
		assertEquals(msgText, "You are logged in as user \"nebojsa\".");
	}
	
	@Test(dependsOnMethods= {"loginNewUserTestSecondTime"}) 
	public void addNewExamPeriodTest() {
		
		navbarPage.clickEntities();
		navbarPage.clickIspitniRokovi();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/ispitniRokovis"));
		
		int tableRowsBefore = basePage.tableSize();
		
		examPeriodPage = new ExamPeriodPage(driver);
		
		examPeriodPage.clickNewExamPeriod();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/ispitniRokovis/new"));
		
		examPeriodPage.setNameField("parcijalni modul 2");
		examPeriodPage.setStartField("2021-11-03");
		examPeriodPage.setEndField("2021-11-03");
		
		basePage.saveButtonClick();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/ispitniRokovis"));
		
		int tableRowsAfter = basePage.tableSize();
		assertEquals(tableRowsAfter, tableRowsBefore+1);
		assertTrue(basePage.isEntityDisplayed("parcijalni modul 2"));		
	}
	
	@Test(dependsOnMethods = "addNewExamPeriodTest")
	public void logOutSecondTimeTest (){
		
		navbarPage.clickAccount();
		navbarPage.clickLogOut();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/"));				
	}
	
	@Test(dependsOnMethods= "logOutSecondTimeTest")
	public void adminLoginSecondTimeTest() {
		
		navbarPage.clickAccount();
		navbarPage.clickSignIn();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/login"));
				
		loginPage.setUsernameField("admin");
		loginPage.setPasswordField("admin");
		loginPage.clickBttnSignin();
		
		String msgText = loginPage.getWelcomeTxt();
		
		assertEquals(msgText, "You are logged in as user \"admin\".");			
	}
	
	@Test(dependsOnMethods= "adminLoginSecondTimeTest")
	public void deleteNewUserTest() {
		
		navbarPage.clickAdministration();
		navbarPage.clickUserManagement();
		
		assertTrue(basePage.isUrlDisplayed("http://localhost:8080/#/user-management"));
		
		usersPage.deleteButtonForCertainUserClick("nebojsa");
		
		deleteModalPage = new DeleteModalPage(driver);
		
		assertTrue(deleteModalPage.deleteModal().isDisplayed());
		
		deleteModalPage.confirmDeleteButtonClick();
		
		//assertFalse(usersPage.isUserDisplayed("nebojsa"));
	}
	
	@AfterSuite
	public void quitDriver() {
		driver.quit();
	}
	
	

}

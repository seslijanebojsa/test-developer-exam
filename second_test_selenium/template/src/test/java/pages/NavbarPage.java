package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavbarPage {
	
	private WebDriver driver;

	public NavbarPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void clickAccount() {
		Utilities.clickableWait(driver, By.id("account-menu"), 10).click();
	}
	
	public void clickSignIn() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='login']"), 10).click();
	}
	
	public void clickEntities() {
		Utilities.clickableWait(driver, By.xpath("//span[@translate='global.menu.entities.main']"), 10).click();
	}
	
	public void clickStudenti() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='studenti']"), 10).click();
	}
	
	public void clickNastavnici() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='nastavnici']"), 10).click();
	}
	
	public void clickPredmeti() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='predmeti']"), 10).click();
	}
	
	public void clickIspitniRokovi() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='ispitniRokovi']"), 10).click();
	}
	
	public void clickIspitnePrijave() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='ispitnePrijave']"), 10).click();
	}
	
	public void clickSettings() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='settings']"), 10).click();
	}
	
	public void clickLogOut() {
		Utilities.clickableWait(driver, By.id("logout"), 10).click();
	}
	
	public void clickRegister() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='register']"), 10).click();
	}
	
	public void clickAdministration() {
		Utilities.clickableWait(driver, By.id("admin-menu"), 10).click();
	}

	public void clickUserManagement() {
		Utilities.clickableWait(driver, By.xpath("//a[@ui-sref='user-management']"), 10).click();
	}

}

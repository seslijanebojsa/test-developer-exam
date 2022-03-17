package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	
	private WebDriver driver;

	public BasePage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public boolean isUrlDisplayed(String urlValue) {
		return Utilities.urlWait(driver, urlValue, 10);
	}
	
	public boolean isTitleDisplayed(String titelValue) {
		return Utilities.titleWait(driver, titelValue, 10);
	}
	
	public void addNewButtonClick(String hrefForSelector) {
		Utilities.clickableWait(driver, By.xpath("//button[@ui-sref='" + hrefForSelector + "']"), 10).click();
	}
	
	public WebElement table() {
		return Utilities.visibilityWait(driver, By.xpath("//table[@class = 'jh-table table table-striped']"), 10);
	}
	
	public int tableSize() {
		return driver.findElements(By.xpath("//table[@class='jh-table table table-striped']/tbody/tr")).size();	
	}
	
	public WebElement modal() {
		return Utilities.visibilityWait(driver, By.className("modal-content"), 10);
	}
	
	public void saveButtonClick() {
		driver.findElement(By.xpath("//span[@translate='entity.action.save']")).click();
	}
	
	public boolean isEntityDisplayed (String entitiValue) {
		return Utilities.isPresent(driver, By.linkText(entitiValue));
	}

}

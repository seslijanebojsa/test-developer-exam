package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteModalPage {
	
	private WebDriver driver;

	public DeleteModalPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public WebElement deleteModal() {
		return Utilities.visibilityWait(driver, By.name("deleteForm"), 10);
	}
	
	public void confirmDeleteButtonClick() {
		driver.findElement(By.xpath("//form[@name='deleteForm']//button[@class='btn btn-danger']")).click();
   }
	
	public void cancleButtonClick() {
		driver.findElement(By.xpath("//form[@name='deleteForm']//button[@class='btn btn-default']")).click();
   }

}

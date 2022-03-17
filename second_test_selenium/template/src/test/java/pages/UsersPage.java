package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsersPage {
	
	private WebDriver driver;

	public UsersPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public WebElement usersTable() {
		return Utilities.visibilityWait(driver, By.xpath("//table[@class = 'table table-striped']"), 10);
	} 
	
	public void editButtonForCertainUserClick(String username) {
		driver.findElement(By.xpath("//tr/td[text() = '" + username + "']/following-sibling::td//span[@translate='user-management.deactivated']")).click();
	}
	
	public void deleteButtonForCertainUserClick(String username) {
		driver.findElement(By.xpath("//tr/td[text() = '" + username + "']/following-sibling::td//button[@ui-sref='user-management.delete({login:user.login})']")).click();
	}
	
	public boolean isUserDisplayed (String username) {
		return Utilities.isPresent(driver, By.xpath("//tr/td[text() = '" + username + "']"));
	} 
	
	public boolean isUserActivaed (String username) {
		return Utilities.isPresent(driver, By.xpath("//tr/td[text() = '" + username + "']/following-sibling::td//span[@translate='user-management.activated']"));
	}

}

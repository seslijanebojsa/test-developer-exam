package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public WebElement getUsernameField () {
		return Utilities.visibilityWait(driver, By.id("username"), 10);
	}

	public void setUsernameField (String usernameValue) {
		WebElement usernameEl = this.getUsernameField();
		usernameEl.clear();
		usernameEl.sendKeys(usernameValue);
	}
	
	public WebElement getPasswordField () {
		return Utilities.visibilityWait(driver, By.id("password"), 10);
	}
	
	public void setPasswordField (String passwordValue) {
		WebElement passwordEl = this.getPasswordField();
		passwordEl.clear();
		passwordEl.sendKeys(passwordValue);
	}
	
	public void clickBttnSignin () {
		Utilities.clickableWait(driver, By.xpath("//button[@translate='login.form.button']"), 10).click();
	}
	
	public String getWelcomeTxt () {
		WebElement txt = Utilities.visibilityWait(driver, By.xpath("//div[@translate='main.logged.message']"), 10);
		String welcomeTxt = txt.getText();
		return welcomeTxt;
	}
	
	public String getErrorTxt () {
		WebElement txt = Utilities.visibilityWait(driver, By.xpath("//div[@translate='login.messages.error.authentication']"), 10);
		String errorTxt = txt.getText();
		return errorTxt;
	}

}

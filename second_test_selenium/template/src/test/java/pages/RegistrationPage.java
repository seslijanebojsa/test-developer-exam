package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
	
	private WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public WebElement getUserNameField() {
		return driver.findElement(By.id("login"));
	}
	
	public void setUserNameField(String nameValue) {
		WebElement nameEl = this.getUserNameField();
		nameEl.clear();
		nameEl.sendKeys(nameValue);	
	}
	
	public WebElement getEmailField() {
		return driver.findElement(By.id("email"));
	}
	
	public void setEmailField(String emailValue) {
		WebElement emailEL = this.getEmailField();
		emailEL.clear();
		emailEL.sendKeys(emailValue);	
	}
	
	public WebElement getPasswordField() {
		return driver.findElement(By.id("password"));
	}
	
	public void setPasswordField(String passlValue) {
		WebElement passEl = this.getPasswordField();
		passEl.clear();
		passEl.sendKeys(passlValue);	
	}
	
	public WebElement getConfirmPasswordField() {
		return driver.findElement(By.id("confirmPassword"));
	}
	
	public void setConfirmPasswordField(String passValue) {
		WebElement conPassEl = this.getConfirmPasswordField();
		conPassEl.clear();
		conPassEl.sendKeys(passValue);	
	}
		
	public void registerBttnCLick () {
		Utilities.clickableWait(driver, By.xpath("//button[@translate='register.form.button']"), 10).click();
	}
	
	public String getRegistrationMsg () {
		WebElement msg = Utilities.visibilityWait(driver, By.xpath("//div[@translate='register.messages.success']"), 10);
		String registrationMsgText = msg.getText();
		return registrationMsgText;
	}

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExamPeriodPage {
	
	private WebDriver driver;

	public ExamPeriodPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void clickNewExamPeriod (){
		Utilities.clickableWait(driver, By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div/button"), 10).click();
	}
	
	public WebElement getNameField() {
		return driver.findElement(By.id("field_naziv"));
	}
	
	public void setNameField(String nameValue) {
		WebElement nameEl = this.getNameField();
		nameEl.clear();
		nameEl.sendKeys(nameValue);	
	}
	
	public WebElement getStartField() {
		return driver.findElement(By.id("field_pocetak"));
	}
	
	public void setStartField(String startValue) {
		WebElement startEl = this.getStartField();
		startEl.clear();
		startEl.sendKeys(startValue);	
	}
	
	public WebElement getEndField() {
		return driver.findElement(By.id("field_kraj"));
	}
	
	public void setEndField(String endValue) {
		WebElement endEl = this.getEndField();
		endEl.clear();
		endEl.sendKeys(endValue);	
	}
	
	public void settAllExamPeriodsFields(String nameValue, String startValue, String endValue) {
		this.setNameField(nameValue);
		this.setStartField(startValue);
		this.setEndField(endValue);
		
	}
	
	public boolean iscertainExamPeriosDisplayed(String examsNameValue, String startValue, String endValue) {
		
		try {
			return driver.findElement(By.xpath("//tr/td[a/text() = '" + examsNameValue + "']/following-sibling::td[text() = '" + startValue + "']/following-sibling::td[text() = '" + endValue + "']")).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}

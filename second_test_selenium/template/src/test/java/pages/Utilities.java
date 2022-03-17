package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	public static boolean urlWait(WebDriver driver, String url, int wait) {
		return new WebDriverWait(driver, wait).until(ExpectedConditions.urlToBe(url));
	}
	
	public static boolean titleWait(WebDriver driver, String title, int wait) {
		return new WebDriverWait(driver, wait).until(ExpectedConditions.titleIs(title));
	}
	
	public static WebElement visibilityWait (WebDriver driver, By selector, int wait) {
		return new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOfElementLocated(selector));
	}
	
	public static WebElement presenceWait (WebDriver driver, By selector, int wait) {
		return new WebDriverWait(driver, wait).until(ExpectedConditions.presenceOfElementLocated(selector));
	}
	
	public static WebElement clickableWait (WebDriver driver, By selector, int wait) {
		return new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(selector));
	}
	
	public static boolean isPresent(WebDriver driver, By selector) {
		
		try {
			return driver.findElement(selector).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}

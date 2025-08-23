package com.makemytrip;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMytripAutomation {

	
	public static void main(String[] args) {
		
		ChromeOptions chromeoptions = new ChromeOptions();
		chromeoptions.addArguments("--start-maximized");
		
		WebDriver wd = new ChromeDriver(chromeoptions);
		wd.get("https://www.makemytrip.com/");
		
		WebDriverWait wait = new WebDriverWait(wd,Duration.ofSeconds(30));
		
		By closeModalButtonLocator = By.xpath("//span[@data-cy='closeModal']");
		WebElement closemodalButton=wait.until(ExpectedConditions.elementToBeClickable(closeModalButtonLocator));
		closemodalButton.click();
		
		By forCityLabelLocator = By.xpath("//label[@for='fromCity']");
		WebElement forCityLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(forCityLabelLocator));
		forCityLabel.click();
		
		
		By forCityInputTextBoxLocator = By.xpath(" //input[@placeholder='From']");
		WebElement forCityInputTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(forCityInputTextBoxLocator));
		forCityInputTextBox.sendKeys("Pune");
		
		By fromCitySuggestionListLocator = By.xpath("//p[contains(text(),'SUGGESTIONS')]/ancestor::div[contains(@class,'react-autosuggest')]/ul/li");
		
		boolean state = wait.until(ExpectedConditions.and(ExpectedConditions.invisibilityOfElementLocated(fromCitySuggestionListLocator),
		
				ExpectedConditions.numberOfElementsToBeLessThan(fromCitySuggestionListLocator,12)));
		
		List<WebElement> forCitySuggestionList= null;
		
		if(state) {
			
		forCitySuggestionList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fromCitySuggestionListLocator));
			
		
		}
		
		//System.out.println(forCitySuggestionList.size());
		
		for(WebElement suggestion:forCitySuggestionList) {
			System.out.println(suggestion.getText());
		}
		
		forCitySuggestionList.get(0).click();//Selecting the first city from the list
		
		
		By toCityLabellocator = By.xpath("//label[@for=\"toCity\"]");
		WebElement tocitylabel = wait.until(ExpectedConditions.visibilityOfElementLocated(toCityLabellocator));
		tocitylabel.click();
		
		By toCityInputTextBoxLocator=By.xpath("//input[@placeholder=\"To\"]");
		WebElement toCityTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(toCityInputTextBoxLocator));
		toCityTextBox.sendKeys("Bangalore");
		
		By toSuggestionListLocator =  By.xpath("//p[contains(text(),\"POPULAR CITIES\")]/ancestor::div[contains(@class,'react-autosuggest')]/ul/li");
		
		state =wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(toSuggestionListLocator),
				
				ExpectedConditions.numberOfElementsToBeMoreThan(toSuggestionListLocator, 5)));
		
		List<WebElement> toSuggestionList = null;
		
		if(state) {
			
	  toSuggestionList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(toSuggestionListLocator));
		
		}
				
		
		toSuggestionList.get(0).click(); //Selecting the first city from the list!! [to location]
		
		
		//div[contains(text(),"August 2025")]/ancestor::div[@class="DayPicker-Month"]
		
		LocalDate targetdate = LocalDate.now();
		
		targetdate = targetdate.plusDays(5);
		
		String targetmonth = targetdate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);//August
		
		int targetyear = targetdate.getYear();//2025
		
		int targetday= targetdate.getDayOfMonth(); //28
		
		//System.out.println("div[contains(text(),\"August 2025\")]/ancestor::div[@class=\"DayPicker-Month\"]");	
		
		//System.out.println("div[contains(text(), '"+targetmonth+""+targetyear+"')]/ancestor::div[@class=\"DayPicker-Month\"]");	
		
		//By calendarmonthlocator = By.xpath("//div[contains(@class,'DayPicker-Caption') and contains(text(),'" + targetmonth + " " + targetyear + "')]/ancestor::div[contains(@class,'DayPicker-Month')]");
		
		//By calendarmonthlocator = By.xpath("div[contains(text(), '" + targetmonth +" "+targetyear+ "')]/ancestor::div[@class=\"DayPicker-Month\"]");
		
		By calendarmonthlocator = By.xpath("//div[contains(text(), '" + targetmonth + " " + targetyear + "')]/ancestor::div[@class=\"DayPicker-Month\"]");		
				
		WebElement calendarmonth = wait.until(ExpectedConditions.visibilityOfElementLocated(calendarmonthlocator));
		
		//p[contains(text(),'24')]/ancestor::div[contains(@class,"DayPicker-Day")]
		
		//By dateLocator = By.xpath(".//p[text()='" + targetday + "']/ancestor::div[contains(@class,'DayPicker-Day')]");
		
		//By dateLocator = By.xpath(".//p[text()='"+targetday+"')]/ancestor::div[contains(@class,\"DayPicker-Day\")]");

		By dateLocator = By.xpath(".//p[text()='" + targetday + "']/ancestor::div[contains(@class,'DayPicker-Day')]");
		
		WebElement date = calendarmonth.findElement(dateLocator);	
		
		date.click();
		
		
		
		
		
		
		
		
		
	}
	
		
	
	
	
}

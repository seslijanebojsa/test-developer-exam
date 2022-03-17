package unittesting.parcijalni;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Zadatak2 {
	
	private TehnicalInspection tehnicalInspection;
	
	@BeforeSuite
	private void setUp(){
		
		tehnicalInspection = new TehnicalInspection();
		
	}
	
	@Test (dataProvider = "provideCarYear", dataProviderClass = KlasaDataProvider.class)	
	public void carAgeCategoryTest (int productionYear, String messageCarCategory) {
		
		String actCategory = tehnicalInspection.carAgeCategory(productionYear);
		
		assertEquals(actCategory, messageCarCategory);
		
	}
	
	
	

}

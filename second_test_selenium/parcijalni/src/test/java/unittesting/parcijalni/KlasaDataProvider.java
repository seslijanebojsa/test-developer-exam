package unittesting.parcijalni;

import org.testng.annotations.DataProvider;

public class KlasaDataProvider {
	
	@DataProvider (name = "provideCarYear")
	public static Object[][] provideCarYear() {
		
		return new Object[][] {
			
			{1918, "Not classified"},
			{2022, "Not classified"},
			{1919, "Vintage Car"},
			{1920, "Vintage Car"},
			{1929, "Vintage Car"},
			{1975, "Antique Car"},
			{1974, "Antique Car"},
			{1990, "Classic Car"},
			{1989, "Classic Car"},
			{2021, "Modern Car"},
		};
		
	}

}

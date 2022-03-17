package unittesting.parcijalni;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.mockito.Mockito;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class Zadatak1 {
	
	private TehnicalInspection tehnicalInspection;
	private CarRepository mockedCarRepository;
	
	
	@BeforeSuite
	@BeforeMethod	
	public void setUp() {
		
		tehnicalInspection = new TehnicalInspection();
		mockedCarRepository = Mockito.mock(CarRepository.class);		
		tehnicalInspection.setCarRepository(mockedCarRepository);		
		
	}
	
	@Test
	public void needCarSeviceTest1() throws NotFound {
		
		Car car = new Car();
		
		car.setCurrentMileage(9999);
		
		when(mockedCarRepository.findByChassisNumber("12345678901234567")).thenReturn(car);
		
		String actual = tehnicalInspection.needCarService("12345678901234567");
		
		AssertJUnit.assertEquals(actual, "Bez servisa");		
		
	}
	@Test
	public void needCarSeviceTest2() throws NotFound {
		
		Car car = new Car();
		
		car.setCurrentMileage(10000);
		
		when(mockedCarRepository.findByChassisNumber("12345678901234567")).thenReturn(car);
		
		String actual = tehnicalInspection.needCarService("12345678901234567");
		
		AssertJUnit.assertEquals(actual, "Mali servis");
		
	}
	@Test
	public void needCarSeviceTest3() throws NotFound {
		
		Car car = new Car();
		
		car.setCurrentMileage(79999);
		
		when(mockedCarRepository.findByChassisNumber("12345678901234567")).thenReturn(car);
		
		String actual = tehnicalInspection.needCarService("12345678901234567");
		
		AssertJUnit.assertEquals(actual, "Mali servis");
		
	}
	
	@Test
	public void needCarServiceTest4() throws NotFound {
		
		Car car = new Car();
		
		car.setCurrentMileage(80000);
		
		when(mockedCarRepository.findByChassisNumber("12345678901234567")).thenReturn(car);
		
		String actual = tehnicalInspection.needCarService("12345678901234567");
		
		AssertJUnit.assertEquals(actual, "Veliki servis");
		
	}
	@Test (expectedExceptions = NotFound.class)
	public void needCarServiceTestNotFound () throws NotFound {
		
		when(mockedCarRepository.findByChassisNumber("12345678901234567")).thenReturn(null);
		
		tehnicalInspection.needCarService("12345678901234567");
		
	}
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void needCarSeviceIllegalArgumentExceptionTest1 () throws NotFound {
		//18 digit chassis number
		tehnicalInspection.needCarService("111111111111111111");
		
	}
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void needCarSeviceIllegalArgumentExceptionTest2 () throws NotFound {
		//16 digit chassis number
		tehnicalInspection.needCarService("111111111111111111");
		
	}
		

}

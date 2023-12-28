package testCases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import racingTeam.Car;

public class CarConcurrencyTest {
	
	private static final int THREAD_COUNT = 100;
	private static final int CAR_ID_START = 1001;
	
	@Test
	public void testConcurrentSetCarID() throws InterruptedException {
		
		//Arrange
		Car car = new Car(1000, 200, "rtx_v");
		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
		
		
		//Act
		for(int i = 0; i< THREAD_COUNT;i++) {
			executorService.submit(() -> {
				int nextCarID;
				synchronized (CarConcurrencyTest.this) {
					nextCarID = CAR_ID_START + car.getCarID();
					car.setCarID(nextCarID);
				}
			});
		}
		
		
		//Assert
		executorService.shutdown();
		executorService.awaitTermination(5, TimeUnit.SECONDS);
		
		
		//Verify
		assertEquals(CAR_ID_START + THREAD_COUNT -1, car.getCarID());
	}
}

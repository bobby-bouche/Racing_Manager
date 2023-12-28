package testCases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import racingTeam.Car;

public class CarConcurrencyTest {
	
	private static final int THREAD_COUNT = 100;
	private static final int CAR_ID_START = 1001;
	
	@Test
	public void testConcurrentSetCarID() throws InterruptedException {
		
		//Arrange
		Car car = new Car(1000, 200, "rtx_v");
		AtomicInteger carIDCounter = new AtomicInteger(CAR_ID_START);
		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
		
		
		//Act
		for(int i = 0; i< THREAD_COUNT;i++) {
			executorService.submit(() -> {
				synchronized (CarConcurrencyTest.this) {
					car.setCarID(carIDCounter.getAndIncrement());
				}
			});
		}
		
		
		//Assert
		executorService.shutdown();
		executorService.awaitTermination(5, TimeUnit.SECONDS);
		
		
		//Verify
		assertEquals(CAR_ID_START + THREAD_COUNT -1, car.getCarID());
		System.out.println("Concurrency test successfull");
	}
}

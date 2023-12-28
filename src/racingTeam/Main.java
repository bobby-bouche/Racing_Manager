package racingTeam;

import testCases.CarConcurrencyTest;

public class Main {

	public static void main(String[] args) {
	
		
		CarConcurrencyTest test = new CarConcurrencyTest();
		try {
			test.testConcurrentSetCarID();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

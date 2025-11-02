package operations;

import creational.Operation;

public class Modulo implements Operation {

	@Override
	public double calculate(double a, double b) {
		
		return a % b;
	}
}

package operations;

import creational.Operation;

public class Subtraction implements Operation {

	@Override
	public double calculate(double a, double b) {
		return a - b;
	}

}

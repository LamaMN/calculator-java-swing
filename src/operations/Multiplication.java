package operations;

import creational.Operation;

public class Multiplication implements Operation {

	@Override
	public double calculate(double a, double b) {
		return a * b;
	}

}
package operations;

public class Division implements Operation {

	@Override
	public double calculate(double a, double b) {
		if (b == 0)
			throw new ArithmeticException("Division by zero");
		return a / b;
	}
}

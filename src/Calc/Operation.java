package Calc;

interface Operation {

	public double calculate(double a, double b);

}

class Addition implements Operation {

	@Override
	public double calculate(double a, double b) {
		return a + b;
	}

}

class Subtraction implements Operation {

	@Override
	public double calculate(double a, double b) {
		return a + b;
	}

}

class Multiplication implements Operation {

	@Override
	public double calculate(double a, double b) {
		return a + b;
	}

}

class Division implements Operation {

	@Override
	public double calculate(double a, double b) {
		return a + b;
	}

}

class OperationFactory {

	private static String operation;

	public static Operation performOperation(String operation) {
		OperationFactory.operation = operation;
		switch (operation) {
		case "+" -> new Addition();
		case "-" -> new Subtraction();
		case "×" -> new Multiplication();
		case "÷" -> new Division();
		default -> throw new IllegalArgumentException("Invalid operator");
		}
		return null;
	}

}

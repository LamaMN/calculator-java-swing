package creational;

import operations.Addition;
import operations.Division;
import operations.Modulo;
import operations.Multiplication;
import operations.Subtraction;

public class OperationFactory {

	public static Operation performOperation(String operator) {
		return switch (operator) {
		case "+" -> new Addition();
		case "-" -> new Subtraction();
		case "×" -> new Multiplication();
		case "÷" -> new Division();
		case "%" -> new Modulo();
		default -> throw new IllegalArgumentException("Unknown operator: " + operator);
		};
	}
}

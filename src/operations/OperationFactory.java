package operations;

public class OperationFactory {

	public static Operation performOperation(String operator) {
		return switch (operator) {
		case "+" -> new Addition();
		case "-" -> new Subtraction();
		case "×" -> new Multiplication();
		case "÷" -> new Division();
		default -> throw new IllegalArgumentException("Unknown operator: " + operator);
		};
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structural;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import creational.Operation;
import creational.OperationFactory;


/**
*
* @author Remas + Lama
*/

/**
 * Facade Pattern: Simplifies calculator operation logic Hides complexity of
 * Factory + Decorator pattern from UI
 */
public class CalculatorFacade {

	private boolean enableLogging = false;
	private static final String LOG_FILE = "calculator_log.txt";

	public CalculatorFacade() {
	}

	public CalculatorFacade(boolean enableLogging) {
		this.enableLogging = enableLogging;
	}

	public double calculate(double a, double b, String operator) {
		try {
			if (enableLogging) {
				logToConsole("Calculating: " + a + " " + operator + " " + b);
			}

			// Use factory to create the right operation
			Operation operation = OperationFactory.performOperation(operator);

			// Apply decorators dynamically
			operation = new RoundingDecorator(operation);

			// Execute
			double result = operation.calculate(a, b);

			if (enableLogging) {
				logToConsole("Result: " + result);
				logToFile(a, operator, b, result); // Save to file
			}

			return result;
		} catch (Exception e) {
			if (enableLogging) {
				logToConsole("Error: " + e.getMessage());
				logToFile("Error: " + e.getMessage());
			}
			return Double.NaN;
		}
	}

	/**
	 * Log to console (standard output)
	 */
	private void logToConsole(String message) {
		System.out.println(message);
	}

	/**
	 * Log to file with timestamp
	 */
	private void logToFile(double a, String operator, double b, double result) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			writer.println(String.format("[%s] %s %s %s = %s", timestamp, a, operator, b, result));
		} catch (IOException e) {
			System.err.println("Failed to write to log file: " + e.getMessage());
		}
	}

	/**
	 * Log error message to file
	 */
	private void logToFile(String errorMessage) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			writer.println(String.format("[%s] %s", timestamp, errorMessage));
		} catch (IOException e) {
			System.err.println("Failed to write to log file: " + e.getMessage());
		}
	}

	/**
	 * Clear the log file
	 */
	public static void clearLog() {
		try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, false))) {
			writer.println("=== Calculator Log ===");
			writer.println("Log started: " + LocalDateTime.now());
			writer.println();
		} catch (IOException e) {
			System.err.println("Failed to clear log file: " + e.getMessage());
		}
	}
}

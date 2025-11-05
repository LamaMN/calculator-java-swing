package structural;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import creational.Operation;

/**
 * 
 * @author Lama
 *
 * Decorator that adds logging functionality to operations
 * Logs all calculations with timestamps to console and file
 */
public class LoggingDecorator extends OperationDecorator {

    private static final String LOG_FILE = "calculator_log.txt";
    private final String operator;
    private final boolean logToFile;
    private final boolean logToConsole;

    /**
     * Constructor with full control
     * @param operation The operation to decorate
     * @param operator The operator symbol ("+", "-", "×", etc.)
     * @param logToConsole Whether to log to console
     * @param logToFile Whether to log to file
     */
    public LoggingDecorator(Operation operation, String operator, boolean logToConsole, boolean logToFile) {
        super(operation);
        this.operator = operator;
        this.logToConsole = logToConsole;
        this.logToFile = logToFile;
    }

    /**
     * Constructor - logs to both console and file by default
     * @param operation The operation to decorate
     * @param operator The operator symbol
     */
    public LoggingDecorator(Operation operation, String operator) {
        this(operation, operator, true, true);
    }

    @Override
    public double calculate(double a, double b) {
        // Log before calculation
        if (logToConsole) {
            System.out.println("Calculating: " + a + " " + operator + " " + b);
        }

        // Perform the actual calculation
        double result;
        try {
            result = super.calculate(a, b);
            
            // Log successful result
            if (logToConsole) {
                System.out.println("Result: " + result);
            }

            // Log to file
            if (logToFile) {
                logToFile(a, operator, b, result, null);
            }
        } catch (ArithmeticException e) {
            // Log error
            if (logToConsole) {
                System.out.println("Error: " + e.getMessage());
            }
            if (logToFile) {
                logToFile(a, operator, b, 0, e.getMessage());
            }
            throw e; // Re-throw the exception
        }

        return result;
    }

    /**
     * Log calculation to file with timestamp
     */
    private void logToFile(double a, String operator, double b, double result, String error) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            if (error != null) {
                writer.println(String.format("[%s] %s %s %s = ERROR: %s", 
                    timestamp, a, operator, b, error));
            } else {
                writer.println(String.format("[%s] %s %s %s = %s", 
                    timestamp, a, operator, b, result));
            }
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    /**
     * Clear the log file (utility method)
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
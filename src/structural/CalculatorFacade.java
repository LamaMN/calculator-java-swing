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
 * Facade Pattern: Simplifies calculator operation logic
 * Applies decorators (Rounding, Logging) automatically
 */

public class CalculatorFacade {

    private boolean enableLogging = false;

    public CalculatorFacade() {
    }

    public CalculatorFacade(boolean enableLogging) {
        this.enableLogging = enableLogging;
    }

    public double calculate(double a, double b, String operator) {
        try {
            // Step 1: Use Factory to get operation
            Operation operation = OperationFactory.performOperation(operator);

            // Step 2: Apply Rounding Decorator
            operation = new RoundingDecorator(operation);

            // Step 3: Apply Logging Decorator (if enabled)
            if (enableLogging) {
                operation = new LoggingDecorator(operation, operator);
            }

            // Step 4: Execute and return
            return operation.calculate(a, b);
        } catch (Exception e) {
            if (enableLogging) {
                System.err.println("Calculation error: " + e.getMessage());
            }
            return Double.NaN;
        }
    }
}
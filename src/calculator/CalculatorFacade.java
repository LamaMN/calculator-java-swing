/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import operations.*;

/**
 *
 * @author Remas
 */
public class CalculatorFacade {

    public double calculate(double a, double b, String operator) {
        try {
            // Use factory to create the right operation
            Operation operation = OperationFactory.performOperation(operator);

            // Apply decorators dynamically
            operation = new RoundingDecorator(operation);

            // Execute
            return operation.calculate(a, b);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Double.NaN;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

/**
 *
 * @author Remas
 */
public class RoundingDecorator extends OperationDecorator {

    public RoundingDecorator(Operation operation) {
        super(operation);
    }

    @Override
    public double calculate(double a, double b) {
        double result = super.calculate(a, b);
        return Math.round(result * 100.0) / 100.0; // round to 2 decimal places
    }
}

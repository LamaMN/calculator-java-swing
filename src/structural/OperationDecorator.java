/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structural;

import creational.Operation;

/**
 *
 * @author Remas
 */
public class OperationDecorator implements Operation {

    protected Operation decoratedOperation;

    public OperationDecorator(Operation operation) {
        this.decoratedOperation = operation;
    }

    @Override
    public double calculate(double a, double b) {
        return decoratedOperation.calculate(a, b);

    }
}

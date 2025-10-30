package calculator;

import javax.swing.SwingUtilities;

/**
 *
 * @author youcefhmd
 */

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("  Calculator - Design Patterns  ");
        System.out.println("=================================");
        System.out.println("Initializing Calculator...");
        System.out.println("Patterns: Singleton, Factory, Builder, Facade, Decorator");
        System.out.println();
        
        // Launch calculator on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = Calculator.getInstance();
            calculator.setVisible(true);
            System.out.println("Calculator launched successfully!");
        });
    }
}


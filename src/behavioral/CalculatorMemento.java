package behavioral;


/**
 * @author lama
 * Memento Pattern: Stores calculator state for undo/redo
 * Immutable snapshot of calculator state at a point in time
 */
public class CalculatorMemento {
    private final String currentOperand;
    private final String previousOperand;
    private final String operation;
    private final String timestamp;
    
    public CalculatorMemento(String currentOperand, String previousOperand, 
                           String operation) {
        this.currentOperand = currentOperand;
        this.previousOperand = previousOperand;
        this.operation = operation;
        this.timestamp = java.time.LocalDateTime.now()
            .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    
    // Getters only - immutable
    public String getCurrentOperand() {
        return currentOperand;
    }
    
    public String getPreviousOperand() {
        return previousOperand;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        if (previousOperand.isEmpty()) {
            return currentOperand;
        }
        return previousOperand + " " + operation + " " + currentOperand;
    }
}

package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lama
 * Caretaker: Manages memento history for undo/redo
 */
public class CalculatorHistory {
    private final List<CalculatorMemento> history;
    private int currentIndex;
    private static final int MAX_HISTORY = 50; // Limit history size
    
    public CalculatorHistory() {
        this.history = new ArrayList<>();
        this.currentIndex = -1;
    }
    
    /**
     * Save current state to history
     */
    public void save(CalculatorMemento memento) {
        // Remove any "redo" states if we're in the middle of history
        while (history.size() > currentIndex + 1) {
            history.remove(history.size() - 1);
        }
        
        // Add new state
        history.add(memento);
        currentIndex++;
        
        // Limit history size
        if (history.size() > MAX_HISTORY) {
            history.remove(0);
            currentIndex--;
        }
    }
    
    /**
     * Undo to previous state
     */
    public CalculatorMemento undo() {
        if (!canUndo()) {
            return null;
        }
        currentIndex--;
        return history.get(currentIndex);
    }
    
    /**
     * Redo to next state
     */
    public CalculatorMemento redo() {
        if (!canRedo()) {
            return null;
        }
        currentIndex++;
        return history.get(currentIndex);
    }
    
    /**
     * Check if undo is possible
     */
    public boolean canUndo() {
        return currentIndex > 0;
    }
    
    /**
     * Check if redo is possible
     */
    public boolean canRedo() {
        return currentIndex < history.size() - 1;
    }
    
    /**
     * Get current state
     */
    public CalculatorMemento getCurrentState() {
        if (currentIndex >= 0 && currentIndex < history.size()) {
            return history.get(currentIndex);
        }
        return null;
    }
    
    /**
     * Get full history for display
     */
    public List<CalculatorMemento> getHistory() {
        return new ArrayList<>(history); // Return copy for safety
    }
    
    /**
     * Clear all history
     */
    public void clear() {
        history.clear();
        currentIndex = -1;
    }
    
    /**
     * Get history size
     */
    public int size() {
        return history.size();
    }
}

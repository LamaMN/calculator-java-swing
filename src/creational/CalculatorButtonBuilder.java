package creational;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
*
* @author Lama
*/

/*
 * Builder Pattern for creating Calculator Buttons
 * Simplifies the complex button initialization process
 */
public class CalculatorButtonBuilder {
    
    // Required parameters
    private String text;
    
    // Optional parameters with default values
    private Color background = new Color(21, 20, 22);
    private Color foreground = Color.WHITE;
    private Color borderColor = new Color(41, 39, 44);
    private Font font = new Font("Century Gothic", Font.BOLD, 18);
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private Dimension preferredSize = new Dimension(70, 70);
    private Insets margin = new Insets(0, 0, 0, 0);
    private int iconTextGap = 1;
    private boolean focusPainted = false;
    
    // Button type enum for predefined configurations
    public enum ButtonType {
        NUMBER,      // 0-9, .
        OPERATION,   // +, -, ×, ÷, =
        FUNCTION,    // C, ←, +/-
        WINDOW       // Close, Minimize (for title bar)
    }
    
    public CalculatorButtonBuilder(String text) {
        this.text = text;
    }
    
    // Fluent API methods
    public CalculatorButtonBuilder withBackground(Color background) {
        this.background = background;
        return this;
    }
    
    public CalculatorButtonBuilder withForeground(Color foreground) {
        this.foreground = foreground;
        return this;
    }
    
    public CalculatorButtonBuilder withBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }
    
    public CalculatorButtonBuilder withFont(Font font) {
        this.font = font;
        return this;
    }
    
    public CalculatorButtonBuilder withPreferredSize(Dimension size) {
        this.preferredSize = size;
        return this;
    }
    
    public CalculatorButtonBuilder withPreferredSize(int width, int height) {
        this.preferredSize = new Dimension(width, height);
        return this;
    }
    
    public CalculatorButtonBuilder withCursor(Cursor cursor) {
        this.cursor = cursor;
        return this;
    }
    
    public CalculatorButtonBuilder withMargin(Insets margin) {
        this.margin = margin;
        return this;
    }
    
    public CalculatorButtonBuilder withFocusPainted(boolean focusPainted) {
        this.focusPainted = focusPainted;
        return this;
    }
    
    /**
     * Convenience method: Apply predefined button type configurations
     */
    public CalculatorButtonBuilder ofType(ButtonType type) {
        switch (type) {
            case NUMBER:
                this.background = new Color(21, 20, 22);
                this.borderColor = new Color(41, 39, 44);
                break;
            case OPERATION:
                this.background = new Color(41, 39, 44);
                this.borderColor = new Color(41, 39, 44);
                break;
            case FUNCTION:
                this.background = new Color(41, 39, 44);
                this.borderColor = new Color(41, 39, 44);
                break;
            case WINDOW:
                this.background = new Color(21, 20, 22);
                this.borderColor = null; // No border
                this.font = new Font("Century Gothic", Font.BOLD, 24);
                this.preferredSize = new Dimension(30, 30);
                break;
        }
        return this;
    }
    
    /**
     * Build the final JButton with all configurations
     */
    public JButton build() {
        JButton button = new JButton(text);
        
        // Apply all configurations
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(font);
        button.setCursor(cursor);
        button.setPreferredSize(preferredSize);
        button.setMargin(margin);
        button.setIconTextGap(iconTextGap);
        button.setFocusPainted(focusPainted);
        
        // Set border
        if (borderColor != null) {
            button.setBorder(BorderFactory.createLineBorder(borderColor));
        } else {
            button.setBorder(null);
        }
        
        return button;
    }
    
    /**
     * Static factory methods for common button types
     */
    public static JButton createNumberButton(String text) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.NUMBER)
                .build();
    }
    
    public static JButton createOperationButton(String text) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.OPERATION)
                .build();
    }
    
    public static JButton createFunctionButton(String text) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.FUNCTION)
                .build();
    }
    
    public static JButton createWindowButton(String text) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.WINDOW)
                .build();
    }
}
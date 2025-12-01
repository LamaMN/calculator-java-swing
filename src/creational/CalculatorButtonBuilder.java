package creational;

import behavioral.ThemeStrategy;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.*;

/**
 * @author lama
 * Builder Pattern with Theme Support
 */
public class CalculatorButtonBuilder {
    
    private String text;
    private Color background;
    private Color foreground;
    private Color borderColor;
    private Font font;
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private Dimension preferredSize = new Dimension(70, 70);
    private Insets margin = new Insets(0, 0, 0, 0);
    private int iconTextGap = 1;
    private boolean focusPainted = false;
    
    public enum ButtonType {
        NUMBER, OPERATION, FUNCTION, WINDOW
    }
    
    public CalculatorButtonBuilder(String text) {
        this.text = text;
    }
    
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
    
    public CalculatorButtonBuilder withPreferredSize(int width, int height) {
        this.preferredSize = new Dimension(width, height);
        return this;
    }
    
    public CalculatorButtonBuilder ofType(ButtonType type, ThemeStrategy theme) {
        this.font = theme.getButtonFont();
        this.foreground = theme.getButtonForeground();
        
        switch (type) {
            case NUMBER:
                this.background = theme.getNumberButtonBackground();
                this.borderColor = theme.getBorderColor();
                break;
            case OPERATION:
                this.background = theme.getOperationButtonBackground();
                this.foreground = theme.getOperationButtonForeground();
                this.borderColor = theme.getBorderColor();
                break;
            case FUNCTION:
                this.background = theme.getFunctionButtonBackground();
                this.borderColor = theme.getBorderColor();
                break;
            case WINDOW:
                this.background = theme.getTitleBarBackground();
                this.borderColor = null;
                this.font = new Font("Century Gothic", Font.BOLD, 24);
                this.preferredSize = new Dimension(30, 30);
                break;
        }
        return this;
    }
    
    public JButton build() {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(font);
        button.setCursor(cursor);
        button.setPreferredSize(preferredSize);
        button.setMargin(margin);
        button.setIconTextGap(iconTextGap);
        button.setFocusPainted(focusPainted);
        
        if (borderColor != null) {
            button.setBorder(BorderFactory.createLineBorder(borderColor));
        } else {
            button.setBorder(null);
        }
        
        return button;
    }
    
    // Static factory methods with theme support
    public static JButton createNumberButton(String text, ThemeStrategy theme) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.NUMBER, theme)
                .build();
    }
    
    public static JButton createOperationButton(String text, ThemeStrategy theme) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.OPERATION, theme)
                .build();
    }
    
    public static JButton createFunctionButton(String text, ThemeStrategy theme) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.FUNCTION, theme)
                .build();
    }
    
    public static JButton createWindowButton(String text, ThemeStrategy theme) {
        return new CalculatorButtonBuilder(text)
                .ofType(ButtonType.WINDOW, theme)
                .build();
    }
}
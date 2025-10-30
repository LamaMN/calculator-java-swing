package structural;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * Decorator Pattern for adding hover effects to buttons
 * Dynamically adds behavior without modifying button classes
 */

/*
*
* @author Lama
*/

public class HoverDecorator {
    
    private final JButton button;
    private final Color originalBackground;
    private final Color hoverBackground;
    private final Color hoverForeground;
    
    /**
     * Simple hover with only background color change
     */
    public HoverDecorator(JButton button, Color hoverBackground) {
        this.button = button;
        this.originalBackground = button.getBackground();
        this.hoverBackground = hoverBackground;
        this.hoverForeground = null; // Keep original foreground
        applyHoverEffect();
    }
    
    /**
     * Full hover with both background and foreground color changes
     */
    public HoverDecorator(JButton button, Color hoverBackground, Color hoverForeground) {
        this.button = button;
        this.originalBackground = button.getBackground();
        this.hoverBackground = hoverBackground;
        this.hoverForeground = hoverForeground;
        applyHoverEffect();
    }
    
    private void applyHoverEffect() {
        button.addMouseListener(new MouseAdapter() {
            private Color originalFg = button.getForeground();
            
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverBackground);
                if (hoverForeground != null) {
                    button.setForeground(hoverForeground);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalBackground);
                button.setForeground(originalFg);
            }
        });
    }
    
    public JButton getButton() {
        return button;
    }
    
    /**
     * Static factory methods for common hover types
     */
    public static JButton applyNumberHover(JButton button) {
        new HoverDecorator(button, new Color(73, 69, 78));
        return button;
    }
    
    public static JButton applyOperationHover(JButton button) {
        new HoverDecorator(button, new Color(73, 69, 78));
        return button;
    }
    
    public static JButton applyCloseButtonHover(JButton button) {
        new HoverDecorator(button, new Color(255, 75, 75), new Color(31, 30, 33));
        return button;
    }
    
    public static JButton applyMinimizeButtonHover(JButton button) {
        new HoverDecorator(button, new Color(73, 69, 78));
        return button;
    }
}

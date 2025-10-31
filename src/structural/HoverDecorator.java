package structural;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Decorator Pattern for adding hover effects to buttons Dynamically adds
 * behavior without modifying button classes
 */

/*
*
* @author Lama
 */
public class HoverDecorator extends UIDecorator {

    private final Color hoverBackground;
    private final Color hoverForeground;
    private final Color originalBackground;
    private final Color originalForeground;

    public HoverDecorator(JButton button, Color hoverBackground, Color hoverForeground) {
        super(button);
        this.hoverBackground = hoverBackground;
        this.hoverForeground = hoverForeground;
        this.originalBackground = button.getBackground();
        this.originalForeground = button.getForeground();
    }

    public HoverDecorator(JButton button, Color hoverBackground) {
        this(button, hoverBackground, null);
    }

    @Override
    public void apply() {
        button.addMouseListener(new MouseAdapter() {
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
                button.setForeground(originalForeground);
            }
        });
    }

    // Static helpers (like before)
    public static void applyNumberHover(JButton button) {
        new HoverDecorator(button, new Color(73, 69, 78)).apply();
    }

    public static void applyOperationHover(JButton button) {
        new HoverDecorator(button, new Color(73, 69, 78)).apply();
    }

    public static void applyCloseButtonHover(JButton button) {
        new HoverDecorator(button, new Color(255, 75, 75), new Color(31, 30, 33)).apply();
    }

    public static void applyMinimizeButtonHover(JButton button) {
        new HoverDecorator(button, new Color(73, 69, 78)).apply();
    }
}

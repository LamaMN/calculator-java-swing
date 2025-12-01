package behavioral;

import java.awt.Color;
import java.awt.Font;

/**
 * Neon Theme - Vibrant cyberpunk aesthetic
 */
public class NeonThemeStrategy implements ThemeStrategy {
    
    @Override
    public Color getNumberButtonBackground() {
        return new Color(25, 25, 40);
    }
    
    @Override
    public Color getOperationButtonBackground() {
        return new Color(138, 43, 226); // Purple
    }
    
    @Override
    public Color getFunctionButtonBackground() {
        return new Color(255, 20, 147); // Deep Pink
    }
    
    @Override
    public Color getButtonForeground() {
        return new Color(0, 255, 255); // Cyan
    }
    
    @Override
    public Color getOperationButtonForeground() {
    	return new Color(0, 255, 255); // Cyan
    }
    
    @Override
    public Color getDisplayForeground() {
        return new Color(0, 255, 255); // Cyan
    }
    
    @Override
    public Color getPreviousDisplayForeground() {
        return new Color(255, 20, 147); // Pink
    }
    
    @Override
    public Color getAppBackground() {
        return new Color(10, 10, 25);
    }
    
    @Override
    public Color getDisplayBackground() {
        return new Color(20, 20, 35);
    }
    
    @Override
    public Color getPreviousDisplayBackground() {
        return new Color(15, 15, 30);
    }
    
    @Override
    public Color getButtonsPanelBackground() {
        return new Color(15, 15, 30);
    }
    
    @Override
    public Color getTitleBarBackground() {
        return new Color(20, 20, 35);
    }
    
    @Override
    public Color getNumberHoverColor() {
        return new Color(50, 50, 80);
    }
    
    @Override
    public Color getOperationHoverColor() {
        return new Color(178, 83, 255); // Lighter purple
    }
    
    @Override
    public Color getCloseHoverBackground() {
        return new Color(255, 0, 127);
    }
    
    @Override
    public Color getCloseHoverForeground() {
        return Color.BLACK;
    }
    
    @Override
    public Color getMinimizeHoverColor() {
        return new Color(138, 43, 226);
    }
    
    @Override
    public Color getBorderColor() {
        return new Color(138, 43, 226);
    }
    
    @Override
    public Font getButtonFont() {
        return new Font("Century Gothic", Font.BOLD, 18);
    }
    
    @Override
    public Font getDisplayFont() {
        return new Font("Century Gothic", Font.BOLD, 24);
    }
    
    @Override
    public Font getPreviousDisplayFont() {
        return new Font("Century Gothic", Font.BOLD, 18);
    }
    
    @Override
    public Font getTitleFont() {
        return new Font("Century Gothic", Font.BOLD, 17);
    }
    
    @Override
    public String getThemeName() {
        return "Neon";
    }
}

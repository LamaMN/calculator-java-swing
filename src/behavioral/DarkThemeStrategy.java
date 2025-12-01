package behavioral;

import java.awt.Color;
import java.awt.Font;

/**
 * Dark Theme (Original Calculator Theme)
 */
public class DarkThemeStrategy implements ThemeStrategy {
    
    @Override
    public Color getNumberButtonBackground() {
        return new Color(21, 20, 22);
    }
    
    @Override
    public Color getOperationButtonBackground() {
        return new Color(41, 39, 44);
    }
    
    @Override
    public Color getFunctionButtonBackground() {
        return new Color(41, 39, 44);
    }
    
    @Override
    public Color getButtonForeground() {
        return Color.WHITE;
    }
    @Override
    public Color getOperationButtonForeground() {
    	return Color.WHITE;
    }
    
    @Override
    public Color getDisplayForeground() {
        return Color.WHITE;
    }
    
    @Override
    public Color getPreviousDisplayForeground() {
        return new Color(203, 198, 213);
    }
    
    @Override
    public Color getAppBackground() {
        return new Color(13, 12, 20);
    }
    
    @Override
    public Color getDisplayBackground() {
        return new Color(41, 39, 44);
    }
    
    @Override
    public Color getPreviousDisplayBackground() {
        return new Color(21, 20, 22);
    }
    
    @Override
    public Color getButtonsPanelBackground() {
        return new Color(21, 20, 22);
    }
    
    @Override
    public Color getTitleBarBackground() {
        return new Color(21, 20, 22);
    }
    
    @Override
    public Color getNumberHoverColor() {
        return new Color(73, 69, 78);
    }
    
    @Override
    public Color getOperationHoverColor() {
        return new Color(73, 69, 78);
    }
    
    @Override
    public Color getCloseHoverBackground() {
        return new Color(255, 75, 75);
    }
    
    @Override
    public Color getCloseHoverForeground() {
        return new Color(31, 30, 33);
    }
    
    @Override
    public Color getMinimizeHoverColor() {
        return new Color(73, 69, 78);
    }
    
    @Override
    public Color getBorderColor() {
        return new Color(41, 39, 44);
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
        return "Dark";
    }
}
package behavioral;

import java.awt.Color;
import java.awt.Font;

/**
 * @author lama
 * Light Theme - Professional light color scheme
 */
public class LightThemeStrategy implements ThemeStrategy {
    
    // Core Colors
    private static final Color DARK_GRAY = new Color(33, 33, 33);
    private static final Color MEDIUM_GRAY = new Color(100, 100, 100);
    private static final Color GOOGLE_BLUE = new Color(66, 133, 244);
    private static final Color DARK_BLUE_HOVER = new Color(51, 105, 232);

    @Override
    public Color getNumberButtonBackground() {
        return Color.WHITE; // Pure white for better distinction
    }
    
    @Override
    public Color getOperationButtonBackground() {
        return GOOGLE_BLUE;
    }
    
    @Override
    public Color getFunctionButtonBackground() {
        return new Color(229, 229, 229); // Light gray for function buttons
    }
    
    @Override
    public Color getButtonForeground() {
        return MEDIUM_GRAY;
    }
    
    @Override
    public Color getOperationButtonForeground() {
    	return Color.WHITE;
    }
    

    @Override
    public Color getDisplayForeground() {
        return DARK_GRAY;
    }
    
    @Override
    public Color getPreviousDisplayForeground() {
        return MEDIUM_GRAY;
    }
    
    @Override
    public Color getAppBackground() {
        return new Color(247, 247, 247); // Very light off-white
    }
    
    @Override
    public Color getDisplayBackground() {
        return Color.WHITE;
    }
    
    @Override
    public Color getPreviousDisplayBackground() {
        return new Color(245, 245, 245);
    }
    
    @Override
    public Color getButtonsPanelBackground() {
        return new Color(247, 247, 247);
    }
    
    @Override
    public Color getTitleBarBackground() {
        return new Color(235, 235, 235);
    }
    
    @Override
    public Color getNumberHoverColor() {
        return new Color(240, 240, 240);
    }
    
    @Override
    public Color getOperationHoverColor() {
        return DARK_BLUE_HOVER;
    }
    
    @Override
    public Color getCloseHoverBackground() {
        return new Color(232, 17, 35);
    }
    
    @Override
    public Color getCloseHoverForeground() {
        return Color.WHITE;
    }
    
    @Override
    public Color getMinimizeHoverColor() {
        return new Color(221, 221, 221);
    }
    
    @Override
    public Color getBorderColor() {
        return new Color(200, 200, 200);
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
        return "Light";
    }
}
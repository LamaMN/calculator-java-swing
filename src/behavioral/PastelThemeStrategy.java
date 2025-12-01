package behavioral;

import java.awt.Color;
import java.awt.Font;

/**
 * @author lama
 * Pastel Theme - Soft, muted, and gentle color scheme.
 */
public class PastelThemeStrategy implements ThemeStrategy {
    
    // Core Pastel Colors
    private static final Color SOFT_WHITE = new Color(255, 255, 255);
    private static final Color PALE_LAVENDER = new Color(248, 244, 249);
    private static final Color SOFT_MINT = new Color(178, 206, 179); 
    private static final Color MUTED_BLUE = new Color(176, 224, 230); // Powder Blue
    private static final Color DARK_TEXT = new Color(51, 51, 51);
    private static final Color MEDIUM_TEXT = new Color(120, 120, 120);

    @Override
    public Color getNumberButtonBackground() {
        return SOFT_WHITE;
    }
    
    @Override
    public Color getOperationButtonBackground() {
        return MUTED_BLUE;
    }
    
    @Override
    public Color getFunctionButtonBackground() {
        return PALE_LAVENDER;
    }
    
    @Override
    public Color getButtonForeground() {
        return DARK_TEXT;
    }
    
    @Override
    public Color getOperationButtonForeground() {
    	return DARK_TEXT;
    }
    
    @Override
    public Color getDisplayForeground() {
        return DARK_TEXT;
    }
    
    @Override
    public Color getPreviousDisplayForeground() {
        return MEDIUM_TEXT;
    }
    
    @Override
    public Color getAppBackground() {
        return PALE_LAVENDER;
    }
    
    @Override
    public Color getDisplayBackground() {
        return SOFT_WHITE;
    }
    
    @Override
    public Color getPreviousDisplayBackground() {
        return new Color(240, 235, 240); // Slightly darker lavender
    }
    
    @Override
    public Color getButtonsPanelBackground() {
        return PALE_LAVENDER;
    }
    
    @Override
    public Color getTitleBarBackground() {
        return new Color(235, 230, 240);
    }
    
    @Override
    public Color getNumberHoverColor() {
        return new Color(245, 245, 245);
    }
    
    @Override
    public Color getOperationHoverColor() {
        return new Color(144, 200, 208); // Darker pastel blue
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
        return new Color(220, 210, 230);
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
        return "Pastel";
    }
}

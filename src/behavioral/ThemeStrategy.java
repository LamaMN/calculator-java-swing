package behavioral;

import java.awt.Color;
import java.awt.Font;

/**
 * @author lama
 * Strategy Pattern: Defines interface for different calculator themes
 * Allows runtime theme switching without modifying Calculator class
 */
public interface ThemeStrategy {
    
    // Button colors
    Color getNumberButtonBackground();
    Color getOperationButtonBackground();
    Color getFunctionButtonBackground();
    
    // Text colors
    Color getButtonForeground();
    Color getOperationButtonForeground();
    Color getDisplayForeground();
    Color getPreviousDisplayForeground();
    
    // Background colors
    Color getAppBackground();
    Color getDisplayBackground();
    Color getPreviousDisplayBackground();
    Color getButtonsPanelBackground();
    Color getTitleBarBackground();
    
    // Hover colors
    Color getNumberHoverColor();
    Color getOperationHoverColor();
    Color getCloseHoverBackground();
    Color getCloseHoverForeground();
    Color getMinimizeHoverColor();
    
    // Border colors
    Color getBorderColor();
    
    // Font
    Font getButtonFont();
    Font getDisplayFont();
    Font getPreviousDisplayFont();
    Font getTitleFont();
    
    // Theme name
    String getThemeName();
}
package structural;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ScaleHoverDecorator {
    
    private final JButton button;
    private final Font originalFont;
    private final Font hoverFont;
    
    public ScaleHoverDecorator(JButton button, float fontScaleFactor) {
        this.button = button;
        this.originalFont = button.getFont();
        
        // Create larger font for hover
        float newSize = originalFont.getSize() * fontScaleFactor;
        this.hoverFont = originalFont.deriveFont(newSize);
        
        applyFontScale();
    }
    
    private void applyFontScale() {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(hoverFont);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(originalFont);
            }
        });
    }
    
    public JButton getButton() {
        return button;
    }
}
package structural;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Adds font scaling behavior on hover.
 */
public class ScaleHoverDecorator extends UIDecorator {

    private final float scaleFactor;

    public ScaleHoverDecorator(JButton button, float scaleFactor) {
        super(button);
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void apply() {
        Font originalFont = button.getFont();
        Font hoverFont = originalFont.deriveFont(originalFont.getSize() * scaleFactor);

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
}

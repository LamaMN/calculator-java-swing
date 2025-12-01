package structural;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import behavioral.ThemeStrategy;

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

	// Theme-aware static methods
	public static JButton applyNumberHover(JButton button, ThemeStrategy theme) {
		// CORRECTION: Create the decorator and call apply()
		HoverDecorator decorator = new HoverDecorator(button, theme.getNumberHoverColor());
		decorator.apply();
		return button;
	}

	public static JButton applyOperationHover(JButton button, ThemeStrategy theme) {
		// CORRECTION: Create the decorator and call apply()
		HoverDecorator decorator = new HoverDecorator(button, theme.getOperationHoverColor());
		decorator.apply();
		return button;
	}

	public static JButton applyCloseButtonHover(JButton button, ThemeStrategy theme) {
		// CORRECTION: Create the decorator and call apply()
		HoverDecorator decorator = new HoverDecorator(button, theme.getCloseHoverBackground(),
				theme.getCloseHoverForeground());
		decorator.apply();
		return button;
	}

	public static JButton applyMinimizeButtonHover(JButton button, ThemeStrategy theme) {
		// CORRECTION: Create the decorator and call apply()
		HoverDecorator decorator = new HoverDecorator(button, theme.getMinimizeHoverColor());
		decorator.apply();
		return button;
	}
}
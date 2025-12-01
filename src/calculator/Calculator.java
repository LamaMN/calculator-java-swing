package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import behavioral.CalculatorHistory;
import behavioral.CalculatorMemento;
import behavioral.DarkThemeStrategy;
import behavioral.LightThemeStrategy;
import behavioral.NeonThemeStrategy;
import behavioral.PastelThemeStrategy;
import behavioral.ThemeStrategy;
import creational.CalculatorButtonBuilder;
import structural.CalculatorFacade;
import structural.HoverDecorator;
import structural.ScaleHoverDecorator;

/**
 *
 * @author youcefhmd
 */
@SuppressWarnings("serial")
public final class Calculator extends JFrame {

	private static Calculator instance;
	private CalculatorHistory history;
	private String currentOperand;
	private String previousOperand;
	private String operation;
	private int x, y;

	// Theme strategy
	private ThemeStrategy currentTheme;

	// UI Components
	private JPanel app, resultsPanel, buttonsPanel, titleBar;
	private JTextField current, previous;
	private JLabel title;

	// Calculator buttons
	private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	private JButton btnMod, btnPlus, btnSub, btnMult, btnDiv, btnEqual;
	private JButton btnClear, btnDel, btnDot, btnPlusSub;
	private JButton btnClose, btnMini, btnTheme;
	private JButton btnUndo, btnRedo;

	private Calculator() {
		currentTheme = new DarkThemeStrategy(); // Start with dark theme
		initComponents();
		rebuildButtonsWithTheme();
		history = new CalculatorHistory();
		clear();
		addEventsWithDecorators();
		addKeyboardShortcuts();
		saveState();
	}

	public static Calculator getInstance() {
		if (instance == null) {
			instance = new Calculator();
		}
		return instance;
	}

	/**
	 * Initialize main UI components (panels, text fields)
	 */
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Calculator - " + currentTheme.getThemeName() + " Theme");
		setLocation(500, 100);
		setUndecorated(true);
		setResizable(false);

		// Main app panel
		app = new JPanel();
		app.setBackground(new Color(13, 12, 20));
		app.setForeground(new Color(40, 40, 40));
		app.setLayout(null);
		app.setBounds(0, 0, 320, 530);

		// Results panel
		resultsPanel = new JPanel();
		resultsPanel.setBackground(new Color(34, 34, 34));
		resultsPanel.setForeground(new Color(57, 57, 57));
		resultsPanel.setLayout(null);
		resultsPanel.setBounds(0, 30, 320, 110);

		// Previous operand display
		previous = new JTextField();
		previous.setEditable(false);
		previous.setBackground(new Color(21, 20, 22));
		previous.setFont(new Font("Century Gothic", Font.BOLD, 18));
		previous.setForeground(new Color(203, 198, 213));
		previous.setHorizontalAlignment(JTextField.RIGHT);
		previous.setBorder(null);
		previous.setBounds(0, 0, 320, 50);

		// Current operand display
		current = new JTextField();
		current.setEditable(false);
		current.setBackground(new Color(41, 39, 44));
		current.setFont(new Font("Century Gothic", Font.BOLD, 24));
		current.setForeground(Color.WHITE);
		current.setHorizontalAlignment(JTextField.RIGHT);
		current.setBorder(null);
		current.setBounds(0, 50, 320, 60);

		resultsPanel.add(previous);
		resultsPanel.add(current);
		app.add(resultsPanel);

		// Buttons panel
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(21, 20, 22));
		buttonsPanel.setLayout(null);
		buttonsPanel.setBounds(0, 140, 320, 390);
		app.add(buttonsPanel);

		// Title bar
		titleBar = new JPanel();
		titleBar.setBackground(new Color(21, 20, 22));
		titleBar.setLayout(null);
		titleBar.setBounds(0, 0, 320, 30);

		// Title label
		title = new JLabel("Calculator");
		title.setFont(new Font("Century Gothic", Font.BOLD, 17));
		title.setForeground(Color.WHITE);
		title.setBounds(6, 2, 100, 25);
		titleBar.add(title);

		// Add title bar dragging
		titleBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				x = evt.getX();
				y = evt.getY();
			}
		});
		titleBar.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				int xx = evt.getXOnScreen();
				int yy = evt.getYOnScreen();
				setLocation(xx - x, yy - y);
			}
		});

		app.add(titleBar);
		applyThemeToComponents();

		// Set content pane
		setContentPane(app);
		pack();
		setSize(320, 540);
	}

	private void saveState() {
		CalculatorMemento memento = new CalculatorMemento(currentOperand, previousOperand, operation);
		history.save(memento);
		updateUndoRedoButtons();
	}

	private void restoreState(CalculatorMemento memento) {
		if (memento != null) {
			currentOperand = memento.getCurrentOperand();
			previousOperand = memento.getPreviousOperand();
			operation = memento.getOperation();
			updateDisplay();
			updateUndoRedoButtons();
		}
	}

	private void undo() {
		CalculatorMemento memento = history.undo();
		if (memento != null) {
			restoreState(memento);
			System.out.println("Undo to: " + memento);
		}
	}

	private void redo() {
		CalculatorMemento memento = history.redo();
		if (memento != null) {
			restoreState(memento);
			System.out.println("Redo to: " + memento);
		}
	}

	private void updateUndoRedoButtons() {
		if (btnUndo != null) {
			btnUndo.setEnabled(history.canUndo());
		}
		if (btnRedo != null) {
			btnRedo.setEnabled(history.canRedo());
		}
	}

	/**
	 * Rebuild all buttons with current theme
	 */
	private void rebuildButtonsWithTheme() {
		buttonsPanel.removeAll();

		// NUMBER BUTTONS
		btn0 = CalculatorButtonBuilder.createNumberButton("0", currentTheme);
		btn1 = CalculatorButtonBuilder.createNumberButton("1", currentTheme);
		btn2 = CalculatorButtonBuilder.createNumberButton("2", currentTheme);
		btn3 = CalculatorButtonBuilder.createNumberButton("3", currentTheme);
		btn4 = CalculatorButtonBuilder.createNumberButton("4", currentTheme);
		btn5 = CalculatorButtonBuilder.createNumberButton("5", currentTheme);
		btn6 = CalculatorButtonBuilder.createNumberButton("6", currentTheme);
		btn7 = CalculatorButtonBuilder.createNumberButton("7", currentTheme);
		btn8 = CalculatorButtonBuilder.createNumberButton("8", currentTheme);
		btn9 = CalculatorButtonBuilder.createNumberButton("9", currentTheme);
		btnDot = CalculatorButtonBuilder.createNumberButton(".", currentTheme);
		btnPlusSub = CalculatorButtonBuilder.createNumberButton("+/-", currentTheme);

		// OPERATION BUTTONS
		btnMod = CalculatorButtonBuilder.createOperationButton("%", currentTheme);
		btnPlus = CalculatorButtonBuilder.createOperationButton("+", currentTheme);
		btnSub = CalculatorButtonBuilder.createOperationButton("-", currentTheme);
		btnMult = CalculatorButtonBuilder.createOperationButton("×", currentTheme);
		btnDiv = CalculatorButtonBuilder.createOperationButton("÷", currentTheme);
		btnEqual = CalculatorButtonBuilder.createOperationButton("=", currentTheme);
		// FUNCTION BUTTONS
		btnClear = CalculatorButtonBuilder.createFunctionButton("C", currentTheme);
		btnDel = CalculatorButtonBuilder.createFunctionButton("←", currentTheme);

		// WINDOW BUTTONS
		btnClose = CalculatorButtonBuilder.createWindowButton("×", currentTheme);
		btnMini = CalculatorButtonBuilder.createWindowButton("-", currentTheme);

		Font symbolFont = new Font("Arial Unicode MS", Font.BOLD, 20);
		Color bgColor = currentTheme.getAppBackground();
		Color titleBgColor = currentTheme.getTitleBarBackground();
		Color fgColor = currentTheme.getButtonForeground(); // Use button foreground for visibility

		btnUndo = new CalculatorButtonBuilder("↶").withBackground(bgColor)
				.withForeground(currentTheme.getButtonForeground()).withFont(symbolFont).withPreferredSize(70, 35).build();
		btnUndo.setToolTipText("Undo (Ctrl+Z)");

		btnRedo = new CalculatorButtonBuilder("↷").withBackground(bgColor).withForeground(fgColor).withFont(symbolFont).withPreferredSize(70, 35).build();
		
		btnRedo.setToolTipText("Redo (Ctrl+Y)");

		String themeSymbol = "";
		if (currentTheme instanceof DarkThemeStrategy) {
			themeSymbol = "☀️";
		} else if (currentTheme instanceof LightThemeStrategy) {
			themeSymbol = "⚡";
		} else if (currentTheme instanceof NeonThemeStrategy) {
			themeSymbol = "🌸";
		} else {
			themeSymbol = "🌙";
		}
		// THEME TOGGLE BUTTON (special)
		btnTheme = new CalculatorButtonBuilder(themeSymbol) // Use the dynamic themeSymbol
				.withBackground(titleBgColor).withForeground(fgColor)
				.withFont(new Font("Arial Unicode MS", Font.BOLD, 16)).withPreferredSize(30, 30).build();
		btnTheme.setToolTipText("Switch Theme");

		addButtonsToPanel();
		addWindowButtonsToTitleBar();

		buttonsPanel.revalidate();
		buttonsPanel.repaint();
	}

	private void addButtonsToPanel() {
		// Row 1
		btnDel.setBounds(20, 20, 70, 70);
		btnClear.setBounds(90, 20, 70, 70);
		btnDiv.setBounds(160, 20, 70, 70);
		btnMult.setBounds(230, 20, 70, 70);

		// Row 2
		btn7.setBounds(20, 90, 70, 70);
		btn8.setBounds(90, 90, 70, 70);
		btn9.setBounds(160, 90, 70, 70);
		btnSub.setBounds(230, 90, 70, 70);

		// Row 3
		btn4.setBounds(20, 160, 70, 70);
		btn5.setBounds(90, 160, 70, 70);
		btn6.setBounds(160, 160, 70, 70);
		btnPlus.setBounds(230, 160, 70, 70);

		// Row 4
		btn1.setBounds(20, 230, 70, 70);
		btn2.setBounds(90, 230, 70, 70);
		btn3.setBounds(160, 230, 70, 70);
		btnMod.setBounds(230, 230, 70, 70);

		// Row 5
		btnPlusSub.setBounds(20, 300, 70, 70);
		btn0.setBounds(90, 300, 70, 70);
		btnDot.setBounds(160, 300, 70, 70);
		btnEqual.setBounds(230, 300, 70, 70);

		// Row 6:
		btnUndo.setBounds(20, 370, 50, 25);
		btnRedo.setBounds(230, 370, 55, 25);

		buttonsPanel.add(btnDel);
		buttonsPanel.add(btnClear);
		buttonsPanel.add(btnDiv);
		buttonsPanel.add(btnMult);
		buttonsPanel.add(btn7);
		buttonsPanel.add(btn8);
		buttonsPanel.add(btn9);
		buttonsPanel.add(btnSub);
		buttonsPanel.add(btn4);
		buttonsPanel.add(btn5);
		buttonsPanel.add(btn6);
		buttonsPanel.add(btnPlus);
		buttonsPanel.add(btn1);
		buttonsPanel.add(btn2);
		buttonsPanel.add(btn3);
		buttonsPanel.add(btnMod);
		buttonsPanel.add(btnPlusSub);
		buttonsPanel.add(btn0);
		buttonsPanel.add(btnDot);
		buttonsPanel.add(btnEqual);
		buttonsPanel.add(btnUndo);
		buttonsPanel.add(btnRedo);
	}

	private void addWindowButtonsToTitleBar() {
		// This ensures that only the new, themed buttons are displayed.
		titleBar.removeAll();

		// Re-add the static title label
		titleBar.add(title);

		// Set bounds for the new buttons
		btnTheme.setBounds(230, 0, 30, 30);
		btnMini.setBounds(260, 0, 30, 30);
		btnClose.setBounds(290, 0, 30, 30);

		// Add the new buttons
		titleBar.add(btnTheme);
		titleBar.add(btnMini);
		titleBar.add(btnClose);

		titleBar.revalidate();
		titleBar.repaint();
	}

	/**
	 * Add events with theme-aware decorators
	 */
	public void addEventsWithDecorators() {
		JButton[] numbers = { btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9 };
		JButton[] operations = { btnDiv, btnMult, btnSub, btnPlus, btnMod, btnEqual, btnClear, btnDel };

		// Apply theme-aware decorators
		for (JButton number : numbers) {
			number.addActionListener(e -> appendNumber(((JButton) e.getSource()).getText()));
			HoverDecorator.applyNumberHover(number, currentTheme);
			new ScaleHoverDecorator(number, 1.2f).apply();
			;
		}

		for (JButton op : operations) {
			HoverDecorator.applyOperationHover(op, currentTheme);
			new ScaleHoverDecorator(op, 1.3f).apply();
			;
		}

		HoverDecorator.applyNumberHover(btnDot, currentTheme);
		new ScaleHoverDecorator(btnDot, 1.2f).apply();
		;

		HoverDecorator.applyNumberHover(btnPlusSub, currentTheme);
		new ScaleHoverDecorator(btnPlusSub, 1.2f).apply();
		;

		HoverDecorator.applyCloseButtonHover(btnClose, currentTheme);
		HoverDecorator.applyMinimizeButtonHover(btnMini, currentTheme);
		HoverDecorator.applyMinimizeButtonHover(btnTheme, currentTheme);

		addActionListeners();
	}

	/**
	 * Attach action listeners to all buttons
	 */
	private void addActionListeners() {
		btnDot.addActionListener(e -> appendNumber(currentOperand.isBlank() ? "0." : "."));
		btnClear.addActionListener(e -> clear());
		btnDel.addActionListener(e -> {
			if (!currentOperand.equals("")) {
				currentOperand = currentOperand.substring(0, currentOperand.length() - 1);
				updateDisplay();
			}
		});

		btnPlus.addActionListener(e -> chooseOperation("+"));
		btnMod.addActionListener(e -> chooseOperation("%"));
		btnSub.addActionListener(e -> chooseOperation("-"));
		btnMult.addActionListener(e -> chooseOperation("×"));
		btnDiv.addActionListener(e -> chooseOperation("÷"));
		btnUndo.addActionListener(e -> undo());
		btnRedo.addActionListener(e -> redo());

		btnEqual.addActionListener(e -> {
			compute();
			updateDisplay();
			if (currentOperand.equals("Error")) {
				currentOperand = "";
			}
		});

		btnPlusSub.addActionListener(e -> {
			if (!currentOperand.isBlank()) {
				double tmp = -Double.parseDouble(currentOperand);
				currentOperand = String.valueOf(tmp);
				updateDisplay();
			}
		});

		// Window controls
		btnClose.addActionListener(e -> System.exit(0));
		btnMini.addActionListener(e -> setState(Calculator.ICONIFIED));
		btnTheme.addActionListener(e -> toggleTheme());
	}

	private void addKeyboardShortcuts() {
		app.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Ctrl+Z for Undo
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
					undo();
				}
				// Ctrl+Y for Redo
				else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
					redo();
				}
				// Ctrl+T for Theme toggle
				else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_T) {
					toggleTheme();
				}
			}
		});
		app.setFocusable(true);
		app.requestFocusInWindow();
	}

	public void clear() {
		currentOperand = "";
		previousOperand = "";
		operation = "";
		updateDisplay();
	}

	private void applyThemeToComponents() {
		app.setBackground(currentTheme.getAppBackground());
		resultsPanel.setBackground(currentTheme.getAppBackground());
		buttonsPanel.setBackground(currentTheme.getButtonsPanelBackground());
		titleBar.setBackground(currentTheme.getTitleBarBackground());

		previous.setBackground(currentTheme.getPreviousDisplayBackground());
		previous.setForeground(currentTheme.getPreviousDisplayForeground());
		previous.setFont(currentTheme.getPreviousDisplayFont());

		current.setBackground(currentTheme.getDisplayBackground());
		current.setForeground(currentTheme.getDisplayForeground());
		current.setFont(currentTheme.getDisplayFont());

		title.setForeground(currentTheme.getButtonForeground());
		title.setFont(currentTheme.getTitleFont());

		setTitle("Calculator - " + currentTheme.getThemeName() + " Theme");
	}

	/**
	 * Toggle between themes (Strategy Pattern)
	 */
	private void toggleTheme() {
		// Cycle through themes: Dark → Light → Neon → Pastel → Dark
		if (currentTheme instanceof DarkThemeStrategy) {
			currentTheme = new LightThemeStrategy();
		} else if (currentTheme instanceof LightThemeStrategy) {
			currentTheme = new NeonThemeStrategy();
		} else if (currentTheme instanceof NeonThemeStrategy) {
			currentTheme = new PastelThemeStrategy();
		} else { // if (currentTheme instanceof PastelThemeStrategy)
			currentTheme = new DarkThemeStrategy();
		}

		// Apply new theme
		applyThemeToComponents();
		rebuildButtonsWithTheme();
		addEventsWithDecorators();

		// Refresh display
		updateDisplay();

		buttonsPanel.revalidate();
		buttonsPanel.repaint();
		System.out.println("Theme switched to: " + currentTheme.getThemeName());
	}

	public void appendNumber(String number) {
		if (currentOperand.equals("0") && number.equals("0"))
			return;
		if (number.equals(".") && currentOperand.contains("."))
			return;
		if (currentOperand.equals("0") && !number.equals("0") && !number.equals(".")) {
			currentOperand = "";
		}
		currentOperand += number;
		updateDisplay();
	}

	public void chooseOperation(String op) {
		if (currentOperand.isEmpty() && !previousOperand.isEmpty()) {
			operation = op;
			updateDisplay();
			return;
		}
		if (currentOperand.isEmpty())
			return;
		if (!previousOperand.isEmpty()) {
			compute();
		}
		operation = op;
		previousOperand = currentOperand;
		currentOperand = "";
		updateDisplay();
	}

	public void compute() {
		if (currentOperand.isEmpty() || previousOperand.isEmpty()) {
			return;
		}

		try {
			double prev = Double.parseDouble(previousOperand);
			double curr = Double.parseDouble(currentOperand);

			// Use Facade - it handles Factory, RoundingDecorator, LoggingDecorator
			CalculatorFacade facade = new CalculatorFacade(true); // Enable logging
			double result = facade.calculate(prev, curr, operation);

			currentOperand = String.valueOf(result);
			previousOperand = "";
			operation = "";

			saveState();
		} catch (ArithmeticException e) {
			currentOperand = "Error";
		} catch (NumberFormatException e) {
			currentOperand = "";
		}
	}

	public void updateDisplay() {
		current.setText(currentOperand);
		previous.setText(previousOperand + " " + operation);
	}
}
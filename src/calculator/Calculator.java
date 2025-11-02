package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import creational.CalculatorButtonBuilder;
import creational.Operation;
import creational.OperationFactory;
import structural.CalculatorFacade;
import structural.HoverDecorator;
import structural.ScaleHoverDecorator;

/**
 *
 * @author youcefhmd
 */
public final class Calculator extends JFrame {

    private static Calculator instance;
    private String currentOperand;
    private String previousOperand;
    private String operation;
    private int x, y;

    // UI Components
    private JPanel app, resultsPanel, buttonsPanel, titleBar;
    private JTextField current, previous;
    private JLabel title;

<<<<<<< Updated upstream
    // Calculator buttons
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton btnPlus, btnSub, btnMult, btnDiv, btnEqual;
    private JButton btnClear, btnDel, btnDot, btnPlusSub;
    private JButton btnClose, btnMini;
=======
	// Calculator buttons
	private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	private JButton btnMod, btnPlus, btnSub, btnMult, btnDiv, btnEqual;
	private JButton btnClear, btnDel, btnDot, btnPlusSub;
	private JButton btnClose, btnMini;
>>>>>>> Stashed changes

    private Calculator() {
        initComponents(); // Create panels first
        rebuildButtonsWithBuilder(); // Then create buttons
        clear();
        addEventsWithDecorators();
        addActionListeners(); // ✅ reattach operational button events

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
        setTitle("Calculator");
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

        // Set content pane
        setContentPane(app);
        pack();
        setSize(320, 530);
    }

<<<<<<< Updated upstream
    /**
     * Rebuild all buttons using the Builder Pattern
     */
    private void rebuildButtonsWithBuilder() {
        // NUMBER BUTTONS (0-9)
        btn0 = CalculatorButtonBuilder.createNumberButton("0");
        btn1 = CalculatorButtonBuilder.createNumberButton("1");
        btn2 = CalculatorButtonBuilder.createNumberButton("2");
        btn3 = CalculatorButtonBuilder.createNumberButton("3");
        btn4 = CalculatorButtonBuilder.createNumberButton("4");
        btn5 = CalculatorButtonBuilder.createNumberButton("5");
        btn6 = CalculatorButtonBuilder.createNumberButton("6");
        btn7 = CalculatorButtonBuilder.createNumberButton("7");
        btn8 = CalculatorButtonBuilder.createNumberButton("8");
        btn9 = CalculatorButtonBuilder.createNumberButton("9");
        btnDot = CalculatorButtonBuilder.createNumberButton(".");
=======
		// OPERATION BUTTONS
		btnMod = CalculatorButtonBuilder.createOperationButton("%");
		btnPlus = CalculatorButtonBuilder.createOperationButton("+");
		btnSub = CalculatorButtonBuilder.createOperationButton("-");
		btnMult = CalculatorButtonBuilder.createOperationButton("×");
		btnDiv = CalculatorButtonBuilder.createOperationButton("÷");
		btnEqual = CalculatorButtonBuilder.createOperationButton("=");
>>>>>>> Stashed changes

        // OPERATION BUTTONS
        btnPlus = new CalculatorButtonBuilder("+").ofType(CalculatorButtonBuilder.ButtonType.OPERATION)
                .withPreferredSize(70, 140) // ← ADD THIS LINE
                .build();
        btnSub = CalculatorButtonBuilder.createOperationButton("-");
        btnMult = CalculatorButtonBuilder.createOperationButton("×");
        btnDiv = CalculatorButtonBuilder.createOperationButton("÷");
        btnEqual = CalculatorButtonBuilder.createOperationButton("=");

        // FUNCTION BUTTONS
        btnClear = CalculatorButtonBuilder.createFunctionButton("C");
        btnDel = CalculatorButtonBuilder.createFunctionButton("←");
        btnPlusSub = CalculatorButtonBuilder.createNumberButton("+/-");

        // WINDOW BUTTONS
        btnClose = CalculatorButtonBuilder.createWindowButton("×");
        btnMini = CalculatorButtonBuilder.createWindowButton("-");

        // Add buttons to panels
        addButtonsToPanel();
        addWindowButtonsToTitleBar();
    }

    /**
     * Add buttons to panel using null layout (Eclipse-compatible)
     */
    private void addButtonsToPanel() {
        // Row 1: Del, Clear, Div, Mult
        btnDel.setBounds(20, 20, 70, 70);
        btnClear.setBounds(90, 20, 70, 70);
        btnDiv.setBounds(160, 20, 70, 70);
        btnMult.setBounds(230, 20, 70, 70);

<<<<<<< Updated upstream
        // Row 2: 7, 8, 9, Sub
        btn7.setBounds(20, 90, 70, 70);
        btn8.setBounds(90, 90, 70, 70);
        btn9.setBounds(160, 90, 70, 70);
        btnSub.setBounds(230, 90, 70, 70);

        // Row 3: 4, 5, 6, Plus
        btn4.setBounds(20, 160, 70, 70);
        btn5.setBounds(90, 160, 70, 70);
        btn6.setBounds(160, 160, 70, 70);
        btnPlus.setBounds(230, 160, 70, 140);
=======
		// Row 3: 4, 5, 6, Plus
		btn4.setBounds(20, 160, 70, 70);
		btn5.setBounds(90, 160, 70, 70);
		btn6.setBounds(160, 160, 70, 70);
		btnPlus.setBounds(230, 160, 70, 70);

		// Row 4: 1, 2, 3
		btn1.setBounds(20, 230, 70, 70);
		btn2.setBounds(90, 230, 70, 70);
		btn3.setBounds(160, 230, 70, 70);
		btnMod.setBounds(230, 230, 70, 70);
>>>>>>> Stashed changes

        // Row 4: 1, 2, 3
        btn1.setBounds(20, 230, 70, 70);
        btn2.setBounds(90, 230, 70, 70);
        btn3.setBounds(160, 230, 70, 70);

<<<<<<< Updated upstream
        // Row 5: +/-, 0, ., =
        btnPlusSub.setBounds(20, 300, 70, 70);
        btn0.setBounds(90, 300, 70, 70);
        btnDot.setBounds(160, 300, 70, 70);
        btnEqual.setBounds(230, 300, 70, 70);
=======
		// Add all buttons
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
	}
>>>>>>> Stashed changes

        // Add all buttons
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
        buttonsPanel.add(btnPlusSub);
        buttonsPanel.add(btn0);
        buttonsPanel.add(btnDot);
        buttonsPanel.add(btnEqual);
    }

    /**
     * Add window control buttons to title bar
     */
    private void addWindowButtonsToTitleBar() {
        btnMini.setBounds(260, 0, 30, 30);
        btnClose.setBounds(290, 0, 30, 30);

<<<<<<< Updated upstream
        titleBar.add(btnMini);
        titleBar.add(btnClose);
    }

    /**
     * Add events with Decorator Pattern for hover effects
     */
    public void addEventsWithDecorators() {
        // Group buttons logically
        JButton[] numbers = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        JButton[] operations = {btnDiv, btnMult, btnSub, btnPlus, btnEqual, btnClear, btnDel};

        // 🧮 Apply decorators to number buttons
        for (JButton number : numbers) {
            number.addActionListener(e -> appendNumber(number.getText()));

            // Apply hover color
            new HoverDecorator(number, new Color(73, 69, 78)).apply();

            // Apply font scaling (slightly smaller)
            new ScaleHoverDecorator(number, 1.2f).apply();
        }

        // ➕ Apply decorators to operation buttons
        for (JButton op : operations) {
            // Hover color (same background)
            new HoverDecorator(op, new Color(73, 69, 78)).apply();

            // Slightly larger scale for visibility
            new ScaleHoverDecorator(op, 1.3f).apply();
        }
=======
	/**
	 * Add events with Decorator Pattern for hover effects
	 */
	public void addEventsWithDecorators() {
		JButton[] numbers = { btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9 };
		JButton[] operations = { btnDiv, btnMult, btnSub, btnPlus, btnEqual, btnClear, btnDel, btnMod };

		// Number buttons: Color + Font scale
		for (JButton number : numbers) {
			number.addActionListener(e -> appendNumber(((JButton) e.getSource()).getText()));
			HoverDecorator.applyNumberHover(number);
			new ScaleHoverDecorator(number, 1.2f); // Font 20% bigger
		}

		// Operation buttons: Color + Font scale
		for (JButton op : operations) {
			HoverDecorator.applyOperationHover(op);
			new ScaleHoverDecorator(op, 1.3f); // Font 30% bigger
		}

		// Special buttons
		HoverDecorator.applyNumberHover(btnDot);
		new ScaleHoverDecorator(btnDot, 1.2f);

		HoverDecorator.applyNumberHover(btnPlusSub);
		new ScaleHoverDecorator(btnPlusSub, 1.2f);

		// Window buttons (color only, no font scaling)
		HoverDecorator.applyCloseButtonHover(btnClose);
		HoverDecorator.applyMinimizeButtonHover(btnMini);

		getMouseMotionListeners();
		addActionListeners();
	}
>>>>>>> Stashed changes

        // ⚙️ Special buttons (dot and +/-)
        new HoverDecorator(btnDot, new Color(73, 69, 78)).apply();
        new ScaleHoverDecorator(btnDot, 1.2f).apply();

<<<<<<< Updated upstream
        new HoverDecorator(btnPlusSub, new Color(73, 69, 78)).apply();
        new ScaleHoverDecorator(btnPlusSub, 1.2f).apply();
=======
		btnPlus.addActionListener(e -> chooseOperation("+"));
		btnMod.addActionListener(e -> chooseOperation("%"));
		btnSub.addActionListener(e -> chooseOperation("-"));
		btnMult.addActionListener(e -> chooseOperation("×"));
		btnDiv.addActionListener(e -> chooseOperation("÷"));
>>>>>>> Stashed changes

        // 🪟 Window control buttons (close & minimize)
        new HoverDecorator(btnClose, new Color(255, 75, 75), new Color(31, 30, 33)).apply();
        new HoverDecorator(btnMini, new Color(73, 69, 78)).apply();
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
        btnSub.addActionListener(e -> chooseOperation("-"));
        btnMult.addActionListener(e -> chooseOperation("×"));
        btnDiv.addActionListener(e -> chooseOperation("÷"));

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
    }

    public void clear() {
        currentOperand = "";
        previousOperand = "";
        operation = "";
        updateDisplay();
    }

    public void appendNumber(String number) {
        if (currentOperand.equals("0") && number.equals("0")) {
            return;
        }
        if (number.equals(".") && currentOperand.contains(".")) {
            return;
        }
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
        if (currentOperand.isEmpty()) {
            return;
        }
        if (!previousOperand.isEmpty()) {
            compute();
        }
        operation = op;
        previousOperand = currentOperand;
        currentOperand = "";
        updateDisplay();
    }

    public void compute() {
        try {
            double prev = Double.parseDouble(previousOperand);
            double curr = Double.parseDouble(currentOperand);
            CalculatorFacade facade = new CalculatorFacade(true);
            double result = facade.calculate(prev, curr, operation);
            currentOperand = String.valueOf(result);
            previousOperand = "";
            operation = "";
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

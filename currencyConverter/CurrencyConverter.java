package currencyConverter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {

	private JTextField inputField;
	private JLabel outputLabel;
	private JComboBox<String> fromComboBox, toComboBox;

	private static final String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "HKD"};
	private static final double[][] conversionRates = {
			{1.0, 0.85, 109.60, 0.72, 1.30, 1.26, 0.92, 6.47, 7.76}, // USD
			{1.18, 1.0, 128.35, 0.85, 1.54, 1.49, 1.09, 7.69, 9.21}, // EUR
			{0.0091, 0.0078, 1.0, 0.0067, 0.012, 0.011, 0.008, 0.055, 0.066}, // JPY
			{1.39, 1.18, 152.06, 1.0, 1.81, 1.75, 1.28, 9.00, 10.79}, // GBP
			{0.77, 0.66, 85.28, 0.56, 1.0, 0.97, 0.71, 4.99, 5.99}, // AUD
			{0.79, 0.68, 87.92, 0.58, 1.03, 1.0, 0.73, 5.13, 6.14}, // CAD
			{1.09, 0.93, 119.61, 0.79, 1.44, 1.39, 1.0, 7.02, 8.41}, // CHF
			{0.15, 0.13, 16.68, 0.11, 0.20, 0.19, 0.14, 1.0, 1.20}, // CNY
			{0.13, 0.11, 14.10, 0.093, 0.17, 0.16, 0.12, 0.84, 1.0} // HKD
	};

	public CurrencyConverter() {
		super("Currency Converter");

		// Set up input field
		inputField = new JTextField(10);
		inputField.addActionListener(this);

		// Set up "from" combo box
		fromComboBox = new JComboBox<>(currencies);
		fromComboBox.addActionListener(this);

		// Set up "to" combo box
		toComboBox = new JComboBox<>(currencies);
		toComboBox.addActionListener(this);

		// Set up output label
		outputLabel = new JLabel(" ");

		// Set up content pane
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(10, 5));
		contentPane.add(new JLabel("Enter amount:"));
		contentPane.add(inputField);
		contentPane.add(new JLabel("Convert from:"));
		contentPane.add(fromComboBox);
		contentPane.add(new JLabel("Convert to:"));
		contentPane.add(toComboBox);
		contentPane.add(outputLabel);
		setContentPane(contentPane);

		// Set up frame
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		double amount;
		String fromCurrency, toCurrency;
		int fromIndex, toIndex;

		// Get input amount
		try {
			amount = Double.parseDouble(inputField.getText());
		} catch (NumberFormatException ex) {
			outputLabel.setText("Invalid amount");
			return;
		}

		// Get "from" currency
		fromIndex = fromComboBox.getSelectedIndex();
		fromCurrency = currencies[fromIndex];

		// Get "to" currency
		toIndex = toComboBox.getSelectedIndex();
		toCurrency = currencies[toIndex];

		// Convert currency
		double conversionRate = conversionRates[fromIndex][toIndex];
		double result = amount * conversionRate;

		// Update output label
		String outputText = String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency);
		outputLabel.setText(outputText);
	}

	public static void main(String[] args) {
		new CurrencyConverter();
	}
}



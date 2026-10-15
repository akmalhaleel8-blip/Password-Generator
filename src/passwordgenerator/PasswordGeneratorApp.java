
package passwordgenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class PasswordGeneratorApp extends JFrame {

    private static final long serialVersionUID = 1L;

    private final SecureRandom random = new SecureRandom();

    private JSpinner lengthSpinner;
    private JCheckBox uppercaseBox;
    private JCheckBox lowercaseBox;
    private JCheckBox numbersBox;
    private JCheckBox symbolsBox;
    private JTextField passwordField;

    private static final String UPPERCASE =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWERCASE =
            "abcdefghijklmnopqrstuvwxyz";

    private static final String NUMBERS =
            "0123456789";

    private static final String SYMBOLS =
            "!@#$%^&*()-_=+[]{}?";

    public PasswordGeneratorApp() {

        setTitle("Password Generator");
        setSize(480, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(
                20, 20, 20, 20));

        JLabel title = new JLabel(
                "PASSWORD GENERATOR",
                JLabel.CENTER);

        title.setFont(
                new java.awt.Font("Arial",
                        java.awt.Font.BOLD, 20));

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(
                new GridLayout(0, 1, 8, 8));

        centerPanel.add(new JLabel("Password Length:"));

        lengthSpinner = new JSpinner(
                new SpinnerNumberModel(16, 4, 64, 1));

        centerPanel.add(lengthSpinner);

        uppercaseBox = new JCheckBox(
                "Uppercase Letters (A-Z)", true);

        lowercaseBox = new JCheckBox(
                "Lowercase Letters (a-z)", true);

        numbersBox = new JCheckBox(
                "Numbers (0-9)", true);

        symbolsBox = new JCheckBox(
                "Special Characters (!@#$)", true);

        centerPanel.add(uppercaseBox);
        centerPanel.add(lowercaseBox);
        centerPanel.add(numbersBox);
        centerPanel.add(symbolsBox);

        centerPanel.add(new JLabel("Generated Password:"));

        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setFont(
                new java.awt.Font("Monospaced",
                        java.awt.Font.PLAIN, 15));

        centerPanel.add(passwordField);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton generateButton =
                new JButton("Generate Password");

        JButton copyButton =
                new JButton("Copy Password");

        JButton clearButton =
                new JButton("Clear");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 8, 5));

        buttonPanel.add(generateButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        generateButton.addActionListener(e ->
                generatePassword());

        copyButton.addActionListener(e ->
                copyPassword());

        clearButton.addActionListener(e -> {
            passwordField.setText("");
            lengthSpinner.setValue(16);
            uppercaseBox.setSelected(true);
            lowercaseBox.setSelected(true);
            numbersBox.setSelected(true);
            symbolsBox.setSelected(true);
        });
    }

    private void generatePassword() {

        StringBuilder characters = new StringBuilder();

        if (uppercaseBox.isSelected()) {
            characters.append(UPPERCASE);
        }

        if (lowercaseBox.isSelected()) {
            characters.append(LOWERCASE);
        }

        if (numbersBox.isSelected()) {
            characters.append(NUMBERS);
        }

        if (symbolsBox.isSelected()) {
            characters.append(SYMBOLS);
        }

        if (characters.length() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select at least one character type.",
                    "Selection Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int length = (Integer) lengthSpinner.getValue();

        StringBuilder password = new StringBuilder();

        // Ensure every selected character group is represented.
        if (uppercaseBox.isSelected()) {
            password.append(randomChar(UPPERCASE));
        }

        if (lowercaseBox.isSelected()) {
            password.append(randomChar(LOWERCASE));
        }

        if (numbersBox.isSelected()) {
            password.append(randomChar(NUMBERS));
        }

        if (symbolsBox.isSelected()) {
            password.append(randomChar(SYMBOLS));
        }

        // Fill the remaining positions randomly.
        while (password.length() < length) {
            password.append(
                    randomChar(characters.toString()));
        }

        // Shuffle so required characters aren't always at
        // the beginning of the generated password.
        char[] result = password.toString().toCharArray();

        for (int i = result.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }

        passwordField.setText(new String(result));

        try {
            HistoryDAO.saveHistory(
                length,
                uppercaseBox.isSelected(),
                lowercaseBox.isSelected(),
                numbersBox.isSelected(),
                symbolsBox.isSelected()
            );

        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(
                this,
                "Password generated, but history could not be saved.",
                "Database Warning",
                JOptionPane.WARNING_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private char randomChar(String characterSet) {
        return characterSet.charAt(
                random.nextInt(characterSet.length()));
    }

    private void copyPassword() {

        String password = passwordField.getText();

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Generate a password before copying.",
                    "No Password",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringSelection selection =
                new StringSelection(password);

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(selection, null);

        JOptionPane.showMessageDialog(
                this,
                "Password copied to clipboard.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            PasswordGeneratorApp app =
                    new PasswordGeneratorApp();

            app.setVisible(true);
        });
    }
}

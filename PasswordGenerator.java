import javax.swing.*;
import java.security.SecureRandom;

public class PasswordGenerator extends JFrame {
    private JCheckBox upperCaseCheckBox;
    private JCheckBox lowerCaseCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JTextField lengthField;
    private JTextArea passwordArea;

    public PasswordGenerator() {
        setTitle("Password Generator");
        setSize(400, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Password Length:"));
        lengthField = new JTextField(10);
        lengthField.setMaximumSize(lengthField.getPreferredSize());
        add(lengthField);

        upperCaseCheckBox = new JCheckBox("Include Uppercase Letters");
        lowerCaseCheckBox = new JCheckBox("Include Lowercase Letters");
        numbersCheckBox = new JCheckBox("Include Numbers");
        specialCharsCheckBox = new JCheckBox("Include Special Characters");

        add(upperCaseCheckBox);
        add(lowerCaseCheckBox);
        add(numbersCheckBox);
        add(specialCharsCheckBox);

        JButton generateButton = new JButton("Generate Password");
        generateButton.addActionListener(e -> generatePassword());
        add(generateButton);

        passwordArea = new JTextArea(5, 30);
        passwordArea.setEditable(false);
        add(new JScrollPane(passwordArea));

        setVisible(true);
    }

    private void generatePassword() {
        String lengthText = lengthField.getText().trim();
        int length;
        try {
            length = Integer.parseInt(lengthText);
            if (length <= 2) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for password length.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean includeUpper = upperCaseCheckBox.isSelected();
        boolean includeLower = lowerCaseCheckBox.isSelected();
        boolean includeNumbers = numbersCheckBox.isSelected();
        boolean includeSpecial = specialCharsCheckBox.isSelected();

        if (!includeUpper && !includeLower && !includeNumbers && !includeSpecial) {
            JOptionPane.showMessageDialog(this, "Please select at least one character type.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String password = PasswordGeneratorLogic.generatePassword(length, includeUpper, includeLower, includeNumbers, includeSpecial);
        passwordArea.setText(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasswordGenerator::new);
    }
}

class PasswordGeneratorLogic {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_+=<>?";

    public static String generatePassword(int length, boolean upper, boolean lower, boolean numbers, boolean special) {
        StringBuilder validChars = new StringBuilder();
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        if (upper) validChars.append(UPPERCASE);
        if (lower) validChars.append(LOWERCASE);
        if (numbers) validChars.append(NUMBERS);
        if (special) validChars.append(SPECIAL);

        // Add at least one of each selected type
        if (upper) password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        if (lower) password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        if (numbers) password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        if (special) password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // Fill the rest
        for (int i = password.length(); i < length; i++) {
            int index = random.nextInt(validChars.length());
            password.append(validChars.charAt(index));
        }

        // Shuffle the final password
        return shuffleString(password.toString(), random);
    }

    private static String shuffleString(String input, SecureRandom random) {
        char[] chars = input.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}

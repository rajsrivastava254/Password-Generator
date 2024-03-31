import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setSize(400, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Password Length:"));
        lengthField = new JTextField(10);
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
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
        add(generateButton);

        passwordArea = new JTextArea(5, 20);
        passwordArea.setEditable(false);
        add(new JScrollPane(passwordArea));

        setVisible(true);
    }

    private void generatePassword() {
        int length = Integer.parseInt(lengthField.getText());
        boolean includeUpperCase = upperCaseCheckBox.isSelected();
        boolean includeLowerCase = lowerCaseCheckBox.isSelected();
        boolean includeNumbers = numbersCheckBox.isSelected();
        boolean includeSpecialChars = specialCharsCheckBox.isSelected();

        String password = PasswordGenerator1.generatePassword(length, includeUpperCase, includeLowerCase, includeNumbers, includeSpecialChars);
        passwordArea.setText(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordGenerator();
            }
        });
    }
}

class PasswordGenerator1 {
    public static String generatePassword(int length, boolean includeUpperCase, boolean includeLowerCase, boolean includeNumbers, boolean includeSpecialChars) {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_+=<>?";
        StringBuilder validChars = new StringBuilder();
        SecureRandom random = new SecureRandom();

        if (includeUpperCase) {
            validChars.append(uppercaseChars);
        }
        if (includeLowerCase) {
            validChars.append(lowercaseChars);
        }
        if (includeNumbers) {
            validChars.append(numberChars);
        }
        if (includeSpecialChars) {
            validChars.append(specialChars);
        }

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(validChars.length());
            password.append(validChars.charAt(index));
        }

        return password.toString();
    }
}


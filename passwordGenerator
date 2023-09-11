import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUpperCase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowerCase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecialChars = scanner.nextLine().equalsIgnoreCase("yes");

        String password = generatePassword(length, includeUpperCase, includeLowerCase, includeNumbers, includeSpecialChars);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }

    public static String generatePassword(int length, boolean includeUpperCase, boolean includeLowerCase, boolean includeNumbers, boolean includeSpecialChars) {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_+=<>?";

        StringBuilder validChars = new StringBuilder();

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

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            char randomChar = validChars.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}

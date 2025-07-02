package BMIcalculator;

import java.util.Scanner;
import java.util.Locale;

public class BMIcalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        char repeat;

        do {
            int unitChoice = getUnitChoice(scanner);

            double weight = (unitChoice == 1)
                    ? getValidInput(scanner, "Enter your weight in kilograms: ", 10, 600)
                    : getValidInput(scanner, "Enter your weight in pounds: ", 22, 1300);

            double height = (unitChoice == 1)
                    ? getValidInput(scanner, "Enter your height in meters: ", 0.5, 2.5)
                    : getValidInput(scanner, "Enter your height in inches: ", 20, 100);

            double bmi = calculateBMI(unitChoice, weight, height);
            System.out.printf("Your BMI is: %.2f\n", bmi);

            repeat = askToRepeat(scanner);
            System.out.println();

        } while (repeat == 'Y' || repeat == 'y');

        scanner.close();
    }

    public static int getUnitChoice(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("Select a preferred unit:");
            System.out.println("1. Metric (kg, m)");
            System.out.println("2. Imperial (lbs, in)");
            System.out.print("Please select either option 1 or 2: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter either 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.next(); // consume invalid input
            }
        }

        return choice;
    }

    public static double getValidInput(Scanner scanner, String prompt, double min, double max) {
        double value;

        while (true) {
            System.out.println(prompt);

            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.printf("Please enter a value between %.1f and %.1f.\n", min, max);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid input
            }
        }

        return value;
    }

    public static double calculateBMI(int unitChoice, double weight, double height) {
        if (unitChoice == 1) {
            // Metric BMI formula
            return weight / (height * height);
        } else {
            // Imperial BMI formula
            return (703 * weight) / (height * height);
        }
    }

    public static char askToRepeat(Scanner scanner) {
        System.out.print("Would you like to calculate again? (Y/N): ");
        String input = scanner.next();
        return input.charAt(0);
    }
}

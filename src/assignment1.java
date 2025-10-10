
import java.util.Scanner;
public class assignment1 {

    public static  class SimpleCalculator {

        public static double add(double a, double b) {
            return a + b;
        }

        public static double subtract(double a, double b) {
            return a - b;
        }

        public static double multiply(double a, double b) {
            return a * b;
        }

        public static double divide(double a, double b) {
            if (b == 0) {
                System.out.println("Error: Division by zero!");
                return Double.NaN;
            }
            return a / b;
        }

        public static void displayMenu() {
            System.out.println("Simple Calculator");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.print("Choose an operation: ");
        }

        // Encapsulate the calculator process in a function
        public static void processCalculator() {
            Scanner scanner = new Scanner(System.in);
            int choice;
            double num1, num2, result;

            while (true) {
                displayMenu();
                choice = scanner.nextInt();

                if (choice == 5) {
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                }

                System.out.print("Enter first number: ");
                num1 = scanner.nextDouble();
                System.out.print("Enter second number: ");
                num2 = scanner.nextDouble();

                switch (choice) {
                    case 1:
                        result = add(num1, num2);
                        System.out.println("Result: " + result);
                        break;
                    case 2:
                        result = subtract(num1, num2);
                        System.out.println("Result: " + result);
                        break;
                    case 3:
                        result = multiply(num1, num2);
                        System.out.println("Result: " + result);
                        break;
                    case 4:
                        result = divide(num1, num2);
                        System.out.println("Result: " + result);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
                System.out.println();
            }
            scanner.close();
        }

        public static void main(String[] args) {
            processCalculator();
        }
    }
}
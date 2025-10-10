
import java.util.Scanner;
public class assignment2 {

    public static class simplehotelbooking {
        static String[][] rooms = new String[3][3]; // 3 floors, 3 rooms each
        static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            run();
            scanner.close();
        }

        static void run() {
            boolean exit = false;
            while(!exit) {
                printMenu();
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> viewRooms();
                    case 2 -> bookRoom();
                    case 3 -> {
                        exit = true;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }

        static void printMenu() {
            System.out.println("\n1. View Rooms  2. Book Room  3. Exit");
            System.out.print("Choose option: ");
        }

        static void viewRooms() {
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[i].length; j++) {
                    String status = (rooms[i][j] == null) ? "Available" : "Booked BY ";
                    System.out.println("Floor " + (i + 1) + " Room " + (j + 1) + ": " + status);
                }
            }
        }

        static void bookRoom() {
            System.out.print("Enter floor (1-3): ");
            int floor = scanner.nextInt();
            System.out.print("Enter room (1-3): ");
            int room = scanner.nextInt();

            if (floor < 1 || floor > 3 || room < 1 || room > 3) {
                System.out.println("Invalid floor or room.");
                return;
            }
            if (rooms[floor - 1][room - 1] != null) {
                System.out.println("Room already booked  ");
                return;
            }
            scanner.nextLine(); // consume newline
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            rooms[floor - 1][room - 1] = name;
            System.out.println("Room booked succesfully.");
        }
    }
}

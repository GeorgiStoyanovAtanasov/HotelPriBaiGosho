import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> freeRooms = new ArrayList<Integer>();
        for (int i = 1; i < 204; i++) {
            if (i % 9 == 0 || i % 1.5 == 0) {

                freeRooms.add(i);
            }
        }
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Please select what you want to do (1 - Make a reservation; 2- List free rooms; 3 - Checkout room; 4 - Stats; 5 - Find a room; 6 - Update a room; 7 - Reset)");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter room: ");
                    int roomChoice = sc.nextInt();
                    if (freeRooms.contains(roomChoice)) {
                        System.out.println("Enter starting date");
                        String userInput = sc.next();


                        String[] parts = userInput.split("\\.");


                        int part1 = Integer.parseInt(parts[0]);
                        int part2 = Integer.parseInt(parts[1]);
                        int part3 = Integer.parseInt(parts[2]);
                        System.out.println("End date: ");
                        String EndDateChoice = sc.next();
                        System.out.println("Enter notes: ");
                        String notes = sc.next();
                        System.out.println("Reservation was created!");
                        int n = freeRooms.indexOf(roomChoice);
                        freeRooms.remove(n);
                    }

                    break;
                case 2:
                    System.out.println();
                    System.out.println("The room which are currently free are " + freeRooms);

                    break;
                case 3:


                    break;
                case 4:


                    break;
                case 5:

                    break;
                case 6:

                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6 );
    }


}

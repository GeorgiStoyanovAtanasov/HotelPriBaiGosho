

import java.util.ArrayList;
import java.util.Scanner;

// i will make an array list for all the rooms and fill it using for loop, let's  say the number of rooms will be 203 and when the person asks to list all free rooms i would simply print the array list.
//
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> freeRooms = new ArrayList<Integer>();
        for (int i = 1; i < 204; i++) {
            if (i % 9 == 0 || i % 1.5 == 0) {

                freeRooms.add(i);
            }

        }


        Scanner sc = new Scanner(System.in);
        System.out.println("Please select what you want to do (1 - Make a reservation; 2- List free rooms; 3 - Checkout room; 4 - Stats; 5 - Find a room; 6 - Update a room)");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.println("Enter room: ");
            int roomChoice = sc.nextInt();
            if (freeRooms.contains(roomChoice)) {
                String userInput = sc.next();


                String[] parts = userInput.split("\\.");


                int part1 = Integer.parseInt(parts[0]);
                int part2 = Integer.parseInt(parts[1]);
                int part3 = Integer.parseInt(parts[2]);



            }
            // System.out.println("The room which are currently free are " + freeRooms);

        }
        System.out.println(freeRooms);

    }
}
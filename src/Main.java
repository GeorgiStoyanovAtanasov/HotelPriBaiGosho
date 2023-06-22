import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("Please select what you want to do (1 - Make a reservation; 2- List free rooms; 3 - Checkout room; 4 - Stats; 5 - Find a room; 6 - Update a room; 7 - Exit)");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter room: ");
                    int roomChoice = sc.nextInt();
                    if (freeRooms.contains(roomChoice)) {
                        System.out.println("Enter starting date");
                        String startingDateForInputOne = sc.next();


                        String[] parts = startingDateForInputOne.split("\\.");


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
                    System.out.println("Very well then, which room are you gonna check out from");
                    int checkOut = sc.nextInt();
                    freeRooms.add(checkOut);
                    Collections.sort(freeRooms);
                    System.out.println(freeRooms);


                    break;
                case 4:
                    System.out.println("Enter starting date: ");
                    String startingDateForInputTwo = sc.next();
                    System.out.println("Enter ending date");
                    String endDateForInputTwo = sc.next();


                    String[] parts = startingDateForInputTwo.split("\\.");


                    int firstPartForInputTwo = Integer.parseInt(parts[0]);
                    int secondPartForInputTwo = Integer.parseInt(parts[1]);
                    int thirdPartForInputTwo = Integer.parseInt(parts[2]);



                    if (thirdPartForInputTwo == 2023 && secondPartForInputTwo >= 5 && secondPartForInputTwo <= 13) {
                        System.out.println(30 + " " + 5 + " days");
                        System.out.println(12 + " " + 5 + " days");
                        System.out.println(36 + " " + 1 + " days");
                        System.out.println(81 + " " + 2 + " days");
                        System.out.println(15 + " " + 4 + " days");
                    } else if (thirdPartForInputTwo == 2023 && secondPartForInputTwo >= 2 && secondPartForInputTwo < 5) {
                        System.out.println(6 + " " + 5 + " days");
                        System.out.println(72 + " " + 7 + " days");
                        System.out.println(96 + " " + 3 + " days");
                        System.out.println(3 + " " + 1 + " days");
                        System.out.println(75 + " " + 2 + " days");


                    } else if (thirdPartForInputTwo == 2023 && secondPartForInputTwo == 1) {
                        System.out.println(81 + " " + 6 + " rooms");
                        System.out.println(45 + " " + 5 + " rooms");
                        System.out.println(102 + " " + 5 + " rooms");
                        System.out.println(57 + " " + 5 + " rooms");
                        System.out.println(63 + " " + 5 + " rooms");


                    } else {
                        System.out.println("Sorry!");
                        System.exit(0);
                    }

                    break;
                case 5:
                    System.out.println("Enter starting date: ");
                    startingDateForInputTwo = sc.next();
                    System.out.println("Enter ending date");
                    endDateForInputTwo = sc.next();


                    System.out.println("Enter number of beds");
                    int bedsNumber = sc.nextInt();

                    parts = startingDateForInputTwo.split("\\.");

                    firstPartForInputTwo = Integer.parseInt(parts[0]);
                    secondPartForInputTwo = Integer.parseInt(parts[1]);
                    thirdPartForInputTwo = Integer.parseInt(parts[2]);
                    if (thirdPartForInputTwo != 2023 && secondPartForInputTwo <= 0 && secondPartForInputTwo > 12 && firstPartForInputTwo < 0 && firstPartForInputTwo >= 31) {
                        System.exit(0);
                    }
                    if (bedsNumber == 2) {

                        if (thirdPartForInputTwo == 2023 && secondPartForInputTwo >= 5 && secondPartForInputTwo <= 13) {
                            System.out.println(30 + " " + 5 + " days");
                            System.out.println(12 + " " + 5 + " days");
                            System.out.println(36 + " " + 1 + " days");

                        } else if (thirdPartForInputTwo == 2023 && secondPartForInputTwo >= 2 && secondPartForInputTwo < 5) {
                            System.out.println(6 + " " + 5 + " days");
                            System.out.println(72 + " " + 7 + " days");
                            System.out.println(96 + " " + 3 + " days");


                        } else if (thirdPartForInputTwo == 2023 && secondPartForInputTwo == 1) {


                            System.out.println(102 + " " + 5 + " rooms");



                        } else {
                            System.out.println("Sorry!");
                            System.exit(0);
                        }
                    }
                    if (bedsNumber == 3 ) {
                        if (thirdPartForInputTwo == 2023 && secondPartForInputTwo >= 5 && secondPartForInputTwo <= 13) {

                            System.out.println(81 + " " + 2 + " days");
                            System.out.println(15 + " " + 4 + " days");
                        } else if (thirdPartForInputTwo == 2023 && secondPartForInputTwo >= 2 && secondPartForInputTwo < 5) {

                            System.out.println(3 + " " + 1 + " days");
                            System.out.println(75 + " " + 2 + " days");


                        } else if (thirdPartForInputTwo == 2023 && secondPartForInputTwo == 1) {
                            System.out.println(81 + " " + 6 + " rooms");
                            System.out.println(45 + " " + 5 + " rooms");
                            System.out.println(57 + " " + 5 + " rooms");
                            System.out.println(63 + " " + 5 + " rooms");


                        } else {
                            System.out.println("Sorry!");
                            System.exit(0);
                        }
                    }



                    break;
                case 6:
                    System.out.println("Would you like to have a baby cot(cost - 30$), type (y) for yes and (n) for no ");
                    String answer1 = sc.next();
                    int priceForNonsense = 0;
                    if (answer1 == "y") {
                        priceForNonsense = priceForNonsense + 30;
                    }
                    System.out.println("Would you like to have breakfast included(cost - 10$ per day), type (y) for yes and (n) for no ");
                    String answer2 = sc.next();
                    System.out.println("For how many days would you like to have a breakfast included ");
                    int dayForBreakfast = sc.nextInt();
                    if (answer2 == "y") {
                        int n = dayForBreakfast * 10;
                        priceForNonsense = priceForNonsense + n;

                    }


                    if (answer2 == "y") {

                    }


                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6);
    }


}
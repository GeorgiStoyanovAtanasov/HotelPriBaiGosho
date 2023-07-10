import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class testing {
    private static ArrayList<Integer> freeRooms;

    public static void main(String[] args) {
        freeRooms = new ArrayList<Integer>();
        for (int i = 1; i < 204; i++) {
            if (i % 9 == 0) {
                freeRooms.add(i);
            }
        }

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Please select what you want to do (1 - Make a reservation; 2 - List free rooms; 3 - Checkout room; 4 - Stats; 5 - Find a room; 6 - Update a room; 7 - Exit)");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    makeReservation(sc);
                    break;
                case 2:
                    listFreeRooms();
                    break;
                case 3:
                    checkoutRoom(sc);
                    break;
                case 4:
                    showStats(sc);
                    break;
                case 5:
                    findRoom(sc);
                    break;
                case 6:
                    updateRoom(sc);
                    break;
                default:
                    System.out.println("Well then, goodbye friend of mine.");
                    System.exit(0);
                    break;
            }
        } while (choice >= 1 && choice <= 6);
    }

    private static void makeReservation(Scanner sc) {
        System.out.print("Enter room: ");
        int roomChoice = sc.nextInt();
        System.out.println();
        System.out.print("Enter a starting date (dd.MM.yyyy): ");
        String startingDate = sc.next();
        System.out.println();
        System.out.print("Enter an ending date (dd.MM.yyyy): ");
        String endingDate = sc.next();
        System.out.println();
        System.out.print("Enter notes: ");
        String notes = sc.next();
        System.out.println();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);

        try {
            Date inputDateFirst = dateFormat.parse(startingDate);
            Date inputDateSecond = dateFormat.parse(endingDate);
            Date currentDate = new Date();
            if (inputDateFirst.before(currentDate) || inputDateSecond.before(currentDate) || inputDateSecond.equals(inputDateFirst) || inputDateSecond.before(inputDateFirst)) {
                System.out.println("Invalid input.");
                System.exit(0);
            } else {
                System.out.println("Reservation created successfully");
            }
        } catch (ParseException e) {
            System.out.println("Invalid input");
        }
    }

    private static void listFreeRooms() {
        System.out.println();
        System.out.println("The rooms which are currently free are " + freeRooms);
        System.out.println();
    }

    private static void checkoutRoom(Scanner sc) {
        System.out.println("Very well then, which room are you going to check out from");
        int checkOut = sc.nextInt();
        freeRooms.add(checkOut);
        Collections.sort(freeRooms);
        System.out.println("You successfully checked out from room " + checkOut);
    }

    private static void showStats(Scanner sc) {
        System.out.print("Enter a starting date (dd.MM.yyyy): ");
        String startingDate = sc.next();
        System.out.println();
        System.out.print("Enter an ending date (dd.MM.yyyy): ");
        String endingDate = sc.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);

        try {
            Date inputDateFirst = dateFormat.parse(startingDate);
            Date inputDateSecond = dateFormat.parse(endingDate);
            long diffInMillies = Math.abs(inputDateSecond.getTime() - inputDateFirst.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            Date currentDate = new Date();
            if (inputDateFirst.after(currentDate) || inputDateSecond.after(currentDate) || inputDateSecond.equals(inputDateFirst) || inputDateSecond.before(inputDateFirst)) {
                System.out.println("Invalid input.");
                System.exit(0);
            } else {
                Random random = new Random(123);

                for (int i = 1; i <= diff; i++) {
                    int randomNumber = random.nextInt(5) + 1;
                    System.out.println("room " + (100 + i) + " for " + randomNumber + " days");
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid input");
        }
    }

    private static void findRoom(Scanner sc) {
        System.out.print("Enter a starting date (dd.MM.yyyy): ");
        String startingDate = sc.next();
        System.out.println();
        System.out.print("Enter an ending date (dd.MM.yyyy): ");
        String endingDate = sc.next();
        System.out.println();
        System.out.print("Enter number of beds: ");
        byte numberOfBeds = sc.nextByte();
        System.out.println();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);

        try {
            Date inputDateFirst = dateFormat.parse(startingDate);
            Date inputDateSecond = dateFormat.parse(endingDate);
            long diffInMillies = Math.abs(inputDateSecond.getTime() - inputDateFirst.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            Date currentDate = new Date();
            if (inputDateFirst.before(currentDate) || inputDateSecond.before(currentDate) || inputDateSecond.equals(inputDateFirst) || inputDateSecond.before(inputDateFirst)) {
                System.out.println("Invalid input.");
                System.exit(0);
            } else if (numberOfBeds == 2 || numberOfBeds == 1) {
                for (int i = 2; i <= diff; i++) {
                    if (i % 2 == 0) {
                        System.out.println("* " + (100 + i));
                    }
                }
            } else if (numberOfBeds == 3) {
                for (int i = 2; i <= diff; i++) {
                    if (i % 2 != 0) {
                        System.out.println("* " + (100 + i));
                    }
                }
            } else if (numberOfBeds >= 4 || numberOfBeds <= 0) {
                System.out.println("We are sincerely sorry but we currently don't offer rooms with this amount of beds: ");
            }

        } catch (ParseException e) {
            System.out.println("Invalid input");
        }
    }

    private static void updateRoom(Scanner sc) {
        System.out.println("Would you like to have a baby cot (cost - 30$)? Type (y) for yes and (n) for no ");
        String answer1 = sc.next();
        int priceForNonsense = 0;

        if (answer1.equals("y")) {
            priceForNonsense += 30;
        }

        System.out.println("Would you like to have breakfast included (cost - 10$ per day)? Type (y) for yes and (n) for no ");
        String answer2 = sc.next();
        System.out.println("For how many days would you like to have breakfast included? ");
        int dayForBreakfast = sc.nextInt();

        if (answer2.equals("y")) {
            int n = dayForBreakfast * 10;
            priceForNonsense += n;
        }
    }
}

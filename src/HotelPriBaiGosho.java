import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelPriBaiGosho {
    static List<Room> roomList = new ArrayList<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) throws ParseException {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        boolean continueProgram = true;
        while (continueProgram) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    ListFreeRooms();
                    break;
                case 3:
                    checkOut();
                    break;
                case 4:
                    Stats();
                    break;
                case 5:
                    findRoom();
                    break;
                case 6:
                    updateRoom();
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }

            continueProgram = askIfUserWantsToContinue();
        }
    }

    public static void printWelcomeMessage() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("                                       WELCOME TO HOTEL priBaiGosho!");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public static void printMenu() {

        System.out.println("Please select what you want to do \n 1 - Make a reservation; \n 2 - List free rooms; \n 3 - Checkout room; \n 4 - Stats; \n 5 - Find a room; \n 6 - Update a room; \n ");

    }

    public static boolean askIfUserWantsToContinue() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to continue the program (enter true or false)? ");
        boolean continueProgram = scan.nextBoolean();
        if (!continueProgram) {
            System.out.println("Bye! :)");
            System.exit(0);
        }
        return continueProgram;
    }

    public static void makeReservation() throws ParseException {
        System.out.println("=========================================");
        System.out.println("           MAKE A RESERVATION");
        System.out.println("=========================================");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please write the room number you want to reserve (1-10): ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        if (roomNumber < 1 || roomNumber > 10) {
            System.out.println("-----------------------------------------------------");
            System.out.println("INVALID INPUT! THERE ISN'T A ROOM WITH THAT NUMBER!");
            System.out.println("Please enter the information again!");
            System.out.println("-----------------------------------------------------");
            makeReservation();
            return;
        }

        System.out.println("Please enter the start date of the reservation (dd/MM/yyyy): ");
        Date startDate = dateFormat.parse(scanner.nextLine());

        System.out.println("Please enter the end date of the reservation (dd/MM/yyyy): ");
        Date endDate = dateFormat.parse(scanner.nextLine());

        System.out.println("Enter some information about the customer: ");
        String information = scanner.nextLine();

        //Room room = new Room(roomNumber, startDate, endDate, information);
        roomList.add(room);
        Date currentDate = new Date();
        if (startDate.before(currentDate)) {
            System.out.println("We are sorry but you cannot make a reservation in which the start date is before the current date! ");
            System.exit(0);
        } else if (endDate.before(currentDate)) {
            System.out.println("We are sorry but you cannot make a reservation in which the end date is before the current date! ");
            System.exit(0);
        } else if (endDate.equals(startDate)) {
            System.out.println("We are sorry but you cannot make a reservation in which the start date and the end date are the same! ");
            System.exit(0);
        } else if (endDate.before(startDate)) {
            System.out.println("We are sorry but you cannot make a reservation in which the end date id before the start date! ");
            System.exit(0);
        }

        System.out.println("The start day is: " + dateFormat.format(startDate) + "   12:00h.");
        System.out.println("The end day is: " + dateFormat.format(endDate) + "   12:00h.");
        System.out.println("Room No: " + roomNumber + " is successfully reserved");
    }

    public static void ListFreeRooms() {
        System.out.println("===========================================");
        System.out.println("      CHECK FOR CURRENTLY FREE ROOMS");
        System.out.println("===========================================");
        Date currentDate = new Date();
        for (Room room : roomList) {
            if (room.getStartDate().after(currentDate) || room.getEndDate().before(currentDate)) {
                System.out.println("Room No: " + room.getRoomNumber() + " is currently -> empty");
            } else {
                System.out.println("Room No: " + room.getRoomNumber() + " is currently -> reserved");
            }
        }
    }


    public static void checkOut() {
        System.out.println("=============================================");
        System.out.println("           CANCEL A RESERVATION");
        System.out.println("=============================================");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please write the number of the room whose reservation you want to cancel (1-10): ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        if (roomNumber < 1 || roomNumber > 10) {
            System.out.println("Invalid room number input! The rooms are numbered from 1 to 10!");
            System.out.println("Please enter the number again!");
            roomNumber = scanner.nextInt();
            scanner.nextLine();
        }

        boolean removed = false;
        Iterator<Room> iterator = roomList.iterator();
        while (iterator.hasNext()) {
            Room room = iterator.next();
            if (room.getRoomNumber() == roomNumber) {
                iterator.remove();
                removed = true;
            }
        }

        if (removed) {
            System.out.println("The reservation for Room No: " + roomNumber + " has been canceled.");
        } else {
            System.out.println("There are no reservations for this room!");
        }
    }

    public static void Stats() throws ParseException {
        System.out.println("=============================================");
        System.out.println("                  STATS                      ");
        System.out.println("=============================================");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the start day: ");
        int startDay = scanner.nextInt();
        System.out.print("Enter the start month: ");
        int startMonth = scanner.nextInt();
        System.out.print("Enter the start year: ");
        int startYear = scanner.nextInt();
        System.out.print("Enter the end day: ");
        int endDay = scanner.nextInt();
        System.out.print("Enter the end month: ");
        int endMonth = scanner.nextInt();
        System.out.print("Enter the end year: ");
        int endYear = scanner.nextInt();

        Date startDate = dateFormat.parse(String.format("%02d/%02d/%04d", startDay, startMonth, startYear));
        Date endDate = dateFormat.parse(String.format("%02d/%02d/%04d", endDay, endMonth, endYear));

        int totalDays = 0;
        for (Room room : roomList) {
            if (room.getStartDate().before(endDate) || room.getStartDate().equals(endDate)) {
                if (room.getEndDate().after(startDate) || room.getEndDate().equals(startDate)) {
                    long diffInMillies = Math.abs(room.getEndDate().getTime() - room.getStartDate().getTime());
                    int diffInDays = (int) (diffInMillies / (24 * 60 * 60 * 1000));
                    totalDays += diffInDays;
                }
            }
        }

        System.out.println("The total number of nights spent in the hotel between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate) + " is " + totalDays);
    }


    class Room {
        private int roomNumber;
        private Date startDate;
        private Date endDate;
        private String information;

        public Room(int roomNumber, Date startDate, Date endDate, String information) {
            this.roomNumber = roomNumber;
            this.startDate = startDate;
            this.endDate = endDate;
            this.information = information;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public String getInformation() {
            return information;
        }

        public void setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public void setInformation(String information) {
            this.information = information;
        }
    }
}

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelPriBaiGosho {
    static int[] safeStartDaysArr = new int[100];
    static int[] safeStartMonthsArr = new int[100];
    static int[] safeStartYearsArr = new int[100];
    static int[] safeEndDaysArr = new int[100];
    static int[] safeEndMonthsArr = new int[100];
    static int[] safeEndYearsArr = new int[100];
    static int[] numberOfBedsArr = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3};
    static String [] additionalServices = new String [100];
    static List<Room> roomList = new ArrayList<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");



    public static void printWelcomeMessage() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("                                       WELCOME TO HOTEL priBaiGosho!");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public static void printMenu() {

        System.out.println("Please select what you want to do \n 1 - Make a reservation; \n 2 - List free rooms; \n 3 - Checkout room; \n 4 - Stats; \n 5 - Find a room; \n 6 - Update a room; \n " );

    }
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
                    //System.out.println("Enter the number of the room would would like to update: ");
                    //int number = scanner.nextByte();
                    //updateRoom(number);
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }

            continueProgram = askIfUserWantsToContinue();
        }
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


        cancelTheWrongReservationForUpdateARoom(roomNumber);
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

        Room room = new Room(roomNumber, startDate, endDate, information);
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
    public static void cancelTheWrongReservationForUpdateARoom(int roomNumber){
        for(int i=roomNumber-1; i<100; i+=10){
            if(safeStartDaysArr[i]!=0 && safeStartDaysArr[i+1]==0){
                safeStartDaysArr[i]=0;
                safeStartMonthsArr[i]=0;
                safeStartYearsArr[i]=0;
                safeEndDaysArr[i]=0;
                safeEndMonthsArr[i]=0;
                safeEndYearsArr[i]=0;
            }
        }
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

    public static void findRoom() throws ParseException {
        System.out.println("=============================================");
        System.out.println("           FIND A ROOM");
        System.out.println("=============================================");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the start day of the period (dd.MM.yyyy): ");
        Date startDate = dateFormat.parse(scanner.nextLine());

        System.out.print("Enter the end day of the period (dd.MM.yyyy): ");
        Date endDate = dateFormat.parse(scanner.nextLine());

        boolean found = false;
        for (Room room : roomList) {
            if ((room.getStartDate().before(endDate) || room.getStartDate().equals(endDate))
                    && (room.getEndDate().after(startDate) || room.getEndDate().equals(startDate))) {
                System.out.println("Room No: " + room.getRoomNumber() + " is reserved from "
                        + dateFormat.format(room.getStartDate()) + " to " + dateFormat.format(room.getEndDate()));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No reservations found for the specified period!");
        }
    }
    public static void printTheSelectionsForUpdateARoom() {
        System.out.println("=========================================");
        System.out.println("             UPDATE A ROOM");
        System.out.println("=========================================");
        System.out.println("THESE ARE THE ADDITIONAL SERVICES OFFERED BY OUR HOTEL");
        System.out.println("1. Baby cot");
        System.out.println("2. With breakfast");
        System.out.println("3. Three-meal courses");
        System.out.println("4. Overlooking the sea");
        System.out.println("5. Overlooking the mountain");
        System.out.println("6. Overlooking the courtyard");
    }
    public static void updateRoom(int roomNumber) throws ParseException {
        printTheSelectionsForUpdateARoom();
        Scanner scan = new Scanner(System.in);
        System.out.print("Please, enter your choice here(1-6) -> ");
        byte addChoice = scan.nextByte();
        switch (addChoice){
            case 1 -> {
                babyCot(roomNumber);
            }
            case 2 ->{
                withBreakfast(roomNumber);
            }
            case 3 ->{
                threeCourseMeal(roomNumber);
            }
            case 4 ->{
                overlookingAtTheSea(roomNumber);

            }
            case 5 ->{
                OverlookingTheMountain(roomNumber);
            }
            case 6 ->{
                OverlookingTheCourtyard(roomNumber);
            }
            default -> {
                System.out.println("        INVALID INPUT!");
                System.out.println("Now enter the information again!");
                updateRoom(roomNumber);
            }
        }
    }
    public static void babyCot(int roomNumber) {
        System.out.println("*********************************");
        System.out.println("     YOU CHOSE -> BABY COT");
        System.out.println("*********************************");
        for(int i=roomNumber; i<100; i+=10){
            if(safeStartDaysArr[i]==0) {
                additionalServices[i] = " baby cot ";
            }
        }
    }
    public static void withBreakfast(int roomNumber) {
        System.out.println("***********************************************");
        System.out.println("  YOU CHOSE -> RESERVATION WITH BREAKFAST");
        System.out.println("************************************************");
        for(int i=roomNumber; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " breakfast ";
            }
        }
    }
    public static void threeCourseMeal(int roomNumber) {
        System.out.println("******************************************************");
        System.out.println("   YOU CHOSE -> RESERVATION BREAKFAST AND DINNER ");
        System.out.println("******************************************************");
        for(int i=roomNumber; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " breakfast and dinner ";
            }
        }
    }
    public static void overlookingAtTheSea(int roomNumber) throws ParseException {
        System.out.println("*******************************************");
        System.out.println("    YOU CHOSE -> OVERLOOKING THE SEA");
        System.out.println("    ROOMS NO: 1,5,7,10 HAVE A SEA VIEW");
        System.out.println("*******************************************");
        while(roomNumber!=1 && roomNumber!=5 && roomNumber!=7 && roomNumber!=10){
            System.out.println("                    INVALID INPUT!");
            System.out.println("            THE RESERVATION IS CANCELED");
            System.out.println("        ROOMS NO: 1,5,7,10 HAVE A SEA VIEW");
            System.out.println("ENTER THE INFORMATION ABOUT THE RESERVATION AGAIN!");
            cancelTheWrongReservationForUpdateARoom(roomNumber);
            makeReservation();
        }
        for(int i=roomNumber; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " sea view ";
            }
        }
    }
    public static void OverlookingTheMountain(int roomNumber) throws ParseException {
        System.out.println("*******************************************");
        System.out.println("    YOU CHOSE -> OVERLOOKING THE MOUNTAIN");
        System.out.println("     ROOMS NO: 2,3 HAVE A MOUNTAIN VIEW");
        System.out.println("*******************************************");
        makeReservation();
        while(roomNumber!=2 && roomNumber!=3){
            System.out.println("                    INVALID INPUT!");
            System.out.println("            THE RESERVATION IS CANCELED");
            System.out.println("         ROOMS NO: 2,4 HAVE A MOUNTAIN VIEW");
            System.out.println("ENTER THE INFORMATION ABOUT THE RESERVATION AGAIN!");
            cancelTheWrongReservationForUpdateARoom(roomNumber);
            makeReservation();
        }
        for(int i=roomNumber; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " mountain view ";
            }
        }
    }
    public static void OverlookingTheCourtyard(int roomNumber) throws ParseException {
        System.out.println("*********************************************");
        System.out.println("    YOU CHOSE -> OVERLOOKING THE COURTYARD");
        System.out.println("   ROOMS NO: 4,6,8,9 HAVE A COURTYARD VIEW");
        System.out.println("*********************************************");
        makeReservation();
        while(roomNumber!=4 && roomNumber!=6 && roomNumber!=8 && roomNumber!=9){
            System.out.println("               INVALID INPUT!");
            System.out.println("         THE RESERVATION IS CANCELED");
            System.out.println("     ROOMS NO: 4,6,8,9 HAVE A COURTYARD VIEW");
            System.out.println("ENTER THE INFORMATION ABOUT THE RESERVATION AGAIN!");
            cancelTheWrongReservationForUpdateARoom(roomNumber);
            makeReservation();
        }
        for(int i=roomNumber; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " courtyard view ";
            }
        }
    }

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

import java.util.Arrays;

public class testing {
    public static void main(String[] args) {
        String userInput = "12.12.12";

        // Split the input string using the dot (.) as the delimiter
        String[] parts = userInput.split("\\.");

        // Separate variables for each part
        int part1 = Integer.parseInt(parts[0]);
        int part2 = Integer.parseInt(parts[1]);
        int part3 = Integer.parseInt(parts[2]);

        // Display the separated values
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
        System.out.println("Part 3: " + part3);
    }
}
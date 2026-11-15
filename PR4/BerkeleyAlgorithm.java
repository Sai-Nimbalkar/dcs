import java.util.*;
public class BerkeleyAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Number of computers
        System.out.print("Enter number of computers: ");
        int n = sc.nextInt();
        int[] clocks = new int[n];
        // Enter clock values
        System.out.println("\nEnter clock times in minutes:");
        for (int i = 0; i < n; i++) {
            System.out.print("Computer " + (i + 1) + ": ");
            clocks[i] = sc.nextInt();
        }
        // First computer is selected as master
        int master = 0;
        System.out.println("\nComputer " + (master + 1)
                + " is selected as Master.");
        // Display original clocks
        System.out.println("\nOriginal Clock Times:");
        for (int i = 0; i < n; i++) {
            System.out.println(
                    "Computer " + (i + 1)
                    + ": " + clocks[i] + " minutes");
        }
        // Calculate offsets
        int[] offsets = new int[n];
        System.out.println("\nClock Offsets:");
        int totalOffset = 0;

        for (int i = 0; i < n; i++) {

            offsets[i] = clocks[i] - clocks[master];

            totalOffset += offsets[i];

            System.out.println(
                    "Computer " + (i + 1)
                    + ": " + offsets[i] + " minutes");
        }

        // Calculate average offset
        int averageOffset = totalOffset / n;

        System.out.println(
                "\nAverage Clock Offset: "
                + averageOffset + " minutes");

        // Calculate adjustments
        System.out.println("\nClock Adjustments:");

        for (int i = 0; i < n; i++) {

            int adjustment =
                    averageOffset - offsets[i];

            System.out.println(
                    "Computer " + (i + 1)
                    + ": " + adjustment
                    + " minutes");
        }

        // Synchronize clocks
        System.out.println("\nSynchronized Clock Times:");

        for (int i = 0; i < n; i++) {

            clocks[i] =
                    clocks[i]
                    + (averageOffset - offsets[i]);

            System.out.println(
                    "Computer " + (i + 1)
                    + ": " + clocks[i]
                    + " minutes");
        }

        sc.close();
    }
}

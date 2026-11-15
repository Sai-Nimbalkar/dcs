import java.util.*;

public class LeaderElection {

    static int[] processes;
    static int n;

    // ==============================
    // BULLY ALGORITHM
    // ==============================

    static void bullyAlgorithm(int failedProcess) {

        System.out.println("\n================================");
        System.out.println("       BULLY ALGORITHM");
        System.out.println("================================");

        System.out.println(
                "Process " + failedProcess + " has failed.");

        int initiator = -1;

        // Find the highest active process
        for (int i = 0; i < n; i++) {

            if (processes[i] != failedProcess) {

                if (processes[i] > initiator) {
                    initiator = processes[i];
                }
            }
        }

        System.out.println(
                "Election initiated by Process " + initiator);

        boolean higherProcessExists = false;

        for (int i = 0; i < n; i++) {

            if (processes[i] > initiator &&
                    processes[i] != failedProcess) {

                System.out.println(
                        "Process " + initiator
                        + " sends election message to Process "
                        + processes[i]);

                higherProcessExists = true;
            }
        }

        if (!higherProcessExists) {

            System.out.println(
                    "No higher active process exists.");

            System.out.println(
                    "Process " + initiator
                    + " becomes the new leader.");

        } else {

            int newLeader = -1;

            for (int i = 0; i < n; i++) {

                if (processes[i] != failedProcess &&
                        processes[i] > newLeader) {

                    newLeader = processes[i];
                }
            }

            System.out.println(
                    "Process " + newLeader
                    + " becomes the new leader.");
        }
    }


    // ==============================
    // RING ALGORITHM
    // ==============================

    static void ringAlgorithm(int failedProcess) {

        System.out.println("\n================================");
        System.out.println("        RING ALGORITHM");
        System.out.println("================================");

        System.out.println(
                "Process " + failedProcess + " has failed.");

        // Find an active process to start election
        int start = -1;

        for (int i = 0; i < n; i++) {

            if (processes[i] != failedProcess) {

                start = i;
                break;
            }
        }

        System.out.println(
                "Election initiated by Process "
                + processes[start]);

        ArrayList<Integer> electionList =
                new ArrayList<>();

        int current = start;

        do {

            int process = processes[current];

            if (process != failedProcess) {

                System.out.println(
                        "Process " + process
                        + " receives election message.");

                electionList.add(process);
            }

            current = (current + 1) % n;

        } while (current != start);

        System.out.println(
                "\nElection message contains:");

        System.out.println(electionList);

        // Find highest process ID
        int leader = Collections.max(electionList);

        System.out.println(
                "\nProcess " + leader
                + " becomes the new leader.");
    }


    // ==============================
    // MAIN METHOD
    // ==============================

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter number of processes: ");

        n = sc.nextInt();

        processes = new int[n];

        System.out.println(
                "\nEnter process IDs:");

        for (int i = 0; i < n; i++) {

            System.out.print(
                    "Process " + (i + 1) + ": ");

            processes[i] = sc.nextInt();
        }

        System.out.println(
                "\nProcesses in the system:");

        for (int process : processes) {

            System.out.print(process + " ");
        }

        System.out.println();

        System.out.print(
                "\nEnter failed process ID: ");

        int failedProcess = sc.nextInt();

        // Run Bully Algorithm
        bullyAlgorithm(failedProcess);

        // Run Ring Algorithm
        ringAlgorithm(failedProcess);

        sc.close();
    }
}

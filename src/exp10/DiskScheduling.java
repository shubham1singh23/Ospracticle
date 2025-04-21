package exp10;

import java.util.*;

public class DiskScheduling {
    static Scanner sc = new Scanner(System.in);

    // FCFS - First Come First Serve
    static void fcfs(int[] requests, int head) {
        int total = 0;
        System.out.println("\n--- FCFS Disk Scheduling ---");
        for (int i = 0; i < requests.length; i++) {
            total += Math.abs(head - requests[i]);  // absolute distance between head and request
            head = requests[i];
        }
        System.out.println("Total head movement: " + total);
    }

    // SCAN - Elevator Algorithm
    static void scan(int[] requests, int head) {
        int total = 0;
        Arrays.sort(requests);  // sort the requests
        System.out.println("\n--- SCAN Disk Scheduling ---");

        // First go towards 0, then to higher values
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int r : requests) {
            if (r < head) left.add(r);
            else right.add(r);
        }

        Collections.sort(left, Collections.reverseOrder()); // descending order
        Collections.sort(right); // ascending order

        // Move toward 0 first (left), then to right
        for (int r : left) {
            total += Math.abs(head - r);
            head = r;
        }
        // Move to 0 if not already there
        if (head != 0) {
            total += Math.abs(head - 0);
            head = 0;
        }
        for (int r : right) {
            total += Math.abs(head - r);
            head = r;
        }

        System.out.println("Total head movement: " + total);
    }

    // C-SCAN - Circular SCAN
    static void cscan(int[] requests, int head, int diskSize) {
        int total = 0;
        Arrays.sort(requests);
        System.out.println("\n--- C-SCAN Disk Scheduling ---");

        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();

        for (int r : requests) {
            if (r >= head) right.add(r);
            else left.add(r);
        }

        Collections.sort(right);
        Collections.sort(left);

        // Move to the end (diskSize - 1), jump to 0, and continue
        for (int r : right) {
            total += Math.abs(head - r);
            head = r;
        }

        if (head != diskSize - 1) {
            total += Math.abs(head - (diskSize - 1)); // go to end
            head = 0; // jump to start
            total += diskSize - 1; // full disk jump
        }

        for (int r : left) {
            total += Math.abs(head - r);
            head = r;
        }

        System.out.println("Total head movement: " + total);
    }

    public static void main(String[] args) {
        System.out.print("Enter number of disk requests: ");
        int n = sc.nextInt();
        int[] requests = new int[n];

        System.out.print("Enter the disk requests: ");
        for (int i = 0; i < n; i++) {
            requests[i] = sc.nextInt();
        }

        System.out.print("Enter initial head position: ");
        int head = sc.nextInt();

        System.out.print("Enter disk size (e.g., 200): ");
        int diskSize = sc.nextInt();

        fcfs(requests.clone(), head);
        scan(requests.clone(), head);
        cscan(requests.clone(), head, diskSize);
    }
}

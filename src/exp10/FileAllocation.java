package exp10;

import java.util.*;

public class FileAllocation {
    static Scanner sc = new Scanner(System.in);
    static int totalBlocks = 100;
    static boolean[] memory = new boolean[totalBlocks]; // false = free, true = allocated

    // Sequential Allocation
    static void sequentialAllocation() {
        System.out.println("\n--- Sequential Allocation ---");
        System.out.print("Enter starting block: ");
        int start = sc.nextInt();
        System.out.print("Enter length of file: ");
        int length = sc.nextInt();

        // Check if all required blocks are free
        boolean available = true;
        for (int i = start; i < start + length; i++) {
            if (i >= totalBlocks || memory[i]) {
                available = false;
                break;
            }
        }

        if (available) {
            for (int i = start; i < start + length; i++) {
                memory[i] = true;
            }
            System.out.println("Sequential Allocation successful.");
            System.out.print("Allocated blocks: ");
            for (int i = start; i < start + length; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        } else {
            System.out.println("Sequential Allocation failed. Blocks not available.");
        }
    }

    // Linked Allocation
    static void linkedAllocation() {
        System.out.println("\n--- Linked Allocation ---");
        System.out.print("Enter number of blocks needed: ");
        int n = sc.nextInt();

        List<Integer> allocated = new ArrayList<>();
        for (int i = 0; i < totalBlocks && allocated.size() < n; i++) {
            if (!memory[i]) {
                memory[i] = true;
                allocated.add(i);
            }
        }

        if (allocated.size() == n) {
            System.out.println("Linked Allocation successful.");
            System.out.println("Blocks linked: " + allocated);
        } else {
            for (int i : allocated)
            {
                memory[i] = false;
            }
            System.out.println("Linked Allocation failed. Not enough free blocks.");
        }
    }

    // Indexed Allocation
    static void indexedAllocation() {
        System.out.println("\n--- Indexed Allocation ---");
        System.out.print("Enter number of blocks needed: ");
        int n = sc.nextInt();

        int indexBlock = -1;
        for (int i = 0; i < totalBlocks; i++) {
            if (!memory[i]) {
                indexBlock = i;
                memory[i] = true;
                break;
            }
        }

        if (indexBlock == -1) {
            System.out.println("No free block for index block.");
            return;
        }

        List<Integer> allocated = new ArrayList<>();
        for (int i = 0; i < totalBlocks && allocated.size() < n; i++) {
            if (!memory[i]) {
                memory[i] = true;
                allocated.add(i);
            }
        }

        if (allocated.size() == n) {
            System.out.println("Indexed Allocation successful.");
            System.out.println("Index block: " + indexBlock);
            System.out.println("Allocated blocks: " + allocated);
        } else {
            for (int i : allocated) memory[i] = false;
            memory[indexBlock] = false;
            System.out.println("Indexed Allocation failed. Not enough blocks.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nFile Allocation Strategies Menu:");
            System.out.println("1. Sequential Allocation");
            System.out.println("2. Linked Allocation");
            System.out.println("3. Indexed Allocation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: sequentialAllocation(); break;
                case 2: linkedAllocation(); break;
                case 3: indexedAllocation(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }
}

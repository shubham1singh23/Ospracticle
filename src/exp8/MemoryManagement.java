package exp8;

import java.util.Scanner;

public class MemoryManagement {

    // MVT - Variable Partitioning
    static void mvt(int totalMemory, int[] processSize) {
        int remainingMemory = totalMemory;
        System.out.println("\n--- MVT (Multiprogramming with Variable Tasks) ---");
        for (int i = 0; i < processSize.length; i++) {
            if (processSize[i] <= remainingMemory) {
                System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " is allocated.");
                remainingMemory -= processSize[i];
            } else {
                System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " cannot be allocated.");
            }
        }
        System.out.println("Remaining memory: " + remainingMemory);
    }

    // MFT - Fixed Partitioning
    static void mft(int totalMemory, int partitionSize, int[] processSize) {
        int numberOfPartitions = totalMemory / partitionSize;
        int internalFragmentation = 0;
        int i = 0;

        System.out.println("\n--- MFT (Multiprogramming with Fixed Tasks using while loop) ---");

        // Allocate as long as partitions and processes are available
        while (i < numberOfPartitions && i < processSize.length) {
            if (processSize[i] <= partitionSize) {
                System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " is allocated to partition " + (i + 1));
                internalFragmentation += (partitionSize - processSize[i]);
            } else {
                System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " is too large for the partition.");
            }
            i++;
        }

        // Remaining processes that couldn't be allocated (no partitions left)
        while (i < processSize.length) {
            System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " cannot be allocated (no partition left).");
            i++;
        }

        System.out.println("Total internal fragmentation: " + internalFragmentation);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inputs
        System.out.print("Enter total memory available: ");
        int totalMemory = sc.nextInt();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] processSize = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter size of process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }

        System.out.print("Enter partition size for MFT: ");
        int partitionSize = sc.nextInt();

        // Function calls
        mvt(totalMemory, processSize);
        mft(totalMemory, partitionSize, processSize);

        sc.close();
    }
}

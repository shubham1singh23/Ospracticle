package exp8;

public class MemoryAllocation {

    // First Fit
    static void firstFit(int[] blockSize, int[] processSize) {
        int[] allocation = new int[processSize.length];
        for (int i = 0; i < allocation.length; i++) allocation[i] = -1;

        for (int i = 0; i < processSize.length; i++) {
            for (int j = 0; j < blockSize.length; j++) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
                }
            }
        }

        System.out.println("\nFirst Fit:");
        printResult(processSize, allocation);
    }

    // Best Fit
    static void bestFit(int[] blockSize, int[] processSize) {
        int[] allocation = new int[processSize.length];
        for (int i = 0; i < allocation.length; i++) allocation[i] = -1;

        for (int i = 0; i < processSize.length; i++) {
            int bestIdx = -1;
            for (int j = 0; j < blockSize.length; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }

        System.out.println("\nBest Fit:");
        printResult(processSize, allocation);
    }

    // Worst Fit
    static void worstFit(int[] blockSize, int[] processSize) {
        int[] allocation = new int[processSize.length];
        for (int i = 0; i < allocation.length; i++) allocation[i] = -1;

        for (int i = 0; i < processSize.length; i++) {
            int worstIdx = -1;
            for (int j = 0; j < blockSize.length; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[j] > blockSize[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
            }
        }

        System.out.println("\nWorst Fit:");
        printResult(processSize, allocation);
    }

    // Print result
    static void printResult(int[] processSize, int[] allocation) {
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < processSize.length; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }

    // Main function
    public static void main(String[] args) {
        int[] blockSize = {100, 500, 200, 300, 600};
        int[] processSize = {212, 417, 112, 426};

        firstFit(blockSize.clone(), processSize);
        bestFit(blockSize.clone(), processSize);
        worstFit(blockSize.clone(), processSize);
    }
}


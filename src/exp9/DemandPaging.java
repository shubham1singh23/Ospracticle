package exp9;

import java.util.*;

public class DemandPaging {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of pages in the reference string
        System.out.print("Enter number of pages in reference string: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        // Simulate demand paging using FIFO
        Set<Integer> memory = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If page is not in memory, it's a fault
            if (!memory.contains(page)) {
                pageFaults++;

                // If memory is full, remove the oldest page
                if (memory.size() == frames) {
                    int removed = queue.poll();
                    memory.remove(removed);
                }

                // Add the new page
                memory.add(page);
                queue.add(page);
            }

            // Show current state of memory
            System.out.print("Memory after accessing page " + page + ": ");
            for (int p : queue) {
                System.out.print(p + " ");
            }
            System.out.println();
        }

        System.out.println("Total Page Faults: " + pageFaults);
        sc.close();
    }
}

package exp9;

import java.util.*;

public class PageReplacement {

    // FIFO Page Replacement
    static void fifo(int[] pages, int frames) {
        Set<Integer> memory = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("\nFIFO Page Replacement:");

        for (int page : pages) {
            if (!memory.contains(page)) {
                pageFaults++;
                if (memory.size() == frames) {
                    int removed = queue.poll();
                    memory.remove(removed);
                }
                memory.add(page);
                queue.add(page);
            }

            System.out.println("Memory: " + queue);
        }

        System.out.println("Total Page Faults (FIFO): " + pageFaults);
    }

    // LRU Page Replacement
    static void lru(int[] pages, int frames) {
        List<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("\nLRU Page Replacement:");

        for (int page : pages) {
            if (!memory.contains(page)) {
                pageFaults++;
                if (memory.size() == frames) {
                    memory.remove(0); // remove least recently used
                }
            } else {
                memory.remove(page); // remove current to re-add at end
            }
            memory.add(page); // most recently used at end
            System.out.println("Memory: " + memory);
        }

        System.out.println("Total Page Faults (LRU): " + pageFaults);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input reference string
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.print("Enter page reference string: ");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        fifo(pages, frames);
        lru(pages, frames);

        sc.close();
    }
}

package exp7;
import java.util.concurrent.*;

// Philosopher class extending Thread
class Philospher extends Thread {
    int id;
    Semaphore leftSpoon;
    Semaphore rightSpoon;
    boolean isLast;

    // Constructor to initialize philosopher details
    Philospher(int id, Semaphore left, Semaphore right, boolean isLast) {
        this.id = id;
        this.leftSpoon = left;
        this.rightSpoon = right;
        this.isLast = isLast;
    }

    // Philosopher's actions: think -> pick spoons -> eat -> release spoons
    public void run() {
        try {
            System.out.println("Philosopher " + id + " is thinking");

            // To avoid deadlock, last philosopher picks right spoon first
            if (isLast) {
                rightSpoon.acquire();
                leftSpoon.acquire();
            } else {
                leftSpoon.acquire();
                rightSpoon.acquire();
            }

            System.out.println("Philosopher " + id + " is eating");

            // Simulate eating time (optional)
            Thread.sleep(500);

            System.out.println("Philosopher " + id + " has finished eating");

            // Release the spoons after eating
            leftSpoon.release();
            rightSpoon.release();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Main class to start the simulation
class DiningPhilosophers {
    public static void main(String[] args) {
        int n = 5; // number of philosophers
        Philospher[] p = new Philospher[n];
        Semaphore[] spoons = new Semaphore[n];

        // Initialize 1 spoon (semaphore) between each pair of philosophers
        for (int i = 0; i < n; i++) {
            spoons[i] = new Semaphore(1); // only 1 philosopher can hold a spoon
        }

        // Create and start philosopher threads
        for (int i = 0; i < n; i++) {
            Semaphore left = spoons[i];
            Semaphore right = spoons[(i + 1) % n];
            boolean isLast = (i == n - 1); // last philosopher check
            p[i] = new Philospher(i, left, right, isLast);
            p[i].start();
        }
    }
}

import java.util.*;

//assuming arival time as 0
class Processs {
    int pid;          // Process ID
    int burstTime;    // Burst time
    int remainingTime; // Remaining burst time
    int waitingTime;  // Waiting Time
    int turnaroundTime; // Turnaround Time

    Processs(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime; // Initially, remaining time is the same as burst time
    }
}

public class PreemptiveRoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes and time quantum
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();

        // Create an array of processes
        Processs[] processes = new Processs[n];

        // Input burst time for each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            int bt = sc.nextInt();
            processes[i] = new Processs(i + 1, bt);
        }

        // Round Robin Scheduling
        int time = 0; // Current time in the system
        Queue<Processs> queue = new LinkedList<>();

        // Add all processes to the queue initially
        for (int i = 0; i < n; i++) {
            queue.add(processes[i]);
        }

        while (!queue.isEmpty()) {
            Processs currentProcess = queue.poll();

            // If remaining time is less than or equal to quantum, process will finish
            if (currentProcess.remainingTime <= quantum) {
                time += currentProcess.remainingTime; // Process finishes execution
                currentProcess.turnaroundTime = time; // Turnaround time = completion time for RR
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
            } else {
                // Process does not finish in this quantum
                time += quantum;
                currentProcess.remainingTime -= quantum;
                queue.add(currentProcess); // Re-add the process with updated remaining time
            }
        }

        // Display the results for each process
        System.out.println("\nPID\tBurst Time\tWaiting Time\tTurnaround Time");
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Processs p : processes) {
            System.out.println(p.pid + "\t" + p.burstTime + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        // Calculate averages
        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / n);
    }
}

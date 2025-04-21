package exp5extra;

import java.util.*;

// Process class
class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int turnaroundTime;
    int waitingTime;

    Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        // Input arrival and burst times
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes[i] = new Process(i + 1, at, bt);
        }

        // Sort by arrival time
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int totalTAT = 0, totalWT = 0;

        for (int i = 0; i < n; i++) {
            Process p = processes[i];

            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            // Turnaround time = current time + burst - arrival
            p.turnaroundTime = (currentTime + p.burstTime) - p.arrivalTime;

            // Waiting time = turnaround - burst
            p.waitingTime = p.turnaroundTime - p.burstTime;

            currentTime += p.burstTime;

            totalTAT += p.turnaroundTime;
            totalWT += p.waitingTime;
        }

        // Print process table
        System.out.println("\nPID\tAT\tBT\tTAT\tWT");
        for (Process p : processes) {
            System.out.println(p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" +
                    p.turnaroundTime + "\t" + p.waitingTime);
        }

        // Averages
        System.out.printf("\nAverage Turnaround Time: %.2f\n", (float) totalTAT / n);
        System.out.printf("Average Waiting Time: %.2f\n", (float) totalWT / n);
    }
}

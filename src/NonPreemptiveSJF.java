import java.util.*;

class Process {
    int pid; // Process ID
    int burstTime;
    int waitingTime;
    int turnaroundTime;

    Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
    }
}

public class NonPreemptiveSJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            int bt = sc.nextInt();
            processes[i] = new Process(i + 1, bt);
        }

        // Sort by burst time (SJF)
        Arrays.sort(processes, Comparator.comparingInt(p -> p.burstTime));

        // Calculate waiting time and turnaround time
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        processes[0].waitingTime = 0;
        processes[0].turnaroundTime = processes[0].burstTime;

        for (int i = 1; i < n; i++) {
            processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
            processes[i].turnaroundTime = processes[i-1].turnaroundTime + processes[i].burstTime;

            totalWaitingTime += processes[i].waitingTime;
            totalTurnaroundTime += processes[i].turnaroundTime;
        }

        // Add the first process's turnaround time (it's missed in the loop)
        totalTurnaroundTime += processes[0].turnaroundTime;

        // Display results
        System.out.println("\nPID\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println(p.pid + "\t" + p.burstTime + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
        }

        // Calculate averages
        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / n);
    }
}

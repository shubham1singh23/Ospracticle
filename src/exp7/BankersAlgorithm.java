package exp7;

import java.util.Scanner;

public class BankersAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of processes and resources
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int[][] alloc = new int[n][m]; // Allocation matrix
        int[][] max = new int[n][m];   // Maximum matrix
        int[][] need = new int[n][m];  // Need matrix
        int[] avail = new int[m];      // Available resources

        // Input Allocation matrix
        System.out.println("Enter Allocation Matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                alloc[i][j] = sc.nextInt();

        // Input Maximum matrix
        System.out.println("Enter Maximum Matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                max[i][j] = sc.nextInt();

        // Input Available resources
        System.out.println("Enter Available Resources:");
        for (int i = 0; i < m; i++)
            avail[i] = sc.nextInt();

        // Calculate Need matrix = Max - Alloc
        System.out.println("Need Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }

        // Banker's algorithm to find safe sequence
        boolean[] finished = new boolean[n];
        int[] safeSeq = new int[n];
        int finishedCount = 0;

        while(finishedCount<n){
            boolean progress=false;
            for (int i=0;i<n;i++){
                if(finished[i]){
                    continue;
                }
                boolean canExecute=true;
                for (int j=0;j<m;j++){
                    if(need[i][j]>avail[j]){
                        canExecute=false;
                        break;
                    }
                }

                if(canExecute){
                    for (int j=0;j<m;j++){
                        avail[j]+=alloc[i][j];
                    }
                    safeSeq[finishedCount++]=i;
                    finished[i]=true;
                    progress=true;
                }
            }
            if(!progress){
                System.out.println("The system is not in safe state");
                return;
            }
        }



        // Print safe sequence
        System.out.print("System is in a SAFE state.\nSafe Sequence: ");
        for (int i = 0; i < n; i++)
            System.out.print("P" + safeSeq[i] + " ");
    }
}

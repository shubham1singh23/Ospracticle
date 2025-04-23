#include <stdio.h>
#include <unistd.h>     // for fork(), getpid(), getppid()
#include <sys/types.h>  // for pid_t
#include <sys/wait.h>   // for wait(), waitpid()

int main() {
    pid_t pid = fork(); // Create a child process

    if (pid < 0) {
        // Error occurred during fork
        perror("fork failed");
        return 1;
    }
    else if (pid == 0) {
        // Child process
        printf("Child Process:\n");
        printf("Child PID: %d\n", getpid());       // Child's PID
        printf("Parent PID: %d\n", getppid());     // Parent's PID
        printf("Child is doing some work...\n");
        sleep(2);  // Simulate work
        printf("Child completed.\n");
    }
    else {
        // Parent process
        printf("Parent Process:\n");
        printf("Parent PID: %d\n", getpid());      // Parent's PID
        printf("Waiting for child (PID: %d) to finish...\n", pid);

        // Option 1: Wait for any child (simple wait)
        // wait(NULL);

        // Option 2: Wait for a specific child using waitpid
        int status;
        waitpid(pid, &status, 0);

        printf("Child process finished.\n");
    }

    return 0;
}


#include <stdio.h>
#include <fcntl.h>     // open
#include <unistd.h>    // read, write, close, getpid, getuid, getgid, etc.
#include <sys/types.h>

int main() {
    char buffer[100];
    int fd;

    // ---------- System call: open ----------
    fd = open("sample.txt", O_RDONLY); // open file for reading
    if (fd < 0) {
        perror("Error opening file");
        return 1;
    }

    // ---------- System call: read ----------
    int bytesRead = read(fd, buffer, sizeof(buffer) - 1);
    buffer[bytesRead] = '\0';  // null-terminate
    printf("File content read using read():\n%s\n", buffer);

    // ---------- System call: close ----------
    close(fd);

    // ---------- Process and user info ----------
    printf("\nProcess ID (getpid): %d\n", getpid());
    printf("User ID (getuid): %d\n", getuid());
    printf("Effective User ID (geteuid): %d\n", geteuid());
    printf("Group ID (getgid): %d\n", getgid());
    printf("Effective Group ID (getegid): %d\n", getegid());

    // ---------- Using system commands ----------
    printf("\nUsing sort:\n");
    system("sort sample.txt");

    printf("\nUsing grep (search 'hello'):\n");
    system("grep hello sample.txt");

    printf("\nUsing awk (print 1st and 2nd words):\n");
    system("awk '{print $1, $2}' sample.txt");

    return 0;
}
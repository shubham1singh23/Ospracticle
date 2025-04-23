#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

#define BUFFER_SIZE 1024

int main(int argc, char *argv[]) {
    if (argc < 3) {
        printf("Usage: %s <source> <destination>\n", argv[0]);
        return 1;
    }

    int src = open(argv[1], O_RDONLY);
    if (src < 0) {
        perror("open source");
        return 1;
    }

    int dest = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (dest < 0) {
        perror("open dest");
        close(src);
        return 1;
    }

    char buffer[BUFFER_SIZE];
    ssize_t bytes;
    while ((bytes = read(src, buffer, BUFFER_SIZE)) > 0) {
        write(dest, buffer, bytes);
    }

    close(src);
    close(dest);
    return 0;
}

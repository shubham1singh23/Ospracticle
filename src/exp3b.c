#include <stdio.h>

int main(int argc, char *argv[]) {
    if (argc < 3) {
        printf("Usage: %s <oldname> <newname>\n", argv[0]);
        return 1;
    }

    if (rename(argv[1], argv[2]) != 0) {
        perror("rename");
        return 1;
    }

    return 0;
}

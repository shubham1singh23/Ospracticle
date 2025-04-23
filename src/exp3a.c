#include <stdio.h>
#include <dirent.h>

int main(int argc, char *argv[]) {
    const char *path = (argc > 1) ? argv[1] : ".";
    struct dirent *entry;
    DIR *dir = opendir(path);

    if (dir == NULL) {
        perror("opendir");
        return 1;
    }

    while ((entry = readdir(dir)) != NULL) {
        printf("%s\n", entry->d_name);
    }

    closedir(dir);
    return 0;
}

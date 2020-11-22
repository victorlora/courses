#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include "utable.h"
#include <unistd.h>
#include <stdlib.h>

struct _user {
    char username[20];
    char ip[20];
};

struct _user utable[MAX_USERS];
int users = 0;

int cleanTable(void)
{
    users = 0;
    return 0;
}

int showTable(void)
{
    /* Print the table of users */
    printf("\n--------------------\n");

    int i;

    for (i = 0; i < users; i++) {
        printf("Username: %s  IP: %s\n", utable[i].username, utable[i].ip);
    }

    printf("\n--------------------\n");
    return 0;
}

int addToTable(const char *username, const char *ip) {
    if (users < MAX_USERS) {
        strcpy(utable[users].username, username);
        strcpy(utable[users].ip, ip);
        users = users + 1;
        return 0;
    } else {
        return 1;
    }
}

int removeFromTable(const char *username) {
    int done = FALSE;
    int found = FALSE;
    int i = 0;
    int j;

    while (!done) {
        if (strcmp(utable[i].username, username) == 0) {
            for (j = i; j < users; j++) {
                utable[j] = utable[j + 1];
            }
            
            done = found = TRUE;
            users = users - 1;
        } else {
            i++;
            done = (i == users);
        }
    }

    return found ? 1 : 0;
}

int isDuplicate(const char *username) {
    if (users > 0) {
        int done = FALSE;
        int found = FALSE;
        int i = 0;
        int j;

        while (!done) {
            if (strcmp(utable[i].username, username) == 0) {
                done = found = TRUE;
            } else {
                i++;
                done = (i == users);
            }
        }

        return found ? 1 : 0;
    } else {
        return 0;
    }
}

char* getTable(void) {
    /* Print the table of users */
    char *data = malloc(5012);
    int i;

    for (i = 0; i < users; i++) {

        char currentData[2056];
        strcpy(currentData, "Username: ");
        strcat(currentData, utable[i].username);
        strcat(currentData, "; IP: ");
        strcat(currentData, utable[i].ip);
        strcat(currentData, "\n");

        strcat(data, currentData);
    }

    return data;
}

//==============================================================================
//=  Author: Victor Lora                                                       =
//=  File: chatServer.c                                                        =
//=  A chat server                                                             =
//=    - Uses threads for parallel tcp connections                             =
//==============================================================================
//=  Compile:                                                                  =
//=    - UNIX: gcc -o chatServer chatServer.c -pthread                         =
//=----------------------------------------------------------------------------=
//=  Execute: chatServer                                                       =
//==============================================================================

#define BSD

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <pthread.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <fcntl.h>
#include <netdb.h>
#include "utable.h"

// ----- Defines ------
#define PORT_NUM        6060
#define BUF_SIZE        4096
#define MULTICAST_GROUP "224.0.0.7"
#define MULTICAST_PORT  6061
#define BYE_BYE         "You've been disconnected"
#define CMD_ERROR       "Error: Invalid command"
#define DUP_USR_ERROR   "Error: Duplicate username"
#define FULL_CHAT_ERROR "Error: Chat room is full. Try again later"
#define HELLO           "Connected to chat server ..."

int    thread_count = 0;
char   current_client_ip_addr[20];

// Multicast setup
struct sockaddr_in multicast_addr;
int    fd, cnt;
struct ip_mreq mreq;

// ----- Function Prototypes -----
void *chat_serve(void *in_arg); // POSIX thread function to handle service

//====== Main ======
int main(void)
{
    int                     server_s;           // Server socket descriptor
    struct  sockaddr_in     server_addr;        // Server internet address
    int                     client_s;           // Client socket descriptor
    struct  sockaddr_in     client_addr;        // Client Internet address
    struct  in_addr         client_ip_addr;     // Client IP address
    socklen_t               addr_len;           // Internet address length
    pthread_t               thread_id;          // Thread ID
    int                     retcode;            // Return code

    // Create socket, fill address information & bind it
    server_s = socket(AF_INET, SOCK_STREAM, 0);
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT_NUM);
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    
    // Bind socket to address
    retcode = bind(server_s, (struct sockaddr*)&server_addr, sizeof(server_addr));
    if (retcode < 0) {
        printf("*** ERROR - bind() failed \n");
        exit(-1);
    }

    // Set-up listen
    listen(server_s, 100);

    /* create what looks like an ordinary UDP socket */
    if ((fd=socket(AF_INET,SOCK_DGRAM,0)) < 0) {
        perror("socket");
        exit(1);
    }

    /* set up multicast destination address */
    memset(&multicast_addr,0,sizeof(multicast_addr));
    multicast_addr.sin_family=AF_INET;
    multicast_addr.sin_addr.s_addr=inet_addr(MULTICAST_GROUP);
    multicast_addr.sin_port=htons(MULTICAST_PORT);

    printf(">>> Server is runing on port %d <<< \n", PORT_NUM);

    // Main loop to accept connections & create threads to handle client
    while (1) {
        addr_len = sizeof(client_addr);
        // Accept connection from client
        client_s = accept(server_s, (struct sockaddr *)&client_addr, &addr_len);
        
        if (client_s == -1) {
            printf("ERROR - Unable to create a socket \n");
            exit(1);
        } else {
            printf("Accepting connection - client_s: %d\n", client_s); 
            memcpy(&client_ip_addr, &client_addr.sin_addr.s_addr, 4);
            strcpy(current_client_ip_addr, inet_ntoa(client_ip_addr));
        }

        if (pthread_create(&thread_id, NULL, chat_serve, (void *)(intptr_t)client_s) != 0) {
            printf("ERROR - Unable to create a thread to handle the GET \n");
            exit(1);
        }
    }

    return(0);
}    


//==============================================================================
//=  This is the thread function that handles service                          =
//==============================================================================
void *chat_serve(void *in_arg)
{
    long    client_s;           // Client socket descriptor
    char    in_buf[BUF_SIZE];   // Input buffer
    char    out_buf[BUF_SIZE];  // Output buffer
    int     fh;                 // File handle
    int     buf_len;            // Buffer length for file reads
    char    command[BUF_SIZE];  // Command buffer
    char    word[BUF_SIZE];     // Word to capitalize
    int     retcode;            // Return code
    int     i;
    char    *token;
    char    user[20];
    char    ip[20];

    

    // Set client_s to in_arg
    client_s = (long) in_arg;
    
    // NOTE: protect thread_count with a mutex
    thread_count++;
    printf("Thread sending hello (%d)\n", thread_count);

    strcpy(out_buf, HELLO);
    send(client_s, out_buf, strlen(out_buf)+1, 0);


    
    while (1) {  
        // Receive a chat request from a client
        memset(in_buf, 0, BUF_SIZE);
        retcode = recv(client_s, in_buf, BUF_SIZE, 0);
        printf("Received from client: %s\n", in_buf);

        // If the recv() return code is bad then bail-out
        if (retcode <= 0) {
            thread_count--;
            close(client_s);
            pthread_exit(NULL);
        }

        // Establish string and get the first token
        token = strtok(in_buf, " ");
        strcpy(command, token);

        // Parse and output the tokens
        while(token != NULL) {
            token = strtok(NULL, "\0");

            if (token != NULL) {
                strcpy(word, token);
            }
        }


        // Process command
        if (strcmp(command, "JOIN") == 0) {
            // Add client info to table
            if (!isDuplicate(word)) {
                if (!addToTable(word, current_client_ip_addr)) {
                    strcpy(user, word);
                    strcpy(ip, current_client_ip_addr);
                    showTable();
                } else {
                    strcpy(out_buf, FULL_CHAT_ERROR);
                    send(client_s, out_buf, strlen(out_buf)+1, 0);
                    thread_count--;
                    close(client_s);
                    pthread_exit(NULL);
                }
            } else {
                strcpy(out_buf, DUP_USR_ERROR);
                printf("%s, no user added", DUP_USR_ERROR);
                send(client_s, out_buf, strlen(out_buf)+1, 0);
                showTable();
                thread_count--;
                close(client_s);
                pthread_exit(NULL);
            }

            printf("Sending back: %s\n", out_buf);
            send(client_s, out_buf, strlen(out_buf)+1, 0);
        } else if (strcmp(command, "BYE") == 0) {
            removeFromTable(user);
            showTable();
            strcpy(out_buf, BYE_BYE);
            send(client_s, out_buf, strlen(out_buf)+1, 0);
            thread_count--;
            close(client_s);
            pthread_exit(NULL);
        } else if (strcmp(command, "WHO") == 0) {
            strcpy(out_buf, getTable());
            send(client_s, out_buf, strlen(out_buf)+1, 0);
        } else if (strcmp(command, "SEND") == 0) {
            strcpy(out_buf, user);
            strcat(out_buf, " says: ");
            strcat(out_buf, word);
            if (sendto(fd,out_buf,sizeof(out_buf),0,(struct sockaddr *) &multicast_addr,
                    sizeof(multicast_addr)) < 0) {
                perror("sendto");
                exit(1);
            }        
            strcpy(out_buf, "\n");
            send(client_s, out_buf, strlen(out_buf)+1, 0);
        } else {
            strcpy(out_buf, CMD_ERROR);
            send(client_s, out_buf, strlen(out_buf)+1, 0);
        }

    }

    thread_count--;
    close(client_s);
    pthread_exit(NULL);
}


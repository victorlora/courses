//==============================================================================
//=  Author: Victor Lora                                                       =
//=  File: chatClient.c                                                        =
//=  A chat client                                                             =
//==============================================================================
//=  Compile:                                                                  =
//=    - UNIX: gcc -o chatClient chatClient.c                                  =
//=----------------------------------------------------------------------------=
//=  Execute: chatClient                                                       =
//==============================================================================

#include <stdio.h>      // Needed for printf()
#include <stdlib.h>     // Needed for exit()
#include <string.h>     // Needed memcpy() and strcpy()
#include <sys/types.h>  // Needed for sockets stuff
#include <netinet/in.h> // Needed for sockets stuff
#include <sys/socket.h> // Needed for sockets stuff
#include <arpa/inet.h>  // Needed for sockets stuff
#include <fcntl.h>      // Needed for sockets stuff
#include <netdb.h>      // Needed for sockets stuff

//------ Defines ------
#define PORT_NUM              6055          // Port number used at the server
#define IP_ADDR         "192.168.130.10"    // IP address of server (* HARDWIREDD *)
#define BYE_BYE         "You've been disconnected"
#define CMD_ERROR       "Error: Invalid command"
#define FULL_CHAT_ERROR "Error: Chat room is full. Try again later"
#define DUP_USR_ERROR   "Error: Duplicate username"

//====== Main ======
void terminate(int socket, char *message) {
    printf("%s\n", message);
    close(socket);
    exit(-1);
}

int main(int argc, char *argv[])
{
    int                 client_s;       // Client socket descriptor
    struct sockaddr_in  server_addr;    // Server Internet address
    char                out_buf[4096];  // Output buffer for data
    char                in_buf[4096];   // Input buffer for data
    int                 retcode;        // Return code
    int                 finished = 0;

    // Create client socket
    client_s = socket(AF_INET, SOCK_STREAM, 0);

    if (client_s < 0) {
        printf("*** ERROR - socket() failed \n");
        exit(-1);
    }

    // Fill-in server address info & connect
    server_addr.sin_family      = AF_INET;              // Address family to use
    server_addr.sin_port        = htons(PORT_NUM);      // Port num to use
    server_addr.sin_addr.s_addr = inet_addr(IP_ADDR);   // IP address to use

    retcode = connect(client_s, (struct sockaddr *)&server_addr, sizeof(server_addr));

    if (retcode < 0) {
        printf("*** ERROR - connect() failed \n");
        exit(-1);
    }

    // Receive from the server using the client socket
    retcode = recv(client_s, in_buf, sizeof(in_buf), 0);

    if (retcode < 0) {
        printf("*** ERROR - recv() failed \n");
        exit(-1);
    }

    // Output the received message
    // printf("Received from server: %s \n", in_buf);
    char temp_out_buf[1024];
    strcpy(temp_out_buf, "JOIN ");

    if (argc > 1) {
        if (argv[1] != NULL) {
            char user_name[20];
            strcpy(user_name, argv[1]);
            strcat(temp_out_buf, user_name);
        } else {
            printf("*** ERROR - username must be provided as an argument");
            exit(-1);
        }
    } else {
        printf("*** ERROR - username must be provided as an argument");
        exit(-1);
    }

    strcpy(out_buf, temp_out_buf);
    retcode = send(client_s, out_buf, strlen(out_buf), 0);
    
    if (retcode < 0) {
        printf("*** ERROR - send() failed");
        exit(-1);
    }

    retcode = recv(client_s, in_buf, sizeof(in_buf), 0);

    if (retcode < 0) {
        printf("*** ERROR - recv() failed");
        close(client_s);
        exit(-1);
    }

    if (strcmp(DUP_USR_ERROR, in_buf) == 0) {
        terminate(client_s, DUP_USR_ERROR);        
    } else if (strcmp(FULL_CHAT_ERROR, in_buf) == 0) {
        terminate(client_s, FULL_CHAT_ERROR);
    }

    printf("Received from the server: %s\n", in_buf);

    while (!finished) {
        fgets(out_buf, 1024, stdin);
        out_buf[strlen(out_buf)-1] = '\0';

        if (strcmp(out_buf, "BYE") == 0) {
            printf("Client stopping \n");
            finished = 1;
        }

        printf("------\n");
        retcode = send(client_s, out_buf, strlen(out_buf), 0);

        if (retcode < 0) {
            printf("*** ERROR - send() failed \n");
            exit(-1);
        }

        // Receive from the server using the client socket
        retcode = recv(client_s, in_buf, sizeof(in_buf), 0);

        if (retcode < 0) {
            printf("*** ERROR - recv() failed \n");
            close(client_s);
            exit(-1);
        }

        // Output received message
        printf("Received from server: %s \n", in_buf);
    }

    // Close the client socket
    retcode = close(client_s);
    if (retcode < 0) {
        printf("*** ERROR - close() failed \n");
        exit(-1);
    }
}

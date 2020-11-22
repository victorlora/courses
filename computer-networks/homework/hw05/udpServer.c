//==================================================== file = udpServer.c =====
//
//  Author(s): Victor Lora
//      - George Benjamin's in-class example was used as the foundation
//        for this program
//
//  CSI355 - Homework 5
//
//
//----- Include files --------------------------------------------------------
#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <stdlib.h>
#include <sys/types.h>    // Needed for sockets stuff
#include <netinet/in.h>   // Needed for sockets stuff
#include <sys/socket.h>   // Needed for sockets stuff
#include <arpa/inet.h>    // Needed for sockets stuff
#include <fcntl.h>        // Needed for sockets stuff
#include <netdb.h>        // Needed for sockets stuff

//----- Defines --------------------------------------------------------------
#define  PORT_NUM   6060    // port number for the server

//===== Main program =========================================================

void main(void)
{
    int                  server_s;        // Server socket descriptor
    struct sockaddr_in   server_addr;     // Server Internet address
    struct sockaddr_in   client_addr;     // Client Internet address
    struct in_addr       client_ip_addr;  // Client IP address
    int                  addr_len;        // Internet address length
    char                 out_buf[4096];   // Output buffer for data
    char                 in_buf[4096];    // Input buffer for data
    int                  retcode;         // Return code

    // >>> Step #1 <<<
    // Create a socket
    //   - AF_INET is Address Family Internet and SOCK_DGRAM is datagram

    server_s = socket(AF_INET, SOCK_DGRAM, 0);
    if (server_s < 0)
    {
        printf("*** ERROR - socket() failed \n");
        exit(-1);
    }

    // >>> Step #2 <<<
    // Fill-in my socket's address information

    server_addr.sin_family = AF_INET;                 // Address family to use
    server_addr.sin_port = htons(PORT_NUM);           // Port number to use
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);  // Listen on any IP address
    retcode = bind(server_s, (struct sockaddr *)&server_addr, sizeof(server_addr));
    if (retcode < 0)
    {
        printf("*** ERROR - bind() failed \n");
        exit(-1);
    }

    // >>> Step #3 <<<<
    // Wait to receive a message from client

    printf("Waiting for recvfrom() to complete... \n");
    addr_len = sizeof(client_addr);
    retcode = recvfrom(server_s, in_buf, sizeof(in_buf), 0,
    (struct sockaddr *)&client_addr, &addr_len);


    // While message received doesn't equal "QUIT"
    // keep server alive & ready to respond
    while (strcmp(in_buf, "QUIT\n") != 0)
    {
        if (retcode < 0)
        {
            printf("*** ERROR - recvfrom() failed \n");
            exit(-1);
        }

        // Copy the four-byte client IP address into an IP address structure
        memcpy(&client_ip_addr, &client_addr.sin_addr.s_addr, 4);

        // Print an informational message of IP address and port of the client
        printf("IP address of client = %s  port = %d) \n", inet_ntoa(client_ip_addr),
        ntohs(client_addr.sin_port));

        // Output the received message
        printf("Received from client: %s \n", in_buf);


        // Uppercase Input
        int i = 0;

        // Convert all the chars in in_buf to UPPER and assign them to the indices
        // in out_buf
        while (in_buf[i])
        {
            out_buf[i] = toupper(in_buf[i]);
            i++;
        }

        // >>> Step #4 <<<
        // Send to the client using the server socket
        retcode = sendto(server_s, out_buf, (strlen(out_buf) + 1), 0,
        (struct sockaddr *)&client_addr, sizeof(client_addr));

        if (retcode < 0)
        {
            printf("*** ERROR - sendto() failed \n");
            exit(-1);
        }

        // Clear the array after every message is processed
        memset(out_buf, 0, sizeof out_buf);

        // Prepare for new message to be received
        retcode = recvfrom(server_s, in_buf, sizeof(in_buf), 0,
        (struct sockaddr *)&client_addr, &addr_len);
        
    }

    printf("Quitting...\n");

    // >>> Step #5 <<<
    // Close all open sockets
    retcode = close(server_s);
    if (retcode < 0)
    {
        printf("*** ERROR - close() failed \n");
        exit(-1);
    }
}

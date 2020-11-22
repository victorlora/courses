//==================================================== file = udpClient.c =====
//    
//    Author(s): Victor Lora
//      - George Benjamin's in class example
//        was used as the foundation for this assignment
//
//    CSI 355 - Homework 5
//
//    A client program to communicate on a UDP socket.
//
//    To compile MS VS 2010 Express:   cl udpClient.c Ws2_32.lib
//    To execute:                      udpClient
//    To compile with gcc on Linux:    gcc -o udpClient udpClient.c
//    To execute:                      udpClient

#define BSD       // WIN for Winsock and BSD for BSD sockets

#include <stdio.h>            // Needed for printf()
#include <string.h>           // Needed for memcpy() and strcpy()
#include <stdlib.h>
#ifdef WIN
    #include <windows.h>
#endif
#ifdef BSD
    #include <sys/types.h>    // Needed for sockets stuff
    #include <netinet/in.h>   // Needed for sockets stuff
    #include <sys/socket.h>   // Needed for sockets stuff
    #include <arpa/inet.h>    // Needed for sockets stuff
    #include <fcntl.h>        // Needed for sockets stuff
    #include <netdb.h>        // Needed for sockets stuff
#endif

//----- Defines ---------------------------------------------------------------
#define  PORT_NUM     6060             // Port number used
#define  IP_ADDR      "192.168.130.10" // IP address of server (*** HARDWIRED ***)

//===== Main program ==========================================================

void main(void)
{
#ifdef WIN
    WORD wVersionRequested = MAKEWORD(1,1);    // For WSA functions
    WSADATA wsaData;                           // For WSA functions
#endif
  int                  client_s;        // Client socket descriptor
  struct sockaddr_in   server_addr;     // Server Internet address
  int                  addr_len;        // Internet address length
  char                 out_buf[4096];   // Output buffer for data
  char                 in_buf[4096];    // Input buffer for data
  int                  retcode;         // Return code

#ifdef WIN
    // This initialized winsock
    WSAStartup(wVersionRequested, &wsaData);
#endif

  // >>> Step #1 <<<
  // Create a socket
  //   - AF_INET is Address Family Internet and SOCK_DGRAM is datagram
  client_s = socket(AF_INET, SOCK_DGRAM, 0);
  if (client_s < 0)
  {
    printf("*** ERROR - socket() failed \n");
    exit(-1);
  }

  // >>> Step #2 <<<
  // Fill-in server1 socket's address information
  server_addr.sin_family = AF_INET;                 // Address family to use
  server_addr.sin_port = htons(PORT_NUM);           // Port num to use
  server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); // IP address to use

  // Create an a buffer for input
  char input[140];

  printf("Enter your message: \n");
  fgets(input, 140, stdin);

  // Keep asking for input until user types in "QUIT"
  while (1)
  {
      // >>> Step #3 <<<
      // Now send the message to server.  The "+ 1" is for the end-of-string
      // delimiter
      strcpy(out_buf, input);

      retcode = sendto(client_s, out_buf, (strlen(out_buf) + 1), 0,
                      (struct sockaddr *)&server_addr, sizeof(server_addr));
      if (retcode < 0)
      {
        printf("*** ERROR - sendto() failed \n");
        exit(-1);
      }

      if (strcmp(out_buf, "QUIT\n") == 0)
      {
         break;
      }
        

      // >>> Step #4 <<<
      // Wait to receive a message
      //
      addr_len = sizeof(server_addr);
      retcode = recvfrom(client_s, in_buf, sizeof(in_buf), 0,
                         (struct sockaddr *)&server_addr, &addr_len);
      if (retcode < 0)
      {
        printf("*** ERROR - recvfrom() failed \n");
        exit(-1);
      }

      // Output the received message
      printf("\nReceived from server: %s", in_buf);

      // Prompt for new message
      printf("\nEnter your message: \n");
      fgets(input, 140, stdin);
  }

  printf("Quitting...\n");

  // >>> Step #5 <<<
  // Close all open sockets
#ifdef WIN 
    retcode = closesocket(client_s);
#endif
#ifdef BSD
    retcode = close(client_s);
#endif
    if (retcode < 0)
    {
      printf("*** ERROR - close() failed \n");
      exit(-1);
    }

#ifdef WIN
    // This cleans-up winsock
    WSACleanup();
#endif
}

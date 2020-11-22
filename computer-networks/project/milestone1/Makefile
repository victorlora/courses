all : chatServer chatClient

chatServer : utable.o chatServer.o
	gcc -o chatServer utable.o chatServer.o -lpthread

utable.o : utable.c
	gcc -c utable.c

chatServer.o : chatServer.c
	gcc -c chatServer.c -pthread

chatClient : chatClient.o
	gcc -o chatClient chatClient.o

chatClient.o : chatClient.c
	gcc -c chatClient.c

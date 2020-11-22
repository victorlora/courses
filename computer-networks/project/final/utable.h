#ifndef _UTABLE_H_
#define _UTABLE_H_

struct _user;  
typedef struct _user user;

#define MAX_USERS 10
#define FALSE     0
#define TRUE      1

int cleanTable(void);
int addToTable(const char*, const char*);
int removeFromTable(const char*);
int isDuplicate(const char*);
int showTable(void);
char* getTable(void);

#endif

/* showbytes.c
 *
 * This program display the bytes in a variety of
 * different variables using hex notation
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef unsigned char *byte_pointer; /* A type definition */

void show_bytes (byte_pointer start, int len) {
  int i;
  for (i = 0; i < len; i++)	/* Display each byte  */
    printf(" %.2x", start[i]);	/* %.2x is the format */
  printf("\n");                 /* specifier for hex  */
  printf("-------------\n");
}

void show_int(int x) {
  show_bytes((byte_pointer) &x, sizeof(int));
}

void show_float(float x) {
  show_bytes((byte_pointer) &x, sizeof(float));
}

void show_pointer(void *x) {
  show_bytes((byte_pointer) &x, sizeof(void *));
}

int main()
{
  /* int   ival = 10; 		*/	/* An integer value. 		*/
  /* float fval = (float) ival; */	/* A float value.i		*/
  /* int   *ptr = &ival;	*/	/* A pointer to an integer 	*/
  
  /* show_int( ival );	/* Show the bytes in the various	*/
  /* show_float( fval );	/* variable using hex notation		*/
  /* show_pointer( ptr );
   show_float(1.0);
   show_float(-1.0);
  */
  
  int val = 0xabcd1234;
 
  byte_pointer valp = (byte_pointer) &val;
  show_bytes(valp, 1); /* A. */
  show_bytes(valp, 2); /* B. */
  show_bytes(valp, 3); /* C. */
  
  show_int(783429);
  show_float(783429.0);

  const char *s  = "ABCDEF";
  const char *x = "abcdef";

  show_bytes((byte_pointer) s, strlen(s));
  show_bytes((byte_pointer) x, strlen(x));

  int z = 0x87654321 ^ ~0xff000000;
  int y = 0x87654321 |  0xff000000;

  printf(" %.2x \n",z);
  printf(" %.2x \n",y);

  return 0;
}


#include <stdio.h>

int num; /* Note: num has global scope. */

int main()
{
	num = 7;
	num = num + 12;
	return 0;
}

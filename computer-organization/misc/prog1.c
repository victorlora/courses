#include <stdio.h>

int main()
{
	int n;
	float x;

	n = 40000 * 40000;
	printf("40000 * 40000 = %d\n" , n);

	x = (1e20 + -1e20) + 3.14;
	printf("(1e20 + -1e20) + 3.14 = %f\n", x);

 	long z;

	z = 50000 * 50000;
	printf("50000 * 50000 = %lu\n", z);

}

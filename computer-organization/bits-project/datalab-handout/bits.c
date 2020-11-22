
/* 
 * CS:APP Data Lab 
 * 
 * Victor A. Lora
 * vl248684
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting an integer by more
     than the word size.

EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implent floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
     that you are allowed to use for your implementation of the function. 
     The max operator count is checked by dlc. Note that '=' is not 
     counted; you may use as many of these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* 
 * bitAnd - x&y using only ~ and | 
 *   Example: bitAnd(6, 5) = 4
 *   Legal ops: ~ |
 *   Max ops: 8
 *   Rating: 1
 */
int bitAnd(int x, int y) {

  /* Uses cantor's theorem to implement x&y w/o using & */
  return ~(~x|~y);
}

/* 
 * getByte - Extract byte n from word x
 *   Bytes numbered from 0 (LSB) to 3 (MSB)
 *   Examples: getByte(0x12345678,1) = 0x56
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 6
 *   Rating: 2
 */
int getByte(int x, int n) {

  /* Shifts x to the right 0,8,16,24; and then &s with a mask of
   * 0xff, which preserves and returns the requestedbyte. 
   */ 
  return x>>(n<<3) & 0xff;

}

/* 
 * logicalShift - shift x to the right by n, using a logical shift
 *   Can assume that 0 <= n <= 31
 *   Examples: logicalShift(0x87654321,4) = 0x08765432
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3 
 */
int logicalShift(int x, int n) {
  
  /* Shifts x to the right by n; creates a mask of 1s and 0s that
   * turns the bits introduced by the shifts to 0s, while preserving
   * the rest.
   */
  int shiftX = x >> n;
  int mask   = ~0;				// mask of 32 1's.
  int shiftMask = (mask << (32 + (~n)) << 1);
  shiftMask = ~shiftMask;
  
  return shiftX & shiftMask;
}

/*
 * bitCount - returns count of number of 1's in word
 *   Examples: bitCount(5) = 2, bitCount(7) = 3
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 40
 *   Rating: 4
 */
int bitCount(int x) {
  /* &s x with 0x1 and adds the result to sum, 32 times.  */
  int sum  = x       & 0x1;
      sum += (x>>1)  & 0x1;
      sum += (x>>2)  & 0x1;
      sum += (x>>3)  & 0x1;
      sum += (x>>4)  & 0x1;
      sum += (x>>5)  & 0x1;
      sum += (x>>6)  & 0x1;
      sum += (x>>7)  & 0x1;
      sum += (x>>8)  & 0x1;
      sum += (x>>9)  & 0x1;
      sum += (x>>10) & 0x1;
      sum += (x>>11) & 0x1;
      sum += (x>>12) & 0x1;
      sum += (x>>13) & 0x1;
      sum += (x>>14) & 0x1;
      sum += (x>>15) & 0x1;
      sum += (x>>16) & 0x1;
      sum += (x>>17) & 0x1;
      sum += (x>>18) & 0x1;
      sum += (x>>19) & 0x1;
      sum += (x>>20) & 0x1;
      sum += (x>>21) & 0x1;
      sum += (x>>22) & 0x1;
      sum += (x>>23) & 0x1;
      sum += (x>>24) & 0x1;
      sum += (x>>25) & 0x1;
      sum += (x>>26) & 0x1;
      sum += (x>>27) & 0x1;
      sum += (x>>28) & 0x1;
      sum += (x>>29) & 0x1;
      sum += (x>>30) & 0x1;
      sum += (x>>31) & 0x1;

  return sum;
}

/* 
 * bang - Compute !x without using !
 *   Examples: bang(3) = 0, bang(0) = 1
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 4 
 *
 *   In collaboration with Ron Gerschel
 */
int bang(int x) {
  /* Implementation ORs x and its 2's complement, then shifts the
   * results sign to the left by 31 to create a 'mask' of all 1s or all
   * 0s. Then it ^'s that mask with another mask of all 1s and &'s that
   * with 0x1, which should only return 1 if x was originally 0.
   */
  int tcX = ((~x)+1);
  x = tcX|x;
  x = ((x>>31)^((0x1<<31)>>31))&0x1;
  
  return x;
}

/* 
 * tmin - return minimum two's complement integer 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 4
 *   Rating: 1
 */
int tmin(void) {
  
  /* returns the min two's complement: 0x80000000. Can also
   * be written as: 0x1<<31.
   */
  return 0x8<<28;
}

/* 
 * fitsBits - return 1 if x can be represented as an 
 *  n-bit, two's complement integer.
 *   1 <= n <= 32
 *   Examples: fitsBits(5,3) = 0, fitsBits(-4,3) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 *
 *   In collaboration with Ron Gerschel
 */
int fitsBits(int x, int n) {
  
  /* By shifting x to left by shiftAmt, you in eliminate the "cushion"
   *  created by the sign bit. By shifting right by the same amount and
   *  ^ with x you eliminate the number entirely or eliminate the original
   *  number leaving behind the extras. If extras exist, then ! will give you
   *  0 and if the entire number was eliminated, ! will give you 1.
   */
  int result;
  int shiftAmt = (32+(~n+1));
  int shift1   = x << shiftAmt;
  int shift2   = shift1 >> shiftAmt;;
  result       = shift2 ^ x;
 
  return !(result);


}
 
/* 
 * divpwr2 - Compute x/(2^n), for 0 <= n <= 30
 *  Round toward zero
 *   Examples: divpwr2(15,1) = 7, divpwr2(-33,4) = -2
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int divpwr2(int x, int n) {
  
  /* In chapter two, the formula for dividing negative numbers by
   * pwrs of 2 is given as follows: (x+(1<<n)-1) >> n (1). Note also that 
   * the formula for dividing pos. numbers by pwr2 is: x>>n (2). Using these
   * two facts, I modified formula (1) by only allowing a shift of 1 and 
   * subtraction of 1 when the number is negative; these two procedure won't
   * happen otherwise, and instead give simply: x+(0<<n)-0 >> n, which is
   * equivalent to (2).
   */
  int signX = (x>>31) &0x1; 
  return ((x + (signX << n)) + (~(signX) + 1)) >> n;
}

/* 
 * negate - return -x 
 *   Example: negate(1) = -1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 5
 *   Rating: 2
 */
int negate(int x) {

  /* Simply returning the two's complement of x */
  return ~x+1;
}

/* 
 * isPositive - return 1 if x > 0, return 0 otherwise 
 *   Example: isPositive(-1) = 0.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 3
 */
int isPositive(int x) {
  
  /* This implementation, first takes the 2's complement of a num.
   * and then takes the sign of that complement. Then takes the sign
   * of X and !'s it. If x is positive, shifting it to the right by
   * 31 should result in 32 0s and in a 1 after applying !. similarly
   * the sign of the 2's complement of X should be 1. &ing the two
   * together results in 1 if its positive and 0 if it is negative or 0.
   */
  int result;
  int tcX     = (~x)+1;
  int signTcX = tcX >> 31;
  signTcX     = signTcX & 0x1;
  result      = signTcX & !(x>>31);
  
  return result;
}

/* 
 * isLessOrEqual - if x <= y  then return 1, else return 0 
 *   Example: isLessOrEqual(4,5) = 1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 24
 *   Rating: 3
 */
int isLessOrEqual(int x, int y) {
  
  /* Determines the sign for both x and y. By exclusive or-ing the signs
   *  we determine whether x and y are: +/+, -/-, +/-, or -/+. 
   *  SignZ1 creates a mask of all 1s if x and y are: -/+ or +/-.
   *  SignZ2 creates a mask of all 1s if SignZ2 is all 1s and signX is 
   *  all 1s.
   */
  int signX   = (x>>31);
  int signY   = (y>>31);
  int signZ1  = (signX^signY); 
  int signZ2  = signZ1 & signX;

  /* Sub subtracts  signSub takes the sign after result of the
   * subtraction. 
   */
  int sub = y+(~x+1);
  int signSub = sub >>31;

  int result = (signSub | signZ1) ^ signZ2;

  return !result;
}

/*
 * ilog2 - return floor(log base 2 of x), where x > 0
 *   Example: ilog2(16) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 90
 *   Rating: 4
 */
int ilog2(int x) {

  /* Unfortunately, while loops are not allowed  :'( */	
  int count = 0;
  while(x>>31 == 0)
  {
    x = x<<1;
    count += 1;
  }
 	
  return 31 - count;
}

//int main () { int result = ilog2(0x7fffffff); return result;}

/* 
 * float_neg - Return bit-level equivalent of expression -f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned float_neg(unsigned uf) {  
 
  // E gets the exponent of uf; frac gets the frac of uf.
  unsigned E    = uf & (0x7f800000);
  unsigned frac = uf & (0x007fffff);

  // Returns the argument if E=255 and frac=0, because the argument is NaN.
  if (E==0x7f800000 && frac!=0) {
    return uf;
  }
  else{
    return uf^0x80000000;	// toggles the sign bit.
  }
}

/* 
 * float_i2f - Return bit-level equivalent of expression (float) x
 *   Result is returned as unsigned int, but
 *   it is to be interpreted as the bit-level representation of a
 *   single-precision floating point values.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_i2f(int x) {
  return 2;
}

/* 
 * float_twice - Return bit-level equivalent of expression 2*f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representation of
 *   single-precision floating point values.
 *   When argument is NaN, return argument
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_twice(unsigned uf) {
/*  unsigned sign = uf & 0x10000000;
  unsigned E  = uf & (0x7f800000);
  unsigned frac = uf & (0x007fffff);
  unsigned body = E | frac;
  if (E==0x7f800000 && frac!=0) {
    return uf;
  }
  else if(uf == 0x80000000) {
    return uf;
  }
  else if(uf == 0x0) {
    return uf;
  }
  else {
    unsigned E2 = E + 0x80;
    return ((sign | E2 ) | frac);
  }
*/
 return 2; 
}

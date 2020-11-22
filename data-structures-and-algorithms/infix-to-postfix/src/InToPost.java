/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 22 October 2015
 * @author Victor Lora
 * @description 
 *   A program that translates Infix to Postfix.
 *   
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InToPost {
	
	// Construct the operator stack
	static Stack<String> OpStk = new Stack<String>();
	
	// Construct two maps for Stack and In priority
	static Map<String, Integer> StkPri = new Map<String, Integer>();
	static Map<String, Integer> InPri  = new Map<String, Integer>();

	public static void main(String[] args) throws FileNotFoundException
	{
		File fin = new File(args[0]);		// Construct a file object
		Scanner inFile = new Scanner(fin);	// A Scanner to read the file
		
		String alpha = "abcdefghijklmnopqrstuvwxyz";	// Construct the alphabet
		
		
		String token;
		
		// Add operator and it priority in corresponding map
		InPri.insert("^", 5);		StkPri.insert("^", 4);
		InPri.insert("*", 3);		StkPri.insert("*", 3);
		InPri.insert("/", 3);		StkPri.insert("/", 3);
		InPri.insert("+", 2);		StkPri.insert("+", 2);
		InPri.insert("-", 2);		StkPri.insert("-", 2);
		InPri.insert("==", 1);		StkPri.insert("==", 1);
		InPri.insert(">=", 1);		StkPri.insert(">=", 1);
		InPri.insert("<=", 1);		StkPri.insert("<=", 1);
									StkPri.insert("(", 0);
									StkPri.insert(")", 0);
									StkPri.insert("#", 0);
		
		
		while (inFile.hasNext()) {			// While not at EOF
			OpStk.push("#");					// Push # onto the stack
			String infix = inFile.nextLine(); 	// Read infix expression
			
			// Construct a StringTokenizer for infix expression.
			StringTokenizer expr = new StringTokenizer(infix);
			
			String postFix = "";
			while (expr.hasMoreTokens()) {	// While not at EOL
				token = expr.nextToken();		// get next token
				
				while (!token.equals("#")) {	// While token is not '#'
					if (alpha.indexOf(token) != -1) {	// If token is an operand
						postFix = postFix + token;		//		append it to postfix
					}
					else if (token.equals("(")) { // Left paren - Start of sub-expr
						OpStk.push(token);
					}
					else if (token.equals(")")) { // Right paren - End of sub-expr
						String operator = OpStk.pop();	
						
						while (!operator.equals("(")) {	// pop everything up to ')'
							postFix = postFix + operator;	// off stack and append
							operator = OpStk.pop();			// to post fix
						}
					}
					else {
						String opr = OpStk.top();	// set opr to top operand in stk
						
						// while value of stk operand is greater than input operand
						while (StkPri.getValue(opr) >= InPri.getValue(token)) {
							opr = OpStk.pop();			// pop operand from stk
							postFix = postFix + opr;	// append it to postfix
							opr = OpStk.top();			// set opr to next operand
						} // end-while when input token has higher priority
						
						OpStk.push(token);	// otherwise, push new operand onto stk
					}
					
					token = expr.nextToken();	// move on to next token
					
				} // end-while when '#' is reached
				
				while (!OpStk.isEmpty()) { 		// pop all remaining operands from stk
					String opr = OpStk.pop();	// inluding '#'
					postFix = postFix + opr;	// and append to postfix
				} // end-while when the stk is empty
				
				System.out.println(postFix + "\n");	// print new postfix expr
				
			} // end-while after end of line
		} // end-while when after last line
		
		inFile.close();	// close inFile to avoid memory leak
		
	} // end main()
}

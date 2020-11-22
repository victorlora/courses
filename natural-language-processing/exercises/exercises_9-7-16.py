import math

def exercise1():
    width = 17
    height = 12.0
    delimiter = '.'

    print "1: " + `width/2`
    print "2: " + `width/2.0`
    print "3: " + `height/3`
    print "4: " + `1 + 2 * 5`
    print "5: " + `delimiter * 5`

def exercise2():
    print_hello()
    
    print_name()
   
    multiply_inputs()
    
def print_hello():
    print "1:"
    print "hello" * 3

def print_name():
    print "2:"
    name = raw_input("What is your name? ")
    print "Hello " + name + "!"

def multiply_inputs():
    input1 = input("Input 1: ")
    input2 = input("Input 2: ")
    input3 = input("Input 3: ")

    print `input1 * input2 * input3`

def check_fermat(a, b, c, n):
    if n > 2 and a**n + b**n == c**n:
        print "Holy smokes, Fermat was wrong!"
    elif n < 2 and a**n + b**n == c**n:
        print a**n + b**n == c**n
    else:
        print "no, that doesn't work"

def eval_loop():
    keepGoing = True 

    while keepGoing:
        userInput = raw_input("Enter a value: ")
        if userInput == "done":
            keepGoing = False
            return userInput
        print eval(userInput)
        
def string_print():
    user_string = raw_input("What is your input? ")
    for c in user_string:
        print c
    print user_string.lower()
    print user_string.upper()
    reverse_string(user_string)

def reverse_string(user_input):
    lst = []
    for c in user_input:
        lst.append(c)

    lst.reverse()
    reversed_string = ""
    for c in lst:
        reversed_string += c

    print reversed_string

def main():
    #exercise1()
    #exercise2()
    #check_fermat(input("a= "), input("b= "), input("c= "), input("n ="))
    #eval_loop()
    string_print()

main()

""" WindChill.py

    This program is designed to give the windChill based on temperature and wind
    velocity inputed by the user.

    Author: Victor Lora

	Date: 1/23/14

	A.I.C.:_____________________
"""


#This line will prompt the user to enter the temperature
T = float(input('Enter the temperature (F): '))
#This will prompt the user to enter the wind velocity
V = float(input('Enter the wind velocity (mph): '))
#This will calculate the WindChill
WC = (35.74+0.6215*T)-(35.75*V**0.16)+(0.4275*T*V**0.16)
print(format(WC, ',.2f'))

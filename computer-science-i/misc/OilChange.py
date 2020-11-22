"""
OilChange.py

This program determines when its time for an oil change

Author: Victor Lora

Date: 4/8/14

A.I.C.:_____________________
"""
    
#This function requests the user to input the odometer reading for that week

def getOdometer(week, Previous):
    
    Odometer = int(input("Week %d: " %week))
    if Odometer < Previous:
            print ("Invalid reading - must be at least %d" %Previous)
            week -= 1
    return (Odometer, week)

#This function determines which message to display depending on wether or not an oil
#Change is required

def message(passedMiles,passedWeeks):
    
    if passedMiles == True and passedWeeks == True:
        print ("Oil change required \n Driven over 3000 miles since your\n"+
               "last oil change.\n 13 weeks since your last oil change.")
    elif passedMiles == True:
        print ("Oil change required\n Driven over 3000 miles since your\n last oil change.")
    elif passedWeeks == True:
        print ("Oil change required\n 13 weeks since your last oil change.")


def main():

    initialOdometer = int(input("Odometer at last oil change: ")) #prompts user for odometer
    Odometer = initialOdometer                                    #reading
    week = 1
    passedMiles = False
    passedWeeks = False
    
    while passedMiles == False and passedWeeks == False:          #While loop continues to prompt
                                                                  #
        Previous = Odometer
        (Odometer,week) = getOdometer(week, Previous)
        week += 1
                                      

        if Odometer >= (initialOdometer+3000):                    #Checks to see if its time for
            passedMiles = True                                    #for an oil change or if the
        if week > 13:                                             #number of weeks have been passed
            passedWeeks = True
            
    message(passedMiles,passedWeeks)
        
            
main()

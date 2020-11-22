"""paintjob.py
    This is a program that calculates the cost of painting a room

    Author: Victor Lora

    Date: 2/28/14

    AIC:_________________________
"""
def ComputeCost(Area,GallonPrice):
    NumGals = float(Area/115)                   #Calculates no. of gallons needed
    print ("Gallons needed: $ %.2f" %(NumGals)) #Prints Gallons needed
    LaborHours = float(NumGals*8)               #Calculates no. of labor hours
    print ("Labor hours: $ %.2f" %(LaborHours)) #Prints hours of labor
    PaintCost = float(GallonPrice*NumGals)      #Computes paint cost
    print ("Paint cost: %.2f" %(PaintCost))     #prints paint cost
    LaborCost = float(20.00*LaborHours)         #Computes total labor costs
    print ("Labor charges: $ %.2f" %(LaborCost))#prints labor cost
    TotalCost = float(PaintCost+LaborCost)      #Computes Labor costs
    print ("Total Cost of Job: $ %.2f" %(TotalCost))   #prints labor costs 

def main():
    Area = float(input("Wall space(in sq ft): "))         #Assigns a value to Area
    GallonPrice = float(input("Cost of paint per gallon: $")) #Assigns value to GalPrice
    ComputeCost(Area, GallonPrice)                 #Passes argument to ComputeCost()

main()

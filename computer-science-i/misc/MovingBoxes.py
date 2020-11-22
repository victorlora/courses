"""MovingBoxes.py
    This is a program that has four moving boxes in opposite directions that
    change color once the mouse button is pressed.

    Author: Victor Lora

    Date: 2/28/14

    AIC:_________________________
"""
import pygame
import random
pygame.init()


screen = pygame.display.set_mode((640,480))
def changeBoxColor(box):
    box.fill((random.randint(0,255),random.randint(0,255),random.randint(0,255)))
    #Randomizes the colors for each box when the mouse button is pressed down

def main():

    background = pygame.Surface(screen.get_size())
    #Constructs background
    background = background.convert()
    #Converts graphics format
    background.fill((0,0,255))
    #Fill background with blue

    box1=pygame.Surface((50,50)) #Constructs (50,50) surfaces
    box1=box1.convert()          #Converts graphics
    box1.fill((255,0,0))         #Fills with Red

    box2=pygame.Surface((50,50))
    box2=box2.convert()
    box2.fill((0,255,0))         #Fills box with Green

    box3=pygame.Surface((50,50))
    box3=box3.convert()
    box3.fill((255,0,255))       #Fills box with Pink

    box4=pygame.Surface((50,50))
    box4=box4.convert()
    box4.fill((0,255,255))       #Fills box with Teal


    box1_x = 300 #Red (Gives the original starting coordinates for box1)
    box1_y = 0

    box2_x = 200 #Green (Gives the original starting coordinates for box2)
    box2_y = 200

    box3_x = 400 #Pink (Gives the original starting coordinates for box3)
    box3_y = 300

    box4_x = 500 #Teal
    box4_y = 300

    clock = pygame.time.Clock()
    keepGoing=True

    while keepGoing:

        clock.tick(30)

        for event in pygame.event.get():
            if event.type==pygame.QUIT:
                keepGoing = False

        box1_y = box1_y+10 #This makes it move
        box2_x = box2_x+10 #
        box3_y = box3_y-10 #
        box4_x = box4_x-10 #

        if box1_y > screen.get_width():#These statments make sure the box
            box1_y = -100              #comes back
        if box2_x > screen.get_width():
            box2_x = -100
        if box3_y+50 < 0:
            box3_y = 640
        if box4_x+50 < 0:
            box4_x = 680

        elif event.type == pygame.MOUSEBUTTONDOWN:#this changes the box colors
            changeBoxColor(box1)
            changeBoxColor(box2)
            changeBoxColor(box3)
            changeBoxColor(box4)

            


        screen.blit(background, (0,0))      #Blits box and background onto screen
        screen.blit(box1, (box1_x, box1_y))
        screen.blit(box2, (box2_x, box2_y))
        screen.blit(box3, (box3_x, box3_y))
        screen.blit(box4, (box4_x, box4_y))

        pygame.display.flip()


main()
pygame.quit()

""" timingBox.py

    This is a Python / pygame program to calculate the average speed
    (in pixels per second) of a box moving across the screen.

    Author: Victor Lora

    Date: 1/23/14

    A.I.C.:_____________________
    """

import pygame
pygame.init()

# Construct a screen - 640 by 480 pixels.
screen = pygame.display.set_mode((640,480))


def main():


    # Construct a yellow background surface the same size as the screen.

    background = pygame.Surface(screen.get_size())  # Construct background.
    background = background.convert()               # Convert graphics format.
    background.fill((255, 255, 0))                  # Fill with yellow.

    # Now construct a box to move on the screen.

    box = pygame.Surface((100,100))   # Construct a 100 x 100 surface.
    box = box.convert()               # Convert graphics format.
    box.fill((255,0,0))               # Fill with red.

    myFont = pygame.font.SysFont("None", 30)
    myText1 = myFont.render("up arrow: faster", 1, (0,0,0) )
    myText2 = myFont.render("down arrow: slower", 1, (0,0,0) )
    myText3 = myFont.render("right arrow: speed = 5", 1, (0,0,0) )
    myText4 = myFont.render("left arrow: speed = 0", 1, (0,0,0) )
    myText5 = myFont.render("letter r: color red", 1, (0,0,0) )
    myText6 = myFont.render("letter g: color green", 1, (0,0,0) )
    myText7 = myFont.render("letter b: color blue", 1, (0,0,0) )
    myText8 = myFont.render("F1 - reset", 1, (0,0,0) )
    background.blit(myText1, (30,20))
    background.blit(myText2, (30,45))
    background.blit(myText3, (30,70))
    background.blit(myText4, (30,95))
    background.blit(myText5, (30,120))
    background.blit(myText6, (30,145))
    background.blit(myText7, (30,170))
    background.blit(myText8, (30,195))


    # set up some box variables - these will determine where
    #     the upper left corner of the box is located on the surface.

    box_x = 0       # The x-coordinate.
    box_y = 240     # The y-coordinate.
    xspeed = 5      # The speed of the box
    passes = 0      # The number of times the box crossed the screen.
    frames = 0      # The number of animation frames displayed since
                    #     a reset event.
                

    clock = pygame.time.Clock()  # A clock to control the frame rate.
    keepGoing = True             # Signals when the program ends.

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        for event in pygame.event.get():    # This will cause the program to
            if event.type == pygame.QUIT:   # terminate when the upper right
                keepGoing = False           # X on the window is clicked.
            elif event.type == pygame.MOUSEBUTTONDOWN:
                print('Number of passes:')+format(passes, ',.2f')
                frames1 = float(frames)
                minutes = frames1/1800
                print("Time Elapsed: ") + format(minutes, ',.2f') + (' minutes')
                xspeed1 = float(xspeed)
                print("Average Speed: ")+format(xspeed1/300, ',.2f') + (' pps')  
                
                ## Using the variables: frames, passes, box_x and box_x
                ## compute and print (since the last reset):
                ##
                ## 1) The number of box screen passes (to 2 decimal places).
                ## 2) The elapsed time (in minutes and seconds)
                ## 3) The average speed of the box
                ##    in pixels per second (to 2 decimal places).

                pass

                ##
                ##
                ##
                
            elif event.type == pygame.MOUSEBUTTONUP:  
                pass
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP:        # Up Arrow 
                    xspeed = xspeed + 1
                elif event.key == pygame.K_DOWN:    # Down Arrow
                    xspeed = xspeed -1
                    if xspeed < 0:
                        xspeed = 0
                elif event.key == pygame.K_RIGHT:   # Right Arrow ...
                    xspeed = 5                      #   start box moving.
                elif event.key == pygame.K_LEFT:    # Left Arrow ...
                    xspeed = 0                      #   stop the box.
                elif event.key == pygame.K_F1:      # F1 resets
                    frames = 0
                    passes = 0
                    box_x = 0
                    xspeed = 5
                    box.fill( (255,0,0) )
                elif event.key == pygame.K_r:
                    box.fill( (255,0,0) )
                elif event.key == pygame.K_g:
                    box.fill( (0,255,0) )
                elif event.key == pygame.K_b:
                    box.fill( (0,0,255) )
        
            
                    
                    

        box_x = box_x + xspeed  # move the box to the right 5 pixels
    
        # check if the box moved off the right side
        # of the screen and if it did, bring it back on the left.
    
        if box_x > screen.get_width():    # Reset box on the left
       	    box_x = 0                     # and count the number of
       	    passes = passes + 1           # screen traversals.

        # Blit the background to the screen at position (0,0)
        # Blit the box to the screen at its (box_x, box_y) position.
    
        screen.blit(background, (0,0))
        screen.blit(box, (box_x, box_y))  

        frames = frames + 1    # Count the number of frames displayed.
        
        pygame.display.flip()  # Flip the double buffered screen to
                               # create the animation.

# Call the main() function

main()
pygame.quit()  # Quit pygame

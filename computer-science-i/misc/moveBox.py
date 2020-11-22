""" moveBox.py
    """

import pygame
pygame.init()

    # Construct a screen - 640 by 480 pixels.
screen = pygame.display.set_mode((640,480))

def main():


    # Construct a yellow background surface the same size as the screen.

    background = pygame.Surface(screen.get_size())  # Construct background.
    background = background.convert()               # Convert graphics format.
    background.fill((0, 255, 0))                  # Fill with yellow.

    # Now construct a box to move on the screen. 

    box = pygame.Surface((100,100))   # Construct a 100 x 100 surface.
    box = box.convert()               # Convert graphics format.
    box.fill((255,0,0))               # Color the box red.

    # set up some box variables - these will determine where
    #     the upper left corner of the box is located on the surface.

    box_x = 0       # The x-coordinate.
    box_y = 200     # The y-coordinate.

    clock = pygame.time.Clock()  # A clock to control the frame rate.
    keepGoing = True             # Signals when the program ends.

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        for event in pygame.event.get():    # This will cause the program to
            if event.type == pygame.QUIT:   # terminate when the upper right
                keepGoing = False           # X on the window is clicked.

        box_y = box_y + 10
        box_x = box_x + 10 # move the box to the right 5 pixels
    
        # check if the box moved off the right side
        # of the screen and if it did, bring it back on the left.
    
        if box_y > screen.get_width():
       	    box_y = -100

        # Blit the background to the screen at position (0,0)
        # Blit the box to the screen at its (box_x, box_y) position.
    
        screen.blit(background, (0,0))
        screen.blit(box, (box_x, box_y))  
     
        pygame.display.flip()  # Flip the double buffered screen to
                               # create the animation.

# Call the main() function

main()
pygame.quit()  # Quit pygame

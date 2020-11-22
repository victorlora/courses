"""
	boucingBall.py
	
	A Program to display a bouncing ball that changes colors when the
	mouse button is pressed

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
    #background = pygame.Surface((600, 400))
    background = background.convert()               # Convert graphics format.
    background.fill((255, 255, 0))                  # Fill with yellow.

    # Now construct a box to move on the screen.

    box = pygame.Surface((100,100))   # Construct a 100 x 100 surface.
    box = box.convert()               # Convert graphics format.
    box.fill((255,255,0))             # Fill with yellow.

    # Draw a red circle on the box.

    pygame.draw.circle(box, (255,0,0), (50,50), 50, 0) 

    # set up some box variables - these will determine where
    #     the upper left corner of the box is located on the surface.

    box_x = 0       # The x-coordinate.
    box_y = 200     # The y-coordinate.

    xspeed = 5
    yspeed = 5

    clock = pygame.time.Clock()  # A clock to control the frame rate.
    keepGoing = True             # Signals when the program ends.

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        # Check for events.

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                pygame.draw.circle(box, (0,0,255), (50,50), 50, 0)
                pygame.draw.line(box, (255,255,255), (50,0), (50,100), 5)
                pygame.draw.line(box, (255,255,255), (0,50), (100,50), 5)
                print("Ouch!")
            elif event.type == pygame.MOUSEBUTTONUP:
                pygame.draw.circle(box, (255,0,0), (50,50), 50, 0) 
                

        box_x = box_x + xspeed  # move the box horizontally.
        box_y = box_y + yspeed  # move the box vertically.
    
        # check if the box moved off the right side
        # of the screen and if it did, bring it back on the left.
    
        if box_x > 540:
            xspeed = -1 * xspeed

        if box_x < 0:
            xspeed = -1 * xspeed

        if box_y > 380:
            yspeed = -1 * yspeed

        if box_y < 0:
            yspeed = -1 * yspeed

        #print(xspeed, yspeed)

        # Blit the background to the screen at position (0,0)
        # Blit the box to the screen at its (box_x, box_y) position.
    
        screen.blit(background, (0,0))
        screen.blit(box, (box_x, box_y))  
     
        pygame.display.flip()  # Flip the double buffered screen to
                               # create the animation.

# Call the main() function

main()
pygame.quit()  # Quit pygame

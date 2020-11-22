"""
   hitTarget.py

   This is a game whose object is to hit a moving target
   with a bomb fired from a cannon.

   The player has 4 bombs and loses the game if he / she does
   not hit the target within 4 tries.  The bomb is fired from
   the cannon by pressing on the space bar.

   Author: Victor Lora

    Date: 4/8/14

    A.I.C.:_____________________
   """

import pygame
import random
pygame.init()

screen = pygame.display.set_mode((640,480))   # Construct the screen and
pygame.display.set_caption("Hit The Target")  # set its caption.


class Cannon(pygame.sprite.Sprite):

    # A Cannon sprite is used to shot a bomb at at Target.

    def __init__(self):
        pygame.sprite.Sprite.__init__(self)    # Initialize the sprite.
        self.image = pygame.image.load("cannon.jpg")   #   Load image onto a Surface
        self.image = self.image.convert()              #   Convert pixel format.
        aColor = self.image.get_at((1,1))              #   Get color to make transparent.
        self.image.set_colorkey(aColor)                #   Now make it transparent.
        self.image = pygame.transform.scale(self.image, (100,75))  # Scale the surface.
        self.rect = self.image.get_rect()             # Get the rectangle for the image.
        self.rect.center = (70,215)                    # Cannon location.

    #  self.update() -  Rather than use update to change the
    #                   position of the cannon, we use functions:
    #                      self.moveup()
    #                      self.movedown()
    
    def update(self):
        pass

    #  self.moveup() - move the cannon 10 pixels up.
    #                  Don't move the top above the screen.

    def moveup(self):
        if (self.rect.top > 10):
            self.rect.centery -= 10

    #  self.movedown() - move the cannon 10 pixels down.
    #                    Don't move the top above the screen.
    
    def movedown(self):
        if (self.rect.bottom < 470):
            self.rect.bottom += 10

    #  self.get_pos() - return the center of the cannon
    #                   rectangle.  This is used to determine
    #                   where to shot the bomb from.
    
    def get_pos(self):
        return self.rect.center
        
            
class Target(pygame.sprite.Sprite):

    # A Target sprite is the target the cannon shoots at.

    def __init__(self):
        pygame.sprite.Sprite.__init__(self)    # Initialize the sprite.
        self.image = pygame.image.load("dartstarget.jpg")    # Create a target Surface
        self.image = self.image.convert()
        aColor = self.image.get_at((0,0))
        self.image.set_colorkey(aColor)                      # Note: the target's position
        self.image = pygame.transform.scale(self.image, (90,90)) # is at (tx, ty) and its
        self.rect = self.image.get_rect()
        self.rect.center = (540, 200)
        self.dy = 5                    # Target inital speed


    #  self.update() - Move the target up or down the screen.
    #                  When the target moves off the screen,
    #                  a new random speed and vertical direction
    #                  is chosen.
    
    def update(self):

        # Move the target vertically.

        self.rect.centery += self.dy

        if self.rect.bottom <= 0 or self.rect.top >= screen.get_height():
            
            # The target is off the screen, put it back on.
            # The target moves up or down using a random choice.

            direction = random.randint(0,1)
            
            if direction == 0:                    # Target goes down the screen.
                self.rect.bottom = 0
                self.dy = random.randint(5, 20)   # Change the target speed.
                
            else:                                 # Target goes up the screen.
                self.rect.top = screen.get_height()
                self.dy = - random.randint(5, 20) # change the target speed.


class Bomb(pygame.sprite.Sprite):

    # A Bomb sprite is fired by the cannon toward the target.

    def __init__(self):
        pygame.sprite.Sprite.__init__(self)    # Initialize the sprite.

        self.image = pygame.image.load("bomb.png")  # Create a surface for
        self.image = self.image.convert()           # the bomb image.
        aColor = self.image.get_at((0,0))
        self.image.set_colorkey(aColor)
        self.image = pygame.transform.scale(self.image, (25,25))
        self.rect = self.image.get_rect()           # Get the image's rectangle
        self.rect.center = (-100, -100)             # Move bomb offscreen.
        self.dx = 0                                 # Speed is 0 until fired.
        self.shooting = False        # Initially the bomb is not fired.

    # self.update() - move the Bomb horizontally toward the target.
    #   
            
    def update(self):
        if self.shooting:                                   
            self.rect.centerx += self.dx

    # self.reset() - Move the bomb off screen with
    #                a speed of 0.  It is finished being shot.

    def reset(self):
        self.rect.center = (-100, -100)
        self.dx = 0
        self.shooting = False

    # self.fire() - Initiate the firing of a Bomb.  The bomb
    #               is moved to the cannon location so that
    #      it is seen to fire from the cannon.  Set its speed
    #      and keep track of the fact that it is being shot.

    def fire(self, cannon_pos):
        if not self.shooting:
            self.rect.center = cannon_pos  # Move Bomb to cannon.
            self.rect.centery -= 25
            self.dx = 15                    # Set its velocity.
            self.shooting = True  # Signal the Bomb is in flight
                                  # toward the target.


class Label(pygame.sprite.Sprite):

    # A Label is used to render text on the screen.  When constructed
    #         the following parameters are specified:
    #              textStr - the text to render on the label.
    #              topleft - the topleft pixel location.
    #              fontType - the font file or None.
    #              fontSize - the size of the font.
    #              textColor - the color of the text on the label.
    #              backColor - the background color of the label.
    
    def __init__(self,textStr, topleft, fontType, fontSize, textColor, backColor):
        pygame.sprite.Sprite.__init__(self)
        self.font = pygame.font.Font(fontType, fontSize)
        self.text = textStr
        self.topleft = topleft
        self.textColor = textColor
        self.backColor = backColor

    # self.update() - Render the text on the label.
        
    def update(self):
        self.image = self.font.render(self.text, 1, \
                                      self.textColor, self.backColor)
        self.rect = self.image.get_rect()
        self.rect.topleft = self.topleft

#
#   title_screen() - Display a title screen and instructions.
#                    The screen stays displayed until the
#                    user clicks the mouse to start the game.


def title_screen(background):
    
    # Construct labels for a title and game instructions.
    
    titleLabel = Label("Hit the Target", \
                       (180,70), None,60,(0,0,0),(0,255,255))
    instr1 = Label("Try to hit the moving target", \
                   (30,200), None,30, (0,0,0), (0, 255, 255))
    instr2 = Label("You only have 4 tries", \
                   (30,240), None, 30 ,(0,0,0), (0, 255, 255))
    instr3 = Label("Press the space bar to fire the cannon!!", \
                   (30,280), None, 30, (0,0,0), (0, 255, 255))
    instr4 = Label("Use UP / Down arrows to move the cannon!!", \
                   (30, 320), None, 30, (0,0,0), (0, 255, 255))
    
    startLabel = Label("Click to start", \
                       (500, 420), None, 30,(0,0,0), (0, 255, 255))

    # Add the labels to a group
    
    labelGroup = pygame.sprite.Group(titleLabel, startLabel, \
                                     instr1, instr2, instr3, instr4)

    screen.blit(background, (0,0))  # Blit background to screen only once.

    clock = pygame.time.Clock()
    keepGoing = True

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        for event in pygame.event.get(): 
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.MOUSEBUTTONDOWN: # Title screen ends
                keepGoing = False                      # when the mouse button
                                                       # is clicked.
                                                       
        labelGroup.clear(screen, background)  # Update the display
        labelGroup.update()
        labelGroup.draw(screen)
        
        pygame.display.flip()
                


#  game() - Play the Hit the Target game.  A player is given 4 tries
#           to hit a target with a bomb fired from a cannon.

def game(background):

    background.fill((0,255,255))    # Clear the background
    screen.blit(background, (0,0))  # Blit background to screen only once.
    
                        # Construct the game entities:
    cannon = Cannon()   #    A Cannon object named cannon.  
    target = Target()   #    A Target object named target.
    bomb = Bomb()       #    A Bomb() object named bomb.
                        #    A Label object named bombsLabel that
                        #            is used to display the number
                        #            of bombs remaining to be fired.
                        
    bombsLabel = Label("",(10,10), None, 40, (0,0,0), (0, 255, 255))

    # Add the sprites to a group.

    allsprites = pygame.sprite.Group(cannon, target, bomb, bombsLabel)             
        
    clock = pygame.time.Clock()   # A clock for setting a frame rate.
    keepGoing = True              # Signals the game is over.
    bombs = 4                     # Bombs left to shoot  4 to start with.
    win = False                   # Flags game win or loss.
    shooting = False              # Flags a bomb in flight toward target.

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        for event in pygame.event.get():    # Handle events
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP:    # Move cannon up.
                    cannon.moveup()
                elif event.key == pygame.K_DOWN:
                    cannon.movedown()
                elif event.key == pygame.K_SPACE:  # Shoot a bomb
                    ## Step 1:  fire the bomb from the position of the cannon.
                    ##          DO NOT fire the bomb if there is one in flight.

                    if bomb.shooting == False:      #Allows the bomb to be 
                        bomb.fire(cannon.get_pos()) #fired only if another isn't
                                                    #mid-flight
    
                                                            
                pass
 

        bombsLabel.text =  "Bombs left: %d" % bombs                 

        ## Step 2 - dedented so NOT in the EVENT HANDLING for-loop
        ## In the space below write the code that checks if the bomb
        ## missed the target (i.e. if it went off the right side of the screen.
        ## If the bomb does miss the target you have to update the count of
        ## the number of bombs left and move the bomb off the stage by
        ## calling its reset() function.

        if bomb.rect.left > 640:            #Checks to see if the bomb went off
            bomb.reset()                    #the screen and if so, resets the 
            bombs -= 1                      #bomb to its original position

        
        
        ## Step 3 - dedented so NOT in the EVENT HANDLING for-loop
        ## Also check if the bomb hits the target.  In this
        ## case you want to signal a win for the player and stop the game.
        ## You can also move the bomb off the screen.

        if bomb.rect.colliderect(target):   #Checks if bomb hit target
            bomb.reset()                    #If so, win is set to True
            win = True                      #and the proper message screen is
            keepGoing = False               #displayed

         
        ## Step 4 - dedented so NOT in the EVENT HANDLING for-loop
        ## Below, check if the player has used all his or her bombs.
        ## In this case the game ends with a loss.

        if bombs == 0:                      #Checks to see the number of bombs
            win = False                     #remaining
            keepGoing = False               #Displays proper display screen



        # Update the display
        
        allsprites.clear(screen, background)
        allsprites.update()
        allsprites.draw(screen)                                        

        pygame.display.flip()

    ## Complete the code below to display an appropriate win or loss screen.
    ## Use the "win" boolean flag value to determine which message
    ## to display.
    
                                     
    if win == True:
        messageScreen(background,"You won!!")   # Display a win screen.
    if win == False:
        messageScreen(background, "You lost!!") # lose screen.
                

# messageScreen() - Display a message on the screen.  This function is
#                   used to display win, loss and end screens.

def messageScreen(background, message):
    # Display a message on the screen for 1.5 seconds.
    
    background.fill((0,255,255))   # Clear the background
    screen.blit(background, (0,0)) # Blit background to screen only once.

    # Construct the message to display and add it to a group.

    msgLabel = Label(message, (170,180), None, 90, (0,0,0), (0, 255, 255))
    msgGroup = pygame.sprite.Group( msgLabel )

    clock = pygame.time.Clock()
    keepGoing = True
    frames = 0

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False

        frames = frames + 1     # Count the number of frames displayed

        if frames == 45:        # After 1.5 seconds terminate the message
            keepGoing = False   # display.

        msgGroup.clear(screen, background)
        msgGroup.update()
        msgGroup.draw(screen)

        pygame.display.flip()


def main():
    
    background = pygame.Surface(screen.get_size()) # Construct a background
    background = background.convert()
    background.fill((0, 255, 255))

    title_screen(background)               # Display title and instructions.
    game(background)                       # Play the game.
    messageScreen(background, "The End")   # Final "The End" Screen.


# Call the main function

main()
pygame.quit() 

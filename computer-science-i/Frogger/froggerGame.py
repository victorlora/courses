"""froggerGame.py

    frogger game, objective is to get to all 3 lilly pads in order to win the game

    
    Author: Victor Lora

    Date: 4/28/14

    A.I.C.:_____________________
   """
import pygame, random
pygame.init()

screen = pygame.display.set_mode((640,480))

background = pygame.Surface(screen.get_size())       # Constructs a background
background = background.convert()               
background.fill((0, 200, 0))                         # fills the background with green
screen.blit(background, (0,0))                       # blits background


class Car(pygame.sprite.Sprite):                                           # this car class defines the model 
    
    def __init__(self, location, direction):                               # for all the cars being constructed in the game function 
        length = random.randint (40,120)                                   # this randomizes the length of the cars between 40-120 pixels
        
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("bus.png")                          # loads the image of a bus
        self.image = self.image.convert()                                  # converts the image
        aColor = self.image.get_at((1,1))                                  # picks out a color
        self.image.set_colorkey(aColor)                                    # makes that color transparent
        self.image = pygame.transform.scale(self.image, (length, 40))      # transforms the image to appropriate size
        self.rect = self.image.get_rect()                                  # assigns image as rect
        self.rect.centerx = 320                                            # centers image's x axis at 320
        self.rect.centery = location                                       # assigns a certain location input to the images y axis
        self.dx = direction * random.randint(5,10)                          # assigns the argument direction to self.dx
        self.dy = 0

    def update(self):                                                               
        self.rect.centerx += self.dx                  # moves the car in the x direction but not the y
        self.rect.centery += self.dy

        if self.rect.left > screen.get_width():       # causes the car to loop around the screen
            self.rect.left = -40
        if self.rect.right < 0:
            self.rect.right = 680

class Frog(pygame.sprite.Sprite):                     # creates the frog and follows same procedure from above
    
    def __init__(self):    
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("frog.png")
        self.image = self.image.convert()
        aColor = self.image.get_at((1,1))
        self.image.set_colorkey(aColor)
        self.image = pygame.transform.scale(self.image, (40,40))
        self.rect = self.image.get_rect();
        self.rect.centerx = 320
        self.rect.centery = 450
        self.dx = 20
        self.dy = 60

        
    def moveDown(self):                               # following functions dictate the frogs movements
        self.rect.centery += self.dy

    def moveUp(self):
        self.rect.centery -= self.dy

    def moveLeft(self):
        self.rect.centerx -= self.dx

    def moveRight(self):
        self.rect.centerx += self.dx

    def reset(self):
        self.rect.centerx = 320
        self.rect.centery = 450

    def update(self):                                  # prevents the frog from leaving the screen
        if self.rect.top < 0:
            self.rect.centery = 30
        elif self.rect.bottom > 480:
            self.reset()
        elif self.rect.left < 0:
            self.rect.left = 0
        elif self.rect.right > 640:
            self.rect.right = 640

class LilyPad(pygame.sprite.Sprite):                   # creates the lilypads and follows similar procedure from above
    
    def __init__(self ,locationX ,locationY):    
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("lilyPad2.jpg")
        self.image = self.image.convert()
        aColor = self.image.get_at((1,1))
        self.image.set_colorkey(aColor)
        self.image = pygame.transform.scale(self.image, (50,50))
        self.rect = self.image.get_rect();
        self.rect.centerx = locationX
        self.rect.centery = locationY

    def paintFrog(self,x):                                           # changes image from lily pad to lilypad with a frog when
        pygame.sprite.Sprite.__init__(self)                          # a frog lands on one
        self.image = pygame.image.load("lilyFrog2.png")
        self.image = self.image.convert()
        aColor = self.image.get_at((1,1))
        self.image.set_colorkey(aColor)
        self.image = pygame.transform.scale(self.image, (50,50))
        self.rect = self.image.get_rect();
        self.rect.center = x
        
class Label(pygame.sprite.Sprite):                                   # Template for the label
    
    def __init__(self,text,fontsize,fontcolor,backcolor):
        pygame.sprite.Sprite.__init__(self)
        self.font = pygame.font.Font(None, fontsize)
        self.image = self.font.render(text, 1, fontcolor, backcolor)
        self.image = self.image.convert()
        self.rect = self.image.get_rect()
        self.dy = 2

    def update(self):
        self.rect.centery -= self.dy

def titleScreen():                                                    # Creates titlescreen    
    titleLabel = Label("Frogger", 90, (0,0,0),(0,200,0))
    startLabel = Label("Click to start", 30, (0,0,0),(0,200,0))
    titleLabel.rect.center = (320,200)                                # Centers the labels
    startLabel.rect.center = (540,440)

    titleGroup = pygame.sprite.Group(titleLabel,startLabel)

    clock = pygame.time.Clock()                                       # Creates the clock
    keepGoing = True                                                  # Sets keepGoing to True

    while keepGoing:                                                  # Creates the while loop that displays title screen
        clock.tick(30)
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            if event.type == pygame.MOUSEBUTTONDOWN:
                keepGoing = False

        titleGroup.clear(screen,background)                            # Clears/updates/draws sprites on background
        titleGroup.draw(screen)

        pygame.display.flip()

def playAgain():                                                       # Creates the playAgain option screen  
    background.fill((0,200,0))                                         # Fills background with green
    screen.blit(background, (0,0))                                     # Blits the background
    
    playAgain = Label("Play again?", 80, (0,0,0),(0,200,0))            # Make labels for play again screen
    yesLabel = Label("Yes", 80, (0,0,0),(255,0,0))                                  
    noLabel = Label("No", 80, (0,0,0),(255,0,0))

    playAgain.rect.center = (320,200)                                               # Centers the labels
    yesLabel.rect.center = (200,350)
    noLabel.rect.center = (420,350)

    playagainSprites = pygame.sprite.Group(playAgain, yesLabel, noLabel)            # Creates sprite group
                                                              
    clock = pygame.time.Clock()                                                     # Creates the clock
    keepGoing = True

    while keepGoing:                                                                # Creates while loop that displays the screen
        clock.tick(30)

        for event in pygame.event.get():                                            # Checks for in game events

            if event.type == pygame.QUIT:                                           # Checks to see if player quit the game
                keepGoing = False
                playagain = False

            if event.type == pygame.MOUSEBUTTONDOWN:                                # Checks to see whether player wants to play
                collide = yesLabel.rect.collidepoint(pygame.mouse.get_pos())        # again or not
                if collide:                                                         
                    playagain = True
                    keepGoing = False

                noCollide = noLabel.rect.collidepoint(pygame.mouse.get_pos())
                if noCollide:                                                       # Stops playing if "no" is clicked
                    playagain = False
                    keepGoing = False

        playagainSprites.clear(screen,background)                                   # Clears/updates/draws sprites on background
        playagainSprites.draw(screen)

        pygame.display.flip()

    return playagain                                             # Returns the bool to main() whether or not to play again

def winLoseScreen(message):   
    pygame.draw.rect(background,(0,0,0),(50,50,540,340))         # Draws black rectangle on the screen
    screen.blit(background, (0,0))                               # Blits it onto the background

    WinLoseLabel = Label(message, 30,(255,255,255),(0,0,0))      # Template for label
    WinLoseLabel.rect.center = (320,220)                         # if win or lose
    WinLoseLabelSprites = pygame.sprite.Group(WinLoseLabel)

    clock = pygame.time.Clock()                                  # Creates clock
    keepGoing = True
    frames = 0
    
    while keepGoing:
        clock.tick(30)

        frames += 1                                              # Make statement last for 10 seconds
        if frames == 300:
            keepGoing = False

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            if event.type == pygame.MOUSEBUTTONDOWN:
                keepGoing = False

        WinLoseLabelSprites.clear(screen,background)              # Clears/updates/draws sprites on background
        WinLoseLabelSprites.draw(screen)

        pygame.display.flip()

def creditScreen():                                                                 # Displays ending credits
    background.fill((0,0,0))                                                        # Fills background with black
    screen.blit(background, (0,0))                                                  # Blits the background
    
    theEndLabel = Label("GAME OVER", 80, (255,255,255),(0,0,0))
    createdBy = Label("Created by:", 50, (255,255,255),(0,0,0))                     # Make labels for credit screen
    VLlabel = Label("Victor Lora", 30, (255,255,255),(0,0,0))                                  

    endY = 500
    createdY = 600
    VL = 650
    
    theEndLabel.rect.center = (320,endY)                                               # Centers the labels
    createdBy.rect.center = (320,createdY)
    VLlabel.rect.center = (320,VL)

    playagainSprites = pygame.sprite.Group(theEndLabel, createdBy, VLlabel)         # Creates sprite group
                                                              
    clock = pygame.time.Clock()                                                     # Creates the clock
    keepGoing = True
    frames = 0

    while keepGoing:                                                                # Creates while loop that displays the screen
        clock.tick(30)                                                                # Make statement last for 10 seconds
        
        if frames == 400 or VLlabel.rect.bottom < 0:
            keepGoing = False

        for event in pygame.event.get():                                            # Checks for in game events

            if event.type == pygame.QUIT:                                           # Checks to see if player quit the game
                keepGoing = False
                playagain = False
            elif event.type == pygame.MOUSEBUTTONDOWN:                              # game ends
                keepGoing = False
                playagain = False

        playagainSprites.clear(screen,background)                                   # Clears/updates/draws sprites on background
        playagainSprites.update()
        playagainSprites.draw(screen)

        pygame.display.flip()
    
 

def game():                                                                         # game() - Play the Frogger game.  
    background.fill((255, 255, 255))                                                # Fills background with white     
        
    pygame.draw.line(background, (0,0,0), (0,60), (640,60), 1)                      # Draws black lines onto the background
    pygame.draw.line(background, (0,0,0), (0,120), (640,120), 1)                    # Which represent the traffic lanes
    pygame.draw.line(background, (0,0,0), (0,180), (640,180), 1)
    pygame.draw.line(background, (0,0,0), (0,240), (640,240), 1)
    pygame.draw.line(background, (0,0,0), (0,300), (640,300), 1)
    pygame.draw.line(background, (0,0,0), (0,360), (640,360), 1)
    pygame.draw.line(background, (0,0,0), (0,420), (640,420), 1)

    water = pygame.Surface((640,60))                                                # Paints on of the sections blue
    water.fill((0,0,225))                                                           # to represent water

    grass = pygame.Surface((640,60))                                                # Paints another green for grass
    grass.fill((0,200,0))
    
    background.blit(water, (0,0))                                                   # Blits lines/grass/water onto screen
    background.blit(grass, (0,420))
    screen.blit(background, (0,0))

    car1 = Car(90, 1)                                                               # Creates 6 cars each inside a lane
    car2 = Car(150, -1)
    car3 = Car(210, 1)
    car4 = Car(270, -1)
    car5 = Car(330, 1)
    car6 = Car(390, -1)

    frog = Frog()                                                                   # Creates the frog

    lilyPad1 = LilyPad(160,30)                                                      # Creates 3 lilypads
    lilyPad2 = LilyPad(320,30)
    lilyPad3 = LilyPad(480,30)

    doneSprites = pygame.sprite.Group()                                             # Creates a spritegroup for the lilypads that are hit
    carSprites = pygame.sprite.Group(car1, car2, car3, car4, car5, car6)            # Car sprite group
    frogSprite = pygame.sprite.Group(frog)                                          # Frog sprite group
    lilySprites = pygame.sprite.Group(lilyPad1 ,lilyPad2 ,lilyPad3)                 # Lilypad sprite group
    
    
    clock = pygame.time.Clock()                                                     # Creates clock
    keepGoing = True                                                                # Sets keepGoing to True
    count = 0                                                                       # Sets count to 0
    
    while keepGoing:
    
        clock.tick(30)                                                              # Frame rate 30 frames per second.
        for event in pygame.event.get():                                            # Checks to see if user has quit the game
            if event.type == pygame.QUIT:
                keepGoing = False

            elif event.type == pygame.MOUSEBUTTONDOWN:                              # game ends
                keepGoing = False                                                   # when the mouse button is clicked
            elif event.type == pygame.KEYDOWN:                                      # Checks for keyDown events
                if event.key == pygame.K_DOWN:                                      # Moves frog down
                    frog.moveDown()
                elif event.key == pygame.K_UP:                                      # Moves frog up only if in front of lily
                    if frog.rect.centery != 90 or\
                       frog.rect.centerx == 160 or\
                       frog.rect.centerx == 320 or\
                       frog.rect.centerx == 480:
                        frog.moveUp()
                elif event.key == pygame.K_RIGHT:                                   # Moves frog right
                    frog.moveRight()
                elif event.key == pygame.K_LEFT:                                    # Moves frog left
                    frog.moveLeft()

        hitCar = pygame.sprite.spritecollide(frog, carSprites, False)               # Checks for frog and car collisions

        if hitCar:                                                                  # Ends the game if frog is hit by car
            keepGoing = False       
            winLoseScreen("Frog Dead! You Lose!")                                   # Displasy "you lose" screen    

        hitLily = pygame.sprite.spritecollide(frog ,lilySprites ,True)              # Checks for frog and lily collisions   

        for lily in hitLily:                                                        # Changes lily image when landed on
            x = lily.rect.center                                                    # by frog and passes lily's center
            lily.paintFrog(x)                                                       # x coordinate to paintFrog()
            count += 1                                                              # adds 1 to count
            doneSprites.add(lily)                                                   # adds lily to doneSprite group
            frog.reset()                                                            # resets frog
                
        if count == 3:                                                              # Once count = 3, game quits and 
            keepGoing = False                                                       # "you win" displays
            winLoseScreen("You Won!")
            
                    
        carSprites.clear(screen ,background)                                        # Clears/updates/draws sprites on background
        frogSprite.clear(screen ,background)
        lilySprites.clear(screen ,background)
        doneSprites.clear(screen ,background)
        
        carSprites.update()
        frogSprite.update()
        doneSprites.update()

        lilySprites.draw(screen)
        doneSprites.draw(screen)
        frogSprite.draw(screen)
        carSprites.draw(screen)
    
        pygame.display.flip()

    
def main():
    titleScreen()
    playagain = True
    while playagain:
        game()
        playagain = playAgain()
    creditScreen()
	

main()
pygame.quit()

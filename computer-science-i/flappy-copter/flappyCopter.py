"""flappyCopter.py

    flappyCopter game where you steer a chopper through the
    clouds, trying to get the highest score possible

    
    Author: Victor Lora & Jeff Mintz

    Date: 5/07/14

    A.I.C.:_____________________
    
    A.I.C.:_____________________
   """

import pygame, random
pygame.init()

# Loads the background

screen = pygame.display.set_mode((640, 480))
pygame.display.set_caption("FlappyCopter: Like Flappy Bird, "\
"but Better")

background = pygame.Surface(screen.get_size())
background = pygame.image.load("skyTitle.jpg")
background = pygame.transform.scale(background, (640,480))

screen.blit(background, (0,0))

class Chopper(pygame.sprite.Sprite):    # Creates Helicopter

    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.loadImages()

        self.frame = 0
        self.delay = 3
        self.pause = 0

        self.image = self.imgList[0]
        self.rect = self.image.get_rect()

        self.rect.center = (screen.get_width()/4, screen.get_height()/2)

    # Loads the sound for the chopper
        pygame.mixer.init()
        self.sndEngine = pygame.mixer.Sound("helicopter.wav")
        self.sndEngine.play(-1)

    # Loads the images in order and allows for the animation    
    def loadImages(self):
        imgMaster = pygame.image.load("heli3.png")
        imgMaster = imgMaster.convert()

        self.imgList = []

        #Determines the location and size of each image
        imgSize = [(128, 60), (128, 60), (128,60), (128, 60)]
        offset = [(2, 76), (134, 76), (266, 76), (398, 76)]

        for i in range(4):
            tmpImg = pygame.Surface(imgSize[i])

            tmpImg.blit(imgMaster, (0, 0), (offset[i], imgSize[i]))
            transColor = tmpImg.get_at((1, 1))
            tmpImg.set_colorkey(transColor)
            self.imgList.append(tmpImg)

    # Allows the user to move the chopper and checks to make sure its 
    # within the screen height
    def update(self):

        keys = pygame.key.get_pressed()

        if keys[pygame.K_UP]:
            self.rect.centery -= 12
        if keys[pygame.K_DOWN]:
            self.rect.centery += 12


        if self.rect.left < 0:
            self.rect.left = 0
        if self.rect.right > screen.get_width():
            self.rect.right = screen.get_width()
        if self.rect.top < 0:
            self.rect.top = 0
        if self.rect.bottom > screen.get_height():
            self.rect.bottom = screen.get_height()

        self.pause += 1
        if self.pause >= self.delay:
            self.pause = 0
            self.frame += 1
            if self.frame >= len(self.imgList):
                self.frame = 0

            self.image = self.imgList[self.frame]
            oldCenter = self.rect.center
            self.rect = self.image.get_rect()
            self.rect.center = oldCenter

    def get_pos(self):
        return self.rect.center

class Bomb(pygame.sprite.Sprite):

    # A Bomb sprite is fired by the cannon toward the target.

    def __init__(self):
        pygame.sprite.Sprite.__init__(self)    # Initialize the sprite.

        self.image = pygame.image.load("bomb1.jpg")  # Create a surface for
        self.image = self.image.convert()           # the bomb image.
        aColor = self.image.get_at((0,0))
        self.image.set_colorkey(aColor)
        self.image = pygame.transform.scale(self.image, (25,25))
        self.rect = self.image.get_rect()          # Get the image's rectangle
        self.rect.center = (-100, -100)             # Move bomb offscreen.
        self.dx = 0                                 # Speed is 0 until fired.
        self.shooting = False        # Initially the bomb is not fired.

    # self.update() - move the Bomb horizontally toward the target.
    #   
            
    def update(self):
        self.rect.centerx += self.dx
        if self.rect.left > 640:            #Checks to see if the bomb went off
            self.reset()                    #the screen and if so, resets the 
               

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

    def fire(self, chopper_pos):
            (x,y) = chopper_pos
            self.rect.center = (x, (y + 20))    # Move Bomb to cannon.
            self.rect.centery -= 25
            self.dx = 10                    # Set its velocity.
            self.shooting = True
            self.update()# Signal the Bomb is in flight

            pygame.mixer.init()
            self.sndEngine = pygame.mixer.Sound("missle.wav")
            self.sndEngine.play(0)                      # toward the target.

class Terrain(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("sky3.png")
        self.image = pygame.transform.scale(self.image, (1500,600))
        self.rect = self.image.get_rect();
        self.rect.bottom = 600      # Sets the background to the bottom
        self.dx = 5     # Sets the speed at which the background scrolls

    # Makes sure the background scrolls everytime the right side reaches the end    
    def update(self):
        self.rect.left -= self.dx
        if self.rect.right <= 640 or self.rect.left > 0:
            self.reset()
        return
    
    def reset(self):
        self.rect.left = 0      # Resets background
        return

# Loads the cloud images and sets their appropriate
# size
class Cloud(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("Cloud.gif")
        self.image = pygame.transform.scale(self.image,(100,100))
        self.image = self.image.convert()
        self.rect = self.image.get_rect()
        self.dx = 7
        

    def update(self):
        self.rect.centerx -= self.dx
        y = random.randint(200,400)
        if self.rect.right <= 0:
            self.reset()
        return
    
    def reset(self):
        self.image = pygame.transform.scale(self.image, \
                                            (random.randint(100,130),\
                                             random.randint(100,120)))
        self.rect.left = 640
        self.rect.centery = random.randint(50, 400)
        self.dy = 0
        return
    
class Label(pygame.sprite.Sprite):            # Template for the labels
    
    def __init__(self,text,fontsize,fontcolor,backcolor):
        pygame.sprite.Sprite.__init__(self)
        self.font = pygame.font.Font(None, fontsize)
        self.image = self.font.render(text, 1, fontcolor, backcolor)
        self.image = self.image.convert()
        self.rect = self.image.get_rect()
        self.dy = 2

    def update(self):
        self.rect.centery -= self.dy

def titleScreen():          # Creates titlescreen    
    titleLabel = Label("FlappyCopter", 70, (0,0,0),(184,252,255))
    startLabel = Label("Click to start", 30, (0,0,0),(184,252,255))
    titleLabel.rect.center = (200,50)     # Centers the labels
    startLabel.rect.center = (540,440)

    titleGroup = pygame.sprite.Group(titleLabel,startLabel)

    clock = pygame.time.Clock()         # Creates the clock
    keepGoing = True                    # Sets keepGoing to True

    while keepGoing:                    # Creates the while loop that displays title screen
        clock.tick(30)
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                keepGoing = False

        titleGroup.clear(screen,background)         # Clears/updates/draws sprites on background
        titleGroup.draw(screen)

        pygame.display.flip()

def playAgain():                                    # Creates the playAgain option screen  
    background = pygame.Surface(screen.get_size())
    background = pygame.image.load("skyTitle.jpg")
    background = pygame.transform.scale(background, (640,480))

    screen.blit(background, (0,0))                  # Blits the background
    
    playAgain = Label("Play again?", 80, (0,0,0),(184,252,255))    # Make labels for play again screen
    yesLabel = Label("Yes", 80, (0,0,0),(184,252,255))                                  
    noLabel = Label("No", 80, (0,0,0),(184,252,255))

    playAgain.rect.center = (320,30)                # Centers the labels
    yesLabel.rect.center = (200,400)
    noLabel.rect.center = (430,400)

    playagainSprites = pygame.sprite.Group(playAgain, yesLabel, noLabel)    # Creates sprite group
                                                              
    clock = pygame.time.Clock()                     # Creates the clock
    keepGoing = True

    while keepGoing:                                # Creates while loop that displays the screen
        clock.tick(30)

        for event in pygame.event.get():            # Checks for in game events

            if event.type == pygame.QUIT:           # Checks to see if player quit the game
                keepGoing = False
                playagain = False

            elif event.type == pygame.MOUSEBUTTONDOWN:    # Checks to see whether player wants to play again
                collide = yesLabel.rect.collidepoint(pygame.mouse.get_pos())    
                if collide:                                                         
                    playagain = True
                    keepGoing = False

                noCollide = noLabel.rect.collidepoint(pygame.mouse.get_pos())
                if noCollide:                       # Stops playing if "no" is clicked
                    playagain = False
                    keepGoing = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    playagain = False
                    keepGoing = False
                

        playagainSprites.clear(screen,background)   # Clears/updates/draws sprites on background
        playagainSprites.draw(screen)

        pygame.display.flip()

    return playagain                                # Returns the bool to main() 
                                                    # whether or not to play again

def winLoseScreen(message):   
    pygame.draw.rect(background,(0,0,0),(50,50,540,340))         # Draws black rectangle on the screen
    screen.blit(background, (0,0))                               # Blits it onto the background

    WinLoseLabel = Label(message, 30,(255,255,255),(0,0,0))      # Template for label
    WinLoseLabel.rect.center = (320,220)                         # if win or lose
    WinLoseLabelSprites = pygame.sprite.Group(WinLoseLabel)

    clock = pygame.time.Clock()                                  # Creates clock
    keepGoing = True
    frames = 0

    pygame.mixer.init()
    sound = pygame.mixer.Sound("bomb.wav")
    sound.play(0)
    
    while keepGoing:
        clock.tick(30)

        frames += 1                  # Make statement last for 10 seconds
        if frames == 100:
            keepGoing = False

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                keepGoing = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    keepGoing = False

        WinLoseLabelSprites.clear(screen,background)      # Clears/updates/draws sprites on background
        WinLoseLabelSprites.draw(screen)

        pygame.display.flip()

def creditScreen():                        # Displays ending credits
    background.fill((0,0,0))               # Fills background with black
    screen.blit(background, (0,0))         # Blits the background
    
    theEndLabel = Label("GAME OVER", 80, (255,255,255),(0,0,0))
    createdBy = Label("Created by:", 50, (255,255,255),(0,0,0))     # Make labels for credit screen
    VLlabel = Label("Victor Lora and Jeff Mintz", 40, (255,255,255),(0,0,0))                                  

    endY = 500
    createdY = 600
    VL = 650
    
    theEndLabel.rect.center = (320,endY)   # Centers the labels
    createdBy.rect.center = (320,createdY)
    VLlabel.rect.center = (320,VL)

    playagainSprites = pygame.sprite.Group(theEndLabel, createdBy, VLlabel)  # Creates sprite group
                                                              
    clock = pygame.time.Clock()            # Creates the clock
    keepGoing = True
    frames = 0

    while keepGoing:                       # Creates while loop that displays the screen
        clock.tick(30)                     # Make statement last for 10 seconds
        
        if frames == 400 or VLlabel.rect.bottom < 0:
            keepGoing = False

        for event in pygame.event.get():   # Checks for in game events

            if event.type == pygame.QUIT:  # Checks to see if player quit the game
                keepGoing = False
                playagain = False
            elif event.type == pygame.MOUSEBUTTONDOWN:    # game ends
                keepGoing = False
                playagain = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    keepGoing = False
                    playagain = False

        playagainSprites.clear(screen,background)   # Clears/updates/draws sprites on background
        playagainSprites.update()
        playagainSprites.draw(screen)

        pygame.display.flip()

class Scoreboard(pygame.sprite.Sprite):     # Creates Scoreboard Labels
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.lives = 5
        self.score = 0
        self.bombs = 10
        self.font = pygame.font.SysFont("None", 30)
        
    def update(self):       # Updates score
        self.text = "Choppers: %d, Score: %d, Bombs: %d" % (self.lives, self.score, self.bombs)
        self.image = self.font.render(self.text, 1, (255, 255, 0))
        self.rect = self.image.get_rect()

def instructions():                     # Fucntion for instructions
    background.fill((0,0,0))            # Fills background with black
    screen.blit(background, (0,0))

    pygame.display.set_caption("FlappyCopter: Like Flappy Bird, "\
    "but Better")
    
    insFont = pygame.font.SysFont(None, 30)
    insLabels = []
    instructions = (
    "Flappy Copter!!!" ,
    "Instructions:  You are a chopper pilot,",
    "Fly around and try to achieve a high score,",
    "but be careful not to fly too close",    
    "to the clouds. Your chopper will fall ",
    "apart if it is hit by clouds too",
    "many times. Steer with the up/down arrows.",
    "You can also fire at the clouds with the",
    "Space Bar. If a cloud is hit you will be rewarded;",
    "however, you have a limited number of bombs.",
    "",
    "good luck!",
    "",
    "Click to start"
    )
    
    for line in instructions:
        tempLabel = insFont.render(line, 1, (255, 255, 255))
        insLabels.append(tempLabel)
 
    keepGoing = True
    clock = pygame.time.Clock()
    
    while keepGoing:
        clock.tick(30)
        for event in pygame.event.get():        # Checks for quitting
            if event.type == pygame.QUIT:
                keepGoing = False
                donePlaying = True
            if event.type == pygame.MOUSEBUTTONDOWN:
                keepGoing = False
                donePlaying = False
            
    
        

        for i in range(len(insLabels)):
            screen.blit(insLabels[i], (50, 30*i))

        pygame.display.flip()
             
def game():

    
    
    chopper = Chopper()     # Creates chopper
    terrain = Terrain()     # Creates terrain

    cloud1 = Cloud()        # Creates clouds
    cloud2 = Cloud()
    cloud3 = Cloud()
    cloud4 = Cloud()

    bomb = Bomb()           # Creates bomb

    scoreboard = Scoreboard()   # Creates scoreboard

    allSprites = pygame.sprite.OrderedUpdates(terrain, chopper, bomb)
    cloudSprites = pygame.sprite.Group(cloud1, cloud2, cloud3, cloud4)
    scoreSprite = pygame.sprite.Group(scoreboard)
    
    cloud1.rect.centerx = random.randint(600, 650)
    cloud2.rect.centerx = random.randint(850, 900)
    cloud3.rect.centerx = random.randint(1050, 1100)
    cloud4.rect.centerx = random.randint(1250,1400)

    clock = pygame.time.Clock()     # Creates clock
    keepGoing = True
    frames = 0

            
    while keepGoing:        # While loop that controls
        clock.tick(30)      # in-game event

        frames += 1        # Make statement last for a few seconds
        if frames % 2 == 0:
            scoreboard.score += 2
        if frames % 100 == 0:
            print("speed increased")
            terrain.dx += .5
            cloud1.dx += .5
            cloud2.dx += .5
            cloud3.dx += .5
            cloud4.dx += .5
            
            
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
                chopper.sndEngine.stop()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    keepGoing = False
                    chopper.sndEngine.stop()
                elif event.key == pygame.K_SPACE:  # Shoot a bomb
                    if scoreboard.bombs <= 0:      # Stops bombs from shooting after
                        bomb.shooting = True       # they run out
                    elif bomb.shooting == False:      # Allows the bomb to be 
                        bomb.fire(chopper.get_pos())  # fired only if another isn't
                        scoreboard.bombs -= 1
                    
        planeHit = pygame.sprite.spritecollide(chopper, cloudSprites, False)
        if planeHit:
            pygame.mixer.init()     # Creates sound when chopper crashes into clouds
            chopper.sndEngine = pygame.mixer.Sound("metalBang.wav")
            chopper.sndEngine.play(0)
            for cloud in planeHit:      
                scoreboard.lives -= 1   # Subtracts 1 for every cloud hit
                cloud.reset()           # resets clouds
            if scoreboard.lives < 1:    # Stops the game after lives
                keepGoing = False       # run out
                chopper.sndEngine.stop()
                winLoseScreen("Chopper Crashed! You Lose!")
                

        hitClouds = pygame.sprite.spritecollide(bomb, cloudSprites, False)
        if hitClouds:
            for cloud in hitClouds:
                scoreboard.score += 10
                cloud.reset()   # Resets cloud
                bomb.reset()    # Mid-flight

        # Clears sprites
        allSprites.clear(screen, background)
        cloudSprites.clear(screen, background)
        scoreSprite.clear(screen, background)
        
        # Updates sprites
        allSprites.update()
        cloudSprites.update()
        scoreSprite.update()
        
        # Draws the sprites on screen
        allSprites.draw(screen)
        cloudSprites.draw(screen)
        scoreSprite.draw(screen)
        
        
        pygame.display.flip()

        
def main():
    titleScreen()
    instructions()
    playagain = True
    while playagain:
        game()
        playagain = playAgain()
    creditScreen()
        
main()
pygame.quit()

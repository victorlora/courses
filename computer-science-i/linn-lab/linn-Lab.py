""" linn.py 
    illustrates chopping several images
    from a master image to make an animation.
    Linn can walk toward the left,right, stand still, swing a sword.
"""

import pygame
pygame.init()

screen = pygame.display.set_mode((640, 480))
pygame.display.set_caption("Using a multi-image master file")

class Linn(pygame.sprite.Sprite):
    """ A sprite that can walk and jump - illustrates
        grouping images into different lists for different
        movements. """
    
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        
        self.loadImages()  # Load the images for the animation
                           # into a list of images.
        self.frame = 0  # Current image frame to display.
        self.delay = 3  # Used to slow down the animation.
        self.pause = 0  # Also used in slowing the animation.

        self.image = self.imgList[0]      # First image to show.
        self.rect = self.image.get_rect() # Used to place the image
        self.rect.center = (320,400)      
        self.dx = 0            # "speed" in the x direction.
        self.jump = False      # Indicates if the sprite is jumping.
        self.jumpCount = 0     # Used to implement the jump.
        self.attacking = False # Indicates if Linn is swinging a sword.
        
    def loadImages(self):                      
        imgMaster = pygame.image.load("kurosawa_sheet.png")  # The master image
        imgMaster = imgMaster.convert()             # has all the smaller
                                            # images that make up the
        self.imgList = []                   # animation.
        
                                # Here are the image sizes and their
        imgSize = (60, 80)      # location on the master image.
                                
        offset = ((220,0),   (320,0), (20,100), (120,100), (220,100),  \
                  (320,100), (20,200), (120,200), (220,200), (320,200),\
                  (20,300))

        for i in range(11):
            tmpImg = pygame.Surface(imgSize)        # Get a surface the size
                                                    # of the image then
                                          # blit the portion of the master
                                          # image that has the image we want.
            tmpImg.blit(imgMaster, (0, 0), (offset[i], imgSize))  
            transColor = tmpImg.get_at((1, 1))
            tmpImg.set_colorkey(transColor)   # Get a pixel from the corner
            self.imgList.append(tmpImg)       # of the image to use for
                                              # transparency.

        for i in range(1,11):                 # Flip the walking right images
            tmpImg = self.imgList[i]          # to obtain walking left images.
            tmpImg = pygame.transform.flip(self.imgList[i],True,False)
            self.imgList.append(tmpImg)

        # Load the images of Linn swinging a sword.
        
        imgsz = ((60,80), (60,80), (60,80), (60,80), (60,80), (114,80), (70,80),\
                 (100,80), (80,80), (156,80), (156,80), (72,80) )
                 
        offst = ( (119,300), (220,300), (320,300), (20,400), (120,400), (192,400),\
                  (314,400), (0,500),(112,500), (220,500), (20,600), (212,600))

        for i in range(12):
            tmpImg = pygame.Surface(imgsz[i])       # Get a surface the size
                                                    # of the image then
                                          # blit the portion of the master
                                          # image that has the image we want.
            tmpImg.blit(imgMaster, (0, 0), (offst[i], imgsz[i]))  
            transColor = tmpImg.get_at((1, 1))
            tmpImg.set_colorkey(transColor)   # Get a pixel from the corner
            self.imgList.append(tmpImg)       # of the image to use for transparency.

        # Now create lists of frames for the motions we want: walking left, right, etc.
                          
        self.stand = [0]                                                     # Standing
        self.right = [1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2]                  # Right
        self.left = [11,12,13,14,15,16,17,18,19,20, 19,18,17,16,15,14,13,12] # Left
        self.swing = [0,21,22,23,24,25,26,27,28,29,30,31,32]                 # Swinging
        
        self.frameList = self.stand  # frameList is the current list
                                     # of image frames - start out standing.                                                
    def update(self):
        self.pause += 1
        if self.pause >= self.delay:        # Only change the image if
            self.pause = 0                  # the delay is finished.
            self.frame += 1
            if self.frame >= len(self.frameList):  # Get the next frame
                self.frame = 0                     # number.

                # Keep the image centered and get the next image number
                # from our current image frame list.
                
            oldCenter = self.rect.center    
            self.image = self.imgList[self.frameList[self.frame]]
            self.rect = self.image.get_rect()
            self.rect.center = oldCenter
            self.rect.centerx += self.dx  # Move the image by dx.


            # Check if you're at the end of a sword swing animation
            
            if self.attacking and self.frame == len(self.swing) - 1:
                self.attacking = False
                self.frameList = self.stand

            if self.jump:                  # If the figure is supposed
                self.jumpCount += 1        # to be jumping, move it in
                if self.jumpCount < 11:    # y (vertical) direction, 
                    self.rect.centery -= 8 # first up and then down.
                else:
                    self.rect.centery += 8
                if self.jumpCount == 20:   # ...done jumping, reset the
                    self.jump = False      # jump flag.
                    self.jumpCount = 0

        self.checkbounds() # If Linn moves off the screen, change her direction.

    def standStill(self):
        """ Display the standing still images """
        self.frameList = self.stand
        self.dx = 0

    def walkRight(self):
        """ Display the walking right images """
        self.frameList = self.right
        self.dx = 0

    def walkLeft(self):
        """ Disply the walking left images """
        self.frameList = self.left
        self.dx = 0

    def attack(self):
        """ Use the sword swinging frames """
        self.frameList = self.swing  # Use the sword swing animations.
        self.frame = -1              # Prepare to start with the first frame.
        self.dx = 0                  # Keep Linn statonary.
        self.attacking = True        # Flag the use of sword swing frames.
        

    def jumpUp(self):
        """ initiate a jump """
        self.jump = True
        self.jumpCount = 0

    def checkbounds(self):
        """ When linn moves off the screen she turns
            and walks back on. """
        if self.rect.left > screen.get_width():
            self.walkLeft()
        elif self.rect.right < 0:
            self.walkRight()

class Terrain(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("terrain.bmp")
        self.rect = self.image.get_rect()
        self.rect.bottom = 30
        self.dx = 0
        self.reset()

        
    def update(self):
        self.rect.left += self.dx
        if self.rect.right <= 640 or self.rect.left > 0:
            self.reset()
        return
    
    def reset(self):
        self.rect.left = -960
        return
        
def main():
    
    background = pygame.Surface(screen.get_size())
    background.fill((155, 155, 255))
    screen.blit(background, (0, 0))
    
    linn = Linn()
    terrain = Terrain()
    allSprites = pygame.sprite.OrderedUpdates(terrain, linn)
    
    clock = pygame.time.Clock()
    keepGoing = True
    
    while keepGoing:
        clock.tick(30)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                linn.jumpUp()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RIGHT:
                    linn.walkRight()
                    terrain.dx -= 5
                elif event.key == pygame.K_LEFT:
                    linn.walkLeft()
                    terrain.dx += 5
                elif event.key == pygame.K_SPACE:
                    linn.standStill()
                elif event.key == pygame.K_s:
                    linn.attack()
                else: linn.standStill()
            elif event.type == pygame.KEYUP:
                linn.standStill()
                terrain.dx = 0
                

        
        allSprites.clear(screen, background)
        allSprites.update()
        allSprites.draw(screen)
        
        pygame.display.flip()
        

main()
pygame.quit()

import pygame
pygame.init()

screen = pygame.display.set_mode((640, 480))
pygame.display.set_caption("Using a multi-image master file")

class Chopper(pygame.sprite.Sprite):

    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.loadImages()

        self.frame = 0
        self.delay = 3
        self.pause = 0

        self.image = self.imgList[0]
        self.rect = self.image.get_rect()

        self.rect.center = (screen.get_width()/2, screen.get_height()/2)

    def loadImages(self):
        imgMaster = pygame.image.load("heli3.bmp")
        imgMaster = imgMaster.convert()

        self.imgList = []


        imgSize = [(128, 64), (128, 64), (128,64), (128, 64)]
        offset = [(2, 78), (134, 78), (266, 78), (398, 78)]

        for i in range(4):
            tmpImg = pygame.Surface(imgSize[i])

            tmpImg.blit(imgMaster, (0, 0), (offset[i], imgSize[i]))
            transColor = tmpImg.get_at((1, 1))
            tmpImg.set_colorkey(transColor)
            self.imgList.append(tmpImg)

    def update(self):

        keys = pygame.key.get_pressed()
        if keys[pygame.K_RIGHT]:
            self.rect.centerx += 5
        if keys[pygame.K_LEFT]:
            self.rect.centerx -= 5
        if keys[pygame.K_UP]:
            self.rect.centery -= 5
        if keys[pygame.K_DOWN]:
            self.rect.centery += 5


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

def main():

    background = pygame.Surface(screen.get_size())
    background.fill((155, 155, 255))
    screen.blit(background, (0,0))

    chopper = Chopper()
    allSprites = pygame.sprite.Group(chopper)

    clock = pygame.time.Clock()
    keepGoing = True

    while keepGoing:
        clock.tick(30)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False

        allSprites.clear(screen, background)
        allSprites. update()
        allSprites.draw(screen)
        pygame.display.flip()

main()
pygame.quit()

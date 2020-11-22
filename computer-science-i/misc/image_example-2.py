""" image_example.py
    Draw some images on the screen and move them around.
    """
    
import pygame
pygame.init()

screen = pygame.display.set_mode((640,480))
pygame.display.set_caption("Display an image")

def main():
    
    background = pygame.Surface(screen.get_size())
    background = background.convert()
    background.fill((255,255,0))
    
    mario = pygame.image.load("mario3.jpg")    # Load an image of mario
    mario = mario.convert()                    # make the background
    aColor = mario.get_at((1,1))               # transparent.
    mario.set_colorkey(aColor)
    (mario_x, mario_y) = (250,100)

    face2 = pygame.image.load("washington.jpg")      # Load an image of the
    face2 = pygame.transform.scale(face2, (150,150)) # 1st U.S. president.
    face2 = face2.convert()
    (f_x, f_y) = (10,10)
    
    clock = pygame.time.Clock()
    keepGoing = True
    while keepGoing:
        clock.tick(30)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    keepGoing = False
                elif event.key == pygame.K_UP:  # Move mario around the screen
                    if mario_y - 10 >= 0:       # but don't let him move off.
                        mario_y -= 10
                elif event.key == pygame.K_DOWN:
                    if mario_y + mario.get_height() + 10 < screen.get_height():
                        mario_y += 10
                elif event.key == pygame.K_LEFT:
                    if mario_x - 10 >= 0:
                        mario_x -= 10
                elif event.key == pygame.K_RIGHT:
                    if mario_x + mario.get_width() + 10 < screen.get_width():
                        mario_x += 10

        f_x += 5    # Move Washington's face across the screen
        if f_x > screen.get_width():
            f_x = - face2.get_width()
        
        screen.blit(background, (0,0))
        screen.blit(mario, (mario_x,mario_y))
        screen.blit(face2, (f_x,f_y))    
        pygame.display.flip()
        
main()
pygame.quit()
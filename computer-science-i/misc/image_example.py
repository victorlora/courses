""" image_example.py
    Draw some images on the screen and move them around.
    """
    
import pygame
pygame.init()

screen = pygame.display.set_mode((640,480))
pygame.display.set_caption("Display an image")

def main():
    
    background = pygame.Surface(screen.get_size())
    background = pygame.image.load("sky.png")
    background = pygame.transform.scale(background, (640,480))
   
    
    
    #sky = pygame.image.load("sky.png")      # Load an image of the
    #sky = pygame.transform.scale(sky, (640,480)) # 1st U.S. president.
    #sky = sky.convert()
    #(f_x, f_y) = (0,0)
    
    bird = pygame.image.load("flyingBird.jpg")
    bird = pygame.transform.scale(bird, (50,50))# Load an image of mario
    bird = bird.convert()                    # make the background
    aColor = bird.get_at((0,0))               # transparent.
    bird.set_colorkey(aColor)
    (bird_x, bird_y) = (240,5)

   
    
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
                    if bird_y - 10 >= 0:       # but don't let him move off.
                        bird_y -= 10
                elif event.key == pygame.K_DOWN:
                    if bird_y + bird.get_height() + 10 < screen.get_height():
                        bird_y += 10
                elif event.key == pygame.K_LEFT:
                    if bird_x - 10 >= 0:
                        bird_x -= 10
                elif event.key == pygame.K_RIGHT:
                    if bird_x + bird.get_width() + 10 < screen.get_width():
                        bird_x += 10

        #f_x += 5    # Move Washington's face across the screen
        #if f_x > screen.get_width():
        #    f_x = - sky.get_width() 
        
        screen.blit(background, (0,0))
        screen.blit(bird, (bird_x,bird_y))
        #screen.blit(sky, (0,0))    
        pygame.display.flip()
        
main()
pygame.quit()

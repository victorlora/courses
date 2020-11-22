"""
   catch_mickey_lab.py

   Click on a moving Mickey Mouse to win this game.
   """

import pygame
import random
pygame.init()

def title_screen(screen, background):
    # Display a title screen for the game.
    # Play begins when the mouse is clicked.

    disneyFont = pygame.font.Font("disney-comic.ttf", 50)
    disney2Font = pygame.font.Font("disney-comic.ttf", 30)
    title_label = disneyFont.render("Catch Mickey Mouse", 1, (0,0,0), (255,255,0))
    instr1_label = disney2Font.render("Click on Mickey to win.", 1, (0,0,0), (255,255,0))
    instr2_label = disney2Font.render("You only have ten seconds!",1, (0,0,0), (255,255,0))

    myFont = pygame.font.Font(None, 30)
    my_label = myFont.render("Click to start", 1, (0,0,0), (255, 255, 0))

    clock = pygame.time.Clock()
    keepGoing = True

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        for event in pygame.event.get(): 
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                keepGoing = False

        screen.blit(background, (0,0))
        screen.blit(title_label, (30,50))
        screen.blit(instr1_label, (30,200))
        screen.blit(instr2_label, (30,240))
        screen.blit(my_label, (500,420))

        pygame.display.flip()
                

def game(screen, background, games, games1, mouseClicks,sx,sy):
    # Play the Catch Mickey Game

                                              # Create a Mickey Mouse Surface.
    mick1 = pygame.image.load("Mickey2.jpg")  #   Load image onto a Surface
    mick1 = mick1.convert()                   #   Conert pixel format.
    aColor = mick1.get_at((1,1))              #   Get color to make transparent.
    mick1.set_colorkey(aColor)                #   Now make it transparent.
    mick1 = pygame.transform.scale(mick1, (50,50))  # Scale the surface.
    (m1_x, m1_y) = (295,215)                        # Initial Mickey location.

    
    dx = random.randint(sx, sy)   # Pick initial speed for Mickey.
    dy = random.randint(sx, sy)
    
    timerFont = pygame.font.Font(None, 40)  # For displaying time remaining.
        
    clock = pygame.time.Clock()   # A clock for setting a frame rate.
    keepGoing = True              # Signals the game is over.
    seconds = 10                  # Time left to play - initially 10 secs.
    frames = 0                    # Frame count for keeping time.
    win = False                   # Flags game win or loss.

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.
        for event in pygame.event.get():    # Handle events
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.MOUSEBUTTONDOWN:  # Check for a click
                (mx, my) = pygame.mouse.get_pos()       # on Mickey.
                mouseClicks += 1
                pygame.draw.circle(background,(255,0,0),(mx,my),10,0)
                if mx >= m1_x and mx <= m1_x + 50 and \
                   my >= m1_y and my <= m1_y + 50:
                    (win, keepGoing) = (True, False)    # Caught Mickey!
                    pygame.draw.circle(background,(0,255,0),(mx,my),5,0)
                    


        frames += 1                            # The frame count tracks time.
        if frames == 30:                       # Note: 30 frames per second
           (frames, seconds) = (0, seconds - 1)

        (m1_x, m1_y) = (m1_x + dx, m1_y + dy)   # Move Mickey
                                 
        if m1_x < 0 or m1_x > 590:   # Bounce Mickey off the screen boundaries.
            dx = -dx                 #    Change horizontal direction.
        if m1_y < 0 or m1_y > 430:   #
            dy = -dy                 #    Change vertical direction.

        if seconds == 0:         # When time runs out the game
            keepGoing = False    # the game is over.

        timer_text = timerFont.render( "Seconds: %d" % seconds, 1, (0,0,0), (255,255,0))
        gamesLeft = timerFont.render( "Game: %d of %d" % ((games-games1+1),games), 1, (0,0,0), (255,255,0))
        mouseClicks1=timerFont.render("Mouse Clicks: %d" %(mouseClicks), 1, (0,0,0), (255,255,0))
        
      
        screen.blit(background, (0,0))
        screen.blit(timer_text, (10,10))
        screen.blit(gamesLeft, (425,5))
        screen.blit(mouseClicks1, (0,450))
        screen.blit(mick1, (m1_x,m1_y))
        pygame.display.flip()
                              
    if win:                                      # Display a win or
        messageScreen(screen, background,"You won!!")    # lose screen.
    else:
        messageScreen(screen, background, "You lost!!")
                

def messageScreen(screen, background, message):
    # Display a message on the screen for three seconds.

    disneyFont = pygame.font.Font("disney-comic.ttf", 70)
    msg_label = disneyFont.render(message, 1, (0,0,0), (255,255,0))

    clock = pygame.time.Clock()
    keepGoing = True
    frames = 0

    while keepGoing:
    
        clock.tick(30)  # Frame rate 30 frames per second.

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False

        frames = frames + 1     # Count the number of frames displayed

        if frames == 90:        # After 3 seconds terminate the message
            keepGoing = False   # display.

        screen.blit(background, (0,0))
        screen.blit(msg_label, (170,180))

        pygame.display.flip()


def main():
    # Run the Catch Mickey Game. 

    ###  Add code here to prompt the user on the number of
    ###  games they want to play

    games = int(input("how many: "))

    

    ###
    ###

    screen = pygame.display.set_mode((640,480))  # Create a screen
    pygame.display.set_caption("Catch Mickey")   # and caption

    background = pygame.Surface(screen.get_size())
    background = background.convert()
    background.fill((255, 255, 0))


    title_screen(screen, background)               # Display title and instructions.
    ###
    
    games1 = games
    mouseClicks = 0
    sx = 15
    sy = 25
    while games1 != 0:
        game(screen,background,games,games1,mouseClicks,sx,sy)                     # Play the game.
        games1 -= 1
        sx+=10
        sy+=10
        background.fill((255, 255, 0))
        


    

        


    ###
    ###
    
    messageScreen(screen, background, "The End")   # Final "The End" Screen.


# Call the main function

main()
pygame.quit() 
    

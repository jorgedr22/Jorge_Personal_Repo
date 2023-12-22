import pygame as pg
import time
import random 

def score_board(score):
    value = score_font.render("Score:"+ str(score), True,white)
    dis.blit(value,[0,0])

def our_snake(snake_block,snake_list):
    for x in snake_list:
        pg.draw.rect(dis,green,[x[0],x[1],snake_block,snake_block])

def message(msg,color):
     mesg = font_style.render(msg,True,color)
     dis.blit(mesg,[dis_width/4, dis_height/3])
       
def SnakeGame():
    game_over = False
    game_close = False

    x1 = dis_width/2
    y1 = dis_height/2

    x1_change = 0
    y1_change = 0

    snake_List = []
    length_of_snake = 1
    
    food_x = round(random.randrange(0,dis_width - snake_block)/snake_block)*snake_block
    food_y = round(random.randrange(0,dis_height - snake_block)/snake_block)*snake_block

    while not game_over:
    
        while game_close == True:
            dis.fill(black)
            
            message("You Lost! Press Q-Quit or C-Play Again",white)
            
            pg.display.update()
            
            for event in pg.event.get():
                if event.type == pg.KEYDOWN:
                    if event.key == pg.K_q:
                        game_over = True
                        game_close = False
                    if event.key == pg.K_c:
                        SnakeGame()
            
        for event in pg.event.get():
            if event.type == pg.QUIT:
                game_over = True
            if event.type == pg.KEYDOWN:
                if event.key == pg.K_LEFT:
                    x1_change = -snake_block
                    y1_change = 0
                elif event.key == pg.K_RIGHT:
                    x1_change = snake_block
                    y1_change = 0
                elif event.key == pg.K_UP:
                    x1_change = 0
                    y1_change = -snake_block
                elif event.key == pg.K_DOWN:
                    x1_change = 0
                    y1_change = snake_block
        if x1 >= dis_width or x1 < 0 or y1 >= dis_height or y1 < 0:
            game_over = True
            
        x1 += x1_change
        y1 += y1_change
        
        dis.fill(black)
        pg.draw.rect(dis,red,[food_x,food_y,snake_block,snake_block])
        
        snake_Head = []
        snake_Head.append(x1)
        snake_Head.append(y1)
        snake_List.append(snake_Head)
        
        if len(snake_List) > length_of_snake:
            del snake_List[0]
        
        for x in snake_List[:-1]:
            if x == snake_Head:
                game_close = True 
        
        our_snake(snake_block,snake_List)
        score_board(length_of_snake - 1)
        
        pg.display.update()
        
        if x1 == food_x and y1 == food_y:
            food_x = round(random.randrange(0,dis_width - snake_block)/snake_block)*snake_block
            food_y = round(random.randrange(0,dis_height - snake_block)/snake_block)*snake_block
            length_of_snake += 1
            #length_of_snake += random.randrange(1,5)
        
        clock.tick(snake_speed)
    pg.quit()
    quit()

pg.init()

white = (255,255,255)
yellow = (255,255,102)
black = (0,0,0)
red = (213,50,80)
green = (0,255,0)
blue = (50,153,213)

dis_width = 800
dis_height = 600
dis = pg.display.set_mode((dis_width,dis_height))
pg.display.set_caption('Snake')

clock = pg.time.Clock()
snake_block = 20
snake_speed = 20

font_style = pg.font.SysFont(None,25)
score_font = pg.font.SysFont(None,35)

SnakeGame()

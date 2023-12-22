import pygame as pg
import time
import random 
import collections

# functions needed for agent (A.I.) to learn how to play
# reset 
# reward
# play(action) -> direction
# game_iteration
# is_collision

# optional:
# vary the speed of snake?
# infinte window?
# vary the length of snake after eating food?


#initializing pygame
pg.init()
font_style = pg.font.Font(None,25)
score_font = pg.font.Font(None,35)

#size and speed of snake
snake_block = 20
snake_speed = 20

#RGB COLORS
white = (255,255,255)
yellow = (255,255,102)
black = (0,0,0)
red = (213,50,80)
green = (0,255,0)
blue = (50,153,213)

#window dimensions
dis_width = 800
dis_height = 600

class SnakeGameAI:
    def __init__(self,w=dis_width,h=dis_height):
        self.w = w
        self.h = h
        
        #initialize display
        self.dis = pg.display.set_mode((dis_width,dis_height))
        pg.display.set_caption('Snake')
        self.clock = pg.time.Clock()
        self.reset()
         
        #initialize snake length
        self.snake_List = []
        self.length_of_snake = 1
    
    def reset(self):
        
        #initialize game
        self.x1=self.w/2
        self.y1=self.h/2
        
        self.x1_change = 0
        self.y1_change = 0
        
        self.score = 0
        self.food_x = 0
        self.food_y = 0
        self.place_food()
        
        self.frame_iteration = 0
    
    def play_step(self):
        
        # 1. user input
        for event in pg.event.get():
            if event.type == pg.QUIT:
                pg.quit()
                quit()
            if event.type == pg.KEYDOWN:
                if event.key == pg.K_LEFT:
                    self.x1_change = -20
                    self.y1_change = 0
                elif event.key == pg.K_RIGHT:
                    self.x1_change = 20
                    self.y1_change = 0
                elif event.key == pg.K_UP:
                    self.x1_change = 0
                    self.y1_change = -20
                elif event.key == pg.K_DOWN:
                    self.x1_change = 0
                    self.y1_change = 20
                    
        self.x1 += self.x1_change
        self.y1 += self.y1_change
                
        # 2. move
        self.move(self.x1,self.y1)
        
        # 3. check if game over
        game_over = False
        if self.collision():
            game_over = True
            return game_over

        # 4. place new food or just move
        if self.x1 == self.food_x and self.y1 == self.food_y:
            self.length_of_snake += 5
            self.place_food()
            
        # 5. update display and clock
        self.update_disp()
        self.clock.tick(snake_speed)
        
        # 6. return game over
        return game_over
        
        
    def update_disp(self):
        self.dis.fill(black)
        pg.draw.rect(self.dis,red,[self.food_x,self.food_y,snake_block,snake_block])
        self.clock.tick(snake_speed)
        
        for x in self.snake_List:
            pg.draw.rect(self.dis,black,[x[0],x[1],snake_block,snake_block])
            pg.draw.rect(self.dis,green,[x[0]+2,x[1]+2,snake_block-1,snake_block-1])
            
        value = score_font.render("Score:"+ str((self.length_of_snake - 1)), True,white)
        self.dis.blit(value,[0,0])
        pg.display.update()
        
    def place_food(self):
        self.food_x = round(random.randint(0,dis_width - snake_block)//snake_block)*snake_block
        self.food_y = round(random.randint(0,dis_height - snake_block)//snake_block)*snake_block
         
                    
    def move(self,x1,y1):
        #update snake head
        self.snake_Head = []
        self.snake_Head.append(x1)
        self.snake_Head.append(y1)
        self.snake_List.append(self.snake_Head)
        if len(self.snake_List) > self.length_of_snake:
            del self.snake_List[0]
    
    def collision(self):
        # hits boundary
        if self.x1 >= dis_width or self.x1 < 0 or self.y1 >= dis_height or self.y1 < 0:
            return True
        # hits itself
        if self.snake_Head in self.snake_List[:-1]:
            return True 
        
        return False
    
        
if __name__ == '__main__':
    game = SnakeGameAI()
    
    # game loop
    while True:
        game_over = game.play_step()
        if game_over == True:
            break
    
pg.quit()
    

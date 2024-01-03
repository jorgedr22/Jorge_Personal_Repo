# import the modules
import tkinter
import random
from tkinter import *
from tkinter.ttk import *
import sys
import os
import pygame
import time
import math
pygame.mixer.init()
# ADD FEATURES:
# - INPUT WORD NOT LETTERS
# - ADD BOXES TO INDICATE LETTER APPEARANCE AND/OR CORRECT INDEX
# - ADD SAD TRUMPET SOUND EFFECT IF LOSE
def start():
    pygame.init()

    WIDTH, HEIGHT = 800, 600

    win = pygame.display.set_mode((WIDTH, HEIGHT))
    pygame.display.set_caption("You won!!!!")


    FPS = 30

    COLORS = [
        (255, 0, 0),
        (0, 255, 0),
        (0, 0, 255),
        (0, 255, 255),
        (255, 165, 0),
        (255, 255, 255),
        (230, 230, 250),
        (255, 192, 203)
    ]

    class Projectile:
        WIDTH = 5
        HEIGHT = 10
        ALPHA_DECREMENT = 3

        def __init__(self, x, y, x_vel, y_vel, color):
            self.x = x
            self.y = y
            self.x_vel = x_vel
            self.y_vel = y_vel
            self.color = color
            self.alpha = 255

        def move(self):
            self.x += self.x_vel
            self.y += self.y_vel
            self.alpha = max(0, self.alpha - self.ALPHA_DECREMENT)

        def draw(self, win):
            self.draw_rect_alpha(win, self.color + (self.alpha,),
                                (self.x, self.y, self.WIDTH, self.HEIGHT))

        @staticmethod
        def draw_rect_alpha(surface, color, rect):
            shape_surf = pygame.Surface(pygame.Rect(rect).size, pygame.SRCALPHA)
            pygame.draw.rect(shape_surf, color, shape_surf.get_rect())
            surface.blit(shape_surf, rect)


    class Firework:
        RADIUS = 10
        MAX_PROJECTILES = 50
        MIN_PROJECTILES = 25
        PROJECTILE_VEL = 4

        def __init__(self, x, y, y_vel, explode_height, color):
            self.x = x
            self.y = y
            self.y_vel = y_vel
            self.explode_height = explode_height
            self.color = color
            self.projectiles = []
            self.exploded = False

        def explode(self):
            self.exploded = True
            num_projectiles = random.randrange(
                self.MIN_PROJECTILES, self.MAX_PROJECTILES)

            if random.randint(0, 1) == 0:
                self.create_circular_projectiles(num_projectiles)
            else:
                self.create_star_projectiles()

        def create_circular_projectiles(self, num_projectiles):
            angle_dif = math.pi*2 / num_projectiles
            current_angle = 0
            vel = random.randrange(self.PROJECTILE_VEL - 1,
                                self.PROJECTILE_VEL + 1)
            for _ in range(num_projectiles):
                x_vel = math.sin(current_angle) * vel
                y_vel = math.cos(current_angle) * vel
                color = random.choice(COLORS)
                self.projectiles.append(Projectile(self.x, self.y, x_vel, y_vel, color))
                current_angle += angle_dif

        def create_star_projectiles(self):
            angle_diff = math.pi/4
            current_angle = 0
            num_projectiles = 32
            for i in range(1, num_projectiles + 1):
                vel = self.PROJECTILE_VEL + (i % (num_projectiles / 8))
                x_vel = math.sin(current_angle) * vel
                y_vel = math.cos(current_angle) * vel
                color = random.choice(COLORS)
                self.projectiles.append(Projectile(
                    self.x, self.y, x_vel, y_vel, color))
                if i % (num_projectiles / 8) == 0:
                    current_angle += angle_diff

        def move(self, max_width, max_height):
            if not self.exploded:
                self.y += self.y_vel
                if self.y <= self.explode_height:
                    self.explode()

            projectiles_to_remove = []
            for projectile in self.projectiles:
                projectile.move()

                if projectile.x >= max_width or projectile.x < 0:
                    projectiles_to_remove.append(projectile)
                elif projectile.y >= max_height or projectile.y < 0:
                    projectiles_to_remove.append(projectile)

            for projectile in projectiles_to_remove:
                self.projectiles.remove(projectile)

        def draw(self, win):
            if not self.exploded:
                pygame.draw.circle(win, self.color, (self.x, self.y), self.RADIUS)

            for projectile in self.projectiles:
                projectile.draw(win)


    class Launcher:
        WIDTH = 20
        HEIGHT = 20
        COLOR = 'grey'

        def __init__(self, x, y, frequency):
            self.x = x
            self.y = y
            self.frequency = frequency  # ms
            self.start_time = time.time()
            self.fireworks = []

        def draw(self, win):
            pygame.draw.rect(
                win, self.COLOR, (self.x, self.y, self.WIDTH, self.HEIGHT))

            for firework in self.fireworks:
                firework.draw(win)

        def launch(self):
            color = random.choice(COLORS)
            explode_height = random.randrange(50, 400)
            firework = Firework(self.x + self.WIDTH/2,
                                self.y, -5, explode_height, color)
            self.fireworks.append(firework)

        def loop(self, max_width, max_height):
            current_time = time.time()
            time_elapsed = current_time - self.start_time

            if time_elapsed * 1000 >= self.frequency:
                self.start_time = current_time
                self.launch()

            fireworks_to_remove = []
            for firework in self.fireworks:
                firework.move(max_width, max_height)
                if firework.exploded and len(firework.projectiles) == 0:
                    fireworks_to_remove.append(firework)

            for firework in fireworks_to_remove:
                self.fireworks.remove(firework)


    def draw(launchers):
        win.fill("black")

        for launcher in launchers:
            launcher.draw(win)
        
        pygame.display.update()


    def start_firework():
        run = True
        clock = pygame.time.Clock()

        launchers = [Launcher(100, HEIGHT - Launcher.HEIGHT, 3000), Launcher(300, HEIGHT - Launcher.HEIGHT, 4000),
                    Launcher(500, HEIGHT - Launcher.HEIGHT, 2000), Launcher(700, HEIGHT - Launcher.HEIGHT, 5000)]

        while run:
            clock.tick(FPS)

            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_RETURN:
                        run = False
                        break

            for launcher in launchers:
                launcher.loop(WIDTH, HEIGHT)

            draw(launchers)
        restart_program()    
        
    start_firework()  

class Wordle:
    lives = 5   #lives to guess the word
    count = 0   # if count == 5, You win    
    mystery_word = {} #random word chosed from words.txt
    word = ""
    rand = 0 #random word gen
    temp = ""
    
         
def startGame(event):
    global s1    
    global letters_guess
    
    letter = event.char
    
    
    if len(str(letter)) > 2: #input more than two letters
        wrong = tkinter.Label(jorge, text = "One letter at a time",font = ('Helvetica', 12))
        wrong.pack()
        
    elif event.keysym == "Return" and s1.temp in s1.mystery_word.values(): #submit a guess and the guess is in the word
        for j in range(0,5):
            if s1.temp == s1.mystery_word[j]:
                letters_guess = letters_guess[:(j*2)] + s1.temp + letters_guess[(j*2)+1:]
                s1.count = s1.count + 1
        label_word.config(text=letters_guess)
        letters_box.delete(0,END)  
           
    elif event.keysym == "Return" and s1.temp not in s1.mystery_word.values(): #submit a guess and the guess is not in the word 
        label_word.config(text=letters_guess)
        s1.lives -= 1
        lives_counter.config(text=f'Lives left: {s1.lives}')
        letters_box.delete(0,END)
        if s1.lives == 0:
            label_word.config(text="You lose!\nThe word was\n"+s1.word)
            pygame.mixer.music.load("sadtrombone.swf.mp3")
            pygame.mixer.music.play(loops=0)
            
    elif event.keysym == "Return": # only press enter
        return
    
    elif letter:
        s1.temp = letter
        
    if event.keysym == "Return" and letters_guess.replace(" ","") == s1.word:
        label_word.config(text=letters_guess)
        pygame.mixer.music.load("Hall_of_Fame.mp3")
        pygame.mixer.music.play(loops=0)
        start()
        
        
def restart_program():
    os.system("clear")  
    python = sys.executable
    os.execl(python, python, * sys.argv)    
    
# Driver Code
# create a GUI window
jorge = Tk()

global s1
s1 = Wordle()

global letters_guess
letters_guess = "_ _ _ _ _"

with open("words.txt","r") as k:
        words = k.read()

s1.rand = 6 * (random.randint(0,5757))

for i in range(s1.rand,s1.rand+5):
    s1.mystery_word[(i%6)] = words[i];

for i in range(s1.rand,s1.rand+5):
    s1.word = s1.word[:(i%6)] + str(words[i]) + s1.word[(i%6)+1:]

# set the title
jorge.title("Wordle")

# set the size
jorge.geometry("500x500")

# add an instructions label
instructions = tkinter.Label(jorge, text = "Guess a letter in the word!",font = ('Helvetica', 18))
instructions.pack()

restart = Button(jorge, text="Restart", command=restart_program)
restart.pack(side=tkinter.BOTTOM)

# add a lives left label
lives_counter = Label(jorge, text = f"Lives left: {s1.lives}", font = ('Helvetica', 18))   
lives_counter.pack()

# add a label for displaying the word
label_word = tkinter.Label(jorge, text=letters_guess, font = ('Helvetica', 60))
label_word.pack()

#text box for inputing letters
letters_box = tkinter.Entry(jorge)
letters_box.focus_set()
letters_box.pack()

jorge.bind('<Key>',startGame)
print(s1.word)
# start the GUI
jorge.mainloop()


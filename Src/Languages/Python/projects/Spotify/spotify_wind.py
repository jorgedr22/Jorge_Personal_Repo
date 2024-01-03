import tkinter
import sys 
import os
from tkinter import *
from tkinter.ttk import *
import spotify as spot

def search(event):
    song_request = request.get()
    if event.keysym == "Return":
        spot.search_Song(song_request)
        request.delete(0,END)

music = Tk()

music.title("Spotify")
music.geometry("500x500")


inquery = tkinter.Label(music, text="What song would you like to search for?")
inquery.pack()

request = tkinter.Entry(music)
request.focus_set()
request.pack()

#restart = Button(music, text="Restart", command=restart_program)
#restart.pack(side=tkinter.BOTTOM)

music.bind('<Return>',search)

music.mainloop()


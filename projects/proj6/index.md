---
title: Project 6
parent: Projects
---

<script src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML" type="text/javascript"></script>


# CS 142 Project 6: Freeform Game
{: .no_toc }

1. TOC
{:toc}

In this assignment, you will be designing a game of your choice using object-oriented programming techniques learned in class. 
There are particular requirements for obtaining certain grades on the project, so you can pick what grade you want to aim for.


However, if you don't want to create a game using this framework, you can talk to me and design a project of your choice.  See the end of this page for details.

You may also work with a partner for this project if you desire.

## Starter code

**Make sure you create the project in a place on your computer where you can find it! I suggest making a new subfolder in your CS 142 projects folder.**

You can download the starter code for this assignment by creating a new IntelliJ project from version control (VCS) and using the following URL:

```
https://github.com/pkirlin/cs142-f24-proj6
```

## Concepts in this program

This project is almost entirely up to you in terms of both what you want the final game to look like and how it works, and also in terms of program design.  I am providing you with a general framework that you should work within, but you can modify things as you see fit.

The framework is designed around a canvas that holds a collection of `GameObject` objects.  These `GameObject`s represent images that can move around the screen and interact with each other however you want them to.  The `GameObjects` are stored inside the `Game` class (which you will either modify or make a copy of and modify the copy).

### The `GameObject` class

*I recommend opening the `GameObject.java` file and following along as you read this description.*

A `GameObject` represents an image in the game that will be drawn somewhere on the canvas.  `GameObjects` can represent any sort of object or entity in the game, be it yourself (your player or character), another character who might be a friend or an enemy or opponent, or some object in the environment that you might interact with in some way: food, a wall, something you can pick up and carry around, a vehicle you can get in and drive, a projectile of some sort, etc.

The basic `GameObject` class can be used directly; but it is also intended to be subclassed.

Each `GameObject` has the following instance variables:

- an (x, y) location, 
- an image filename used to display the object on the canvas, 
- a width and height (images will be automatically resized to fit in the width/height dimensions), and 
- a border color.  The border might be used to show some additional information about an object, such as whether it is "on" or "active" or how much energy it has left.  The border color could be used in a variety of different ways, or you can set it to `null` to hide the border.

`GameObject`s have the following methods:

- `public void draw(SimpleCanvas canvas)`: Each `GameObject` can draw itself on a canvas.  The default `draw()` code simply draws the appropriate image with a border of the desired color.  If you subclass `GameObject`, you may or may not want to override this method.
- `public boolean overlaps(GameObject other)`: `GameObject`s can detect when they collide or intersect with other `GameObject`s.  You will write this method to detect when one object overlaps another one, which can be used in various ways in a game.
- `public boolean isInside(int x, int y)`: If you want to use the mouse in your game, you may want to implement this method that can be used to detect if an (x, y) point (presumably a mouse click) is inside the object.
- `public int getLeftX()`/`getRightX()`/`getTopY()`/`getBottomY()`/`getCenterX()`/`getCenterY():` These methods are used to calculate the boundaries of the object on the canvas.  The (x, y) location instance variable stores the *center* of the image, so if we want to know where the four sides are, there are getter methods for those values.
- Various setters and getters for the image filename, width, height, and border color.

### The `Game` class


*I recommend opening the `Game.java` file and following along as you read this description.*

The `Game` class holds the main logic of your game.  It is expected that you will make a class similar to this one, or just modify the `Game.java` file itself.  I don't necessarily recommend subclassing `Game`; it's probably easier to copy and paste the code into a new file and just modify the copy.  That way you still have the basic `Game.java` skeleton to refer back to.

I will describe the general workings of this class, but you can modify it as you want/need to.

- Instance variables:
  Your class can have whatever instance variables you want.  Most likely you will need some `GameObject`s or probably variables of a *subclass* of `GameObject`.  You might need ArrayLists of GameObjects or their subclasses, depending on the game you design.
  
- There is also an instance variable for the canvas you will draw the game on.

Methods:

- `public void draw()`: This method is responsible for drawing the current "state" of the game on the canvas.  I recommend you do not do much drawing outside of this method, otherwise it will become challenging to keep track of what should be drawn when.  Exceptions might be small things like status messages on the canvas.  You will need to write most of the code for this method.

- `private boolean isGameOver()`: This method returns true when the game is over. It is not necessary to use this method if you don't want to, but if you do, you will need to write its code.

- `public void runGame()`: This method starts the game running.  You will need to fill in lots of the code here, but there are comments and a general guide included in the code I've given you already.  However, you should feel free to deviate from this guide if you want.

- `private void handleKeyboard()`: This method is called by `runGame()` to handle when the user presses keys on the keyboard. This could be used to move something in the game around (like a character or other `GameObject`), or switch between different "modes" or "options" in the game (spells to cast, attack or defense strategies, etc).  If you want to do anything with the keyboard, I suggest filling in this method.  There is code already written for you that demonstrates using the arrow keys, but you can detect any key on the keyboard with a similar strategy.

- `private void handleMouse()`: This method is called by `runGame()` to handle when the user clicks the mouse.  In truth, it detects when the mouse button is *down*, so if you hold the mouse down it it might register in your program more than once.  The mouse might be used to select objects in the game, move them around, target objects, etc.  If you want to do anything with the mouse, I suggest filling in this method.  There is code already written for you that demonstrates how to get the (x, y) location of the mouse click. 

## Two sample games

- Open and run the `RunDefaultGame` class.  This class serves as the "driver" code for the basic `Game` class, which really doesn't do anything.  When you run the code, it will open a canvas (that will be blank) and you can click the mouse and press the arrow keys, and messages will be printed showing information about what you clicked and pressed.  This will serve as a good demonstration of how the `Game` class works and how you might go about making your own game.

- Now open and run the `RunAlarmGame` class.  This class is a (slightly) more sophisticated game.  The idea is that you (the robot) need to turn off the alarm clock in a certain amount of time.  The robot turns off the alarm clock by touching it (so the two pictures intersect).  The problem is that some of the code is not written, namely, the code to detect when the the robot and the clock are touching.  Furthermore, it is possible to move the robot off the screen, which we don't want to allow.

  The game logic for this game is in the `AlarmGame` class, which you should look at, along with the `AlarmClock` class.  `AlarmGame` illustrates how to use `GameObjects`, how to use the keyboard to move an object around the canvas, and how to write code for `draw()`, `runGame()`, and `handleKeyboard()`.  

  You should also look at the code for the `AlarmClock` class, a subclass of `GameObject`.  This is a good example of how to make a subclass of `GameObject` with its own logic.  Here, we added an `update()` method to the class that is called once through the main game loop in `runGame()`, along with an instance variable and getter method to keep track of the "amount of time left" on the clock before it runs out.

## Implementing your game

First, **read through all of the assignment carefully**. Because this is a freeform project, you can design whatever classes you want.  You will most likely need to make one or more subclasses of `GameObject`.  You can add others classes as you see fit.

### Guidelines

Below, I specify exactly what you need to do get a "baseline" grade of A, B, or C.  Completing the requirements for that particular baseline grade will earn you that grade, provided that you do all the *other* things required for programs, such as proper documentation/comments, good use of object-oriented design, etc.  Excellent work will give you up to 4 additional points, but will not move you to the next letter grade boundary.  In other words, if you complete the guidelines for a "B" project, you will earn a baseline score of 85 points, plus up to 4 more for good object oriented design and programming choices, but you cannot earn a higher grade than 89.

- For a baseline grade of "C" (a 75), you must:
  - Implement the alarm clock game so that
    - The game ends either when the robot touches the alarm clock (a win), or when the time runs out (goes negative, which is a loss).
    - The robot must not be allowed to leave the boundary of the canvas.  As it stands, the keyboard can take the robot off the board, and you should prevent this from happening.
  - Note that to implement detecting when the robot touches the clock, you will need to write the code in `GameObject` for the `overlaps()` method.

- For a baseline grade of "B" (an 85), you must implement a game with the following features:
  - There will be a `GameObject` representing the player.  Similarly to the "C" project,
  the player should not be allowed to leave the boundaries of the screen.
  - There will be a collection of `GameObjects` representing "good" objects and "bad" objects, that will scroll across the screen in one direction.  You should have at least one subclass of
  `GameObject` for each type of object ("good" versus "bad").  You may wish to make
  more subclasses as well.
  - The player can use the keyboard to move around the canvas.  When a player touches an object, it disappears.  "Good" objects earn points, while "bad" objects deduct points.
  You should implement the `overlap()` method in `GameObject` for this.
  - Objects that scroll off the screen without being touched should be replaced in
  some way.
  - You get to choose the game's theme/story, by choosing the images, distribution of objects, game speed, scoring function, and game-over condition.  You can also vary things like how the objects move, their sizes, and how many points each one earns.  And, of course, you can do more if you like, as long as your game still fits the description given above. (Be sure to choose a theme for your game that is appropriate for a general audience!)

- For a baseline grade of "A" (a 95), you must implement a game with *some* of following features:
  - In general, you should do everything a "B" game does, but you can relax some of the rules if they don't fit your game idea.  In other words, it should be some game where there are objects that move around and the player touches them to advance the game in some way.  Here are things you can modify:
  - The main player character might have more capabilities than in a "B" project.  For example, maybe they have spells they can cast, or weapons they can use, or objects they can "throw," or they can change their size, or they might carry an inventory around, or they can change the way they move around the world, etc.  Maybe there are multiple players you can control.  Maybe the player has other attributes they keep track of besides the number of points, such as their armor, or life left, or what they are wearing.  These might affect when the game ends or other gameplay features.
  - The other objects might move in different ways (as opposed to just right to left).  Maybe they bounce around (like in the Fish lab) or maybe they will seek out the player and move towards them, or maybe they *avoid* the player and move away from them.  Maybe they have other capabilities like in the paragraph above (spells, weapons, an inventory, changing sizes, ...).  Maybe they work together as a team in some way.
  - Your game might use the mouse in some way, to target other players or objects.
  - You do not need to do *all* of these things for an "A".  However, the more you do, the higher your grade will be.

Baseline grades may be adjusted up or down for for excellent or poor use of object-oriented
programming techniques.

You may need to write another class like `RunDefaultGame` or `RunAlarmGame`  to get your game started.

## Demonstrations

The alarm clock game, when won, should work more or less like this:

<video controls>
  <source src="alarm-demo-win.mp4" type="video/mp4">
Your browser does not support the video tag.
</video>

The alarm clock game, when lost, should work more or less like this:

<video controls>
  <source src="alarm-demo-lose.mp4" type="video/mp4">
Your browser does not support the video tag.
</video>

Here's a *very* basic "B" game, probably the minimal "B" game you could write.  
*Your* game, to get an 85, should have more of a theme other than check marks and X's.

<video controls>
  <source src="get-avoid-demo.mp4" type="video/mp4">
Your browser does not support the video tag.
</video>



## Hints

- For the "B" game:
  - Try keeping an ArrayList of the `GameObjects` that are scrolling by.  You can move them a few pixels to the left each time through the game loop in `runGame()`. You can also loop over the ArrayList to check if any of them overlap with the player object, at which point they should disappear (remove them from the ArrayList or move them back to the right edge of the screen).
  - When you have images that scroll off the screen, you should remove them from the game if they will never appear again.  For instance, when images scroll off the left side, you can detect when they are no longer visible, and either remove them from the ArrayList entirely, or move them back to the right side of the screen so they will scroll by again.  What you *don't* want to do is create more and more images forever, because this will eventually slow the game down.  Basically you want the ArrayList to always have a fixed number of objects in it.
  
- You can find plenty of images online to use in your game.  You can also use emoji images (these work well because they are small sizes) at [Emojipedia](https://emojipedia.org/). 
Google Image Search also works well using the term "icon," like "snowman icon" or "wizard icon."   You can also use Google Image search and under "Tools," pick "Clip Art."  But truly any image will work fine.

## What to turn in

**Note that you need to turn in all your files for this project!**

Through Canvas, turn in *all* your `.java` files and *all* your image files.  If it's easier, you may upload a zip file with 
all the files. Additionally, upload a text file answering the following questions: **(note that there is an extra question!)**

- Which baseline project (A, B, or C) did you implement?  Describe what specific features you implemented that I should look for.
- What bugs and conceptual difficulties did you encounter? How did you overcome them? What did you learn?
- Describe any serious problems you encountered while writing the program (larger things than the previous question).
- Describe whatever help (if any) that you received. Don’t include readings, lectures, and exercises, but do include any help from other sources, such as 
websites or people (including classmates and friends) and attribute them by name.
- Did you do any of the challenges (see below)? If so, explain what you did.
- List any other feedback you have. Feel free to provide any feedback on how much you learned from doing the assignment, and whether you enjoyed doing it.

## Alternative projects

If you don't like the idea of creating a game, talk to me and suggest an alternative project, and we'll work out what you need to do
to have it substitute for this project.

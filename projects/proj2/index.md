---
title: Project 2
parent: Projects
---

# CS 142 Project 2
{: .no_toc }

1. TOC
{:toc}

You will write a program that allows the user to play a "Candy Crush Saga"-style game 
that we are calling "Balloon Bash." (Let me know if you can come up with a 
better name for this...) In the game, you are presented with a two-dimensional grid of 
balloons, and you release them from the bottom of the grid.  They float to the top, where
they meet other balloons, and if you get three balloons of the same color all touching,
the entire group pops.  You earn points for every balloon that pops, and you win the
game when all the balloons are popped.

**Alternate games**

If this game doesn't particularly interest you, you may create any two-dimensional game 
of your choice of comparable programming difficulty. See the end of this document for 
requirements and suggested games. You must get the instructor's approval before starting 
work on your project if you are not writing the Balloon Bash program.

## Game description

A video is worth a thousand words, so let's take a look at a demonstration.

<video controls>
  <source src="balloons-demo.mp4" type="video/mp4">
Your browser does not support the video tag.
</video>


Your game doesn't have to work exactly like mine, but it must meet the following requirements:

- The game should ask the user how big the board should be, in rows and columns.
- The game should draw a board of the specified size, filled with colored circles 
representing balloons along the *top three rows*. 
I find that using four different colors for balloons works well.
- The game should tell the user what color balloon will be released next (your code
should pick randomly among all the colors left on the board).
- The game should let the user click in the *bottom row of the screen*.  This row will
almost certainly be blank (unless you pick a really small number of rows or you are really
bad at the game).
  With every click, the program should place the new balloon at the bottom of the grid
where the user has clicked, and then the balloon should "float" to the top of the screen.
The balloon stops when it hits any other balloon or the top row of the grid.  
- At this point, the game checks if the balloon that floated to the top forms a region
of at least three balloons of identical color.
If it does, then all the balloons 
connected to the original one of the same color should pop (disappear).  Here, "connected" means
a neighbor of the same color, or neighbors of neighbors of the same color, or neighbors of
neighbors of neighbors, etc.  In other words, the single entire region of the same color
should disappear.  "Neighbors" means left, right, above, or below, not diagonal.
- When a region of balloons disappears, they are not replaced; those grid cells simply
become empty like the lower part of the board.
- If a floated balloon does not have any neighbors (or only one) of the same color, then
it simply stays where it is.
- 10 points are accumulated for each balloon popped in a region. For example, 
if you eliminate 3 balloons at once, that earns 30 points. However, bonuses are given 
for larger regions of balloons eliminated at once. For regions at least 5 balloons big, 
each balloon earns 50 points. So a region of 5 balloons all disappearing at once earns 
250 points. For regions at least 10 balloons big, you earn 100 points per balloon. For 
instance, if you can eliminate 10 balloons at once, that earns 1000 points!
- When all balloons are popped, the game ends and prints the total points earned.  About
the only way the game could not (eventually) be won is if the user plays so poorly the
region of balloons at the top grows so big as to fill the entire screen (but you do
not need to check for this).

## Starter code

**Make sure you create the project in a place on your computer where you can find it!
I suggest making a new subfolder in your CS 142 projects folder.**

You can download the starter code for this assignment by creating a new IntelliJ
project from version control (VCS) and using the following URL:

`https://github.com/pkirlin/cs142-f24-proj2`

In the starter project you will find three files:

- `BalloonBash.java` is where you will write your code.  A few methods are already
written for you.
- `SimpleCanvas.java` is the SimpleCanvas code that you don't need to worry about.
- `AnimationDemo.java` is a demonstration on using the `pause()` method in `SimpleCanvas`
to make an animation.  You will probably want to use `pause()` when you start implementing
the gravity part of the project, so you can use this as an example.

## Guide to the project

This project is set up similarly to the Tic-Tac-Toe lab.  The starter code uses a number
of familiar functions, including `handleMouseClick()` and `draw()`.  

**Representing a balloon board**

The balloon board will be represented as a 2D array of integers (`int[][]`), unlike the
tic-tac-toe board, which was a 2D array of characters (`char[][]`).  While you could use
`char`s, I think integers are easier because it allows us to represent the balloons
on the board like this:

  - If `board[row][col]` contains a zero, it means that square on the board is empty.
  - If `board[row][col]` contains an integer 1--4 (inclusive), that means that square
  contains a balloon.  The specific number 1--4 corresponds to the color of the balloon 
    (you can pick the colors; I used red/green/blue/yellow).
  - If `board[row][col]` contains a *negative* integer 1--4, that means that square
  contains a *marked* balloon.  Marked balloons are ones that are about to be removed,
  and are drawn in white.  (This concept is explained more in-depth later.) 
  
*In other words, just like the tic-tac-toe lab used `char`s to represent the empty spaces,
X's, and O's on the board, we will use `int`s in a similar way: zero = empty space, positive
numbers = balloons, negative numbers = "marked" balloons.*

**Drawing the board on the canvas**
In tic-tac-toe we used a square size of 100-by-100 because the game board was only 3 by 3, which
then scaled up to a 300 x 300 sized canvas.   
Because the game board for this project can be larger (it's more fun if it's larger), 
I suggest using a balloon size of 40-by-40 instead.  In other words, instead of multiplying
and dividing by 100, you will multiply and divide by 40.

**Marking balloons for removal**

When a balloon floats up and reaches the top of the screen or another balloon, 
we need to figure out the region of balloons that will be
removed.  The way we will do this is by "marking" balloons for removal in an iterative
process.  When a balloon reaches the top, you should check to see if it has at least one
neighbor of the same color (see below).  If it does, your code should change the balloon's number on
the board to its
*negative* equivalent.  Remember that the balloon colors are stored internally by positive
integers 1--4.  So when a balloon is clicked on, it will be marked for removal by changing
its integer to the corresponding negative integer.  That is, "1" becomes "-1", "2" becomes
"-2", etc.

**Finding the region of balloons to remove**

Remember that when a rising balloon reaches a stopping point, the entire contiguous color-matching region of balloons
connected to the initially-clicked balloon should be removed (as long as this region has at least three balloons.)  This will be done in two
phases.  First, we will find the region of balloons to be removed by "spreading" the
marked balloon (now a negative number) to all neighboring balloons above, below, left,
and right *that are the same color as the marked balloon*.  We do this over and over again,
as long as there are more balloons to mark.  Note that this iterative spreading process
is not shown visually; the user should only see the final result after the spreading
can't go any farther.

## Functions you will write

- The game begins in the `main()` method, like all Java programs.  There are instructions
there explaining the general algorithm.  To be clear, you should not write all of your
code inside `main()`!  
- I've given you the function definitions for two other functions: `handleMouseClick` and `draw`.  I've also
given you a useful function for debugging, `printBoard`, that prints the board in text.
This is useful for debugging.
- You must write a few other functions as well.  This will help you split the project into manageable pieces:

	- `public static boolean isColorOnBoard(int[][] board, int color)`: This function checks to see if a specific
	color is still located on the board.  When the game picks a random color for a new balloon, it should only use
	colors already on the board (to prevent a frustrating situation at the end of the game where you've eliminated a color
	but then it keeps getting regenerated).  I did this by picking a random color (1--4) and then checking to see if it's
	already on the board using this function.  If it wasn't, I just kept picking colors over and over until I got one that was
	on the board.
	
	- `public static boolean canMoveUp(int[][] board, int row, int col)`: This function checks to see if the balloon
	at (row, col) can "float" upwards on the board (in other words, checks to see if (row, col) has a blank space
	above it.)  If so, we know this balloon can continue to rise upwards.
	
	- `public static void moveUp(int[][] board, int row, int col)`: This function actually moves the balloon one
	space upwards.  (This function and the one above are very short, but very handy to write separately!)

	- `public static boolean hasMatchingNeighbor(int[][] board, int row, int col)`: This function returns a `boolean` indicating
	whether or not the balloon at (row, col) has a neighboring balloon of the same color.
	This function will be used in `main` to detect if a
	balloon that just rose to the top has a matching neighbor of the same color.  If
	there isn't a matching neighbor, the function will return `false`, and no balloons will be popped.
	
	- `public static int spreadMarked(int[][] board)`: After "marking" the initial balloon by switching the number
	of the balloon to its negative value, this function should "spread" the negative value
	by checking all the neighbors (up/down/left/right) of any marked balloon and marking
	neighbors with the matching number (color).  This can be called in a loop until
	no more marking happens.  Hints are below.  This function returns the total number of balloons that were switched
	to negative.  We need this number to keep track of the total number of negatives on the board, because if it's less than
	3, no balloons get popped.
	
	- `public static int removeMarked(int[][] board)`: After all the balloons needing to be marked are marked,
	I used this function to pop them (turn the negative numbers into zeroes).  Remember, popping
	a balloon just means we turn that element of the `board` array back into a zero (indicating it is empty).
	I returned the total number of balloons marked.
	
	- `public static int flipNegativeBack(int[][] board)`:  Used in case the rising balloon, when it stops at the top,
	only makes a region of *two* balloons.  Because we need at least *three* balloons to pop, we might end up in a situation
	where the `spreadMarked()` function has spread the negative number to all neighbors but we only have two.  In this case
	we need to turn the negatives back into positives (not zeroes) since no balloons are removed.
	
	- `public static int calcPoints(int balloons)`: I used this function to calculate how many points the player
	earns for removing however many balloons they removed.
	
**Javadoc comments**

In Lab 2, we learned about Javadoc comments.  You should place Javadoc comments
before any function you write in this project.  There are examples in the starter
code.
	
## Testing your program

You should test your program thoroughly to make sure it works.  You will do this by 
writing "test functions."  A test function is a function designed to test a different function,
that will run a number of test cases on the function being tested.  You can call these
test functions from main.

You must write test functions for `hasMatchingNeighbor()`, `canMoveUp()` and `spreadMarked()`.  Additional
test functions are highly recommended, but not required.

For instance, to test `gravity()`, you might write this:

```java
public static void testGravity()
{
	int[][] board1 = 
		{ {1, 3, 2, 1},
		  {1, 0, 0, 3},
		  {2, 2, 0, 0},
		  {0, 0, 0, 1} };
	boolean b = canMoveUp(board1, 2, 1);
	System.out.println(b);
}
```

And then you would verify that your test code prints `true`.

## What to turn in


- Through Canvas, turn in your `BalloonBash.java` file.  
Additionally, upload a text file answering the following questions:

-   What bugs and conceptual difficulties did you encounter? How did you overcome them? What did you learn?
- Describe any serious problems you encountered while writing the program (larger things than the previous question).
-   Describe whatever help (if any) that you received. Don’t include readings, lectures, and exercises, but do include any help from other sources, 
such as websites or people (including classmates and friends) and attribute them by name.
-   Did you do any of the challenges (see below)? If so, explain what you did.
-   List any other feedback you have. Feel free to provide any feedback on how much you learned from doing the assignment, and whether you enjoyed 
doing it.

## Challenges

- Add additional functionality to the game like you might find in games like Candy Crush Saga, Angry Birds, Toon Blast, etc.
- For example, make up different kinds of "items" that can appear in squares on the board. Like a bomb that explodes neighboring squares, or a present that earns you bonus points.
- Make the same-color-neighbor concept work with diagonal directions (so regions can extend diagonally as well as horizontally or vertically).

## Hints and tips

- **Checking to see if a balloon is next to one of the same color**
This is a slightly tricky one because it involves checking the neighboring squares on the game board. What makes it tricky is that if the balloon is on the border of the board, then all four neighbors (up, down, left, right) may not exist.

- **Finding the region of balloons that should disappear**

	In the demo video above, we use the color white to show the balloons that are about to disappear.  To make this happen, we will use a negative numbers to represent these balloons.

	When a balloon stops rising, first check if the balloon has a neighbor of the same color.  If there is a same-colored neighbor, change the balloon from whatever number it is to its negative version (so change 1 to -1, 2 to -2, etc).  This indicates that it will disappear.  Then we will "spread" these negative numbers to all neighboring balloons that have the same color as the original one (match in number).
  
  Try writing this function:
  
  `public static int spreadMarked(int[][] board)`: This function should "spread" any negative numbers  
  in the board to their upper, lower, left, and right neighbors, if those neighbors have the 
  same number as the negative number in question.  This sounds harder than it is.  To do this, 
  use a standard nested-for loop to iterate through the board.  Whenever you find a negative number, first check to see if 
  any of the four neighbors have the positive
  version of that number in them.  If they do, overwrite the neighbor with the negative
  number.  I suggest having this function return the number of cells changed; that way you can call this function repeatedly until it returns `0`
  (indicating the negative region can't get any larger). 
  
  Examples:
  
  Imagine a board like this:
  
  ```
  board = { {4, 3, 2, 1},
            {1, 4, 3, 3},
            {2, 4, 4, 4},
            {3, 0, 4, 1} };
  ```
  
  Imagine a balloon has just floated up and stops at row 2 and column 1.  
  First, we would use `hasMatchingNeighbors` to make sure that (row 2, col 1) has a matching neighbor, which it does.
  Then we have Java do the command `board[2][1] *= -1` which changes the board to this:
  
  ```
  board = { {4,  3, 2, 1},
            {1,  4, 3, 3},
            {2, -4, 4, 4},
            {3,  0, 4, 1} };
  ```
  
  Then we call `spreadMarked(board)` which changes the board to this:
  
  ```
  board = { {4,  3,  2, 1},
            {1, -4,  3, 3},
            {2, -4, -4, 4},
            {3,  0,  4, 1} };
  ```
  
  The function call above will return `2`, meaning at 2 squares changed.  
  Notice how the -4 has spread to two additional squares.  Then we call `spreadMarked(board)` again.  Now the board changes to:
  
  ```
  board = { {4,  3,  2,  1},
            {1, -4,  3,  3},
            {2, -4, -4, -4},
            {3,  0, -4,  1} };
  ```
  
  and the function call returns `2`, since we changed two more 4s into -4s.  
  Then we would `spreadMarked(board)` one more time, but the board wouldn't change, 
  because there are no more 4s to change into -4s.  Note that the 4 in the upper left corner doesn't change to -4 
  because it's not directly next to any of the -4s.  So the function returns `0` (and we can therefore stop calling it).
  
- **Replacing the negative numbers with `0`s**

	You can write a simple function that replaces all the negative numbers with zeros.  The
only reason the negatives are there in the first place was so we can draw them
in white for a split second  and then
remove them from the board by replacing them with zeros.

	I called my function `removeMarked`.  I also had it return the number of 
negative numbers replaced by zeroes, which I then used to assign the points that the user
earned.   



## Other games

- If you don't like this game, you can make a different one. The requirements are that it must involve a customizable-size board, and it must involve some concept where you examine the "neighbors" of the squares on the board.
- Some ideas are: Minesweeper, Connect 4, 2048, Candy Crush, Angry Birds, ...
- You must clear your idea with me first.

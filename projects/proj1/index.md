---
title: Project 1
parent: Projects
---

# CS 142 Project 1: Leroy and Leora

*If you have not completed the One Is Zero program, you might want to use that as a warm-up to this project, although that is optional, and does not 
need to be turned in.*

For this project, you will write a game similar to the Leroy and the Cookies lab.

### Game description

Leroy the Lynx begins with 24 cookies, labeled with the numbers 2 through 25, inclusive.  Like in the previous game, he wants to eat as many cookies 
as possible, but now he is playing against the other Rhodes mascot, Leora the Lynx.  Each time one of the lynxes eats a cookie, they get the number 
of points that the cookie is worth (so unlike the first game, where all the cookies were "equal," in this game, higher-valued cookies are worth more 
points).

The game begins with all 24 cookies available, except for 4, which are chosen randomly and removed, to make the game different each time it is 
played.  Leroy can then choose any cookie to eat (and he then earns that many points).  However, Leora then gets to eat all the cookies that have 
numbers that are factors of Leroy's cookie.  For instance, if Leroy eats cookie 10, he gets 10 points, but then Leora gets to eat cookies 2 and 5, 
and gets 7 points.  Leroy is *not* allowed to eat any cookie that would result in Leora getting zero points (in other words, Leroy can never eat a 
cookie that has no factors left on the board).  Leroy also cannot eat any cookie that has already been eaten.

Leroy then gets to pick another cookie to eat.  This continues as long as Leroy wants (notice that Leora never gets to pick cookies directly, her 
cookies are determined from what Leroy picks).

After Leroy decides to stop eating cookies (or cannot eat any more due to the rule about factors), the game ends.  At this point, Leora gets to eat 
all the remaining cookies on the board!  The winner is whoever has more points at the end.

## What you need to do

-   Create a new IntelliJ project. Inside, create a new Java class called  `LeroyAndLeora`. Write a  `main`  function inside this class that allows 
the user to play the game above, taking on the role of Leroy.  (The computer will automatically assign Leora's cookies and points.)
-   While your program is running, it should clearly show the board (which cookies are remaining), the current scores, and prompt the user for which 
cookie to eat.
-   Your program should display the cookies and points as they are eaten and and earned (see example below).
-   At the end of the game, announce the winner.

## Details
- Leroy is not allowed to eat a cookie that has already been eaten.  However, if the user does this, you may treat this as Leroy choosing to stop 
eating cookies (rather than detecting this situation and re-prompting the user to enter a cookie).
- Leroy is not allowed to eat a cookie that has no factors left on the board.  If this happens, you can treat it as Leroy choosing to not eat any 
more cookies.  
- You do not have to detect a negative number or number too big being entered, you can treat these situations also like Leroy choosing to stop 
eating cookies.

## Testing your program

-   You should test your program thoroughly to make sure it works.
-   You may assume the user will never enter anything other than an integer for the cookie to eat.

## Sample interactions

### Run 1:
```
Let's play Leroy and Leora!

Current scores: Leroy: 0 Leora: 0
2 3 4 - 6 7 8 9 10 11 - 13 - 15 16 17 18 19 - 21 22 23 24 25 
Enter cookie to eat: 24
Leroy eats cookie 24
Leora eats cookie 2
Leora eats cookie 3
Leora eats cookie 4
Leora eats cookie 6
Leora eats cookie 8
Leroy earns 24 points.
Leora earns 23 points.

Current scores: Leroy: 24 Leora: 23
- - - - - 7 - 9 10 11 - 13 - 15 16 17 18 19 - 21 22 23 - 25 
Enter cookie to eat: 18
Leroy eats cookie 18
Leora eats cookie 9
Leroy earns 18 points.
Leora earns 9 points.

Current scores: Leroy: 42 Leora: 32
- - - - - 7 - - 10 11 - 13 - 15 16 17 - 19 - 21 22 23 - 25 
Enter cookie to eat: 22
Leroy eats cookie 22
Leora eats cookie 11
Leroy earns 22 points.
Leora earns 11 points.

Current scores: Leroy: 64 Leora: 43
- - - - - 7 - - 10 - - 13 - 15 16 17 - 19 - 21 - 23 - 25 
Enter cookie to eat: 21
Leroy eats cookie 21
Leora eats cookie 7
Leroy earns 21 points.
Leora earns 7 points.

Current scores: Leroy: 85 Leora: 50
- - - - - - - - 10 - - 13 - 15 16 17 - 19 - - - 23 - 25 
Enter cookie to eat: 16
Leroy eats cookie 16
Leroy earns 16 points.
Leora earns 0 points.
Leora can't earn zero points, Leroy can't eat any more cookies!
Leora eats cookie 10
Leora eats cookie 13
Leora eats cookie 15
Leora eats cookie 17
Leora eats cookie 19
Leora eats cookie 23
Leora eats cookie 25
Leora earns 122 points.
Final scores:
Leroy: 101
Leora: 172
Leora wins!
```

### Run 2:

```
Let's play Leroy and Leora!

Current scores: Leroy: 0 Leora: 0
2 3 4 5 6 - 8 - 10 11 12 13 14 - 16 17 18 19 20 - 22 23 24 25 
Enter cookie to eat: 25
Leroy eats cookie 25
Leora eats cookie 5
Leroy earns 25 points.
Leora earns 5 points.

Current scores: Leroy: 25 Leora: 5
2 3 4 - 6 - 8 - 10 11 12 13 14 - 16 17 18 19 20 - 22 23 24 - 
Enter cookie to eat: 14
Leroy eats cookie 14
Leora eats cookie 2
Leroy earns 14 points.
Leora earns 2 points.

Current scores: Leroy: 39 Leora: 7
- 3 4 - 6 - 8 - 10 11 12 13 - - 16 17 18 19 20 - 22 23 24 - 
Enter cookie to eat: 20
Leroy eats cookie 20
Leora eats cookie 4
Leora eats cookie 10
Leroy earns 20 points.
Leora earns 14 points.

Current scores: Leroy: 59 Leora: 21
- 3 - - 6 - 8 - - 11 12 13 - - 16 17 18 19 - - 22 23 24 - 
Enter cookie to eat: 16
Leroy eats cookie 16
Leora eats cookie 8
Leroy earns 16 points.
Leora earns 8 points.

Current scores: Leroy: 75 Leora: 29
- 3 - - 6 - - - - 11 12 13 - - - 17 18 19 - - 22 23 24 - 
Enter cookie to eat: 18
Leroy eats cookie 18
Leora eats cookie 3
Leora eats cookie 6
Leroy earns 18 points.
Leora earns 9 points.

Current scores: Leroy: 93 Leora: 38
- - - - - - - - - 11 12 13 - - - 17 - 19 - - 22 23 24 - 
Enter cookie to eat: 24
Leroy eats cookie 24
Leora eats cookie 12
Leroy earns 24 points.
Leora earns 12 points.

Current scores: Leroy: 117 Leora: 50
- - - - - - - - - 11 - 13 - - - 17 - 19 - - 22 23 - - 
Enter cookie to eat: 22
Leroy eats cookie 22
Leora eats cookie 11
Leroy earns 22 points.
Leora earns 11 points.

Current scores: Leroy: 139 Leora: 61
- - - - - - - - - - - 13 - - - 17 - 19 - - - 23 - - 
Enter cookie to eat: 23
Leroy eats cookie 23
Leroy earns 23 points.
Leora earns 0 points.
Leora can't earn zero points, Leroy can't eat any more cookies!
Leora eats cookie 13
Leora eats cookie 17
Leora eats cookie 19
Leora earns 49 points.

Final scores:
Leroy: 162
Leora: 110
Leroy wins!
```

## Guidelines
- Your program does not have to have output that 100% matches mine, but all the functionality seen above should be there, in particular:
- You should display the board clearly indicating which cookies are present and which are absent, using spaces and dashes (or some other character) 
so it's unambiguous.
- Current scores should be displayed before Leroy gets to pick a cookie.
- Make it clear which cookies Leora gets after each time Leroy picks a cookie, and how many points she earns.
- Make it clear which cookies Leora eats all at once at the end of the game and how many points she earns.

## Outside code
All your code must be developed and written independently by yourself.  Any code or ideas you receive from outside sources (people, websites, or 
AI), must be acknowledged.  This can be done with a comment in the code, explaining where the code or idea came from, including the person's name, 
website URL, or name of the AI.

Remember, it's fine to get outside help, but you must acknowledge your sources.

## Hints and tips

- I suggest using the Leroy and the Cookies lab as a guide for this program.  In particular, you should use an array of booleans (`boolean[]`) to 
represent the cookies.  I suggest making an array of 26 booleans (`boolean[] eaten = new boolean[26]`) and ignoring indices 0 and 1.  In other 
words, use just `eaten[2]` through `eaten[25]` for representing which cookies are eaten.
- You shouldn't need to do much (if any) string handling in this program, but if you need to, be aware that comparing  `String`s in Java is a bit 
tricky. If you have two Strings, you shouldn’t compare them with  `==`  or  `!=`:
    
    ```
      String str = scanner.next();
      if (str == "A") {	// don't do this!
          statements...
      }
    
    ```
    
    The code above is legal, however the comparison will sometimes work correctly and sometimes not (it will be clear later).
    
    The correct way to compare  `String`s in Java for equality or inequality is:
    
    ```
      // compare for equality
      if (str.equals("A")) {
          statements...
      }
    
      // compare for inequality:
      if (!str.equals("A")) {    // the exclamation point means "not"
          statements...
      }
    
    ```

## Style, comments, and pledge

-   You should use  [good programming style](https://pkirlin.github.io/cs142-f24/coding-style)  when writing your program, except that you don’t 
need to use functions in Java because we haven’t fully learned them yet. You may use them if you so desire. All other style guidelines, including 
proper indentation and comments, should be followed.
    
-   In particular, be sure to include a block comment at the top of your program with your name and the statement "I have neither given nor received 
unauthorized aid on this program."
    
## What to turn in

Through Canvas, turn in your  `LeroyAndLeora.java`  file. Additionally, upload a text file answering the following questions:

-   What bugs and conceptual difficulties did you encounter? How did you overcome them? What did you learn?
- Describe any serious problems you encountered while writing the program (larger things than the previous question).
-   Describe whatever help (if any) that you received. Don’t include readings, lectures, and exercises, but do include any help from other sources, 
such as websites or people (including classmates and friends) and attribute them by name.
-   Did you do any of the challenges (see below)? If so, explain what you did.
-   List any other feedback you have. Feel free to provide any feedback on how much you learned from doing the assignment, and whether you enjoyed 
doing it.



## Challenge Problems

From time to time, I will offer “challenge problems” on assignments. These problems are designed to have little (but some) impact on your grade 
whether you do them or not. You should think of these problems as opportunities to work on something interesting and optional, rather than a way to 
raise your grade through “extra credit.”

**Policy on challenge problems:**

-   Challenge problems will typically allow you to get 2-5% of additional credit on an assignment, yet they will typically be much more difficult 
than this credit amount suggests.
-   You should not attempt a challenge problem until you have finished the rest of an assignment.
-   The tutors will not provide help on challenge problems. The instructor will provide minimal assistance only, as these problems are optional and 
are designed to encourage independent thought.
-   Challenge problems may be less carefully specified and less carefully calibrated for how difficult or time-consuming they are.
-   If you solve a challenge problem, include a comment at the top of your program detailing what you did.

**Challenge problems for this assignment:**

-   Let the user specify how many random cookies to remove at the beginning, rather than always 4.
-   Add code that detects ahead of time when the game should end (when there are no cookies left that have factors also on the board).
-   Add code that detects illegal inputs (like negative numbers, or picking a cookie twice) and prompts the user to re-enter the number.
-   Develop a strategy for the game and print the suggested move for your strategy at the beginning of each turn.  (Explain what your strategy is in 
a comment.)

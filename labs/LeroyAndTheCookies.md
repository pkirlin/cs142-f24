# Leroy and the Cookies

Team members (edit these):

1. Team member 1
2. Team member 2
3. Team member 3

## Before you begin...

There are a few things that you should know about this lab assignment:

1. It is a _pair programming_ activity. Pair programming is a software
   development practice where two programmers sit side-by-side at the
   same computer and work together to develop a program. One programmer
   is the _driver_--they type and edit the code, explaining what they
   are doing. The other programmer is the _navigator_, who should be
   thinking about the big picture, offering advice and suggestions, and
   __asking questions__. The members of a pair programming team should talk
   throughout the process so that they can switch roles at any moment.

2. You will be editing this file using [markdown](https://commonmark.org/help/).
   You will write your answers in this file, and this edited file will be
   what you submit for your lab submission. You can switch back and forth
   between viewing and editing this file using the "preview" button in the
   top right of this window. You don't need to worry about the syntax of
   Markdown too much, but it's nice that it's just text and IntelliJ can
   display it nicely formatted. Don't worry about getting everything to
   look perfect.

3. Each person on the team should have their own copy of this file, but
   you should choose to fill in __one__ team member's markdown file.
   When you are done with the lab, this team member should share their
   file with the rest of the team.

   You should note who you worked with at the top of this file.

## Goals

By the time that you are done with this activity, you and your partner
should be able to:

* declare and create arrays and access their elements,
* use and explain parts of `for` loops,
* and work more effectively as a team.

## Playing the Game

Run `LeroyAndTheCookies.java` to play the game. It is a one-player game,
so you and your partner should play a few times before getting together
to answer the questions. Limit yourself to ~5 minutes.

Note that to answer these questions, you may need to go back and play
the game again to answer some of the questions to come. But you should
do so __deliberately__, in order to find something out, not just because
you got bored with the conversation or thought you could answer a question
better on your own.

1. When does Leroy win the game?

   __Answer__:

2. What constitutes an illegal move (when Leroy forfeits)?

   __Answer__:

3. Assuming you make no illegal moves but don't win, when does the game end?

   __Answer__:

4. If you get a score, how is your score determined?

   __Answer__:

5. On the class webpage, under "Resources," click the link for "Java
   Documentation." On the top right of the page, there is a search box.
   Search for Math, and click on "java.lang.Math".  This will let you
   browse all the built-in math functions.  Scroll down to the `random()`
   function and read about it.

   Explain the line of code that says `int roll = (int)(1 + Math.random() * 6);`,
   in particular, mention why there is a 1 added to the random number, and why
   the `(int)` part is there.

   __Answer__:

6. Under what circumstances do you roll a different number of dice?

   __Answer__:

7. Did all team members reach consensus on your answers to the previous questions?

   __Answer__:

## Stop here and check in with me

If your team couldn't reach consensus, let me know. If you all agree on the
answers, keep going below!

8. What is a good strategy for this game? For example, is it better to eat
   the cookies with smaller or larger numbers first? Why?

   __Answer__:

## Arrays

Now examine the `main` function in the Java file.

9. In Java, we must declare variables before they are used. Variables are
   declared by stating the data type of the variable, followed by the name
   of the variable (and a semicolon). Optionally, the variable may be
   initialized at the same time.

   Here are some examples:

   ```
   int score = 45;  // declares an integer called score and sets it to 45
   double number;   // declares a double called number with no initial value
   ```

   What is the type of the variable declared on the line below?

   `boolean[] eaten = new boolean[10];`

   __Answer__:

10. On the line below, what expression gives the initial value of `eaten`?

    `boolean[] eaten = new boolean[10];`

    __Answer__:

For the next several questions, you will have to temporarily add some code
to the program. You might want to leave a couple of blank lines around
your additions so that you can easily return the program to its original
state after you're done.

11. After the line

    `boolean[] eaten = new boolean[10];`

    add:

    `System.out.println(eaten[3]);`

    What does this cause the program to print? (When you run the program, the
    new output will appear after the instructions but before the first run.)

    __Answer__:

12. What happens if you print `eaten[30]` instead?

    __Answer__:

13. What are the limits on the number you can put between the square brackets
    in an array indexing expression like those in the two previous questions?

    __Answer__:

14. What is printed if you add the following two lines?
   ```
   eaten[2] = true;
   System.out.println(eaten[2]);
   ```

__Answer__:

15. Is `int[]` a valid type?

    __Answer__:

## Stop here and check in with me

If I'm helping another group, you can experiment with the following question:

16. What syntax would you use to create an array __of arrays__ of booleans
    (_e.g._, a 2-dimensional array)? There are several reasonable guesses.
    Experiment to find one that works.

    __Answer__:

## For loops

Return your program to its original state by deleting any new code that
you added. The undo function in your editor may be useful.

17. A for loop begins with the keyword `for`. How many for loops appear
    in this program?

    __Answer__:

Here is a template for a while loop:

```
while (boolean expression) {
  statement
  ...
}
```

An _expression_ is a piece of code that has a value, such as `"hello"`
or `x + 5`. More specifically, a _boolean expression_ is an expression
with a value of type boolean, such as `true` or `score == 0`. A _statement_
is a "complete sentence" of code that does something, like

`System.out.println("Comp Sci is awesome!");`

or:

`int score = 45;`

18. Write a template for a for loop, similar to the while loop template that
    is directly above. Note that there are three parts within the parentheses,
    separated by semicolons. Two of these parts are statements; the other is a
    boolean expression. Note: you do not have to know exactly how each part of
    the template works yet. You will figure that out over the next few questions.

    __Answer__:

19. How many times does the for loop below run?

   ```
   for (int i = 1; i <= 9; i++) {
     System.out.println(i);
   }
   ```

You can determine this by putting the code into your Java file. I suggest
adding it towards the top of the main function, that way you can easily
see the output before the cookie game starts.

    __Answer__:

20. What is the value of the local variable `i` on the first pass through
    the loop?

    __Answer__:

21. What is the value of the local variable `i` on the last pass through
    the loop?

    __Answer__:

22. Consider the first part of the `for` loop within the parentheses
    (here, this is the `int i = 1;` part):

    Does it run only once, or every pass through the loop?

    Does it run before or after the statements between the curly braces?

    __Answer__:

23. Consider the second part of the `for` loop within the parentheses
    (here, this is the `i <= 9;` part):

    Does it run only once, or every pass through the loop?

    Does it run before or after the statements between the curly braces?

    __Answer__:

24. Consider the third part of the `for` loop within the parentheses
    (here, `i++;`):

    Does it run only once, or every pass through the loop?

    Does it run before or after the statements between the curly braces?

    __Answer__:

25. How would you modify the loop so that it prints the numbers counting
    down from 9 to 1, instead of up from 1 to 9?

    __Answer__:

26. Did your team have any conflicts while working on this activity? If
    so, how did you resolve them?

    __Answer__:

## Stop here and check in with me

If I'm with another group, discuss the following:

27. Could a for loop be placed inside another for loop? If so, how? If not, why not?

    __Answer__:


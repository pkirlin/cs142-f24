---
title: Project 5
parent: Projects
---

<script src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML" type="text/javascript"></script>


# CS 142 Project 5: Ladybug Dance Party
{: .no_toc }

1. TOC
{:toc}

In this assignment, you will be creating a graphical animation of a ladybug dance party. There can be lots of different kinds of dancers at the party--but since they all share some characteristics (e.g., they are all dancing bugs), you can use *inheritance* to easily create the program and avoid code duplication.

A sample dance party is below:

<video controls>
  <source src="demo1.mp4" type="video/mp4">
Your browser does not support the video tag.
</video>

## Starter code

**Make sure you create the project in a place on your computer where you can find it! I suggest making a new subfolder in your CS 142 projects folder.**

You can download the starter code for this assignment by creating a new IntelliJ project from version control (VCS) and using the following URL:

```
https://github.com/pkirlin/cs142-f24-proj5
```

## Concepts in this program

You will mainly be practicing with inheritance, polymorphism, and abstract classes in this project.

### Enumerated types

In Java, an enumerated type (called an `enum`) is a special kind of data type (like a class), but only allows for variables of that type to be set to a pre-defined range of constants.  So it's good for situations where you want a variable to be set to one of only a small number of possible choices.  

We use two enums in this project: Directions and DanceSteps.  Open up those files and take a look at them.  You don't need to really understand the syntax, but just know that whenever you define a Direction variable, it must be set to one of `Direction.NORTH`, `Direction.SOUTH`, `Direction.EAST`, and `Direction.WEST`.  There are no other possible directions, which makes sense.  The direction `enum` also has some instance variables and methods, but you will most likely not have to know about them or call them.

Similarly for the DanceStep class, a DanceStep variable may only be set to one of the seven possible dance steps listed in that class.

### The `DancingBug` class

(You should follow along in the DancingBug class as you read this explanation.)

The DancingBug class is the base class from which all dancing bugs must derive.  Note that a DancingBug has a location (row & column), a direction the bug is facing, and a color.  (Note that colors in this project are just strings, and should be one of the colors you are given the image files for: red, blue, green, gray, pink, orange, brown, or purple).

Pay special attention to the `step()` method in DancingBug.  It is marked as `abstract`, which we know means that all classes that inherit from DancingBug must write this method.  The `step()` method must perform *one* step of a dance, probably by calling the `doStep()` method with the name of the dance step desired.  In particular, you should probably *not* have a bug take multiple dance steps inside the `step()` method, only because those steps will be collapsed into a single step in the graphical output.

### The `DanceFloor` class

(You should follow along in the DanceFloor class as you read this explanation.)

The DanceFloor class represents the grid where the bugs dance, though you don't have to pay *too* much attention to this class.  All you really need to know is you can call `addDancer()` to add a bug to the dance floor, `removeDancer()` to remove one, and `everyoneDance()` starts the dance party.  Note that the `stepAll()` function simply calls `step()` on each bug to have them take whatever dance step is next.  You should not need to modify these functions except if you need/want to add print statements for debugging.

### The `LeaderBug` interface

Normally, Java subclasses may only inherit from a *single* superclass.  Some programming languages allow multiple inheritance (where one subclass may have multiple superclasses, but Java is not one of them).  However, Java supports another concept, called *interfaces*, which are useful when a class needs to have a specific parent class, but also needs to define some methods that relate to a second concept.

In Java, interfaces are like abstract classes, except *every* method is abstract.  No method bodies are allowed in interfaces.  No instance variables are allowed either.  An interface is *solely* a contract saying that "whatever class implements this interface *must* define these methods."  In this project, there is an interface called LeaderBug, that defines an (abstract) method called `getLastStep()`.  This interface will be used when we want to define a type of bug that can "lead" a series of dance steps and have other bugs follow along. A bug that wants to follow the dance steps of another bug can look for Bug objects that have a `getLastStep()` function and call that function to copy what the other bug (the leader did).  But because every bug must derive from `DancingBug`, and `DancingBug` doesn't have a `getLastStep()` function, we have to tell Java that leader bugs have something else in common, and this is done through implementing an interface.  (This will make more sense when you get to that problem.)

## A sample dancing bug

Open BoredBug.java.  Notice the BoredBug constructor, which allows someone to specify the row & column where a BoredBug should be.  The constructor calls the superclass constructor, passing in row & col, and also that the bug should be gray.

Look at the `step()` method.  A BoredBug simply does nothing; it stands around on the dance floor looking bored.  So the `step()` method just calls `doStep(DanceStep.PAUSE)`, which you can probably guess doesn't do anything.  In fact, we could have left this method empty, but this illustrates how to use the `doStep()` function.

Now open DanceTester.java.  Notice how the `testBoredBug` method makes a small dance floor, adds a BoredBug to it, and then starts the dance party.  Run the code, and you should see the dance floor appear.  Click it, and the bug will start "dancing" (you should see it printing info about itself, though obviously the bug doesn't move because its `step()` method doesn't do anything).

## Implementing the project

First, **read through all of the assignment carefully**. As you do so, think about what kinds of classes and objects you may need to create, and how your objects will interact.

For this program you will be making **many** new classes: specifically ones representing specific kinds of dancers. Many of these classes will `extend` either `DancingBug` or each other. I will describe each class you need below.  You can write these in any order,
though they generally go from easiest to hardest.  There are a few bugs which depend on 
other types as well.

### DanceTester

This class is already created for you, and is where you will write your testing code for each dancing bug, and the final `main` method.

### SpinBug

This bug is the most basic dancing bug.  It should just turn right for each dance step.

   You should make a new `SpinBug` class that inherits from `DancingBug` and write a constructor for it.  I suggest having the constructor take `int row, int col` to set the location of the bug.  You can pick whatever color you want (the example `SpinBug` uses yellow).  Override the `step()` method and have the bug turn right on each step.

   **Stop and test:** In DanceTester, write a testSpinBug() function that works similarly to testBoredBug.  You should see your SpinBug dancing!
   
   <video controls>
   <source src="spinbug.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

### RoutineBug

This bug is more complicated. This bug follows a specified dance routine, performing one step of the routine at each step.  This dance routine will be specified as an ArrayList of `DanceStep`s. This bug class should have a method which allows a user to set this list of steps.  Then in its `step()` method, you should get the *next* step in the list and perform it with `doStep()`. When you get to the end of the list, go back to the beginning!

   Begin by making a new `RoutineBug` class that inherits from `DancingBug`.  You may choose the instance variables you want to use.  You can pick whatever color you want (the example RoutineBug uses red).  You will need to write the `step()` method, write a constructor, and a "setter" method to set the dance routine steps.
   
   **Stop and test:** In DanceTester, write a testRoutineBug() function.  Here is an example dance routine you may use:

   ```java
   ArrayList<DanceStep> steps = new ArrayList<DanceStep>();
   steps.add(DanceStep.STEP_RIGHT);
   steps.add(DanceStep.STEP_RIGHT);
   steps.add(DanceStep.STEP_RIGHT);
   steps.add(DanceStep.PAUSE);
   steps.add(DanceStep.STEP_LEFT);
   steps.add(DanceStep.STEP_LEFT);
   steps.add(DanceStep.STEP_LEFT);
   steps.add(DanceStep.PAUSE);
   steps.add(DanceStep.BACKWARD);
   steps.add(DanceStep.BACKWARD);
   steps.add(DanceStep.BACKWARD);
   steps.add(DanceStep.PAUSE);
   steps.add(DanceStep.FORWARD);
   steps.add(DanceStep.FORWARD);
   steps.add(DanceStep.FORWARD);
   steps.add(DanceStep.TURN_LEFT);
   ```
   
   <video controls>
   <source src="routinebug.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

### SquareBug

A SquareBug is a specialization of a RoutineBug: a SquareBug always does a specific routine (walks in a square).  

   Begin by making a new `SquareBug` class that inherits from `RoutineBug` (not `DancingBug`!).  
   You may choose the instance variables you want to use.  A SquareBug constructor will take a `int row, int col, int size` to set its location, 
   as well as the size of the square the bug will trace.  Note that the constructor should use the `size` parameter to construct the appropriate 
   dance routine automatically (a routine that will have the bug walk in a square of the size given).  <i>(Note: the `size` parameter should be interpreted
   as the number of steps the `SquareBug` takes when it walks forward.  Therefore, the actual length of the side of the square the `SquareBug` traces will
   be one greater than the `size` parameter.)

   If you have written this constructor appropriately, you will not need to override the `step()` method or any other methods.

   **Stop and test:** In DanceTester, write a testSquareBug() function that creates a SquareBug of size 4.
   
   <video controls>
   <source src="squarebug.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

### LeaderBug

You'll need to enable some bugs to *lead* other bugs (to give the other bugs directions about what dance steps to do). But you'll want multiple different kinds of bugs to be able to lead. As such, we have provided you with a `LeaderBug` **interface** that other classes can **implement**.

   Remember that interfaces only have abstract methods --- all the method bodies are left unspecified.  And just like abstract classes, any class that *implements* an interface must define those methods.  You're specifying that all `LeaderBug`s are able to perform certain actions (methods)---the interface describes what those functions are.

   The interface for `LeaderBug` only has one method:

   ```java
   public abstract DanceStep getLastStep();
   ```

   This means that in order to be a `LeaderBug`, a class which implements this interface *must* define this `getLastStep()` method.  The way the interface works is that any bug which wants to follow the directions of a `LeaderBug` must be able to ask the `LeaderBug`, "What was the last dance step you took?"  The follower bug may query this by calling `getLastStep()` on the `LeaderBug`, and a `DanceStep` will be returned.  Then the follower bug may choose to copy this dance step exactly, or take some other action.  The next few bugs you will write will be follower bugs that are given access to a `LeaderBug` to follow.

   Note: This interface is already written for you, but you should open `LeaderBug.java` to look at it.  Notice it looks a lot like a very basic abstract class, except it uses the word `interface` rather than `class.`  There is nothing else to do for this step of the project.

### ImitationBug

The first follower bug you will write will be a bug that simply copies the exact dance steps of a LeaderBug.  The graphical effect will be that the ImitationBug imitates whatever the LeaderBug is doing, so they will dance in sync. 

   First, we will need to have a class that actually implements a LeaderBug so the ImitationBug can follow it.  We will turn SpinBug into a LeaderBug.  To do this, change the line in SpinBug from:

   ```java
   public class SpinBug extends DancingBug 
   ```

   to 

   ```java
   public class SpinBug extends DancingBug implements LeaderBug
   ```

   This tells Java that the SpinBug class will still have all the functionality of a DancingBug, but now will also have the functionality of a LeaderBug as well.  So now you must add the `public DanceStep getLastStep()` method to this class.  Remember, getLastStep() should simply return the last dance step this bug took.  And since a SpinBug always turns right on every step, writing getLastStep() is literally one line of code.

   Second, create an ImitationBug class.  The class definition line should be:

   ```java
   public class ImitationBug extends DancingBug
   ```

   Add a `private LeaderBug leaderBug` instance variable to the class.  This variable will hold the specific bug the ImitationBug is following.  

   Add a constructor to the class that takes a row, a column, and a LeaderBug:

   ```
   public ImitationBug(int row, int col, LeaderBug leaderBug)
   ```

   The body of the constructor should set the appropriate instance variables from the parameters above.  You may pick a color for the ImitationBug (in the demos, this is pink.)

   Now, write the `step()` method for the ImitationBug.  Since an ImitationBug simply copies whatever its `LeaderBug` does, this should be straightforward: ask the LeaderBug instance variable for whatever its last dance step was, and have the ImitationBug do that same step.

   **Stop and test:** In DanceTester, write a `testImitationBug()` function.  Create a dance floor with a SpinBug and an ImitationBug that imitates the SpinBug.  The effect should be two spinning bugs.
   
   <video controls>
   <source src="imitationbug1.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

### A second ImitationBug

Change your RoutineBug class to also implement LeaderBug.  This will be slightly more challenging that making the SpinBug into a LeaderBug, because the RoutineBug will be following an arbitrary dance routine.  However, this should not be too much work.

   **Stop and test:** In DanceTester, write a `testImitationBug2()` function.  Create a dance floor with a RoutineBug that performs the dance routine from earlier, and an ImitationBug that imitates the RoutineBug.  The effect should be similar to the video below (though I added many more ImitationBugs).
   
   <video controls>
   <source src="elecslide.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>
   
   (Supposed to be a recreation of this:)
   
   <iframe width="560" height="315" src="https://www.youtube.com/embed/5jBkoEM0SSE" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

### MirrorBug

The second follower bug you will write will be a bug that copies the exact dance steps of a LeaderBug, but does with right/left directions flipped.  The graphical effect will be that the ImitationBug imitates whatever the LeaderBug is doing, but as if it is being done in a mirror.

   Create a `MirrorBug` class that operates just like `ImitationBug`, but imitates the dance with left and right swapped.  In the demos, `MirrorBug`s are blue.

   **Stop and test:** In DanceTester, write a `testMirrorBug()` function.  Create a dance floor with a `SquareBug` of size 4, and a MirrorBug that mirrors the SquareBug.  The effect should be two `SquareBug`s tracing out identically-sized squares, but the paths will be mirrored.
   
   <video controls>
   <source src="mirrorbug.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

### CongaBug

The final part of the project will be writing a bug class that will enable the bugs to dance a "conga line."  This dance is lead by a `SquareBug` that will simply walk in a square.  Each bug behind the `SquareBug` is a `CongaBug`, whose job is to follow the bug in front of it (whether a `SquareBug` or another `CongaBug`).  However, this particular
style of following is not the same kind of following that the `ImitationBug` or the `MirrorBug` do; these earlier bugs always copy their `LeaderBug` in real time.
A `CongaBug`, however, must observe what its `LeaderBug` does, but then *delay* that dance step by one unit of time (one call to `step()`).  This will enable the bugs to turn corners one at a time, rather than simultaneously.  

Begin by adding a `testCongaLine()` function to DanceTester that simply creates a SquareBug that walks in a square of size 4.

Note that a `SquareBug` is a (extends) `RoutineBug`, so a `SquareBug` is also a `LeaderBug`.  Therefore, we can write a CongaBug class that is similar to ImitationBug and MirrorBug, but executes its LeaderBug's steps with a one-step delay.  Create this CongaBug class: similarly to the other "follower" bugs, this bug should have a constructor that takes a row and a column, along with a LeaderBug variable.  The demos show `CongaBug`s 
   in green.
   
To write the `step()` function, you must think about how to delay the LeaderBug's steps by one.  Hint: try using an instance variable to save the most recent step of the LeaderBug.  Then the `step()` function can execute the dance step in this variable, then call `getLastStep()` on the LeaderBug and update the result in the variable to be used on the subsequent call to `step()`. However, the very *first* call to `step()` will be problematic, because there is no delayed value for the LeaderBug's last step yet.  Instead, just have the CongaBug walk forward on the first call to `step()`.

**Stop and test:** Modify `testCongaLine()` to add a CongaBug immediately behind the SquareBug, and have the CongaBug follow the SquareBug.  At this point, both bugs should walk in a square, but the CongaBug should always be one step behind.  <I>(Note: because the bugs can't turn and walk forward at the same time, whenever the SquareBug approaches a corner and turns, the CongaBug behind it will walk on top of the SquareBug for one 
   time step.  This is ok.)</i>
   
   <video controls>
   <source src="congabug1.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

We now want to add a second CongaBug to the conga line.  So the conga line will consist of:

   - SquareBug (front of the line)
   - CongaBug (following the SquareBug)
   - a second CongaBug (following the first CongaBug)

To accomplish this, we must make CongaBug itself into a LeaderBug.  This is not particularly difficult, but is slightly tricky because the CongaBug is now *both* a leader and a follower.  So the definition line for `CongaBug` will now be:

```
public class CongaBug extends DancingBug implements LeaderBug
```

and you must write the `getLastStep()` method appropriately.

**Stop and test:** Modify `testCongaLine()` to add a second CongaBug as explained above.   At this point, you should have a three-bug conga line.
   
      
   <video controls>
   <source src="congabug2.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

### `main()` function

Write a main method to let the user pick which test they want to see:

   - SpinBug
   - RoutineBug (using the routine from above)
   - SquareBug
   - ImitationBug (imitating a RoutineBug) [<i>earlier this said "imitating a SquareBug," which is also fine, but that's not a test case I asked you to write</i>]
   - MirrorBug (mirroring a SquareBug)
   - CongaBug (3-bug conga line)

## What to turn in

Through Canvas, turn in all your `.java` files. Additionally, upload a text file answering the following questions:

1. What bugs and conceptual difficulties did you encounter? How did you overcome them? What did you learn?
2. Describe whatever help (if any) that you received. Don’t include readings, lectures, and exercises, but do include any help from other sources, such as websites or people (including classmates and friends) and attribute them by name.
3. Describe any serious problems you encountered while writing the program.
4. Did you do any of the challenges (see below)? If so, explain what you did.
5. List any other feedback you have. Feel free to provide any feedback on how much you learned from doing the assignment, and whether you enjoyed doing it.

## Challenges

- Add more types of bugs.

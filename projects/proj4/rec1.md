---
title: First recursive formulation
nav_exclude: true
---

# First recursive formulation

- Is our current location the ending location, AND are all row/col counts zero? If so, return success (true).
- Else if our current location is *not* the ending location: check the following:
  - Check if we can move one step north. If so, recursively solve the maze from one step north. If this recursive 
call succeeds, then we succeed.
  - Check if we can move one step south. If so, recursively solve the maze from one step south. If this 
recursive call succeeds, then we succeed.
  - Repeat for east and west.  (So always north first, then south, then east, then west.)
- If none of the (up to four) recursive calls succeeded, then fail.  Note that we also fail if we are at the ending location
  but the row/col counts are not all zero.

Let's run through this idea with maze0.txt, drawn out as a diagram here:


```
S . . 3
. . . 1
F . . 3
2 2 3

```

S=start, F=finish, but those letters do not actually appear in the maze.  Only `'` and `*` do.

Remember, the numbers at the end of each row/col indicate how many squares in each row/col must be filled
by the solution path to the maze.

We begin at (0, 0)  *(remember this is row=0, col=0)*, so we begin by calling `canSolve(0, 0)`.  We decrease the row/col 
counts for (0, 0), so our maze looks like this:

```
* . . 2
. . . 1
F . . 3
1 2 3

```

The ending location is not (0, 0), so we will examine north, south, east, west in that order, and consider if it is a legal
move to go there.

North is illegal (off the board).

South is possible, so we will try 
solving one from step south: make the recursive call canSolve(1, 0).

So far our recursion diagram looks like this:


```
canSolve(0, 0)
|
+--> canSolve(1, 0)
```

And our maze looks like this (after updating row/col counts):

```
* . . 2
* . . 0
F . . 3
0 2 3

```

The ending location is not (1, 0), so again we consider north/south/east/west in that order.

We can't go north because (0, 0) is already part of the path.

We can't go south because (2, 0) the column 0 counter is already at zero, so we can't use any more squares in column 0 as part
of our answer.  

We can't go east because the row 1 counter is also at zero.

We can't go west because that would leave the boundary of the maze.  So clearly we are at a dead-end and need to BACKTRACK.

So this current call to canSolve(1, 0) is going to return failure (false).

```
canSolve(3, 1)
|
+--> canSolve(2, 1)
     |
     +--> canSolve(1, 1)
          |
          +--> failure (because we can't move anywhere)
```

So when `canSolve(1, 1)` returns failure, we will automatically backtrack to (2, 1):

```
canSolve(3, 1)
|
+--> canSolve(2, 1)
     |
     +--> canSolve(1, 1)
     |    |
     |    +--> failure 
     |
     +--> what now?
```

What is the second recursive case in (2, 1)? We need to try south. But south from (2, 1) is where we arrived at (2, 1) from, so that makes no sense. Let's try east:

```
canSolve(3, 1)
|
+--> canSolve(2, 1)
     |
     +--> canSolve(1, 1)
     |    |
     |    +--> return failure 
     |
     +--> canSolve(2, 2)
```

We've found the cup, so return success!

```
canSolve(3, 1)
|
+--> canSolve(2, 1)
     |
     +--> canSolve(1, 1)
     |    |
     |    +--> return failure 
     |
     +--> canSolve(2, 2)
          |
          +--> return success (because we found the cup here)
```

`canSolve(2, 2)` returns success back to canSolve(2, 1):

```
canSolve(3, 1)
|
+--> canSolve(2, 1)
     |
     +--> canSolve(1, 1)
     |    |
     |    +--> return failure 
     |
     +--> canSolve(2, 2)
     |    |
     |    +--> return success 
     |
     +--> return success (because the previous recursive call was successful)
```

`canSolve(2, 1)` returns success back to `canSolve(3, 1)`:

```
canSolve(3, 1)
|
+--> canSolve(2, 1)
|    |
|    +--> canSolve(1, 1)
|    |    |
|    |    +--> return failure 
|    |
|    +--> canSolve(2, 2)
|    |    |
|    |    +--> return success 
|    |
|    +--> return success 
|
+--> return success (because the previous recursive call was successful)
```

And therefore the original recursive call to `canSolve(3, 1)` returns success, indicating the cup was found successfully.

If this makes sense, go back to the previous page and continue.

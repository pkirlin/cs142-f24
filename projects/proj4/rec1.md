---
title: First recursive formulation
nav_exclude: true
---

# First recursive formulation

- Is the patronus at the same location as the cup? If so, return success.
- Check if the patronus can move one step north. If so, recursively solve the maze from one step north. If this recursive call succeeds, then we succeed.
- Check if the patronus can move one step south. If so, recursively solve the maze from one step south. If this recursive call succeeds, then we succeed.
- Repeat for east and west.  (So always north first, then south, then east, then west.)
- If none of the (up to four) recursive calls succeeded, then fail.

Let's run through this idea with maze0.txt, repeated here, with labels on the rows and columns:

```
 0123
0####
1# ##
2# C#
3#H##
4####
```

The patronus begins at (3, 1) *(remember this is row=3, col=1)*, so we begin by calling `canSolve(3, 1)`. The cup is not at (3, 1), so we try solving one from step north (make a recursive call at (2, 1). The cup is not at (2, 1), so again we recursively solve from one step north, so we make a recursive call at (1, 1). Again, the cup isn't at (1, 1), so we try going north, but that isn't possible (because the hedge blocks us). We try south, but that's where we just came from, so that isn't a good idea (your code will drop a breadcrumb to prevent this). We try east and west, but those are both blocked. So it turns out (1, 1) is a dead end. So far this is a diagram of our recursive calls:

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

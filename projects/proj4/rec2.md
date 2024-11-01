---
title: First recursive formulation
nav_exclude: true
---

# Second recursive formulation

- Is the patronus at the same location as the cup? If so, return "C".
- Check if the patronus can move one step north. If so, recursively solve the maze from one step north. If this recursive call succeeds (the string returned is not "X"), then we return an updated string with our path.
- Check if the patronus can move one step south. If so, recursively solve the maze from one step north. If this recursive call succeeds (the string returned is not "X"), then we return an updated string with our path.
- Repeat for east and west.  (So always north first, then south, then east, then west.)
- If none of the (up to four) recursive calls succeeded, then return "X".

Let's run through this idea with maze0.txt, repeated here, with labels on the rows and columns:

```
 0123
0####
1# ##
2# C#
3#H##
4####
```

The patronus begins at (3, 1) just like in the first formulation, so we begin by calling `directionalSolve(3, 1)`, followed by `directionalSolve(2, 1)` and `directionalSolve(1, 1)`. We again reach a dead-end at (1, 1), but instead of returning failure, we return the string "X" (indicating failure):

```
directionalSolve(3, 1)
|
+--> directionalSolve(2, 1)
     |
     +--> directionalSolve(1, 1)
          |
          +--> return "X" (because we can't move anywhere)
```

So when `directionalSolve(1, 1)` returns "X", we will automatically backtrack to (2, 1), where we try south, but we're blocked, so we try east:

```
directionalSolve(3, 1)
|
+--> directionalSolve(2, 1)
     |
     +--> directionalSolve(1, 1)
     |    |
     |    +--> return "X" 
     |
     +--> directionalSolve(2, 2)
```

We've found the cup, so return "C", indicating we found the cup.

```
directionalSolve(3, 1)
|
+--> directionalSolve(2, 1)
     |
     +--> directionalSolve(1, 1)
     |    |
     |    +--> return "X" 
     |
     +--> directionalSolve(2, 2)
          |
          +--> return "C" (because we found the cup here)
```

`directionalSolve(2, 2)` returns "C" back to `directionalSolve(2, 1)`. What should we do with this "C"? It's not "X", so it indicates a successful search, but what should `directionalSolve(2, 1)` return? We can't just return "C" again, because the cup isn't at (2, 1). Instead, we'll return a string indicating the direction that we walked to find the cup, which was east (so we'll return "E").

```
directionalSolve(3, 1)
|
+--> directionalSolve(2, 1)
     |
     +--> directionalSolve(1, 1)
     |    |
     |    +--> return "X" 
     |
     +--> directionalSolve(2, 2)
     |    |
     |    +--> return "C" 
     |
     +--> return "E" (because we walked east to find the cup from here)
```

`directionalSolve(2, 1)` returns "E" back to directionalSolve(3, 1), and again, what should directionalSolve(3, 1) return? We walked north from (3, 1) to (2, 1), so let's attach an "N" to the front of our answer:

```
directionalSolve(3, 1)
|
+--> directionalSolve(2, 1)
|    |
|    +--> directionalSolve(1, 1)
|    |    |
|    |    +--> return "X" 
|    |
|    +--> directionalSolve(2, 2)
|    |    |
|    |    +--> return "C" 
|    |
|    +--> return "E" 
|
+--> return "NE" (because we attached an "N" to the previous call)
```

And therefore the original recursive call to solve(3, 1) returns "NE", indicating the path to the cup from the original starting location is North, then East.

If this makes sense, go back to the previous page and continue.

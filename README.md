# java-brick-breaker

## Index ##

* [About](#about)
* [Run](#run)
* [Issues](#issues)
* [Contributing](#contributing)
* [Future Plans](#future-plans)

## About ## 

This video game (inspired by the arcade hit [Breakout!](https://en.wikipedia.org/wiki/Breakout_(video_game)) is written 
in Java and utilizes [JOGL](https://en.wikipedia.org/wiki/Java_OpenGL) in order to implements various design decisions 
such as shadows, scene graphs, collision detection, and particle physics. 

### Instructions ###
The game gives the user control of a paddle which is used to bounce a ball towards a collection of bricks with the goal 
of destroying all of them to beat the level. 

#### Brick Types ####
* Regular Bricks
* Bonus Bricks 
** Drop power-ups
** Rubber Bricks 
** Increase the speed (and strength) of the ball
* Metal bricks 
** Can only be broken with repeated hits or fast moving balls

#### Power-ups ####
Power-ups can both help and hinder the user. Good luck!

* Shrink/Grow paddle
** Harder/Easier to hit
* Speed up/slow down ball
** Increase/Decreasre strength
* Brighter/Dimmer lighting
** Make it easier or harder to see
* Grow/Shrink ball 
** Increases/Descreases strength
* Lower/Raise Bricks
** Faster/Slower ball return time
* Split ball into multiple
** Just remember to keep at least one alive!

* Visit [my website](https://jrquick.com) for me cool stuff!

## Run

* Clone the repository

    ```git clone git@github.com:jrquick17/java-brick-breaker.git```
    
* Compile project

    ```javac src/*.java -d target/```
    
* Run project

    ```java -cp target/ Main```


## Contributing ##

To contribute, submit any pull request and I will have look.  

## Issues ##

If you find any issues feel free to open a request in [the Issues tab](https://github.com/jrquick17/java-brick-breaker/issues). If I have the time I will try to solve any issues but cannot make any guarantees. Feel free to contribute yourself.

### Thanks ###

* [jrquick17](https://github.com/jrquick17)

## Future Plans

* Run using Java 8
* Add pre requisite dependencies 

# Mystic City - A Text-Based RPG Game

## Description

Mystic City is a text-based RPG Game that emulates a dungeon crawler and allows the player to pick up items, drop items, check inventory, look around, and move throughout the dungeon. 


## Getting Started

1. Firstly, clone the repository.
2. If you are in an IDE, navigate to the GameTester.java file and click run. Then, type in the name of one of the possible maps (either 'MystiCity 30.gdf' or 'MystiCity 31.gdf')
3. If you are in a command line, type `make` to make all the associated files and then run `java GameTester.java` to run the game.

### Possible Commands

1. `q`, `quit`, or `exit` will exit the game if typed at anytime.
2. `go _`, will make the player move in one of the following directions: N, S, E, W (North, South, East, West)
3. `look` looks around for any available items
4. `get _` gets the item in the room by name
5. `use _` uses the item specified in the second argument
6.  `inve` or `inventory` returns the players inventory

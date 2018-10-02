
GameTester.class: Artifact.class Direction.class Game.class GameTester.java Place.class LineParser.class
	javac GameTester.java

Game.class: Artifact.class Direction.class Place.class LineParser.class Game.java
	javac Game.java

Artifact.class: Artifact.java Direction.class Place.class LineParser.class
	javac Artifact.java

Direction.class: Direction.java Place.class
	javac Direction.java

Place.class: Direction.java Place.java Artifact.java
	javac Place.java

LineParser.class: LineParser.java
	javac LineParser.java

clean: 
	rm *.class
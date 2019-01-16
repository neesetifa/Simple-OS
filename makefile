all: compile run
JC=javac
SRCS=*.java
compile:
	$(JC) ./src/$(SRCS)
run: ./src/gui.class
	java -classpath ./src gui

clean:
	rm ./src/*.class

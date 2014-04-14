BASE=/home/patrick/hacking/compilers/antlr/brainfudj
# BASE=`pwd`
OUT=$(BASE)/out
INPUT=$(BASE)/grammar/Brainfuck.gr4
JAVA=$(BASE)/java
ANTLR=/usr/local/lib/antlr-4.2.2-complete.jar

all: clean visitor compile

visitor:
	$(ANTLR)  -o $(OUT) -no-listener -visitor  $(INPUT)

compile:
	cp $(JAVA)/* $(OUT)/
	javac $(OUT)/*.java

clean:
	rm $(OUT)/*

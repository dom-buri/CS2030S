#!/bin/bash

mkdir cs2030s
cd cs2030s
mkdir fp
cd ..

mv Action.java cs2030s/fp/Action.java
mv Actionable.java cs2030s/fp/Actionable.java
mv Actually.java cs2030s/fp/Actually.java
mv Constant.java cs2030s/fp/Constant.java
mv Immutator.java cs2030s/fp/Immutator.java
mv Immutatorable.java cs2030s/fp/Immutatorable.java
mv Transformer.java cs2030s/fp/Transformer.java

javac cs2030s/fp/*.java
javac *.java
@echo off
color %b%%t%
title Traxxas Compiler
"C:/Program files/java/jdk1.8.0_171/bin/javac.exe" -cp . *.java javazoom\*.java
pause
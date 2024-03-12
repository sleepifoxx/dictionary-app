@echo off
chcp 65001
cls
color F1
title Dictionary Commandline
java -jar --enable-preview "%~dp0\DictionaryBasic.jar"
PAUSE
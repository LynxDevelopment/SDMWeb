@echo off

if ""%1"" == """" goto end
set CLASSPATH=%1;%CLASSPATH%
:end
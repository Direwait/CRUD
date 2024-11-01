@echo off
javac Main.java
if %errorlevel% neq 0 (
    echo Ошибка компиляции!
    pause
    exit /b
)
java Main
pause

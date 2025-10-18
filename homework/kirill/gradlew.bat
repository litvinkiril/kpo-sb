@echo off
where gradle >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
  echo Gradle is required to run this project. Install Gradle or use the Gradle Wrapper.
  exit /b 1
)
gradle %*

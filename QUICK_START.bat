@echo off
REM Quick setup script for Reaktor environment

echo.
echo ============================================
echo  REAKTOR - Quick Start Commands
echo ============================================
echo.

echo [1] Starting Frontend (somosjandula)
echo Command: cd somosjandula-main && npm run dev
echo Access: http://localhost:5173
echo.

echo [2] Building Maven Projects
echo First time setup (do once):
echo   cd Reaktor_Dependencies-main && mvn clean install
echo   cd Reaktor_Base-main && mvn clean install
echo   cd Reaktor_BaseServer-main && mvn clean install
echo   cd Reaktor_BaseClient-main && mvn clean install
echo   cd Reaktor_FirebaseServer-main && mvn clean install
echo.

echo [3] Security Keys Location
echo   C:\claves\private_key.pem
echo   C:\claves\public_key.pem
echo   C:\claves\firebaseGoogleCredentials.json
echo.

echo [4] Firebase Configuration
echo   File: somosjandula-main/.env.development
echo   Status: Already configured
echo.

echo [5] Database Setup
echo   Required: MySQL with user 'root' password 'toor'
echo   Create database: reaktor_firebaseserver
echo.

echo ============================================
echo Ready to start development!
echo ============================================
echo.

cmd /c mvn clean install &^
cmd /c docker build -t request-manager-app . &^
cmd /c docker compose up -d
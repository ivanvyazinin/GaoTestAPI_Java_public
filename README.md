# Automated tests for gaozhiyuan_api 

**Run:**<br>
Requires Java 8 and maven 3.5<br><br>
mvn test <br>
mvn allure:serve

or

docker build . -t gaoapitest <br>
docker run --rm -v "$(pwd)":"$(pwd)" -w "$(pwd)" gaoapitest mvn test -nsu
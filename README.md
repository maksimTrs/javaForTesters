# javaForTesters.

# Project base: Selenium + TestNG + Dockerfile + docker-compose.yaml + Gradle

#project contains 3 parts:

1) Code base for testing
2) Tests
3) Xampp app
4) Docker files to start app via images
   All parts were added to the docker compose file.

For Xampp docker compose part need to use "addressbook.7z" folder with service data.
xampp_with_book_app:
volumes:

- {path tp the unziped addressbook.7z}:/www
  By default, addressbook folder has Xampp location: \\xampp\htdocs\addressbook

Separate image parts of the app:

- docker push roronoazorroippo/xampp-with-addressbook-app:5
- docker push roronoazorroippo/java-for-testers-1:latest

https://hub.docker.com/r/tomsik68/xampp/


To prepare jar main and test data use:
1) ./gradlew clean
2) ./gradlew assemble
3) E:\MAX\IT\1Coding\IntelliJ_IDEA_Projects\javaForTesters\build\libs> java -cp "javaForTesters-1.0-SNAPSHOT.jar;javaForTesters-test-1.0-SNAPSHOT.jar;lib/*" org.testng.TestNG ..\resour
ces\test\testngCreationTests.xml

java -cp "javaForTesters-1.0-SNAPSHOT.jar;javaForTesters-test-1.0-SNAPSHOT.jar;lib/*" -DHUB_HOST="host.docker.internal" -DBROWSER=chrome  org.testng.TestNG ..\re
sources\test\testngCreationTests.xml



FROM openjdk:17-jdk-slim

ENV GRADLE_VERSION 7.4.2

RUN apt-get update && \
    apt-get install -y curl unzip && \
    rm -rf /var/lib/apt/lists/*

RUN curl -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip && \
    unzip gradle.zip && \
    rm gradle.zip && \
    mv gradle-${GRADLE_VERSION} /opt/gradle

ENV PATH $PATH:/opt/gradle/bin
#ENV HUB_HOST=""
#ENV BROWSER=""

COPY src /app/src
COPY build.gradle.kts /app/build.gradle.kts
#COPY /build/libs/*.jar /app/build/libs/
#COPY settings.gradle.kts /app/settings.gradle.kts
#COPY gradlew /app/gradlew
#COPY gradlew.bat /app/gradlew.bat
#RUN mkdir -p /app/images

WORKDIR /app

#EXPOSE 8080

#RUN gradle build #--no-daemon
RUN gradle assemble

#CMD  gradle test  $   -Psuite1 -Psuite2
#RUN cd /app/src

#ENTRYPOINT gradle test -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST   $MODULE
ENTRYPOINT gradle clean test $HUB_HOST $BROWSER $MODULE  allureReport
#  gradle allureReport
#-DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST   $MODULE
#$MODULE


#  docker build -t roronoazorroippo/java-for-testers-1 .

#  docker run -e HUB_HOST=-Phub_host  -e BROWSER=-Pfirefox   -e  MODULE="-Psuite1 -Psuite2" --name java-for-testers   roronoazo
  #rroippo/java-for-testers-1

 # docker run --name myXampp -p 41061:22 -p 41062:80  -v E:\MAX\IT\xampp\htdocs\addressbook:/www   roronoazorroippo/xampp-with-addressbook-app:5

 # allure serve
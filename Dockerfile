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

WORKDIR /app

EXPOSE 8080

#RUN gradle build #--no-daemon
RUN gradle assemble

#CMD  gradle test  $   -Psuite1 -Psuite2
#RUN cd /app/src

#ENTRYPOINT gradle test -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST   $MODULE
ENTRYPOINT gradle clean test  -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST   $MODULE
#$MODULE

# docker run   -e  MODULE="-Psuite1 -Psuite2"  --rm  roronoazorroippo/java-for-testers-1

# docker build -t roronoazorroippo/java-for-testers-2 .

# docker run  -e BROWSER=FIREFOX  -e HUB_HOST=192.168.0.191  -e  MODULE="-Psuite1 -Psuite2"  --rm  roronoazorroippo/java-for-testers-1
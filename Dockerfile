FROM gradle AS build
COPY --chown=gradle:gradle . /home/gradle/src/currency-rate
WORKDIR /home/gradle/src/currency-rate
RUN gradle build --no-daemon --stacktrace
FROM openjdk
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/src/currency-rate/build/libs/*.jar currency-rate.jar
ENTRYPOINT ["java","-jar","/currency-rate.jar"]
from eclipse-temurin:21-jdk as build

workdir /workspace

copy gradlew .
copy gradlew.bat .
copy gradle ./gradle

copy settings.gradle .
copy build.gradle .

copy services/user-service/build.gradle \
     ./services/user-service/build.gradle

copy services/user-service/src \
     ./services/user-service/src

run chmod +x gradlew

run ./gradlew :services:user-service:bootJar --no-daemon


from eclipse-temurin:21-jre

workdir /app

copy --from=build \
     /workspace/services/user-service/build/libs/*.jar \
     app.jar

expose 8081

entrypoint ["java", "-jar", "app.jar"]
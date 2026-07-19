from eclipse-temurin:21-jdk as build

workdir /workspace

copy gradlew .
copy gradlew.bat .
copy gradle ./gradle

copy settings.gradle .
copy build.gradle .

copy contracts/grpc-contracts/build.gradle \
     ./contracts/grpc-contracts/build.gradle

copy contracts/grpc-contracts/src \
     ./contracts/grpc-contracts/src

copy services/user-service/build.gradle \
     ./services/user-service/build.gradle

copy services/product-service/build.gradle \
     ./services/product-service/build.gradle

copy services/product-service/src \
     ./services/product-service/src

run chmod +x gradlew

run ./gradlew :services:product-service:bootJar --no-daemon


from eclipse-temurin:21-jre

workdir /app

copy --from=build \
     /workspace/services/product-service/build/libs/*.jar \
     app.jar

expose 8082
expose 9092

entrypoint ["java", "-jar", "app.jar"]
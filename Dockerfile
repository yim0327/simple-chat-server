# 빌드 단계
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

# 1. 프로젝트 전체 복사
COPY . .

# 2. Gradle 빌드
RUN ./gradlew clean build -x test

# 실행 단계
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# 3. 빌드된 jar 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
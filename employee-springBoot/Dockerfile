# ============================
# 1️⃣ BUILD STAGE
# ============================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
Copy src ./src

#Build the application
RUN mvn clean package -DskipTests

# ============================
# 2️⃣ RUNTIME STAGE
# ============================
FROM eclipse-temurin:21-jdk

WORKDIR /app

#Copy Jar from build stage
COPY --from=builder /app/target/*.jar app.jar

#Expose port
EXPOSE 8080

#Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]


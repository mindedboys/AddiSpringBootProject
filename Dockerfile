FROM openjdk:21-alpine
EXPOSE 8080
ADD target/BankingApplication.jar BankingApplication.jar
ENTRYPOINT ["java", "-jar", "/BankingApplication.jar"]
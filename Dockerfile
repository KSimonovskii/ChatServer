FROM eclipse-temurin:21-jre-alpine

LABEL author = "Kirill"

WORKDIR /app

COPY ./out/artifacts/ChatServer_jar/chat-server.jar ./chat-server.jar

ENTRYPOINT ["java", "-jar", "/app/chat-server.jar", "9000"]
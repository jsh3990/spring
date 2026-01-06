FROM eclipse-temurin:17-jdk

LABEL creator=coding404

ARG jarfile=build/libs/spring-myweb-0.0.1-SNAPSHOT.jar

COPY ${jarfile} /app.jar

# volume 지정
#VOLUME ["/upload"]
RUN mkdir /upload

# 
EXPOSE 8282

CMD ["java", "-jar", "/app.jar"]
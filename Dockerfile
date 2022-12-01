FROM openjdk:11-jre-slim
VOLUME /tmp
ADD target/mission-0.0.1.jar app.jar
EXPOSE 80
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

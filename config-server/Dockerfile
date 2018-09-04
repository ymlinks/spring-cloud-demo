FROM frolvlad/alpine-oraclejdk8:latest
VOLUME /tmp
ADD ./build/libs/config-server.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
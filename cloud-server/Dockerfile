FROM frolvlad/alpine-oraclejdk8:latest
VOLUME /tmp
ADD ./build/libs/cloud-server-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
FROM frolvlad/alpine-oraclejdk8:latest
VOLUME /tmp
ADD ./build/libs/boot-demo.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
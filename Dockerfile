FROM openjdk:8
ENV WSCL 1.0
LABEL maintainer="khisaham"
LABEL group = "Logicea"
EXPOSE 8080
VOLUME /tmp
COPY . .
#RUN set -ex && apt-get update && apt-get install -y maven && mvn clean install -X
#TODO pull source code from gitlab, build (mvn)
#TODO pull webapp from gitlab test it then add to main repo
ADD target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar
CMD /bin/sh -c 'touch /cards-0.0.1-SNAPSHOT.jar'
CMD apk add tzdata
ENV TZ=Africa/Nairobi
CMD ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx256m","-Djava.security.egd=file:/dev/./urandom","-jar","/cards-0.0.1-SNAPSHOT.jar"]
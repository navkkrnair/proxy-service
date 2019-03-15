FROM openjdk:8-jdk-alpine
MAINTAINER "navkkrnair@gmail.com"
ENV APPROOT="/usr/nobody"
WORKDIR $APPROOT    
ADD build/libs/proxy-service-1.0.jar $APPROOT
EXPOSE 8080
USER nobody
ENTRYPOINT ["java"]
CMD ["-jar","-Xmx512m","-Xms512m","-Djava.security.egd=file:/dev/./urandom", "/usr/nobody/proxy-service-1.0.jar"]
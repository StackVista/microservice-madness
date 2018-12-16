FROM ubuntu:latest

RUN apt-get update \
    && apt-get --no-install-recommends -y install \
    openjdk-8-jre gnupg \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists

RUN mkdir /root/mad-service
COPY build/libs/mad-service-0.1.0.jar /root/mad-service

CMD /usr/bin/java -jar /root/mad-service/mad-service-0.1.0.jar

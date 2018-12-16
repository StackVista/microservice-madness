FROM alpine:edge

RUN apk add --no-cache openjdk8
RUN mkdir /root/config-service
COPY build/libs/config-service-0.1.0.jar /root/config-service

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/root/config-service/config-service-0.1.0.jar"]
EXPOSE 8080

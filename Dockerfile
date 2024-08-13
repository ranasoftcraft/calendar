FROM registry.access.redhat.com/ubi8/openjdk-11-runtime
MAINTAINER sandeep.rana
COPY target/*.jar executable.jar
ENTRYPOINT ["java", "-jar", "executable.jar"]
FROM java:8
MAINTAINER chenjie32@lenovo.com

ARG JAR_FILE=smart-edge-management.jar
ADD ./target/${JAR_FILE}  /app.jar
EXPOSE 8000
ENTRYPOINT ["/bin/sh", "-c", "/usr/bin/java ${JAVA_OPTS} -jar /app.jar"]
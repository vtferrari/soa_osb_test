FROM openjdk:11-jre-slim

COPY ./run.sh /opt/run.sh
RUN chmod +x /opt/run.sh
COPY target/soa-osb-test-*.jar /opt/app.jar
EXPOSE 8080
CMD /opt/run.sh
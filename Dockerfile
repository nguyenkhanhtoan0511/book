FROM openjdk:8-jre

# Karaf environment variables
ENV KARAF_INSTALL_PATH /opt
ENV KARAF_HOME $KARAF_INSTALL_PATH/apache-karaf
ENV PATH $PATH:$KARAF_HOME/bin
#WORKDIR $KARAF_HOME

# karaf_dist can point to a directory or a tarball on the local system
ARG karaf_dist=NOT_SET

# Install build dependencies and karaf
ADD $karaf_dist $KARAF_INSTALL_PATH
RUN set -x && \
  ln -s $KARAF_INSTALL_PATH/apache-karaf* $KARAF_HOME

ADD api/target/api-1.0-SNAPSHOT.jar /app/api-1.0-SNAPSHOT.jar
ADD service/target/service-1.0-SNAPSHOT.jar /app/service-1.0-SNAPSHOT.jar
ADD rest/target/rest-1.0-SNAPSHOT.jar /app/rest-1.0-SNAPSHOT.jar
ADD webapp/target/webapp-1.0-SNAPSHOT.war /app/webapp-1.0-SNAPSHOT.war

EXPOSE 8101 1099 44444 8181
CMD ["karaf", "run"]


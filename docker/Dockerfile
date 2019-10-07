FROM openjdk:8-jre

EXPOSE 8181 8101
ENV KARAF_VERSION 4.2.6

ADD apache-karaf-4.2.6.tar.gz /tmp/apache-karaf-4.2.6.tar.gz

RUN tar xzf /tmp/apache-karaf-4.2.6.tar.gz -C /opt/ \
 && ln -s /opt/apache-karaf-${KARAF_VERSION} /opt/karaf \
 && rm /tmp/apache-karaf-4.2.6.tar.gz

ENV PATH $PATH:/opt/karaf/bin

CMD /opt/karaf/bin/karaf server



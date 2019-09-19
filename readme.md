mvn clean install

feature:install booking-service

feature:repo-add mvn:www.booking/features/1.0-SNAPSHOT/xml
feature:install booking-api
bundle:install mvn:www.booking/service/1.0-SNAPSHOT

start 114

Notes:

- check DataSource

karaf@root()> service:list DataSource
[javax.sql.DataSource]
----------------------
 databaseName = booking
 dataSourceName = booking
 felix.fileinstall.filename = file:/home/nqloc/software/apache-karaf-4.2.6/etc/org.ops4j.datasource-booking.cfg
 org.apache.karaf.features.configKey = org.ops4j.datasource-booking
 osgi.jdbc.driver.class = org.mariadb.jdbc.Driver
 osgi.jdbc.driver.name = mariadb
 osgi.jndi.service.name = booking
 password = mysql
 pax.jdbc.managed = true
 service.bundleid = 48
 service.factoryPid = org.ops4j.datasource
 service.id = 151
 service.pid = org.ops4j.datasource.2eee8ed4-2a85-4977-94d3-0d2f691ec20e
 service.scope = singleton
 url = jdbc:mariadb://192.168.95.229:3306/booking?characterEncoding=UTF-8
 user = root
Provided by :
 OPS4J Pax JDBC Config (48)


- check jdbc
    jdbc:ds-info booking
    jdbc:ds-list



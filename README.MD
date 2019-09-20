mvn clean install

----------------------
gedit file apache-karaf-4.2.6/etc/org.ops4j.datasource-book.cfg

osgi.jdbc.driver.class = org.mariadb.jdbc.Driver
osgi.jdbc.driver.name=mariadb
pool=dbcp2
xa=true
databaseName=mybook
user=root
password=123456
url=jdbc:mysql://localhost:3306/mybook?characterEncoding=UTF-8
dataSourceName=book
org.apache.karaf.features.configKey = org.ops4j.datasource-book
----------------------

Install and deploy features:

feature:repo-add mvn:www.book/features/1.0-SNAPSHOT/xml
feature:install book-api
feature:install book-service
feature:install book-rest

Notes:

- check DataSource

karaf@root()> service:list DataSource
[javax.sql.DataSource]
----------------------
 databaseName = mybook
 dataSourceName = book
 felix.fileinstall.filename = file:/home/nktoan/software/apache-karaf-4.2.6/etc/org.ops4j.datasource-book.cfg
 org.apache.karaf.features.configKey = org.ops4j.datasource-book
 osgi.jdbc.driver.class = org.mariadb.jdbc.Driver
 osgi.jdbc.driver.name = mariadb
 osgi.jndi.service.name = book
 password = 123456
 pax.jdbc.managed = true
 service.bundleid = 48
 service.factoryPid = org.ops4j.datasource
 service.id = 151
 service.pid = org.ops4j.datasource.5bcc40ab-9e74-42c6-b443-7bc1bf0caa55
 service.scope = singleton
 url = jdbc:mysql://localhost:3306/mybook?characterEncoding=UTF-8
 user = root
Provided by :
 OPS4J Pax JDBC Config (48)
Used by:
 book-service (114)


- check jdbc
    jdbc:ds-list

karaf@root()> jdbc:ds-list
Name │ Service Id │ Product │ Version                              │ URL                                                        │ Status
─────┼────────────┼─────────┼──────────────────────────────────────┼────────────────────────────────────────────────────────────┼───────
book │ 151        │ MySQL   │ 10.4.7-MariaDB-1:10.4.7+maria~bionic │ jdbc:mysql://localhost:3306/mybook?characterEncoding=UTF-8 │ OK










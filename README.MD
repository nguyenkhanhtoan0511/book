- Build
    The build uses Apache Maven. Simply use:
    mvn clean install
- Add config Datasource
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

- Install and deploy features:
feature:repo-add mvn:www.book/features/1.0-SNAPSHOT/xml
feature:install book-api
Notes:
- check DataSource
karaf@root()> service:list DataSource
- check jdbc
karaf@root()> jdbc:ds-list
feature:install book-service
feature:install book-rest











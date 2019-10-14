# Requirement
**Karaf version 4.2.6**

**MariaDB**

# Build
    The build uses Apache Maven. Simply use:
    mvn clean install
# Add config Datasource
**gedit file apache-karaf-4.2.6/etc/org.ops4j.datasource-book.cfg**

```ruby
osgi.jdbc.driver.class = org.mariadb.jdbc.Driver
osgi.jdbc.driver.name=mariadb
pool=dbcp2
xa=true
databaseName=mybook
user=root
password=123456
url=jdbc:mysql://localhost:3306/mybook?characterEncoding=UTF-8
dataSourceName=mybook
org.apache.karaf.features.configKey = org.ops4j.datasource-book
```

# Install and deploy features:
```ruby
feature:repo-add mvn:www.book/features/1.0-SNAPSHOT/xml
```
```ruby
feature:install book-api
```
- Notes check connect jdbc :
  - check DataSource
  ```ruby
    karaf@root()> service:list DataSource
    ```
  - check jdbc
   ```ruby
    karaf@root()> jdbc:ds-list
    ```

```ruby
feature:install book-service
```
```ruby
feature:install book-rest
```
```ruby
feature:install book-webapp
```
# Start web: 
**http://localhost:8181**














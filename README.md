# Activiti Explorer

This is HSSC's customized fork of the activiti-explorer webapp. This repository
is a subset of the activiti svn repository, with history starting at release
5.7. 

## Differences

- Shibboleth Login
- ObisEntity-based identity service

## Building

The project is set up so that the java needs to build first followed by the
Clojure. So to get a war file you `mvn clean compile clojure:compile package`.

It's also necessary to have a configuration file on the classpath called
`db.properties` with config information for the activiti mysql database
as well as the info for obis-entity. E.g., something like this:

```
db=mysql
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost/activiti
jdbc.username=activiti
jdbc.password=47mFggfuG4N6P3QD

obis.entity.host=localhost
obis.entity.port=4567
```

The easiest way to get this on the classpath is to place it in
`src/main/resources/db.properties`, which is in the `.gitignore` file.

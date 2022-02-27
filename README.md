# Zara Challenge

Spring boot application that returns price details for a given selected date, product id, and brand id

## Tech Stack

- Spring Boot
- H2
- FlyWay migration
- Java 11
- Spring Data JPA
- Maven
- Lombok
- Mockito


## Initialize H2 database

The init script located at db/migration will initialize schema and data. This configuration is set in [application.properties](https://github.com/nujovich/zara-challenge/blob/main/src/main/resources/application.properties) file.

```bash
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:prices
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.flyway.locations=classpath:/db/migration
```

In order to initialize database, we need to run the application and flyway will pick-up the init script located at that folder

```bash
mvnw spring-boot:run
```

### Console Output

```bash
2022-02-27 13:08:44.152  INFO 3904 --- [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:prices'
2022-02-27 13:08:44.399  INFO 3904 --- [  restartedMain] o.f.c.internal.license.VersionPrinter    : Flyway Community Edition 8.0.5 by Redgate
2022-02-27 13:08:44.399  INFO 3904 --- [  restartedMain] o.f.c.i.database.base.BaseDatabaseType   : Database: jdbc:h2:mem:prices (H2 1.4)
2022-02-27 13:08:44.518  INFO 3904 --- [  restartedMain] o.f.core.internal.command.DbValidate     : Successfully validated 1 migration (execution time 00:00.043s)
2022-02-27 13:08:44.539  INFO 3904 --- [  restartedMain] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "PUBLIC"."flyway_schema_history" ...
2022-02-27 13:08:44.606  INFO 3904 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": << Empty Schema >>
2022-02-27 13:08:44.621  INFO 3904 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "0.0.1 - init"
2022-02-27 13:08:44.657  INFO 3904 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Successfully applied 1 migration to schema "PUBLIC", now at version v0.0.1 (execution time 00:00.061s)
```


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
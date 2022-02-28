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
- JaCoCo


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
## JaCoCo Report Generation
I included Unit Tests on both controller and service. In order to generate the coverage report write the following on the terminal.

```bash
mvn install
mvn clean jacoco:prepare-agent install jacoco:report
```

This will generate a report at site/jacoco/index.html that can be visualized in browser

## Prices API

### Required paramaters:
- Datetime: a date and time for which the price will be applied. It must follow ISO Format, otherwise will throw an HTTP BAD REQUEST error
- Product id: Unique identifier for the product
- Brand Id: Unique identifier for the brand (1=ZARA)

```bash
curl --location --request GET
'http://localhost:8080/challenge/v1/price/2020-06-14T10:00/35455/1'
```
```json
{
    "id": 1,
    "brandId": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "priceListId": 1,
    "price": 35.5,
    "productId": 35455,
    "priority": 1,
    "currency": "EUR"
}
```

In case more than one price can be applied, the API will return the price with the highest priority

```bash
curl --location --request GET 'http://localhost:8080/challenge/v1/price/2020-06-14T16:00/35455/1'
```
```json
{
    "id": 2,
    "brandId": 1,
    "startDate": "2020-06-14T15:00:00",
    "endDate": "2020-06-14T18:30:00",
    "priceListId": 1,
    "price": 25.45,
    "productId": 35455,
    "priority": 2,
    "currency": "EUR"
}
```

## Developer
[Nadia Ujovich](https://www.linkedin.com/in/nadiaujovich/)

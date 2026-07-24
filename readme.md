# Торговая система (Trading System)

## Описание проекта

REST API для торговой системы с сопоставлением заявок и генерацией сделок.

### Технологии
- Java 21
- Spring Boot 3.2.3
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven

---

## Запуск приложения

### 1. Настройка базы данных

В файле `src/main/resources/application.properties` укажите параметры вашей PostgreSQL:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/название_вашей_бд
spring.datasource.username=ваш_пользователь
spring.datasource.password=ваш_пароль
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 2. Локальное развертывание сервера

В терминале введите команду

```
mvn clean spring-boot:run
```

После запуска приложение будет доступно по адресу: http://localhost:8080
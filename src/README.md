Chat Backend

Java Spring Boot backend для чат-приложения с аутентификацией и историей сообщений.

Технологии

- Java 17
- Spring Boot
- Spring Security + JWT
- PostgreSQL
- Gradle
- Docker + Docker Compose

---

Запуск проекта через Docker

Требования

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

Команды для запуска

```bash
git clone https://github.com/AssylBoribay/ChatBackend
cd ChatBackend
./gradlew build
docker-compose up --build
```


---

Возможности

-  JWT-аутентификация (регистрация / логин)
-  Отправка и получение сообщений
-  История сообщений по пользователям
-  REST API, протестировано через Postman

---

EndPoints:

---
```
POST: http://localhost:8080/auth/register  || Регистрация юзера
POST: http://localhost:8080/auth/login  || Логин(Возвращает JWT)
POST: http://localhost:8080/messages/send || Отправка сообщении
GET: http://localhost:8080/messages/history || История чата
```
---
Примеры запросов:
````
Register: {username: "user1", password: "password"}
Send message: {toUsername: "user2", content: "hi"}
Chat history: http://localhost:8080/messages/history?user=user1


##  Автор

Assyl Boribay
[GitHub](https://github.com/AssylBoribay)
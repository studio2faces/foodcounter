Food Counter
________


1. Бд MySQL

2. Скрипт создания таблиц:
\main\resources\fridge_counter_db.sql

3. Tomcat
3.1. Создать Run/Debug Configuration (Maven)
3.2. Command line - пишем "tomcat7:run"
3.3. Select Maven Goal - в Command line пишем "clean"

4. Запросы

Логин:
POST http://localhost:8888/AuthorizationServlet?login=Dasha

Добавить продукт:
POST http://localhost:8888/AddAndShowServlet?name=Milk&weight=200&price=80&kcal=500&users_uuid=4856190a-03c6-4c7c-8da2-89353a4a83ca

Получить содержимое холодильника пользователя:
(correct uuid)
GET http://localhost:8888/AddAndShowServlet?users_uuid=b7aeba0d-af13-4366-91d2-e37cd47eac28

(uuid=null)
GET http://localhost:8888/AddAndShowServlet?

(incorrect uuid)
GET http://localhost:8888/AddAndShowServlet?users_uuid=4852230a-0000-0000-8da2-89353a4a83ca
Food Counter

Что это?
________
Возможность добавить продукты в холодильник (БД) и получить список добавленных продуктов по ссылке.
________

1. Бд MySQL

2. Создать бд
CREATE database fridge_counter_db;
USE fridge_counter_db;
----

CREATE DATABASE `fridge_counter_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

Это из workbench, что это такое после названия я не поняла, ошибки какие-то, но откуда..


3. Запустить скрипт создания таблиц
CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `weight` int NOT NULL,
  `priceFor1g` double NOT NULL,
  `kcal` int NOT NULL,
  `isCooked` varchar(1) NOT NULL DEFAULT 'n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

----
Создать таблицу users
CREATE TABLE `users` (
  `users_uuid` varchar(36) NOT NULL,
  `login` varchar(20) NOT NULL,
  PRIMARY KEY (`users_uuid`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


4. Запустить скрипт добавления тестовых продуктов
INSERT INTO food VALUES(null,'Water', 1000, 0.03, 0, 'n');
INSERT INTO food VALUES(null,'Bread', 400, 0.13, 400, 'n');
INSERT INTO food VALUES(null,'Juice', 1000, 0.1, 50, 'n');

5. Собрать проект командой
Может этой? Command line: tomcat7:run

6. Запустить проект в томкат
Не знаю, что здесь писать

7. Запросы

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

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
  `price` double NOT NULL,
  `kcal` int NOT NULL,
  `isCooked` varchar(1) NOT NULL DEFAULT 'n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

----
Насчет последней строки не знаю что это такое, из Workbench, вдруг без нее не будет работать, поэтому не стала удалять

4. Запустить скрипт добавления тестовых продуктов
INSERT INTO food VALUES(null,'Water', 1000, 0.03, 0, 'n');
INSERT INTO food VALUES(null,'Bread', 400, 0.13, 400, 'n');
INSERT INTO food VALUES(null,'Juice', 1000, 0.1, 50, 'n');

5. Собрать проект командой
Может этой? Command line: tomcat7:run

6. Запустить проект в томкат
Не знаю, что здесь писать

7. Запросы
Стартовая страница
http://localhost:8888/add-to-fridge.html

Добавить продукт в холодильник
POST http://localhost:8888/AddToFridgeServlet?productName=Juice&productWeight=1000&productPrice=120&productKcal=50

Получить список продуктов из холодильника
GET http://localhost:8888/ShowAllServlet
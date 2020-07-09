USE fridge_counter_db;

DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS users;


CREATE TABLE `users` (
  `uuid` varchar(36) NOT NULL,
  `login` varchar(20) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `users_uuid_UNIQUE` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users VALUES
('6cdc2e99-9180-4a4c-8077-c3c023c5f18e', 'Alex'),
('fab7859c-8c9b-42b6-8ff1-3fbc70d88338', 'Anton'),
('b7aeba0d-af13-4366-91d2-e37cd47eac28', 'Dasha');


CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `weight` int NOT NULL,
  `price` double NOT NULL,
  `priceByOneGramm` double NOT NULL,
  `kcal` int NOT NULL,
  `isCooked` tinyint NOT NULL DEFAULT '0',
  `uuid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO food VALUES
(null, 'Carrot', '1500', '30', '0.02', '20', '0', 'fab7859c-8c9b-42b6-8ff1-3fbc70d88338'),
(null, 'Cheese', '400', '500', '1.25', '300', '0', 'b7aeba0d-af13-4366-91d2-e37cd47eac28'),
(null, 'Meat', '800', '1200', '1.5', '300', '0', 'b7aeba0d-af13-4366-91d2-e37cd47eac28'),
(null, 'Orange', '1000', '50', '0.05', '300', '0', 'b7aeba0d-af13-4366-91d2-e37cd47eac28');
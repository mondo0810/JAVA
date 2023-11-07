
CREATE TABLE `books` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_borrowed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `borrowhistory` (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `book_id` int DEFAULT NULL,
  `ticket_id` int DEFAULT NULL,
  `is_returned` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`borrow_id`),
  KEY `book_id` (`book_id`),
  KEY `borrowhistory_FK` (`ticket_id`),
  CONSTRAINT `borrowhistory_FK` FOREIGN KEY (`ticket_id`) REFERENCES `borrowtickets` (`ticket_id`),
  CONSTRAINT `borrowhistory_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `borrowtickets` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `borrow_date` datetime DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `borrowtickets_FK` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `student_email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

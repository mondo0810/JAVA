-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3308
-- Thời gian đã tạo: Th10 07, 2023 lúc 07:12 AM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: ` aptechlibrary`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `books`
--

CREATE TABLE `books` (
  `book_id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `is_borrowed` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `books`
--

INSERT INTO `books` (`book_id`, `code`, `name`, `author`, `is_borrowed`) VALUES
(1, 'B001', 'Book A', 'Author 1', 0),
(2, 'B002', 'Book B', 'Author 2', 1),
(3, 'B003', 'Book C', 'Author 3', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowhistory`
--

CREATE TABLE `borrowhistory` (
  `borrow_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `borrow_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `borrowhistory`
--

INSERT INTO `borrowhistory` (`borrow_id`, `book_id`, `student_id`, `borrow_date`, `return_date`) VALUES
(1, 1, 2, '2023-01-15 10:00:00', '2023-01-20 15:30:00'),
(2, 2, 1, '2023-02-10 08:45:00', '2023-02-15 16:20:00'),
(3, 3, 3, '2023-03-05 09:15:00', '2023-03-10 14:45:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowtickets`
--

CREATE TABLE `borrowtickets` (
  `ticket_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `borrow_date` datetime DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `is_returned` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `borrowtickets`
--

INSERT INTO `borrowtickets` (`ticket_id`, `book_id`, `student_id`, `borrow_date`, `due_date`, `is_returned`) VALUES
(1, 1, 3, '2023-04-01 11:30:00', '2023-04-06 12:00:00', 1),
(2, 2, 3, '2023-04-02 13:20:00', '2023-04-07 17:45:00', 0),
(3, 3, 1, '2023-04-03 09:00:00', '2023-04-08 11:30:00', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `students`
--

INSERT INTO `students` (`student_id`, `student_name`, `student_email`) VALUES
(1, 'Student 1', 'student1@example.com'),
(2, 'Student 2', 'student2@example.com'),
(3, 'Student 3', 'student3@example.com');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Chỉ mục cho bảng `borrowhistory`
--
ALTER TABLE `borrowhistory`
  ADD PRIMARY KEY (`borrow_id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Chỉ mục cho bảng `borrowtickets`
--
ALTER TABLE `borrowtickets`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Chỉ mục cho bảng `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `borrowhistory`
--
ALTER TABLE `borrowhistory`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `borrowtickets`
--
ALTER TABLE `borrowtickets`
  MODIFY `ticket_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `borrowhistory`
--
ALTER TABLE `borrowhistory`
  ADD CONSTRAINT `borrowhistory_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  ADD CONSTRAINT `borrowhistory_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);

--
-- Các ràng buộc cho bảng `borrowtickets`
--
ALTER TABLE `borrowtickets`
  ADD CONSTRAINT `borrowtickets_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  ADD CONSTRAINT `borrowtickets_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

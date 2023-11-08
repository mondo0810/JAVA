-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3308
-- Thời gian đã tạo: Th10 08, 2023 lúc 05:17 AM
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
-- Cơ sở dữ liệu: `asm`
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
  `is_borrowed` tinyint(1) DEFAULT 0,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `books`
--

INSERT INTO `books` (`book_id`, `code`, `name`, `author`, `is_borrowed`, `created_at`) VALUES
(1, 'B001', 'PHP', 'Do Thi', 1, '2023-11-08 11:15:39'),
(2, 'B002', 'SQL', 'Do Thi', 0, '2023-11-08 11:15:39'),
(3, 'B003', 'Java', 'Do Thi', 0, '2023-11-08 11:15:39'),
(4, 'B004', 'NodeJs', 'Do Thi', 0, '2023-11-08 11:15:39');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowhistory`
--

CREATE TABLE `borrowhistory` (
  `borrow_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `ticket_id` int(11) DEFAULT NULL,
  `is_returned` tinyint(1) DEFAULT 0,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `borrowhistory`
--

INSERT INTO `borrowhistory` (`borrow_id`, `book_id`, `ticket_id`, `is_returned`, `created_at`) VALUES
(1, 1, 1, 0, '2023-11-08 11:15:59');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowtickets`
--

CREATE TABLE `borrowtickets` (
  `ticket_id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `borrow_date` datetime DEFAULT current_timestamp(),
  `due_date` datetime DEFAULT NULL,
  `created_at` int(11) NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `borrowtickets`
--

INSERT INTO `borrowtickets` (`ticket_id`, `student_id`, `borrow_date`, `due_date`, `created_at`) VALUES
(1, 1, NULL, '2023-10-10 00:00:00', 2147483647);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `students`
--

INSERT INTO `students` (`student_id`, `student_name`, `student_email`, `created_at`) VALUES
(1, 'Thi', 'Thi', '2023-11-08 11:16:36');

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
  ADD KEY `borrowhistory_FK` (`ticket_id`);

--
-- Chỉ mục cho bảng `borrowtickets`
--
ALTER TABLE `borrowtickets`
  ADD PRIMARY KEY (`ticket_id`),
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
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `borrowhistory`
--
ALTER TABLE `borrowhistory`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `borrowtickets`
--
ALTER TABLE `borrowtickets`
  MODIFY `ticket_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `borrowhistory`
--
ALTER TABLE `borrowhistory`
  ADD CONSTRAINT `borrowhistory_FK` FOREIGN KEY (`ticket_id`) REFERENCES `borrowtickets` (`ticket_id`),
  ADD CONSTRAINT `borrowhistory_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`);

--
-- Các ràng buộc cho bảng `borrowtickets`
--
ALTER TABLE `borrowtickets`
  ADD CONSTRAINT `borrowtickets_FK` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

DROP TABLE IF EXISTS `bookings` CASCADE CONSTRAINTS;
create table bookings
(
   booking_id bigint NOT NULL,
   `show_date` date NOT NULL,
   `show_time` varchar(255) NOT NULL,
   `movie_name` varchar(255) NOT NULL,
   `seatId` varchar(255) NOT NULL,
   PRIMARY KEY(booking_id)
);

DROP TABLE IF EXISTS `city` CASCADE CONSTRAINTS;
CREATE TABLE `city` (
  `city_id` bigint NOT NULL,
  `city_name` varchar(255) NOT NULL,
  `city_pincode` varchar(255) NOT NULL,
  `city_state` varchar(255) NOT NULL,
  PRIMARY KEY (`city_id`)
);

DROP TABLE IF EXISTS `movie` CASCADE CONSTRAINTS;
CREATE TABLE `movie` (
  `movie_id` bigint NOT NULL,
  `movie_description` varchar(255) DEFAULT NULL,
  `movie_director` varchar(255) NOT NULL,
  `movie_name` varchar(255) NOT NULL,
  PRIMARY KEY (`movie_id`)
);

DROP TABLE IF EXISTS `theater` CASCADE CONSTRAINTS;
CREATE TABLE `theater` (
  `theater_id` bigint NOT NULL,
  `theater_area` varchar(255) NOT NULL,
  `theater_name` varchar(255) NOT NULL,
  `city_city_id` bigint DEFAULT NULL,
  PRIMARY KEY (`theater_id`),
  CONSTRAINT `FKtflvxj66v6b9kicc9kwh3js3g` FOREIGN KEY (`city_city_id`) REFERENCES `city` (`city_id`)
);

DROP TABLE IF EXISTS `show` CASCADE CONSTRAINTS;
CREATE TABLE `show` (
  `show_id` bigint NOT NULL,
  `show_date` date NOT NULL,
  `show_time` varchar(255) NOT NULL,
  `the_movie_movie_id` bigint DEFAULT NULL,
  `the_theater_theater_id` bigint DEFAULT NULL,
  PRIMARY KEY (`show_id`),
  CONSTRAINT `FKeg2ca18ko9iie9lo3h6ay2ury` FOREIGN KEY (`the_theater_theater_id`) REFERENCES `theater` (`theater_id`),
  CONSTRAINT `FKsayo2xxw82i3o8h7twmmps3um` FOREIGN KEY (`the_movie_movie_id`) REFERENCES `movie` (`movie_id`)
);

DROP TABLE IF EXISTS `seat` CASCADE CONSTRAINTS;
CREATE TABLE `seat` (
  `seat_id` bigint NOT NULL,
  `matrix_x` varchar(255) DEFAULT NULL,
  `matrix_y` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `the_show_show_id` bigint DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
   CONSTRAINT `FKeg2ca18ko9iie9lo3h6ay2ura` FOREIGN KEY (`the_show_show_id`) REFERENCES `show` (`show_id`)
);


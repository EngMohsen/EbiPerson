DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
       `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
       `first_name` varchar(255) DEFAULT NULL,
       `last_name` varchar(255) DEFAULT NULL,
       `age` varchar(255) DEFAULT NULL,
       `favourite_color` varchar(255) DEFAULT NULL
)
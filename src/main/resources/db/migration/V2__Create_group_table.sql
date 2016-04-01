create table `group` (
    `id` int not null AUTO_INCREMENT,
    `name` varchar(128) not null,
    `adminId` int not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_admin_id` FOREIGN KEY (`adminId`) REFERENCES `users` (`id`) ON UPDATE CASCADE
)ENGINE = InnoDB DEFAULT CHARSET=utf8

create table `users` (
    `id` int not null AUTO_INCREMENT,
    `email` varchar(100) not null,
    `mobilePhone` varchar(100) not null,
    `password` varchar(100) not null,
    `createDate` int(11) not null,
    `role` ENUM("1", "2"),
    PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8

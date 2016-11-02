DROP TABLE IF EXISTS users;
CREATE TABLE  users (
    id int not null primary key AUTO_INCREMENT,  #自增
    mobilePhone varchar(64) not null,
    email varchar(128) not null,
    password varchar(128) not null,
    createDate int(11) not null
);

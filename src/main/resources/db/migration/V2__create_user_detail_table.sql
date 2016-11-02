CREATE TABLE userDetail (
    userId INT UNIQUE NOT NULL,
    school VARCHAR(128) NOT NULL,
    name VARCHAR(128) NOT NULL,
    major VARCHAR(128) NOT NULL,
    degree VARCHAR (128) NOT NULL,
    gender ENUM("F", "M"),
    schoolProvince VARCHAR(128),
    schoolCity VARCHAR(128),
    entranceYear VARCHAR(128)
)
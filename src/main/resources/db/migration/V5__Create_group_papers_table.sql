create table `groupPapers` (
    `groupId` int(11) not null,
    `paperId` int(11) not null,
    CONSTRAINT `fk_group_paper_group_id` FOREIGN KEY (`groupId`) REFERENCES `group` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE INDEX(`groupId`, `paperId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8

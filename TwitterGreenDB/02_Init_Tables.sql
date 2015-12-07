
use twittergreendb;

DROP TABLE IF EXISTS `tweet`;

CREATE TABLE `tweet` (
  `idtweet` bigint(20) NOT NULL AUTO_INCREMENT,
  `iduser` bigint(20) NOT NULL,
  `content` varchar(140) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idtweet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `iduser` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `picture` varchar(255) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `retweet`;

CREATE TABLE `retweet` (
	`idretweet` bigint(20) NOT NULL AUTO_INCREMENT,
    `idtweet` bigint(20) NOT NULL,
    `iduser` bigint(20) NOT NULL,
    `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`idretweet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `DynamicClass` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(200) NOT NULL,
  `SourceCode` text NOT NULL,
  `AddTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `un_class_name` (`ClassName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
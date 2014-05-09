CREATE TABLE `DynamicClass` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(200) NOT NULL,
  `SourceCode` text NOT NULL,
  `AddTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `un_class_name` (`ClassName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Spider` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `PageProcessorId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `PipelineId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SchedulerId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Config` text NOT NULL,
  `AddTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `un_class_name` (`ClassName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PageProcessor` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(200) NOT NULL,
  `Params` text NOT NULL,
  `AddTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `un_class_name` (`ClassName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
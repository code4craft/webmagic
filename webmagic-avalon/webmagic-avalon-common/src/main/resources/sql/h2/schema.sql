CREATE TABLE DynamicClass(
  Id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ClassName` varchar(200) NOT NULL,
  `SourceCode` text NOT NULL,
  `AddTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  UNIQUE INDEX `un_class_name` (`ClassName`)
);
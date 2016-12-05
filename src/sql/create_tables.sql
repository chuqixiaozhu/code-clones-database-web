-- CREATE DATABASE  IF NOT EXISTS `code_clones` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `code_clones`;

-- DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `Project` CASCADE;
CREATE TABLE IF NOT EXISTS `Project`(
  `projectID` int NOT NULL AUTO_INCREMENT,
  `projectName` varchar(140) NOT NULL,
  `authorName` varchar(140) DEFAULT NULL,
  `submitTime` timestamp DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (`projectID`),
  INDEX index_project (`projectID`)
);

DROP TABLE IF EXISTS `Revision` CASCADE;
CREATE TABLE IF NOT EXISTS `Revision` (
  `revisionID` int NOT NULL AUTO_INCREMENT,
  `projectID` int NOT NULL,
  `revisionName` varchar(140) NOT NULL,
  `authorName` varchar(140) DEFAULT NULL,
  `submitTime` timestamp DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (`revisionID`),
  INDEX index_revision (`revisionID`),
  FOREIGN KEY (`projectID`)
  	REFERENCES `Project` (`projectID`)
  	ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS `CodeClone` CASCADE;
CREATE TABLE IF NOT EXISTS `CodeClone` (
  `cloneID` int NOT NULL AUTO_INCREMENT,
  `projectID1` int NOT NULL,
  `revisionID1` int NOT NULL,
  `fileName1` varchar(140) DEFAULT NULL,
  `startLine1` int DEFAULT NULL,
  `endLine1` int DEFAULT NULL,
  `projectID2` int NOT NULL,
  `revisionID2` int NOT NULL,
  `fileName2` varchar(140) DEFAULT NULL,
  `startLine2` int DEFAULT NULL,
  `endLine2` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  
  PRIMARY KEY (`cloneID`),
  INDEX index_clone (`cloneID`),
  FOREIGN KEY (`projectID1`)
  	REFERENCES `Project` (`projectID`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`revisionID1`)
  	REFERENCES `Revision` (`revisionID`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`projectID2`)
  	REFERENCES `Project` (`projectID`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`revisionID2`)
  	REFERENCES `Revision` (`revisionID`)
  	ON UPDATE CASCADE ON DELETE CASCADE
);

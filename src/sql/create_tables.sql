CREATE DATABASE  IF NOT EXISTS `code_clones`;
USE `code_clones`;

CREATE TABLE IF NOT EXISTS `Project`(
  `projectID` INT NOT NULL AUTO_INCREMENT,
  `projectName` VARCHAR(140) NOT NULL,

  PRIMARY KEY (`projectID`),
  UNIQUE (`projectName`),
  INDEX index_project (`projectID`)
);

CREATE TABLE IF NOT EXISTS `Revision` (
  `revisionID` INT NOT NULL AUTO_INCREMENT,
  `projectID` INT NOT NULL,
  `revisionName` VARCHAR(140) NOT NULL,

  PRIMARY KEY (`revisionID`),
  INDEX index_revision (`revisionID`),
  UNIQUE (`revisionName`),
  FOREIGN KEY (`projectID`)
  	REFERENCES `Project` (`projectID`)
  	ON UPDATE CASCADE ON DELETE CASCADE
  
);

CREATE TABLE IF NOT EXISTS `Detector` (
  `detectorID` INT NOT NULL AUTO_INCREMENT,
  `detectorName` VARCHAR(140) NOT NULL,
  `detectorConfig` VARCHAR(10240) NOT NULL,

  PRIMARY KEY (`detectorID`),
  INDEX index_detector (`detectorID`)
);

CREATE TABLE IF NOT EXISTS `Fragment` (
	`fragmentID` INT NOT NULL AUTO_INCREMENT,
	`projectID` INT NOT NULL,
	`revisionID` INT NOT NULL,
	`filePath` VARCHAR(1024) NOT NULL,
	`startLine` INT  NOT NULL,
	`endLine` INT  NOT NULL,
	
	PRIMARY KEY (`fragmentID`),
	INDEX index_fragment (`fragmentID`),
	FOREIGN KEY (`projectID`)
	  	REFERENCES `Project` (`projectID`)
	  	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`revisionID`)
	  	REFERENCES `Revision` (`revisionID`)
	  	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `CodeClone` (
  `cloneID` INT NOT NULL AUTO_INCREMENT,
  `fragment1ID` INT NOT NULL,
  `fragment2ID` INT NOT NULL,
  `detectorID` INT NOT NULL,
  
  PRIMARY KEY (`cloneID`),
  INDEX index_clone (`cloneID`),
  FOREIGN KEY (`fragment1ID`)
  	REFERENCES `Fragment` (`fragmentID`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`fragment2ID`)
  	REFERENCES `Fragment` (`fragmentID`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`detectorID`)
    REFERENCES `Detector` (`detectorID`)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `Evaluation`(
  `evaluationID` INT NOT NULL AUTO_INCREMENT,
  `cloneID` INT NOT NULL,
  `cloneType` INT DEFAULT NULL,
  `similarity` float DEFAULT NULL,
  `truePositive` Boolean DEFAULT NULL,
  `score` float DEFAULT NULL,

  PRIMARY KEY (`evaluationID`),
  INDEX index_evaluation (`evaluationID`),
  FOREIGN KEY (`cloneID`)
  	REFERENCES `CodeClone` (`cloneID`)
  	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `WebUser`(
  `userID` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(140) NOT NULL,
  `password` VARCHAR(140) NOT NULL,
  `userType` VARCHAR(140) NOT NULL,

  PRIMARY KEY (`userID`),
  INDEX index_user (`userID`)
);
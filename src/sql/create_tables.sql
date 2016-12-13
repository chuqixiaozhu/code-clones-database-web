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
  UNIQUE (`projectName`),
  INDEX index_project (`projectID`)
);

DROP TABLE IF EXISTS `Revision` CASCADE;
CREATE TABLE IF NOT EXISTS `Revision` (
  `revisionID` int NOT NULL AUTO_INCREMENT,
  -- `projectID` int NOT NULL,
  `projectName` varchar(140) NOT NULL,
  `revisionName` varchar(140) NOT NULL,
  `authorName` varchar(140) DEFAULT NULL,
  `submitTime` timestamp DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (`revisionID`),
  INDEX index_revision (`revisionID`),
  UNIQUE (`revisionName`),
  -- FOREIGN KEY (`projectID`)
  -- 	REFERENCES `Project` (`projectID`)
  -- 	ON UPDATE CASCADE ON DELETE CASCADE
  FOREIGN KEY (`projectName`)
    REFERENCES `Project` (`projectName`)
    ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS `Detector` CASCADE;
CREATE TABLE IF NOT EXISTS `Detector` (
  `detectorID` int NOT NULL AUTO_INCREMENT,
  -- `projectID` int NOT NULL,
  `detectorName` varchar(140) NOT NULL,
  `configuration` varchar(1024) NOT NULL,

  PRIMARY KEY (`detectorID`),
  INDEX index_detector (`detectorID`)
);

DROP TABLE IF EXISTS `CodeClone` CASCADE;
CREATE TABLE IF NOT EXISTS `CodeClone` (
  `cloneID` int NOT NULL AUTO_INCREMENT,
  -- `projectID1` int NOT NULL,
  -- `revisionID1` int NOT NULL,
  `project1Name` varchar(140) NOT NULL,
  `revision1Name` varchar(140) NOT NULL,
  `fileName1` varchar(140) DEFAULT NULL,
  `startLine1` int DEFAULT NULL,
  `endLine1` int DEFAULT NULL,
  -- `projectID2` int NOT NULL,
  -- `revisionID2` int NOT NULL,
  `project2Name` varchar(140) NOT NULL,
  `revision2Name` varchar(140) NOT NULL,
  `fileName2` varchar(140) DEFAULT NULL,
  `startLine2` int DEFAULT NULL,
  `endLine2` int DEFAULT NULL,
  -- `type` int DEFAULT NULL,
  `detectorID` int NOT NULL,
  
  PRIMARY KEY (`cloneID`),
  INDEX index_clone (`cloneID`),
  -- FOREIGN KEY (`projectID1`)
  --   REFERENCES `Project` (`projectID`)
  --   ON UPDATE CASCADE ON DELETE CASCADE,
  -- FOREIGN KEY (`revisionID1`)
  --   REFERENCES `Revision` (`revisionID`)
  --   ON UPDATE CASCADE ON DELETE CASCADE,
  -- FOREIGN KEY (`projectID2`)
  --   REFERENCES `Project` (`projectID`)
  --   ON UPDATE CASCADE ON DELETE CASCADE,
  -- FOREIGN KEY (`revisionID2`)
  --   REFERENCES `Revision` (`revisionID`)
  --   ON UPDATE CASCADE ON DELETE CASCADE
  FOREIGN KEY (`project1Name`)
  	REFERENCES `Project` (`projectName`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`revision1Name`)
  	REFERENCES `Revision` (`revisionName`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`project2Name`)
  	REFERENCES `Project` (`projectName`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`revision2Name`)
  	REFERENCES `Revision` (`revisionName`)
  	ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`detectorID`)
    REFERENCES `Detector` (`detectorID`)
    ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS `Evaluation` CASCADE;
CREATE TABLE IF NOT EXISTS `Evaluation`(
  `evaluationID` int NOT NULL AUTO_INCREMENT,
  `cloneID` int NOT NULL,
  `type` int DEFAULT NULL,
  `similarity` float DEFAULT NULL,
  `truePositive` Boolean DEFAULT NULL,
  `score` float DEFAULT NULL,

  PRIMARY KEY (`evaluationID`),
  INDEX index_evaluation (`evaluationID`),
  FOREIGN KEY (`cloneID`)
  	REFERENCES `CodeClone` (`cloneID`)
  	ON UPDATE CASCADE ON DELETE CASCADE
);
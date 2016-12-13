USE `code_clones`;

INSERT INTO `Project` VALUES
	(1, 'Code Clones', 'John', NULL),
	(2, 'Music App', 'John', NULL),
	(3, 'Fog Computing', 'John', NULL);

INSERT INTO `Revision` VALUES
	(1, 'Code Clones', 'P1-R1', 'John', NULL),
	(2, 'Code Clones', 'P1-R2', 'John', NULL),
	(3, 'Music App', 'P2-R1', 'John', NULL),
	(4, 'Music App', 'P2-R2', 'John', NULL),
	(5, 'Fog Computing', 'P3-R1', 'John', NULL);

INSERT INTO `Detector` VALUES
	(1, 'Detector1', '0x155117'),
	(2, 'Detector2', '0x125198'),
	(3, 'Detector3', '0x142516');

INSERT INTO `CodeClone` VALUES
	-- (1, 1, 1, 'P1-R1-F1', 1111, 1211, 1, 1, 'P1-R1-F2', 1112, 1212, 2),
	-- (2, 1, 1, 'P1-R1-F1', 2111, 2211, 1, 2, 'P1-R2-F1', 2121, 2221, 2),
	-- (3, 1, 1, 'P1-R1-F1', 3111, 3211, 2, 1, 'P2-R1-F1', 3211, 3311, 3);
	(1, 'Code Clones', 'P1-R1', 'P1-R1-F1', 11, 61,
		'Code Clones', 'P1-R1', 'P1-R1-F2', 12, 62, 1),
	(2, 'Code Clones', 'P1-R1', 'P1-R1-F1', 21, 71, 
		'Code Clones', 'P1-R2', 'P1-R2-F1', 21, 71, 2),
	(3, 'Code Clones', 'P1-R1', 'P1-R1-F1', 31, 81, 
		'Music App', 'P2-R1', 'P2-R1-F1', 32, 81, 3),
	(4, 'Music App', 'P2-R1', 'P2-R1-F1', 41, 91,
		'Fog Computing', 'P3-R1', 'P3-R1-F1', 13, 63, 1),
	(5, 'Fog Computing', 'P3-R1', 'P3-R1-F1', 25, 75,
		'Code Clones', 'P1-R1', 'P1-R1-F2', 33, 83, 2);
		
INSERT INTO `Evaluation` VALUES
	(1, 1, 2, 7.0, 1, 9.1),
	(2, 1, 2, 8.0, 1, 8.7),
	(3, 2, 3, 6.0, 0, 7.3);

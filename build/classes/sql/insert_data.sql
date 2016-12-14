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
	(5, 'Fog Computing', 'P3-R1', 'John', NULL),
	(6, 'Fog Computing', 'P3-R2', 'John', NULL);

INSERT INTO `File` VALUES
	('P1-R1-F1', 'Code Clones', 'P1-R1', 'print(a)\na=b\nc=d\x=g'),
	('P1-R1-F2', 'Code Clones', 'P1-R1', 'printf("\n")'),
	('P1-R2-F1', 'Code Clones', 'P1-R2', 'for'),
	('P1-R2-F2', 'Code Clones', 'P1-R2', 'while'),
	('P2-R1-F1', 'Music App', 'P2-R1', 'if'),
	('P2-R2-F1', 'Music App', 'P2-R2', 'try'),
	('P3-R1-F1', 'Fog Computing', 'P3-R1', 'switch'),
	('P3-R2-F1', 'Fog Computing', 'P3-R2', 'pass'),
	('P2-R2-F2', 'Music App', 'P2-R2', 'exit');

-- INSERT INTO `Detector` VALUES
-- 	(1, 'D1', '0x155117'),
-- 	(2, 'D2', '0x125198'),
-- 	(3, 'D3', '0x142516');

INSERT INTO `CodeClone` VALUES
	(1, 'Code Clones', 'P1-R1', 'P1-R1-F1', 11, 61,
		'Code Clones', 'P1-R1', 'P1-R1-F2', 12, 62, 'D1', '0x155117'),
	(2, 'Code Clones', 'P1-R1', 'P1-R1-F1', 21, 71, 
		'Code Clones', 'P1-R1', 'P1-R1-F1', 21, 71, 'D2', '0x125198'),
	(3, 'Code Clones', 'P1-R1', 'P1-R1-F1', 31, 81, 
		'Music App', 'P2-R1', 'P2-R1-F1', 32, 81, 'D1', '0x155117'),
	(4, 'Music App', 'P2-R1', 'P2-R1-F1', 41, 91,
		'Fog Computing', 'P3-R1', 'P3-R1-F1', 13, 63, 'D2', '0x125198'),
	(5, 'Fog Computing', 'P3-R1', 'P3-R1-F1', 25, 75,
		'Code Clones', 'P1-R1', 'P1-R1-F2', 33, 83, 'D1', '0x155117'),
	(6, 'Music App', 'P2-R2', 'P2-R2-F1', 14, 78,
		'Code Clones', 'P1-R2', 'P1-R2-F2', 23, 98, 'D3', '0x142516'),
	(7, 'Fog Computing', 'P3-R2', 'P3-R2-F1', 25, 63,
		'Music App', 'P2-R2', 'P2-R2-F2', 54, 81, 'D3', '0x142516');
		
INSERT INTO `Evaluation` VALUES
	(1, 1, 2, 7.0, 1, 9.1),
	(2, 1, 2, 8.0, 1, 8.7),
	(3, 2, 3, 6.0, 0, 7.3);

INSERT INTO `User` VALUES
	('admin', 'admin', 'Administrator'),
	('evaluator', 'evaluator', 'Evaluator'),
	('contributor', 'contributor', 'Contributor');
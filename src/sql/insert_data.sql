USE `code_clones`;

INSERT INTO `Project` VALUES
	(1, 'Code Clones'),
	(2, 'Music App'),
	(3, 'Fog Computing');

INSERT INTO `Revision` VALUES
	(1, 1, 'P1-R1'),
	(2, 1, 'P1-R2'),
	(3, 2, 'P2-R1'),
	(4, 2, 'P2-R2'),
	(5, 3, 'P3-R1'),
	(6, 3, 'P3-R2');

INSERT INTO `Detector` VALUES
 	(1, 'D1', '0x155117'),
 	(2, 'D2', '0x125198'),
 	(3, 'D3', '0x142516');
 	
INSERT INTO `Fragment` VALUES 
	(1, 1, 1, 'P1-R1-F1', 11, 61),
	(2, 1, 1, 'P1-R1-F2', 12, 77),
	(3, 1, 2, 'P1-R2-F1', 1, 54),
	(4, 2, 3, 'P2-R1-F1', 32, 81),
	(5, 2, 4, 'P2-R2-F1', 3, 51),
	(6, 3, 5, 'P3-R1-F1', 13, 91),
	(7, 3, 6, 'P3-R2-F1', 21, 55),
	(8, 3, 6, 'P3-R2-F2', 32, 87);
	
INSERT INTO `CodeClone` VALUES
	(1, 1, 2, 1),
	(2, 3, 4, 1),
	(3, 5, 6, 2),
	(4, 7, 8, 3);
		
INSERT INTO `Evaluation` VALUES
	(1, 1, 2, 7.0, TRUE, 9.1),
	(2, 1, 2, 8.0, TRUE, 8.7),
	(3, 2, 3, 6.0, FALSE, 7.3),
	(4, 3, 3, 8.0, FALSE, 8.6),
	(5, 4, 4, 6.0, FALSE, 7.9),
	(6, 4, 3, 9.0, FALSE, 9.3);

INSERT INTO `WebUser` VALUES
	(1, 'admin', 'admin', 'Administrator'),
	(2, 'evaluator', 'evaluator', 'Evaluator'),
	(3, 'contributor', 'contributor', 'Contributor');
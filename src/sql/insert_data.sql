use `code_clones`;

insert into `Project` values
	(1, 'Code Clones Database System', 'John', NULL),
	(2, 'Music Online Streaming App', 'John', NULL),
	(3, 'Fog Computing Survey', 'John', NULL);

insert into `Revision` values
	(1, 1, 'P1-R1', 'John', NULL),
	(2, 1, 'P1-R2', 'John', NULL),
	(3, 2, 'P2-R1', 'John', NULL),
	(4, 2, 'P2-R1', 'John', NULL),
	(5, 3, 'P3-R1', 'John', NULL);

insert into `CodeClone` values
	(1, 1, 1, 'P1-R1-F1', 1111, 1211, 1, 1, 'P1-R1-F2', 1112, 1212, 2),
	(2, 1, 1, 'P1-R1-F1', 2111, 2211, 1, 2, 'P1-R2-F1', 2121, 2221, 2),
	(3, 1, 1, 'P1-R1-F1', 3111, 3211, 2, 1, 'P2-R1-F1', 3211, 3311, 3);
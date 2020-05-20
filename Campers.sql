DROP TABLE Campers;

CREATE TABLE Campers(
	ID NUMBER
	,firstName VARCHAR2(20)
	,lastName VARCHAR2(20)
	,nickName VARCHAR2(20)
	,CampStoreBudget FLOAT
	,CampStoreSpent FLOAT
	,revNum NUMBER(8) DEFAULT 1
	PRIMARY KEY (ID)
);
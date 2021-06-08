CREATE TABLE VOIVODESHIPS
(
	ID 		NUMBER(5) 	NOT NULL,
	NAME	VARCHAR(30) NOT NULL UNIQUE,
	ACTIVE	CHAR(1) 	DEFAULT 1,
	CONSTRAINT VOIVODESHIP_PK PRIMARY KEY(ID)
);

CREATE TABLE CITIES
(
	ID 				NUMBER(5) 		NOT NULL,
	NAME			VARCHAR2(30) 	NOT NULL,
	FK_VOIVODESHIP 	NUMBER(5)		NOT NULL
		CONSTRAINT VOIVODESHIP_FK REFERENCES VOIVODESHIPS,
	CONSTRAINT CITY_PK PRIMARY KEY(ID)
);

CREATE TABLE CLIENTS
(
	ID 				NUMBER(5) 		NOT NULL,
	SURNAME 		VARCHAR2(30) 	NOT NULL,
	NAME			VARCHAR2(20) 	NOT NULL,
	PERSONAL_ID 	NUMBER(11) 		NOT NULL UNIQUE,
	BIRTH_DATE 		DATE,
	EMPLOYMENT_DATE	DATE			DEFAULT CURRENT_DATE,
	SALARY			DECIMAL			DEFAULT 0 CHECK (SALARY >= 0),
	YEAR_SALARY		DECIMAL			AS (SALARY * 12),
	STREET			VARCHAR2(30),	
	HOUSE_NUMBER	NUMBER(5),
	APARTMENTS		INTEGER,
	FK_CITY			NUMBER(5)		NOT NULL
		CONSTRAINT CITY_FK REFERENCES CITIES,
	AGE 			INTEGER 		AS ((TO_DATE('20210520','YYYYMMDD') - BIRTH_DATE)/365.25) null CHECK(AGE > 18),
	CONSTRAINT CLIENT_PK PRIMARY KEY(ID)
);

CREATE SEQUENCE VOIVODESHIPS_SEQ 	MINVALUE 1 START WITH 1 NOMAXVALUE INCREMENT BY 1 NOCACHE NOORDER;
CREATE SEQUENCE CITIES_SEQ 			MINVALUE 1 START WITH 1 NOMAXVALUE INCREMENT BY 1 NOCACHE NOORDER;
CREATE SEQUENCE CLIENTS_SEQ 		MINVALUE 1 START WITH 1 NOMAXVALUE INCREMENT BY 1 NOCACHE NOORDER;

INSERT INTO VOIVODESHIPS(ID, NAME) VALUES (VOIVODESHIPS_SEQ.NEXTVAL, 'Łódzkie');
INSERT INTO VOIVODESHIPS(ID, NAME) VALUES (VOIVODESHIPS_SEQ.NEXTVAL, 'Pomorskie');
INSERT INTO VOIVODESHIPS(ID, NAME) VALUES (VOIVODESHIPS_SEQ.NEXTVAL, 'Mazowieckie');

INSERT INTO CITIES(ID, NAME, FK_VOIVODESHIP) VALUES (CITIES_SEQ.NEXTVAL, 'Łódź', (SELECT ID FROM VOIVODESHIPS WHERE NAME='Łódzkie'));
INSERT INTO CITIES(ID, NAME, FK_VOIVODESHIP) VALUES (CITIES_SEQ.NEXTVAL, 'Gdynia', (SELECT ID FROM VOIVODESHIPS WHERE NAME='Pomorskie'));
INSERT INTO CITIES(ID, NAME, FK_VOIVODESHIP) VALUES (CITIES_SEQ.NEXTVAL, 'Warszawa', (SELECT ID FROM VOIVODESHIPS WHERE NAME='Mazowieckie'));

INSERT INTO CLIENTS(ID, SURNAME, NAME, PERSONAL_ID, BIRTH_DATE, SALARY, STREET, HOUSE_NUMBER, APARTMENTS, FK_CITY) VALUES (CLIENTS_SEQ.NEXTVAL, 'Skrzypczyk', 'Mateusz', 0000000000000, TO_DATE('1997-04-06','YYYY-MM-DD'), 20000, 'XyZ', 24, 2, (SELECT ID FROM CITIES WHERE NAME='Łódź'));
INSERT INTO CLIENTS(ID, SURNAME, NAME, PERSONAL_ID, BIRTH_DATE, SALARY, STREET, HOUSE_NUMBER, APARTMENTS, FK_CITY) VALUES (CLIENTS_SEQ.NEXTVAL, 'Test', 'Test', 0000000000001, TO_DATE('1997-04-07','YYYY-MM-DD'), 10000, 'Pkd', 23, 2, (SELECT ID FROM CITIES WHERE NAME='Gdynia'));
INSERT INTO CLIENTS(ID, SURNAME, NAME, PERSONAL_ID, BIRTH_DATE, SALARY, STREET, HOUSE_NUMBER, APARTMENTS, FK_CITY) VALUES (CLIENTS_SEQ.NEXTVAL, 'Test2', 'Testt2', 0000000000002, TO_DATE('1997-04-08','YYYY-MM-DD'), 50000, 'QWE', 22, 2, (SELECT ID FROM CITIES WHERE NAME='Warszawa'));

-- 1
ALTER TABLE VOIVODESHIPS ADD COUNTRY VARCHAR2(20);

-- 2
UPDATE VOIVODESHIPS SET COUNTRY = 'Polska' WHERE COUNTRY IS NULL;
ALTER TABLE VOIVODESHIPS MODIFY (COUNTRY DEFAULT 'Polska' NOT NULL);

-- 3
ALTER TABLE VOIVODESHIPS MODIFY(COUNTRY VARCHAR2(5));

-- 4
ALTER TABLE VOIVODESHIPS MODIFY(COUNTRY VARCHAR2(35));

-- 5
ALTER TABLE VOIVODESHIPS RENAME COLUMN ACTIVE TO Activee;
ALTER TABLE VOIVODESHIPS RENAME COLUMN Activee TO ACTIVE;

-- 6
ALTER TABLE CLIENTS ADD CONSTRAINT ADULT CHECK (AGE > 18);
ALTER TABLE CLIENTS DISABLE CONSTRAINT ADULT;

-- 7
COMMENT ON COLUMN CITIES.ID IS 'City ID';
COMMENT ON COLUMN CITIES.NAME IS 'City name';
COMMENT ON COLUMN CITIES.FK_VOIVODESHIP IS 'Voivodeship ID';

-- 8
ALTER TABLE CITIES DROP CONSTRAINT VOIVODESHIP_FK;
ALTER TABLE CITIES ADD CONSTRAINT VOIVODESHIP_FK FOREIGN KEY (FK_VOIVODESHIP) REFERENCES VOIVODESHIPS(ID) ON DELETE CASCADE;

ALTER TABLE CLIENTS DROP CONSTRAINT CITY_FK;
ALTER TABLE CLIENTS ADD CONSTRAINT CITY_FK FOREIGN KEY (FK_CITY) REFERENCES CITIES(ID) ON DELETE CASCADE;

DELETE FROM VOIVODESHIPS WHERE NAME = 'Pomorskie';
SELECT * FROM VOIVODESHIPS;
SELECT * FROM CITIES;
SELECT * FROM CLIENTS;

-- 9
CREATE VIEW CLIENTS_VIEW AS
SELECT cl.SURNAME AS "Surname", cl.AGE AS "Age", cl.YEAR_SALARY AS "Year salary", ci.NAME AS "City name", v.NAME AS "Voivodeship name"
FROM CLIENTS cl, CITIES ci, VOIVODESHIPS v
WHERE cl.FK_CITY = ci.ID AND ci.FK_VOIVODESHIP = v.ID;

SELECT * FROM CLIENTS_VIEW;

-- 10
SELECT * FROM ALL_TABLES;
SELECT * FROM ALL_VIEWS;

-- 11
ALTER TABLE CLIENTS RENAME TO CLIENTS1;









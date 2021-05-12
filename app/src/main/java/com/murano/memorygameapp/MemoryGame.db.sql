BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "User" (
	"Username"	TEXT NOT NULL,
	"Password"	NUMERIC NOT NULL,
	PRIMARY KEY("Username")
);
CREATE TABLE IF NOT EXISTS "Levels" (
	"Lev_1"	NUMERIC,
	"Lev_2"	NUMERIC,
	"Lev_3"	NUMERIC,
	"Lev_4"	NUMERIC,
	"Lev_5"	NUMERIC,
	"Lev_6"	NUMERIC
);
COMMIT;
-- Database: AubergeInn

CREATE TABLE Clients
(
	id INTEGER NOT NULL,
	prenom VARCHAR(50) NOT NULL,
	nom VARCHAR(50) NOT NULL,
	age INTEGER NOT NULL,
	CONSTRAINT "Clients_cc0" PRIMARY KEY (id),
	CONSTRAINT "Clients_cc1" UNIQUE (id),
	CONSTRAINT "Clients_cc2" CHECK (age >= 0 AND age <= 140)
);


CREATE TABLE Chambres
(
	id INTEGER NOT NULL,
	nom VARCHAR(50) NOT NULL,
	type_lit VARCHAR(50) NOT NULL,
	prix DECIMAL(12,2) NOT NULL,
	CONSTRAINT "Chambres_cc0" PRIMARY KEY (id),
	CONSTRAINT "Chambres_cc1" UNIQUE (id),
	CONSTRAINT "Chambres_cc2" CHECK (prix >= 0.00)
);


CREATE TABLE Commodites
(
	id INTEGER NOT NULL,
	description VARCHAR(255) NOT NULL,
	prix DECIMAL(12,2) NOT NULL,
	CONSTRAINT "Commodites_cc0" PRIMARY KEY (id),
	CONSTRAINT "Commodites_cc1" UNIQUE (id),
	CONSTRAINT "Commodites_cc2" CHECK (prix >= 0.00)
);


CREATE TABLE CommoditesChambres
(
	commodite_Id INTEGER NOT NULL,
	chambre_Id INTEGER NOT NULL,
	CONSTRAINT "CommoditesChambres_cc0" PRIMARY KEY (commodite_Id, chambre_Id),
	CONSTRAINT "CommoditesChambres_cr0" FOREIGN KEY (commodite_Id)
		REFERENCES Commodites(id),
	CONSTRAINT "CommoditesChambres_cr1" FOREIGN KEY (chambre_Id)
		REFERENCES Chambres(id)
);


CREATE TABLE Reservations
(
	client_Id INTEGER NOT NULL,
	chambre_Id INTEGER NOT NULL,
	date_Debut DATE NOT NULL,
	date_Fin DATE NOT NULL,
	--prix_Total DECIMAL(12,2) NOT NULL,
	CONSTRAINT "Reservations_cc0" PRIMARY KEY (client_Id, chambre_Id, date_Debut),
	CONSTRAINT "Reservations_cc1" CHECK (date_Fin >= date_Debut),
	--CONSTRAINT "Reservations_cc2" CHECK (prix_Total >= 0.00),
	CONSTRAINT "Reservations_cr0" FOREIGN KEY (client_Id)
		REFERENCES Clients(id),
	CONSTRAINT "Reservations_cr1" FOREIGN KEY (chambre_Id)
		REFERENCES Chambres(id)
	);
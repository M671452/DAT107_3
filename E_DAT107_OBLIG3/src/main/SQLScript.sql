-- Lager tabeller:
CREATE SCHEMA oblig3;

CREATE TABLE oblig3.Prosjekt
(
ProsjektId SERIAL PRIMARY KEY,
ProsjektNavn VARCHAR(50) NOT NULL,
Beskrivelse VARCHAR(255)
);

CREATE TABLE oblig3.Avdeling
(
AvdelingId SERIAL PRIMARY KEY,
AvdNavn VARCHAR(50) NOT NULL,
SjefId INT NOT NULL,
CONSTRAINT FK_AVD_SJEF FOREIGN KEY (SjefId) REFERENCES oblig3.Ansatt (AnsattId) ON DELETE RESTRICT
);

CREATE TABLE oblig3.Ansatt 
(
AnsattId SERIAL PRIMARY KEY,
Brukernavn VARCHAR(4) UNIQUE,
Fornavn VARCHAR(25),
Etternavn VARCHAR(25),
AnsattDato DATE,
Stilling VARCHAR(25),
MndLoenn INT,
AnsattAvd INT NOT NULL,
CONSTRAINT FK_AVD FOREIGN KEY(AnsattAvd)
REFERENCES Avdeling(AvdelingId)
ON DELETE RESTRICT
);

CREATE TABLE oblig3.KoblingAnsPro
(
AnsattId INT,
ProsjektId INT,
Rolle VARCHAR(50),
AntTimer INT,
CONSTRAINT PK_ANSPRO PRIMARY KEY (AnsattId, ProsjektId),
CONSTRAINT FK_ANSPRO_ANS FOREIGN KEY (AnsattId)
REFERENCES Ansatt(AnsattId)
ON DELETE RESTRICT,
CONSTRAINT FK_ANSPRO_PRO FOREIGN KEY (ProsjektId)
REFERENCES Prosjekt(ProsjektId)
ON DELETE RESTRICT
);

-- Legger til fremmednøkkel:
ALTER TABLE oblig3.Avdeling ADD CONSTRAINT FK_SJEF FOREIGN KEY (SjefId)
REFERENCES oblig3.Ansatt (AnsattId) ON DELETE RESTRICT;

-- Legger inn testdata:
INSERT INTO OBLIG3.Ansatt (Brukernavn, Fornavn, Etternavn, AnsattDato, Stilling, MndLoenn, AnsattAvd)
VALUES 
('jode', 'John', 'Doe', '2010-01-10', 'Biolog', 55000, 1),
('cosc', 'Cosco', 'Colo', '2021-01-01', 'Elektro-ingeniør', 55000, 2),
('vile', 'Victoria', 'Lee', '2009-02-15', 'Sjef', 80000, 1),
('jasm', 'Jane', 'Smith', '2012-02-15', 'Sjef', 80000, 3),
('sala', 'Salomon', 'Lars', '2024-01-01', 'Sjef', 85000, 2);

INSERT INTO OBLIG3.Avdeling (AvdNavn, SjefId)
VALUES ('Forskning', 3),('Teknologi', 4), ('Salg', 5);

INSERT INTO OBLIG3.Prosjekt (ProsjektNavn, Beskrivelse)
VALUES 
('Robotfrosker for miljøet',
'Vi har gått igjennom ulike froskearter for å finne frosken som er best egnet til å bli til en robot.
Dette kan hjelpe miljøet på en eller annen måte.'),
('Robotiske tektoniske plater',
'Platene under jorda beveger seg uten at vi kan kontrollere dem.
Finnes det måter å løse dette på?');

INSERT INTO OBLIG3.KoblingAnsPro (AnsattId, ProsjektId, Rolle, AntTimer)
VALUES 
(1, 1, 'Biolog', 40),
(2, 2, 'Elektro-ingeniør', 30);
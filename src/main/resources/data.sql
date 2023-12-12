-- Creación de la tabla Segurotb
CREATE TABLE Segurotb (
  id_seguro INT NOT NULL,
  empresa VARCHAR(200),
  fecha_alta DATE,
  fecha_baja DATE
);

-- Creación de la tabla Vehiculotb
CREATE TABLE Vehiculotb (
  id_vehiculo INT NOT NULL,
  id_seguro INT,
  marca VARCHAR(200),
  color VARCHAR(200)
);

-- Creación de la tabla Usuariotb
CREATE TABLE Usuariotb (
  id_usuario INT NOT NULL,
  id_seguro INT,
  nombre VARCHAR(200),
  apellido VARCHAR(200),
  dni VARCHAR(14)
);

-- Creación de la tabla Contactotb
CREATE TABLE Contactotb (
  id_contacto INT NOT NULL,
  id_usuario INT,
  num_telefono INT
);

-- Creación de la tabla 
CREATE TABLE TipoContactotb (
  id_tipo_contacto INT NOT NULL,
  id_contacto INT,
  descripcion VARCHAR(200)
);


-- Seguro
ALTER TABLE Segurotb 
ADD PRIMARY KEY (id_seguro);

-- Vehiculo
ALTER TABLE Vehiculotb 
ADD PRIMARY KEY (id_vehiculo);

ALTER TABLE Vehiculotb
ADD CONSTRAINT FK_Vehiculo_Seguro
FOREIGN KEY (id_seguro) REFERENCES Segurotb(id_seguro);

-- Usuario
ALTER TABLE Usuariotb 
ADD PRIMARY KEY (id_usuario);

ALTER TABLE Usuariotb
ADD CONSTRAINT FK_Usuario_Seguro
FOREIGN KEY (id_seguro) REFERENCES Segurotb(id_seguro);

-- Contacto
ALTER TABLE Contactotb 
ADD PRIMARY KEY (id_contacto);

ALTER TABLE Contactotb
ADD CONSTRAINT FK_Contacto_Usuario
FOREIGN KEY (id_usuario) REFERENCES Usuariotb(id_usuario);

-- Contacto
ALTER TABLE TipoContactotb 
ADD PRIMARY KEY (id_tipo_contacto);

ALTER TABLE TipoContactotb
ADD CONSTRAINT FK_TipoContacto_Contacto
FOREIGN KEY (id_contacto) REFERENCES Contactotb(id_contacto);

-- Inserción de datos en la tabla Segurotb
INSERT INTO Segurotb (id_seguro, empresa, fecha_alta, fecha_baja)
VALUES
  (1, 'Seguro A', '2022-01-01', '2023-01-01'),
  (2, 'Seguro B', '2022-02-01', '2023-02-01'),
  (3, 'Seguro C', '2022-03-01', '2023-03-01');

-- Inserción de datos en la tabla Vehiculotb
INSERT INTO Vehiculotb (id_vehiculo, id_seguro, marca, color)
VALUES
  (1, 1, 'Toyota', 'Rojo'),
  (2, 1, 'Honda', 'Azul'),
  (3, 2, 'Ford', 'Verde'),
  (4, 2, 'Chevrolet', 'Negro'),
  (5, 2, 'Volkswagen', 'Blanco'),
  (6, 3, 'BMW', 'Gris');

-- Inserción de datos en la tabla Usuariotb
INSERT INTO Usuariotb (id_usuario, id_seguro, nombre, apellido, dni)
VALUES
  (1, 1, 'Gordon', 'Freeman', '12345678A'),
  (2, 1, 'Alyx', 'Vance', '87654321B'),
  (3, 2, 'Barney', 'Calhoun', '54321678C'),
  (4, 3, 'Eli', 'Vance', '87651234D'),
  (5, 3, 'Isaac', 'Kleiner', '43218765E');

  -- Inserción de datos en la tabla Contactotb
INSERT INTO Contactotb (id_contacto, id_usuario, num_telefono)
VALUES
  (1, 1, 123456789),
  (2, 1, 987654321),
  (3, 2, 555555555),
  (4, 3, 111111111),
  (5, 3, 999999999);

INSERT INTO TipoContactotb (id_tipo_contacto, id_contacto, descripcion)
VALUES
  (1, 1, 'Teléfono'),
  (2, 1, 'Movil'),
  (3, 2, 'Fijo'),
  (4, 2, 'Satelite');

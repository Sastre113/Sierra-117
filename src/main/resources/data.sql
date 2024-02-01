CREATE TABLE Usuarios (
    nif VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    primer_apellido VARCHAR(100),
    segundo_apellido VARCHAR(100),
    fecha_nacimiento DATE
);

CREATE TABLE Roles (
    id_rol VARCHAR(36) PRIMARY KEY,
    rol_nombre VARCHAR(50),
    activo NUMERIC(1,0)
);

CREATE TABLE Permisos (
    id_permiso VARCHAR(36) PRIMARY KEY,
    permiso_nombre VARCHAR(50)
);


CREATE TABLE Usuarios_Roles (
    nif VARCHAR(20),
    id_rol VARCHAR(36),
    PRIMARY KEY (nif, id_rol),
    FOREIGN KEY (nif) REFERENCES Usuarios(nif),
    FOREIGN KEY (id_rol) REFERENCES Roles(id_rol)
);


CREATE TABLE Roles_Permisos (
    id_rol VARCHAR(20),
    id_permiso VARCHAR(36),
    PRIMARY KEY (id_rol, id_permiso),
    FOREIGN KEY (id_rol) REFERENCES Roles(id_rol),
    FOREIGN KEY (id_permiso) REFERENCES Permisos(id_permiso)
);

-- Insertar datos en Usuarios
INSERT INTO Usuarios (nif, nombre, primer_apellido, segundo_apellido, fecha_nacimiento)
VALUES
    ('12345678A', 'Gordon', 'Freeman', '', '1980-07-05'),
    ('87654321B', 'Alyx', 'Vance', '', '1988-02-20'),
    ('98765432C', 'Barney', 'Calhoun', '', '1975-09-01');

-- Insertar datos en Roles
INSERT INTO Roles (id_rol, rol_nombre, activo)
VALUES
    ('1', 'Administrador', 1),
    ('2', 'Usuario Estándar', 1),
    ('3', 'Invitado', 1);

-- Insertar datos en Permisos
INSERT INTO Permisos (id_permiso, permiso_nombre)
VALUES
    ('001', 'Leer'),
    ('002', 'Escribir'),
    ('003', 'Eliminar');

-- Insertar datos en Usuarios_Roles
INSERT INTO Usuarios_Roles (nif, id_rol)
VALUES
    ('12345678A', '1'),
    ('87654321B', '2'),
    ('98765432C', '3');

-- Insertar datos en Roles_Permisos
INSERT INTO Roles_Permisos (id_rol, id_permiso)
VALUES
    ('1', '001'),
    ('2', '001'),
    ('2', '002'),
    ('3', '001');
    
-- Insertar más datos en Usuarios
INSERT INTO Usuarios (nif, nombre, primer_apellido, segundo_apellido, fecha_nacimiento)
VALUES
    ('23456789D', 'Isaac', 'Kleiner', '', '1958-03-07'),
    ('34567890E', 'Eli', 'Vance', '', '1955-12-15'),
    ('45678901F', 'Alex', 'Mercer', '', '1970-06-30');

-- Insertar más datos en Usuarios_Roles
INSERT INTO Usuarios_Roles (nif, id_rol)
VALUES
    ('23456789D', '2'),
    ('34567890E', '2'),
    ('45678901F', '3');

-- Insertar más datos en Roles_Permisos
INSERT INTO Roles_Permisos (id_rol, id_permiso)
VALUES ('3', '003');

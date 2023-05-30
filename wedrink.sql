SET GLOBAL time_zone = '-3:00';
drop database if exists wedrink;
create database wedrink;

use wedrink;
-- Crear la tabla ClienteDiscotecaConPuntos
CREATE TABLE ClienteDiscotecaConPuntos (
  cliente_id INT,
  puntos INT,
  FOREIGN KEY (cliente_id) REFERENCES ClienteDiscoteca(id)
);

-- Crear la tabla ClienteDiscoteca
CREATE TABLE ClienteDiscoteca (
  id INT PRIMARY KEY AUTO_INCREMENT,
  puntosAcumulados INT,
  discoteca_id INT,
  FOREIGN KEY (discoteca_id) REFERENCES Discoteca(id)
  
);
ALTER TABLE ClienteDiscoteca
ADD FOREIGN KEY (id) REFERENCES Usuario(id);
-- Crear la tabla Premio
CREATE TABLE Premio (
  id INT PRIMARY KEY AUTO_INCREMENT,
  marca VARCHAR(100),
  nombre VARCHAR(100),
  descripcion VARCHAR(200),
  puntosNecesarios INT
);
insert into premio(marca,nombre,descripcion,puntosNecesarios) values ("Beefeeter","Premio Principiante","Copa de tubo de Beefeeter","100");
insert into premio(marca,nombre,descripcion,puntosNecesarios) values ("Almirante","Premio Principiante","Copa de tubo de Almirante","100");
insert into premio(marca,nombre,descripcion,puntosNecesarios) values ("Champan Dior","Premio Olimpo","Champan Olimpo premium","1500");

 

-- Crear la tabla Producto
CREATE TABLE Producto (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100),
  descripcion VARCHAR(200),
  puntosPorCompra INT,
  precioProducto DECIMAL(10, 2)
);
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto) values ("Beefeeter","Alcohol ginebra","20","10");
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto) values ("Brugal","Alcohol ron","20","10");
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto) values ("Red Label","Alcohol wishky","25","15");
ALTER TABLE producto ADD imagenUrl VARCHAR(255);
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto,ImagenUrl) values ("Brugal","ron barato resacosos","20","10","https://www.divinodrinks.com/834-large_default/ron-brugal-anejo.jpg");
-- Crear la tabla Evento
CREATE TABLE Evento (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100),
  localidad VARCHAR(100),
  descripcion VARCHAR(200),
  direccion VARCHAR(200),
  fecha DATE,
  puntosPorAsistir INT,
  precioEvento DECIMAL(10, 2)
);

insert into evento (nombre,localidad,descripcion,direccion,fecha,puntosPorAsistir,precioEvento) values("San Juan festival","Malaga","Festival de musica urbana para la celebracion de San Juan","los santos 13","24-06-23","100","20");
insert into evento (nombre,localidad,descripcion,direccion,fecha,puntosPorAsistir,precioEvento) values("El Row","Malaga","Festival de musica tecno","acacias 3","5-06-23","200","40");
insert into evento (nombre,localidad,descripcion,direccion,fecha,puntosPorAsistir,precioEvento) values("Los Alamos beach festival","Malaga","festival musica en la playa de los alamos en malaga
","los alamos 32","06-07-23","100","20");

-- Crear la tabla Discoteca
CREATE TABLE Discoteca (
  id INT PRIMARY KEY AUTO_INCREMENT,
  cif VARCHAR(100),
  nombre VARCHAR(100),
  localidad VARCHAR(100),
  direccion VARCHAR(200),
  descripcion VARCHAR(200),
  pais VARCHAR(100),
  telefono VARCHAR(20)
);

-- Crear la tabla Usuario
CREATE TABLE Usuario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nick VARCHAR(100),
  nombre VARCHAR(100),
  correo VARCHAR(200),
  contraseña VARCHAR (100),
  localidad VARCHAR(100),
  direccion VARCHAR(200),
  qrUsuario VARCHAR(100),
  apellidos VARCHAR(100)
);
insert into user values("prueba@prueba.es","123123","Prueba");
insert into usuario (nick,nombre,correo,contraseña,localidad,direccion,qrUsuario,apellidos) values ("Miguel_Granada","Miguel","miguelcenec@gmail.com","1234","Malaga","granada 1","qr-2","Paramos");
SELECT * FROM usuario;

CREATE TABLE wedrink.compras (
  id INT AUTO_INCREMENT PRIMARY KEY,
  usuario_nick VARCHAR(255),
  producto_nombre VARCHAR(255),
  fecha DATE,
  FOREIGN KEY (usuario_nick) REFERENCES wedrink.usuario(nick),
  FOREIGN KEY (producto_nombre) REFERENCES wedrink.producto(nombre)
);
ALTER TABLE WeDrink.usuario ADD INDEX idx_usuarios_nick (nick);
ALTER TABLE WeDrink.producto ADD INDEX idx_producto_nombre (nombre);
ALTER TABLE usuario ADD INDEX idx_usuario_nick (nick);
SET GLOBAL time_zone = '-3:00';
drop database if exists wedrink;
create database wedrink;
DROP TABLE clientediscotecaconpuntos;
DROP TABLE ClienteDiscoteca;

CREATE TABLE ClienteDiscoteca (
  id_cliente INT,
  puntosAcumulados INT,
  discoteca_id INT,
  FOREIGN KEY (discoteca_id) REFERENCES Discoteca(id),
  FOREIGN KEY (id_cliente) REFERENCES Usuario(id)
);
CREATE TABLE LOGROS (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(200),
imagenlogro VARCHAR(255),
descripcion VARCHAR(200),
puntos INT,
id_producto INT,
id_cliente INT,
FOREIGN KEY (id_producto) REFERENCES producto(id),
FOREIGN KEY (id_cliente) REFERENCES usuariopuntos(usuario_id)
);
ALTER TABLE logros ADD logrocompletado VARCHAR(5);
insert into logros(nombre,imagenlogro,descripcion,puntos,id_producto,id_cliente,logroCompletado) values ("BICHUPITO","https://www.divinodrinks.com/834-large_default/ron-brugal-anejo.jpg","dos chupitos en la misma noche","10","9","2","true");

insert into logros(nombre,imagenlogro,descripcion,puntos,id_producto,id_cliente,logroCompletado) values ("HACTRICK GINEBRINOS","https://media-cdn.tripadvisor.com/media/photo-s/06/d6/84/e8/vinos-de-bellota.jpg","3 copas","30","8","2","true");

insert into logros(nombre,imagenlogro,descripcion,puntos,id_producto,id_cliente,logroCompletado) values ("BIG CUBATA","https://cadena100-cdnmed.agilecontent.com/resources/jpg/9/9/1623830522299.jpg","cubata cubalitro para todos","12","6","6","true");

DELETE FROM logros WHERE id = "6";


select * from logros;

insert into clientediscoteca values ("1","80","1");
insert into clientediscoteca values ("1","20","2");
insert into clientediscoteca values ("2","50","1");
insert into clientediscoteca values ("3","200","3");

SELECT * FROM PRODUCTO;
SELECT * FROM USUARIO;

CREATE TABLE UsuarioPuntos (
  usuario_id INT,
  puntos INT,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

SELECT * FROM USUARIO;

INSERT INTO usuariopuntos values("8","0");

DROP TABLE PREMIO;

CREATE TABLE Premio (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100),
  marca VARCHAR(100),
  descripcion VARCHAR(200),
  puntosNecesarios INT,
	Disponible VARCHAR(5),
  imagenUrl VARCHAR(250),
	idDiscoteca INT,
  FOREIGN KEY (idDiscoteca) REFERENCES Discoteca(id)
);

insert into premio(nombre,marca,descripcion,puntosNecesarios,Disponible,imagenUrl,idDiscoteca) values ("COPA VASO ANCHO","PRIMERAS MARCAS","COPA DE VASO ANCHO DE TU ALCOHOL FAVORITO","100","true","https://www.47cocktailbar.es/wp-content/uploads/2019/11/gin-tonic-hibiscus-new-400x509.jpg",null);
insert into premio(nombre,marca,descripcion,puntosNecesarios,Disponible,imagenUrl,idDiscoteca) values ("2 COPAS VASO ANCHO","PRIMERAS MARCAS","2 COPAS DE VASO ANCHO DE TU ALCOHOL FAVORITO","200","true","https://s2.abcstatics.com/media/espana/2016/12/09/copas-discoteca-madrid-ktQ--620x349@abc.jpg","1");
insert into premio(nombre,marca,descripcion,puntosNecesarios,Disponible,imagenUrl,idDiscoteca) values ("RESERVADO VIP 4 PERSONAS","VIP","RESERVADO VIP PARA 4 PERSONAS","5000","true","https://w7.pngwing.com/pngs/166/759/png-transparent-ticket-very-important-person-concert-music-festival-others.png","3");
INSERT INTO premio (nombre, marca, descripcion, puntosNecesarios, Disponible, imagenUrl, idDiscoteca)VALUES ("BOTELLA CHAMPAGNE MOET", "MOET", "BOTELLA DEL CHAMPAGNE MOET", 800, "true", "https://lh3.googleusercontent.com/p/AF1QipOdy59gO-k2MUsrDSyGW8VILg1o8bXwZbLLwwkP=w768-h768-n-o-v1", 2);

SELECT * FROM premio;

DELETE FROM Premio WHERE id=6;

-- Crear la tabla Producto
CREATE TABLE Producto (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100),
  descripcion VARCHAR(200),
  puntosPorCompra INT,
  precioProducto DECIMAL(10, 2),
  imagenUrl VARCHAR(250)
);

UPDATE Producto
SET imagenUrl = 'https://www.divinodrinks.com/728-large_default/gin-beefeater.jpg'
WHERE id = 6;

DELETE FROM Producto WHERE id = '1';
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto) values ("Beefeeter","Alcohol ginebra","20","10");
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto) values ("Brugal","Alcohol ron","20","10");
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto) values ("Red Label","Alcohol wishkyDD20""15");
ALTER TABLE producto ADD imagenUrl VARCHAR(255);
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto,ImagenUrl) values ("Brugal","Alcohol RON","20","10","https://www.divinodrinks.com/834-large_default/ron-brugal-anejo.jpg");
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto,ImagenUrl) values ("Red Label","Alcohol WISHKY","25","10","https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202007/06/00118721000133____8__600x600.jpg");
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto,ImagenUrl) values ("Beefeeter","Alcohol GINEBRA","15","10","https://admin.bodeboca.com/sites/default/files/bot-beefeater-70-nuevaimagen.jpg");
insert into producto(nombre,descripcion,puntosPorCompra,precioProducto,ImagenUrl) values ("Tequila","Alcohol CHUPITO","5","2","https://media.gettyimages.com/id/157337398/es/foto/primer-plano-de-la-toma-de-vidrio-y-limes.jpg?s=612x612&w=gi&k=20&c=ZEa3BcHuqL8Zh_y81WrgCnJr7T3GqRWhoXjjDZHTA6U=");


CREATE TABLE Evento (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100),
  localidad VARCHAR(100),
  descripcion VARCHAR(200),
  direccion VARCHAR(200),
  fecha DATE,
  puntosPorAsistir INT,
  precioEvento DECIMAL(10, 2),
  imagen VARCHAR(250)
);
ALTER TABLE Evento
ADD imagen VARCHAR(255);

insert into evento (nombre,localidad,descripcion,direccion,fecha,puntosPorAsistir,precioEvento) values("San Juan festival","Malaga","Festival de musica urbana para la celebracion de San Juan","los santos 13","24-06-23","100","20");
insert into evento (nombre,localidad,descripcion,direccion,fecha,puntosPorAsistir,precioEvento) values("El Row","Malaga","Festival de musica tecno","acacias 3","5-06-23","200","40");
insert into evento (nombre,localidad,descripcion,direccion,fecha,puntosPorAsistir,precioEvento) values("Los Alamos beach festival","Malaga","festival musica en la playa de los alamos en malaga
","los alamos 32","06-07-23","100","20");

UPDATE Evento
SET imagen = "https://visitestepona.eu/wp-content/uploads/2019/12/CARTEL-FINAL-LOS-ALAMOS-2019_V04.png"
WHERE nombre = "Los Alamos beach festival";

SELECT * FROM EVENTO;

SET SQL_SAFE_UPDATES = 0;

UPDATE Evento
SET imagen = 'ruta_de_la_imagen'
WHERE nombre = 'El Row';

SET SQL_SAFE_UPDATES = 1;

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
SELECT * FROM usuario;

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
SELECT * FROM discoteca;

CREATE TABLE compras (
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
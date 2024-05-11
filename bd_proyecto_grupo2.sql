Drop Database If Exists bd_proyecto_grupo2;
Create Database If Not Exists bd_proyecto_grupo2;
Use bd_proyecto_grupo2;

Create Table Rol(
rolid Int Primary Key Auto_Increment,
nomrol Varchar(30) Not Null);

Create Table Usuario(
usuarioid Int Primary Key Auto_Increment,
username Varchar(11) Not Null Unique,
password Char(60) Not Null,
nomusuario Varchar(50) Not Null,
apeusuario Varchar(50) Not Null,
activo Bit Not Null,
ultimologin TimeStamp Null);

Create Table Usuario_Rol(
usuarioid Int,
rolid Int,
Foreign Key (usuarioid) References Usuario (usuarioid),
Foreign Key (rolid) References Rol (rolid));

Create Table Departamento(
departamentoid Int Primary Key Auto_Increment,
nomdepa Varchar(100) Not Null);

Create Table Provincia(
provinciaid Int Primary Key Auto_Increment,
nomprov Varchar(100) Not Null,
departamentoid Int Not Null,
Foreign Key (departamentoid) References Departamento (departamentoid));

Create Table Distrito(
distritoid Int Primary Key Auto_Increment,
nomdist Varchar(100) Not Null,
provinciaid Int Not Null,
Foreign Key (provinciaid) References Provincia (provinciaid));

Create Table Empleado(
empleadoid Int Primary Key Auto_Increment,
nomemp Varchar(50) Not Null,
apeemp Varchar(50) Not Null,
estado Bit Not Null,
disponible Bit Not Null,
fecnac Date Not Null,
email Varchar(50) Not Null,
telefono Char(9) Not Null,
direccion Varchar(50) Not Null,
distritoid Int Null,
Foreign Key (distritoid) References Distrito (distritoid));

Create Table Empresa(
empresaid Int Primary Key Auto_Increment,
nomempresa Varchar(70) Not Null,
ruc Char(11) Not Null,
activo Bit Not Null);

Create Table Plano(
planoid Varchar(20) Primary Key,
distritoid Int Not Null,
estado Bit Not Null,
Foreign Key (distritoid) References Distrito (distritoid));

Create Table Proyecto(
proyectoid Int Primary Key Auto_Increment,
empresaid Int Not Null,
fecinicio Date Not Null,
fecfin Date Null,
estado Char(1) Not Null, -- E en proceso, F - finalizado, C - cancelado
Foreign Key (empresaid) References Empresa (empresaid));

Create Table Asignacion(
asignacionid Int Primary Key Auto_Increment,
proyectoid Int Not Null,
planoid Varchar(20) Not Null,
empleadoid Int Null,
Foreign Key (proyectoid) References Proyecto (proyectoid),
Foreign Key (planoid) References Plano (planoid),
Foreign Key (empleadoid) References Empleado (empleadoid));

-- Inserciones
-- Todos los Roles
Insert Into Rol
Values (Null, 'Administrador'),
       (Null, 'Operador de Proyectos'),
       (Null, 'Operador de Empleados'),
       (Null, 'Operador de Planos'),
       (Null, 'Operador de Ubigeos'),
       (Null, 'Operador de Empresas');

-- Único Administrador
-- username: admin
-- password: admin1234
Insert Into Usuario
Values (Null, 'admin',
'$2a$12$1LfV01H5j8HnQEIq0EXkjeP5ktdkbwOt7od2Kb5CHSjUroolKKxca',
'Penélope Pura', 'Nelson Taylor',
true, null);
Insert Into Usuario_Rol
Values (1, 1);

-- Operador de Proyectos y de Planos
-- username: usuario1453
-- password: usuario1453
Insert Into Usuario
Values (Null, 'usuario1453',
'$2a$10$7c/voM1.rWnFROGRoiSJFeptVO/aPkwJ1abv1ttKoY2b/.CrKlKVC',
'Carlos Gabriel', 'Baca Manrique',
true, null);
Insert Into Usuario_Rol
Values (2, 2), (2, 4);

-- Operador de Empleados y de Planos
-- username: usuario6266
-- password: usuario6266
Insert Into Usuario
Values (Null, 'usuario6266',
'$2a$10$ZU9wKNJt3pc8R6nK1578teTYI4uUG.BDiMB5mJSvj77lW9QF1oZOa',
'Fernando', 'Castillo Vinces',
true, null);
Insert Into Usuario_Rol
Values (3, 3), (3, 4);

-- Operador de Ubigeos
-- username: usuario7252
-- password: usuario7252
Insert Into Usuario
Values (Null, 'usuario7252',
'$2a$10$grikvmqivXolnoin3rdGA.NveSEud.8HDEx.H6wB8sl2wdlGR12Km',
'Anilu Camila', 'Díaz Lopéz',
true, null);
Insert Into Usuario_Rol
Values (4, 5);

-- Operador de Ubigeos y Empresas
-- username: usuario2476
-- password: usuario2476
Insert Into Usuario
Values (Null, 'usuario2476',
'$2a$10$ZQtS326fqNVLxVNdyQ9ILuE3QQH7rXKeayRfYOCqaD9G0SWGt2xIS',
'Julio Adriano', 'Manchay Seminario',
true, null);
Insert Into Usuario_Rol
Values (5, 5), (5, 6);

-- Inserciones de Departamentos
Insert Into Departamento
Values (Null, 'Amazonas'), (Null, 'Ancash'), (Null, 'Apurímac'),
       (Null, 'Arequipa'), (Null, 'Ayacucho'), (Null, 'Cajamarca'),
       (Null, 'Callao'), (Null, 'Cusco'), (Null, 'Huancavelica'), 
       (Null, 'Huánuco'), (Null, 'Ica'), (Null, 'Junín'),
       (Null, 'La Libertad'), (Null, 'Lambayeque'), (Null, 'Lima'),
       (Null, 'Loreto'), (Null, 'Madre de Dios'), (Null, 'Moquegua'),
       (Null, 'Pasco'), (Null, 'Piura'), (Null, 'Puno'),
       (Null, 'San Martín'), (Null, 'Tacna'), (Null, 'Tumbes'), (Null, 'Ucayali');

-- Inserciones de Provincias de Amazonas
Insert Into Provincia
Values (Null, 'Chachapoyas', 1), (Null, 'Bagua', 1), (Null, 'Bongará', 1),
       (Null, 'Condorcanqui', 1), (Null, 'Luya', 1), (Null, 'Rodríguez de Mendoza', 1),
       (Null, 'Utcubamba', 1);
       
-- Inserciones de Provincias de Ancash
Insert Into Provincia
Values (Null, 'Huaraz', 2), (Null, 'Aija', 2), (Null, 'Antonio Raymondi', 2),
       (Null, 'Asunción', 2), (Null, 'Bolognesi', 2), (Null, 'Carhuaz', 2),
       (Null, 'Carlos Fermín Fitzcarrald', 2), (Null, 'Casma', 2), (Null, 'Corongo', 2),
       (Null, 'Huari', 2), (Null, 'Huarmey', 2), (Null, 'Huaylas', 2),
       (Null, 'Mariscal Luzuriaga', 2), (Null, 'Ocros', 2), (Null, 'Pallasca', 2),
       (Null, 'Pomabamba', 2), (Null, 'Recuay', 2), (Null, 'Santa', 2),
       (Null, 'Sihuas', 2), (Null, 'Yungay', 2);
       
-- Inserciones de Provincias de Apurímac
Insert Into Provincia
Values (Null, 'Abancay', 3), (Null, 'Antabamba', 3), (Null, 'Aymaraes', 3),
       (Null, 'Cotabambas', 3), (Null, 'Grau', 3), (Null, 'Chincheros', 3),
       (Null, 'Andahuaylas', 3);
       
-- Inserciones de Provincias de Arequipa
Insert Into Provincia
Values (Null, 'Arequipa', 4), (Null, 'Camaná', 4), (Null, 'Caravelí', 4),
       (Null, 'Castilla', 4), (Null, 'Caylloma', 4), (Null, 'Condesuyos', 4),
       (Null, 'Islay', 4), (Null, 'La Unión', 4);
       
-- Inserciones de Provincias de Ayacucho
Insert Into Provincia
Values (Null, 'Cangallo', 5), (Null, 'Huanta', 5), (Null, 'Huamanga', 5),
       (Null, 'Huanca Sancos', 5), (Null, 'La Mar', 5), (Null, 'Lucanas', 5),
       (Null, 'Parinacochas', 5), (Null, 'Páucar del Sara Sara', 5), (Null, 'Sucre', 5),
       (Null, 'Víctor Fajardo', 5), (Null, 'Vilcashuamán', 5);
       
-- Inserciones de Provincias de Cajamarca
Insert Into Provincia
Values (Null, 'Cajamarca', 6), (Null, 'Cajabamba', 6), (Null, 'Celendín', 6),
       (Null, 'Chota', 6), (Null, 'Contumazá', 6), (Null, 'Cutervo', 6),
       (Null, 'Hualgayoc', 6), (Null, 'Jaén', 6), (Null, 'San Ignacio', 6),
       (Null, 'San Marcos', 6), (Null, 'San Miguel', 6), (Null, 'San Pablo', 6),
       (Null, 'Santa Cruz', 6);
       
-- Inserciones de Provincias de Callao
Insert Into Provincia
Values (Null, 'Callao', 7);

-- Inserciones de Provincias de Cusco
Insert Into Provincia
Values (Null, 'Cusco', 8), (Null, 'Acomayo', 8), (Null, 'Anta', 8),
       (Null, 'Calca', 8), (Null, 'Canas', 8), (Null, 'Canchis', 8),
       (Null, 'Chumbivilcas', 8), (Null, 'Espinar', 8), (Null, 'La Convención', 8),
       (Null, 'Paruro', 8), (Null, 'Paucartambo', 8), (Null, 'Quispicanchi', 8),
       (Null, 'Urubamba', 8);
       
-- Inserciones de Provincias de Huancavelica
Insert Into Provincia
Values (Null, 'Huancavelica', 9), (Null, 'Acobamba', 9), (Null, 'Angaraes', 9),
       (Null, 'Castrovirreyna', 9), (Null, 'Churcampa', 9), (Null, 'Huaytará', 9),
       (Null, 'Tayacaja', 9);
       
-- Inserciones de Provincias de Huánuco
Insert Into Provincia
Values (Null, 'Huánuco', 10), (Null, 'Ambo', 10), (Null, 'Dos de Mayo', 10),
       (Null, 'Huacaybamba', 10), (Null, 'Huamalíes', 10), (Null, 'Leoncio Prado', 10),
       (Null, 'Marañón', 10), (Null, 'Pachitea', 10), (Null, 'Puerto Inca', 10),
       (Null, 'Lauricocha', 10), (Null, 'Yarowilca', 10);
       
-- Inserciones de Provincias de Ica
Insert Into Provincia
Values (Null, 'Ica', 11), (Null, 'Chincha', 11), (Null, 'Nazca', 11),
       (Null, 'Palca', 11), (Null, 'Pisco', 11);
       
-- Inserciones de Provincias de Junín
Insert Into Provincia
Values (Null, 'Chanchamayo', 12), (Null, 'Chupaca', 12), (Null, 'Concepción', 12),
       (Null, 'Huancayo', 12), (Null, 'Jauja', 12), (Null, 'Junín', 12),
       (Null, 'Satipo', 12), (Null, 'Tarma', 12), (Null, 'Yauli', 12);
       
-- Inserciones de Provincias de La Libertad
Insert Into Provincia
Values (Null, 'Trujillo', 13), (Null, 'Ascope', 13), (Null, 'Bolívar', 13),
       (Null, 'Chepén', 13), (Null, 'Julcán', 13), (Null, 'Otuzco', 13),
       (Null, 'Gran Chimú', 13), (Null, 'Pacasmayo', 13), (Null, 'Pataz', 13),
       (Null, 'Sánchez Carrión', 13), (Null, 'Santiago de Chuco', 13), (Null, 'Virú', 13);
       
-- Inserciones de Provincias de Lambayeque
Insert Into Provincia
Values (Null, 'Chiclayo', 14), (Null, 'Ferreñafe', 14), (Null, 'Lambayeque', 14);

-- Inserciones de Provincias de Lima
Insert Into Provincia
Values (Null, 'Barranca', 15), (Null, 'Cajatambo', 15), (Null, 'Canta', 15),
       (Null, 'Cañete', 15), (Null, 'Huaral', 15), (Null, 'Huarochirí', 15),
       (Null, 'Huaura', 15), (Null, 'Lima', 15), (Null, 'Oyón', 15),
       (Null, 'Yauyos', 15);
       
-- Inserciones de Provincias de Loreto
Insert Into Provincia
Values (Null, 'Maynas', 16), (Null, 'Putumayo', 16), (Null, 'Alto Amazonas', 16),
       (Null, 'Loreto', 16), (Null, 'Mariscal Ramón Castilla', 16), (Null, 'Requena', 16),
       (Null, 'Ucayali', 16), (Null, 'Datem del Marañón', 16);
       
-- Inserciones de Provincias de Madre de Dios
Insert Into Provincia
Values (Null, 'Tambopata', 17), (Null, 'Manu', 17), (Null, 'Tahuamanu', 17);

-- Inserciones de Provincias de Moquegua
Insert Into Provincia
Values (Null, 'Mariscal Nieto', 18), (Null, 'General Sánchez Cerro', 18), (Null, 'Ilo', 18);

-- Inserciones de Provincias de Pasco
Insert Into Provincia
Values (Null, 'Pasco', 19), (Null, 'Oxapampa', 19), (Null, 'Daniel Alcides Carrión', 19);

-- Inserciones de Provincias de Piura
Insert Into Provincia
Values (Null, 'Ayabaca', 20), (Null, 'Huancabamba', 20), (Null, 'Morropón', 20),
       (Null, 'Piura', 20), (Null, 'Senchura', 20), (Null, 'Sullana', 20),
       (Null, 'Paita', 20), (Null, 'Talara', 20);
       
-- Inserciones de Provincias de Puno
Insert Into Provincia
Values (Null, 'San Román', 21), (Null, 'Puno', 21), (Null, 'Azángaro', 21),
       (Null, 'Chucuito', 21), (Null, 'El Collao', 21), (Null, 'Melgar', 21),
       (Null, 'Carabaya', 21), (Null, 'Huancané', 21), (Null, 'Sandia', 21),
       (Null, 'San Antonio de Putina', 21), (Null, 'Lampa', 21), (Null, 'Yunguyo', 21),
       (Null, 'Moho', 21);
       
-- Inserciones de Provincias de San Martin
Insert Into Provincia
Values (Null, 'Bellavista', 22), (Null, 'El Dorado', 22), (Null, 'Huallaga', 22),
       (Null, 'Lamas', 22), (Null, 'Mariscal Cáceres', 22), (Null, 'Moyobamba', 22),
       (Null, 'Picota', 22), (Null, 'Rioja', 22), (Null, 'San Martín', 22),
       (Null, 'Tocache', 22);
       
-- Inserciones de Provincias de Tacna
Insert Into Provincia
Values (Null, 'Tacna', 23), (Null, 'Candarave', 23), (Null, 'Jorge Basadre', 23),
       (Null, 'Tarata', 23);
       
-- Inserciones de Provincias de Tumbes
Insert Into Provincia
Values (Null, 'Tumbes', 24), (Null, 'Zarumilla', 24), (Null, 'Contralmirante', 24);

-- Inserciones de Provincias de Ucayali
Insert Into Provincia
Values (Null, 'Coronel Portillo', 25), (Null, 'Atalaya', 25), (Null, 'Padre Abad', 25),
       (Null, 'Purús', 25);

-- Inserciones de Distritos de Chachapoyas
Insert Into Distrito
Values (Null, 'Asunción', 1), (Null, 'Balsas', 1), (Null, 'Chachapoyas', 1),
       (Null, 'Cheto', 1), (Null, 'Chiliquín', 1), (Null, 'Chuquibamba', 1),
       (Null, 'Granda', 1), (Null, 'Huancas', 1), (Null, 'Jalca Grande', 1),
       (Null, 'Leimebamba', 1), (Null, 'Levanto', 1), (Null, 'Magdalena', 1),
       (Null, 'Mariscal Castilla', 1), (Null, 'Molinopampa', 1), (Null, 'Montevideo', 1),
       (Null, 'Olleros', 1), (Null, 'Quinjalca', 1), (Null, 'San Francisco de Daguas', 1),
       (Null, 'San Isidro de Maino', 1), (Null, 'Soloco', 1), (Null, 'Sonche', 1);
       
-- Inserciones de Distritos de Bagua
Insert Into Distrito
Values (Null, 'Bagua', 2), (Null, 'Aramango', 2), (Null, 'La Peca', 2),
       (Null, 'Copallín', 2), (Null, 'El Parco', 2), (Null, 'Imaza', 2);
       
-- Inserciones de Distritos de Bongará
Insert Into Distrito
Values (Null, 'Jumbilla', 3), (Null, 'Chisquilla', 3), (Null, 'Churuja', 3),
       (Null, 'Corosha', 3), (Null, 'Cuispes', 3), (Null, 'Florida', 3),
       (Null, 'Jazán', 3), (Null, 'Recta', 3), (Null, 'San Carlos', 3),
       (Null, 'Shipasbamba', 3), (Null, 'Valera', 3), (Null, 'Yambrasbamba', 3);

-- Inserciones de Distritos de Condorcanqui
Insert Into Distrito
Values (Null, 'Nieva', 4), (Null, 'El Cenepa', 4), (Null, 'Río Santiago', 4);

-- Inserciones de Distritos de Luya
Insert Into Distrito
Values (Null, 'Camporredondo', 5), (Null, 'Cocabamba', 5), (Null, 'Colcamar', 5),
       (Null, 'Conila', 5), (Null, 'Inguilpata', 5), (Null, 'Lámud', 5),
       (Null, 'Longuita', 5), (Null, 'Lonya Chico', 5), (Null, 'Luya', 5),
       (Null, 'Luya Viejo', 5), (Null, 'María', 5), (Null, 'Ocalli', 5),
       (Null, 'Ocumal', 5), (Null, 'Pisuquía', 5), (Null, 'Providencia', 5),
       (Null, 'San Cristóbal', 5), (Null, 'San Francisco del Yeso', 5), (Null, 'San Jerónimo', 5),
       (Null, 'San Juan de Lopecancha', 5), (Null, 'Santa Catalina', 5), (Null, 'Santo Tomás', 5),
       (Null, 'Tingo', 5), (Null, 'Trita', 5);
       
-- Inserciones de Distritos de Rodríguez de Mendoza
Insert Into Distrito
Values (Null, 'San Nicolás', 6), (Null, 'Chirimoto', 6), (Null, 'Cochamal', 6),
       (Null, 'Huambo', 6), (Null, 'Limabamba', 6), (Null, 'Longar', 6),
       (Null, 'Mariscal Benavides', 6), (Null, 'Mílpuc', 6), (Null, 'Omia', 6),
       (Null, 'Santa Rosa', 6), (Null, 'Totora', 6), (Null, 'Vista Alegre', 6);
       
-- Inserciones de Distritos de Utcubamba
Insert Into Distrito
Values (Null, 'Bagua Grande', 7), (Null, 'Cajaruro', 7), (Null, 'Cumba', 7),
       (Null, 'El Milagro', 7), (Null, 'Jamalca', 7), (Null, 'Lonya Grande', 7),
       (Null, 'Yamón', 7);
       
-- Inserciones de Distritos de Callao
Insert Into Distrito
Values (Null, 'Callao', 67), (Null, 'Bellavista', 67), (Null, 'Carmen de La Legua Reynoso', 67),
       (Null, 'La Perla', 67), (Null, 'La Punta', 67), (Null, 'Ventanilla', 67),
       (Null, 'Mi Perú', 67);

Select * From Provincia Order By nomprov;
Select * From Distrito Order By nomdist;
-- Inserciones de Empleados
Insert Into Empleado
Values (Null, 'Christian Alberto', 'Goméz Mendoza', true, true, 
       '20000702', 'christiangm@gmail.com', '912742219', 'Av. Grau 767', 3),
	   (Null, 'Rodríguez', 'Rojas Ramos', true, true,
       '19990202', 'rodriguezrr@gmail.com', '974284579', 'Av. Grau 766', 3),
       (Null, 'María Ana', 'Calderón Vargas', true, true,
       '20000830', 'mariacalderon@gmail.com', '93718229', 'Av. Grau 765', 3),
       (Null, 'Jessica', 'Díaz Quispe', true, true,
       '19980202', 'jessicadq@gmail.com', '928475289', 'Jr. Junin 630', 14),
       (Null, 'Roberto Mauricio', 'Méndoza del Prado', true, true,
       '19970404', 'robertmend@gmail.com', '928928109', 'Jr. ', 14);

Insert Into Empresa
Values (Null, 'Grupo Ips Latam S.A.C.', '20605483942', true),
       (Null, 'Cicsa Peru S.A.C.', '20512780114', true),
       (Null, 'Huawei Peru S.A.C.', '20507646728', true),
       (Null, 'Grupo Lari', '20550849551', true);
       
-- Inserciones de Proyecto 1
Insert Into Plano
Values ('LMLO01', 1, false), ('LMLO02', 1, false), ('LMLO03', 1, false),
	   ('LMLO04', 1, false), ('LMLO05', 1, false), ('LMLO06', 1, false),
       ('LMLO07', 1, false), ('LMLO08', 1, false), ('LMLO09', 1, false),
	   ('LMLO10', 1, false), ('LMLO11', 1, false), ('LMLO12', 1, false),
       ('LMLO13', 1, false), ('LMLO14', 1, false), ('LMLO15', 1, false),
	   ('LMLO16', 1, false), ('LMLO17', 1, false), ('LMLO18', 1, false),
       ('LMLO19', 1, false), ('LMLO20', 1, false), ('LMLO21', 1, false),
	   ('LMLO22', 1, false), ('LMLO23', 1, false), ('LMLO24', 1, false),
       ('LMLO25', 1, false), ('LMLO26', 1, false), ('LMLO27', 1, false),
	   ('LMLO28', 1, false), ('LMLO29', 1, false), ('LMLO30', 1, false),
       ('LMLO31', 1, false), ('LMLO32', 1, false), ('LMLO33', 1, false),
	   ('LMLO34', 1, false), ('LMLO35', 1, false), ('LMLO36', 1, false),
       ('LMLO37', 1, false), ('LMLO38', 1, false);
       
Insert Into Proyecto
Values (Null, 3, '20200401', '20200408', 'F');

Delimiter //
Create Procedure PaginacionProvincias(In _skip Int)
Begin
    Select *
    From Provincia
    Order By nomprov Asc
    Limit _skip, 20;
End //
Delimiter ;

Delimiter //
Create Procedure PaginacionDistritos(In _skip Int)
Begin
    Select *
    From Distrito
    Order By nomdist Asc
    Limit _skip, 20;
End //
Delimiter ;

Delimiter //
Create Procedure PaginacionEmpresas(In _skip Int)
Begin
    Select *
    From Empresa
    Order By nomempresa Asc
    Limit _skip, 20;
End //
Delimiter ;

Delimiter //
Create Procedure PaginacionEmpleados(In _skip Int)
Begin
    Select * 
    From Empleado
    Order By apeemp Asc
    Limit _skip, 20;
End //
Delimiter ;

Delimiter //
Create Procedure PaginacionUsuarios(In _skip Int)
Begin
    Select Distinct U.*
    From Usuario U
    Join Usuario_Rol UR On U.usuarioid = UR.usuarioid
    Join Rol R On R.rolid = UR.rolid
    Where R.nomrol != 'Administrador'
    Order By apeusuario Asc
    Limit _skip, 20;
End //
Delimiter ;

Delimiter //
Create Procedure PaginacionProyectos(In _skip Int)
Begin
    Select *
    From Proyecto
    Order By fecinicio Desc
    Limit _skip, 20;
End //
Delimiter ;

Select * From Empleado;
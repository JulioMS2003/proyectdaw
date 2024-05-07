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
fecnac Date Not Null,
email Varchar(50) Not Null,
telefono Char(9) Not Null,
foto Varchar(100) Null,
direccion Varchar(50) Null,
distritoid Int Not Null,
Foreign Key (distritoid) References Distrito (distritoid));

Create Table Empresa(
empresaid Int Primary Key Auto_Increment,
nomempresa Varchar(70) Not Null,
ruc Char(11) Not Null,
activo Bit Not Null);

Create Table Plano(
planoid Char(7) Primary Key,
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
planoid Char(7) Not Null,
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

-- Operador de Proyectos y de Planos
-- username: usuario1453
-- password: usuario1453
Insert Into Usuario
Values (Null, 'usuario1453',
'$2a$10$7c/voM1.rWnFROGRoiSJFeptVO/aPkwJ1abv1ttKoY2b/.CrKlKVC',
'Carlos Gabriel', 'Baca Manrique',
true, null);

-- Operador de Empleados y de Planos
-- username: usuario6266
-- password: usuario6266
Insert Into Usuario
Values (Null, 'usuario6266',
'$2a$10$ZU9wKNJt3pc8R6nK1578teTYI4uUG.BDiMB5mJSvj77lW9QF1oZOa',
'Fernando', 'Castillo Vinces',
true, null);

-- Operador de Ubigeos
-- username: usuario7252
-- password: usuario7252
Insert Into Usuario
Values (Null, 'usuario7252',
'$2a$10$grikvmqivXolnoin3rdGA.NveSEud.8HDEx.H6wB8sl2wdlGR12Km',
'Anilu Camila', 'Díaz Lopéz',
true, null);

-- Operador de Ubigeos y Empresas
-- username: usuario2476
-- password: usuario2476
Insert Into Usuario
Values (Null, 'usuario2476',
'$2a$10$ZQtS326fqNVLxVNdyQ9ILuE3QQH7rXKeayRfYOCqaD9G0SWGt2xIS',
'Julio Adriano', 'Manchay Seminario',
true, null); 

Insert Into Usuario_Rol
Values (1, 1), (2, 2), (2, 4), (3, 3), (3, 4), (4, 5), (5, 5), (5, 6);

Insert Into Departamento
Values (Null, 'Amazonas'), (Null, 'Ancash'), (Null, 'Apurímac'),
       (Null, 'Arequipa'), (Null, 'Ayacucho'), (Null, 'Cajamarca'),
       (Null, 'Cusco'), (Null, 'Huancavelica'), (Null, 'Huánuco'),
       (Null, 'Ica'), (Null, 'Junín'), (Null, 'La Libertad'),
       (Null, 'Lambayeque'), (Null, 'Lima'), (Null, 'Loreto'),
       (Null, 'Madre de Dios'), (Null, 'Moquegua'), (Null, 'Pasco'),
       (Null, 'Piura'), (Null, 'Puno'), (Null, 'San Martín'),
       (Null, 'Tacna'), (Null, 'Tumbes'), (Null, 'Ucayali');

Insert Into Provincia
Values (Null, 'Bagua', 1),(Null, 'Bongara', 1),(Null, 'Chachapoyas', 1),(Null, 'Condorcanqui', 1),(Null, 'Luya', 1),(Null, 'Rodríguez de Méndoza', 1),(Null, 'Utcubamba', 1),
       (Null, 'Aija', 2),(Null, 'Antonio Raimondi', 2),(Null, 'Asunción', 2),(Null, 'Bolognesi', 2),(Null, 'Carhuaz', 2),(Null, 'Carlos Fermin Fitzcarrald', 2),(Null, 'Casma', 2),(Null, 'Corongo', 2),(Null, 'Huaraz', 2),(Null, 'Huari', 2),(Null, 'Huarmey', 2),(Null, 'Huaylas', 2),(Null, 'Mariscal Luzuriaga', 2),(Null, 'Ocros', 2),(Null, 'Pallasca', 2),(Null, 'Pomabamba', 2),(Null, 'Recuay', 2),(Null, 'Santa', 2),(Null, 'Sihuas', 2),(Null, 'Yungay', 2),
       (Null, 'Abancay', 3),(Null, 'Andahuaylas', 3),(Null, 'Antabamba', 3),(Null, 'Aymaraes', 3),(Null, 'Chincheros', 3),(Null, 'Cotabambas', 3),(Null, 'Grau', 3),
       (Null, 'Arequipa', 4),(Null, 'Camana', 4),(Null, 'Caraveli', 4),(Null, 'Castilla', 4),(Null, 'Caylloma', 4),(Null, 'Condesuyos', 4),(Null, 'Islay', 4),(Null, 'La Unión', 4),
       (Null, 'Cangallo', 5),(Null, 'Huamanga', 5),(Null, 'Huanca Sancos', 5),(Null, 'Huanta', 5),(Null, 'La Mar', 5),(Null, 'Lucanas', 5),(Null, 'Parinacochas', 5),(Null, 'Paucar del Sara Sara', 5),(Null, 'Sucre', 5),(Null, 'Víctor Fajardo', 5),(Null, 'Vilcas Huáman', 5),
       (Null, 'Cajabamba', 6),(Null, 'Cajamarca', 6),(Null, 'Celendín', 6),(Null, 'Chota', 6),(Null, 'Contumaza', 6),(Null, 'Cutervo', 6),(Null, 'Hualgayoc', 6),(Null, 'Jaen', 6),(Null, 'San Ignacio', 6),(Null, 'San Marcos', 6),(Null, 'San Miguel', 6),(Null, 'San Pablo', 6),(Null, 'Santa Cruz', 6),
       (Null, 'Acomayo', 7),(Null, 'Anta', 7),(Null, 'Calca', 7),(Null, 'Canas', 7),(Null, 'Canchis', 7),(Null, 'Chumbivilcas', 7),(Null, 'Cusco', 7),(Null, 'Espinar', 7),(Null, 'La Convención', 7),(Null, 'Paruro', 7),(Null, 'Paucartambo', 7),(Null, 'Quispicanchi', 7),(Null, 'Urubamba', 7),
       (Null, 'Acobamba', 8),(Null, 'Angaraes', 8),(Null, 'Castrovirreyna', 8),(Null, 'Churcampa', 8),(Null, 'Huancavelica', 8),(Null, 'Huaytara', 8),(Null, 'Tayacaja', 8),
       (Null, 'Ambo', 9),(Null, 'Dos de Mayo', 9),(Null, 'Huacaybamba', 9),(Null, 'Huamalies', 9),(Null, 'Huánuco', 9),(Null, 'Lauricocha', 9),(Null, 'Leoncio Prado', 9),(Null, 'Marañon', 9),(Null, 'Pachitea', 9),(Null, 'Puerto Inca', 9),(Null, 'Yarowilca', 9),
       (Null, 'Barranca', 14),(Null, 'Cajatambo', 14),(Null, 'Canta', 14),(Null, 'Cañete', 14),(Null, 'Huaral', 14),(Null, 'Huarochiri', 14),(Null, 'Huaura', 14),(Null, 'Lima', 14),(Null, 'Oyon', 14),(Null, 'Yauyos', 14),(Null, 'Callao', 14);

Insert Into Distrito
Values (Null, 'Aramango', 1),(Null, 'Bagua', 1),(Null, 'Copallin', 1),(Null, 'Chisquilla', 2),(Null, 'Churuja', 2),(Null, 'Corosha', 2),(Null, 'Asunción', 3),(Null, 'Balsas', 3),(Null, 'Chachapoyas', 3),(Null, 'El Cenepa', 4),
	   (Null, 'Nieva', 4),(Null, 'Río Santiago', 4),(Null, 'Camporredondo', 5),(Null, 'Cocabamba', 5),(Null, 'Colcamar', 5),(Null, 'Chirimoto', 6),(Null, 'Cochamal', 6),(Null, 'Huambo', 6),(Null, 'Bagua Grande', 7),(Null, 'Cajaruro', 7),
	   (Null, 'Cumba', 7),(Null, 'Aija', 8),(Null, 'Coris', 8),(Null, 'Huacllan', 8),(Null, 'Aczo', 9),(Null, 'Chaccho', 9),(Null, 'Chingas', 9),(Null, 'Acochaca', 10),(Null, 'Chacas', 10),
       (Null, 'Chiquian', 11),(Null, 'Abelardo Pardo Lezameta', 11),(Null, 'Mangas', 11),(Null, 'Pariahuanca', 12),(Null, 'San Miguel de Aco', 12),(Null, 'Shilla', 12),(Null, 'San Luis', 13),(Null, 'Yauya', 13),(Null, 'San Nicolas', 13),(Null, 'Casma', 14),
       (Null, 'Buena Vista Alta', 14),(Null, 'Comandante Noel', 14),(Null, 'Corongo', 15),(Null, 'Aco', 15),(Null, 'Bambas', 15),(Null, 'Huaraz', 16),(Null, 'Jangas', 16),(Null, 'La Libertad', 16),(Null, 'Huari', 17),(Null, 'Cajay', 17),
       (Null, 'Chavín de Huantar', 17),(Null, 'Huarmey', 18),(Null, 'Cochapeti', 18),(Null, 'Huayan', 18),(Null, 'Caraz', 19),(Null, 'Huallanca', 19),(Null, 'Huata', 19),(Null, 'Piscobamba', 20),(Null, 'Casca', 20),(Null, 'Lucma', 20),
       (Null, 'Abancay', 28),(Null, 'Lima', 105),(Null, 'Ancón', 105),(Null, 'Ate', 105),(Null, 'Breña', 105),(Null, 'Carabayllo', 105),(Null, 'Comas', 105),(Null, 'Chaclayo', 105),(Null, 'Chorrillos', 105),(Null, 'La Victoria', 105),
       (Null, 'La Molina', 105),(Null, 'Lince', 105),(Null, 'Lurigancho', 105),(Null, 'Lurín', 105),(Null, 'Magdalena del Mar', 105),(Null, 'Miraflores', 105),(Null, 'Pachacamac', 105),(Null, 'Pueblo Libre', 105),(Null, 'Pucusana', 105),(Null, 'Puente Piedra', 105),
       (Null, 'Punta Hermosa', 105),(Null, 'Punta Negra', 105),(Null, 'Rímac', 105),(Null, 'San Bartolo', 105),(Null, 'San Isi', 105),(Null, 'Cajatambo', 99),(Null, 'Copa', 99),(Null, 'Gorgor', 99),(Null, 'Huancapon', 99),(Null, 'Manas', 99),(Null, 'Callao', 108),
       (Null, 'Bellavista', 108),(Null, 'La Punta', 108),(Null, 'Carmen de la Legua Reynoso', 108),(Null, 'La Perla', 108),(Null, 'Ventanilla', 108);

Insert Into Empleado
Values (Null, 'Christian Alberto', 'Goméz Méndoza', true,
'19980702', 'christiangm@gmail.com', '974128219',
Null, 'Av. Las Manzanas 483', 20);

Select * From Rol;
Select * From Usuario;
Select U.*, R.nomrol From Usuario_Rol UR
Join Usuario U On UR.usuarioid = U.usuarioid
Join Rol R On R.rolid = UR.rolid
Order By U.apeusuario;

Insert Into Empresa
Values (Null, 'Viettel Peru S.A.C.', '20543254798', true);

Delimiter //
Create Procedure PaginacionProvincias(In _skip Int)
Begin
    Select *
    From Provincia
    Order By nomprov Asc
    Limit _skip, 20;
End //
Delimiter ;
Call PaginacionProvincias(140);
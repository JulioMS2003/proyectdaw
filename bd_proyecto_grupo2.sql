Drop Database If Exists bd_proyecto_grupo2;
Create Database If Not Exists bd_proyecto_grupo2;
Use bd_proyecto_grupo2;

Create Table Rol(
rolid Int Primary Key Auto_Increment,
nomrol Varchar(30) Not Null);

Insert Into Rol
Values (null, 'Admin');

Create Table Usuario(
usuarioid Char(7) Primary Key, 
clave Char(60) Not Null,
nomusuario Varchar(50) Not Null,
apeusuario Varchar(50) Not Null,
estado Bit Not Null,
ultimo_login TimeStamp Null);

Insert Into Usuario
Values ('caba123', '$2a$12$zgsaMiI6UJlzBq3xzpaEMe2xNyJh7DR.tbNa8BLIu29n3okq5kJey',
'Carlos Gabriel', 'Baca Manrique',
true, null);

Create Table Usuario_Rol(
usuarioid Char(7),
rolid Int,
Foreign Key (usuarioid) References Usuario (usuarioid),
Foreign Key (rolid) References Rol (rolid));

Insert Into Usuario_Rol
Values ('caba123', 1);

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
fecNac Date Not Null,
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
estado Bit Not Null);

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
empleadoid Int Not Null,
Foreign Key (proyectoid) References Proyecto (proyectoid),
Foreign Key (planoid) References Plano (planoid),
Foreign Key (empleadoid) References Empleado (empleadoid));
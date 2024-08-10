/********************
 * INITIAL DATABASE *
 *******************/

DROP DATABASE IF EXISTS "db_security";
CREATE DATABASE "db_security";

DROP SCHEMA IF EXISTS "security";
CREATE SCHEMA "security";

DROP SCHEMA IF EXISTS "parameter";
CREATE SCHEMA "parameter";


/********************************
 * INITIAL DATA OF THE ENTITIES *
 ********************************/


/***********
 * MODULES *
 ***********/
INSERT INTO security.module(name, color, path, icon, state, created_at) VALUES ('Seguridad', '#001f3f', 'security', 'fa-lock', true, now());
INSERT INTO security.module(name, color, path, icon, state, created_at) VALUES ('Parametrización', '#17a2b8', 'parameterization', 'fa-users-cog', true, now());
INSERT INTO security.module(name, color, path, icon, state, created_at) VALUES ('Gestión', '#28a745', 'management-service', 'fa-users-cog', true, now());
INSERT INTO security.module(name, color, path, icon, state, created_at) VALUES ('Reporte', '#28a745', 'reports', 'fa-users-cog', true, now());

/*********
 * ROLES *
 *********/
INSERT INTO security.role(name, state, created_at)	VALUES ('Administrador', true, now());
INSERT INTO security.role(name, state, created_at)	VALUES ('Gestión', true, now());
INSERT INTO security.role(name, state, created_at)	VALUES ('Reporte', true, now());

/*********
 * USERS *
 *********/
INSERT INTO security."user"(username, password, state, created_at) VALUES ('admin@gmail.com', '$2a$10$nswv9rlvoPMHcgUXCdzk0OrLoWVgFbqh5G1LGFiGjZhqkyUdT4DCu', true, now());
INSERT INTO security."user"(username, password, state, created_at) VALUES ('user@gmail.com', '$2a$10$nswv9rlvoPMHcgUXCdzk0OrLoWVgFbqh5G1LGFiGjZhqkyUdT4DCu', true, now());

/***************
 * USERS ROLES *
 ***************/
INSERT INTO security.user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO security.user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO security.user_role(user_id, role_id) VALUES (1, 3);
INSERT INTO security.user_role(user_id, role_id) VALUES (2, 2);

/****************
 * SYSTEM FORMS *
 ****************/

-- FOMR LOGIN
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Usuarios', 'security/parameter/user', 1, true, now());

-- FORMS SECURITY
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Roles', 'security/parameter/role', 1, true, now());
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Formularios', 'security/parameter/form', 1, true, now());
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Modulos', 'security/parameter/module', 1, true, now());
 
-- PARAMETERIZATION
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Tipo de documento', 'parameterization/parameter/document-type', 1, true, now());
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Tipo de estado', 'parameterization/parameter/state-type', 1, true, now());
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Paises', 'parameterization/parameter/country', 1, true, now());
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Departamentos', 'parameterization/parameter/estate', 1, true, now());
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Ciudades', 'parameterization/parameter/city', 1, true, now());
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Estados', 'parameterization/parameter/state', 1, true, now());

-- MANAGEMENT
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'Personas', 'management-service/management/people', 2, true, now());

-- REPORT
INSERT INTO security.form(icon, name, path, section, state, created_at) VALUES ('fa-cog', 'xxx', 'reports/report/xxxx', 3, true, now());


/****************
 * MODULE FORMS *
 ****************/
INSERT INTO security.form_module(form_id, module_id) VALUES (1, 1);
INSERT INTO security.form_module(form_id, module_id) VALUES (2, 1);
INSERT INTO security.form_module(form_id, module_id) VALUES (3, 1);
INSERT INTO security.form_module(form_id, module_id) VALUES (4, 1);
INSERT INTO security.form_module(form_id, module_id) VALUES (5, 2);
INSERT INTO security.form_module(form_id, module_id) VALUES (6, 2);
INSERT INTO security.form_module(form_id, module_id) VALUES (7, 2);
INSERT INTO security.form_module(form_id, module_id) VALUES (8, 2);
INSERT INTO security.form_module(form_id, module_id) VALUES (9, 2);
INSERT INTO security.form_module(form_id, module_id) VALUES (10, 2);
INSERT INTO security.form_module(form_id, module_id) VALUES (11, 3);
INSERT INTO security.form_module(form_id, module_id) VALUES (12, 4);

-- ROLES FORMULARIOS
INSERT INTO security.role_form(form_id, role_id) VALUES (1, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (2, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (3, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (4, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (5, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (6, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (7, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (8, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (9, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (10, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (11, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (11, 1);
INSERT INTO security.role_form(form_id, role_id) VALUES (12, 1);

-- COUNTRY
INSERT INTO parameter.country(name, state, created_at) VALUES ('Colombia', true, now());

-- ESTATE
INSERT INTO parameter.estate(name, state, created_at, country_id) VALUES ('Huila', true, now(),1);

-- CITY
INSERT INTO parameter.city(name, state, created_at, estate_id) VALUES ('Neiva', true, now(),1);
INSERT INTO parameter.city(name, state, created_at, estate_id) VALUES ('Garzón', true, now(),1);
INSERT INTO parameter.city(name, state, created_at, estate_id) VALUES ('Rivera', true, now(),1);

-- DOCUMENT TYPE
INSERT INTO parameter.document_type(name, state, created_at) VALUES ('Cédula de ciudadanía', true, now());
INSERT INTO parameter.document_type(name, state, created_at) VALUES ('Tarjeta de identidad', true, now());
INSERT INTO parameter.document_type(name, state, created_at) VALUES ('Cédula extranjera', true, now());
INSERT INTO parameter.document_type(name, state, created_at) VALUES ('Registro civil', true, now());
INSERT INTO parameter.document_type(name, state, created_at) VALUES ('Pasaporte', true, now());

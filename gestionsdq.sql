-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-04-2013 a las 07:04:20
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

CREATE DATABASE gestionsdq;
USE gestionsdq;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `gestionsdq`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_infoadmin`()
begin
	select estudiantes.codigo, estudiantes.nombre, estudiantes.apellido, cursos.nombre_curso
	from estudiantes, cursos, clases
	where estudiantes.codigo = clases.estudiante_id && cursos.curso_id = clases.curso_id
        order by estudiantes.codigo;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_infoadmin2(in codigo int)`(IN `codigo` INT(4))
    NO SQL
SELECT estudiantes.codigo,estudiantes.nombre,estudiantes.apellido,cursos.nombre_curso
FROM estudiantes, clases, cursos
where cursos.profesor_id = codigo and estudiantes.codigo = clases.estudiante_id
and clases.curso_id = cursos.curso_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_infoestudent(in prmcodigo int)`(IN `prmcodigo` INT(4))
    NO SQL
select estudiantes.codigo, estudiantes.nombre, estudiantes.apellido, cursos.nombre_curso, estudiantes.clave
from estudiantes, cursos, clases
where estudiantes.codigo = prmcodigo && estudiantes.codigo = clases.estudiante_id && cursos.curso_id = clases.curso_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_newclasestudent(nomestudent, appestudent, curestudent)`(IN `nomestudent` VARCHAR(30), IN `appestudent` VARCHAR(30), IN `curestudent` VARCHAR(30))
    NO SQL
BEGIN
SELECT @A := codigo 
FROM estudiantes 
WHERE nombre = nomestudent 
AND apellido =  appestudent; 

SELECT @B := curso_id 
FROM cursos WHERE nombre_curso =  curestudent; 

INSERT INTO  clases (estudiante_id , curso_id) VALUES (@A , @B);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tbladmin`()
begin
	SELECT * FROM administradores;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tblestudent`()
begin
	SELECT * FROM estudiantes;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE IF NOT EXISTS `administradores` (
  `Codigo` int(4) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Clave` varchar(20) DEFAULT NULL,
  `Rol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`Codigo`, `Nombre`, `Apellido`, `Clave`, `Rol`) VALUES
(1, 'Limberg', 'Reyes', 'Limberg1', 'Profesor'),
(2, 'Franchy', 'Reyes', 'Franchy2', 'Profesor'),
(3, 'Manuel', 'Dominguez', 'Manuel3', 'Profesor'),
(4, 'Ana', 'Rodriguez', 'Ana4', 'Secretaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clases`
--

CREATE TABLE IF NOT EXISTS `clases` (
  `estudiante_id` int(4) NOT NULL,
  `curso_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clases`
--

INSERT INTO `clases` (`estudiante_id`, `curso_id`) VALUES
(1, 1),
(2, 4),
(3, 3),
(4, 1),
(5, 2),
(6, 2),
(7, 4),
(8, 3),
(9, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE IF NOT EXISTS `cursos` (
  `curso_id` int(4) NOT NULL,
  `Nombre_curso` varchar(30) NOT NULL,
  `Profesor_id` int(4) NOT NULL,
  PRIMARY KEY (`curso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`curso_id`, `Nombre_curso`, `Profesor_id`) VALUES
(1, 'Java1', 1),
(2, 'Java2', 2),
(3, 'Android', 1),
(4, 'Photoshop', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiantes`
--

CREATE TABLE IF NOT EXISTS `estudiantes` (
  `Codigo` int(4) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Clave` varchar(30) DEFAULT NULL,
  `Rol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `estudiantes`
--

INSERT INTO `estudiantes` (`Codigo`, `Nombre`, `Apellido`, `Clave`, `Rol`) VALUES
(1, 'Juan', 'Casanova', 'Casanova', 'Estudiante'),
(2, 'Dewin', 'De Leon', 'De Leon', 'Estudiante'),
(3, 'Robert', 'Cuevas', 'Cuevas', 'Estudiante'),
(4, 'Byron', 'Garrido', 'Byron4', 'Estudiante'),
(5, 'Leandro', 'Rodriguez', 'Rodriguez', 'Estudiante'),
(6, 'Alejandro', 'Cuello', 'Cuello', 'Estudiante'),
(7, 'Maria', 'Alvarez', 'Alvarez', 'Estudiante'),
(8, 'Julio', 'Alberto', 'Alberto', 'Estudiante'),
(9, 'Luis', 'Marinez', 'Marinez', 'Estudiante');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

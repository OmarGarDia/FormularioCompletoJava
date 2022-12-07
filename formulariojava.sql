-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-12-2022 a las 13:56:41
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `formulariojava`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formulariocontacto`
--

CREATE TABLE `formulariocontacto` (
  `id` int(4) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` int(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `sexo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `formulariocontacto`
--

INSERT INTO `formulariocontacto` (`id`, `nombre`, `apellidos`, `email`, `telefono`, `direccion`, `sexo`) VALUES
(10, 'Omar', 'Garrocho', 'okjasod@hotmail.com', 12345678, 'Paseo Independencia', 'Masculino'),
(18, 'Omar', 'Garrocho Diaz', '1oqsdfa@hotmail.com', 1231234, 's', 'Masculino');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `formulariocontacto`
--
ALTER TABLE `formulariocontacto`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `formulariocontacto`
--
ALTER TABLE `formulariocontacto`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

# README: Práctica de Información del Autor

Esta aplicación web permite buscar, crear y actualizar información de autores utilizando una API REST y una base de datos H2.

## Endpoints

- GET /autor/{userID}: Obtiene información del autor por ID.
- POST /autor: Agrega un nuevo autor a la base de datos.
- POST /autor/update/{userID}: Actualiza la información de un autor.
- DELETE /autor/{userID}: Elimina un autor de la base de datos.

## Pruebas

Se han realizado pruebas unitarias para los siguientes casos:

- Búsqueda exitoa de un autor por ID.
- Búsqueda de un autor no existente por ID.
- Agregar un autor exitosamente.
- Agregar un autor con entrada inválida.
- Agregar un autor con un error inesperado.


## Transacción

Se utilizó una transacción en la función `updateUser` para garantizar actualizaciones seguras y atómicas. También se ha usado la anotación `@Transactional` para el borrado de usaurios.

## Base de datos H2 y tabla AUTOR

Se empleó una base de datos en memoria H2 con una tabla `AUTOR` que contiene columnas para ID, NAME, PHONE y EMAIL.

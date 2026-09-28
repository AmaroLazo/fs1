# Backend de Huellitas Club — Microservicios

Este repositorio contiene el backend en Java/Spring Boot del proyecto PetShop **Huellitas Club**, adaptado para trabajar con el frontend de `fullstack2_petshop`.

## Arquitectura

- Backend: Java + Spring Boot.
- Persistencia: JPA / Hibernate + MySQL.
- Microservicio de catálogo: `8082`.
- Carrito: `8083`.
- Pedidos: `8081`.
- Reseñas: `8084`.
- Favoritos: `8085`.
- Wishlist: `8086`.
- Cupones: `8087`.
- Usuario: `8090`.
- Boleta: `8091`.
- Anuncios: `8092`.

## API compatible con el frontend

El microservicio de catálogo incorpora el recurso:

`GET http://localhost:8082/api/productos`

Parámetros opcionales:

- `q`: búsqueda por nombre o descripción.
- `animal`: `perros`, `gatos`, `aves`, `roedores`, `peces` o `reptiles`.
- `categoria`: `alimentos`, `snacks`, `juguetes`, `accesorios`, `higiene` o `camas`.
- `disponible=true`: devuelve solo productos disponibles.

También están disponibles:

- `GET /api/productos/{id}`
- `POST /api/productos`
- `PUT /api/productos/{id}`
- `DELETE /api/productos/{id}`

La respuesta utiliza los nombres que necesita el frontend: `id`, `nombre`, `descripcion`, `precio`, `imagen`, `animal`, `categoria`, `stock` y `disponible`.

El catálogo permite CORS para que el frontend estático pueda consumir el API desde otro origen. Se incluyen datos iniciales basados en los productos que actualmente presenta `fullstack2_petshop`.

## Base de datos

Crear `catalogo_db` en MySQL/XAMPP y configurar las credenciales en `codigoms_catalogo/src/main/resources/application.properties`.

Luego ejecutar el microservicio de catálogo con Maven/Spring Boot.

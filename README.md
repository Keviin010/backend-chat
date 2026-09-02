# backend-chat

> **En construcción** — proyecto en desarrollo activo.

Chat en tiempo real construido con Spring Boot, WebSocket, STOMP y PostgreSQL.

![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat&logo=postgresql&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-000000?style=flat&logo=socketdotio&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18-BC4521?style=flat&logo=lombok&logoColor=white)

## Tecnologías

- Java 21
- Spring Boot 3.5
- WebSocket + STOMP
- PostgreSQL
- Spring Data JPA
- Lombok

## Repositorio frontend

[frontend-chat](https://github.com/Keviin010/frontend-chat) — Angular 21 + SockJS + StompJS

## Funcionalidades

- [x] Configuración WebSocket + STOMP
- [x] Envío y recepción de mensajes
- [x] Notificación cuando un usuario se une o se va
- [x] Indicador de "está escribiendo"
- [x] Historial de mensajes con MySQL
- [x] Frontend Angular conectado


## Cómo ejecutar (local)

1. Tener PostgreSQL corriendo en `localhost:5432`
2. Crear la base de datos:

```sql
CREATE DATABASE chatdb;
```

3. Clonar el repositorio y ejecutar:

```bash
./mvnw spring-boot:run
```

El backend levanta en `http://localhost:8080`. Si tu usuario/contraseña de Postgres local no son `postgres`/`postgres`, exportá `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD` antes de correrlo.

## Despliegue en Render

1. Crear una base de datos PostgreSQL en Render (plan Free) y copiar su Internal Database URL, usuario y contraseña.
2. Crear un Web Service apuntando a este repo:
   - Build command: `./mvnw clean package -DskipTests`
   - Start command: `java -jar target/backend-chat-0.0.1-SNAPSHOT.jar`
3. Agregar las variables de entorno `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD` con los datos del paso 1.
4. Render define `PORT` automáticamente — la app ya está configurada para respetarla.

## Estado

Proyecto en construcción — se irán agregando funcionalidades progresivamente.

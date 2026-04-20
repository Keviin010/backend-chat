# backend-chat

> **En construcción** — proyecto en desarrollo activo.

Chat en tiempo real construido con Spring Boot, WebSocket, STOMP y MySQL.

![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=flat&logo=mysql&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-000000?style=flat&logo=socketdotio&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18-BC4521?style=flat&logo=lombok&logoColor=white)

## Tecnologías

- Java 21
- Spring Boot 3.5
- WebSocket + STOMP
- MySQL
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


## Cómo ejecutar

1. Tener MySQL corriendo en `localhost:3306`
2. Crear la base de datos:

```sql
CREATE DATABASE chatdb;
```

3. Clonar el repositorio y ejecutar:

```bash
./mvnw spring-boot:run
```

El backend levanta en `http://localhost:8080`.

## Estado

Proyecto en construcción — se irán agregando funcionalidades progresivamente.

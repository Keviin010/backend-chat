# backend-chat

> En construcción — proyecto en desarrollo activo.

Chat en tiempo real construido con Spring Boot, WebSocket, STOMP y MongoDB.

![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-5.5-47A248?style=flat&logo=mongodb&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-000000?style=flat&logo=socketdotio&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18-BC4521?style=flat&logo=lombok&logoColor=white)

## Tecnologías

- Java 21
- Spring Boot 3.5
- WebSocket + STOMP
- MongoDB
- Lombok

## Funcionalidades

- [x] Configuración WebSocket + STOMP
- [x] Envío y recepción de mensajes
- [x] Notificación cuando un usuario se une o se va
- [x] Indicador de "está escribiendo"
- [x] Historial de mensajes con MongoDB
- [ ] Frontend Angular (en progreso)
- [ ] Despliegue en producción

## Cómo ejecutar

1. Tener MongoDB corriendo en `localhost:27017`
2. Clonar el repositorio
3. Ejecutar con Maven:

```bash
./mvnw spring-boot:run
```

El backend levanta en `http://localhost:8080`.

## Estado

Proyecto en construcción — se irán agregando funcionalidades progresivamente.

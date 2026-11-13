# backend-chat

Servidor de un chat en tiempo real hecho con Spring Boot, WebSocket (STOMP) y PostgreSQL.

![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat&logo=postgresql&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-000000?style=flat&logo=socketdotio&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)

Frontend: [frontend-chat](https://github.com/Keviin010/frontend-chat) (Angular 21, SockJS y StompJS).

## Qué hace

- Recibe mensajes y los reparte a todos los usuarios conectados.
- Avisa cuando alguien entra al chat y cuando se va.
- Avisa cuando alguien está escribiendo.
- Guarda los mensajes en PostgreSQL y entrega los últimos 50 a quien entra.
- Recorta nombres a 30 caracteres y mensajes a 255, e ignora los mensajes vacíos.

## Tecnologías

Java 21, Spring Boot 3.5, Spring WebSocket con STOMP y SockJS, Spring Data JPA, PostgreSQL y Lombok.

## Cómo ejecutarlo en tu computador

1. Ten PostgreSQL corriendo en `localhost:5432` y crea la base de datos:

```sql
CREATE DATABASE chatdb;
```

2. Ejecuta el backend:

```bash
./mvnw spring-boot:run
```

El servidor queda en `http://localhost:8080`. Si tu usuario o contraseña de PostgreSQL no son `postgres`, define `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD` antes de ejecutarlo.

Si ya tenías la tabla `messages` de una versión anterior, bórrala una vez con `DROP TABLE messages;` para que se cree con la fecha nueva.

3. Pruebas (no necesitan base de datos):

```bash
./mvnw test -Dtest=ChatControllerTests
```

## Rutas

| Tipo | Ruta | Para qué sirve |
| --- | --- | --- |
| HTTP GET | `/` | Muestra que el servidor está activo |
| HTTP GET | `/api/messages` | Devuelve los últimos 50 mensajes |
| WebSocket | `/chat-websocket` | Punto de conexión (SockJS) |
| STOMP envío | `/app/sendMessage` | Enviar un mensaje de tipo `CHAT` o `JOIN` |
| STOMP envío | `/app/typing` | Avisar que alguien escribe |
| STOMP suscripción | `/topic/chat` | Recibir mensajes, entradas y salidas |
| STOMP suscripción | `/topic/typing` | Recibir los avisos de "está escribiendo" |

## Variables de entorno

| Variable | Para qué sirve | Valor por defecto |
| --- | --- | --- |
| `SPRING_DATASOURCE_URL` | Dirección de la base de datos, empieza con `jdbc:postgresql://` | `jdbc:postgresql://localhost:5432/chatdb` |
| `SPRING_DATASOURCE_USERNAME` | Usuario de la base de datos | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña de la base de datos | `postgres` |
| `FRONTEND_URL` | Direcciones del frontend permitidas, separadas por coma | `http://localhost:4200` y `https://*.vercel.app` |
| `PORT` | Puerto del servidor, lo define el hosting | `8080` |

## Despliegue en Render

1. Crea una base de datos PostgreSQL gratuita (en Render o en Neon) y copia el host, el nombre de la base, el usuario y la contraseña.
2. Arma la dirección con este formato, tomando el host y el nombre de tu base:

```
jdbc:postgresql://HOST:5432/NOMBRE_BASE
```

Si el proveedor la exige, agrega `?sslmode=require` al final. Neon siempre la exige. La dirección interna de Render no la necesita.

3. En Render crea un Web Service con este repositorio. Render detecta el `Dockerfile` y lo construye. Elige el plan Free.
4. En Environment agrega `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD`.
5. Cuando termine el despliegue, abre la dirección del servicio. Debe responder `Chat en tiempo real: servidor activo`.

En el plan gratuito Render apaga el servicio tras 15 minutos sin visitas y tarda cerca de un minuto en despertar. La base de datos gratuita de Render expira a los 30 días.

## Autor

Kevin Dair Royo Cuesta

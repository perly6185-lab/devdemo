# DevAgent Demo

A small Spring Boot demo application exposing a handful of HTTP endpoints.

## HTTP Endpoints

| Method | Path | Description |
| ------ | ---- | ----------- |
| GET | `/hello` | Returns a demo greeting; an optional `name` query parameter personalizes it (e.g. `/hello?name=Ada`). |
| GET | `/hello2` | Returns the generic demo greeting (same as `/hello` without a name). |
| GET | `/greet` | Returns a personalized greeting; optional `name` and `lang` (`en` or `es`, defaults to English) query parameters. |
| GET | `/hola` | Returns a fixed Spanish greeting (`Hola`). |
| GET | `/goodbye` | Returns a farewell message. |
| GET | `/ping` | Returns `pong`; useful as a liveness check. |
| GET | `/health` | Returns `OK` to indicate the service is up. |
| GET | `/healthz` | Returns `{"healthy":true}` as JSON to indicate the service is healthy. |
| GET | `/time` | Returns the current server time as JSON: `{"epochMillis":<milliseconds since the Unix epoch>}`. |

## Running the app

```bash
mvn spring-boot:run
```

The server starts on port 8080 by default.

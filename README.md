# SeatLock

> A high-concurrency resource booking engine built to solve real-world distributed systems problems.

## About

**SeatLock** is a REST API designed for environments under heavy contention — think ticket sales for popular events or scheduling of shared resources where hundreds of simultaneous requests compete for the same slot.

The core challenges this project addresses:

- **Double-booking prevention** under concurrent request bursts
- **Strict idempotency** so that network retries never create duplicate reservations
- **Concurrency validation** with real integration tests against a live PostgreSQL instance

## Tech Stack

| Layer        | Technology                          |
|--------------|-------------------------------------|
| Language     | Java 21 (LTS)                       |
| Framework    | Spring Boot 4.x                     |
| Database     | PostgreSQL 16+                      |
| Migrations   | Flyway                              |
| Testing      | JUnit 5 + Testcontainers            |
| API Docs     | SpringDoc OpenAPI (Swagger UI)      |
| Container    | Docker / Docker Compose             |

## Prerequisites

- Java 21+
- Docker and Docker Compose

## Running Locally

**1. Start the database:**

```bash
docker compose up -d
```

**2. Run the application:**

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.  
Interactive API documentation (Swagger UI): `http://localhost:8080/swagger-ui.html`

## Running Tests

```bash
./mvnw test
```

> Tests use Testcontainers and require Docker to be running. A real PostgreSQL container is spun up automatically during the test suite.

## API Overview

| Method  | Endpoint                              | Description              |
|---------|---------------------------------------|--------------------------|
| `POST`  | `/api/v1/reservations`                | Create a new reservation |
| `GET`   | `/api/v1/reservations/{id}`           | Get reservation by ID    |
| `PATCH` | `/api/v1/reservations/{id}/cancel`    | Cancel a reservation     |

### Idempotency

Mutation requests (`POST`) support the `Idempotency-Key` header. Sending the same key and payload multiple times will always return the same response without creating duplicate records.

```
POST /api/v1/reservations
Idempotency-Key: <your-uuid>
```

## Reservation Lifecycle

```
PENDING → CONFIRMED
        → CANCELLED
        → EXPIRED
```

A cancelled or expired reservation cannot be re-confirmed.

## Project Status

**Work in progress** — initial setup complete, core implementation underway.

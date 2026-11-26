
### `README.md` for **northwind-frontend**

```markdown
# Northwind Frontend

Spring Boot 3.4 + Thymeleaf application that consumes the Northwind Data REST API and presents a clean, Bootstrap 5 UI.  
Designed as a practical SMB storefront baseline and as a portfolio piece showing full-stack Spring skills with a focus on maintainable server-side rendering.

**Tech Stack**
- Java 17
- Spring Boot 3.4.6
- Thymeleaf
- Spring Security + OAuth2 Client
- Bootstrap 5 / MDB (CDN)
- Maven (WAR packaging)
- Spring Boot Actuator

## Architecture Overview

This is the presentation layer. It is deliberately decoupled from the data service:
- Backend: dedicated Spring Data REST API (`northwind-data`)
- Frontend: Thymeleaf templates + Bootstrap 5 that call the API

This structure demonstrates separation of concerns, independent deployment, and realistic enterprise/SMB patterns.  
The UI is intentionally lightweight (no heavy SPA framework) so it remains easy for non-specialists to maintain.

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+
- Running instance of the [northwind-data](https://github.com/llin0001itjobs/northwind-data) service (default `http://localhost:8080`)

### Run locally
```bash
git clone https://github.com/llin0001itjobs/northwind-frontend.git
cd northwind-frontend
./mvnw spring-boot:run

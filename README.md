# FleetManager API 🚀

A production-grade, cloud-native REST API built with **Java 21 LTS** and **Spring Boot 3.5**. This project serves as a showcase for modern DevOps practices, including containerization, orchestration, and automated health monitoring.

## 🛠 Tech Stack
- **Backend:** Spring Boot, Spring Actuator (Metrics & Health)
- **Runtime:** Java 21 LTS
- **Build Tool:** Maven (using Maven Wrapper)
- **Containerization:** Docker (Multi-stage builds, non-root user security)
- **Orchestration:** Kubernetes (Deployments, Services, Liveness/Readiness Probes)
- **Packaging:** Helm Charts (Dynamic scaling & Release management)

## 🏗 Key Features
- **Unit Tested:** REST endpoints verified via `MockMvc`.
- **Zero-Downtime Ready:** Implemented Kubernetes Liveness and Readiness probes using Spring Actuator.
- **Optimized Images:** Multi-stage Docker builds using Eclipse Temurin JRE for a minimal security footprint.
- **Scalable:** Horizontally scalable via Helm parameters.

## 🚦 Quick Start

### Run Tests
```bash
./mvnw clean test

### Local Docker Build
```bash
docker build -t fleet-manager:v1 .
```
Kubernetes Deployment via Helm

```bash
helm install my-fleet ./fleet-manager-chart --set replicaCount=3
```

## 📈  Monitoring
Access health and metrics via Spring Actuator:
```link 
http://localhost:30080/actuator/health
http://localhost:30080/api/vehicles
```

## 🚦 API Usage & Troubleshooting

### Local Access (Docker/Dev)
Once running, you can interact with the Fleet Manager API at:
- **List All Vehicles:** `GET http://localhost:8080/api/vehicles`
- **Vehicle by ID:** `GET http://localhost:8080/api/vehicles/V1`

### Kubernetes Access
The service is exposed via NodePort `30080`:
- **API Entry:** `http://localhost:30080/api/vehicles`
- **Actuator Health:** `http://localhost:30080/actuator/health`

### 🔧 Troubleshooting Tips
- **Port Mapping:** If the container starts but you cannot reach the API, verify your port mapping: `docker run -p 8080:8080 fleet-manager:v1`.
- **Endpoint Discovery:** To see a full list of registered URL paths and Actuator mappings, visit: `http://localhost:8080/actuator/mappings`.
- **Image Architecture:** Ensure your local Java version (21) matches the [Eclipse Temurin](https://hub.docker.com) base image in the Dockerfile.

---

## 🧪 Testing & Database Persistence

### Local Database (Docker Sidecar)
To support local development without a system-wide PostgreSQL installation, run the database as a container:
```bash
docker run --name fleet-db -e POSTGRES_PASSWORD=password -e POSTGRES_DB=fleetdb -p 5432:5432 -d postgres:15
```

## Running the Application
he application uses Spring Profiles to switch between local Docker and Kubernetes internal networking.
Local Development:
``` 
./mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
Unit Testing:
./mvnw clean test (Mocks the repository layer via MockitoBean for isolated CI/CD execution).
```

### 🚦 API Documentation (Happy & Sad Paths)


| Scenario | Method | Endpoint | Expected Result |
| :--- | :--- | :--- | :--- |
| **List All** | `GET` | `/api/vehicles` | `200 OK` - Array of 3 seeded vehicles |
| **Search Found** | `GET` | `/api/vehicles/V1` | `200 OK` - Tesla Model 3 Details |
| **Search Missing** | `GET` | `/api/vehicles/999` | `404 Not Found` - Standardized Error JSON |


---

## Error Handling
The project implements a Global Exception Handler using @ControllerAdvice. All errors return a consistent structure:
```json
{
  "timestamp": "2026-02-22T03:30:00",
  "message": "Vehicle not found with ID: 999",
  "details": "uri=/api/vehicles/999"
}

```
### 🚀 Final Sync to Main
Now that your documentation is as polished as your code, perform the final merge:

1. **Commit to Feature:**
   ```bash
   git add README.md
   git commit -m "docs: update README with database sidecar and testing instructions"
   git push origin feature/add-postgres-persistence
```
Merge to Main:
```bash
git checkout main
git merge feature/add-postgres-persistence
git push origin main
```
## 🎡 Jenkins Check
Now that your main branch is perfect, are you ready to run the Jenkins Docker command to start the automation phase?
```powershell 
docker run -d -p 8081:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --name jenkins-server jenkins/jenkins:lts
```
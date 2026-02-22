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


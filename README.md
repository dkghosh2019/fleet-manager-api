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
---


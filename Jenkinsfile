pipeline {
    agent any

    options {
        skipDefaultCheckout()
    }

    environment {
        DOCKER_IMAGE = "fleet-manager:v1"
        CHART_PATH   = "./fleet-manager-chart"
        REPO_URL     = "https://github.com/dkghosh2019/fleet-manager-api.git"
    }

    stages { // <--- This was missing
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'chmod +x mvnw'
                // Forcing H2 on one line to avoid Groovy string issues
                sh './mvnw clean test -Dspring.profiles.active=test -Dspring.datasource.url=jdbc:h2:mem:testdb -Dspring.datasource.driverClassName=org.h2.Driver -Dspring.jpa.database-platform=org.hibernate.dialect.H2Dialect'
            }
        }

        stage('Build Artifact') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }


        stage('Kubernetes Deploy') {
            steps {
                // Use the insecure flag to ignore the certificate name mismatch on local Docker Desktop
                sh "helm upgrade --install my-fleet ${CHART_PATH} --kube-insecure-skip-tls-verify"
            }
        }

    } // <--- End of stages

    post {
        success { echo '✅ Deployment successful!' }
        failure { echo '❌ Pipeline failed. Check logs for details.' }
    }
}

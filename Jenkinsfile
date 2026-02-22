pipeline {
    agent any

    options {
        // This stops Jenkins from trying its own broken checkout
        skipDefaultCheckout()
    }

    environment {
        DOCKER_IMAGE = "fleet-manager:v1"
        CHART_PATH   = "./fleet-manager-chart"
        REPO_URL     = "https://github.com/dkghosh2019/fleet-manager-api.git"
    }

    stages {
        stage('Checkout') {
            steps {
                // Now this is the ONLY place checkout happens
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean test -Dspring.profiles.active=local'
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
                sh "helm upgrade --install my-fleet ${CHART_PATH}"
            }
        }
    }

    post {
        success { echo '✅ Deployment successful!' }
        failure { echo '❌ Pipeline failed. Check logs for details.' }
    }
}

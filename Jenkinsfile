pipeline {
    agent any

    environment {
        // Defining variables for reusability
        DOCKER_IMAGE = "fleet-manager:v1"
        CHART_PATH   = "./fleet-manager-chart"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull the latest code from GitHub
                checkout scm
            }
        }

        stage('Unit Tests') {
            steps {
                // Run Maven tests using the 'local' profile we created
                sh './mvnw clean test -Dspring.profiles.active=local'
            }
        }

        stage('Build Artifact') {
            steps {
                // Package the JAR file (skipping tests since they passed above)
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                // Build the image using the multi-stage Dockerfile
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                // Use Helm to upgrade/install the release into your cluster
                sh "helm upgrade --install my-fleet ${CHART_PATH}"
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs for details.'
        }
    }
}

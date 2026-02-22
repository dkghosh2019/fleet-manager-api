pipeline {
    agent any

    environment {
        // Defining variables for reusability
        DOCKER_IMAGE = "fleet-manager:v1"
        CHART_PATH   = "./fleet-manager-chart"
        // Use the full URL directly to avoid the "empty string" error
        REPO_URL = "https://github.com/dkghosh2019/fleet-manager-api.git"
    }

    stages {
        stage('Checkout') {
            steps {
               // Simplified git checkout
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Unit Tests') {
            steps {
                // Run Maven tests using the 'local' profile we created
               // sh './mvnw clean test -Dspring.profiles.active=local'
                sh 'chmod +x mvnw' // Ensures script can run
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

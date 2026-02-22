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
        stage('Unit Tests') {
            steps {
                sh 'chmod +x mvnw'
                // Pass H2 settings directly to Maven to bypass file issues
                sh """
                ./mvnw clean test \
                -Dspring.profiles.active=test \
                -Dspring.datasource.url=jdbc:h2:mem:testdb \
                -Dspring.datasource.driverClassName=org.h2.Driver \
                -Dspring.jpa.database-platform=org.hibernate.dialect.H2Dialect
                """
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean test -Dspring.profiles.active=test'
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

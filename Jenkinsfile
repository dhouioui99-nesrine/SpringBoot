pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = "tonuser"
        SONAR_HOST_URL = "http://sonarqube:9000"
        SONAR_LOGIN = credentials('sonar-token')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/tonrepo/backend-ctt.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=backend-ctt'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                sh '''
                    docker build -t $DOCKERHUB_USERNAME/backend-ctt:latest .
                    echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin
                    docker push $DOCKERHUB_USERNAME/backend-ctt:latest
                '''
            }
        }
    }
}

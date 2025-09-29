pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21' 
            args '-v /root/.m2:/root/.m2 --network springboot_app-network'
        }
    }

    environment {
        DOCKER_IMAGE = "nesrinedh/backend:latest"
        DOCKERHUB_USERNAME = "nesrinedh"
        DOCKERHUB_PASSWORD = credentials('dockerhub-pass')
         SONAR_HOST_URL = "http://sonarqube:9000"
         SONAR_LOGIN = credentials('sonarqube')
    }

    stages {
        
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
    

        stage('Build & Test') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
        steps {
            withSonarQubeEnv('SonarQube') {
               sh 'mvn clean package sonar:sonar -Dsonar.projectKey=backend -DskipTests -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN'

            }
        }
    }

  stage('Docker Build & Push') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    // Login Docker
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                    // Push Docker image
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
    
    }
}

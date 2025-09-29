pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21' 
            args '-v /root/.m2:/root/.m2 --network springboot_app-network'
        }
    }

    environment {
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
            // Injection des identifiants DockerHub
            withCredentials([usernamePassword(
                credentialsId: 'dockerhub-pass', 
                usernameVariable: 'nesrinedh', 
                passwordVariable: 'dckr_pat_ba1jYsIJrhzVG51s8H4CzHz0RE8'
            )]) {
                sh """
                    docker build -t $DOCKER_IMAGE .
                    echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                    docker push $DOCKER_IMAGE
                """
            }
        }
    }
}

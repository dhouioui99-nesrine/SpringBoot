pipeline {
    agent none
    environment {
        DOCKER_IMAGE = "nesrinedh/backend:latest"
        SONAR_HOST_URL = "http://sonarqube:9000"
        SONAR_LOGIN = credentials('sonarqube')
    }
    stages {
        stage('Build & SonarQube') {
            agent {
                docker {
                    image 'maven:3.9.9-eclipse-temurin-21'
                    args '--network springboot_app-network'
                }
            }
            steps {
                checkout scm
                sh 'mvn clean package -DskipTests'
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn clean package sonar:sonar -Dsonar.projectKey=backend -DskipTests -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN'

                }
            }
        }

    stage('Docker Build & Push') {
    agent {
        docker {
            image 'docker:24.0.2-dind'
            args '--network springboot_app-network --privileged -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    steps {
        withCredentials([usernamePassword(
            credentialsId: 'dockerhub-pass', 
            usernameVariable: 'DOCKER_USERNAME', 
            passwordVariable: 'DOCKER_PASSWORD'
        )]) {
            sh """
                docker build -t $DOCKER_IMAGE .
                echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin
                docker push $DOCKER_IMAGE
            """
        }
    }
}
    }
}

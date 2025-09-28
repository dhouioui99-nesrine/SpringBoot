pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21' 
            args '-v /root/.m2:/root/.m2'
        }
    }

    environment {
        DOCKERHUB_USERNAME = "nesrinedh"
        DOCKERHUB_PASSWORD = credentials('dockerhub-pass')
         SONAR_HOST_URL = "http://172.20.0.2:9000"
         SONAR_LOGIN = credentials('SonarQube')
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
                sh '''
                    docker build -t $DOCKERHUB_USERNAME/backend:latest .
                    echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin
                    docker push $DOCKERHUB_USERNAME/backend:latest
                '''
            }
        }
    }
}

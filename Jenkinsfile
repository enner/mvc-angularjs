pipeline {
    agent none 
    stages {
        stage('GetUserCredential') {
        // Requires Credential setup (MyCredentialID)
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'testcredid', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            sh '''
                set +x
                echo "$USERNAME" > output.txt
               '''
        }
        }
        stage('Build') {
            agent { docker 'maven:3-alpine' } 
            steps {
                echo 'Hello, Maven'
                sh 'mvn -version'
                echo '$USERNAME'
            }
        }
        stage('Test') {
            agent { docker 'openjdk:8-jre' } 
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
    }
}

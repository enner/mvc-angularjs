pipeline {
    agent none 
    stages {
        stage('Build') {
            agent { docker 'maven:3-alpine' } 
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'testcredid', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']])
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

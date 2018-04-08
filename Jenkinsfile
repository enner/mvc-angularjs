pipeline {
    agent any
    
	parameters {
        choice(choices: '1-INT\n2-TEST\n3-PROD', description: 'CloudFoundry Deployment Space', name: 'cfspace')
    }
     
    stages {
        stage('Build') {
            agent { docker 'maven:3-alpine' } 
            steps {
            		sh "echo ${params.cfspace}"
                echo 'Hello, Maven'
                sh '''
                mvn -version
                java -version
                '''
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
